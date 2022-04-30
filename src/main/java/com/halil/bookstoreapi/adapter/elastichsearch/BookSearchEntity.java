package com.halil.bookstoreapi.adapter.elastichsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "book-search")
public class BookSearchEntity {
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String title;

    private String  price;

}
