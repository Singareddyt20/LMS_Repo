package com.springboot.rest.example.exception;

public class UpdateBookNotAllowedException extends RuntimeException{
	 private static final long serialVersionUID = 1L;

			    public UpdateBookNotAllowedException(String message) {
			        super(message);
			    }

}
