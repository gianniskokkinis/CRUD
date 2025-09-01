package models;

import java.sql.Date;

import jakarta.persistence.*;


@Entity
@Table(name="users")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
	private String name;
	
	@Column(name="description")
	private String description; 
	
	@Column(name="price")
	private double price;
	
	@Column(name="date_add")
	private Date addDate;
	
	@Column(name="date_upd")
	private Date updateDate;

}
