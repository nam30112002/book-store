package com.bookstore.service;

import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(UUID id);
    Book saveBook(Book book);
    void deleteBook(UUID id);
}
