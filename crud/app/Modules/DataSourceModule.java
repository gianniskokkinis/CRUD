package Modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import com.typesafe.config.Config;

public class DataSourceModule extends AbstractModule {

    private final Config config;

    @Inject
    public DataSourceModule(Config config){
        this.config = config;
    }

    protected void configure(){
        bind(DataSource.class).toInstance(createDataSource());
    }

    private DataSource createDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(config.getString("db.default.driver"));
        ds.setUrl(config.getString("db.default.url"));
        ds.setUsername(config.getString("db.default.username"));
        ds.setPassword(config.getString("db.default.password"));
        return ds;
    }

}
