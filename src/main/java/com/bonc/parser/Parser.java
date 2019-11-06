package com.bonc.parser;

import java.util.Map;

/**
 * 文档解析父类，
 */
public interface Parser {
//	按后缀判定类型，解析文档属性
	public abstract Map<String,Object> parse(FileInfo fi);
//	是否支持当前文档类型
	public abstract boolean support(FileInfo fi);
}
