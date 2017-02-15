package com.mio.selenium.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.mio.selenium.domain.SeDoType;
import com.mio.selenium.domain.SeGetType;
import com.mio.selenium.domain.SeTestCase;
import com.mio.selenium.domain.SeTestStep;

public class CvsUtil {

	public static SeTestCase cvsToCase(File file) throws Exception {
		SeTestCase tcase = new SeTestCase();
		String fileName = file.getName();
		String[] names = fileName.split("_");
		if(names.length >=2){
			
			String name = names[1].substring(0,names[1].lastIndexOf("."));
			tcase.setCaseName(name);
			tcase.setCaseNo(names[0]);
		}
		Reader in =  new FileReader(file);
		CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader());
		List<SeTestStep> steps = new ArrayList<SeTestStep>();
		for (CSVRecord record : parser) {
			String name = record.get("name").trim();
			String stepNo = record.get("stepNo").trim();
			String sequence = record.get("sequence").trim();
			String desc = record.get("desc").trim();
			String url = record.get("url").trim();
			String takePhoto = record.get("takePhoto").trim();
			String doTypes = record.get("doType").trim();
			SeDoType dotype = (SeDoType) Enum.valueOf(SeDoType.class, doTypes);
			String exceptvalue = record.get("exceptvalue").trim();
			SeTestStep step = new SeTestStep();
			step.setStepName(name);
			step.setStepNo(stepNo);
			step.setSequence(Integer.parseInt(sequence));
			step.setStepDesc(desc);
			step.setTakePhoto(Boolean.parseBoolean(takePhoto));
			step.setDoType(dotype);
			step.setExcptValue(exceptvalue);
			step.setUrl(url);
			String elementname = record.get("elementname").trim();
			if(elementname != null && !"".equals(elementname)){
				String elementvalue = record.get("elementvalue").trim();
				String elementgetType = record.get("elementgetType").trim();
				SeGetType type = null;
				if(elementgetType != null){
					type = (SeGetType) Enum.valueOf(SeGetType.class, elementgetType);
				}
				step.setElementName(elementname);
				step.setGetType(type);
				step.setElementValue(elementvalue);
			}
			

			steps.add(step);
		}
		tcase.setSteps(steps);
		return tcase;
	}

	public static File caseToCvs(SeTestCase tcase, String path) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			csvFile = new File(path +"\\"+ tcase.getCaseNo()+"_"+tcase.getCaseName() + ".csv");
			if(csvFile.exists()){
				csvFile.delete();
			}
			// csvFile.getParentFile().mkdir();
			File parent = csvFile.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			csvFile.createNewFile();

			// GB2312 using ","
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
			// head
			csvFileOutputStream.write("\"stepNo\",\"name\",\"sequence\",\"desc\",\"url\",");
			csvFileOutputStream.write("\"elementname\",\"elementvalue\",\"elementgetType\",");
			csvFileOutputStream.write("\"doType\",\"exceptvalue\",");
			csvFileOutputStream.write("\"takePhoto\"");
			csvFileOutputStream.newLine();
			//contant
			List<SeTestStep> steps = tcase.getSteps();
			Collections.sort(steps);
			for (int i = 0; i <steps.size(); i++) {
				SeTestStep step = steps.get(i);
				csvFileOutputStream.write("\""+changeNull(step.getStepNo())+"\",\""+changeNull(step.getStepName())+"\",\""+changeNull(step.getSequence())+"\",");
				csvFileOutputStream.write("\""+changeNull(step.getStepDesc())+"\",\""+changeNull(step.getUrl())+"\",");

				csvFileOutputStream.write("\""+changeNull(step.getElementName())+"\",\""+changeNull(step.getElementValue())+"\",\""+step.getGetType()+"\",");	
			
				csvFileOutputStream.write("\""+changeNull(step.getDoType())+"\",\""+changeNull(step.getExcptValue())+"\",");
				csvFileOutputStream.write("\""+step.getTakePhoto()+"\"");
				if (i<steps.size()-1) {
					csvFileOutputStream.newLine();
				}
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				
			}
		}
		return csvFile;
	}
	
	private static String changeNull(String str){
		if(str == null ){
			return "";
		}
		return str;
	}
	private static String changeNull(Integer str){
		if(str == null ){
			return "";
		}
		return str+"";
	}
	private static String changeNull(SeDoType str){
		if(str == null ){
			return "";
		}
		return str+"";
	}

}
