package dao;

import context.DBContext;
import java.sql.*;

public class PaymentDAO {

    public void insertPayment(int orderId, String paymentMethod, double amount) {
        String sql = "INSERT INTO Payment(order_id, payment_method, amount, status) VALUES (?, ?, ?, 'pending')";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setString(2, paymentMethod);
            ps.setDouble(3, amount);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
