package com.bonc.service.impl;

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
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.bonc.entity.DocEntity;
import com.bonc.parser.DocType;
import com.bonc.service.DocService;

public class DocServiceImpl implements DocService {
	@Autowired
	AutoDetectParser parser;
	
	@Override
	public String add(String path) {
		try {
			dir(Paths.get(path));
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
//	单独抽出，便于递归
	public void dir(Path p) throws Exception {
		Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				try {
					parseExample(path.toFile());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}
//	通过tika读取文档内容
	public void parseExample(File file) throws Exception {
		if(file.isDirectory()) {
			dir(file.toPath());
		}
	    BodyContentHandler handler = new BodyContentHandler();
	    Metadata metadata = new Metadata();
	    //拼接DocEntity对象
	    try (InputStream stream = new FileInputStream(file)) {
	    	parser.parse(stream, handler, metadata);
	    	String fName=file.getName();
	    	int loc=fName.lastIndexOf(".");
	    	DocType dt=loc==-1?DocType.UNKOWN
					:DocType.of(fName.substring(loc).toLowerCase());
	    	Map<String,Object> map=new HashMap<>();
	    	for(String name:metadata.names()) {
	    		map.put(name,metadata.get(name));	
	        }
	    	DocEntity de=new DocEntity(fName,handler.toString(),dt,map);
//	        System.out.println(handler.toString());
//	        for(String name:metadata.names()) {
//	        	System.out.println(name+":"+metadata.get(name));	
//	        }
	    }
//	    System.out.println(file.getName());
//	    System.out.println("end");
	}

}
