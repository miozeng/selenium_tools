package com.mio.selenium.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.mio.selenium.domain.SeModul;
import com.mio.selenium.domain.SeTestCase;
import com.mio.selenium.domain.SeTestStep;
import com.mio.selenium.jsonview.GenericJsonView;
import com.mio.selenium.service.SeModulService;
import com.mio.selenium.service.SeTestCaseService;
import com.mio.selenium.service.SeTestStepSevice;
import com.mio.selenium.util.CvsUtil;
import com.mio.selenium.util.RunCaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "/testcase")
@Api(value = "/testcase", description = "TestCase API")
public class TestCaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestCaseController.class);
	@Autowired
	private SeTestCaseService seTestCaseService;
	
	@Autowired
	private SeModulService modulService;
	
	@Autowired
	private SeTestStepSevice testStepSevice;
	
//	@Value("${type}")
	 @Value("#{configProperties['type']}") 
	private String type;
	
//	@Value("${ie.driver}")
	@Value("#{configProperties['ie.driver']}") 
	private String iedrivers;
	
//	@Value("${chrome.driver}")
	@Value("#{configProperties['chrome.driver']}") 
	private String chormedrivers;
	
//	@Value("${firefox.driver}")
	@Value("#{configProperties['firefox.driver']}") 
	private String firefoxdrivers;
	
//	@Value("${temppath}")
//	@Value("#{configProperties['url']}") 
//	private String url;
	@RequestMapping(value = "/list/{modulId}", method = RequestMethod.GET)
	@ApiOperation(notes = "find  TestCase .  ", httpMethod = "GET", value = "find all ")
	@JsonView(GenericJsonView.Summary.class)
	public List<SeTestCase> getList(@PathVariable Long modulId) {
		return seTestCaseService.findAllByModul(modulId);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ApiOperation(notes = "save  TestCase .  ", httpMethod = "POST", value = "save ")
	public String postUser(@RequestBody SeTestCase testCase) {
		seTestCaseService.save(testCase);
		return "success";
	}
	
	
	@RequestMapping(value = "/findById/{caseId}", method = RequestMethod.GET)
	@ApiOperation(notes = "findById ", httpMethod = "GET", value = "findById")
	@JsonView(GenericJsonView.Summary.class)
	public SeTestCase findById(@PathVariable Long caseId){
		return seTestCaseService.findById(caseId);
	}

	@RequestMapping(value = "/cvsimport/{modulId}", method = RequestMethod.POST)
	@ApiOperation(notes = "import  testcase in cvs.  ", httpMethod = "POST", value = "import  testcase in cvs. ")
	public Map<String, Object> importCvs(@RequestParam("file")MultipartFile file,@PathVariable Long modulId) {
		logger.info("excute  testcase in cvs..file...");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ret", true);
		File tmpfile = new File("C:\\tmp\\selenium\\" + file.getOriginalFilename());

		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), tmpfile);
			SeTestCase tcase = CvsUtil.cvsToCase(tmpfile);
			SeModul modul = modulService.findById(modulId);
			tcase.setModul(modul);
			seTestCaseService.save(tcase);
		    map.put("ret", true);
			
		} catch (Exception e) {
			map.put("ret", false);
			map.put("msg", "something error");
			logger.error("import error: " ,e);
		}
		if (tmpfile.exists()) {
			tmpfile.delete();
		}
		return map;
	}


	@RequestMapping(value = "/cvsexcut", method = RequestMethod.POST)
	@ApiOperation(notes = "sexcut  testcase in cvs.  ", httpMethod = "POST", value = "excute  testcase in cvs. ")
	public Map<String, Object> excuteCvs(MultipartFile file) {
		logger.info("excute  testcase in cvs..file...");
		String temppath = RunCaseUtil.getSysPath();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ret", true);
		File tmpfile = new File("C:\\tmp\\selenium\\" + file.getOriginalFilename());

		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), tmpfile);
			SeTestCase tcase = CvsUtil.cvsToCase(tmpfile);
			 WebDriver driver = RunCaseUtil.getDriver(type, chormedrivers, firefoxdrivers, iedrivers);
			String ret = RunCaseUtil.runcase(driver,tcase,null,temppath);
			 map.put("ret", true);
			if(!"".equals(ret)){
				map.put("ret", false);
				map.put("msg", ret);
			}
		   
		} catch (Exception e) {
			map.put("ret", false);
			map.put("msg", "something error");
			logger.error("import error: " ,e);
		}
		if (tmpfile.exists()) {
			tmpfile.delete();
		}
		return map;
	}
	@RequestMapping(value = "/excutecase/{caseId}", method = RequestMethod.GET)
	@ApiOperation(notes = "excute  testcase in testcase table.  ", httpMethod = "GET", value = "excute  testcase in testcase table.  ")
	public Map<String, Object> testCaseExcute(@PathVariable Long caseId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ret", true);
		String temppath = RunCaseUtil.getSysPath();
		SeTestCase tcase = seTestCaseService.findById(caseId);
		try {
			 WebDriver driver = RunCaseUtil.getDriver(type, chormedrivers, firefoxdrivers, iedrivers);
			String ret = RunCaseUtil.runcase(driver,tcase,null,temppath);
			if (!"".equals(ret)) {
				map.put("ret", false);
				map.put("msg", ret);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ret", false);
			map.put("msg", "something error");
		}

		return map;
	}

	@RequestMapping(value = "/casetocvs", method = RequestMethod.GET)
	@ApiOperation(notes = "testCase To Cvs .  ", httpMethod = "GET", value = "testCase To Cvs . ")
	public void testCaseToCvs(HttpServletResponse res, HttpServletRequest request) throws IOException {
		logger.info("start export cvs................");
		String caseIdStr=request.getParameter("caseId");
		Long caseId = Long.parseLong(caseIdStr);
		SeTestCase tcase = seTestCaseService.findById(caseId);
		List<SeTestStep> steps = testStepSevice.findByTestCase(caseId);
		tcase.setSteps(steps);
		File file = CvsUtil.caseToCvs(tcase, "C:\\tmp\\selenium\\cvstemp");
		OutputStream os = res.getOutputStream();
		FileInputStream in = null;
		try {
			res.reset();
			res.setHeader("Content-Disposition",
					"attachment; filename=" + tcase.getCaseNo() + "_" + new String(tcase.getCaseName().getBytes("gbk"),"iso-8859-1")  + ".csv");
			res.setContentType("application/octet-stream; charset=utf-8");
			in = new FileInputStream(file);
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				os.write(buffer, 0, len);
			}
		
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
			if (file.exists()) {
				file.delete();
			}
		}

	}
	

	@RequestMapping(value = "/delete/{caseId}", method = RequestMethod.GET)
	@ApiOperation(notes = "delete ", httpMethod = "GET", value = "delete")
	public void delete(@PathVariable Long caseId){
		seTestCaseService.delete(caseId);
	}

	
}
