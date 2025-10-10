package controller;

import java.io.IOException;
import java.util.List;

import dao.DAO;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/loadMore")
public class LoadMoreController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy offset và limit từ request (mặc định 0 và 6)
        int offset = 0;
        int limit = 6;
        try {
            if (request.getParameter("offset") != null) {
                offset = Integer.parseInt(request.getParameter("offset"));
            }
            if (request.getParameter("limit") != null) {
                limit = Integer.parseInt(request.getParameter("limit"));
            }
        } catch (Exception e) {
            // fallback nếu lỗi parseInt
            offset = 0;
            limit = 6;
        }

        DAO dao = new DAO();
        List<Product> list = dao.getProductsWithOffset(offset, limit);

        // Trả về HTML từng sản phẩm (giống đoạn trong forEach ở Home.jsp)
        response.setContentType("text/html;charset=UTF-8");
        for (Product o : list) {
            response.getWriter().println(
                "<div class='product col-12 col-md-6 col-lg-4'>" +
                  "<div class='card'>" +
                    "<img class='card-img-top' src='" + o.getImage() + "' alt='Card image cap'>" +
                    "<div class='card-body'>" +
                      "<h4 class='card-title show_txt'><a href='detail?pid=" + o.getId() + "' title='View Product'>" + o.getName() + "</a></h4>" +
                      "<p class='card-text show_txt'>" + o.getTitle() + "</p>" +
                      "<div class='row'>" +
                        "<div class='col'>" +
                          "<p class='btn btn-danger btn-block'>" +
                            "<span class='gia-tien'>" + String.format("%,.0f", o.getPrice()) + "</span> VNĐ" +
                          "</p>" +
                        "</div>" +
                        "<div class='col'>" +
                          "<button class='btn btn-success btn-block add-to-cart-btn' data-id='" + o.getId() + "'>Thêm vào giỏ hàng</button>" +
                        "</div>" +
                      "</div>" +
                    "</div>" +
                  "</div>" +
                "</div>"
            );
        }
    }
}