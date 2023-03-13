package com.lefnds.loremshop.controllers;

import com.lefnds.loremshop.dtos.Request.ProductRequestDto;
import com.lefnds.loremshop.model.Product;
import com.lefnds.loremshop.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAllProducts( @PageableDefault(page = 0, size = 10,sort = "productId", direction = Sort.Direction.ASC) Pageable pageable ) {
        return ResponseEntity.status(HttpStatus.OK).body( productService.findAll(pageable) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOneProduct( @PathVariable UUID id ) {
        Optional<Product> product = productService.findById(id);
        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Product> insertProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = new Product();
        BeanUtils.copyProperties(product, productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID id,
                                                @RequestBody @Valid ProductRequestDto productRequestDto) {
        Optional<Product> oldProduct = productService.findById(id);
        if(oldProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        Product product = new Product();
        BeanUtils.copyProperties(productRequestDto, product);
        product.setProductId(oldProduct.get().getProductId());

        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        Optional<Product> entity = productService.findById(id);
        if(entity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productService.delete(entity.get());

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }


}
