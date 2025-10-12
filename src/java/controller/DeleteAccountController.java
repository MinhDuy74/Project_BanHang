package controller;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Account;

@WebServlet("/deleteAccount")
public class DeleteAccountController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null || acc.getIsAdmin() != 1) {
            response.sendRedirect("home");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        DAO dao = new DAO();
        dao.deleteAccount(id);
        response.sendRedirect("managerAccount");
    }
}
