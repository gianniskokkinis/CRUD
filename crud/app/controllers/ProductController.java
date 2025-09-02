package controllers;

import Services.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import models.Product;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.libs.Json;

import javax.inject.Inject;
import java.util.List;

/**
 *
 *  REST CONTROLLER for manage products
 *
 * */
public class ProductController extends Controller {

    private final ProductService productService;

    @Inject
    public ProductController(ProductService updateProductService){
        this.productService = updateProductService;
    }

    //POST
    public Result createProduct(Http.Request request){
        JsonNode json = request.body().asJson(); //take the json body of request

        //case not json
        if(json == null){
            return badRequest("Invalid JSON");
        }

        Product product = Json.fromJson(json, Product.class);//convert the json body to Product

        productService.createProduct(product);
        return created("Product created"); //201 created
    }

    //GET
    public Result getAllProducts(Http.Request request){
        List<Product> displayProducts = productService.getAllProducts();
        return ok(Json.toJson(displayProducts)); //200 OK
    }

    //PUT
    public Result updateProduct(Http.Request request){
        JsonNode json = request.body().asJson();

        //case not json
        if(json == null){
            return badRequest("Invalid JSON");
        }

        Product productToUpdate = Json.fromJson(json, Product.class);

        try{
            productService.updateProduct(productToUpdate);
            return ok(Json.toJson(productToUpdate));
        }catch (Exception e){
            return internalServerError("Can't update product: "+ e.getMessage());
        }
    }


    //DELETE
    public Result deleteProduct(int id){
        try{
            productService.deleteProduct(id);
            return ok("Product delete with id: "+ id);
        }catch (Exception e){
            return internalServerError("Can't delete product: "+ e.getMessage());
        }
    }



}
