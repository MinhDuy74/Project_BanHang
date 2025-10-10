package controller;

import dao.OrderDAO;
import dao.CartDAO;
import dao.PaymentDAO;
import model.Account;
import model.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu muốn truyền tổng tiền, có thể lấy từ CartDAO
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        int userId = acc.getId();
        CartDAO cartDAO = new CartDAO();
        List<CartItem> cart = cartDAO.getCartByUser(userId);
        double total = 0;
        for (CartItem item : cart) {
            total += cartDAO.getProductPrice(item.getProductId()) * item.getQuantity();
        }
        request.setAttribute("amount", total);
        request.getRequestDispatcher("payment_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        int userId = acc.getId();

        CartDAO cartDAO = new CartDAO();
        OrderDAO orderDAO = new OrderDAO();
        PaymentDAO paymentDAO = new PaymentDAO();

        List<CartItem> cart = cartDAO.getCartByUser(userId);
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("error", "Giỏ hàng của bạn đang trống.");
            request.getRequestDispatcher("payment_form.jsp").forward(request, response);
            return;
        }

        // 1. Tạo đơn hàng mới
        int orderId = orderDAO.createOrder(userId, fullname, phone, address);

        // 2. Thêm chi tiết đơn hàng
        double total = 0;
        for (CartItem item : cart) {
            double price = cartDAO.getProductPrice(item.getProductId());
            int quantity = item.getQuantity();
            orderDAO.insertOrderDetail(orderId, item.getProductId(), quantity, price);
            total += price * quantity;
        }

        // 3. Lưu phương thức thanh toán
        paymentDAO.insertPayment(orderId, paymentMethod, total);

        // 4. Xóa giỏ hàng
        cartDAO.clearCart(userId);

        // 5. Chuyển sang trang thanh toán thành công
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("payment_success.jsp").forward(request, response);
    }
}