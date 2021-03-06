package com.zl.springboot.security.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TokenCache {
    public static final String TOKEN_PREFIX="token_";
    private Logger logger = LoggerFactory.getLogger(getClass());
    //设置初始化容量1000,maxsize代表超过这个容量的时候guava的cache会使用lru算法
    //lru是最少使用算法来移除缓存,expireAfterAccess设置有效期为12小时
    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12,TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                //默认的数据加载实现，如果调用get取值的时候，如果key没有对应得值，就调用这个方法进行加载
                public String load(String s) throws Exception {
                    //为了防止空指针我们使用字符串的Null
                    return "null";
                }
            });
    public static void setKey(String key,String value){
        localCache.put(key,value);
    }
    public static String getKey(String key) {
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (ExecutionException e) {
            e.printStackTrace();
            logger.error("localCache get error", e);
        }
        return null;
    }
}
