<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết đơn hàng</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
    <h3 class="mb-4 text-center font-weight-bold">Chi tiết đơn hàng #${order.orderId}</h3>
    <div class="card mb-3">
        <div class="card-header font-weight-bold">Thông tin đơn hàng</div>
        <div class="card-body">
            <b>Ngày đặt:</b> <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm"/><br>
            <b>Trạng thái:</b> 
            <c:choose>
                <c:when test="${order.status eq 'pending'}"><span class="text-warning">Đang xử lý</span></c:when>
                <c:when test="${order.status eq 'completed'}"><span class="text-success">Đã giao</span></c:when>
                <c:when test="${order.status eq 'cancel'}"><span class="text-danger">Đã hủy</span></c:when>
                <c:otherwise>${order.status}</c:otherwise>
            </c:choose>
            <br>
            <b>Họ tên:</b> ${order.fullname}<br>
            <b>SĐT:</b> ${order.phone}<br>
            <b>Địa chỉ:</b> ${order.address}<br>
        </div>
    </div>
    <div class="card">
        <div class="card-header font-weight-bold">Danh sách sản phẩm</div>
        <div class="card-body p-0">
            <table class="table table-striped mb-0">
                <thead>
                    <tr>
                        <th>Ảnh</th>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Đơn giá</th>
                        <th>Thành tiền</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="sum" value="0"/>
                    <c:forEach var="d" items="${detailList}">
                        <tr>
                            <td>
                                <img src="${d.productImage}" alt="${d.productName}" width="60">
                            </td>
                            <td>${d.productName}</td>
                            <td>${d.quantity}</td>
                            <td><fmt:formatNumber value="${d.price}" type="number" maxFractionDigits="0"/> VNĐ</td>
                            <td>
                                <fmt:formatNumber value="${d.price * d.quantity}" type="number" maxFractionDigits="0"/> VNĐ
                                <c:set var="sum" value="${sum + d.price * d.quantity}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4" class="text-right font-weight-bold">Tổng cộng:</td>
                        <td class="font-weight-bold"><fmt:formatNumber value="${sum}" type="number" maxFractionDigits="0"/> VNĐ</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="mt-4 text-center">
        <a href="orderlist" class="btn btn-secondary">Quay lại danh sách đơn hàng</a>
    </div>
</div>
</body>
</html>