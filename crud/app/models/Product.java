package models;

import java.sql.Date;

import jakarta.persistence.*;


@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description; 
	
	@Column(name="price")
	private double price;
	
	@Column(name="date_add")
	private Date addDate;
	
	@Column(name="date_upd")
	private Date updateDate;


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public Date getAddDate() {
		return addDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}

