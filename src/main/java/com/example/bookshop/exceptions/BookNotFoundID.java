package com.example.bookshop.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book does not exist with that ID")
public class BookNotFoundID extends EntityNotFoundException {
}
