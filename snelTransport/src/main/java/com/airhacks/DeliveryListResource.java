/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks;

import javax.json.Json;
import javax.json.JsonObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Array;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author p.bharwani
 */
@Path("deliverylist")
public class DeliveryListResource {

    @GET
    public JsonArray deliverylist() throws JsonProcessingException {
        JsonArrayBuilder JsonBuild = Json.createArrayBuilder();
    
        JsonBuild.add(delivery(1, "IT Pros", "AB-123-A", "001", 2));
        JsonBuild.add(delivery(2, "ByteSystems", "AB-123-B", "002", 1));
        JsonBuild.add(delivery(3, "St@lker", "AB-123-C", "003", 1));
        JsonBuild.add(delivery(4, "St@lker", "AB-123-C", "004", 3));
        return JsonBuild.build();
    }
    
    public JsonObject delivery (int id, String customerName, String licensePlate, String orderNumber, int status) {
        return Json.createObjectBuilder().
                add("id", id).
                add("customerName", customerName).
                add("licensePlate", licensePlate).
                add("orderNumber", orderNumber).
                add("status", status).
                build();
    }
}
