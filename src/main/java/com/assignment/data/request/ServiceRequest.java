package com.assignment.data.request;

public class ServiceRequest<T> {
	private T data;

	public ServiceRequest(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	public ServiceRequest() {
		
	}

}
