<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Account Management</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Quản lý tài khoản</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>User</th>
                        <th>Role</th>
                        <th>Phân quyền</th>
                        <th>Xóa</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listA}" var="a">
                        <tr>
                            <td>${a.id}</td>
                            <td>${a.user}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${a.isAdmin == 1}">Admin</c:when>
                                    <c:when test="${a.isSell == 1}">Seller</c:when>
                                    <c:otherwise>Khách</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="updateRole" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${a.id}"/>
                                    <select name="role">
                                        <option value="admin" ${a.isAdmin == 1 ? 'selected' : ''}>Admin</option>
                                        <option value="seller" ${a.isSell == 1 && a.isAdmin == 0 ? 'selected' : ''}>Seller</option>
                                        <option value="user" ${(a.isSell == 0 && a.isAdmin == 0) ? 'selected' : ''}>User</option>
                                    </select>
                                    <button type="submit" class="btn btn-sm btn-primary">Lưu</button>
                                </form>
                            </td>
                            <td>
                                <form action="deleteAccount" method="post" style="display:inline;" onsubmit="return confirm('Bạn chắc chắn xóa tài khoản này?');">
                                    <input type="hidden" name="id" value="${a.id}"/>
                                    <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="home" class="btn btn-secondary">Quay về trang chủ</a>
        </div>
    </body>
</html>