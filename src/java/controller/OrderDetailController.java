package controller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import model.Order;
import model.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderdetail")
public class OrderDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr == null) {
            response.sendRedirect("orderlist");
            return;
        }
        int orderId = Integer.parseInt(orderIdStr);

        OrderDAO orderDAO = new OrderDAO();
        OrderDetailDAO detailDAO = new OrderDetailDAO();

        // Lấy thông tin đơn hàng (họ tên, sđt, địa chỉ, trạng thái...)
        Order order = orderDAO.getOrderById(orderId);

        // Lấy danh sách sản phẩm và chi tiết
        List<OrderDetail> detailList = detailDAO.getOrderDetailsByOrderId(orderId);

        request.setAttribute("order", order);
        request.setAttribute("detailList", detailList);
        request.getRequestDispatcher("orderdetail.jsp").forward(request, response);
    }
}