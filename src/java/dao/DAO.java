package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import context.DBContext;
import model.Account;
import model.Category;
import model.Payment;
import model.Product;
import java.sql.Statement;

public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lay du lieu tu Product len jsp
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }

        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM Category";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }

        } catch (Exception e) {

        }

        return list;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE cateID=?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }

        return list;
    }

    public Product getProductByID(String id) {
        String query = "SELECT * FROM Product WHERE id=?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6), 1);
            }

        } catch (Exception e) {

        }

        return null;
    }

    public List<Product> SearchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE name LIKE ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }

        return list;
    }

    // Gọi data lên trang login
    public Account login(String user, String pass) {
        String query = "SELECT * FROM Account WHERE user = ? AND pass = ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }

        } catch (Exception e) {

        }

        return null;
    }

    // Kiểm tra user đã tồn tại hay chưa khi đăng kí tk
    public Account checkAccountExits(String user) {
        String query = "SELECT * FROM Account WHERE user = ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }

        } catch (Exception e) {

        }

        return null;
    }

    // Thêm tài khoản khi đăng kí vào csdl
    public Account signUp(String user, String pass) {
        String query = "INSERT INTO Account(user, pass, isSell, isAdmin) VALUES (?, ?, 0, 0)";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();

        } catch (Exception e) {

        }

        return null;
    }

    // Upload sản phẩm lên manager.jsp bằng sell_id
    public List<Product> getProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE sell_ID = ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }

        return list;
    }

    // Thêm sản phẩm trong manager.jsp
    public boolean insertProduct(String name, String image, double price, String title,
            String description, int categoryId, int sellId) {
        String sql = "INSERT INTO Product(name, image, price, title, description, cateID, sell_ID) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setDouble(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setInt(6, categoryId);
            ps.setInt(7, sellId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi để debug
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ignore) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    // Xóa sản phẩm trong manager.jsp
    public void deleteProduct(String id) {
        String query = "DELETE FROM Product WHERE id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted product with ID: " + id);
            } else {
                System.out.println("No product found with ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error while deleting product with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ignore) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    // Sửa sản phẩm trong manager.jsp
    public void updateProduct(String name, String image, String price, String title,
            String description, String category, String pid) {
        String query = "UPDATE Product SET name=?, image=?, price=?, title=?, description=?, cateID=? WHERE id=?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, pid);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    // Load ra 3 sản phẩm mới nhất
    public List<Product> getTop3() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product ORDER BY id DESC LIMIT 3";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }

        return list;
    }

    // Load ra 3 sản phẩm tiếp theo (phân trang)
    public List<Product> getNextTop3(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product ORDER BY id LIMIT 3 OFFSET ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }

        return list;
    }

    // Đếm xem có bao nhiêu product để phân trang
    public int getTotalProduct() {
        String query = "SELECT COUNT(*) FROM Product";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

//     Phân trang cho ManagerProduct.jsp
    public List<Product> pagingProduct(int id, int index) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE sell_ID = ? ORDER BY id LIMIT 5 OFFSET ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }

        return list;
    }
   
    // Thêm màu mới cho sản phẩm, trả về color_id vừa tạo
    public int addColor(int productId, String colorName, String colorCode) {
        String query = "INSERT INTO product_colors (product_id, color_name, color_code) VALUES (?, ?, ?)";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, productId);
            ps.setString(2, colorName);
            ps.setString(3, colorCode);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

// Thêm ảnh cho màu sản phẩm
    public void addImage(int productId, int colorId, String imageUrl) {
        String query = "INSERT INTO product_images (product_id, color_id, image_url) VALUES (?, ?, ?)";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, colorId);
            ps.setString(3, imageUrl);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy toàn bộ màu của 1 sản phẩm
    public List<model.ProductColor> getColorsByProductId(int productId) {
        List<model.ProductColor> list = new ArrayList<>();
        String sql = "SELECT color_id, product_id, color_name, color_code FROM product_colors WHERE product_id = ? ORDER BY color_id ASC";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new model.ProductColor(
                        rs.getInt("color_id"),
                        rs.getInt("product_id"),
                        rs.getString("color_name"),
                        rs.getString("color_code")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

// Lấy danh sách ảnh theo product + color (nếu colorId null thì trả ảnh mọi màu của sản phẩm)
    public List<String> getImagesByProductAndColor(int productId, Integer colorId) {
        List<String> list = new ArrayList<>();
        String sqlAll = "SELECT image_url FROM product_images WHERE product_id = ? ORDER BY image_id ASC";
        String sqlByColor = "SELECT image_url FROM product_images WHERE product_id = ? AND color_id = ? ORDER BY image_id ASC";
        try {
            conn = DBContext.getConnection();
            if (colorId == null) {
                ps = conn.prepareStatement(sqlAll);
                ps.setInt(1, productId);
            } else {
                ps = conn.prepareStatement(sqlByColor);
                ps.setInt(1, productId);
                ps.setInt(2, colorId);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.pagingProduct(1, 0);
        for (Product o : list) {
            System.out.println(o);
        }
    }

    public void insertPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment (order_id, payment_method, amount, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getPaymentMethod());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getStatus());
            ps.executeUpdate();
        }
    }

    public Payment getPaymentByOrderId(int orderId) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE order_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setOrderId(rs.getInt("order_id"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentDate(rs.getTimestamp("payment_date"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setStatus(rs.getString("status"));
                return payment;
            }
        }
        return null;
    }

    public Integer getPendingOrderIdByUserId(int userId) throws SQLException {
        String sql = "SELECT order_id FROM Orders WHERE user_id = ? AND status = 'pending' LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("order_id");
            }
        }
        return null;
    }

    public int createOrder(int userId) throws SQLException {
        String sql = "INSERT INTO Orders (user_id, status) VALUES (?, 'pending')";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new SQLException("Failed to create order");
    }
}
