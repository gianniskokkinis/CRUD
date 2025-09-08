package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "catName")
    private String catName;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;


    public int getId() {
        return id;
    }

    public String getCatName() {
        return catName;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
