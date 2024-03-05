package com.mocktest.springtest.service;

import com.mocktest.springtest.model.Book;
import com.mocktest.springtest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            // Handle the exception or log it here
            return new ArrayList<>();
        }
    }

    public Book getBookById(int id) {
        try {
            Optional<Book> bookOptional = bookRepository.findById(id);

            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                String bookName = book.getBookName();
                return book;
            } else {
                return null;
            }
        } catch (Exception e) {
            // Handle the exception or log it here
            return null;
        }
    }

    public String saveBook(Book book) {
            bookRepository.save(book);
            return "SAVED BOOK with NAME:" + book.getBookName();
    }

    public String deleteBook(int bookId) {
        try {
            AtomicReference<String> bookName = new AtomicReference<>("");
            bookRepository.findById(bookId).ifPresent(book -> {
                bookRepository.delete(book);
                bookName.set(book.getBookName());
            });
            return "DELETED BOOK with NAME:" + bookName;
        } catch (Exception e) {
            // Handle the exception or log it here
            return e.toString();
        }
    }

    public String updateBook(Book book) {
        try {
            bookRepository.findById(book.getId()).ifPresent(oldBook -> {
                oldBook.setBookName(book.getBookName());
                oldBook.setAuthorName(book.getAuthorName());
                bookRepository.save(oldBook);
            });
            return "UPDATED BOOK with NAME:" + book.getBookName();
        } catch (Exception e) {
            // Handle the exception or log it here
            return e.toString();
        }
    }
}
