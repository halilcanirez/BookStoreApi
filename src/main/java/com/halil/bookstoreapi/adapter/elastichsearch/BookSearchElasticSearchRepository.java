package com.halil.bookstoreapi.adapter.elastichsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookSearchElasticSearchRepository extends ElasticsearchRepository<BookSearchEntity,Long> {

    @Query("{\"match\": {\"title\": {\"?0\"}}")
    Page<BookSearchEntity> findAllByTitleUsingCustomQuery(String title, Pageable pageable);
}
