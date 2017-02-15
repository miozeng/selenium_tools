package com.mio.selenium.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mio.selenium.dao.SeModulDao;
import com.mio.selenium.dao.SeProjectDao;
import com.mio.selenium.dao.SeTestCaseDao;
import com.mio.selenium.domain.SeModul;
import com.mio.selenium.domain.SeProject;
import com.mio.selenium.domain.SeTestCase;
import com.mio.selenium.util.AsciidocConvert;
import com.mio.selenium.util.MyStringUtil;
import com.mio.selenium.util.RunCaseUtil;

@Service
public class SeProjectServiceImpl implements SeProjectService {

	private static final Logger logger = LoggerFactory.getLogger(SeProjectServiceImpl.class);
	
	
	@Autowired
	private SeProjectDao seProjectDao;
	
	@Autowired
	private SeModulDao modulDao;
	
	@Autowired 
	private SeTestCaseDao seTestCaseDao;
	
	
	@Value("${path}")
	private String lpath;
	
	@Value("${type}")
	private String type;
	
	@Value("${ie.driver}")
	private String iedrivers;
	
	@Value("${chrome.driver}")
	private String chormedrivers;
	
	@Value("${firefox.driver}")
	private String firefoxdrivers;

	@Override
	public SeProject save(SeProject project) {
		if (project.getProjectId() == null) {
			project.setCreateDate(new Date());
		}
		project.setLastModifiedDate(new Date());
		seProjectDao.save(project);
		return project;
	}

	@Override
	public SeProject findById(Long id) {
		return seProjectDao.findById(id);
	}

	@Override
	public List<SeProject> findAll() {
		return seProjectDao.findAll();
	}

	@Override
	public Map<String,Object> runProjectTest(Long id, boolean iscreate ) {
		Map<String,Object> retMap= new HashMap<String,Object>();
		boolean isrunOk = true;
		boolean isdocOk = true;
		logger.info("start to excute project");
		StringBuffer sb = new StringBuffer();
		SeProject project = seProjectDao.findById(id);
		sb.append("= "+MyStringUtil.changeDateToStr(new Date())+System.getProperty("line.separator"));
		sb.append(""+System.getProperty("line.separator"));
		sb.append("*"+project.getProjectName()+"*::"+System.getProperty("line.separator"));
		sb.append(project.getProjectDesc()+System.getProperty("line.separator"));
		
		String path = lpath + project.getProjectName() + "\\" ;
		 File file =  new File(path);
			if(file.exists()){
				file.mkdirs();
			}
		List<SeModul> moduls = modulDao.findByProperty("project.projectId", id);
		logger.info("moduls size =: " +moduls.size());
		for (SeModul seModul : moduls) {
			logger.info(seModul.getModulName() +" is start....");
			sb.append(""+System.getProperty("line.separator")+System.getProperty("line.separator"));
			sb.append(". *" + seModul.getModulName() + "*"+System.getProperty("line.separator"));
			sb.append("+"+System.getProperty("line.separator"));
			sb.append("excute time:"+MyStringUtil.changeDateToStr(new Date())+System.getProperty("line.separator"));
			sb.append(""+System.getProperty("line.separator"));
			sb.append("* modul description"+System.getProperty("line.separator"));
			sb.append("+"+System.getProperty("line.separator"));
			sb.append(seModul.getModulDesc()+System.getProperty("line.separator") );
			int failureNum = 0;
			int successNum = 0;
			List<SeTestCase> stcs = seTestCaseDao.findByProperty("modul.modulId", seModul.getModulId());
			logger.info("SeTestCase size =: " +stcs.size());
			StringBuffer sb2 = new StringBuffer();
			sb2.append("= msg"+System.getProperty("line.separator"));
			sb2.append(""+System.getProperty("line.separator"));
			sb2.append("*"+seModul.getModulName()+"* ::"+System.getProperty("line.separator"));
			sb2.append("excute time:" + MyStringUtil.changeDateToStr(new Date())+System.getProperty("line.separator"));
			sb2.append("+"+System.getProperty("line.separator"));
			sb2.append(seModul.getModulDesc()+System.getProperty("line.separator"));
			for (SeTestCase testcase : stcs) {
				 WebDriver driver = RunCaseUtil.getDriver(type, chormedrivers, firefoxdrivers, iedrivers);
				String ret = RunCaseUtil.runcase(driver,testcase,sb2,lpath);
				if (!"".equals(ret)) {
					isrunOk = false;
					failureNum++;
				}else{
					successNum++;
				}
			}
			
			logger.info(seModul.getModulName() +"start od Asciidoc doc process" );
			if(iscreate){
				boolean reta = AsciidocConvert.createAsciidoc(sb2, path +seModul.getModulName()+".adoc");
				boolean reth =AsciidocConvert.converToHtml(path + seModul.getModulName()+".adoc", path + seModul.getModulName()+".html", false);
				if(!reta || !reth){
					isdocOk = false;
				}
			}
			logger.info(seModul.getModulName() +" end of Asciidoc doc process" );
			
			if(failureNum >0){
				logger.error(seModul.getModulName() +" failure");
			}else{
				logger.info(seModul.getModulName() +" success");
			}
			sb.append("* tests result"+System.getProperty("line.separator"));
			sb.append("+"+System.getProperty("line.separator"));
			sb.append("A total of " + (failureNum+successNum) + "testcase ," + failureNum + " failure " + successNum + " success"+System.getProperty("line.separator"));
			
		}

		if(iscreate){
			logger.info(project.getProjectName() + "start od Asciidoc doc process" );
			boolean reta =AsciidocConvert.createAsciidoc(sb, path + project.getProjectName()+".adoc");
			boolean reth =AsciidocConvert.converToHtml(path + project.getProjectName()+".adoc", path + project.getProjectName()+".html", false);
			if(!reta || !reth){
				isdocOk = false;
			}
			logger.info(project.getProjectName() + " end of Asciidoc doc process" );
		}
		retMap.put("isrunOk", isrunOk);
		retMap.put("isdocOk", isdocOk);
		return retMap;
	}

}
