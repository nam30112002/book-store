package com.bookstore.service.impl;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if(existingBook.isEmpty()){
            Book newBook = new Book();
            newBook.setId(UUID.randomUUID());
            if(book.getTitle()!=null) newBook.setTitle(book.getTitle());
            if(book.getAuthor()!=null) newBook.setAuthor(book.getAuthor());
            if(book.getPrice()!=null) newBook.setPrice(book.getPrice());
            if(book.getCategoryId()!=null) newBook.setCategoryId(book.getCategoryId());
            return bookRepository.save(newBook);
        }
        Book result = existingBook.get();
        if(book.getTitle()!=null) result.setTitle(book.getTitle());
        if(book.getAuthor()!=null) result.setAuthor(book.getAuthor());
        if(book.getPrice()!=null) result.setPrice(book.getPrice());
        if(book.getCategoryId()!=null) result.setCategoryId(book.getCategoryId());
        return bookRepository.save(book);
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
