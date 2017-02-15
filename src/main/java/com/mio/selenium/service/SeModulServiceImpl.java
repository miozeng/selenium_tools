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
import com.mio.selenium.dao.SeTestCaseDao;
import com.mio.selenium.domain.SeModul;
import com.mio.selenium.domain.SeProject;
import com.mio.selenium.domain.SeTestCase;
import com.mio.selenium.util.AsciidocConvert;
import com.mio.selenium.util.MyStringUtil;
import com.mio.selenium.util.RunCaseUtil;

@Service
public class SeModulServiceImpl implements SeModulService {

	private static final Logger logger = LoggerFactory.getLogger(SeModulServiceImpl.class);

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
	public List<SeModul> findList(Long projectId) {

		return modulDao.findByProperty("project.projectId", projectId);
	}

	@Override
	public SeModul saveOrUpdate(SeModul modul) {
		if (modul.getModulId() == null) {
			modul.setCreateDate(new Date());
		}
		modul.setLastModifiedDate(new Date());
		modulDao.saveOrUpdate(modul);
		return modul;
	}

	@Override
	public SeModul findById(Long modulId) {
		return modulDao.findById(modulId);
	}

	@Override
	public Map<String, Object> runModulTest(Long id, boolean iscreate) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		boolean isrunOk = true;
		boolean isdocOk = true;
		SeModul seModul = modulDao.findById(id);
		SeProject project = seModul.getProject();

	

		String path = lpath + project.getProjectName() + "\\";
        File file =  new File(path);
		if(file.exists()){
			file.mkdirs();
		}
		List<SeTestCase> stcs = seTestCaseDao.findByProperty("modul.modulId", seModul.getModulId());
		logger.info("SeTestCase size =: " + stcs.size());
		StringBuffer sb2 = new StringBuffer();
		sb2.append("= msg" + System.getProperty("line.separator"));
		sb2.append("" + System.getProperty("line.separator"));
		sb2.append("*" + seModul.getModulName() + "* ::" + System.getProperty("line.separator"));
		sb2.append("excute time:" + MyStringUtil.changeDateToStr(new Date()) + System.getProperty("line.separator"));
		sb2.append("+" + System.getProperty("line.separator"));
		sb2.append(seModul.getModulDesc() + System.getProperty("line.separator"));
		for (SeTestCase testcase : stcs) {
			 WebDriver driver = RunCaseUtil.getDriver(type, chormedrivers, firefoxdrivers, iedrivers);
			String ret = RunCaseUtil.runcase(driver,testcase, sb2,path);
			if (!"".equals(ret)) {
				isrunOk = false;
			}
		}

		logger.info(seModul.getModulName() + "start od Asciidoc doc process");
		if(iscreate){
			boolean reta = AsciidocConvert.createAsciidoc(sb2, path+seModul.getModulName()+".adoc");
			boolean reth =AsciidocConvert.converToHtml(path +seModul.getModulName()+".adoc", path +seModul.getModulName()+".html", false);
			if(!reta || !reth){
				isdocOk = false;
			}
		}
		logger.info(seModul.getModulName() + " end of Asciidoc doc process");

	
	
		retMap.put("isrunOk", isrunOk);
		retMap.put("isdocOk", isdocOk);
		return retMap;
	}

}
