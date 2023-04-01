package com.lefnds.loremshop.repositories;

import com.lefnds.loremshop.dtos.Request.FilterRequestDto;
import com.lefnds.loremshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductRepositoryCustom {

    Page<Product> findAllProducts(String productName, List<FilterRequestDto> filterRequestDtoList, Pageable pageable);

}
