package com.ltim.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = "Book API", version = "v1", description = "This is a sample API for demo Book CURD API",
contact = @Contact(name = "Dnyaneshwar kale", email = "dnyaneshwar.kale@lntinfotech.com"), license = @License(name = "book 2.0", url = "http://www.book.org/licenses/LICENSE-2.0.html")))
@SpringBootApplication
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

}
