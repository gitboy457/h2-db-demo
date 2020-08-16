package com.ace.h2dbdemo.vo;

import java.util.List;

import lombok.Data;

@Data
public class CreateEmployeeRequestBody {
	
	private String firstName;
	
	private String lastName;
	
	private List<EmployeePhoneNumber> employeePhone;
	
	private List<EmployeeEmailAddress> employeeEmailAddress;
	
	
	
	

}
