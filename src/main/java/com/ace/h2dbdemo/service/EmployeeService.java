package com.ace.h2dbdemo.service;
import com.ace.h2dbdemo.vo.CreateEmployeeRequestBody;
import com.ace.h2dbdemo.vo.GetEmployeeRequest;
import com.ace.h2dbdemo.vo.GetEmployeeResponse;

public interface EmployeeService {
	
	public boolean CreateEmployeeDetails(CreateEmployeeRequestBody createEmployeeRequestBody); 
	
	public GetEmployeeResponse getEmployeeDetails(GetEmployeeRequest getEmployeeRequest);
}
