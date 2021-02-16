package com.pin.eshop.controller;

import java.util.ArrayList;
import java.util.List;
// import java.util.Map;
import java.util.Optional;

import com.pin.eshop.model.Product;
import com.pin.eshop.repository.ProductRepository;

// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = new ArrayList<Product>();

            productRepository.findAll().forEach(products::add);

            if (products.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        Optional<Product> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            productRepository.save(product);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") String id) {
        try {
            Optional<Product> productData = productRepository.findById(id);

            if (productData.isPresent()) {
                productRepository.save(productData.get().updateNotNulls(product));    
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @RequestMapping(value = "/product/{id}", method = RequestMethod.PATCH, 
    //     consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<?> partialUpdateProduct(@RequestBody Map<String, Object> updates, 
    //       @PathVariable("id") String id) {
    //     try {
    //         Optional<Product> productData = productRepository.findById(id);

    //         if (productData.isPresent()) {

    //             productRepository.save(updates, id);    
    //             return new ResponseEntity<>(null, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    //         }

    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        Optional<Product> productData = productRepository.findById(id);
        if ( !productData.isPresent())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
 
        try {
            if (productData.isPresent()) {
                productRepository.deleteById(id);
            } else {
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}