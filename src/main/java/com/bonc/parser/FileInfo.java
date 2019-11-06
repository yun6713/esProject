package com.bonc.parser;

import java.io.File;
import java.util.Objects;

import com.bonc.utils.CommonUtil;

public class FileInfo {
	private File doc;
	private DocType type;
//	传入path构建实例，获取file，文档类型
	public FileInfo(String path) {
		Objects.requireNonNull(path);
		doc = new File(path);
//		设置文档类型
		this.type=CommonUtil.getDocType(doc);		
	}
	public FileInfo(File file) {
		Objects.requireNonNull(file);
		this.doc=file;
		this.type=CommonUtil.getDocType(doc);
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
