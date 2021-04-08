/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.connections;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author KHAM
 */
public class Connections {
    public static Connection getConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Assignment1_DangDuongKham","kham","kham");
        return conn;
    }
}
