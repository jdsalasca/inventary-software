package com.sena.inventarioback.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

import lombok.extern.slf4j.Slf4j;
@Configuration
@EnableCaching
@Slf4j
public class CacheConfig {


//  private CaffeineSpec caffeineSpecs() {
//      Map<String, Caffeine<Object, Object>> caffeineSpecs = new HashMap<>();
//
//      caffeineSpecs.put("allgenders", caffeineCacheBuilder().expireAfterWrite(10, TimeUnit.SECONDS));
//      caffeineSpecs.put("alldocumentstypes", caffeineCacheBuilder().expireAfterWrite(1, TimeUnit.MINUTES));
//      caffeineSpecs.put("allpeople", caffeineCacheBuilder().expireAfterWrite(5, TimeUnit.MINUTES));
//
//      return caffeineSpecs;
//  }

    @Bean
    CaffeineCacheManager cacheManager() {
        var cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder());
        //cacheManager.setCaffeineSpec(caffeineSpecs());

        cacheManager.setCacheNames(Arrays.asList("allgenders", "alldocumentstypes", "allpeople", "status", "general-resources"));
        log.info("ready to deploy cache with entries {}", cacheManager.getCacheNames());

        return cacheManager;
    }



    @Bean
    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()

                .expireAfterWrite(8, TimeUnit.HOURS)
                .recordStats();
    }

    @Bean
    CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
				  log.error("Cache '{}' PUT operation failed for key '{}' and value '{}'", cache.getName(), key, exception);
			}

			@Override
			public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
				  log.error("Cache '{}' PUT operation failed for key '{}' and value '{}'", cache.getName(), key, value, exception);
			}

			@Override
			public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
				 log.error("Cache '{}' EVICT operation failed for key '{}'", cache.getName(), key, exception);

			}

			@Override
			public void handleCacheClearError(RuntimeException exception, Cache cache) {
				log.error("Cache '{}' CLEAR operation failed", cache.getName(), exception);

			}
        };
    }
}
