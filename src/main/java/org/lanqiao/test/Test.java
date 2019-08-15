package org.lanqiao.test;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("lq","xhl");
        jedis.lpush("lq2","value");
        jedis.lpush("lq2","string");
        jedis.close();
    }
}
