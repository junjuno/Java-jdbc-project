package com.project.shop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.shop.exception.ConnectionErrorException;

public class DBConnectionUtil {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER_NAME = "C##TEST";
    private static final String DB_USER_PASSWORD = "TEST";
    private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";


    public static Connection getConnection() throws ConnectionErrorException {

        Connection conn = null;
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
            return conn;
        } catch (ClassNotFoundException e) {
           
            e.printStackTrace();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        throw new ConnectionErrorException("연결에 실패했습니다.");
    }

 
    public static void save(PreparedStatement preparedStatement) throws Exception {
        if (preparedStatement == null) {
            throw new SQLException("쿼리가 없습니다.");
        }
        int result = preparedStatement.executeUpdate(); // DML 을 수행하면 DML 행 수만큼 리턴합니다.
        preparedStatement.close();
        if (result < 1) {
            throw new SQLException("쿼리 실행중 문제가 발생했습니다.");
        }
    }

   
    public static ResultSet search(PreparedStatement preparedStatement) throws Exception {
        if (preparedStatement == null) {
            throw new SQLException("쿼리가 없습니다.");
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    
    public static void disConnection(ResultSet rs, PreparedStatement st, Connection conn)
        throws ConnectionErrorException {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionErrorException("연결에 실패했습니다.");
        }
    }
}
