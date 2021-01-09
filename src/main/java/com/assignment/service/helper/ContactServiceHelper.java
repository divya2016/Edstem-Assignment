package com.assignment.service.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.constants.Constants;
import com.assignment.data.model.Contacts;
import com.assignment.data.request.ContactRequest;
import com.assignment.data.request.ServiceRequest;
import com.assignment.data.response.ContactAllResponse;
import com.assignment.data.response.ContactResponse;
import com.assignment.data.response.GenricResponse;
import com.assignment.data.response.ServiceResponse;
import com.assignment.utils.CommonUtils;

@Component
public class ContactServiceHelper {

	@Autowired
	private CommonUtils commonUtils;

	/**
	 * This method is validating contact add request
	 * @param serviceRequest
	 * @return
	 */
	public ServiceResponse<GenricResponse> validateCreateContactRequest(ServiceRequest<ContactRequest> serviceRequest) {
		if (StringUtils.isEmpty(serviceRequest.getData().getPhoneNumber())) {
			return new ServiceResponse<>(Constants.FAILURE, "phone number can not be empty.");
		}
		Map<String, Object> params = new HashMap<>();
		params.put("phoneNumber", serviceRequest.getData().getPhoneNumber());
		Contacts contactDetails = commonUtils.findOne(params, Contacts.class);
		if (null != contactDetails) {
			return new ServiceResponse<>(Constants.FAILURE, "duplicate phone number.");
		}
		if (!validateMobile(serviceRequest.getData().getPhoneNumber())) {
			return new ServiceResponse<>(Constants.FAILURE, "phone number not valid.");
		}
		if (StringUtils.isEmpty(serviceRequest.getData().getName())) {
			return new ServiceResponse<>(Constants.FAILURE, "name can not be empty.");
		}
		if (!validateName(serviceRequest.getData().getName())) {
			return new ServiceResponse<>(Constants.FAILURE, "name not valid.");
		}
		if (StringUtils.isNotEmpty(serviceRequest.getData().getEmail())
				&& !validateEmail(serviceRequest.getData().getEmail())) {
			return new ServiceResponse<>(Constants.FAILURE, "email not valid.");
		}

		return new ServiceResponse<>(Constants.SUCCESS, "request validated sucessfully.");
	}

	/**
	 * This method is preparing contact response
	 * @param data
	 * @return
	 */
	public Contacts prepareContactDetails(ContactRequest data) {
		return new Contacts(data.getName(), data.getPhoneNumber(),
				StringUtils.isNotEmpty(data.getEmail()) ? data.getEmail() : null,
				StringUtils.isNotEmpty(data.getAddress()) ? data.getAddress() : null);
	}

	/**
	 * This method is validating phone number
	 * @param contactDetails
	 * @return
	 */
	public ContactAllResponse prepareContactResponse(List<Contacts> contactDetails) {
		List<ContactResponse> responseList = new ArrayList<>();
		for (Contacts data : contactDetails) {
			responseList.add(new ContactResponse(data.getName(), data.getPhoneNumber(),
					StringUtils.isNotEmpty(data.getEmail()) ? data.getEmail() : null,
					StringUtils.isNotEmpty(data.getAddress()) ? data.getAddress() : null));
		}
		return new ContactAllResponse(responseList);
	}

	/**
	 * This method is preparing the response list
	 * @param phoneNumber
	 * @return
	 */
	public ServiceResponse<GenricResponse> validatePhoneNumber(String phoneNumber) {
		if (StringUtils.isEmpty(phoneNumber)) {
			return new ServiceResponse<>(Constants.FAILURE, "phone number can not be empty.");
		}
		Map<String, Object> params = new HashMap<>();
		params.put("phoneNumber", phoneNumber);
		Contacts contactDetails = commonUtils.findOne(params, Contacts.class);
		if (null == contactDetails) {
			return new ServiceResponse<>(Constants.FAILURE, "phone number not valid.");
		}
		return new ServiceResponse<>(Constants.SUCCESS, "request validated sucessfully.");
	}

	/**
	 * This method is preparing the response
	 * @param data
	 * @return
	 */
	public ContactResponse prepareContactDetailsResponse(Contacts data) {
		return new ContactResponse(data.getName(), data.getPhoneNumber(),
				StringUtils.isNotEmpty(data.getEmail()) ? data.getEmail() : null,
				StringUtils.isNotEmpty(data.getAddress()) ? data.getAddress() : null);
	}

	/**
	 * This method will validate email
	 * @param email
	 * @return
	 */
	public static Boolean validateEmail(String email) {
		return email.matches(Constants.EMAIL_REGEX);
	}

	/**
	 * this method will validate mobile
	 * @param mobile
	 * @return
	 */
	public static Boolean validateMobile(String mobile) {
		return mobile.matches(Constants.MOBILE_REGEX);
	}

	/**
	 * This method will validate mobile
	 * @param name
	 * @return
	 */
	public static Boolean validateName(String name) {
		return name.matches(Constants.NAME_REGEX);
	}
}
