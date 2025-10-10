package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DAO;
import model.Product;

/**
 * Servlet implementation class ListProductController
 */
@WebServlet("/listProduct")
public class ListProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        DAO dao = new DAO();
        int count = dao.getTotalProduct();
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }

        // Lấy danh sách sản phẩm cho trang hiện tại (giả sử DAO có hàm getPagingProduct)
        List<Product> list = dao.pagingProduct(index);

        // Truyền dữ liệu sang JSP
        request.setAttribute("listP", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index); // để active trang hiện tại

        request.getRequestDispatcher("ListProduct.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        response.sendRedirect("listProduct");
    }

}
