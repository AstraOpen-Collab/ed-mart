package com.edmart.elasticsearch.repository;

import com.edmart.elasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<Product, Long> {

    Optional<Product> findProductByProductId(Long productId);
}
