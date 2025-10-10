package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.DAO;
import model.Product;
import model.Cart;
import model.CartItem;

@WebServlet("/print")
public class ShowCartController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        List<Product> list = new ArrayList<>();
        DAO dao = new DAO();
        double total = 0;
        if (cart != null) {
            for (CartItem item : cart.getItems()) {
                Product product = dao.getProductByID(String.valueOf(item.getProductId()));
                if (product != null) {
                    product.setAmount(item.getQuantity());
                    list.add(product);
                    total += product.getAmount() * product.getPrice();
                }
            }
        }
        request.setAttribute("listP", list);
        request.setAttribute("total", total);
        request.setAttribute("vat", 0.1 * total);
        request.setAttribute("sum", 1.1 * total);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}