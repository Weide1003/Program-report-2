package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {

    public static String URL = "jdbc:mysql://localhost:3306/db_demo?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";

    public static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static String USER = "root";

    public static String PWD = "root";


    public static Connection getConnection()  {


        Connection con = null;
        try {

            Class.forName(DRIVER);

            con = DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return con;
    }


    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {

        try {
            if (rs != null){
                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }
            if (con != null){
                con.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
