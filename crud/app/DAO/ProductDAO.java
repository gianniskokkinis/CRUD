package DAO;


import models.Product;
import org.hibernate.dialect.Database;
import play.db.*;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final Database db;

    @Inject
    public ProductDAO(Database db){
        this.db = db;
    }

    public void save(Product product){
        try{
            Connection conn = db.getConnection();
            String sql = "INSERT INTO products (name,description, price, date_add, date_upd VALUES (?,?,?.?,?)";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1,product.getName());
                stmt.setString(2,product.getDescription());
                stmt.setString(3,product.getPrice()+"");
                stmt.setString(4,product.getAddDate()+"");
                stmt.setString(5,product.getUpdateDate()+"");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        try{
            Connection conn = db.getConnection();
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


    public void update(Product product){
        try {
            Connection conn = db.getConnection();
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


    public void delete(Product product){
        try {
            Connection conn = db.getConnection();
            String sql = "DELETE FROM products WHERE id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, product.getId());
                stmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }




}
