<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Thêm sản phẩm</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <style>.container{
            max-width:720px;
            margin-top:30px
        }</style>
    </head>
    <body>
        <div class="container">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
                
            <h3>Thêm sản phẩm</h3>
            <form action="${pageContext.request.contextPath}/add-product" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label>Tên sản phẩm</label>
                    <input name="name" type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Ảnh (URL)</label>
                    <input type="file" name="images" class="form-control" accept="image/*"  required />
                </div>
                <div class="form-group">
                    <label>Giá</label>
                    <input name="price" type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Tiêu đề</label>
                    <textarea name="title" class="form-control" required></textarea>
                </div>
                <div class="form-group">
                    <label>Mô tả</label>
                    <textarea name="description" class="form-control" required></textarea>
                </div>
                <div class="form-group">
                    <label>Danh mục</label>
                    <select name="category" class="form-control" required>
                        <c:forEach items="${listC}" var="c">
                            <option value="${c.cid}">${c.cname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-right">
                    <a class="btn btn-default" href="${pageContext.request.contextPath}/manager">Hủy</a>
                    <button type="submit" class="btn btn-success">Thêm</button>
                </div>
            </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>