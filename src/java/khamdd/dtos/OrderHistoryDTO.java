/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.dtos;

import java.io.Serializable;

/**
 *
 * @author KHAM
 */
public class OrderHistoryDTO implements Serializable{
    private int quantity;
    private float price;
    private String productID;

    public OrderHistoryDTO(int quantity, float price, String productID) {
        this.quantity = quantity;
        this.price = price;
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    
}
