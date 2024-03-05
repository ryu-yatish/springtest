package com.mocktest.springtest.service;

import com.mocktest.springtest.model.Author;
import com.mocktest.springtest.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {


    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public String saveAuthor(Author author) {
        authorRepository.save(author);
        return "SAVED AUTHOR with NAME:" + author.getName();
    }

    public List<Author> getAllAuthors() {
        try {
            return authorRepository.findAll();
        } catch (Exception e) {
            // Handle the exception or log it here
            return new ArrayList<>();
        }
    }
}
