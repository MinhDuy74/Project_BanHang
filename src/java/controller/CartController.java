package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CartController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String action = request.getParameter("action"); // "add" or "sub"

        Cookie[] arr = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + URLDecoder.decode(o.getValue(), StandardCharsets.UTF_8.name());
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }

        // Tách danh sách id (giả sử txt dạng "1, 2, 2, 3, 1")
        String[] ids;
        if(txt.isEmpty()) {
            ids = new String[0];
        } else {
            ids = txt.split(",\\s*");
        }

        StringBuilder newTxt = new StringBuilder();
        boolean found = false;

        if ("sub".equals(action)) {
            // Giảm số lượng: xóa 1 lần xuất hiện id
            int removed = 0;
            for (int i = 0; i < ids.length; i++) {
                if (ids[i].equals(id) && removed == 0) {
                    removed = 1;
                    found = true;
                    continue;
                }
                if (!ids[i].isEmpty()) {
                    if (newTxt.length() > 0) newTxt.append(", ");
                    newTxt.append(ids[i]);
                }
            }
        } else { // add hoặc null
            // Tăng số lượng: thêm id vào cuối
            if (!txt.isEmpty()) {
                newTxt.append(txt).append(", ");
            }
            newTxt.append(id);
        }

        String encodedTxt = URLEncoder.encode(newTxt.toString(), StandardCharsets.UTF_8.name());
        Cookie c = new Cookie("id", encodedTxt);
        c.setMaxAge(60 * 60 * 24);
        response.addCookie(c);

        // Sau khi xử lý xong, chuyển về trang giỏ hàng
        response.sendRedirect("print"); // hoặc "showcart" tùy controller render Cart.jsp
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}