package controller;

import dao.CartDAO;
import dao.DAO;
import model.CartItem;
import model.Product;
import model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        int userId = acc.getId();
        CartDAO cartDAO = new CartDAO();
        DAO dao = new DAO();

        String action = request.getParameter("action");
        int productId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;

        // Nếu action == null hoặc action="add" thì thêm sản phẩm
        if ("add".equals(action) || action == null) {
            if (productId != -1) {
                cartDAO.addOrUpdateCartItem(userId, productId, 1);
            }
        } else if ("sub".equals(action)) {
            List<CartItem> cart = cartDAO.getCartByUser(userId);
            for (CartItem item : cart) {
                if (item.getProductId() == productId) {
                    if (item.getQuantity() > 1) {
                        cartDAO.updateQuantity(userId, productId, item.getQuantity() - 1);
                    } else {
                        cartDAO.removeCartItem(userId, productId);
                    }
                    break;
                }
            }
        } else if ("remove".equals(action)) {
            cartDAO.removeCartItem(userId, productId);
        }

        // Lấy lại cart và hiển thị
        List<CartItem> cart = cartDAO.getCartByUser(userId);
        List<Product> listP = new ArrayList<>();
        double total = 0;
        for (CartItem item : cart) {
            Product p = dao.getProductByID(String.valueOf(item.getProductId()));
            if (p != null) {
                p.setAmount(item.getQuantity());
                listP.add(p);
                total += p.getPrice() * item.getQuantity();
            }
        }
        request.setAttribute("listP", listP);
        request.setAttribute("total", total);
        request.setAttribute("vat", 0.1 * total);
        request.setAttribute("sum", 1.1 * total);

        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }
}