package com.crud.productcatalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name="product_price")
	private double productPrice;

	private String description;
	
	@ManyToOne
	private Category category;
}
