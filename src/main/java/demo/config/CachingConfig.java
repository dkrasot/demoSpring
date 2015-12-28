package demo.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

    //alternative to XML cache config
    @Bean(destroyMethod = "shutdown")
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName("myCache");
        cacheConfiguration.setMaxBytesLocalHeap("50M");
        cacheConfiguration.setTimeToLiveSeconds(1000L);
        //cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        //cacheConfiguration.setMaxEntriesLocalHeap(1000);

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(cacheConfiguration);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager cm) {
        //ehCache CacheManager injected to Spring ECCManager (which implements Spring' CacheManager)
        return new EhCacheCacheManager(cm);
    }


}
