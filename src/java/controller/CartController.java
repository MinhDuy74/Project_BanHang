package controller;

import dao.CartDAO;
import dao.DAO;
import model.CartItem;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CartDAO cartDAO = new CartDAO();
        DAO dao = new DAO();

        String action = request.getParameter("action");
        int productId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
        int colorId = request.getParameter("color") != null ? Integer.parseInt(request.getParameter("color")) : 1;

        if ("add".equals(action)) {
            cartDAO.addOrUpdateCartItem(userId, productId, colorId, 1);
        } else if ("sub".equals(action)) {
            List<CartItem> cart = cartDAO.getCartByUser(userId);
            for (CartItem item : cart) {
                if (item.getProductId() == productId && item.getColorId() == colorId) {
                    if (item.getQuantity() > 1) {
                        cartDAO.updateQuantity(userId, productId, colorId, item.getQuantity() - 1);
                    } else {
                        cartDAO.removeCartItem(userId, productId, colorId);
                    }
                    break;
                }
            }
        } else if ("remove".equals(action)) {
            cartDAO.removeCartItem(userId, productId, colorId);
        }

        // Lấy lại cart và show lên view
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