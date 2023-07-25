package com.ltim.books.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltim.books.entity.Book;
import com.ltim.books.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value="",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookById(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.getBookById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return new Book();
        }
    }

    @PostMapping(value="",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }
    
    @DeleteMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteBook(@PathVariable Long id) {
    	Optional<Book> optionalBook = bookService.getBookById(id);
        if (optionalBook.isPresent()) {
        	 bookService.deleteBook(id);
        } else {
            return "book not present with id "+id;
        }
        return "book deleted for book id "+id;
    }
	
	
}
