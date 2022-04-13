import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Set;

public class RedisStorage {
    private static Jedis j = null;
    private final static String KEY = "USERS";

    public void init(){
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        j = new Jedis("127.0.0.1", 6379);
        j = jedisPool.getResource();
    }

    public void add(int numberInTheQueue, int userId) {
        j.zadd(KEY, numberInTheQueue, String.valueOf(userId));
    }

    public void delete(int numberInTheQueue) {
        j.zrem(KEY, String.valueOf(numberInTheQueue));
    }

    public String getFirstUserInQueue(){
        Set<String> range = j.zrange(KEY, 0, 0);
        return String.valueOf(range).substring(1, String.valueOf(range).length() - 1);
    }

    public Set<String> getAllUser(){
        return j.zrange(KEY, 0, 20);
    }
}