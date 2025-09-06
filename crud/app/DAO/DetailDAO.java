package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Detail;

import javax.inject.Inject;
import java.util.List;

public class DetailDAO {


    private final EntityManager em;

    @Inject
    public DetailDAO(EntityManager em){
        this.em = em;
    }

    public void save(Detail detail){
        em.getTransaction().begin();
        em.persist(detail);
        em.getTransaction().commit();
    }

    public List<Detail> findAll(){
        TypedQuery<Detail> query = em.createQuery("SELECT d FROM Detail d", Detail.class);
        return query.getResultList();
    }

    public void update(Detail detail){
        em.getTransaction().begin();
        em.merge(detail);
        em.getTransaction().commit();
    }

    public void delete(Detail detail){
        em.getTransaction().begin();
        em.remove(detail);
        em.getTransaction().commit();
    }

}
