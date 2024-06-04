[![Chat at https://discord.gg/sgak4e5NKv](https://img.shields.io/badge/chat-on%20Discord-%234cb697)](https://discord.gg/sgak4e5NKv)
[![CI](https://github.com/tipsy/javalin/workflows/Test%20all%20JDKs%20on%20all%20OSes/badge.svg)](https://github.com/tipsy/javalin/actions)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maven](https://img.shields.io/maven-central/v/io.javalin/javalin.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22io.javalin%22%20AND%20a%3A%22javalin%22)

# About Javalin

* [:heart: Sponsor Javalin](https://github.com/sponsors/tipsy)
* The main project webpage is [javalin.io](https://javalin.io)
* Chat on Discord: https://discord.gg/sgak4e5NKv
* License summary: https://tldrlegal.com/license/apache-license-2.0-(apache-2.0)

## Javalin Redis

The plugin enables [Redis](https://redis.io/) operations in a few simple steps, all based on [Jedis](https://github.com/redis/jedis).

### Getting Started

Add the dependencies:

```
It's not ready yet.
```

Register the plugin:

```java
Javalin.create(app -> {
    app.registerPlugin(new RedisPlugin((redisConfig) -> {
        redisConfig.setHost("127.0.0.1");
        redisConfig.setPort(6379);
    }));
}).start(7070);
```

### Execute

Use `RedisTemplate.getInstance()` to get the instance to call Jedis functions.

Note that all Jedis functions need to be called via `execute()`.

```java
RedisTemplate.getInstance().execute((jedis) -> jedis.set("hello", "hello world"));

String executed = RedisTemplate.getInstance().execute((jedis) -> jedis.get("hello"));
```

All methods are thread-safe and JedisPool is also automatically destroyed based on Javalin's shutdown.
