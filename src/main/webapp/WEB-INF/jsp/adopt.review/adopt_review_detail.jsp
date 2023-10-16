<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
         style="background-image: url('../../../resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <c:if test="${param.kindOfBoard eq 'adoptReview'}">
                    <p class="breadcrumbs mb-2">
                        <span class="mr-2"><span>Adopt Review Board - Detail<i class="ion-ios-arrow-forward"></i></span></span>
                    </p>
                    <h1 class="mb-0 bread">입양후기 게시판 - 상세보기</h1>
                </c:if>
            </div>
        </div>
    </div>
</section>

<!-- 글 상세보기 -->
<section class="ftco-section bg-light">
    <div class="container">
        <div class="media-body ml-3">

            <!-- content title, name, wrTime -->
            <b> <span style="font-size: 2em;"><c:out value="${detailForm.title}"/></span> </b>
            <h6 class="mt-1"></h6>
            <small><a href="javascript:void(0)"><c:out value="${detailForm.name}"/></a></small>
            <small style="float: right">조회수: <c:out value="${detailForm.viewCount}"/>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${detailForm.wrTime}"/></small>

            <!-- content 내용 -->
            <div class="modal-footer"></div>

            <c:if test="${detailForm.imgPath ne 'no_image.png'}">
                <img width="50%" src="<c:out value='/adoptReview/upload?filename=${detailForm.imgPath}'/>"><br><br>
            </c:if>

            <p><c:out value="${detailForm.content}"/></p>
            <br>

            <!-- content 수정, 삭제 -->
            <div class="modal-footer">
                <c:if test="${authUser.getMNumber() eq detailForm.getMNumber() || authUser.grade eq '관리자'}">
                    <button type="button" class="btn btn-primary"
                            onclick="window.location.href='<c:out value="/adoptReview/auth/modify?kindOfBoard=${param.kindOfBoard}&bNumber=${detailForm.getBNumber()}"/>'">
                        수정
                    </button>

                    <button type="button" class="btn btn-danger"
                            onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                    {return window.location.href='<c:out value="/adoptReview/auth/delete?kindOfBoard=${param.kindOfBoard}&bNumber=${detailForm.getBNumber()}"/>';}">
                        삭제
                    </button>
                </c:if>

                <!-- content 목록 이동 버튼 -->
                <button type="button" class="btn btn-secondary"
                        onclick="window.location.href='/adoptReview/list?kindOfBoard=adoptReview&sort=adoptReviewNo'">목록으로
                </button>
                <c:if test="${authUser.grade eq '관리자'}">
                    <button type="button" class="btn btn-dark"
                            onclick="window.location.href='/admin/board?kindOfBoard=${param.kindOfBoard}'">게시판 관리로
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</section>

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