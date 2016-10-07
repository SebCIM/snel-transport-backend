package com.airhacks;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author S.Mekes
 */
@Path("products")
public class ProductsResource {
    
    @PersistenceUnit
    EntityManagerFactory emf;
    
    @GET
    public JsonArray customers() throws JsonProcessingException {
        EntityManager em = emf.createEntityManager();
        ObjectMapper mapper = new ObjectMapper();
        
        List list =em.createNativeQuery("SELECT * FROM public.\"Product\"")// em.createQuery("SELECT c FROM Customer c")
                .getResultList();
        
        String clientInfo = "";
        String clientInfoReplace = "";
        JsonArrayBuilder JsonBuild = Json.createArrayBuilder();
        
        // iterate via "for loop"
        for (int i = 0; i < list.size(); i++) {
            clientInfo = mapper.writeValueAsString(list.get(i));
            clientInfoReplace = clientInfo.replaceAll("[\\[\\]\\\\\"]","");
            String[] splitClientInfo = clientInfoReplace.split(",");
            System.out.println(clientInfoReplace);
            
            JsonBuild.add(product(Integer.parseInt(splitClientInfo[4]),splitClientInfo[1], splitClientInfo[3], "?", splitClientInfo[0], Double.parseDouble(splitClientInfo[2])));
        }

        return JsonBuild.build();
    }
    
    public JsonObject product(int productId, String code, String category, String stock, String name, double price) {
        return Json.createObjectBuilder().
                add("id", productId).
                add("category", category).
                add("name", name).
                add("code", code).
                add("stock", stock).
                add("price", price).
                build();
    }
}
