<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
        <%@ page import="model.Cart" %>
        <div class="shopping-cart">
            <div class="px-4 px-lg-0">
                <div class="pb-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                <!-- Kiểm tra nếu giỏ hàng rỗng -->
                                <c:choose>
                                    <c:when test="${empty listP}">
                                        <div class="alert alert-info">Giỏ hàng của bạn đang trống.</div>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Shopping cart table -->
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                                        </th>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="py-2 text-uppercase">Đơn Giá</div>
                                                        </th>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="py-2 text-uppercase">Số Lượng</div>
                                                        </th>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="py-2 text-uppercase">Thành tiền</div>
                                                        </th>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="py-2 text-uppercase">Xóa</div>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set var="total" value="0" scope="page"/>
                                                    <c:forEach items="${listP}" var="o">
                                                        <tr>
                                                            <th scope="row">
                                                                <div class="p-2">
                                                                    <img src="${o.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                                    <div class="ml-3 d-inline-block align-middle">
                                                                        <h5 class="mb-0">
                                                                            <a href="detail?pid=${o.id}" class="text-dark d-inline-block">${o.name}</a>
                                                                        </h5>
                                                                    </div>
                                                                </div>
                                                            </th>
                                                            <td class="align-middle">
                                                                <fmt:formatNumber value="${o.price}" type="number" maxFractionDigits="0"/> VNĐ
                                                            </td>
                                                            <td class="align-middle">
                                                                <a href="cart?id=${o.id}&action=sub">
                                                                   <button class="btn btn-sm btn-secondary">-</button>
                                                                </a>
                                                                <strong>${o.amount}</strong>
                                                                <a href="cart?id=${o.id}&action=add">
                                                                    <button class="btn btn-sm btn-secondary">+</button>
                                                                </a>
                                                            </td>
                                                            <td class="align-middle">
                                                                <fmt:formatNumber value="${o.price * o.amount}" type="number" maxFractionDigits="0"/> VNĐ
                                                                <c:set var="total" value="${total + (o.price * o.amount)}" scope="page"/>
                                                            </td>
                                                            <td class="align-middle">
                                                                <a href="cart?id=${o.id}&action=remove" class="text-dark">
                                                                    <button type="button" class="btn btn-danger btn-sm">Xóa</button>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- End -->
                                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                                            <div class="col-lg-6">
                                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Voucher</div>
                                                <div class="p-4">
                                                    <div class="input-group mb-4 border rounded-pill p-2">
                                                        <input type="text" placeholder="Nhập Voucher" aria-describedby="button-addon3" class="form-control border-0">
                                                        <div class="input-group-append border-0">
                                                            <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2"></i>Sử dụng</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                                                <div class="p-4">
                                                    <ul class="list-unstyled mb-4">
                                                        <li class="d-flex justify-content-between py-3 border-bottom">
                                                            <strong class="text-muted">Tổng tiền hàng</strong>
                                                            <strong><fmt:formatNumber value="${total}" type="number" maxFractionDigits="0"/> VNĐ</strong>
                                                        </li>
                                                        <li class="d-flex justify-content-between py-3 border-bottom">
                                                            <strong class="text-muted">VAT</strong>
                                                            <strong><fmt:formatNumber value="${total * 0.1}" type="number" maxFractionDigits="0"/> VNĐ</strong>
                                                        </li>
                                                        <li class="d-flex justify-content-between py-3 border-bottom">
                                                            <strong class="text-muted">Tổng thanh toán</strong>
                                                            <h5 class="font-weight-bold"><fmt:formatNumber value="${total * 1.1}" type="number" maxFractionDigits="0"/> VNĐ</h5>
                                                        </li>
                                                    </ul>
                                                    <form action="payment" method="get">
                                                        <input type="hidden" name="amount" value="${total * 1.1}" />
                                                        <button type="submit" class="btn btn-dark rounded-pill py-2 btn-block">Thanh toán</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>