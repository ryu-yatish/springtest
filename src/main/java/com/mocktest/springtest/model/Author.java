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
@Document(collection = "author")
public class Author {

    @Field("id")
    @Id
    private int id;

    @Field("age")
    private String age;

    @Field("name")
    private String name;
}
