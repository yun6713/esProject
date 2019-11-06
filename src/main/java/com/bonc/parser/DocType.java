package com.bonc.parser;
/**
 * 文档类型枚举类
 * @author litianlin
 * @date   2019年10月25日上午9:10:09
 * @Description TODO
 */
public enum DocType {
	DOC("doc"),TXT("txt"),PDF("pdf"),XLS("xls"),PPT("ppt"),
	UNKOWN(""),DIR("dir");
	private String type;
	public String getType() {
		return type;
	}
	private DocType(String type) {
		this.type=type;
	}
	public static void main(String[] args) {
		System.out.println(DocType.DOC.getType());
		System.out.println(DocType.DOC);
	}
	//获取文档类型枚举类
	public static DocType of(String type) {
		type=type.trim();
		type=type.startsWith(".")?type.substring(1):type;
		switch (type) {
		case "doc":
		case "docx":
			return DocType.DOC;
		case "txt":
			return DocType.TXT;
		case "pdf":
			return DocType.PDF;
		case "xls":
		case "xlsx":
			return DocType.XLS;
		case "ppt":
		case "pptx":
			return DocType.PPT;
		case "dir":
			return DocType.DIR;
		default:
			return DocType.UNKOWN;
		}
	}
}
