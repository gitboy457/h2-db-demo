package com.ace.h2dbdemo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ace.h2dbdemo.repository.EmployeeEmailRepo;
import com.ace.h2dbdemo.repository.EmployeePhoneRepo;
import com.ace.h2dbdemo.repository.EmployeeRepository;
import com.ace.h2dbdemo.service.EmployeeService;
import com.ace.h2dbdemo.vo.CreateEmployeeRequestBody;
import com.ace.h2dbdemo.vo.CreateEmployeeResponseBody;
import com.ace.h2dbdemo.vo.Employee;
import com.ace.h2dbdemo.vo.GetEmployeeRequest;
import com.ace.h2dbdemo.vo.GetEmployeeResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeEmailRepo employeeEmailRepo;

	@Autowired
	EmployeePhoneRepo employeePhoneRepo;
	
	@Autowired
	EmployeeService employeeService;

	
	@PostMapping("/ace/createEmployee")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
		System.out.println(employee.toString());
		
		return new ResponseEntity<Object>(employee,HttpStatus.OK);

	}
	@PostMapping("/employee/get")
	public ResponseEntity<Object> getEmployee(@RequestBody GetEmployeeRequest getEmployeeRequest) {
		
		GetEmployeeResponse getEmployeeResponse =employeeService.getEmployeeDetails(getEmployeeRequest);
		return new ResponseEntity<Object>(getEmployeeResponse,HttpStatus.OK);

	}

	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody CreateEmployeeRequestBody createEmployeeRequestBody) {
		CreateEmployeeResponseBody createEmployeeResponseBody = new CreateEmployeeResponseBody();
		if(employeeService.CreateEmployeeDetails(createEmployeeRequestBody)) {
			
			createEmployeeResponseBody.setEmployeeCreated(true);
		}else {
		createEmployeeResponseBody.setEmployeeCreated(false);
		}
		return new ResponseEntity<Object>(createEmployeeResponseBody, HttpStatus.OK);
	}

}
