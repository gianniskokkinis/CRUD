package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Category;
import models.Product;

import javax.inject.Inject;
import java.util.List;

public class CategoryDAO {

    private final EntityManager em;

    @Inject
    public CategoryDAO(EntityManager em){
        this.em = em;
    }

    public void save(Category category){
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    public List<Category> findAll(){
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    public Category findById(int id){
        return em.find(Category.class, id);
    }

    public void update(Category category){
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
    }

    public void delete(Category category){
        em.getTransaction().begin();
        em.remove(category);
        em.getTransaction().commit();
    }


}
