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
                                        <!-- Carousel ảnh sản phẩm -->
                                        <div id="productCarousel" class="carousel slide" data-ride="carousel">
                                            <div class="carousel-inner">
                                            <c:forEach items="${listImages}" var="img" varStatus="loop">
                                                <div class="carousel-item ${loop.index == 0 ? 'active' : ''}">
                                                    <img class="d-block w-100" src="${pageContext.request.contextPath}/${img}" alt="Ảnh sản phẩm">
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <a class="carousel-control-prev" href="#productCarousel" role="button" data-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                        <a class="carousel-control-next" href="#productCarousel" role="button" data-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </div>

                                    <!-- Thumbnail indicators (nếu muốn) -->
                                    <ol class="carousel-indicators mt-2">
                                        <c:forEach items="${listImages}" var="img" varStatus="loop">
                                            <li data-target="#productCarousel" data-slide-to="${loop.index}" class="${loop.index == 0 ? 'active' : ''}">
                                                <img src="${pageContext.request.contextPath}/${img}" style="width:60px;height:60px;object-fit:cover;border-radius:4px;">
                                            </li>
                                        </c:forEach>
                                    </ol>
                                    </article>

                                    <!-- Bên phần thông tin (col-sm-7), thêm chọn màu trước Quantity -->
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
                                        <a href="#" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>
                                        <a href="cart?id=${detail.id}" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </a>
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
        </div>        <jsp:include page="Footer.jsp"></jsp:include>
    </body>

    <script src="js/video_reel.js"></script>
</html>
