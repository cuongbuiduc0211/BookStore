/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author USER
 */
public class CartObj implements Serializable{
    private String customerId;
    private Map<String, Integer> items;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String bookTitle){
        if (items == null) {
            items = new HashMap<>();
        }
        int quantity = 1;
        if (items.containsKey(bookTitle)) {
            quantity = items.get(bookTitle) + 1;
        }
        items.put(bookTitle, quantity);
    }
    
    public void removeItemFromCart(String bookTitle){
        if (items.containsKey(bookTitle)) {
            items.remove(bookTitle);
            if (items.isEmpty()) {
                items = null; 
            }
        }
    }
}
