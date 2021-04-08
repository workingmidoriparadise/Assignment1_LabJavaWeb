/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import khamdd.dtos.ProductDTO;
import khamdd.connections.Connections;
import khamdd.dtos.SearchDTO;
import khamdd.dtos.UpdateProductHistoryDTO;

/**
 *
 * @author KHAM
 */
public class ProductDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public int count(SearchDTO dto) throws Exception {
        int count = 0;
        try {
            String sql = "Select count(tbl_product.productID) as count"
                    + " From tbl_product inner join tbl_productCategory on tbl_product.productCategoryID = tbl_productCategory.productCategoryID"
                    + " Where status = 1 and quantity > 0 and tbl_productCategory.productCategoryName like ? and productName like ? and "
                    + "price >= ? and price <= ?";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + dto.getCategory() + "%");
            ps.setString(2, "%" + dto.getName() + "%");
            ps.setFloat(3, dto.getFromPrice());
            ps.setFloat(4, dto.getToPrice());
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public ArrayList<ProductDTO> getListCategory() throws Exception {
        ArrayList<ProductDTO> listCategory = null;
        try {
            String sql = "Select productCategoryName from tbl_productCategory";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            listCategory = new ArrayList<>();
            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setCategory(rs.getString("productCategoryName"));
                listCategory.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listCategory;
    }

    public ArrayList searchForUpdate(SearchDTO dto, int index) throws Exception {
        ArrayList<ProductDTO> listSearched = null;
        try {
            String sql = "Select tbl_product.productID, tbl_product.productName, tbl_product.price, tbl_product.image, tbl_product.quantity, tbl_product.description,"
                    + " tbl_product.status, tbl_productCategory.productCategoryName From tbl_product inner join tbl_productCategory"
                    + " on tbl_product.productCategoryID = tbl_productCategory.productCategoryID"
                    + " Where tbl_product.status = 1 and tbl_product.quantity > 0 and tbl_productCategory.productCategoryName like ? and tbl_product.productName like ? and"
                    + " tbl_product.price >= ? and tbl_product.price <= ? Order By createDate offset ? rows fetch next ? rows only";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + dto.getCategory() + "%");
            ps.setString(2, "%" + dto.getName() + "%");
            ps.setFloat(3, dto.getFromPrice());
            ps.setFloat(4, dto.getToPrice());
            ps.setInt(5, index);
            ps.setInt(6, 6);
            rs = ps.executeQuery();
            listSearched = new ArrayList<>();
            while (rs.next()) {
                ProductDTO proDTO = new ProductDTO(rs.getString("productID"), rs.getString("productName"), rs.getString("image"),
                        rs.getString("productCategoryName"), rs.getFloat("price"), rs.getInt("quantity"), rs.getInt("status"));
                listSearched.add(proDTO);
            }
        } finally {
            closeConnection();
        }
        return listSearched;
    }

    public void deleteProduct(String[] listProductID) throws Exception {
        try {
            String sql = "Update tbl_product set status = 0 where productID like ?";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            for (String s : listProductID) {
                ps.setString(1, s);
                ps.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    public boolean updateProduct(ProductDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tbl_product set productName = ?, price = ?, quantity = ?, image = ?, status = ? where productID = ?";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getProductName());
            ps.setFloat(2, dto.getPrice());
            ps.setInt(3, dto.getQuantity());
            ps.setString(4, dto.getImage());
            ps.setInt(5, dto.getStatus());
            ps.setString(6, dto.getProductID());
            check = ps.executeUpdate() == 1;

        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean recordUpdateProductHistory(UpdateProductHistoryDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "Insert into tbl_updateProductHistory(productID, updateDate,action) values(?,?,?)";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getProductID());
            ps.setTimestamp(2, dto.getUpdateDate());
            ps.setString(3, dto.getAction());
            ps.executeUpdate();

        } finally {
            closeConnection();
        }
        return check;
    }

    public String getCategoryIDByCategoryName(String categoryName) throws Exception {
        String categoryID = "";
        try {
            String sql = "Select productCategoryID from tbl_ProductCategory where productCategoryName = ?";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            if (rs.next()) {
                categoryID = rs.getString("productCategoryID");
            }
        } finally {
            closeConnection();
        }

        return categoryID;
    }

    public boolean insertProduct(ProductDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tbl_Product(productID, productName, price, description, createDate, productCategoryID, quantity, image, status) values"
                    + " (?,?,?,?,?,?,?,?,?)";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getProductID());
            ps.setString(2, dto.getProductName());
            ps.setFloat(3, dto.getPrice());
            ps.setString(4, dto.getDescription());
            ps.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
            ps.setString(6, dto.getCategory());
            ps.setInt(7, dto.getQuantity());
            ps.setString(8, dto.getImage());
            ps.setInt(9, 1);
            check = ps.executeUpdate() == 1;

        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkExistProductID(String productID) throws Exception {
        boolean check = false;

        try {
            String sql = "Select productName from tbl_product where productID like ?";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
