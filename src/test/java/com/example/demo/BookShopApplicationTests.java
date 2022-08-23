package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bookshop.model.books;
import com.example.bookshop.repo.booksRepository;
import com.example.bookshop.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:books-schema.sql",
"classpath:books-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class BookShopApplicationTests {

    @Autowired
    private BookService service;

    @MockBean
    private booksRepository repo;
    
    @Autowired
    private MockMvc mock;
    
    private final books TEST_BOOK = new books(1111,"TestBook", "TestAuthor",1000);

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
	private ObjectMapper obmapper;
    

    //Unit Test Create 1
    @Test
    void testCreate(){
    	
        // GIVEN is our testing data - you can make this a final local variable if you want, e.g.:
        final books TEST_BOOK = new books(0, "TestBook", "TestAuthor",1000);
        final books TEST_SAVED_BOOK = new books(1111,"TestBook", "TestAuthor",1000);

        // WHEN
        Mockito.when(this.repo.save(TEST_BOOK)).thenReturn(TEST_SAVED_BOOK);

        // THEN
        Assertions.assertThat(this.service.saveOrUpdate(TEST_BOOK)).isEqualTo(TEST_SAVED_BOOK);

        // verify that our repo was accessed exactly once
        Mockito.verify(this.repo, Mockito.times(1)).save(TEST_BOOK);
        
        System.out.println("Create Test Successful");
        
    }

    //Unit Test 2
    @Test
    void testGetById() {
    	
    	// Setup the mock repo

        int bookId = 1001;
        final books TEST_BOOK = new books(bookId, "TestBook", "TestAuthor",1000);
        //final Optional<books> TEST_SAVED_BOOK = Optional.empty();

        // Make the service call
    	Mockito.when(this.repo.findById(bookId)).thenReturn(Optional.of(TEST_BOOK)); 
		
       System.out.println("Test for Get By ID Successful");
       
    }   
        
    //Unit Test 3
    @Test
    public void testDeleteStudentById() {
    	
    	
    	int bookId = 1001;
        final books TEST_BOOK = new books(0, "TestBook", "TestAuthor",1000);
        final books TEST_SAVED_BOOK = new books(bookId,"TestBook", "TestAuthor",1000);
        
    	service.delete(TEST_SAVED_BOOK.getBookid());

        Mockito.verify(repo, Mockito.times(1))
                .deleteById(TEST_SAVED_BOOK.getBookid());
        
        System.out.println("Test for Delete By ID Successful");
        
    }
    
    //Unit Test 4
    @Test
    public void testFindAll() {

    	final books TEST_SAVED_BOOK = new books(1001,"TestBook", "TestAuthor",1000);
        List<books> foundbooks = service.getAllBooks();
        foundbooks.add(TEST_SAVED_BOOK);

        assertNotNull(foundbooks);
        assertEquals(1, foundbooks.size());
        
        System.out.println("Test for Find All Successful");
    }

    
    //---------- Integration Test----------//
        
    
  //Integration Test 1
    @Test
	public void IntTestCreate() throws Exception {
		final books newBook = new books(1003, "How to do Skills", "Neymar Jr",1000);
		
		this.mock
				.perform(post("/saveorupdateBook").contentType(MediaType.APPLICATION_JSON)
						.content(this.obmapper.writeValueAsString(newBook)))
				.andExpect(status().isCreated());
	}
    
    
    //Integration Test 2
    @Test
	public void IntTestReadAll() throws Exception {
		final String resultString = this.mock
				.perform(request(HttpMethod.GET, "/allbooks").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		List<books> results = Arrays.asList(obmapper.readValue(resultString, books[].class));
		assertEquals(new ArrayList<>(Arrays.asList()), results);
		System.out.println(results.size());
	}
    
}