package com.lefnds.loremshop.services;

import com.lefnds.loremshop.dtos.Request.FilterRequestDto;
import com.lefnds.loremshop.model.Product;
import com.lefnds.loremshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAllProducts( String productName,
                                          List<FilterRequestDto> filterRequestDtoList,
                                          Pageable pageable) {
        //List<Product> list = productRepository.findAllProducts( productName, filterRequestDtoList, pageable ).stream().toList();
        return productRepository.findAllProducts( productName, filterRequestDtoList, pageable );
    }

    public Optional<Product> findById( UUID productId ) {
        return productRepository.findById( productId );
    }

    public Product save ( Product product ) {
        return productRepository.save( product );
    }

    public void delete ( Product product ) {
        productRepository.delete( product );
    }

//    private byte[] convertByteaForBase64(byte[] bytea) {
//        return Base64.getDecoder().decode(bytea);
//    }

}
