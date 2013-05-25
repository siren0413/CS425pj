package com.proj425.domain;

public class PageStatus {

	private String status;
	private String object;
	private String forwardURL;

	public PageStatus(String object, String status ,String forwardURL) {
		super();
		this.status = status;
		this.object = object;
		this.forwardURL = forwardURL;
	}
	public PageStatus() {
		super();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getForwardURL() {
		return forwardURL;
	}
	public void setForwardURL(String forwardURL) {
		this.forwardURL = forwardURL;
	}

	
	
}
