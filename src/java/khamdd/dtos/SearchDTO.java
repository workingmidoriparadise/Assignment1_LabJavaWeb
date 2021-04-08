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
public class SearchDTO implements Serializable {

    private String name, category;
    private float fromPrice, toPrice;

    public SearchDTO() {
    }

    public SearchDTO(String name, String category, float fromPrice, float toPrice) {
        this.name = name;
        this.category = category;
        this.fromPrice = fromPrice;
        this.toPrice = toPrice;
    }

    public float getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(float fromPrice) {
        this.fromPrice = fromPrice;
    }

    public float getToPrice() {
        return toPrice;
    }

    public void setToPrice(float toPrice) {
        this.toPrice = toPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
