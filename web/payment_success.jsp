<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Thanh toán thành công</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
        <style>
            body {
                background: #f6f7fb;
            }
            .success-container {
                max-width: 420px;
                margin: 70px auto;
                background: #fff;
                border-radius: 13px;
                box-shadow: 0 8px 32px 0 rgba(31,38,135,0.08);
                padding: 40px 30px 32px 30px;
                text-align: center;
            }
            .icon-success {
                color: #38b000;
                font-size: 48px;
                margin-bottom: 18px;
            }
            .order-id {
                font-size: 1.18rem;
                color: #333;
                margin-bottom: 18px;
                font-weight: 500;
            }
            .btn-action {
                font-weight: 600;
                font-size: 16px;
                border-radius: 6px;
                margin: 7px 6px 0 6px;
                padding: 10px 0;
                width: 170px;
            }
        </style>
    </head>
    <body>
        <div class="success-container shadow">
            <div class="icon-success">
                <svg width="52" height="52" fill="none" stroke="currentColor" stroke-width="2"
                     viewBox="0 0 24 24" style="vertical-align:middle;">
                <circle cx="12" cy="12" r="10" stroke="#38b000" stroke-width="2" fill="#e9faef"/>
                <path d="M8 13l2 2 4-4" stroke="#38b000" stroke-width="2" fill="none"/>
                </svg>
            </div>
            <h3 class="mb-3 text-success">Thanh toán thành công!</h3>
            <div class="order-id">
                <c:if test="${not empty orderId}">
                    Mã đơn hàng: <strong>${orderId}</strong>
                </c:if>
            </div>
            <div>
                <a href="home" class="btn btn-outline-primary btn-action">Quay về trang chủ</a>
                <a href="orderlist" class="btn btn-dark btn-action">Theo dõi đơn hàng</a>
            </div>
        </div>
    </body>
</html>