package com.mocktest.springtest.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "book")
public class Book {

    @Field("id")
    @Id
    private int id;

    @Field("book_name")
    private String bookName;

    @Field("author_name")
    private String authorName;

}
