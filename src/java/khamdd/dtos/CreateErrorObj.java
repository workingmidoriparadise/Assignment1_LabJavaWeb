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
public class CreateErrorObj implements Serializable {

    private String errorProductID, errorProductName, errorPrice, errorDescription, errorQuantity, errorImage;

    public CreateErrorObj() {
    }

    public CreateErrorObj(String errorProductID, String errorProductName, String errorPrice, String errorDescription, String errorQuantity, String errorImage) {
        this.errorProductID = errorProductID;
        this.errorProductName = errorProductName;
        this.errorPrice = errorPrice;
        this.errorDescription = errorDescription;
        this.errorQuantity = errorQuantity;
        this.errorImage = errorImage;
    }

    public String getErrorImage() {
        return errorImage;
    }

    public void setErrorImage(String errorImage) {
        this.errorImage = errorImage;
    }

    public String getErrorProductID() {
        return errorProductID;
    }

    public void setErrorProductID(String errorProductID) {
        this.errorProductID = errorProductID;
    }

    public String getErrorProductName() {
        return errorProductName;
    }

    public void setErrorProductName(String errorProductName) {
        this.errorProductName = errorProductName;
    }

    public String getErrorPrice() {
        return errorPrice;
    }

    public void setErrorPrice(String errorPrice) {
        this.errorPrice = errorPrice;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorQuantity() {
        return errorQuantity;
    }

    public void setErrorQuantity(String errorQuantity) {
        this.errorQuantity = errorQuantity;
    }
    
    
}
