package com.ace.h2dbdemo.util;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ace.h2dbdemo.entity.EmployeeEn;
import com.ace.h2dbdemo.entity.EmployeeEmailEn;
import com.ace.h2dbdemo.entity.EmployeePhoneEn;
import com.ace.h2dbdemo.repository.EmployeeEmailRepo;
import com.ace.h2dbdemo.repository.EmployeePhoneRepo;
import com.ace.h2dbdemo.repository.EmployeeRepository;
import com.ace.h2dbdemo.vo.CreateEmployeeRequestBody;
import com.ace.h2dbdemo.vo.EmployeeEmailAddress;
import com.ace.h2dbdemo.vo.EmployeePhoneNumber;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonValidation {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeEmailRepo employeeEmailRepo;

	@Autowired
	EmployeePhoneRepo employeePhoneRepo;

	public boolean isEmployeeNamePresent(CreateEmployeeRequestBody createEmployeeRequestBody) {
		log.info("validate employee  first name and last name from DB");
		List<EmployeeEn> employeeEn = employeeRepository.findByFirstNameAndLastName(
				createEmployeeRequestBody.getFirstName(), createEmployeeRequestBody.getLastName());
		if (!employeeEn.isEmpty()) {
			log.info("employee name  present in DB");
			return true;
		}
		log.info("employee name not present in DB");
		return false;
	}

	public boolean isEmailPresent(CreateEmployeeRequestBody createEmployeeRequestBody) {
		log.info("validate employee email from DB");
		for (EmployeeEmailAddress EmployeeEmailAddress : createEmployeeRequestBody.getEmployeeEmailAddress()) {
			List<EmployeeEmailEn> EmployeeEmailEn = employeeEmailRepo
					.findByEmployeeEmail(EmployeeEmailAddress.getEmail());
			if (!EmployeeEmailEn.isEmpty()) {
				log.info("employee email alrady present in DB");
				return true;
			}
		}
		log.info("employee email not present in DB");
		return false;
	}

	public boolean isPhonePresent(CreateEmployeeRequestBody createEmployeeRequestBody) {
		log.info("validate employee phone alrady from DB");
		for (EmployeePhoneNumber employeePhoneNumber : createEmployeeRequestBody.getEmployeePhone()) {
			List<EmployeePhoneEn> employeePhoneEn = employeePhoneRepo
					.findByPhoneNumber(employeePhoneNumber.getPhoneNumber(), employeePhoneNumber.getPhoneCountryCode());
			if (!employeePhoneEn.isEmpty()) {
				log.info("employee phone alrady present in DB");
				return true;
			}
		}
		log.info("employee phone not present in DB");
		return false;
	}

}
