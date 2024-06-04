package io.javalin.plugin.redis;

import io.javalin.config.JavalinConfig;
import io.javalin.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RedisPlugin extends Plugin<RedisConfig> {

    public RedisPlugin() {
        super(null, new RedisConfig());
    }

    public RedisPlugin(Consumer<RedisConfig> userConfig) {
        super(userConfig, new RedisConfig());
    }

    @Override
    public void onStart(@NotNull JavalinConfig app) {
        RedisTemplate redisTemplate = RedisTemplate.getInstance().setup(pluginConfig);
        app.events(event -> {
            event.serverStopped(() -> {
                redisTemplate.close();
                redisTemplate.destroy();
            });
            event.serverStartFailed(() -> {
                redisTemplate.close();
                redisTemplate.destroy();
            });
        });
    }

}
