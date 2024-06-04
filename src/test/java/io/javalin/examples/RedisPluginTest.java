package io.javalin.examples;

import io.javalin.Javalin;
import io.javalin.plugin.redis.RedisPlugin;
import io.javalin.plugin.redis.RedisTemplate;

public class RedisPluginTest {

    public static void main(String[] args) {
        Javalin.create(app -> {
            app.registerPlugin(new RedisPlugin((redisConfig) -> {
                redisConfig.setHost("127.0.0.1");
                redisConfig.setPort(6379);
            }));
            app.router.mount(router -> {
                router.get("/", ctx -> {
                    RedisTemplate.getInstance().execute((jedis) -> jedis.set("hello", "hello world"));
                    String executed = RedisTemplate.getInstance().execute((jedis) -> jedis.get("hello"));
                    ctx.result(executed);
                });
            });
        }).start(7070);
    }

}
