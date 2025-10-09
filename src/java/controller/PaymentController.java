package controller;

import dao.DAO;
import model.Payment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hiển thị trang thanh toán, truyền orderId và amount (có thể lấy từ session hoặc request)
        String orderId = request.getParameter("orderId");
        String amount = request.getParameter("amount");
        request.setAttribute("orderId", orderId);
        request.setAttribute("amount", amount);
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr == null || orderIdStr.isEmpty()) {
            request.setAttribute("error", "Thiếu mã đơn hàng!");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
            return;
        }
        int orderId = Integer.parseInt(orderIdStr);
        String paymentMethod = request.getParameter("paymentMethod");
        double amount = Double.parseDouble(request.getParameter("amount"));

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setStatus("completed");

        DAO dao = new DAO();
        try {
            dao.insertPayment(payment);
            request.setAttribute("message", "Thanh toán thành công!");
            request.getRequestDispatcher("payment_success.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Thanh toán thất bại!");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }

    }
}
