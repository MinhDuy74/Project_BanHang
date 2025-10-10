package controller;

import dao.OrderDAO;
import model.Account;
import model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderlist")
public class OrderListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        int userId = acc.getId();

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.getOrdersByUser(userId);

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("orderlist.jsp").forward(request, response);
    }
}