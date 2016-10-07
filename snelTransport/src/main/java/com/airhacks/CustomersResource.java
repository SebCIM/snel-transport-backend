/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks;

import ModelsPackage.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Array;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author S.Mekes
 */
@Path("customers")
public class CustomersResource {
    
    @PersistenceUnit
    EntityManagerFactory emf;
    
    @GET
    public JsonArray customers() throws JsonProcessingException {
        EntityManager em = emf.createEntityManager();
        ObjectMapper mapper = new ObjectMapper();
        // System.out.println(em);
        
        List list =em.createNativeQuery("SELECT * FROM public.\"Customer\"")// em.createQuery("SELECT c FROM Customer c")
                .getResultList();
        // System.out.println(list);
        // System.out.println(Arrays.toString(list.toArray()));
        // String jsonInString = mapper.writeValueAsString(list.get(0));
        // String jsonInString = mapper.writeValueAsString(list);

        String clientInfo = "";
        String clientInfoReplace = "";
        JsonArrayBuilder JsonBuild = Json.createArrayBuilder();
        
        // iterate via "for loop"
        for (int i = 0; i < list.size(); i++) {
            // System.out.println(list.get(i));
//            customerOne = list.get(i);
            clientInfo = mapper.writeValueAsString(list.get(i));
            clientInfoReplace = clientInfo.replaceAll("[\\[\\]\\\\\"]","");
            String[] splitClientInfo = clientInfoReplace.split(",");

            System.out.println(clientInfo);
           
            JsonBuild.add(customer(Integer.parseInt(splitClientInfo[0]),splitClientInfo[1], splitClientInfo[2], splitClientInfo[3], splitClientInfo[4], splitClientInfo[5], splitClientInfo[6], splitClientInfo[7]));
        }
            return JsonBuild.build();
    }
    
    public JsonObject customer(int code, String name, String adres, String houseNum, String postcode, String city, String telNum, String faxNum) {
        return Json.createObjectBuilder().
                add("id", code).
                add("name", name).
                add("city", city).
                add("adres", adres).
                add("houseNum", houseNum).
                add("zipCode", postcode).
                add("telNum", telNum).
                add("faxNum", faxNum).
                build();
    }
}
