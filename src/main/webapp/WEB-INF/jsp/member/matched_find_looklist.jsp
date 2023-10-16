<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/css/freeBoard.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/animate.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/magnific-popup.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/jquery.timepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/flaticon.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/style.css">
</head>

<body>
<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"/>
    </svg>
</div>

<!-- header -->
<%@ include file="../include/header.jspf" %>

<%-- 현재 페이지 --%>
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../resources/petsitting-master/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <p class="breadcrumbs mb-2">
                    <span class="mr-2"><span>Member - Matched Look Board List<i class="ion-ios-arrow-forward"></i></span></span>
                </p>
                <h1 class="mb-0 bread">찾아요에 매칭된 봤어요 리스트</h1>
            </div>
        </div>
    </div>
</section>

<!-- 게시판 List -->
<section class="ftco-section bg-light">
    <div class="container">
        <div class="inner-main-body p-2 p-sm-3 collapse forum-content show">

            <!-- 목록 출력 -->
            <div class="container">
                <div class="row d-flex">
                    <c:forEach var="lookBoard" items="${pageForm.content}">
                        <div class="col-md-4 ftco-animate" id="d-flex-out">
                            <div class="blog-entry align-self-stretch" id="d-flex-in">
                                <a href="<c:out value='/lookBoard/detail?laNumber=${lookBoard.laNumber}'/>"
                                   class="block-20 rounded"
                                   style="background-image: url('<c:out value="/lookBoard/upload?filename=${lookBoard.imgPath}"/>');">
                                </a>
                                <div class="text p-4">
                                    <div class="meta mb-2">
                                        <div><small><c:out value="${lookBoard.wrTime}"/></small></div>
                                        <br>
                                        <div><c:out value="종: ${lookBoard.species} / 품종: ${lookBoard.kind} / 발견장소: ${lookBoard.location}"/></div>
                                        <br>
                                        <div>상태: <c:out value="${lookBoard.animalState}"/></div>
                                    </div>
                                    <div><small style="color: #00bd56"><c:out value="${lookBoard.name}"/></small></div>
                                    <h3 class="heading">
                                        <a href="<c:out value='lookBoard/detail?laNumber=${lookBoard.laNumber}'/>">
                                                <c:out value="${lookBoard.title}"/>
                                        </a></h3>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>



            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="window.location.href='/member/auth/findMatching'">뒤로가기</button>
            </div>

            <!-- 페이징 처리 -->
            <div class="row mt-5">
                <div class="col text-center">
                    <div class="block-27">
                        <ul>
                            <li>
                                <c:if test="${pageForm.startPage > 5}">
                                    <a href="/member/auth/findMatching/lookList?pageNo=${pageForm.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <li>
                            <c:forEach var="pNo" begin="${pageForm.startPage}" end="${pageForm.endPage}">
                                <c:if test="${pageForm.currentPage eq pNo}">
                                    <li class="active">
                                        <a href="/member/auth/findMatching/lookList?pageNo=${pNo}">${pNo}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageForm.currentPage ne pNo}">
                                    <li>
                                        <a href="/member/auth/findMatching/lookList?pageNo=${pNo}">${pNo}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            </li>
                            <li>
                                <c:if test="${pageForm.endPage < pageForm.totalPages}">
                                    <a href="/member/auth/findMatching/lookList?pageNo=${pageForm.startPage + 5}">&gt;</a>
                                </c:if>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 게시판 List 끝 -->

<script src="/resources/petsitting-master/js/jquery.min.js"></script>
<script src="/resources/petsitting-master/js/jquery-migrate-3.0.1.min.js"></script>
<script src="/resources/petsitting-master/js/popper.min.js"></script>
<script src="/resources/petsitting-master/js/bootstrap.min.js"></script>
<script src="/resources/petsitting-master/js/jquery.easing.1.3.js"></script>
<script src="/resources/petsitting-master/js/jquery.waypoints.min.js"></script>
<script src="/resources/petsitting-master/js/jquery.stellar.min.js"></script>
<script src="/resources/petsitting-master/js/jquery.animateNumber.min.js"></script>
<script src="/resources/petsitting-master/js/bootstrap-datepicker.js"></script>
<script src="/resources/petsitting-master/js/jquery.timepicker.min.js"></script>
<script src="/resources/petsitting-master/js/owl.carousel.min.js"></script>
<script src="/resources/petsitting-master/js/jquery.magnific-popup.min.js"></script>
<script src="/resources/petsitting-master/js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="/resources/petsitting-master/js/google-map.js"></script>
<script src="/resources/petsitting-master/js/main.js"></script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>