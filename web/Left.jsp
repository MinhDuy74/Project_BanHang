<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase">
            <i class="fa fa-list"></i> Lọc theo
        </div>
        <ul class="list-group category_block" id="categoryList">
            <c:forEach var="o" items="${listC}" varStatus="loop">
                <c:url var="editURL" value="/category">
                    <c:param name="cid" value="${o.cid}"/>
                </c:url>
                <li class="list-group-item text-white ${tag==o.cid ? 'active' : ''}" 
                    style="${loop.index >= 5 ? 'display:none;' : ''}" 
                    data-index="${loop.index}">
                    <a href="${editURL}">${o.cname}</a>
                </li>
            </c:forEach>
        </ul>
        <c:if test="${fn:length(listC) > 5}">
            <button class="btn btn-link" id="showMoreBtn" onclick="showMoreCategories()">Xem thêm</button>
        </c:if>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Sản phẩm mới</div>
        <div class="card-body">
            <img class="img-fluid" src="img/sketcher.webp" />
            <h5 class="card-title">Giày Pickleball Skechers Viper Court Pro</h5>
            <p class="card-text">Mua Giày Skechers Viper Court Pro ‘Pink’ 246069-PNK chính hãng 100% có sẵn tại PicklePro Shop. 
                Giao hàng miễn phí trong 1 ngày. Cam kết đền tiền X5 nếu phát hiện Fake. Đổi trả miễn phí size. FREE vệ sinh trọn đời. MUA NGAY!</p>
            <p class="bloc_left_price">3.990.000 VNĐ</p>
        </div>
    </div>
</div>
<script>
function showMoreCategories() {
    const items = document.querySelectorAll('#categoryList li');
    items.forEach(item => item.style.display = 'block');
    document.getElementById('showMoreBtn').style.display = 'none';
}
</script>