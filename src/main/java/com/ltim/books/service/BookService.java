package com.ltim.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltim.books.entity.Book;
import com.ltim.books.repository.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Book> getBookById(Long id) {
		return bookRepository.findById(id);
	}

	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	public void updateBook(Long id, Book book) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book existingBook = optionalBook.get();
			existingBook.setTitle(book.getTitle());
			existingBook.setAuthor(book.getAuthor());
			bookRepository.save(existingBook);
		}
	}
}
