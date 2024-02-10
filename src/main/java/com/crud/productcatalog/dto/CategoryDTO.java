package com.crud.productcatalog.dto;


import com.crud.productcatalog.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
	

	public CategoryDTO(Category category) {
	}
	private int categoryId;
	private String categoryName;
}
