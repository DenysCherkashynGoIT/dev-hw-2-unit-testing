package ua.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Store {
    private Map<String, Double> basket;
    private WareHouse wareHouse = WareHouse.getInstance();

    public Store() {
        basket = new HashMap<>();
    }

    public void addToBasket(String products) {
        products.chars().mapToObj(i -> String.valueOf((char) i))
                .filter(code -> wareHouse.hasProduct(code))
                .forEach(code -> basket.put(code, incCount(code)));
    }

    public void removeFromBasket(String products) {
        products.chars().mapToObj(i -> String.valueOf((char) i))
                .filter(code -> wareHouse.hasProduct(code))
                .forEach(code -> basket.put(code, decCount(code)));
    }

    public void clearBasket() {
        this.basket.clear();
    }

    public double calculateTotalCost(String products) {
        clearBasket();
        addToBasket(products);
        return calculateTotalCost();
    }

    public double calculateTotalCost() {
        return basket.entrySet().stream()
                .mapToDouble(
                        entry -> calculateProductCost(entry.getKey(), entry.getValue())
                ).sum();
    }

    public void showBasket() {
        StringBuilder output = new StringBuilder();
        String title = String.format("\n%5s%15s%15s%15s%15s%15s%15s\n",
                "Code", "Name", "Price", "PromoPrice", "PromoCount", "TotalCount", "TotalCost");
        String separator = "--------------------------------------------------------------------------------------------------\n";
        output.append(title).append(separator);
        basket.entrySet().forEach(product -> {
                    String row = createProductTableRow(product);
                    output.append(row);
                });
        String total = String.format("%80s%15.2f\n", "TOTAL COST:\t", calculateTotalCost());
        output.append(separator).append(total);
        System.out.println(output);
    }

    private Double incCount(String productCode) {
        Double count = Optional.ofNullable(basket.get(productCode)).orElse(0.0);
        return ++count;
    }

    private Double decCount(String productCode) {
        Double count = Optional.ofNullable(basket.get(productCode)).orElse(0.0);
        return (count > 0) ? --count : 0;
    }

    private double calculateProductCost(String productCode, double count) {
        double result;
        Product product = wareHouse.getProduct(productCode);
        double price = product.getPrice();
        double promoPrice = product.getPromoPrice();
        double promoCount = product.getPromoCount();
        if (promoCount > 0) {
            result = (int) (count / promoCount) * promoPrice + (count % promoCount) * price;
        } else {
            result = count * price;
        }
        return result;
    }

    private String createProductTableRow(Map.Entry<String, Double> product) {
        String code = product.getKey();
        Double count = product.getValue();
        String row = String.format("%5s%s%15.2f%15.2f\n",
                code, wareHouse.getProduct(code), count, calculateProductCost(code, count));
        return row;
    }
}
