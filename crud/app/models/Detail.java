package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="details")
public class Detail {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="detail_name")
    private String detailName;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public int getId() {
        return id;
    }

    public String getDetailName() {
        return detailName;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
