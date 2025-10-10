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

    // Lấy các CartItem của một user
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
                    rs.getInt("user_id"),
                    rs.getInt("product_id"),
                    rs.getInt("color_id"),
                    rs.getInt("quantity")
                ));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm hoặc cập nhật sản phẩm trong giỏ hàng
    public void addOrUpdateCartItem(int userId, int productId, int colorId, int quantity) {
        String check = "SELECT quantity FROM Cart WHERE user_id=? AND product_id=? AND color_id=?";
        String insert = "INSERT INTO Cart(user_id, product_id, color_id, quantity) VALUES (?, ?, ?, ?)";
        String update = "UPDATE Cart SET quantity=? WHERE user_id=? AND product_id=? AND color_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(check);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, colorId);
            rs = ps.executeQuery();
            if(rs.next()) {
                int newQuantity = rs.getInt("quantity") + quantity;
                ps = conn.prepareStatement(update);
                ps.setInt(1, newQuantity);
                ps.setInt(2, userId);
                ps.setInt(3, productId);
                ps.setInt(4, colorId);
                ps.executeUpdate();
            } else {
                ps = conn.prepareStatement(insert);
                ps.setInt(1, userId);
                ps.setInt(2, productId);
                ps.setInt(3, colorId);
                ps.setInt(4, quantity);
                ps.executeUpdate();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa sản phẩm khỏi giỏ
    public void removeCartItem(int userId, int productId, int colorId) {
        String sql = "DELETE FROM Cart WHERE user_id=? AND product_id=? AND color_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, colorId);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật số lượng cụ thể
    public void updateQuantity(int userId, int productId, int colorId, int quantity) {
        String sql = "UPDATE Cart SET quantity=? WHERE user_id=? AND product_id=? AND color_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            ps.setInt(4, colorId);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa toàn bộ giỏ khi thanh toán
    public void clearCart(int userId) {
        String sql = "DELETE FROM Cart WHERE user_id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}