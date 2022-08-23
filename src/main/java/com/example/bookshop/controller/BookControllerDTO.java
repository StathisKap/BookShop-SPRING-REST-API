package com.example.bookshop.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookshop.dto.booksDTO;
import com.example.bookshop.model.books;
import com.example.bookshop.service.booksDTOService;

@RestController
public class BookControllerDTO {
	
	@Autowired
	private booksDTOService service;

    public BookControllerDTO(booksDTOService service) {
        super();
        this.service = service;
    }

    @PostMapping("/createDTO")
    public booksDTO addBook(@RequestBody books books1) {
        return this.service.addBook(books1);
    }

    @GetMapping("/getAllDTO")
    public List<books> getAllBooks() {
        return this.service.getAllBooks();
    }

    @PutMapping("/updateDTO")
    public booksDTO updatePerson(@PathParam("id") int id, @RequestBody books books1) {
        return this.service.updateBook(id, books1);
    }

    @DeleteMapping("/deleteDTO/{id}")
    public boolean removePerson(@PathVariable int id) {
        return this.service.removeBook(id);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello, World!";
    }
}