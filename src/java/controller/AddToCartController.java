package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Account;
import dao.CartDAO;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            // Nếu chưa đăng nhập, có thể trả về lỗi hoặc redirect login
            response.getWriter().write("{\"success\": false, \"message\": \"not_logged_in\"}");
            return;
        }
        int userId = acc.getId();

        int productId = Integer.parseInt(request.getParameter("id"));
        int quantity = 1;
        if (request.getParameter("quantity") != null) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }

        CartDAO cartDAO = new CartDAO();
        cartDAO.addOrUpdateCartItem(userId, productId, quantity);

        response.setContentType("application/json");
        response.getWriter().write("{\"success\":true}");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}