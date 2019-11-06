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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import com.bonc.entity.DocEntity;
import com.bonc.parser.DocType;
import com.bonc.parser.FileInfo;
import com.bonc.service.DocService;
import com.bonc.utils.CommonUtil;
//@Service
public class DocServiceImpl implements DocService {
	@Autowired
	AutoDetectParser parser;
//	无法自动解析txt内容
	@Autowired
	TXTParser txtParser;
	@Autowired
	ElasticsearchOperations eo;
	ParseContext pc=new ParseContext();
	public static final int size=50;
	public static final ExecutorService ES=Executors.newFixedThreadPool(size);
	public static Semaphore sph=new Semaphore(size);
	@Override
	public String add(String path) {
		try {
			File root=new File(path);
			if(!root.exists()){
				throw new RuntimeException("文件不存在："+path);
			}else if(root.isDirectory()){
				travDir(root);
			}else{
				indexFile(root);
			}
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	public void travDir(File dir) throws Exception {
		if(!dir.isDirectory()) {
			throw new RuntimeException("必须为文件夹："+dir.getName());
		}
		Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				try {
					File file=path.toFile();
					if(file.isDirectory()){
						travDir(file);
					}else{
						sph.acquire();
						ES.execute(()->{
							try {
								indexFile(file);
							} catch (Exception e) {
								e.printStackTrace();
							}finally{
								sph.release();
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}
	//	通过tika读取后，发到es
	private void indexFile(File file) throws Exception{
		String fName=file.getName();
		if(file.isDirectory()) {
			throw new RuntimeException("不可为文件夹："+fName);
		}
	    BodyContentHandler handler = new BodyContentHandler();
	    Metadata metadata = new Metadata();
	    //拼接DocEntity对象
	    try (InputStream stream = new FileInputStream(file)) {
	    	DocType dt=CommonUtil.getDocType(file);	    	
	    	getParser(dt).parse(stream, handler, metadata,pc);
	    	Map<String,Object> map=new HashMap<>();
	    	for(String name:metadata.names()) {
	    		map.put(name,metadata.get(name));	
	        }
	    	DocEntity de=new DocEntity(fName,handler.toString(),dt,map);
	    	eo.index(new IndexQueryBuilder().withObject(de).build());	    	
	    }
	}
	//	根据文档类型获取parser；
	private AbstractParser getParser(DocType type){
		switch(type){
		case TXT:
			return txtParser;
		default:
			return parser;
		}
	}
}
