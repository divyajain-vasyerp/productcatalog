package com.crud.productcatalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.productcatalog.dto.CategoryDTO;
import com.crud.productcatalog.exception.CategoryNotFoundException;
import com.crud.productcatalog.exception.ProductNotFoundException;
import com.crud.productcatalog.model.Category;
import com.crud.productcatalog.model.Product;
import com.crud.productcatalog.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> getAllCategory() {

		List<CategoryDTO> list = new ArrayList<>();
		List<Category> categoryList = categoryRepository.findAll();
		for (Category category : categoryList) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryName(category.getCategoryName());
			categoryDTO.setCategoryId(category.getCategoryId());

			list.add(categoryDTO);
		}
		return list;
	}

	public CategoryDTO getCategoryById(int id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category Id not found: " + id));
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());
		return categoryDTO;
	}

	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category category = new Category(categoryDTO);
		Category save = categoryRepository.save(category);
		return new CategoryDTO(save);
	}

	@Override
	public void deleteById(int id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category Id is not Found:  " + id));
		categoryRepository.deleteById(id);
	}

	@Override
	public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("category id is not found: "+id));
		if(categoryDTO.getCategoryId()!=0)
		{
			category.setCategoryId(categoryDTO.getCategoryId());
		}
		if(categoryDTO.getCategoryName()!=null)
		{
			category.setCategoryName(categoryDTO.getCategoryName());
		}
		Category save = categoryRepository.save(category);
		return new CategoryDTO(save);
	}
}
