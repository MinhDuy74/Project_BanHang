package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;
import context.DBContext;

public class CartDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy giỏ hàng theo user
    public List<CartItem> getCartByUser(int userId) {
        List<CartItem> list = new ArrayList<>();
        String query = "SELECT * FROM Cart WHERE user_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CartItem(
                    rs.getInt("product_id"),
                    rs.getInt("quantity")
                ));
            }
        } catch(Exception e) { e.printStackTrace(); }
        return list;
    }

    // Thêm hoặc update sản phẩm trong giỏ
    public void addOrUpdateCartItem(int userId, int productId, int quantity) {
        String check = "SELECT quantity FROM Cart WHERE user_id=? AND product_id=?";
        String insert = "INSERT INTO Cart(user_id, product_id, quantity) VALUES (?, ?, ?)";
        String update = "UPDATE Cart SET quantity=? WHERE user_id=? AND product_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(check);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
            if(rs.next()) {
                int newQuantity = rs.getInt("quantity") + quantity;
                ps = conn.prepareStatement(update);
                ps.setInt(1, newQuantity);
                ps.setInt(2, userId);
                ps.setInt(3, productId);
                ps.executeUpdate();
            } else {
                ps = conn.prepareStatement(insert);
                ps.setInt(1, userId);
                ps.setInt(2, productId);
                ps.setInt(3, quantity);
                ps.executeUpdate();
            }
        } catch(Exception e) { e.printStackTrace(); }
    }

    // Xóa sản phẩm
    public void removeCartItem(int userId, int productId) {
        String sql = "DELETE FROM Cart WHERE user_id=? AND product_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
    }

    // Update số lượng
    public void updateQuantity(int userId, int productId, int quantity) {
        String sql = "UPDATE Cart SET quantity=? WHERE user_id=? AND product_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
    }

    // Xóa toàn bộ cart
    public void clearCart(int userId) {
        String sql = "DELETE FROM Cart WHERE user_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
    }
    public double getProductPrice(int productId) {
    String sql = "SELECT price FROM Product WHERE id=?";
    try (Connection conn = DBContext.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getDouble("price");
    } catch (Exception e) { e.printStackTrace(); }
    return 0;
}
}