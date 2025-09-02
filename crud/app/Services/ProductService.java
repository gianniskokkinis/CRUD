package Services;


import DAO.ProductDAO;
import models.Product;

import javax.inject.Inject;
import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    @Inject
    public ProductService(ProductDAO updateProductDAO){
        this.productDAO = updateProductDAO;
    }

    public void createProduct(Product product){
        if((product.getName() == null)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        productDAO.save(product); //store to databae

    }

    public List<Product> getAllProducts(){
        return productDAO.findAll();
    }


    public void updateProduct(Product product){
        productDAO.update(product);
    }

    public void deleteProduct(int id){
        productDAO.delete(id);
    }



}
