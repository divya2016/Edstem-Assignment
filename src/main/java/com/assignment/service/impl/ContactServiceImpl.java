package com.assignment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.constants.Constants;
import com.assignment.data.model.Contacts;
import com.assignment.data.request.ContactRequest;
import com.assignment.data.request.ServiceRequest;
import com.assignment.data.response.ContactAllResponse;
import com.assignment.data.response.ContactResponse;
import com.assignment.data.response.GenricResponse;
import com.assignment.data.response.ServiceResponse;
import com.assignment.service.ContactService;
import com.assignment.service.helper.ContactServiceHelper;
import com.assignment.utils.CommonUtils;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactServiceHelper contactServiceHelper;

	@Autowired
	private CommonUtils commonUtils;

	@Override
	public ServiceResponse<GenricResponse> createContact(ServiceRequest<ContactRequest> serviceRequest) {
		ServiceResponse<GenricResponse> serviceResponse = contactServiceHelper
				.validateCreateContactRequest(serviceRequest);
		try {
			if (serviceResponse.getStatus().equals(Constants.SUCCESS)) {
				Contacts contactDetails = contactServiceHelper.prepareContactDetails(serviceRequest.getData());
				commonUtils.save(contactDetails);
				serviceResponse = new ServiceResponse<GenricResponse>(Constants.SUCCESS, "Data Saved Successfully.");
			}
		} catch (Exception e) {
			System.out.printf("An error occurred :: ", e);
			serviceResponse = new ServiceResponse<GenricResponse>(Constants.ERROR,
					"Something Went Wrong. Please try again later.");
		}
		return serviceResponse;
	}

	@Override
	public ServiceResponse<ContactAllResponse> getAll() {
		try {
			List<Contacts> contactDetails = commonUtils.findAll(Contacts.class);
			ContactAllResponse contactResponse = contactServiceHelper.prepareContactResponse(contactDetails);
			return new ServiceResponse<ContactAllResponse>(contactResponse, Constants.SUCCESS,
					"Detail Found Successfully.");

		} catch (Exception e) {
			System.out.printf("An error occurred :: ", e);
			return new ServiceResponse<ContactAllResponse>(Constants.ERROR,
					"Something Went Wrong. Please try again later.");
		}
	}

	@Override
	public ServiceResponse<ContactResponse> getContactByPhoneNumber(String phoneNumber) {
		ServiceResponse<GenricResponse> response = contactServiceHelper.validatePhoneNumber(phoneNumber);
		try {
			if (response.getStatus().equals(Constants.SUCCESS)) {
				Map<String, Object> params = new HashMap<>();
				params.put("phoneNumber", phoneNumber);
				Contacts contactDetails = commonUtils.findOne(params, Contacts.class);
				ContactResponse res = contactServiceHelper.prepareContactDetailsResponse(contactDetails);
				return new ServiceResponse<>(res, Constants.SUCCESS, "Detail Found Successfully.");
			} else {
				return new ServiceResponse<>(response.getStatus(), response.getMessage());

			}
		} catch (Exception e) {
			System.out.printf("An error occurred :: ", e);
			return new ServiceResponse<ContactResponse>(Constants.ERROR,
					"Something Went Wrong. Please try again later.");
		}

	}

	@Override
	public ServiceResponse<GenricResponse> deleteContactByPhoneNumber(String phoneNumber) {
		ServiceResponse<GenricResponse> serviceResponse = contactServiceHelper.validatePhoneNumber(phoneNumber);
		try {
			if (serviceResponse.getStatus().equals(Constants.SUCCESS)) {
				Map<String, Object> params = new HashMap<>();
				params.put("phoneNumber", phoneNumber);
				Contacts contactDetails = commonUtils.findOne(params, Contacts.class);
				commonUtils.remove(contactDetails);
				serviceResponse = new ServiceResponse<>(Constants.SUCCESS, "Data removed Successfully.");
			}
		} catch (Exception e) {
			System.out.printf("An error occurred :: ", e);
			serviceResponse = new ServiceResponse<>(Constants.ERROR, "Something Went Wrong. Please try again later.");
		}
		return serviceResponse;
	}
}
