<%-- 
    Document   : Detail
    Created on : Dec 29, 2020, 5:43:04 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="https://ibrand.vn/wp-content/uploads/2022/10/logo-shop-giay-8.jpg" /> 
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/video_reel.css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
            #cart-toast {
                position: fixed;
                top: 80px;
                right: 30px;
                background: #2ecc40;
                color: #fff;
                padding: 16px 22px;
                border-radius: 8px;
                font-size: 1.1rem;
                z-index: 99999;
                box-shadow: 0 2px 8px rgba(0,0,0,0.25);
                display: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>
                    <div class="col-sm-9">
                        <div class="container">
                            <div class="card">
                                <div class="row">
                                    <aside class="col-sm-5 border-right">
                                        <!--List ảnh sản phẩm--> 
                                        <div id="productCarousel" class="carousel slide" data-ride="carousel">
                                            <div class="carousel-inner">
                                            <c:forEach items="${listImages}" var="img" varStatus="loop">
                                                <div class="carousel-item ${loop.index == 0 ? 'active' : ''}">
                                                    <img class="d-block w-100" src="${pageContext.request.contextPath}/${img}" alt="Ảnh sản phẩm">
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <c:if test="${not empty listColors}">
                                        <div class="mb-3">
                                            <label style="font-weight:bold;">Màu sắc:</label><br/>
                                            <c:forEach items="${listColors}" var="c">
                                                <a href="detail?pid=${detail.id}&colorId=${c.colorId}"
                                                   class="btn btn-sm ${activeColorId == c.colorId ? 'btn-primary' : 'btn-outline-secondary'}"
                                                   style="margin:2px;">
                                                    <span style="display:inline-block;width:14px;height:14px;background:${c.colorCode};border:1px solid #ccc;margin-right:6px;vertical-align:middle;"></span>
                                                    ${c.colorName}
                                                </a>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">VNĐ</span>
                                                <span class="num"><fmt:formatNumber value="${detail.price}" type="number" maxFractionDigits="0"/></span>
                                            </span> 
                                        </p>
                                        <!-- price-detail-wrap .// -->
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>
                                                    ${detail.description}

                                                </p></dd>
                                        </dl>

                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <select class="form-control form-control-sm" style="width:70px;">
                                                            <option> 1 </option>
                                                            <option> 2 </option>
                                                            <option> 3 </option>

                                                        </select>
                                                    </dd>
                                                </dl>  <!-- item-property .// -->
                                            </div> <!-- col.// -->

                                        </div> <!-- row.// -->
                                        <hr>
                                        <a href="cart?id=${detail.id}&action=add" class="btn btn-lg btn-primary text-uppercase"> Mua ngay</a>
                                        <button class="btn btn-lg btn-outline-primary text-uppercase add-to-cart-btn"
                                                data-id="${detail.id}">
                                            <i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng
                                        </button>
                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->
                    </div>
                </div>
            </div>
        </div>
        <!-- POPUP MINI REEL di chuyển được -->
        <div id="mini-reel-popup">
            <div id="mini-reel-popup-inner">
                <video id="mini-reel-video" src="videos/video1.mp4" muted autoplay loop></video>
                <button id="mini-reel-close" onclick="closeMiniReelPopup()">×</button>
                <div id="mini-reel-live">▶️ Xem Reels</div>
            </div>
        </div>
        <!-- FULLSCREEN REEL xem dạng Facebook -->
        <div id="full-reel-overlay">
            <button class="full-reel-btn full-reel-btn-close" onclick="closeReelFull()">×</button>
            <div id="full-reel-inner">
                <button class="full-reel-btn full-reel-btn-prev" onclick="prevReel()">‹</button>
                <video id="full-reel-video" controls autoplay></video>
                <button class="full-reel-btn full-reel-btn-next" onclick="nextReel()">›</button>
            </div>
        </div>  
        <div id="cart-toast"></div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>

    <script src="js/video_reel.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
                    $(document).on('click', '.add-to-cart-btn', function (e) {
                        e.preventDefault();
                        var productId = $(this).data('id');
                        $.post('add-to-cart', {id: productId}, function (res) {
                            $('#cart-toast').text('Đã thêm vào giỏ hàng!').fadeIn().delay(1000).fadeOut();
                        });
                    });
    </script>
</html>
