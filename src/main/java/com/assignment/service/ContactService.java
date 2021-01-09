package com.assignment.service;

import com.assignment.data.request.ContactRequest;
import com.assignment.data.request.ServiceRequest;
import com.assignment.data.response.ContactAllResponse;
import com.assignment.data.response.ContactResponse;
import com.assignment.data.response.GenricResponse;
import com.assignment.data.response.ServiceResponse;

public interface ContactService {

	/**
	 * This method will validate the contact details and save that in database
	 * 
	 * @param serviceRequest
	 * @return serviceResponse
	 */

	ServiceResponse<GenricResponse> createContact(ServiceRequest<ContactRequest> serviceRequest);

	/**
	 * @return list of all the available contacts
	 */
	ServiceResponse<ContactAllResponse> getAll();

	/**
	 * this method will return the contact details for a specific id
	 * 
	 * @param phoneNumber
	 * @return contact Details
	 */
	ServiceResponse<ContactResponse> getContactByPhoneNumber(String phoneNumber);

	/**
	 * this method will delete the data from database
	 * 
	 * @param phoneNumber
	 * @return
	 */
	ServiceResponse<GenricResponse> deleteContactByPhoneNumber(String phoneNumber);

}
