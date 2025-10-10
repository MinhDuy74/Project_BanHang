package dao;

import context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Order;

public class OrderDAO {

    // Lấy giá sản phẩm (nếu muốn show tổng)
    public double getProductPrice(int productId) {
        String sql = "SELECT price FROM Product WHERE id=?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Order> getOrdersByUser(int userId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT o.order_id, o.order_date, o.status, o.fullname, o.phone, o.address, "
                + "SUM(od.price * od.quantity) AS total "
                + "FROM Orders o "
                + "LEFT JOIN OrderDetail od ON o.order_id = od.order_id "
                + "WHERE o.user_id = ? "
                + "GROUP BY o.order_id, o.order_date, o.status, o.fullname, o.phone, o.address "
                + "ORDER BY o.order_date DESC";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("order_id"));
                o.setOrderDate(rs.getTimestamp("order_date"));
                o.setStatus(rs.getString("status"));
                o.setFullname(rs.getString("fullname"));
                o.setPhone(rs.getString("phone"));
                o.setAddress(rs.getString("address"));
                o.setTotal(rs.getDouble("total"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int createOrder(int userId, String fullname, String phone, String address) {
        int orderId = -1;
        String sql = "INSERT INTO Orders(user_id, order_date, status, fullname, phone, address) VALUES (?, NOW(), 'pending', ?, ?, ?)";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.setString(2, fullname);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public void insertOrderDetail(int orderId, int productId, int quantity, double price) {
        String sql = "INSERT INTO OrderDetail(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Order getOrderById(int orderId) {
    String sql = "SELECT order_id, order_date, status, fullname, phone, address FROM Orders WHERE order_id = ?";
    try (Connection conn = DBContext.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Order o = new Order();
            o.setOrderId(rs.getInt("order_id"));
            o.setOrderDate(rs.getTimestamp("order_date"));
            o.setStatus(rs.getString("status"));
            o.setFullname(rs.getString("fullname"));
            o.setPhone(rs.getString("phone"));
            o.setAddress(rs.getString("address"));
            return o;
        }
    } catch (Exception e) { e.printStackTrace(); }
    return null;
}
}
