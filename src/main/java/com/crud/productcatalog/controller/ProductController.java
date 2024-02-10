package com.crud.productcatalog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.productcatalog.dto.ProductDTO;
import com.crud.productcatalog.service.ProductService;

@RestController
@RequestMapping("/api/")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> list()
	{
		List<ProductDTO> list=productService.getAllProducts();
		
		return ResponseEntity.ok().body(list);		
	}	
	
	
	@GetMapping("/getProductsById/{id}")
	public ResponseEntity<?> getProductsById(@PathVariable int id)
	{
		ProductDTO productById = productService.getProductById(id);
		return ResponseEntity.ok().body(productById);		
	}

	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO)
	{
//		ProductDTO productDTO1 = productService.save(productDTO,null);
		ProductDTO productDTO1 = productService.save(productDTO,0);
		return ResponseEntity.ok().body(productDTO1);
	}
	
	@PostMapping("/updateProduct/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO,@PathVariable int id)
	{
		ProductDTO productDTO1 = productService.save(productDTO,id);
		return ResponseEntity.ok().body(productDTO1);
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable int productId)
	{
		productService.delete(productId) ;
		return "id is deleted";
	}
	
	@PostMapping("/{productId}/assignCategory/{categoryId}")
	public ResponseEntity<?>assignCategoryToProduct(@PathVariable int productId,@PathVariable int categoryId)
	{
		ProductDTO productDTO = productService.assignCategoryToProduct(productId, categoryId);
        return ResponseEntity.ok("Category Assigned TO Products");
	}
	
	

	/*@PostMapping({"/addProduct", "/updateProduct/{id}"})
	public ResponseEntity<?> addOrUpdateProduct(@RequestBody ProductDTO productDTO, @PathVariable Optional<Integer> id) {
	    if (id.isPresent()) {
	        // If id is present, it's an update
	        int productId = id.get();
	        ProductDTO updatedProductDTO = productService.updateProduct(productId, productDTO);
	        return ResponseEntity.ok().body(updatedProductDTO);
	    } else {
	        // If id is not present, it's an add
	        ProductDTO addedProductDTO = productService.save(productDTO);
	        return ResponseEntity.ok().body(addedProductDTO);
	    }
	}*/
}
