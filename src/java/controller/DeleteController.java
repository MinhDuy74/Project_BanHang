package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DAO;

@WebServlet("/delete")
public class DeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        if(pid == null || pid.isEmpty()){
            System.err.println("Product ID is missing.");
        }
        System.out.println("Deleting product with ID: " + pid); // Log để kiểm tra ID
        DAO dao = new DAO();
        dao.deleteProduct(pid);
        response.sendRedirect("manager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
