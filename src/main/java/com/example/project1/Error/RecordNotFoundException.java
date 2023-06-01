package com.example.project1.Error;

public class RecordNotFoundException extends RuntimeException {

	public RecordNotFoundException() {
		super();

	}

	public RecordNotFoundException(String message) {
		super(message);

	}
}
