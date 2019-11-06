package com.bonc.parser;

import java.io.File;

public class FileInfo {
	private File doc;
	private DocType type;
//	传入path构建实例，获取file，文档类型
	public FileInfo(String path) {
		doc = new File(path);
//		文档不存在时抛异常
		if(!doc.exists())
			throw new RuntimeException("文件不存在："+path);
//		设置文档类型
		int loc;
		type=doc.isDirectory()?DocType.DIR
				:(loc=path.lastIndexOf("."))==-1?DocType.OTHERS
				:DocType.of(path.substring(loc).toLowerCase());
		
	}
	public File getDoc() {
		return doc;
	}
	public void setDoc(File doc) {
		this.doc = doc;
	}
	public DocType getType() {
		return type;
	}
	public void setType(DocType type) {
		this.type = type;
	}
	
}
