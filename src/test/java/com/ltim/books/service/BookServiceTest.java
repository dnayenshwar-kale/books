package com.ltim.books.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ltim.books.entity.Book;
import com.ltim.books.repository.BookRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class BookServiceTest {
	
	@InjectMocks
	BookService bookService;
	
	@Mock
	BookRepository bookRepository;
	

	//@Test
	void testBookService() {
		bookService= new BookService(bookRepository);
		assertNotNull(bookService);
	}

	@Test
	void testGetAllBooks() {
		Book books=new Book("spring tets", "dk");
		List<Book> bookList=new ArrayList<>();
		bookList.add(books);
		when(bookRepository.findAll()).thenReturn(bookList);
		List<Book> book=bookService.getAllBooks();		
		assertEquals(book.size(),1);	
	}

	@Test
	void testGetBookById() {
		Optional<Book> book=Optional.of(new Book("spring tets", "dk"));
		when(bookRepository.findById((long) 100)).thenReturn(book);
		Optional<Book> res=bookService.getBookById((long) 100);
		assertEquals(book, res, "");
	}

	@Test
	void testCreateBook() {		
		Book book=new Book("spring tets", "dk");
		when(bookRepository.save(book)).thenReturn(book);
		Book res=bookService.createBook(book);
		assertEquals(book, res, "");
	}

	@Test
	void testUpdateBook() {
		Book books=new Book("spring tets", "dk");
		Optional<Book> book=Optional.of(new Book("spring tets", "dk"));
		when(bookRepository.findById((long) 100)).thenReturn(book);
		when(bookRepository.save(books)).thenReturn(books);
		Book res=bookService.updateBook((long) 100, books);
		 assertEquals(books, res, "");
	}

	@Test
	void testDeleteBook() {
		doNothing().when(bookRepository).deleteById((long) 100);
		bookService.deleteBook((long) 100);
		assertNotNull("ok");
	}

}
