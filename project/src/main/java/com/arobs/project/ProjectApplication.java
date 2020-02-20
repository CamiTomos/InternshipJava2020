package com.arobs.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProjectApplication {
    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/library-app");
        ApplicationContext applicationContext = SpringApplication.run(ProjectApplication.class, args);
//        BookRepository bookRepository = (BookRepository) applicationContext.getBean("bookRepository");
//        System.out.println(bookRepository.getAllBooks().size());
//		for (Book book:bookRepository.getAllBooks()
//			 ) {
//			System.out.println(book.toString());
//		}

//        BookService bookService = (BookService) applicationContext.getBean("bookService");
//        System.out.println(bookService.getAllBooks().size());
//		for (Book book:bookService.getAllBooks()
//			 ) {
//			System.out.println(book.toString());
//		}
    }

}
