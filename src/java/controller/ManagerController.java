package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DAO;
import model.Account;
import model.Category;
import model.Product;

/**
 * Servlet implementation class ManagerController
 */
@WebServlet("/manager")
public class ManagerController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");

        String indexPage = request.getParameter("index");
        //nếu trang kế tiếp bằng null thì quay lạy trang 1
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        int id = a.getId();
        DAO dao = new DAO();
// Lấy sản phẩm phân trang đúng seller
        List<Product> list = dao.pagingProduct(id, index);
        List<Category> listC = dao.getAllCategory();

// Đếm đúng tổng sản phẩm của seller
        int count = dao.getTotalProductBySeller(id);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }

        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);

        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
