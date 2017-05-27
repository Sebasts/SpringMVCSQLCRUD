package io.hellsing.data;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Memo {
	private int id;
	private String name;
	private String content;
	private Date sendTime;
	
	
	public Memo(String name, String content) {
		this.name = name;
		this.content = content;
	}
	
	
	public Memo() {
	}
	
	public Memo(int id, String name, String content, Date sendTime) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.sendTime = sendTime;
	}


	@Override
	public String toString() {
		return "Memo [name=" + name + ", content=" + content + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getSendTime() {
		return sendTime;
	}


	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
	
	
	
}
