<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <form action="edit" method="post" enctype="multipart/form-data">
            <div class="modal-header">						
                <h4 class="modal-title">Edit Product</h4>
                <button  type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">					
                <div class="form-group">
                    <label>ID</label>
                    <input value="${detail.id }" name="id" type="text" class="form-control" readonly required>
                </div>
                <div class="form-group">
                    <label>Name</label>
                    <input value="${detail.name }" name="name" type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Image</label>
                    <!-- Hiển thị ảnh hiện tại -->
                    <c:if test="${not empty detail.image}">
                        <img src="${detail.image}" alt="Current image" style="width:200px;height:120px;display:block;margin-bottom:8px;"/>
                    </c:if>
                    <!-- Giữ đường dẫn ảnh cũ nếu không upload file mới -->
                    <input type="hidden" name="currentImage" value="${detail.image}" />
                    <!-- Nếu muốn upload ảnh mới -->
                    <input name="image" type="file" class="form-control" accept="image/*">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input value="${detail.price}" name="price" type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Title</label>
                    <textarea name="title" class="form-control" required>${detail.title }</textarea>
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea name="description" class="form-control" required>${detail.description}</textarea>
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <select name="category" class="form-select" aria-label="Default select example">
                        <c:forEach items="${listC}" var="o">
                            <option value="${o.cid}">
                                <c:out value="${o.cname}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-success" value="Edit">
            </div>
        </form>
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>