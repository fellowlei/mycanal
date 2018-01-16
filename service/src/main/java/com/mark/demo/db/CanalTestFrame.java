package com.mark.demo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2018/1/16.
 */
public class CanalTestFrame {
    public static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    public static final AtomicInteger idGen = new AtomicInteger(0);

    public void init() throws Exception {
        Connection conn = ConnectionThreadLocal.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("delete  from xdual");
        int row = pstmt.executeUpdate();
        if(row > 0){
            System.out.println("init success");
        }
    }

    public void insert() throws Exception {
        Connection conn = ConnectionThreadLocal.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into xdual(id) values(?)");
        int id = idGen.incrementAndGet();
        pstmt.setInt(1,id);
        int row = pstmt.executeUpdate();
        if(row > 0){
            System.out.println("insert success");
        }else{
            System.out.println("insert fail");
        }
    }

    public void delete() throws Exception {
        Connection conn = ConnectionThreadLocal.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("delete  from xdual where id = ?");
        int id = idGen.incrementAndGet();
        pstmt.setInt(1,id);
        int row = pstmt.executeUpdate();
        if(row > 0){
            System.out.println("delete success");
        }else {
            System.out.println("delete fail");
        }
    }

    public String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }

    public void update() throws Exception {
        Connection conn = ConnectionThreadLocal.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("update xdual set X = ? where id = 1");
        pstmt.setObject(1,getCurrentTime());
        int row = pstmt.executeUpdate();
        if(row > 0){
            System.out.println("update success");
        }else{
            System.out.println("update fail");
        }
    }

    public void query() throws Exception {
        Connection conn = ConnectionThreadLocal.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select * from xdual limit 20");
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("id") + ":" + rs.getLong("X"));
        }
    }

}
