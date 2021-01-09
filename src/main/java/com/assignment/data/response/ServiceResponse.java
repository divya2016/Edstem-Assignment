package com.assignment.data.response;

public class ServiceResponse<T> {
	private T data;
	private String status;
	private String message;

	public ServiceResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServiceResponse(T data, String status, String message) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
	}

	public ServiceResponse() {
	}

}
