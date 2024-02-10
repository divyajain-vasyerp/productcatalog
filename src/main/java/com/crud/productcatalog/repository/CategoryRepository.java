package com.crud.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.productcatalog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("Select C from Category C ORDER BY C.categoryId ASC")
	List<Category> findAll();
}
