package com.crud.productcatalog.service;

import java.util.List;

import com.crud.productcatalog.dto.ProductDTO;
import com.crud.productcatalog.model.Product;

public interface ProductService {



	public List<ProductDTO> getAllProducts();
	

	public ProductDTO getProductById(int id);
	

	public ProductDTO save(ProductDTO productDTO,int id);

	public void delete(int productId);

//	public ProductDTO save(ProductDTO productDTO, Object id);

//	public ProductDTO updateProduct(int productId, ProductDTO productDTO);
	
	 ProductDTO assignCategoryToProduct(int productId, int categoryId);


}
