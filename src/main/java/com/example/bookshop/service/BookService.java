package com.example.bookshop.service;

	import java.util.ArrayList;
	import java.util.List;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import com.example.bookshop.model.books;
	import com.example.bookshop.repo.booksRepository;


	@Service
public class BookService {
	@Autowired
	booksRepository booksRepository;
	//getting all 	books record by using the method findaAll() of CrudRepository
	public List<books> getAllBooks() {
		List<books> books1 = new ArrayList<books>();
		booksRepository.findAll().forEach(books2 -> books1.add(books2));
		return books1;
	}

	//getting a specific record by using the method findById() of CrudRepository
	public books getBooksById(int id){
		return 	booksRepository.findById(id).get();
	}

	//saving a specific record by using the method save() of CrudRepository
	public void saveOrUpdate(books books1) {
		booksRepository.save(books1);
	}

	//deleting a specific record by using the method deleteById() of CrudRepository
	public void delete(int id) {
		booksRepository.deleteById(id);
	}

	//updating a record
	public void update(books books1, int bookid) {
		booksRepository.save(books1);
	}
}
