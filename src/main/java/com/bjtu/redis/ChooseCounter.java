package com.bjtu.redis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChooseCounter {

    public static void choose(Counter counter) {
        switch (EmCounterType.valueOf(counter.getName())) {

            case showUserNum:
                showUser(counter);
                break;
            case incrUser:
                incrUser(counter);
                break;
            case decrUser:
                decrUser(counter);
                break;
            case showUserInFreq:
                showUserInFreq(counter);
                break;
            case showUserOutFreq:
                showUserOutFreq(counter);
                break;
        }
    }

    private static void decrUser(Counter decr) {
        String key = decr.getKey().get(0);
        String list=decr.getKey().get(1);
        RedisUtil redisUtil = new RedisUtil();
        try {
            redisUtil.decr(key, decr.getValue());
            System.out.println("The value of " + key + " decreased by " + decr.getValue() + " and became " + redisUtil.get(key));
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            String string=time.format(date);
            redisUtil.lpush(list,string);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            redisUtil.ruturnJedisResource();
        }
    }

    private static void showUser(Counter c) {
        String key = c.getKey().get(0);
        RedisUtil redisUtil = new RedisUtil();
        try {
            System.out.println("The value of " + key + " is " + redisUtil.get(key));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            redisUtil.ruturnJedisResource();
        }

    }

    private static void incrUser(Counter incr) {
        String key = incr.getKey().get(0);
        String list=incr.getKey().get(1);
        RedisUtil redisUtil = new RedisUtil();
        try {
            redisUtil.incr(key, incr.getValue());
            System.out.println("The value of " + key + " increased by " + incr.getValue() + " and became " + redisUtil.get(key));
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            String string=time.format(date);
            redisUtil.lpush(list,string);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            redisUtil.ruturnJedisResource();
        }
    }

    public static void showUserInFreq(Counter counter){
        String keyField=counter.getKey().get(0);
        RedisUtil redisUtil=new RedisUtil();
        try{
            String date=counter.getFREQ();
            String startTime=date.substring(0,12);
            String endTime=date.substring(13,25);
            for (int i = 0; i < redisUtil.llen(keyField); i++) {
                String t=redisUtil.lindex(keyField,i);
                if(t.compareTo(startTime)>=0 && t.compareTo(endTime)<=0){
                    //可以输出此用户元素
                    System.out.println("有用户在"+t+"时刻进入");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisUtil.ruturnJedisResource();
        }
    }

    public static void showUserOutFreq(Counter counter){
        String keyField=counter.getKey().get(0);
        RedisUtil redisUtil = new RedisUtil();
        try{
            String date=counter.getFREQ();
            String startTime=date.substring(0,12);
            String endTime=date.substring(13,25);
            for (int i = 0; i < redisUtil.llen(keyField); i++) {
//            System.out.println(jedis.lindex("UserOutList",i));
                if(redisUtil.lindex(keyField,i).compareTo(startTime)>=0 && redisUtil.lindex(keyField,i).compareTo(endTime)<=0){
                    //可以输出此用户元素
                    System.out.println("有用户在"+redisUtil.lindex(keyField,i)+"时刻退出");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisUtil.ruturnJedisResource();
        }
    }
}
