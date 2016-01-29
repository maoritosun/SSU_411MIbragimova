/**
 * Created by Toshiba on 29.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        Basket basket1 = new Basket();
        basket1.addProduct("product1", 2);
        basket1.addProduct("product2", 4);
        basket1.addProduct("product3", 3);
        System.out.println(basket1.product + " " + basket1.quantity);
        basket1.clear();
        basket1.addProduct("product4", 11);
        basket1.addProduct("product5", 3);
        basket1.addProduct("product6", 26);
        basket1.addProduct("product6", 8);
        System.out.println("updateProductQuantity: ");
        basket1.updateProductQuantity("product3", 5);
        System.out.println(basket1.product + " " + basket1.quantity);
        System.out.println("removeProduct(4): ");
        basket1.removeProduct("product4");
        System.out.println(basket1.product + " " + basket1.quantity);
        System.out.println("getProducts: ");
        System.out.println(basket1.getProducts());
        System.out.println("getProductQuantity(6): ");
        System.out.println(basket1.getProductQuantity("product6"));
    }
}
