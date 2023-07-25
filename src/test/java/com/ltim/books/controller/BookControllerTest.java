package com.ltim.books.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ltim.books.service.BookService;
import com.ltim.books.entity.Book;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BookControllerTest {
	
	@InjectMocks
	BookController bookController;
	
	@Mock
	BookService bookService;

	//@Test
	void testBookController() {
		
		bookController=new BookController(bookService);
		assertNotNull(bookController);
	}

	@Test
	void testGetAllBooks() {
		Book books=new Book("spring tets", "dk");
		List<Book> bookList=new ArrayList<>();
		bookList.add(books);
		when(bookService.getAllBooks()).thenReturn(bookList);		
		List<Book> bookres=bookController.getAllBooks();	
       assertEquals(bookList.size(), bookres.size());
}

	@Test
	void testGetBookById() {
		Optional<Book> book=Optional.of(new Book("spring tets", "dk"));
		when(bookService.getBookById((long) 100)).thenReturn(book);	
		Book res=bookController.getBookById((long) 100);
		assertEquals(book.get(), res, "");
	}

	@Test
	void testCreateBook() {
		Book book=new Book("spring tets", "dk");
		when(bookService.createBook(book)).thenReturn(book);
		Book res=bookController.createBook(book);
		assertEquals(book, res, "");
	}

	@Test
	void testUpdateBook() {
		Book book=new Book("spring tets", "dk");
		when(bookService.updateBook((long) 100,book)).thenReturn(book);
		Book res=bookController.updateBook((long) 100, book);
		assertEquals(book, res, "");
	}

	@Test
	void testDeleteBook() {
		doNothing().when(bookService).deleteBook((long) 100);
		bookController.deleteBook(null);
		assertNotNull("ok");
	}

}
