package models;

import java.sql.Date;

import jakarta.persistence.*;


public class Product {
	
	@Id
	
	private int id;
	
	private String name;
	
	private String description; 
	
	private double price;
	
	private Date addDate;
	
	private Date updateDate;

}
