package ua.store;

import java.util.HashMap;
import java.util.Map;

public class WareHouse {
    private static final WareHouse instance = new WareHouse();
    private Map<String, Product> products = new HashMap<>();

    private WareHouse() { }

    public static WareHouse getInstance(){
        return instance;
    }

    public void addProduct(String productCode, Product product) {
        products.put(productCode, product);
    }

    public Product getProduct(String productCode) {
        return products.get(productCode);
    }

    public boolean hasProduct(String productCode) {
        return products.containsKey(productCode);
    }
}
