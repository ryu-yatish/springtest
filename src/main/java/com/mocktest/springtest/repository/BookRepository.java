package com.mocktest.springtest.repository;

import com.mocktest.springtest.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,Integer> {
    List<Book> findAllByAuthorName(String Author);
    List<Book> findAllBybookName(String Author);
}
