package onlineDrug;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c0655617
 */
public class dbConnection {
    
 public  Connection getConnection() {
         Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("JDBC Driver Not Found: " + ex.getMessage());
        }

        try {
            String jdbc = "jdbc:mysql://localhost/online";
            conn = DriverManager.getConnection(jdbc, "root", "");
        } catch (SQLException ex) {
            System.err.println("Failed to Connect: " + ex.getMessage());
        }
        return conn;
    }
    public int checkingDuplicateUserName(String username){
        int rowCount=0;
        try {
            Connection con=getConnection();
            String query="SELECT COUNT(*) count FROM user WHERE username=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                rowCount=rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowCount;
    }
    
    public int checkingDuplicateEmailId(String email){
        int rowCount=0;
        try {
            Connection con=getConnection();
            String query="SELECT COUNT(*) count FROM user WHERE email=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                rowCount=rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowCount;
    }
}
