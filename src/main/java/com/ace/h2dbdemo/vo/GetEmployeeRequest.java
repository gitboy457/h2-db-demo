package com.ace.h2dbdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "request for searching employee")
public class GetEmployeeRequest {
	
@ApiModelProperty(name = "firstName", dataType = "String",required = true, example = "anli")
	private String firstName;

@ApiModelProperty(name = "lastName", dataType = "String",required = true, example = "sharma")
	private String lastName;

@ApiModelProperty(name = "phoneNumber", dataType = "String",required = false, example = "9878675645")
	private String phoneNumber;

@ApiModelProperty(name = "phoneCountryCode", dataType = "String",required = false, example = "91")
	private String phoneCountryCode;

@ApiModelProperty(name = "email", dataType = "String",required = false, example = "anil@gmail.com")
	private String email;
}
