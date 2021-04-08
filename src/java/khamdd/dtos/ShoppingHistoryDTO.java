/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author KHAM
 */
public class ShoppingHistoryDTO implements Serializable{
    private String orderID;
    private float totalPrice;
    private Timestamp orderDate;
    private ArrayList<OrderHistoryDTO> listOrder = null;
    
    public ShoppingHistoryDTO() {
    }

    public ShoppingHistoryDTO(String userID, float totalPrice, Timestamp orderDate) {
        this.orderID = userID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    
    public String getUserID() {
        return orderID;
    }

    public void setUserID(String userID) {
        this.orderID = userID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<OrderHistoryDTO> getListOrder() {
        return listOrder;
    }

    public void setListOrder(ArrayList<OrderHistoryDTO> listOrder) {
        this.listOrder = listOrder;
    }

}
