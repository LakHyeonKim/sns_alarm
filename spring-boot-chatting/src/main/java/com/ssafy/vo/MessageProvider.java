package com.ssafy.vo;

 
public class MessageProvider { 
	private String message; // getter and setter ... 
	
	@Override
	public String toString() {
		return "MessageProvider [message=" + message + "]";
	}

	public MessageProvider() {
		super();
	}

	public MessageProvider(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
