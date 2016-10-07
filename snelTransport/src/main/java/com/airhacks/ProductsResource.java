package com.airhacks;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
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
    @GET
    public JsonArray products() {
        return Json.createArrayBuilder().
                add(product(1, "90-XB1000KM00020", "Keyboard", "?", "Asus U2000 Keyboard + Mouse Set", 16.45)).
                add(product(2, "90-XB2400KM00130-", "Desktop", "?", "Asus W3000 Desktop - Draadloos - Wit - Qwerty", 24.10)).
                build();
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
