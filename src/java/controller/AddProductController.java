package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.DAO;
import model.Category;

@WebServlet("/add-product")
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Hiển thị trang thêm sản phẩm
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listC", listC);
        // Forward tới trang form (đang dùng AddProduct2.jsp)
        request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
    }

    // Không xử lý POST tại đây (form đang POST tới /add). Giữ lại cho rõ ràng.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu ai đó POST nhầm vào /add-product thì chuyển tiếp về /add
//        response.sendRedirect(request.getContextPath() + "/add");
    }
}