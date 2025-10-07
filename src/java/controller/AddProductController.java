package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.DAO;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.File;
import java.nio.file.Paths;
import model.Account;

@MultipartConfig
@WebServlet("/add-product")
public class AddProductController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        model.Account acc = (session != null) ? (model.Account) session.getAttribute("acc") : null;
        if (acc == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String priceStr = request.getParameter("price");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String cateStr = request.getParameter("category");

// Kiểm tra các trường bắt buộc
        if (name == null || name.trim().isEmpty()
                || image == null || image.trim().isEmpty()
                || priceStr == null || priceStr.trim().isEmpty()
                || title == null || title.trim().isEmpty()
                || description == null || description.trim().isEmpty()
                || cateStr == null || cateStr.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin sản phẩm.");
            request.setAttribute("listC", new dao.DAO().getAllCategory());
            request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int cateId = Integer.parseInt(cateStr);
            dao.DAO dao = new dao.DAO();
            boolean ok = dao.insertProduct(name, image, price, title, description, cateId, acc.getId());
            if (ok) {
                response.sendRedirect(request.getContextPath() + "/manager");
            } else {
                request.setAttribute("error", "Không thể thêm sản phẩm. Vui lòng thử lại.");
                request.setAttribute("listC", dao.getAllCategory());
                request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Giá hoặc danh mục không hợp lệ.");
            request.setAttribute("listC", new dao.DAO().getAllCategory());
            request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
        }
    }

    // Nếu form lỡ dùng GET thì chuyển về POST cho đúng (hoặc xử lý tương tự)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
