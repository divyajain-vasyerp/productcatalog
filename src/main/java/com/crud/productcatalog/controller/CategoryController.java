package com.crud.productcatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.productcatalog.dto.CategoryDTO;
import com.crud.productcatalog.model.Category;
import com.crud.productcatalog.service.CategoryService;

@RestController
@RequestMapping("/api/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDTO>> getAllCategory() {
		List<CategoryDTO> allCategory = categoryService.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}
	
	@GetMapping("/getCategoryById/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable int id) {
		CategoryDTO categoryById = categoryService.getCategoryById(id);
		return ResponseEntity.ok(categoryById);
	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO)
	{
		CategoryDTO categoryDTO2 = categoryService.addCategory(categoryDTO);
		return ResponseEntity.ok("Category is added");
	}
	
	
	@PutMapping("/updateCategoryById/{id}")
	public ResponseEntity<?> updateCategoryById(@PathVariable int id,@RequestBody CategoryDTO categoryDTO)
	{
		categoryService.updateCategory(id,categoryDTO);
		return ResponseEntity.ok("updated SucessFully: "+id+categoryDTO);
	}
	@DeleteMapping("/deleteCategoryById/{id}")
	public String deleteCategoryById(@PathVariable int id)
	{
		categoryService.deleteById(id);
		return "Id is Deleted: "+id;
	}
}
