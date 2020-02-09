package com.ssafy.vo;

public class Greeting {
	private String content;
	
	public Greeting() {
		super();
	}

	public Greeting(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Greeting [content=" + content + "]";
	}
	
}
