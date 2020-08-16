package com.ace.h2dbdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

	private String ErrorCode;
	
	private String ErrorMessage;
}
