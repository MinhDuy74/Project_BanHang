<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@500;700&family=Varela+Round&display=swap" rel="stylesheet">
</head>
<style>
    .header-center {
        margin-left: 20px;
        font-family: 'Poppins', 'Varela Round', 'Roboto', sans-serif;
        font-weight: 600;
        font-size: 1.7rem;
        letter-spacing: 1px;
        .header-right {
            display: flex;
            align-items: center;
            gap: 16px;
            font-family: 'Poppins', 'Varela Round', 'Roboto', sans-serif;
        }
        .header-center, .header-right {
            font-family: 'Poppins', 'Varela Round', 'Roboto', sans-serif;
        }
    }
</style>
<body>
    <!--begin of menu-->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <div class="d-flex align-items-center mx-auto"  >
                <a class="navbar-brand"  href="home">
                    <img src="https://ibrand.vn/wp-content/uploads/2022/10/logo-shop-giay-8.jpg" alt="Logo" height="50">
                    <span class="ms-3 fw-bold" style="color: white;margin-left: 8px;font-size:1.7rem; font-family:'Poppins','Varela Round','Roboto',sans-serif;">PicklePro Shop</span>
                </a>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                <ul class="navbar-nav m-auto">
                    <c:if test="${sessionScope.acc.isAdmin == 1}">
                        <li class="nav-item">
                            <a class="nav-link" href="managerAccount">Quản lý tài khoản</a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.acc.isSell == 1}">
                        <li class="nav-item">
                            <a class="nav-link" href="manager">Quản lý sản phẩm</a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.acc !=null}">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Xin chào, ${sessionScope.acc.user}</a>
                        </li>


                        <li class="nav-item">
                            <a class="nav-link" href="logout">Đăng xuất</a>
                        </li>

                    </c:if>

                    <c:if test="${sessionScope.acc == null}">

                        <li class="nav-item">
                            <a class="nav-link" href="Login.jsp">Đăng nhập</a>
                        </li>
                    </c:if>
                </ul>

                <form action="search" method="post" class="form-inline my-2 my-lg-0">
                    <div class="input-group input-group-sm">
                        <input oninput="searchByName(this)" value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Tìm kiếm...">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-secondary btn-number">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <a class="btn btn-success btn-sm ml-3" href="cart">
                        <i class="fa fa-shopping-cart"></i> Giỏ hàng

                        <!--                    <span class="badge badge-light">3</span>-->
                    </a>

                </form>
            </div>
        </div>
    </nav>
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Siêu thị giày chất lượng cao</h1>
            <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp các sản phầm giày nhập từ Trung Quốc</p>
        </div>
    </section>
    <!--end of menu-->

</body>
