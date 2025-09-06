package controllers;

import DAO.ProductDAO;
import com.fasterxml.jackson.databind.JsonNode;
import models.Product;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.libs.Json;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *  REST CONTROLLER for manage products
 *
 * */
public class ProductController extends Controller {

    private final ProductDAO productDAO;

    @Inject
    public ProductController(ProductDAO updateProductDAO){

        this.productDAO = updateProductDAO;

    }

    private Result statusCode(int statusCode, JsonNode json){
        switch (statusCode){
            case 200: return ok(json);
            case 201: return created(json);
            case 400: return badRequest(json);
            case 500: return internalServerError(json);
            default: return status(statusCode, json);
        }
    }

    private Result buildResponse(int statusCode, String status, String message, Object data){

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);

        if(data!=null){
            response.put("data", data);
        }

        return statusCode(statusCode, Json.toJson(response));

    }

    //POST
    public Result createProduct(Http.Request request){
        JsonNode json = request.body().asJson(); //take the json body of request

        //case not json
        if(json == null){
           return buildResponse(400, "fails", "Invalid JSON", null);
        }

        Product product = Json.fromJson(json, Product.class);//convert the json body to Product


        if((product.getName() == null)){
            return buildResponse(400,"fails", "Name cannot be empty", null);
        }


        try {
            //save to DB
            productDAO.save(product); //store to databae
        }catch (Exception e){
            return buildResponse(400, "fails", e.getMessage(), null);
        }


        //return to database
        return buildResponse(201, "success", "Product Created", product);

    }

    //GET
    public Result getAllProducts(){
        try {
            List<Product> displayProducts = productDAO.findAll();
            return buildResponse(200, "success", "Product fetched successfully", displayProducts);
        }catch (Exception e){
            return buildResponse(400, "fails", e.getMessage(), null);
        }
    }

    //PUT
    public Result updateProduct(Http.Request request){
        JsonNode json = request.body().asJson();

        //case not json
        if(json == null){
            return buildResponse(400, "fails", "Invalid JSON", null);
        }

        Product productToUpdate = Json.fromJson(json, Product.class);

        try{
            productDAO.update(productToUpdate);
            return buildResponse(200, "success", "Product updated", productToUpdate);
        }catch (Exception e){
            return buildResponse(400, "fails", e.getMessage(), null);
        }
    }


    //DELETE
    public Result deleteProduct(Http.Request request){

        JsonNode json = request.body().asJson();

        //case not json
        if(json == null){
            return buildResponse(400, "fails", "Invalid JSON", null);
        }

        Product productToDelete = Json.fromJson(json, Product.class);

        try{
            productDAO.delete(productToDelete.getId());
            return buildResponse(200,"success","Product delete with id: "+ productToDelete.getId(), productToDelete);
        }catch (Exception e){
            return buildResponse(400, "fails", e.getMessage(), null);
        }
    }



}
