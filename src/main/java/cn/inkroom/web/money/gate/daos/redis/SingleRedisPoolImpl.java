package cn.inkroom.web.money.gate.daos.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

/**
 * 单机redis连接池
 *
 */

public class SingleRedisPoolImpl implements RedisPool{
    @Autowired
    private JedisPool pool;

    public String set(String key, String value) {
        return pool.getResource().set(key, value);
    }

    public String set(String key, int seconds, String value) {
        return pool.getResource().setex(key, seconds, value);
    }

    public String get(String key) {
        return pool.getResource().get(key);
    }

    public Boolean exists(String key) {
        return pool.getResource().exists(key);
    }

    public Long expire(String key, int seconds) {
        return pool.getResource().expire(key,seconds);
    }

    public Long ttl(String key) {
        return pool.getResource().ttl(key);
    }

    public Long incr(String key) {
        return pool.getResource().incr(key);
    }

    public Long hset(String key, String field, String value) {
        return pool.getResource().hset(key, field, value);
    }

    public String hget(String key, String field) {
        return pool.getResource().hget(key, field);
    }

    public Long hdel(String key, String... field) {
        return pool.getResource().hdel(key, field);
    }

    public Boolean del(String key) {
        return null;
    }

    public void close() {
        if (pool.getResource().isConnected()){
            pool.getResource().close();
        }
    }
}
