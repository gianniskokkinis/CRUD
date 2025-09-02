package Modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.typesafe.config.Config;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EntityManagerModule extends AbstractModule {

    @Override
    protected void configure(){

    }

    @Provides
    @Singleton
    public EntityManager provideEntityManager(Config config){

        Properties settings = new Properties();
        settings.put("jakarta.persistence.jdbc.driver", config.getString("db.default.driver"));
        settings.put("jakarta.persistence.jdbc.url", config.getString("db.default.url"));
        settings.put("jakarta.persistence.jdbc.user", config.getString("db.default.username"));
        settings.put("jakarta.persistence.jdbc.password", config.getString("db.default.password"));
        settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.addAnnotatedClass(models.Product.class);
        cfg.setProperties(settings);

        EntityManagerFactory emf = cfg.buildSessionFactory();
        return emf.createEntityManager();

    }

}
