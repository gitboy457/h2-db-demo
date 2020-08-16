package com.ace.h2dbdemo.exception;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3468741223504459454L;

	public RecordNotFoundException( String msg) {
		super(msg);
	}

}
