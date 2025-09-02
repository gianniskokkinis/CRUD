package DAO;

import com.google.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import models.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.List;

import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class ProductDAOTests extends WithApplication {

    @Inject
    private ProductDAO productDAO;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Before
    public void setup() {
        play.inject.Injector injector = app.injector();
        productDAO = injector.instanceOf(ProductDAO.class);
    }

    @Test
    @Transactional
    public void testSaveAndFindById() {
        Product p = new Product();
        p.setName("Pizza");
        p.setPrice(10.0);

        productDAO.save(p);

        Product found = productDAO.findById(p.getId());
        assertNotNull(found);
        assertEquals("Pizza", found.getName());

        productDAO.delete(p.getId());
    }


    @Test
    public void testFindAll() {
        Product p1 = new Product();
        p1.setName("Burger");
        p1.setPrice(8.0);

        Product p2 = new Product();
        p2.setName("Pasta");
        p2.setPrice(12.0);

        productDAO.save(p1);
        productDAO.save(p2);

        List<Product> products = productDAO.findAll();
        assertTrue(products.size() >= 2);

        productDAO.delete(p1.getId());
        productDAO.delete(p2.getId());
    }

    @Test
    public void testUpdate() {
        Product p = new Product();
        p.setName("Salad");
        p.setPrice(5.0);

        productDAO.save(p);

        p.setPrice(6.0);
        productDAO.update(p);

        Product updated = productDAO.findById(p.getId());
        assertEquals(6.0, updated.getPrice(), 0.0001);

        productDAO.delete(p.getId());
    }

    @Test
    public void testDelete() {
        Product p = new Product();
        p.setName("Soup");
        p.setPrice(4.0);

        productDAO.save(p);
        int id = p.getId();

        productDAO.delete(id);

        Product deleted = productDAO.findById(id);
        assertNull(deleted);

    }

}
