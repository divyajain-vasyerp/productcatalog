package com.crud.productcatalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.productcatalog.dto.CategoryDTO;
import com.crud.productcatalog.dto.ProductDTO;
import com.crud.productcatalog.exception.CategoryNotFoundException;
import com.crud.productcatalog.exception.ProductNotFoundException;
import com.crud.productcatalog.model.Category;
import com.crud.productcatalog.model.Product;
import com.crud.productcatalog.repository.CategoryRepository;
import com.crud.productcatalog.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> list = new ArrayList<>();
		List<Product> productlist = productRepository.findAll();
		for (Product product : productlist) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductId(product.getProductId());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());
			productDTO.setDescription(product.getDescription());

			Category category = product.getCategory();
			if (category != null) {

				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setCategoryId(product.getCategory().getCategoryId());
				categoryDTO.setCategoryName(product.getCategory().getCategoryName());
				productDTO.setCategoryId(category);
			}

			list.add(productDTO);
		}
		return list;
	}

	@Override
	public ProductDTO getProductById(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found: " + id));
		Category category = product.getCategory();

		ProductDTO productDTO = new ProductDTO();
//		CategoryDTO categoryDTO = new CategoryDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductPrice(product.getProductPrice());
		productDTO.setDescription(product.getDescription());

		if (category != null) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryId(product.getCategory().getCategoryId());
			categoryDTO.setCategoryName(product.getCategory().getCategoryName());
			productDTO.setCategoryId(category);
		}

		return productDTO;
	}

	@Override
	public ProductDTO save(ProductDTO productDTO, int id) {
		// For Insert Product
		if (id == 0) {
			Product product = new Product();
			Category category = null; // Allow null library for new student
			if (productDTO.getCategoryId() != null) {
				category = categoryRepository.findById(productDTO.getCategoryId().getCategoryId())
						.orElseThrow(() -> new ProductNotFoundException("Product not found"));

//				category = new Category();
				category.setCategoryId(productDTO.getProductId());
				category.setCategoryName(productDTO.getCategoryId().getCategoryName());
//				categoryRepository.save(category);
				product.setCategory(category);
			}
			product.setCategory(category);
			product.setProductName(productDTO.getProductName());
			product.setProductPrice(productDTO.getProductPrice());
			product.setDescription(productDTO.getDescription());
			productRepository.save(product);
		} else {
			// For Update Products
			Optional<Product> optionalProduct = productRepository.findById((int) id);
//			Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product ID is not found"));

			if (optionalProduct.isPresent()) {
				Product productEdit = optionalProduct
						.orElseThrow(() -> new ProductNotFoundException("Product ID is not found"));
				if (productDTO.getProductName() != null) {
					productEdit.setProductName(productDTO.getProductName());
				}
				if (productDTO.getProductPrice() != 0) {
					productEdit.setProductPrice(productDTO.getProductPrice());
				}
				if (productDTO.getDescription() != null) {
					productEdit.setDescription(productDTO.getDescription());
				}
				Category categoryEdit = productEdit.getCategory();
				if (categoryEdit != null && productDTO.getCategoryId() != null) {
					categoryEdit.setCategoryId(productDTO.getProductId());
					categoryEdit.setCategoryName(productDTO.getProductName());
				}
				productRepository.save(productEdit);
			}
		}
		return productDTO;
	}

	@Override
	public void delete(int productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Id is Not Found"));
		productRepository.deleteById(productId);
	}

	@Override
	public ProductDTO assignCategoryToProduct(int productId, int categoryId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
		if (product.getCategory() != null && product.getCategory().getCategoryId() == categoryId) {
			throw new CategoryNotFoundException("This Category already assigned to this product.");
		}
		
		  // Retrieve the category by ID
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
        
        // Assign the category to the product
        product.setCategory(category);

		productRepository.save(product);
		return new ProductDTO(product);
	}

	// For Specific Update Products
	/*
	 * public ProductDTO updateProduct(int productId, ProductDTO productDTO) {
	 * Optional<Product> optionalProduct = productRepository.findById(productId); if
	 * (optionalProduct.isPresent()) { Product productEdit = optionalProduct
	 * .orElseThrow(() -> new NullPointerException("Any Feilds is null values")); if
	 * (productDTO.getProductName() != null) {
	 * productEdit.setProductName(productDTO.getProductName()); } if
	 * (productDTO.getProductPrice() != 0) {
	 * productEdit.setProductPrice(productDTO.getProductPrice()); } if
	 * (productDTO.getDescription() != null) {
	 * productEdit.setDescription(productDTO.getDescription()); } Category
	 * categoryEdit = productEdit.getCategory(); if (categoryEdit != null &&
	 * productDTO.getCategoryId() != null) {
	 * categoryEdit.setCategoryId(productDTO.getProductId());
	 * categoryEdit.setCategoryName(productDTO.getProductName()); }
	 * productRepository.save(productEdit); } return productDTO; }
	 */
}
