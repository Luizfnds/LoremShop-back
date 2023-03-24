package com.lefnds.loremshop.repositories;

import com.lefnds.loremshop.dtos.Request.FilterRequestDto;
import com.lefnds.loremshop.enums.ProductType;
import com.lefnds.loremshop.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.support.PageableUtils;

import java.lang.reflect.Field;
import java.util.*;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Product> findAllProducts(String productName, List<FilterRequestDto> filterRequestDtoList, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        List<Predicate> predicatesList = new ArrayList<>();
        Sort sort = pageable.getSort();

        if (filterRequestDtoList != null) {
            for (FilterRequestDto filterRequestDto : filterRequestDtoList) {
                if (!filterRequestDto.getFilterList().isEmpty()) {
                    List<Predicate> list = new ArrayList<>();
                    for (String filterType : filterRequestDto.getFilterList()) {
                        list.add(cb.equal(cb.lower(product.get(filterRequestDto.getFilterName())), filterType.toLowerCase()));
                    }
                    Predicate predicate = cb.or(list.toArray(new Predicate[list.size()]));
                    predicatesList.add(predicate);
                }
            }
        }

        if (productName != null) {
            if (!productName.isEmpty()) {
                predicatesList.add(cb.like(cb.lower(product.get("name")), "%" + productName.toLowerCase() + "%"));
            }
        }

        query.select(product)
                .where(cb.and(predicatesList.toArray(new Predicate[predicatesList.size()])))
                .orderBy(QueryUtils.toOrders(sort, product, cb));

        return new PageImpl<>(entityManager.createQuery(query).getResultList());
    }

}

