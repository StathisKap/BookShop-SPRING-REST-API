package com.example.bookshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.bookshop.exceptions.BookNotFoundID;

public class ExceptionController {
	 @ExceptionHandler(value = BookNotFoundID.class)
	   public ResponseEntity<Object> exception(BookNotFoundID exception) {
	      return new ResponseEntity<>("Book Not Found. Please enter the right ID", HttpStatus.NOT_FOUND);
	   }
}
