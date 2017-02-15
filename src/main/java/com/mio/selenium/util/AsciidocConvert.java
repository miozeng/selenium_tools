package com.mio.selenium.util;

import static org.asciidoctor.OptionsBuilder.options;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.asciidoctor.Asciidoctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsciidocConvert {
	
	private static final Logger logger = LoggerFactory.getLogger(AsciidocConvert.class);

	public static boolean createAsciidoc(StringBuffer sb, String filePath) {
		logger.info("Start to create *.adoc");
		try {
			File file = new File(filePath);
			if(!file.exists()){
				file.delete();
			}
			file.createNewFile();
			OutputStreamWriter  writer = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(sb.toString());
			bw.close();
			writer.close();
			logger.info("End to create *.adoc");
		} catch (Exception e) {
			logger.error(" create *.adoc failure:",e);
			return false;
		}

		return true;
	}

	public static boolean converToHtml(String inputPath, String outputPath, boolean isModul){
		logger.info("Start to  conver *.adoc to *.html");
		try {
			File inputFile = new File(inputPath);
			File outputFile = new File(outputPath);
			logger.info("  *.adoc path " + inputPath);
			logger.info("  *.html path " + outputPath);
			if (outputFile.exists()) {
				logger.info("  file is exists delete it");
				outputFile.delete();
			}

			Asciidoctor asciidoctor = Asciidoctor.Factory.create();
			FileReader reader = new FileReader(inputFile);
			StringWriter writer = new StringWriter();
			asciidoctor.convert(reader, writer, options().asMap());
			boolean result = changeToHtml(writer, isModul, outputFile);
			return result;
		} catch (Exception e) {
			logger.error("  conver *.adoc to *.html failure", e);
			return false;
		}

		
	}


	public static boolean changeToHtml(StringWriter writer, boolean isModul, File outputFile) {
		try {
			logger.info("  using vm template to  create html");
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			ve.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
			ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
			ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
			ve.init();
			String path ="projecthtml.vm";
			if(isModul){
				 path ="modulhtml.vm";
			}
			logger.info(" vm path" + path);
			Template t = ve.getTemplate(path);
			VelocityContext ctx = new VelocityContext();
			StringBuffer htmlBuffer = writer.getBuffer();
			ctx.put("assmsg", htmlBuffer.toString());
			StringWriter sw = new StringWriter();
			t.merge(ctx, sw);

			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8");
			BufferedWriter bwriter = new BufferedWriter(write);
			bwriter.write(sw.getBuffer().toString());
			bwriter.flush();
			bwriter.close();

		} catch (Exception e) {
			logger.error(" create html failure", e);
			return false;
		}

		return true;
	}

	
	public static void main(String[] args) throws IOException {
		 AsciidocConvert.converToHtml("D://test.adoc", "d://aa.html", false);
//		StringBuffer sb = new StringBuffer();
//		sb.append("aaa" + System.getProperty("line.separator"));
//		sb.append("aaa" + System.getProperty("line.separator"));
//		AsciidocConvert.createAsciidoc(sb, "D://deu.adoc");

	}

}
