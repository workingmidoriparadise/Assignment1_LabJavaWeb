/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.dtos;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author KHAM
 */
public class ProductDTO implements Serializable{
    private String productID, productName, image, description, category;
    private Date createDate;
    private float price;
    private int quantity;
    private int status;
    
    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, String image, String description, String category, float price) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public ProductDTO(String productName, String image, String description, float price) {
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public ProductDTO(String productID, String productName, String image, String category, float price, int quantity, int status) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public ProductDTO(String productID, String productName, String image, String description, String category, float price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
