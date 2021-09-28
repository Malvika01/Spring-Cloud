package com.malvika.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malvika.springcloud.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
