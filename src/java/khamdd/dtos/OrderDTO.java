/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author KHAM
 */
public class OrderDTO implements Serializable{
    private Timestamp orderDate;
    private float total;
    private String userID;

    public OrderDTO() {
    }

    public OrderDTO(Timestamp orderDate, float total, String userID) {
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}
