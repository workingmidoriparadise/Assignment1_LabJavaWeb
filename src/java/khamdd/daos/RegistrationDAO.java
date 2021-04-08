/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import khamdd.connections.Connections;
import khamdd.dtos.RegistrationDTO;

/**
 *
 * @author KHAM
 */
public class RegistrationDAO {
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Connection conn = null;
    
    private void closeConnection() throws Exception {
        if(rs != null) {
            rs.close();
        }
        if(ps != null) {
            ps.close();
        }
        if(conn != null) {
            conn.close();
        }
    }
    
    public RegistrationDTO login(String userID, String password) throws Exception {
        RegistrationDTO dto = null;
        try{
            String sql = "SELECT userID, role, fullname FROM tbl_user WHERE status = 1 and userID = ? and password = ?";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setString(2, password);
            rs = ps.executeQuery();
            dto = new RegistrationDTO();
            if(rs.next()){
                dto.setRole(rs.getString("role"));
                dto.setFullname(rs.getString("fullname"));
                dto.setUserID(rs.getString("userID"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    
    public boolean checkExistUserID(String id) throws Exception{
        boolean check = false;
        try{
            String sql = "Select userName from tbl_user where userID like ?";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean createGGAccount(String userID, String role, String email, String userName) throws Exception{
        boolean check = false;
        try {
            String sql = "Insert into tbl_user(userID, userName, role, email) values(?,?,?,?)";
            conn = Connections.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setString(2, userName);
            ps.setString(3, "member");
            ps.setString(4, email);
            check = ps.executeUpdate() == 1;
        } finally{
            closeConnection();
        }
        return check;
    }
}
