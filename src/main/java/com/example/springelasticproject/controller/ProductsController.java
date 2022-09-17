package com.example.springelasticproject.controller;

import com.example.springelasticproject.model.Product;
import com.example.springelasticproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class ProductsController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

}
