/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import khamdd.connections.Connections;
import khamdd.dtos.OrderDTO;
import khamdd.dtos.OrderDetailsDTO;
import khamdd.dtos.ShoppingHistoryDTO;

/**
 *
 * @author KHAM
 */
public class OrderDAO {
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
    
    public int insertOrder(OrderDTO dto) throws Exception{
        int orderID = 0;
        
        try{
            String sql = "Insert into tbl_Order(OrderDate, OrderTotalPrice, UserID) values (?,?,?)";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, dto.getOrderDate());
            ps.setFloat(2, dto.getTotal());
            ps.setString(3, dto.getUserID());
            ps.execute();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                orderID = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return orderID;
    }
    
    public boolean insertOrderDetail(OrderDetailsDTO dto) throws Exception{
        boolean check = false;
        try{
            String sql = "Insert into tbl_OrderDetails(orderProductQuantity, orderProductPrice, productID, orderID) values (?,?,?,?)";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dto.getQuantity());
            ps.setFloat(2, dto.getPrice());
            ps.setString(3, dto.getProductID());
            ps.setInt(4, dto.getOrderID());
            check = ps.executeUpdate() == 1;
            
        } finally {
            closeConnection();
        }
        
        return check;
    }
    
    
//    public ArraShoppingHistoryDTO getOrderByUserID(String userID) throws Exception{
//        ShoppingHistoryDTO dto = null;
//        try {
//            String sql = "Select orderID, orderDate, orderTotalPrice from tbl_Order where userID like ?";
//            conn = Connections.getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, userID);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                
//            }
//        } finally{
//            closeConnection();
//        }
//        return dto;
//    }
}
