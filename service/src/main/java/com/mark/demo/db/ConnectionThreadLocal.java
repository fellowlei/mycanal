package com.mark.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lulei on 2018/1/16.
 */
public class ConnectionThreadLocal {
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

    public static Connection getConnection() throws Exception {
        Connection conn = connectionThreadLocal.get();
        if(conn == null || conn.isClosed()){
            conn = newConnection();
            connectionThreadLocal.set(conn);
        }
        return conn;
    }

    private static Connection newConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1");
        System.out.println("gen new connection");
        return conn;
    }
}
