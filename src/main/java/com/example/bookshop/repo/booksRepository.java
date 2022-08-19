package com.example.bookshop.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.bookshop.model.*;

public interface booksRepository extends CrudRepository<books, Integer>{
}