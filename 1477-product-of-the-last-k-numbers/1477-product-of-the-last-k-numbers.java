import java.util.*;

class ProductOfNumbers {
    private List<Integer> prefixProducts;
    
    public ProductOfNumbers() {
        prefixProducts = new ArrayList<>();
        prefixProducts.add(1);
    }
    
    public void add(int num) {
        if (num == 0) {
            prefixProducts.clear();
            prefixProducts.add(1);
        } else {
            int last = prefixProducts.get(prefixProducts.size() - 1);
            prefixProducts.add(last * num);
        }
    }
    
    public int getProduct(int k) {
        if (k >= prefixProducts.size()) {
            return 0;
        }
        int n = prefixProducts.size();
        return prefixProducts.get(n - 1) / prefixProducts.get(n - 1 - k);
    }
}
