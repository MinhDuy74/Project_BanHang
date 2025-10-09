<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="https://ibrand.vn/wp-content/uploads/2022/10/logo-shop-giay-8.jpg" /> 
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/video_reel.css"/>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>

                    <div class="col-sm-9">
                        <div id="content" class="row">
                        <c:forEach items="${listP}" var="o">
                            <div class="product col-12 col-md-6 col-lg-4">
                                <div class="card ">
                                    <img class="card-img-top" src="${o.image}" alt="Card image cap">
                                    <div class="card-body ">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${o.id}" title="View Product">${o.name}</a></h4>
                                        <p class="card-text show_txt">${o.title}
                                        </p>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">
                                                    <fmt:formatNumber value="${o.price}" type="number" maxFractionDigits="0"/> VNĐ
                                                </p>
                                            </div>
                                            <div class="col">
                                                <a href="cart?id=${o.id}" class="btn btn-success btn-block">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <button onclick="loadMore()" class="btn btn-primary mt-3">Load more</button>

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
        <jsp:include page="Footer.jsp"></jsp:include>


    </body>
    <!-- Sư dung ajax Jquery de load san pham  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="js/video_reel.js"></script>

    <script >
        function loadMore() {
        var amount = document.getElementsByClassName("product").length;
                $.ajax({
                url: "/Project_BanHang/load",
                        type: "get",
                        data: {
                        exits: amount
                        },
                        success: function (data) {
                        var row = document.getElementById("content");
                                row.innerHTML += data;
                        },
                        error: function (xhr) {


                        }
 });
        }


<!-- Sư dung ajax Jquery de tim kiem tu dong san pham  -->

        function searchByName(param) {
            var txtSearch = param.value;
            $.ajax({
                url: "/Project_BanHang/searchAjax",
                type: "get",
                data: {
                    txt: txtSearch
                },

                success: function (data) {
                    var row = document.getElementById("content");
                    row.innerHTML = data;
                },
                error: function (xhr) {

                }
            });
        }

    </script>
</html>

