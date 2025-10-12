package controller;

import dao.DAO;
import model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/managerAccount")
public class ManagerAccountController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra quyền admin
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null || acc.getIsAdmin() != 1) {
            response.sendRedirect("home");
            return;
        }

        DAO dao = new DAO();
        List<Account> list = dao.getAllAccount();
        request.setAttribute("listA", list);
        request.getRequestDispatcher("ManagerAccount.jsp").forward(request, response);
    }
}
