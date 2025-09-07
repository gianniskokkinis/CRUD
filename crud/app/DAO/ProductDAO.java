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
import java.time.LocalDate;
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

        //test
        System.out.println("Name: "+ product.getName());
        System.out.println("Category: "+ product.getCategory().getCatName());
        //end test

        Product productToUpdate = null;
        List<Product> checkProducts = this.findAll();
        for(Product ch: checkProducts){
            if((ch.getName().equals(product.getName())) && (ch.getCategory().getCatName().equals(product.getCategory().getCatName()))){
                productToUpdate = ch;
                //test
                System.out.println("FOUND");
                //end test
                break;
            }
        }

        em.getTransaction().begin(); //begin transaction
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setAddDate(product.getAddDate());
        productToUpdate.setUpdateDate(Date.valueOf(LocalDate.now()));
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setDetailList(product.getDetailList());
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
