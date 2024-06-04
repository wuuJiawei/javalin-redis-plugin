package io.javalin.plugin.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class RedisTemplate {

    private static RedisTemplate instance;
    private static final ReentrantLock lock = new ReentrantLock();
    private JedisPool pool;
    private RedisTemplate() {}

    public static RedisTemplate getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new RedisTemplate();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public RedisTemplate setup(RedisConfig config) {
        this.pool = new JedisPool(config.getPoolConfig(), config.getHost(), config.getPort(), config.getTimeout(), config.getUser(), config.getPassword());
        return this;
    }

    public <T> T execute(Function<Jedis, T> function) {
        try (Jedis jedis = this.pool.getResource()) {
            return function.apply(jedis);
        }
    }

    public void close() {
        pool.close();
    }

    public void destroy() {
        pool.destroy();
    }

}
