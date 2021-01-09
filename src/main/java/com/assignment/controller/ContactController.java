package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.data.request.ContactRequest;
import com.assignment.data.request.ServiceRequest;
import com.assignment.data.response.ContactAllResponse;
import com.assignment.data.response.ContactResponse;
import com.assignment.data.response.GenricResponse;
import com.assignment.data.response.ServiceResponse;
import com.assignment.service.ContactService;

/**
 * @author divyarani
 *
 */
@RestController
@RequestMapping("v1/contact/")
public class ContactController {

	@Autowired
	private ContactService contactService;

	/**
	 * @param serviceRequest
	 * @return
	 */
	@PostMapping("create")
	public ServiceResponse<GenricResponse> create(@RequestBody ServiceRequest<ContactRequest> serviceRequest) {
		return contactService.createContact(serviceRequest);
	}

	/**
	 * This api will fetch all the records from database
	 * @return
	 */
	@GetMapping("getAll")
	public ServiceResponse<ContactAllResponse> getAll() {
		return contactService.getAll();
	}

	/**
	 * This api will get the data based on the phone number
	 * @param phoneNumber
	 * @return
	 */
	@GetMapping("get/{phoneNumber}")
	public ServiceResponse<ContactResponse> getContactById(@PathVariable String phoneNumber) {
		return contactService.getContactByPhoneNumber(phoneNumber);

	}

	/**
	 * this api will delete  the record from database
	 * @param phoneNumber
	 * @return
	 */
	@GetMapping("delete/{phoneNumber}")
	public ServiceResponse<GenricResponse> deleteByPhoneNumber(@PathVariable String phoneNumber) {
		return contactService.deleteContactByPhoneNumber(phoneNumber);

	}

}
