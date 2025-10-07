<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Thêm màu và ảnh cho sản phẩm</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        .container { margin-top: 40px; max-width: 600px; }
        .form-group label { font-weight: bold; }
    </style>
</head>
<body>
<div class="container">
    <h3>Thêm màu và ảnh cho sản phẩm</h3>
    <form action="addColorImage" method="post" enctype="multipart/form-data">
        <input type="hidden" name="product_id" value="${param.pid}" />
        <div class="form-group">
            <label>Tên màu</label>
            <input type="text" name="color_name" class="form-control" required />
        </div>
        <div class="form-group">
            <label>Mã màu (Hex)</label>
            <input type="text" name="color_code" class="form-control" placeholder="#RRGGBB" />
        </div>
        <div class="form-group">
            <label>Chọn ảnh sản phẩm (có thể chọn nhiều)</label>
            <input type="file" name="images" class="form-control" accept="image/*" multiple required />
        </div>
        <button type="submit" class="btn btn-success">Thêm màu & ảnh</button>
        <a href="manager" class="btn btn-default">Quay lại</a>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$('#addColorModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var pid = button.data('pid');
    $(this).find('input[name="product_id"]').val(pid);
});
</script>
</body>
</html>