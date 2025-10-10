<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đơn hàng của tôi</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <style>
        body {
            background: #f6f7fb;
            font-family: Arial, sans-serif;
        }
        .order-container {
            max-width: 900px;
            margin: 50px auto;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 8px 32px 0 rgba(31,38,135,0.10);
            padding: 36px 22px 30px 22px;
        }
        .table th, .table td {
            vertical-align: middle !important;
        }
        .btn-detail {
            border-radius: 6px;
            font-weight: 600;
        }
        .status-pending {
            color: #ff9800;
            font-weight: bold;
        }
        .status-success {
            color: #38b000;
            font-weight: bold;
        }
        .status-cancel {
            color: #d90429;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="order-container shadow">
    <h3 class="mb-4 text-center font-weight-bold">Đơn hàng của bạn</h3>
    <c:choose>
        <c:when test="${empty orderList}">
            <div class="alert alert-info text-center">Bạn chưa có đơn hàng nào.</div>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead class="thead-light">
                    <tr>
                        <th>Mã đơn hàng</th>
                        <th>Ngày đặt</th>
                        <th>Trạng thái</th>
                        <th>Tổng tiền</th>
                        <th>Chi tiết</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="o" items="${orderList}">
                        <tr>
                            <td>${o.orderId}</td>
                            <td>
                                <fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm"/>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${o.status eq 'pending'}">
                                        <span class="status-pending">Đang xử lý</span>
                                    </c:when>
                                    <c:when test="${o.status eq 'completed'}">
                                        <span class="status-success">Đã giao</span>
                                    </c:when>
                                    <c:when test="${o.status eq 'cancel'}">
                                        <span class="status-cancel">Đã hủy</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>${o.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatNumber value="${o.total}" type="number" maxFractionDigits="0"/> VNĐ
                            </td>
                            <td>
                                <a href="orderdetail?orderId=${o.orderId}" class="btn btn-outline-primary btn-detail btn-sm">Xem chi tiết</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div><a href="home" class="btn btn-outline-primary btn-action">Quay về trang chủ</a></div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>