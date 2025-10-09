package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.DAO;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
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
        String priceStr = request.getParameter("price");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String cateStr = request.getParameter("category");

        // Kiểm tra các trường bắt buộc
        if (name == null || name.trim().isEmpty()
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

            // Lưu file ảnh lên server và thêm vào DB
            Collection<Part> fileParts = request.getParts(); // Lấy tất cả file từ request
            String uploadPath = getServletContext().getRealPath("/images/products"); // Thư mục lưu file
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại

            String imageUrl = null;
            for (Part part : fileParts) {
                if (part.getName().equals("image") && part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String savePath = uploadPath + File.separator + fileName;
                    part.write(savePath); // Lưu file vào server
                    imageUrl = "images/products/" + fileName; // Đường dẫn lưu vào DB
                }
            }

            if (imageUrl == null) {
                request.setAttribute("error", "Vui lòng chọn ảnh.");
                request.setAttribute("listC", new dao.DAO().getAllCategory());
                request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
                return;
            }

            DAO dao = new DAO();
            boolean ok = dao.insertProduct(name, imageUrl, price, title, description, cateId, acc.getId());
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
