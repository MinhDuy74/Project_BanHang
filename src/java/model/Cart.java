package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        // Nếu sản phẩm đã có thì tăng số lượng, chưa có thì thêm mới
        for (CartItem ci : items) {
            if (ci.getProductId() == item.getProductId()) {
                ci.setQuantity(ci.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(int productId) {
        items.removeIf(ci -> ci.getProductId() == productId);
    }

    public void updateQuantity(int productId, int quantity) {
        for (CartItem ci : items) {
            if (ci.getProductId() == productId) {
                ci.setQuantity(quantity);
                return;
            }
        }
    }

    public List<Integer> getProductIds() {
        List<Integer> ids = new ArrayList<>();
        for (CartItem item : items) {
            ids.add(item.getProductId());
        }
        return ids;
    }
}