package io.hellsing.data;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Memo {
	private int id;
	private String name;
	private String content;
	private LocalDateTime sendTime;
	private String dateTime;
	
	public Memo(String name, String content) {
		this.name = name;
		this.content = content;
	}
	
	
	public Memo() {
	}
	
	public Memo(int id, String name, String content, LocalDateTime sendTime) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.sendTime = sendTime;
	}


	public Memo(int int1, String string, String string2, String string3) {
		this.id=int1;
		this.name = string;
		this.content=string2;
		this.dateTime=string3;
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


	public LocalDateTime getSendTime() {
		return sendTime;
	}


	public void setSendTime(LocalDateTime sendTime) {
		this.sendTime = sendTime;
	}
	
	
	
	
	
}
