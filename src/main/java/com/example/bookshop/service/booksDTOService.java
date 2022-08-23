package com.example.bookshop.service;

import java.util.ArrayList;  
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
import com.example.bookshop.dto.booksDTO;
import com.example.bookshop.model.books;  
import com.example.bookshop.repo.booksRepository;
import lombok.AllArgsConstructor;

@Service  
@AllArgsConstructor
public class booksDTOService   
{  
	//wiring the repo 
	@Autowired  
	private booksRepository repo;
	
	//DTO Service
	private booksDTO mapToDTO(books books1) {
        booksDTO dto = new booksDTO();
        dto.setPrice(books1.getPrice());
        dto.setBookid(books1.getBookid());
        dto.setBookname(books1.getBookname());
        return dto;
    }

	public booksDTO addBook(books books1) {
        books saved =  this.repo.save(books1);
        return this.mapToDTO(saved);
    }

	public List<books> getAllBooks()   
	{  
	List<books> books1 = new ArrayList<books>();  
	repo.findAll().forEach(books2 -> books1.add(books2));  
	return books1;  
	} 

    public booksDTO updateBook(int id, books books1) {
        Optional<books> existingOptional = this.repo.findById(id);
        books existing = existingOptional.get();

        existing.setPrice(books1.getPrice());
        existing.setBookname(books1.getBookname());

        books updated =  this.repo.save(existing);
        return this.mapToDTO(updated);
    }

    public boolean removeBook(int id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}  