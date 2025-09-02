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



    //Manage entities
    private final EntityManager em;

    //dependency injection to get the entity manager
    @Inject
    public ProductDAO(EntityManager em){
        this.em = em;
    }



    /*Save product to database*/
    public void save(Product product){
        em.getTransaction().begin(); //begin transaction
        em.persist(product); //save the entity Product to database
        em.getTransaction().commit(); //end the transaction
    }

    /* display the list of stored products*/
    public List<Product> findAll(){
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class); //custom query
        return query.getResultList(); //return the display list
    }

    /*return the product with the same id (unique)*/
    public Product findById(int id){
        return em.find(Product.class, id);
    }


    /*Update the product*/
    public void update(Product product){
        em.getTransaction().begin(); //begin transaction
        em.merge(product); //update the entity to database
        em.getTransaction().commit(); //end transaction
    }



    public void delete(int id){
        em.getTransaction().begin(); //begin transaction
        Product product = em.find(Product.class, id); //get the entity from database
        if(product!=null){
            em.remove(product); //delete the entity from database
        }
        em.getTransaction().commit(); // end transaction
    }






}
