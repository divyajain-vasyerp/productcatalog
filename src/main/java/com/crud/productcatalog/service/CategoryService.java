package com.crud.productcatalog.service;

import java.util.List;

import com.crud.productcatalog.dto.CategoryDTO;

public interface CategoryService{

	
	List<CategoryDTO>getAllCategory();
	

	CategoryDTO getCategoryById(int id);
	

	 CategoryDTO addCategory(CategoryDTO categoryDTO);

	 CategoryDTO updateCategory(int id, CategoryDTO categoryDTO);
	 
	 void deleteById(int id);


}
