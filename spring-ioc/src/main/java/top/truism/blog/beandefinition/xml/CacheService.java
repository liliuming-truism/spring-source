package top.truism.blog.beandefinition.xml;

import lombok.Data;

@Data
public class CacheService {

    private int cacheSize;

    public void init() {
        System.out.println("CacheService initialized with cache size: " + cacheSize);
    }

    public void destroy() {
        System.out.println("CacheService destroyed");
    }
}
