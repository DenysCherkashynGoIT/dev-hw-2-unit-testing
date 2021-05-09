package ua.store;

import org.junit.Test;

import static org.junit.Assert.*;

public class WareHouseTest {
    WareHouse wareHouse = WareHouse.getInstance();

    @Test
    public void testAddProduct_happyPath() {
        Product expectedProduct = new Product("Oil", 25);
        wareHouse.addProduct("T", expectedProduct);
        Product actualProduct = wareHouse.getProduct("T");
        assertTrue(wareHouse.hasProduct("T"));
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void testAddProductWithWrongCode_returnException() {
        Product product = new Product("Oil", 25);
        assertThrows("Product code must be equals 1 symbol length.", StoreException.class,
                () -> wareHouse.addProduct("Code", product));
        assertFalse(wareHouse.hasProduct("Code"));
    }

    @Test
    public void testAddProductWithNullProduct_returnException() {
        Product product = null;
        assertThrows("Product cann't be null.", StoreException.class,
                () -> wareHouse.addProduct("Y", product));
        assertFalse(wareHouse.hasProduct("Y"));
    }
}
