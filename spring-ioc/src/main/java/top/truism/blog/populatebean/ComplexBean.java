package top.truism.blog.populatebean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComplexBean {

    private String extraProperty;

    // 字段注入
    @Autowired
    private SimpleService simpleService;

    // setter注入
    private DatabaseService databaseService;

    // @Resource注入
    @Resource(name = "cacheService")
    private CacheService cacheService;

    // @Value注入
    @Value("${app.name:DefaultApp}")
    private String appName;

    // 可选依赖
    @Autowired(required = false)
    private Optional<MetricsService> metricsService;

    // 集合注入
    @Autowired
    private List<Plugin> plugins;

    // 构造器（已在实例化阶段完成）
    public ComplexBean() {
        System.out.println("ComplexBean构造器执行");
    }

    // setter方法（用于自动装配）
    @Autowired
    public void setDatabaseService(DatabaseService databaseService) {
        System.out.println("Setter注入: " + databaseService);
        this.databaseService = databaseService;
    }

    public void setExtraProperty(String extraProperty) {
        this.extraProperty = extraProperty;
    }

    @PostConstruct
    public void init() {
        System.out.println("=== 属性填充完成后的状态 ===");
        System.out.println("extraProperty: " + extraProperty);
        System.out.println("simpleService: " + simpleService);
        System.out.println("databaseService: " + databaseService);
        System.out.println("cacheService: " + cacheService);
        System.out.println("appName: " + appName);
        System.out.println("metricsService: " + metricsService);
        System.out.println("plugins size: " + (plugins != null ? plugins.size() : 0));
    }

    @Component
    public static class SimpleService {
        public SimpleService() {
            System.out.println("SimpleService创建");
        }
    }

    @Component
    public static class DatabaseService {
        public DatabaseService() {
            System.out.println("DatabaseService创建");
        }
    }

    @Component("cacheService")
    public static class CacheService {
        public CacheService() {
            System.out.println("CacheService创建");
        }
    }


    public static class Plugin {
        private String name;

        public Plugin(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Plugin{" + name + "}";
        }
    }

    public static class MetricsService {

    }
}


