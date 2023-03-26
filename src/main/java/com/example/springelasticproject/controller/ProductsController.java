package com.example.springelasticproject.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.springelasticproject.model.Product;
import com.example.springelasticproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/")
@RestController
public class ProductsController {
    private final ProductRepository productRepository;
    private final ElasticsearchClient elasticsearchClient;

    @Autowired
    public ProductsController(ProductRepository productRepository, ElasticsearchClient elasticsearchClient) {
        this.productRepository = productRepository;
        this.elasticsearchClient = elasticsearchClient;
    }

    @GetMapping("")
    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }
    @PostMapping("")
    public Product save(@RequestBody Product product){
        return productRepository.save(product);
    }
    @PutMapping("update")
    public Product update(@RequestBody Product product) throws Exception {
        if (product.getId()!=0){
            return productRepository.save(product);
        }
        throw new Exception("Id is required");
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        productRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
    @GetMapping("search/{name}")
    public List<Product> searchHits(@PathVariable("name") String name) throws IOException {
        SearchResponse<Product> searchResponse= elasticsearchClient.search(s -> s
                        .index("products")
                        .query(q -> q
                                .match(t -> t
                                        .field("name")
                                        .query(name)
                                )
                        ),
                Product.class
        );
        List<Hit<Product>> hits =  searchResponse.hits().hits();
        List<Product> products=new ArrayList<>();
        for (Hit<Product> hit:hits){
            products.add(hit.source());
        }
        return products;
    }

}
