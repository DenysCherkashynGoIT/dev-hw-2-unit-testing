package ua.store;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testCreateNewProductWithoutPromo_happyPath() {
        String expectedName = "Something";
        double expectedPrice = 16.8;
        Product product = new Product(expectedName, expectedPrice);
        assertEquals(expectedName, product.getName());
        assertEquals(expectedPrice, product.getPrice(), 0.0);
        assertEquals(0, product.getPromoPrice(), 0.0);
        assertEquals(0, product.getPromoCount(), 0.0);
    }

    @Test
    public void testCreateNewProductWithPromo_happyPath() {
        String expectedName = "SomethingPromo";
        double expectedPrice = 11.1;
        double expectedPromoPrice = 40.0;
        double expectedPromoCount = 4;
        Product product = new Product(expectedName, expectedPrice, expectedPromoPrice, expectedPromoCount);
        assertEquals(expectedName, product.getName());
        assertEquals(expectedPrice, product.getPrice(), 0.0);
        assertEquals(expectedPromoPrice, product.getPromoPrice(), 0.0);
        assertEquals(expectedPromoCount, product.getPromoCount(), 0.0);
    }

    @Test
    public void testCreateNewProductWithNullName_throwExeption() {
        String name = null;
        double price = 11.1;
        double promoPrice = 40.0;
        double promoCount = 4;
        assertThrows("Name of product cann't be null.", StoreException.class,
                () -> new Product(name, price, promoPrice, promoCount));
    }

    @Test
    public void testToString_getStringWithProductAllParameters() {
        String expectedString = String.format("%15s%15.2f%15.2f%15.2f", "Meat", 110.0, 0.0, 0.0);
        Product product = new Product("Meat", 110);
        assertEquals(expectedString, product.toString());
    }

    @Test
    public void compareProducts() {
        Product productX = new Product("Water", 10.0, 19.0, 2);
        Product productY = new Product("Milk", 10.0, 19.0, 2);
        Product productZ = new Product("Water", 12.0);
        assertEquals(productX.hashCode(), productZ.hashCode());
        assertNotEquals(productX.hashCode(), productY.hashCode());
        assertEquals(productX, productZ);
        assertNotEquals(productX, productY);
    }
}