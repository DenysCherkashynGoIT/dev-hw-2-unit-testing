package ua.store;

public class Application {
    static {
        //добавляем услоовные продукты на склад магазина
        WareHouse wareHouse = WareHouse.getInstance();
        Product apple = new Product("Apple", 1.25, 3.0, 3);
        Product watermelon = new Product("Watermelon", 4.25);
        Product banana = new Product("Banana", 1.0, 5.0, 6);
        Product lemon = new Product("Lemon", 0.75);
        wareHouse.addProduct("A", apple);
        wareHouse.addProduct("B", watermelon);
        wareHouse.addProduct("C", banana);
        wareHouse.addProduct("D", lemon);
    }

    public static void main(String[] args) {
        Store store = new Store();
        store.addToBasket("AaDBBBg");
        store.addToBasket("tBCSSDAf");
        store.showBasket();

        store.removeFromBasket("A");
        store.removeFromBasket("CB");
        store.showBasket();

        String requiredProducts = "ABCDABA";
        Double totalCost = store.calculateTotalCost(requiredProducts);
        System.out.printf("Total cost of products with code \'%s\' is %.2f of some currency",
                requiredProducts, totalCost);

        store.showBasket();
    }
}

