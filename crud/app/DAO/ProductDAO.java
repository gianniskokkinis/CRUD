package DAO;


import Modules.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import models.Product;
import org.hibernate.dialect.Database;


import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ProductDAO {


    private final EntityManager em;

    @Inject
    public ProductDAO(EntityManager em){
        this.em = em;
    }



    public void save(Product product){
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public List<Product> findAll(){
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    public Product findById(int id){
        return em.find(Product.class, id);
    }


    public void update(Product product){
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }



    public void delete(int id){
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if(product!=null){
            em.remove(product);
        }
        em.getTransaction().commit();
    }






}
