package com.crud.productcatalog.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crud.productcatalog.dto.ResponseDTO;
import com.crud.productcatalog.exception.CategoryNotFoundException;
import com.crud.productcatalog.exception.ProductNotFoundException;

@RestControllerAdvice
public class AllExceptionHandling {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDTO productNotFoundException(ProductNotFoundException e)
	{
		return new ResponseDTO(404,"error",e.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDTO nullPointerException(NullPointerException e)
	{
		return new ResponseDTO(404,"error",e.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDTO categoryNotFoundException(CategoryNotFoundException e)
	{
		return new ResponseDTO(404,"error",e.getMessage());
	}
}
