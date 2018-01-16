package com.mark.demo.db;

import java.util.concurrent.TimeUnit;

/**
 * Created by lulei on 2018/1/16.
 */
public class DBTestUtil extends CanalTestFrame{
    public static void demoInsert() throws Exception {
        DBTestUtil util = new DBTestUtil();
        util.init();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    util.insert();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },1,1000, TimeUnit.MILLISECONDS);
    }

    public static void demoUpdate() throws Exception {
        DBTestUtil util = new DBTestUtil();
        util.init();
        util.insert();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    util.update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },1,1000, TimeUnit.MILLISECONDS);
    }


    public static void demoDelete() throws Exception {
        DBTestUtil util = new DBTestUtil();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    util.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },1,1000, TimeUnit.MILLISECONDS);
    }

    public static void demoQuery() throws Exception {
        DBTestUtil util = new DBTestUtil();
        util.query();
    }

    public static void main(String[] args) throws Exception {
//      demoInsert();
        demoDelete();
//        demoUpdate();
    }
}
