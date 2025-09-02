package DAO;


import models.Product;
import org.hibernate.dialect.Database;


import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {

    private final DataSource dataSource;

    @Inject
    public ProductDAO(DataSource updateDatasource){
        this.dataSource = updateDatasource;
    }

    public void save(Product product){
        try{
            Connection conn = dataSource.getConnection();
            String sql = "INSERT INTO products (name, description, price, date_add, date_upd) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1,product.getName());
                stmt.setString(2,product.getDescription());
                stmt.setString(3,product.getPrice()+"");
                stmt.setString(4,product.getAddDate()+"");
                stmt.setString(5,product.getUpdateDate()+"");
                stmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        try{
            Connection conn = dataSource.getConnection();
            String sql = "SELECT * FROM products";
            try(Statement stmt = conn.createStatement()){
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Product prodToAdd = new Product();
                    prodToAdd.setId(rs.getInt("id"));
                    prodToAdd.setName(rs.getString("name"));
                    prodToAdd.setDescription(rs.getString("description"));
                    prodToAdd.setPrice(rs.getDouble("price"));
                    prodToAdd.setAddDate(rs.getDate("date_add"));
                    prodToAdd.setUpdateDate(rs.getDate("date_upd"));
                    products.add(prodToAdd);
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return products;
    }

    public Product findById(int id){

        try {
            Connection conn = dataSource.getConnection();
            String sql = "SELECT * FROM products WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1,id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()){
                    Product returnProduct = new Product();
                    returnProduct.setId(id);
                    returnProduct.setName(rs.getString("name"));
                    returnProduct.setDescription(rs.getString("description"));
                    returnProduct.setPrice(rs.getDouble("price"));
                    returnProduct.setAddDate(rs.getDate("date_add"));
                    returnProduct.setUpdateDate(rs.getDate("date_upd"));
                    return returnProduct;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    public void update(Product product){
        try {
            Connection conn = dataSource.getConnection();
            String sql = "UPDATE products SET name=?, description=?, price=?, date_add=?, date_upd=? WHERE id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, product.getName());
                stmt.setString(2, product.getDescription());
                stmt.setDouble(3, product.getPrice());
                stmt.setDate(4, product.getAddDate());
                stmt.setDate(5, product.getUpdateDate());
                stmt.setInt(6, product.getId());
                stmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void delete(int id){
        try {
            Connection conn = dataSource.getConnection();
            String sql = "DELETE FROM products WHERE id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }




}
