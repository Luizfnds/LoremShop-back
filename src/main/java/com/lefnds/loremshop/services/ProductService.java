package com.lefnds.loremshop.services;

import com.lefnds.loremshop.model.Product;
import com.lefnds.loremshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page< Product > findAll(Pageable pageable) {
        return productRepository.findAll( pageable );
    }

    public Optional<Product> findById(UUID productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public Product save (Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void delete (Product product) {
        productRepository.delete(product);
    }


}
