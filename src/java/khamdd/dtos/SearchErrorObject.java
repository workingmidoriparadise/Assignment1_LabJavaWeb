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
public class SearchErrorObject implements Serializable{
    private String errorFromPrice, errorToPrice;

    public SearchErrorObject() {
    }

    public SearchErrorObject(String errorFromPrice, String errorToPrice) {
        this.errorFromPrice = errorFromPrice;
        this.errorToPrice = errorToPrice;
    }

    public String getErrorFromPrice() {
        return errorFromPrice;
    }

    public void setErrorFromPrice(String errorFromPrice) {
        this.errorFromPrice = errorFromPrice;
    }

    public String getErrorToPrice() {
        return errorToPrice;
    }

    public void setErrorToPrice(String errorToPrice) {
        this.errorToPrice = errorToPrice;
    }
    
    
}
