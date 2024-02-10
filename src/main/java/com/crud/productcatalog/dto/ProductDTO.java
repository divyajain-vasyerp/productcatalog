package com.crud.productcatalog.dto;

import com.crud.productcatalog.model.Category;
import com.crud.productcatalog.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	public ProductDTO(Product product) {
	}

	private int productId;

	private String productName;

	private double productPrice;

	private String description;

	private Category categoryId;


//	public ProductDTO(Product product) {
//		this.productId = product.getProductId();
//		this.productName = product.getProductName();
//		this.productPrice = product.getProductPrice();
//		this.description = product.getDescription();
//	}
}
