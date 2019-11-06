package com.bonc.utils;

import java.io.File;
import java.util.Objects;

import com.bonc.parser.DocType;

public class CommonUtil {
	public static DocType getDocType(File file){
		Objects.requireNonNull(file);
		String fName=file.getName();
//		文档不存在时抛异常
		if(!file.exists())
			throw new RuntimeException("文件不存在："+fName);
		if(file.isDirectory()){
			return DocType.DIR;
		}
    	int loc=fName.lastIndexOf(".");
		return loc==-1?DocType.UNKOWN
				:DocType.of(fName.substring(loc+1).toLowerCase());
	}
	
	
}
