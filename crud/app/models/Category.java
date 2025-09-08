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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    @JsonIgnore
    private List<Product> products;


    public int getId() {
        return id;
    }

    public String getCatName() {
        return catName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
