package com.mocktest.springtest.resource;

import com.mocktest.springtest.model.Author;
import com.mocktest.springtest.model.Book;
import com.mocktest.springtest.service.AuthorService;
import com.mocktest.springtest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getAuthors")
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/getBookWithId/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/addAuthor")
    public String saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @DeleteMapping("/deleteBookWithId/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/updateBook")
    public String updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
}
