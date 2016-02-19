import java.util.Scanner;

/**
 * Created by Toshiba on 29.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        int marker;
        Scanner sc = new Scanner(System.in);
        int markerClose;
        Basket basket1 = new Basket();
        do {
            System.out.println("Input 1, if you want to add Product");
            System.out.println("Input 2, if you want to remove Product");
            System.out.println("Input 3, if you want to update ProductQuantity");
            System.out.println("Input 4, if you want to clear Backet");
            System.out.println("Input 5, if you want to get Products");
            System.out.println("Input 6, if you want to get ProductQuantity");
            System.out.println("Input 7, if you want to Close");
            System.out.print(": ");
            marker = sc.nextInt();
            markerClose = 1;
            switch(marker) {
                case 1: {
                    System.out.println("Input name product: ");
                    String productName = sc.next();
                    System.out.println("Input quantity: ");
                    int quantity = sc.nextInt();
                    basket1.addProduct(productName, quantity);
                    break;
                }
                case 2: {
                    System.out.println("Input name product: ");
                    String productName = sc.next();
                    basket1.removeProduct(productName);
                    break;
                }
                case 3: {
                    System.out.println("Input name product: ");
                    String productName = sc.next();
                    System.out.println("Input quantity: ");
                    int quantity = sc.nextInt();
                    basket1.updateProductQuantity(productName, quantity);
                    break;
                }
                case 4: {
                    basket1.clear();
                    break;
                }
                case 5: {
                    System.out.println(basket1.getProducts());
                    break;
                }
                case 6: {
                    System.out.println("Input name product: ");
                    String productName = sc.next();
                    System.out.println(basket1.getProductQuantity(productName));
                    break;
                }
                case 7: {
                    System.out.println("End");
                    markerClose = 0;
                    break;
                }
            }
        } while(markerClose == 1);
    }
}
