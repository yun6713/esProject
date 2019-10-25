package com.bonc.parser;

import java.util.HashMap;
import java.util.Map;

public class DocParser implements Parser {
	@Override
	public Map<String, Object> parse(FileInfo fi) {
		if(!support(fi)) {
			throw new RuntimeException("不支持类型："+fi.getType().getType());
		}
		Map<String, Object> result = new HashMap<String, Object>();
//		解析逻辑
		return result;
	}

	@Override
	public boolean support(FileInfo fi) {
		return fi!=null&&DocType.DOC.equals(fi.getType());
	}

}
