package com.mio.selenium.util;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mio.selenium.domain.SeDoType;
import com.mio.selenium.domain.SeTestCase;
import com.mio.selenium.domain.SeTestStep;

public class RunCaseUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(RunCaseUtil.class);
	
	public static String runcase( WebDriver driver,SeTestCase tcase,StringBuffer sb,String lpath) {
		if(sb != null){
			sb.append(""+System.getProperty("line.separator"));
			sb.append(". *"+tcase.getCaseName()+"*"+System.getProperty("line.separator"));
			sb.append("     "+ tcase.getCaseDesc()+System.getProperty("line.separator"));
		}
		logger.info(" excute testcase " + tcase.getCaseName());
		String caseRet = "";
		List<SeTestStep> steps = tcase.getSteps();
		Collections.sort(steps);

		// getpath
		String path = lpath + tcase.getCaseName();
		if (tcase.getModul() != null && tcase.getModul().getProject() != null) {
			path = lpath + tcase.getModul().getProject().getProjectName() + "\\" + tcase.getModul().getModulName()
					+ "\\" + tcase.getCaseName();
		}
		
		for (int i = 0; i < steps.size(); i++) {
			SeTestStep step = steps.get(i);
			if(sb != null){
				sb.append(""+System.getProperty("line.separator"));
				sb.append(".. "+step.getStepName()+System.getProperty("line.separator"));
				sb.append("*** table."+System.getProperty("line.separator"));
				sb.append("+"+System.getProperty("line.separator"));
				sb.append("[cols=\"8*\"]"+System.getProperty("line.separator"));
				sb.append("|==="+System.getProperty("line.separator"));
				sb.append("|stepno"+System.getProperty("line.separator"));
				sb.append("|name"+System.getProperty("line.separator"));
				sb.append("|url"+System.getProperty("line.separator"));
				sb.append("|element_value"+System.getProperty("line.separator"));
				sb.append("|getType"+System.getProperty("line.separator"));
				sb.append("|doType"+System.getProperty("line.separator"));
				sb.append("|excptValue"+System.getProperty("line.separator"));
				sb.append("|result"+System.getProperty("line.separator"));
				sb.append(""+System.getProperty("line.separator"));
				sb.append("|" + step.getStepNo()+System.getProperty("line.separator"));
				sb.append("|" + step.getStepName()+System.getProperty("line.separator"));
				sb.append("|" + step.getUrl()+System.getProperty("line.separator"));
				sb.append("|" + step.getElementValue()+System.getProperty("line.separator"));
				sb.append("|" + step.getGetType()+System.getProperty("line.separator"));
				sb.append("|" + step.getDoType()+System.getProperty("line.separator"));
				sb.append("|" + step.getExcptValue()+System.getProperty("line.separator"));
			}
			boolean ret = RunCaseUtil.testCaseProcessor(driver, step);
		
			if (!ret) {
				caseRet += step.getStepNo() + "_" + step.getStepName() + " has error test failure;";
				logger.error(caseRet);
				if(sb != null){
					sb.append("|failure"+System.getProperty("line.separator"));
					sb.append("|==="+System.getProperty("line.separator"));
				}
			}else{
				if(sb != null){
					sb.append("|success"+System.getProperty("line.separator"));
					sb.append("|==="+System.getProperty("line.separator"));
				}
				logger.info( step.getStepNo() + "_" + step.getStepName() + " test success;");
			}
			
			if (!ret || step.getTakePhoto()) {
				logger.info(" excute failure or need to take photo....");
				RunCaseUtil.getScreen(driver, step, path,sb);
			}

		}
		
		driver.quit();
		return caseRet;
	}
	
	public static boolean getScreen(WebDriver driver, SeTestStep step, String path,StringBuffer sb) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filePath = path + "\\screenshot_" + step.getTestCase().getCaseName() + "_" + step.getStepName() + ".png";
			logger.info("image path =:" + filePath);
			FileUtils.copyFile(scrFile, new File(filePath));
			if(sb != null){
			sb.append(""+System.getProperty("line.separator"));
			sb.append("*** image."+System.getProperty("line.separator"));
			sb.append("+"+System.getProperty("line.separator"));
			sb.append(
					"image::sunset.jpg[caption=\"Figure 1: \", title=\"" + step.getStepName() +"\", alt=\"img\", width=\"150\", "
							+ "height=\"100\", link=\""+filePath+"\"]"+System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			System.out.println("");
			return false;
		}
		return true;
	}
	
	public static boolean testCaseProcessor(WebDriver driver, SeTestStep step) {

		try {
			List<WebElement> elements = null;
			WebElement element = null;
			boolean result = true;

			// get test elemnt
			if (step.getGetType() != null) {
				logger.info("get WebElement....gettype=:"+step.getGetType() +" value=:" +step.getElementValue());
				switch (step.getGetType()) {
				case byClass:
					elements = driver.findElements(By.className(step.getElementValue()));
					break;
				default:
					element = RunCaseUtil.getWebElemet( step, driver);
					break;
				}

			}

			// do something
			if (step.getDoType() != null && step.getDoType() != SeDoType.noThing) {
				logger.info("do somthimg ....dotype=:" + step.getDoType() );
				result = doSomething(step, element, elements, driver);
			}
			
		
			return result;
		} catch (Exception e) {
			logger.error("Error : ",e);
			return false;
		}
	}

	private static WebElement getWebElemet(SeTestStep e, WebDriver driver) {
		WebElement element = null;
		switch (e.getGetType()) {
		case byId:
			element = driver.findElement(By.id(e.getElementValue()));
			break;
		case byTag:
			element = driver.findElement(By.tagName(e.getElementValue()));
			break;
		case byname:
			element = driver.findElement(By.name(e.getElementValue()));
			break;
		case byLink:
			element = driver.findElement(By.linkText(e.getElementValue()));
			break;
		case byPartialLink:
			element = driver.findElement(By.partialLinkText(e.getElementValue()));
			break;
		case byCss:
			element = driver.findElement(By.cssSelector(e.getElementValue()));
			break;
		case byXpath:
			element = driver.findElement(By.xpath(e.getElementValue()));
			break;

		default:
			break;
		}
		return element;
	}

	private static boolean doSomething(SeTestStep step, WebElement element, List<WebElement> elements,
			WebDriver driver) {
		boolean result = true;
		switch (step.getDoType()) {
		case FetchingPage:
			driver.get(step.getUrl());
			break;
		case getText:
			result = step.getExcptValue().equals(element.getText());
			break;
		case click:
			element.click();
			break;
		case sendKeys:
			element.sendKeys(step.getExcptValue());
			break;
		case clear:
			element.clear();
			break;
		case selectByIndex:
			Select select = new Select(element);
			select.selectByIndex(Integer.parseInt(step.getExcptValue()));
			break;
		case selectByVisibleText:
			Select select2 = new Select(element);
			select2.selectByVisibleText(step.getExcptValue());
			break;
		case selectByValue:
			Select select3 = new Select(element);
			select3.selectByValue(step.getExcptValue());
			break;
		case isSelected:

			result = (element.isSelected() == Boolean.parseBoolean(step.getExcptValue()));
			break;
		case isEnabled:
			result = (element.isEnabled() == Boolean.parseBoolean(step.getExcptValue()));
			break;
		case isDisplayed:
			result = (element.isDisplayed() == Boolean.parseBoolean(step.getExcptValue()));
			break;
		default:
			break;
		}
		return result;
	}
	
	public static WebDriver getDriver(String type, String chormedrivers,String firefoxdrivers,String iedrivers){
        WebDriver driver = null;
        if("chrome".equals(type)){
    		System.setProperty("webdriver.chrome.driver", chormedrivers);
    		driver = new ChromeDriver();
        }else if("firefox".equals(type)){
            System.setProperty("webdriver.gecko.driver",firefoxdrivers);
    		driver = new FirefoxDriver();  
        }else{
   		    System.setProperty("webdriver.ie.driver", iedrivers);
   	        driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
	}

}
