package Modules;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import com.typesafe.config.Config;
import models.Product;
import org.hibernate.jpa.HibernatePersistenceProvider;
import play.api.Configuration;
import play.Environment;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* This is the provider for EntityManagers*/

@Singleton
public class EntityManagerProvider implements Provider<EntityManager> {

    private final EntityManagerFactory emf;

    @Inject
    public EntityManagerProvider(Config config) {

        Properties props = new Properties();

        //take configurations from application.conf file
        props.put("jakarta.persistence.jdbc.driver", config.getString("db.default.driver"));
        props.put("jakarta.persistence.jdbc.url", config.getString("db.default.url"));
        props.put("jakarta.persistence.jdbc.user", config.getString("db.default.username"));
        props.put("jakarta.persistence.jdbc.password", config.getString("db.default.password"));

        //manage the schema to database
        props.put("hibernate.hbm2ddl.auto", config.getString("jpa.hibernate.hbm2ddl"));
        props.put("hibernate.dialect", config.getString("jpa.hibernate.dialect"));


        org.hibernate.cfg.Configuration hibernateCfg = new org.hibernate.cfg.Configuration(); //Ηibrenate Configuration
        //connect @Entity class
        hibernateCfg.addAnnotatedClass(models.Product.class);
        hibernateCfg.addAnnotatedClass(models.Category.class);
        hibernateCfg.addAnnotatedClass(models.Detail.class);
        hibernateCfg.setProperties(props);


        emf = hibernateCfg.buildSessionFactory();
    }

    @Override
    public EntityManager get() {
        return emf.createEntityManager();
    }

}
