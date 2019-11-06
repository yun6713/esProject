package com.bonc.entity;

import java.util.Map;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import com.bonc.parser.DocType;
//ES文档描述
@Document(indexName="doc",type="_doc")
public class DocEntity {
	private String title;
	@Field(store=true)
	private String content;
	private DocType type;
	private Map<String,Object> metadata;
	
	public DocEntity() {
		super();
	}
	public DocEntity(String title, String content, DocType type, Map<String, Object> metadata) {
		super();
		this.title = title;
		this.content = content;
		this.type = type;
		this.metadata = metadata;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type==null?"其他":type.getType();
	}
	public void setType(DocType type) {
		this.type = type;
	}
	public Object getMetadata() {
		return metadata;
	}
	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}
	
}
