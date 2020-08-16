package com.ace.h2dbdemo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ace.h2dbdemo.entity.EmployeeEn;
import com.ace.h2dbdemo.entity.EmployeeEmailEn;
import com.ace.h2dbdemo.entity.EmployeePhoneEn;
import com.ace.h2dbdemo.exception.RecordNotFoundException;
import com.ace.h2dbdemo.exception.RecordPresentException;
import com.ace.h2dbdemo.repository.EmployeeEmailRepo;
import com.ace.h2dbdemo.repository.EmployeePhoneRepo;
import com.ace.h2dbdemo.repository.EmployeeRepository;
import com.ace.h2dbdemo.util.CommonMapper;
import com.ace.h2dbdemo.util.CommonValidation;
import com.ace.h2dbdemo.vo.CreateEmployeeRequestBody;
import com.ace.h2dbdemo.vo.EmployeeEmailAddress;
import com.ace.h2dbdemo.vo.EmployeePhoneNumber;
import com.ace.h2dbdemo.vo.GetEmployeeRequest;
import com.ace.h2dbdemo.vo.GetEmployeeResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeEmailRepo employeeEmailRepo;

	@Autowired
	EmployeePhoneRepo employeePhoneRepo;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	CommonValidation commonValidation;

	@Override
	public boolean CreateEmployeeDetails(CreateEmployeeRequestBody createEmployeeRequestBody) {
		// before creating a new record check weather it is avalible in the table or not
		// check weather employee first name,last name,phone number,email alrady present
		// in DB or Not
		if (commonValidation.isEmployeeNamePresent(createEmployeeRequestBody)) {
			throw new RecordPresentException("employee name alrady present");
		}
		if (commonValidation.isEmailPresent(createEmployeeRequestBody)) {
			log.info("employee email alrady present");
			throw new RecordPresentException("employee email alrady present");
		}
		if (commonValidation.isPhonePresent(createEmployeeRequestBody)) {
			log.info("employee phone alrady present");
			throw new RecordPresentException("employee phone alrady present");
		}
		// record not found then create new record
		log.info("employee is not present and  creating new record for employee");
		EmployeeEn employeeEn = employeeRepository.save(prepareEmployeeDetails(createEmployeeRequestBody));
		if (employeeEn != null) {
			log.info("employee record is created");
			employeeEn.setEmployeePhoneList(preparePhoneDetails(createEmployeeRequestBody, employeeEn));
			employeeEn.setEmployeeEmailList(prepareEmailDetails(createEmployeeRequestBody, employeeEn));
			employeeEn = employeeRepository.save(employeeEn);
			if (employeeEn != null && !employeeEn.getEmployeeEmailList().isEmpty()
					&& !employeeEn.getEmployeePhoneList().isEmpty()) {
				log.info("employee phone and email record is created");
				return true;
			}
		}
		return false;
	}

	@Override
	public GetEmployeeResponse getEmployeeDetails(GetEmployeeRequest getEmployeeRequest) {
		List<EmployeeEn> employeeEnList = employeeRepository
				.findByFirstNameAndLastName(getEmployeeRequest.getFirstName(), getEmployeeRequest.getLastName());
		if (employeeEnList.isEmpty()) {
			log.info("employee record not found with firstname : {} and lastName : {}",
					getEmployeeRequest.getFirstName(), getEmployeeRequest.getLastName());
			throw new RecordNotFoundException("employee record not found");
		}
		if (employeeEnList.isEmpty() && employeeEnList.size() != 1) {
			log.info("multiple employee record found for given first name and last name");
			throw new RecordPresentException("multiple employee record found for given first name and last name");
		}
		log.info("employee record found with firstname : {} and lastName : {}", getEmployeeRequest.getFirstName(),
				getEmployeeRequest.getLastName());
		
		return createResponseForGetEmployeeResponse(employeeEnList.get(0));
	}

	private GetEmployeeResponse createResponseForGetEmployeeResponse(EmployeeEn employeeEn) {
		GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
		List<EmployeePhoneNumber> employeePhoneNumberList = new ArrayList<>();
		List<EmployeeEmailAddress> employeeEmailAddressList = new ArrayList<>();
		
		employeeEn.getEmployeeEmailList().stream().forEach(employeeEmailEn -> {
			employeeEmailAddressList.add(commonMapper.fromEmployeeEmailAddress(employeeEmailEn));
		});
		
		employeeEn.getEmployeePhoneList().stream().forEach(employeePhoneEn -> {
			employeePhoneNumberList.add(commonMapper.fromEmployeePhoneEn(employeePhoneEn));
		});
		getEmployeeResponse.setFirstName(employeeEn.getFirstName());
		getEmployeeResponse.setLastName(employeeEn.getLastName());
		getEmployeeResponse.setEmployeeEmailAddress(employeeEmailAddressList);
		getEmployeeResponse.setEmployeePhone(employeePhoneNumberList);
		return getEmployeeResponse;
	}

	private EmployeeEn prepareEmployeeDetails(CreateEmployeeRequestBody createEmployeeRequestBody) {
		// first save the parent table entity and create record in parent table
		// once we create record in parent table
		// we are suppose to get the forigen key vlaue required for crating record in
		// child table
		EmployeeEn employeeEn = new EmployeeEn();
		employeeEn.setFirstName(createEmployeeRequestBody.getFirstName());
		employeeEn.setLastName(createEmployeeRequestBody.getLastName());
		employeeEn.setCreateTms(new Timestamp(new Date().getTime()));
		// when you will save the entity it will return the saved entity from that
		// we can get the newly generated id
		// this id is required while setting the forien key column of its child entity
		return employeeEn;
	}

	private List<EmployeePhoneEn> preparePhoneDetails(CreateEmployeeRequestBody createEmployeeRequestBody,
			EmployeeEn employeeEn) {
		List<EmployeePhoneEn> employeePhones = new ArrayList<>();

		final EmployeeEn employee1 = employeeEn;
		createEmployeeRequestBody.getEmployeePhone().stream().forEach(employeePhoneNumber -> {
			EmployeePhoneEn employeePhone = commonMapper.fromEmployeePhoneNumber(employeePhoneNumber);
			// here get forign key value from employee and set forigen key value in employee
			// phone
			employeePhone.setEmpId(employee1.getEmployeeId());
			employeePhones.add(employeePhone);
		});
		return employeePhones;
	}

	private List<EmployeeEmailEn> prepareEmailDetails(CreateEmployeeRequestBody createEmployeeRequestBody,
			EmployeeEn employeeEn) {
		List<EmployeeEmailEn> employeeEmails = new ArrayList<>();
		final EmployeeEn employee1 = employeeEn;
		createEmployeeRequestBody.getEmployeeEmailAddress().stream().forEach(email -> {
			EmployeeEmailEn employeeEmail = commonMapper.fromEmployeeEmail(email);
			// here get forign key value from employee and set forigen key value in employee
			// email
			employeeEmail.setEmpId(employee1.getEmployeeId());
			employeeEmails.add(employeeEmail);
		});
		return employeeEmails;
	}

}
