package DAO;


import Modules.EntityManagerProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import models.Category;
import models.Detail;
import models.Product;
import org.hibernate.dialect.Database;
import play.api.Mode;


import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;


public class ProductDAO {



    //Manage entities
    private final EntityManager em;

    //dependency injection to get the entity manager
    @Inject
    public ProductDAO(EntityManager em){
        this.em = em;
    }



    /*Save product to database*/
    public void save(Product product){
        em.getTransaction().begin(); //begin transaction
        em.persist(product); //save the entity Product to database
        em.getTransaction().commit(); //end the transaction
    }

    /* display the list of stored products*/
    public List<Product> findAll(){
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class); //custom query
        return query.getResultList(); //return the display list
    }

    /*return the product with the same id (unique)*/
    public Product findById(int id){
        return em.find(Product.class, id);
    }

    public List<Product> findFilteredProducts(JsonNode json){
        List<Product> filteredList = new ArrayList<Product>();

        //filter by category
        if(json.has("category")){

        }



        //filtered by price
        if(json.has("price")){
            double price = json.get("price").asDouble();

            if(filteredList.size()!=0){
                List<Product> fixList = new ArrayList<Product>();
                for(Product ch: filteredList){
                    if (ch.getPrice()==price){
                        fixList.add(ch);
                    }
                }
                filteredList = fixList;

            }else{
                List<Product> checkProducts = this.findAll();
                for(Product ch: checkProducts){
                    if(ch.getPrice() == price){
                        filteredList.add(ch);
                    }
                }
            }


            if(filteredList.size()==0){
                return filteredList;
            }
        }


        //filtered by name
        if(json.has("name")){
            String name = json.get("name").asText();

            if (filteredList.size()!=0){
                List<Product> fixList = new ArrayList<Product>();
                for(Product ch: filteredList){
                    if (ch.getName().equals(name)){
                        fixList.add(ch);
                    }
                }
                filteredList = fixList;

            }else{
                List<Product> checkProducts = this.findAll();
                for(Product ch: checkProducts){
                    if(ch.getName().equals(name)){
                        filteredList.add(ch);
                    }
                }
            }

            if(filteredList.size()==0){
                return filteredList;
            }
        }


        //filtered by description [like searching if words contains in description]
        if(json.has("description")){
            String description = json.get("description").asText();

            if(filteredList.size()!=0){
                List<Product> fixList = new ArrayList<Product>();
                for(Product ch: filteredList){
                    if (ch.getDescription().contains(description)){
                        fixList.add(ch);
                    }
                }
                filteredList = fixList;

            }else{
                List<Product> checkProducts = this.findAll();
                for(Product ch: checkProducts){
                    if(ch.getDescription().contains(description)){
                        filteredList.add(ch);
                    }
                }
            }


            if(filteredList.size()==0){
                return filteredList;
            }
        }


        //filtered by addDate
        if(json.has("addDate")){
            String dateStr = json.get("addDate").asText();
            Date checkDate = Date.valueOf(LocalDate.parse(dateStr));

            if(filteredList.size()!=0){
                List<Product> fixList = new ArrayList<Product>();
                for(Product ch: filteredList){
                    if (ch.getAddDate().equals(checkDate)){
                        fixList.add(ch);
                    }
                }
                filteredList = fixList;


            }else{
                List<Product> checkProducts = this.findAll();
                for(Product ch: checkProducts){
                    if(ch.getAddDate().equals(checkDate)){
                        filteredList.add(ch);
                    }
                }
            }


            if(filteredList.size()==0){
                return filteredList;
            }
        }




        //filtered by updateDate
        if(json.has("updateDate")){
            String dateStr = json.get("updateDate").asText();
            Date checkDate = Date.valueOf(LocalDate.parse(dateStr));

            if(filteredList.size()!=0){
                List<Product> fixList = new ArrayList<Product>();
                for(Product ch: filteredList){
                    if (ch.getUpdateDate().equals(checkDate)){
                        fixList.add(ch);
                    }
                }
                filteredList = fixList;


            }else{
                List<Product> checkProducts = this.findAll();
                for(Product ch: checkProducts){
                    if(ch.getUpdateDate().equals(checkDate)){
                        filteredList.add(ch);
                    }
                }
            }


            if(filteredList.size()==0){
                return filteredList;
            }
        }


        //filter by detailList
        if (json.has("detailList")){
            ArrayNode detailList = (ArrayNode) json.get("detailList");
            List<String> detailNames = StreamSupport.stream(detailList.spliterator(), false)
                    .map(node -> node.get("detailName").asText())
                    .collect(Collectors.toList());

            for(String detName: detailNames){

                if(filteredList.size()!=0){
                    List<Product> fixList = new ArrayList<Product>();
                    for(Product ch: filteredList){
                        for(Detail det: ch.getDetailList()){
                            if(det.getDetailName().equals(detName)){
                                fixList.add(ch);
                                break;
                            }
                        }
                    }
                    filteredList = fixList;

                }else{
                    List<Product> checkProducts = this.findAll();
                    for(Product ch: checkProducts){
                        for (Detail det: ch.getDetailList()){
                            if(det.getDetailName().equals(detName)){
                                filteredList.add(ch);
                                break;
                            }
                        }
                    }
                }

                if(filteredList.size()==0){
                    return filteredList;
                }


            }

        }


        //sort here
        if(json.has("sort")){
            String type = json.get("sort").asText();

            if (type.equals("price")){
                Collections.sort(filteredList, Comparator.comparingDouble(Product::getPrice));
            }else{
                if(type.equals("name")){
                    Collections.sort(filteredList, Comparator.comparing(Product::getName));
                }else{
                    if(type.equals("addDate")){
                        Collections.sort(filteredList, Comparator.comparing(Product::getAddDate));
                    }else{
                        if(type.equals("updDate")){
                            Collections.sort(filteredList, Comparator.comparing(Product::getUpdateDate));
                        }
                    }
                }
            }
        }














        return filteredList;
    }


    /*Update the product*/
    public void update(Product product){


        //Product productToUpdate = em.find(Product.class, product.getId());

        em.getTransaction().begin(); //begin transaction
        product.setUpdateDate(Date.valueOf(LocalDate.now()));
        em.merge(product);
        em.getTransaction().commit(); //end transaction
    }



    public void delete(int id){
        em.getTransaction().begin(); //begin transaction
        Product product = em.find(Product.class, id); //get the entity from database
        if(product!=null){
            em.remove(product); //delete the entity from database
        }
        em.getTransaction().commit(); // end transaction
    }






}
