package DAO;

import com.google.inject.Inject;
import jakarta.transaction.Transactional;
import models.Product;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.util.List;

import static org.junit.Assert.*;

public class addTestDB extends WithApplication {

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
    public void setList() {
        Product p = new Product();
        p.setName("Pizza");
        p.setPrice(10.0);

        productDAO.save(p);

        Product found = productDAO.findById(p.getId());
        assertNotNull(found);
        assertEquals("Pizza", found.getName());

        productDAO.delete(p.getId());
    }



}
