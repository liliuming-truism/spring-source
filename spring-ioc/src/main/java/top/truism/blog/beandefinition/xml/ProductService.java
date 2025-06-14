package top.truism.blog.beandefinition.xml;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ProductService {

    private List<Object> serviceList;

    private Map<String, Object> configMap;
}
