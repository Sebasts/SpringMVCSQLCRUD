package io.hellsing.data;

import org.springframework.stereotype.Component;

@Component
public class Memo {
	private String name;
	private String content;
	
	
	public Memo(String name, String content) {
		this.name = name;
		this.content = content;
	}
	
	
	public Memo() {

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
	
	
	
	
}
