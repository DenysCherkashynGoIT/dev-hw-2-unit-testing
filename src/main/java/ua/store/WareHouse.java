package ua.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WareHouse {
    private static final WareHouse instance = new WareHouse();
    private Map<String, Product> products = new HashMap<>();

    private WareHouse() {
    }

    public static WareHouse getInstance() {
        return instance;
    }

    public void addProduct(String productCode, Product product) {
        if(productCode.length() != 1){
            throw new StoreException("Product code must be equals 1 symbol length.");
        }
        if(Objects.isNull(product)){
            throw new StoreException("Product cann't be null.");
        }
        products.put(productCode, product);
    }

    public Product getProduct(String productCode) {
        return products.get(productCode);
    }

    public boolean hasProduct(String productCode) {
        return products.containsKey(productCode);
    }
}
