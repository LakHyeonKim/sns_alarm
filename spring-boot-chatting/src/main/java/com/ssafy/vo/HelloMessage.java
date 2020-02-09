package com.ssafy.vo;

public class HelloMessage {
	private String memberId;
	
	public HelloMessage() {
		super();
	}

	public HelloMessage(String memberId) {
		super();
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "HelloMessage [memberId=" + memberId + "]";
	}
}
