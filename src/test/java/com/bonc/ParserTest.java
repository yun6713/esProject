package com.bonc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class ParserTest {
	@Test
	public void parseExample() throws Exception {
	    AutoDetectParser parser = new AutoDetectParser();
	    BodyContentHandler handler = new BodyContentHandler();
	    Metadata metadata = new Metadata();//esInfo  B.doc
	    try (InputStream stream = new ClassPathResource("esInfo").getInputStream()) {
	    	parser.parse(stream, handler, metadata);
	        System.out.println(handler.toString());
	        for(String name:metadata.names()) {
	        	System.out.println(name+":"+metadata.get(name));	
	        }
	    }
	    System.out.println("end");
	}
	public void parseExample(File file) throws Exception {
		if(file.isDirectory()) {
			dir(file.toPath());
		}
	    AutoDetectParser parser = new AutoDetectParser();
	    BodyContentHandler handler = new BodyContentHandler();
	    Metadata metadata = new Metadata();
	    try (InputStream stream = new FileInputStream(file)) {
	    	parser.parse(stream, handler, metadata);
	        System.out.println(handler.toString());
	        for(String name:metadata.names()) {
	        	System.out.println(name+":"+metadata.get(name));	
	        }
	    }
	    System.out.println(file.getName());
	    System.out.println("end");
	}
//	文件夹遍历测试
	@Test
	public void dirTest() throws Exception {
		dir(Paths.get("C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)"));
	}
//	单独抽出，便于递归
	public void dir(Path p) throws Exception {
		Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				try {
					parseExample(path.toFile());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
