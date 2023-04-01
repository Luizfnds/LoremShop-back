package com.lefnds.loremshop.services;

import com.lefnds.loremshop.dtos.Request.FilterRequestDto;
import com.lefnds.loremshop.model.Product;
import com.lefnds.loremshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAllProducts(String productName, List<FilterRequestDto> filterRequestDtoList, Pageable pageable) {
        List<Product> list = productRepository.findAllProducts(productName, filterRequestDtoList, pageable).stream().toList();
        list.stream().forEach(product -> product.setImage(convertByteaForBase64(product.getImage())));
        return new PageImpl<>(list);
    }

    public Optional<Product> findById(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        product.get().setImage(convertByteaForBase64(product.get().getImage()));
        return product;
    }

    public Product save (Product product) {
        return productRepository.save(product);
    }

    public void delete (Product product) {
        productRepository.delete(product);
    }

    private byte[] convertByteaForBase64(byte[] bytea) {
        return Base64.getDecoder().decode(bytea);
    }

}
