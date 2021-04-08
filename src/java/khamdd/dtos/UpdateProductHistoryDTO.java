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
public class UpdateProductHistoryDTO implements Serializable{
    private String productID, action;
    private Timestamp updateDate;

    public UpdateProductHistoryDTO() {
    }

    public UpdateProductHistoryDTO(String productID, String action, Timestamp updateDate) {
        this.productID = productID;
        this.action = action;
        this.updateDate = updateDate;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
    
    
}
