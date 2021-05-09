package ua.store;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class StoreTest {
    private Store store;

    @Before
    public void init() {
        Product productA = new Product("Potato", 9.25);
        Product productB = new Product("Cherry", 5, 40, 5);
        Product productC = new Product("Fish", 12, 33, 3);
        WareHouse wareHouse = WareHouse.getInstance();
        wareHouse.addProduct("A", productA);
        wareHouse.addProduct("B", productB);
        wareHouse.addProduct("C", productC);
        store = new Store();
    }

    @Test
    public void testAddProductToBasketWithoutPromotionPrice_happyPath() {
        Double expectedCost = 33.25;
        store.addToBasket("AC");
        store.addToBasket("C");
        Double actualCost = store.calculateTotalCost();
        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testAddProductToBasketWithPromotionPrice_happyPath() {
        Double expectedCost = 47.25;
        store.addToBasket("ACC");
        store.addToBasket("CB");
        Double actualCost = store.calculateTotalCost();
        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testAddProductToBasketWithNonExistentProduct_addOnlyExistent() {
        Double expectedCost = 26.25;
        store.addToBasket("AfgCc");
        store.addToBasket("Bb");
        Double actualCost = store.calculateTotalCost();
        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testRemoveProductFromBasket_happyPath() {
        Double expectedCost = 33.25;
        store.addToBasket("AACCB");
        store.removeFromBasket("BA");
        Double actualCost = store.calculateTotalCost();
        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testRemoveProductFromBasketWithNonExistentProduct_removeOnlyExistent() {
        Double expectedCost = 33.25;
        store.addToBasket("AACCB");
        store.removeFromBasket("aBcbA");
        Double actualCost = store.calculateTotalCost();
        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testCalculateTotalCostWithParameter_returnTotalSum() {
        Double expectedCost = 47.25;
        Double actualCost = store.calculateTotalCost("AaChjCsBsdC");
        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testShowBasket_printContentsToConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Double expectedCost = 47.25;
        String expectedOutput =
                "\n Code           Name          Price     PromoPrice     PromoCount     TotalCount      TotalCost\n" +
                        "--------------------------------------------------------------------------------------------------\n" +
                        "    A         Potato           9,25           0,00           0,00           1,00           9,25\n" +
                        "    B         Cherry           5,00          40,00           5,00           1,00           5,00\n" +
                        "    C           Fish          12,00          33,00           3,00           3,00          33,00\n" +
                        "--------------------------------------------------------------------------------------------------\n" +
                        "                                                                    TOTAL COST:\t          47,25\n" +
                        "\n";
        Double actualCost = store.calculateTotalCost("AaChjCsBsdC");
        store.showBasket();
        String actualOutput = out.toString(StandardCharsets.UTF_8).replaceAll("\r\n", "\n");
        assertEquals(expectedCost, actualCost);
        assertEquals(expectedOutput, actualOutput);
        out.reset();
    }
}
