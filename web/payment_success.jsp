<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<div class="order-success-container">
    <div class="checkout-box">
        <h2 class="text-center mb-4">🎉 Đặt Hàng Thành Công!</h2>
        <p class="text-center">Cảm ơn bạn đã tin tưởng và đặt hàng. Đơn hàng của bạn đang được xử lý.</p>
        
        <div class="d-flex justify-content-center flex-column flex-md-row gap-3 mt-4 action-buttons">
            <c:if test="${not empty sessionScope.orderId}">
                <a href="${pageContext.request.contextPath}/order-detail?orderId=${sessionScope.orderId}" class="btn btn-primary">
                    Xem Chi Tiết Đơn Hàng
                </a>
            </c:if>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
                Quay Về Trang Chủ
            </a>
        </div>
    </div>
</div>
