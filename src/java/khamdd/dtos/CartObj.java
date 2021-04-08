/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.dtos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author KHAM
 */
public class CartObj implements Serializable{
    private String memberName;
    private HashMap<String, MyOrderDTO> cart = null;

    public CartObj(String memberName) {
        this.memberName = memberName;
        this.cart = new HashMap<>();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public HashMap<String, MyOrderDTO> getCart() {
        return cart;
    }
    
    public void addCart(MyOrderDTO dto) throws Exception {
        if (this.cart.containsKey(dto.getProductID())) {
            int quantity = this.cart.get(dto.getProductID()).getQuantity() + 1;
            dto.setQuantity(quantity);
        }
        this.cart.put(dto.getProductID(), dto);
    }
    public void updateCart(String id, int newQuantity) throws Exception{
        if(this.cart.containsKey(id)){
            this.cart.get(id).setQuantity(newQuantity);
        }
    }
    
    public void removeCart(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }
}
