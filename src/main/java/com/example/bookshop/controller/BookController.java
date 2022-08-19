package com.example.bookshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.bookshop.model.books;
import com.example.bookshop.service.BookService;  
//mark class as Controller  

@RestController 
public class BookController {
	
	//auto-wire the BooksService class  
	@Autowired  
	BookService BookService;  
	
	@GetMapping("/")
	public String greeting() {
		return "Welcome Student. Please go to \"/allbooks\" for all available books";
	}

	//creating a get mapping that retrieves all the books detail from the database   
	@GetMapping("/allbooks")  
	private List<books> getAllBooks() {  
		return 	BookService.getAllBooks();  
	}
  
	//creating a get mapping that retrieves the detail of a specific book  
	@GetMapping("/book/{bookid}")  
	private books getBooks(@PathVariable("bookid") int bookid){  
		return 	BookService.getBooksById(bookid);  
	}
  
	//creating a delete mapping that deletes a specified book  
	@DeleteMapping("/book/{bookid}")  
	private void deleteBook(@PathVariable("bookid") int bookid){  
		BookService.delete(bookid);  
	}
  
	//creating post mapping that post the book detail in the database  
	@PostMapping("/books")  
	private int saveBook(@RequestBody books books1){  
		BookService.saveOrUpdate(books1);  
		return books1.getBookid();  
	}
  
	//creating put mapping that updates the book detail   
	@PutMapping("/books")  
	private books update(@RequestBody books books1){  
		BookService.saveOrUpdate(books1);  
		return books1;  
	}
}