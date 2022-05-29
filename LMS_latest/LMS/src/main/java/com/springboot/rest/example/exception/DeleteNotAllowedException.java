package com.springboot.rest.example.exception;

public class DeleteNotAllowedException extends RuntimeException{
	 private static final long serialVersionUID = 1L;

			    public DeleteNotAllowedException(String message) {
			        super(message);
			    }

}
