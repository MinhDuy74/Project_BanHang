package controller;

import dao.DAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@MultipartConfig
@WebServlet("/addColorImage")
public class AddColorImageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int productId = Integer.parseInt(req.getParameter("product_id"));
        String colorName = req.getParameter("color_name");
        String colorCode = req.getParameter("color_code");

        DAO dao = new DAO();
        // 1. Thêm màu mới cho sản phẩm, lấy ra color_id
        int colorId = dao.addColor(productId, colorName, colorCode);

        // 2. Lưu file ảnh lên server và thêm vào DB
        Collection<Part> fileParts = req.getParts();
        String uploadPath = req.getServletContext().getRealPath("/images/products");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        for (Part part : fileParts) {
            if (part.getName().equals("images") && part.getSize() > 0) {
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String savePath = uploadPath + File.separator + fileName;
                part.write(savePath);
                String imageUrl = "images/products/" + fileName; // Đường dẫn lưu vào DB
                dao.addImage(productId, colorId, imageUrl);
            }
        }
        resp.sendRedirect("manager");
    }
}