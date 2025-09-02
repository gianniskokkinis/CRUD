package DAO;


import models.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import play.inject.Bindings;
import play.test.Helpers;
import play.test.WithApplication;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDAOTest {

    private Application app;
    private ProductDAO productDAO;
    private DataSource dataSource;


    @BeforeAll
    void setup() {
        // Δημιουργία application με την υπάρχουσα configuration
        app = new GuiceApplicationBuilder()
                .build();

        // Ξεκίνημα της εφαρμογής
        Helpers.start(app);

        // Παίρνουμε το DataSource και δημιουργούμε το DAO
        dataSource = app.injector().instanceOf(DataSource.class);
        productDAO = new ProductDAO(dataSource);
    }

    @AfterAll
    void stopApp() {
        if (app != null) {
            Helpers.stop(app);
        }
    }

    @BeforeEach
    void cleanTable() {
        try (Connection conn = dataSource.getConnection()) {
            conn.createStatement().execute("DELETE FROM products");
        } catch (SQLException e) {
            throw new RuntimeException("Error cleaning table", e);
        }
    }


    @Test
    public void testSaveAndFind(){
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(9.99);
        product.setAddDate(Date.valueOf(LocalDate.now()));
        product.setUpdateDate(Date.valueOf(LocalDate.now()));

        // Save
        productDAO.save(product);

        // Find
        Product found = productDAO.findById(product.getId());
        //test
        System.out.println("Product name: "+ product.getName());
        //end test
        Assertions.assertNotNull(found);
        Assertions.assertEquals("Test Product", found.getName());
    }

}
