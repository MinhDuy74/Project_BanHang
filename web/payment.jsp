<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thanh toán đơn hàng</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <style>
        body {
            background: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .payment-container {
            max-width: 460px;
            margin: 50px auto 0 auto;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 8px 32px 0 rgba(31,38,135,0.07);
            padding: 40px 30px 32px 30px;
        }
        .form-label {
            font-weight: 600;
        }
        .form-control[readonly] {
            background: #e9ecef;
        }
        .btn-block {
            font-size: 17px;
            font-weight: 600;
            padding: 10px 0;
            border-radius: 6px;
        }
        .error {
            color: #d90429;
            text-align: center;
            margin-top: 16px;
            font-weight: 600;
        }
        .success {
            color: #38b000;
            text-align: center;
            margin-top: 16px;
            font-weight: 600;
        }
        .title {
            font-weight: 700;
            margin-bottom: 28px;
            text-align: center;
            color: #212529;
        }
    </style>
</head>
<body>
    <div class="payment-container shadow">
        <div class="title h3">Thanh toán đơn hàng</div>
        <form action="payment" method="post" autocomplete="off">
            <input type="hidden" name="orderId" value="${orderId}" />
            <div class="form-group">
                <label class="form-label">Số tiền thanh toán:</label>
                <input type="text" class="form-control" name="amount" value="${amount}" readonly />
            </div>
            <div class="form-group">
                <label class="form-label">Phương thức thanh toán:</label>
                <select class="form-control" name="paymentMethod" required>
                    <option value="COD">Thanh toán khi nhận hàng (COD)</option>
                    <option value="Bank">Chuyển khoản ngân hàng</option>
                </select>
            </div>
            <c:if test="${not empty orderId}">
                <button type="submit" class="btn btn-dark btn-block">Thanh toán</button>
            </c:if>
        </form>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="success">${message}</div>
        </c:if>
    </div>
    <!-- Bootstrap JS (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>