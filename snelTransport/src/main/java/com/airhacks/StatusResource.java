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
@Path("status")
public class StatusResource {
    @GET
    public JsonArray statuses() throws JsonProcessingException {
        JsonArrayBuilder JsonBuild = Json.createArrayBuilder();
    
        JsonBuild.add(status(1, "In behandeling"));
        JsonBuild.add(status(2, "Verzonden"));
        JsonBuild.add(status(3, "Afgeleverd"));
        JsonBuild.add(status(4, "Geannuleerd"));
        return JsonBuild.build();
    }
    
    public JsonObject status(int id, String status) {
        return Json.createObjectBuilder().
                add("id", id).
                add("status", status).
                build();
    }
}
