import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 29.01.2016.
 */
public class Basket implements BasketInterface {

        List<String> product;
        List<Integer> quantity;

        Basket() {
            product = new ArrayList<>();
            quantity = new ArrayList<>();
         }

        public void addProduct(String product, int quantity) {
            int marker = 0;
            for(int i = 0; i < this.product.size(); i++) {
                if(this.product.get(i).equals(product)) {
                    updateProductQuantity(product, quantity);
                    marker = 1;
                }
            }
            if(marker == 0) {
                this.product.add(product);
                this.quantity.add(quantity);
            }
        }
        public void removeProduct(String product) {
            for(int i = 0; i < this.product.size(); i++) {
                if(this.product.get(i).equals(product)) {
                    this.product.remove(i);
                    this.quantity.remove(i);
                }
            }
        }
        public void updateProductQuantity(String product, int quantity){
            for(int i = 0; i < this.product.size(); i++) {
                if(this.product.get(i).equals(product)) {
                    this.product.set(i, product);
                    this.quantity.set(i, quantity);
                }
            }
        }
        public void clear(){
            this.product.clear();
            this.quantity.clear();
        }
        public List<String> getProducts(){
           return product;
        }
        public int getProductQuantity(String product){
            int productQuantity = 0;
            for(int i = 0; i < this.product.size(); i++) {
                if(this.product.get(i).equals(product)) {
                    productQuantity = this.quantity.get(i);
                }
            }
            return productQuantity;
        }
    }
