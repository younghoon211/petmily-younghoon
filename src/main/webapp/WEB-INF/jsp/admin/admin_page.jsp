<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap"
          rel="stylesheet">
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
                <h1 class="mb-0 bread">관리자 페이지</h1>
            </div>
        </div>
    </div>
</section>
<section class="ftco-section bg-light">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12">
                <div class="wrapper">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-4 d-flex align-self-stretch px-4 ftco-animate">
                                <div class="d-block services text-center">
                                    <div class="media-body p-4">
                                        <h3 class="heading">회원 관리</h3>
                                        <a href="/admin/member"
                                           class="btn-custom d-flex align-items-center justify-content-center">
                                            <span class="fa fa-chevron-right"></span><i class="sr-only">Read more</i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 d-flex align-self-stretch px-4 ftco-animate">
                                <div class="d-block services text-center">
                                    <div class="media-body p-4">
                                        <h3 class="heading">유기동물 관리</h3>
                                        <a href="/admin/abandonedAnimal"
                                           class="btn-custom d-flex align-items-center justify-content-center">
                                            <span class="fa fa-chevron-right"></span>
                                            <i class="sr-only">Read more</i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 d-flex align-self-stretch px-4 ftco-animate">
                                <div class="d-block services text-center">
                                    <div class="media-body p-4">
                                        <h3 class="heading">게시글 관리</h3>
                                        <a href="/admin/board?kindOfBoard=free"
                                           class="btn-custom d-flex align-items-center justify-content-center">
                                            <span class="fa fa-chevron-right"></span>
                                            <i class="sr-only">Read more</i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br><br><br><br><br>
                        <div class="row">
                            <div class="col-lg-3 d-flex align-self-stretch px-4 ftco-animate">
                                <div class="d-block services text-center">
                                    <div class="media-body p-4">
                                        <h3 class="heading">입양 관리</h3>
                                        <a href="/admin/adopt"
                                           class="btn-custom d-flex align-items-center justify-content-center">
                                            <span class="fa fa-chevron-right"></span>
                                            <i class="sr-only">Read more</i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 d-flex align-self-stretch px-4 ftco-animate">
                                <div class="d-block services text-center">
                                    <div class="media-body p-4">
                                        <h3 class="heading">임시보호 관리</h3>
                                        <a href="/admin/temp"
                                           class="btn-custom d-flex align-items-center justify-content-center">
                                            <span class="fa fa-chevron-right"></span>
                                            <i class="sr-only">Read more</i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 d-flex align-self-stretch px-4 ftco-animate">
                                <div class="d-block services text-center">
                                    <div class="media-body p-4">
                                        <h3 class="heading">후원 관리</h3>
                                        <a href="/admin/donation"
                                           class="btn-custom d-flex align-items-center justify-content-center">
                                            <span class="fa fa-chevron-right"></span>
                                            <i class="sr-only">Read more</i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 d-flex align-self-stretch px-4 ftco-animate">
                                <div class="d-block services text-center">
                                    <div class="media-body p-4">
                                        <h3 class="heading">보호소 관리</h3>
                                        <a href="/admin/shelter"
                                           class="btn-custom d-flex align-items-center justify-content-center">
                                            <span class="fa fa-chevron-right"></span>
                                            <i class="sr-only">Read more</i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="info-wrap w-100 p-5 img"></div>
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