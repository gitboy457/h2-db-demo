package com.ace.h2dbdemo.util;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ace.h2dbdemo.entity.EmployeeEmailEn;
import com.ace.h2dbdemo.entity.EmployeePhoneEn;
import com.ace.h2dbdemo.vo.EmployeeEmailAddress;
import com.ace.h2dbdemo.vo.EmployeePhoneNumber;

@Component
public class CommonMapper {
	
	public EmployeePhoneEn fromEmployeePhoneNumber(EmployeePhoneNumber employeePhoneNumber) {
		EmployeePhoneEn employeePhone = new EmployeePhoneEn();
		employeePhone.setPhoneNumber(employeePhoneNumber.getPhoneNumber());
		employeePhone.setPhoneCountryCode(employeePhoneNumber.getPhoneCountryCode());
		employeePhone.setCreateTms(new Timestamp(new Date().getTime()));
		return employeePhone;

	}

	public EmployeeEmailEn fromEmployeeEmail(EmployeeEmailAddress emailAddress) {
		EmployeeEmailEn employeeEmailEn = new EmployeeEmailEn();
		employeeEmailEn.setEmailAddress(emailAddress.getEmail());
		employeeEmailEn.setCreateTms(new Timestamp(new Date().getTime()));
		return employeeEmailEn;
	}
	
	public EmployeePhoneNumber fromEmployeePhoneEn(EmployeePhoneEn employeePhoneEn) {
		EmployeePhoneNumber employeePhoneNumber= new EmployeePhoneNumber();
		employeePhoneNumber.setPhoneNumber(employeePhoneEn.getPhoneNumber());
		employeePhoneNumber.setPhoneCountryCode(employeePhoneEn.getPhoneCountryCode());
		return employeePhoneNumber;
	}
	
	public EmployeeEmailAddress fromEmployeeEmailAddress(EmployeeEmailEn employeeEmailEn) {	
		EmployeeEmailAddress employeeEmailAddress= new EmployeeEmailAddress();
		employeeEmailAddress.setEmail(employeeEmailEn.getEmailAddress());
		return employeeEmailAddress;
	}

}
