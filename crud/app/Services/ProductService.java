package Services;


import DAO.ProductDAO;
import models.Product;

import javax.inject.Inject;
import java.util.List;

//service here to manage the requests from frontend with backend
// Business Logic Class
public class ProductService {

    private final ProductDAO productDAO;

    @Inject
    public ProductService(ProductDAO updateProductDAO){
        this.productDAO = updateProductDAO;
    }

    //Create a new product
    public void createProduct(Product product){
        if((product.getName() == null)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        productDAO.save(product); //store to databae

    }

    //display the list with the stored products
    public List<Product> getAllProducts(){
        return productDAO.findAll();
    }


    //update the product
    public void updateProduct(Product product){
        productDAO.update(product);
    }

    //delete the product
    public void deleteProduct(int id){
        productDAO.delete(id);
    }



}
