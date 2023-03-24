package com.lefnds.loremshop.repositories;

import com.lefnds.loremshop.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository< Product, UUID >, ProductRepositoryCustom {

}
