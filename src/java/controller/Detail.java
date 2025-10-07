package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DAO;
import model.Category;
import model.Product;
import model.ProductColor;

@WebServlet("/detail")
public class Detail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("pid");
        String colorIdParam = request.getParameter("colorId");

        if (id == null) {
            response.sendRedirect("home");
            return;
        }

        DAO dao = new DAO();
        Product p = dao.getProductByID(id);
        List<Category> listC = dao.getAllCategory();

        int productId = Integer.parseInt(id);

        // Load tất cả màu của sản phẩm
        List<ProductColor> listColors = dao.getColorsByProductId(productId);

        // Xác định colorId đang chọn (có thể null)
        Integer activeColorId = null;
        if (colorIdParam != null && !colorIdParam.isEmpty()) {
            try {
                activeColorId = Integer.parseInt(colorIdParam);
            } catch (NumberFormatException ignore) {}
        }

        // Lấy ảnh theo màu nếu có, không thì lấy tất cả ảnh của sản phẩm
        List<String> listImages = dao.getImagesByProductAndColor(productId, activeColorId);

        // Fallback: nếu chưa có ảnh phụ, dùng ảnh mặc định của product
        if ((listImages == null || listImages.isEmpty()) && p != null && p.getImage() != null) {
            listImages = java.util.Arrays.asList(p.getImage());
        }

        request.setAttribute("detail", p);
        request.setAttribute("listC", listC);
        request.setAttribute("listColors", listColors);
        request.setAttribute("activeColorId", activeColorId);
        request.setAttribute("listImages", listImages);

        // Lưu ý: file JSP đích tùy bạn đang dùng Detail.jsp hay DetailProduct.jsp
        request.getRequestDispatcher("Detail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}