import java.util.List;

/**
 * Created by Toshiba on 29.01.2016.
 */
public interface BasketInterface {
    void addProduct(String product, int quantity);
    void removeProduct(String product);
    void updateProductQuantity(String product, int quantity);
    void clear();
    List<String> getProducts();
    int getProductQuantity(String product);
}

