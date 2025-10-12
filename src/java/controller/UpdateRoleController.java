package controller;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Account;

@WebServlet("/updateRole")
public class UpdateRoleController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null || acc.getIsAdmin() != 1) {
            response.sendRedirect("home");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        String role = request.getParameter("role");
        int isAdmin = 0, isSell = 0;
        if ("admin".equals(role)) {
            isAdmin = 1;
        } else if ("seller".equals(role)) {
            isSell = 1;
        }
        DAO dao = new DAO();
        dao.updateRole(id, isSell, isAdmin);
        response.sendRedirect("managerAccount");
    }
}
