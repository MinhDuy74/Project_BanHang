<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thanh toán đơn hàng</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <style>
        body {
            background: #f6f7fb;
            font-family: Arial, sans-serif;
        }
        .payment-container {
            max-width: 480px;
            margin: 60px auto 0 auto;
            background: #fff;
            border-radius: 13px;
            box-shadow: 0 8px 32px 0 rgba(31,38,135,0.08);
            padding: 40px 32px 32px 32px;
        }
        .form-label {
            font-weight: 600;
            margin-bottom: 7px;
        }
        .form-control[readonly] {
            background: #e9ecef;
        }
        .btn-block {
            font-size: 18px;
            font-weight: 600;
            padding: 11px 0;
            border-radius: 7px;
        }
        .error {
            color: #d90429;
            text-align: center;
            margin-top: 17px;
            font-weight: 600;
        }
        .success {
            color: #38b000;
            text-align: center;
            margin-top: 17px;
            font-weight: 600;
        }
        .title {
            font-weight: 700;
            margin-bottom: 30px;
            text-align: center;
            color: #212529;
        }
        @media (max-width: 500px) {
            .payment-container {
                padding: 20px 8px 18px 8px;
            }
            .title {
                font-size: 1.3rem;
            }
        }
    </style>
</head>
<body>
    <div class="payment-container shadow">
        <div class="title h3">Thanh toán đơn hàng</div>
        <form action="payment" method="post" autocomplete="off" onsubmit="return validateForm()">
            <div class="form-group">
                <label class="form-label">Họ tên:</label>
                <input type="text" class="form-control" name="fullname" id="fullname" placeholder="Nhập họ tên" required>
            </div>
            <div class="form-group">
                <label class="form-label">Số điện thoại:</label>
                <input type="text" class="form-control" name="phone" id="phone" placeholder="Nhập số điện thoại" required pattern="^0\d{9,10}$" maxlength="11">
            </div>
            <div class="form-group">
                <label class="form-label">Địa chỉ giao hàng:</label>
                <input type="text" class="form-control" name="address" id="address" placeholder="Nhập địa chỉ nhận hàng" required>
            </div>
            <div class="form-group">
                <label class="form-label">Phương thức thanh toán:</label>
                <select class="form-control" name="paymentMethod" required>
                    <option value="">--Chọn phương thức--</option>
                    <option value="COD">Thanh toán khi nhận hàng (COD)</option>
                    <option value="Bank">Chuyển khoản ngân hàng</option>
                </select>
            </div>
            <button type="submit" class="btn btn-dark btn-block mt-2">Thanh toán</button>
        </form>
       
    </div>
    <!-- Bootstrap JS (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <script>
        function validateForm() {
            var fullname = document.getElementById('fullname').value.trim();
            var phone = document.getElementById('phone').value.trim();
            var address = document.getElementById('address').value.trim();
            if (fullname.length < 3) {
                alert('Vui lòng nhập họ tên hợp lệ!');
                return false;
            }
            if (!/^0\d{9,10}$/.test(phone)) {
                alert('Số điện thoại phải bắt đầu bằng 0 và có 10 hoặc 11 số!');
                return false;
            }
            if (address.length < 5) {
                alert('Vui lòng nhập địa chỉ hợp lệ!');
                return false;
            }
            return true;
        }
    </script>
</body>
</html>