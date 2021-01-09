package com.assignment.data.response;

import java.util.List;

public class ContactAllResponse {
	private List<ContactResponse> contactAllResponse;

	public List<ContactResponse> getContactAllResponse() {
		return contactAllResponse;
	}

	public void setContactAllResponse(List<ContactResponse> contactAllResponse) {
		this.contactAllResponse = contactAllResponse;
	}

	public ContactAllResponse(List<ContactResponse> contactAllResponse) {
		super();
		this.contactAllResponse = contactAllResponse;
	}

	public ContactAllResponse() {
	}
}
