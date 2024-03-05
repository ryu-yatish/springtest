package com.mocktest.springtest.repository;

import com.mocktest.springtest.model.Author;
import com.mocktest.springtest.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AuthorRepository extends MongoRepository<Author,Integer> {
}
