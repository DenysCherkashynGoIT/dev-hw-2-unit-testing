package ua.store;

import java.util.Objects;

public class Product {
    private String name;
    private double price;
    private double promoPrice = 0;
    private double promoCount = 0;


    public Product(String name, double price, double promoPrice, double promoCount) {
        if(Objects.isNull(name)){
            throw new StoreException("Name of product cann't be null.");
        }
        this.name = name;
        setPrice(price);
        setPromoPrice(promoPrice);
        setPromoCount(promoCount);
    }

    public Product(String name, double price) {
        this(name, price, 0, 0);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(double promoPrice) {
        this.promoPrice = promoPrice;
    }

    public double getPromoCount() {
        return promoCount;
    }

    public void setPromoCount(double promoCount) {
        this.promoCount = promoCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("%15s%15.2f%15.2f%15.2f", name, price, promoPrice, promoCount);
    }
}
