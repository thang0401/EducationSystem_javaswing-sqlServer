///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package poly.edu.vn.display;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// *
// * @author ASUS
// */
//public class test_connect {
//     private static  String dburl = "jdbc:sqlserver://localhost:1433; databaseName=Polypro;user=myuser;password=myuser;"
//                + "encrypt=true;trustServerCertificate=true";
//
//    // Câu lệnh dùng để nạp driver
//    static {
//        try {
//            Class.forName(driver);
//        } catch (ClassNotFoundException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//// xây dựng prepareStatement
//    public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
//        Connection connection = DriverManager.getConnection(dburl);
//        PreparedStatement pstmt = null;
//        if (sql.trim().startsWith("{")) {
//            pstmt = connection.prepareCall(sql);
//        } else {
//            pstmt = connection.prepareStatement(sql);
//        }
//        for (int i = 0; i < args.length; i++) {
//            pstmt.setObject(i + 1, args[i]);
//        }
//        return pstmt;
//    }
//// câu lệnh  SQL thao tác (INSERT, UPDATE, DELETE) 
//    public static void executeUpdate(String sql, Object... args) {
//        try {
//            PreparedStatement stmt = prepareStatement(sql, args);
//            try {
//                stmt.executeUpdate();
//            } finally {
//                stmt.getConnection().close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
////câu lệnh SQL truy vấn (SELECT) hoặc thủ tục lưu truy vấn dữ liệu
//    public static ResultSet executeQuery(String sql, Object... args) {
//        try {
//            PreparedStatement stmt = prepareStatement(sql, args);
//            return stmt.executeQuery();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static void main(String[] args) {
//        
//    }
//    
//}
