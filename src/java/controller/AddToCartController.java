package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Cart;
import model.CartItem;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) { cart = new Cart(); }
        // Giả sử mặc định colorId=1, quantity=1, bạn có thể lấy từ request nếu muốn
        cart.addItem(new CartItem(productId, 1, 1));
        session.setAttribute("cart", cart);
        response.setContentType("application/json");
        response.getWriter().write("{\"success\":true}");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
