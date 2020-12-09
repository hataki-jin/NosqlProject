package com.bjtu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisUtil {
    private static JedisPool jedisPool;
    private static Jedis jedis;
    private static final String ip = "localhost";
    private static final Integer port = 6379;

    public RedisUtil() {
        jedisPool = JedisInstance.getInstance();
        jedis = jedisPool.getResource();
    }

    public void returnJedisResource(){
        jedis.close();
    }

    public void incr(String key, int num) {
        for (int i = 0; i < num; ++i) {
            jedis.incr(key);
        }
    }

    public void decr(String key, int num) {
        for (int i = 0; i < num; ++i) {
            jedis.decr(key);
        }
    }

    //为string添加元素
    public void set(String key, String value) {
        jedis.set(key, value);
    }

    //获取string
    public String get(String key) {
        return jedis.get(key);
    }


    //追加string
    public void append(String key, String value) {
        jedis.append(key, value);
    }

    //添加set
    public void sadd(String key, Set<String> value) {
        for (String str : value) {
            jedis.sadd(key, str);
        }
    }

    //set删除指定元素
    public void srem(String key, Set<String> value) {
        for (String str : value) {
            jedis.srem(key, str);
        }
    }

    //获取key对应的value总数
    public Long scard(String key) {
        return jedis.scard(key);
    }

    //获取key对应的所有value
    public Set<String> smembers(String key) {
        return jedis.smembers(key);
    }

    //判断set是否存在
    public boolean sismember(String key, String value) {
        return jedis.sismember(key, value);
    }

    //随机获取数据
    public String srandmember(String key) {
        return jedis.srandmember(key);
    }


    //向list添加元素
    public void lpush(String key, String list) {
        jedis.lpush(key, list);
    }

    //获取list
    public List<String> lrange(String key, Integer start, Integer end)
            throws Exception {
        return jedis.lrange(key, start, end);
    }

    //获取list的个数
    public Long llen(String key) {

        return jedis.llen(key);
    }

    //获取list在索引处的值
    public String lindex(String key, int index) {

        return jedis.lindex(key, index);
    }

    //删除任意类型的key
    public void del(String key) {

        jedis.del(key);
    }

    //设置map
    public void hmset(String key, Map<String, String> map) {
        jedis.hmset(key, map);
    }

    //获取map的key的个数
    public Long hlen(String key) {
        return jedis.hlen(key);
    }

    //获取map中所有key
    public Set<String> hkeys(String key) {

        return jedis.hkeys(key);
    }

    //获取map中所有value
    public List<String> hvals(String key) {
        return jedis.hvals(key);
    }

    //获取map中的指定key的value
    public List<String> hmget(String key, String... params)
            throws Exception {
        if (null == params || params.length == 0) {
            throw new RuntimeException(this.getClass().getSimpleName() + "::"
                    + new Exception().getStackTrace()[0].getMethodName() + "参数不能为空");
        }
        return jedis.hmget(key, params);
    }

    //获取map所有的key和value
    public Map<String, String> hgetAll(String key)
            throws Exception {
        return jedis.hgetAll(key);
    }

    //删除指定key的map
    public void hdel(String key, String... params) {
        if (null == params || params.length == 0) {
            throw new RuntimeException(this.getClass().getSimpleName() + "::"
                    + new Exception().getStackTrace()[0].getMethodName() + "参数不能为空");
        }
        jedis.hdel(key, params);
    }
}
