package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import dao.DAO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 20 * 1024 * 1024) // 20MB
@WebServlet("/edit")
public class EditController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditController() {
        super();
    }

    // Chỉ dùng LoadController để load dữ liệu và forward tới Edit.jsp
    // EditController.doPost sẽ xử lý update
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String pid = request.getParameter("id");
        String pname = request.getParameter("name");
        String price = request.getParameter("price");
        String ptitle = request.getParameter("title");
        String pdescription = request.getParameter("description");
        String pcategory = request.getParameter("category");
        String currentImage = request.getParameter("currentImage");

        // Xử lý file upload
        Part filePart = request.getPart("image");
        String imagePath = currentImage; // mặc định giữ ảnh cũ
        if (filePart != null && filePart.getSize() > 0) {
            String submittedFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            if (submittedFileName != null && !submittedFileName.isEmpty()) {
                // Lưu file vào webapp/img (có thể tuỳ chỉnh)
                String uploadDir = getServletContext().getRealPath("/img/");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String savedPath = uploadDir + File.separator + submittedFileName;
                filePart.write(savedPath);
                // Lưu đường dẫn dùng trong DB (tùy cấu trúc site, vd "img/xxx.jpg")
                imagePath = "img/" + submittedFileName;
            }
        }

        DAO dao = new DAO();
        // updateProduct sẽ parse price/category/id bên DAO
        dao.updateProduct(pname, imagePath, price, ptitle, pdescription, pcategory, pid);

        response.sendRedirect("manager");
    }

    // Nếu muốn support GET ở đây, giữ như mặc định hoặc leave empty
}
