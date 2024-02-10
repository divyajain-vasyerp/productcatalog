package com.crud.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.productcatalog.dto.ProductDTO;
import com.crud.productcatalog.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


	@Query("select P from Product P ORDER BY P.productId ASC")
	List<Product>findAll();

}
