package com.example.demo.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class EhCacheConfig extends CachingConfigurerSupport {
	
	
	@Bean
	public CacheManager ehCacheManager() {
		CacheConfiguration allTeacherCache = new CacheConfiguration();
		allTeacherCache.setName("allTeacherCache");
		allTeacherCache.setMaxEntriesLocalHeap(1000);
		allTeacherCache.setTimeToLiveSeconds(30);
		
		CacheConfiguration teacherStudentCache = new CacheConfiguration();
		teacherStudentCache.setName("teacherStudentCache");
		teacherStudentCache.setMaxEntriesLocalHeap(1000);
		teacherStudentCache.setTimeToLiveSeconds(30);
		
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(teacherStudentCache);
		config.addCache(allTeacherCache);
		return CacheManager.newInstance(config);
	}
	
	
	@Bean
	@Override
	public org.springframework.cache.CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

}
