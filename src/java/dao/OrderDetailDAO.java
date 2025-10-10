package dao;

import context.DBContext;
import model.OrderDetail;
import java.sql.*;
import java.util.*;

public class OrderDetailDAO {
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT od.product_id, od.quantity, od.price, p.name as productName, p.image " +
                     "FROM OrderDetail od JOIN Product p ON od.product_id = p.id WHERE od.order_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setProductId(rs.getInt("product_id"));
                od.setProductName(rs.getString("productName"));
                od.setProductImage(rs.getString("image"));
                od.setQuantity(rs.getInt("quantity"));
                od.setPrice(rs.getDouble("price"));
                list.add(od);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}