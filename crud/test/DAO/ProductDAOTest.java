package DAO;


import models.Product;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.*;
import play.Application;
import play.db.Databases;
import play.inject.guice.GuiceApplicationBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import play.inject.Bindings;
import play.test.Helpers;
import play.test.WithApplication;



public class ProductDAOTest extends WithApplication{

    private Application app;
    private ProductDAO productDAO;
    private DataSource dataSource;




    @Test
    public void testSaveAndFind(){
        /*something*/
    }

}
