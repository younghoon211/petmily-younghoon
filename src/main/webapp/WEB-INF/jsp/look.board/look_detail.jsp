<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"></circle>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"></circle>
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
                <p class="breadcrumbs mb-2">
                    <span class="mr-2"><span>Look Animal Board - Detail<i
                            class="ion-ios-arrow-forward"></i></span></span>
                </p>
                <h1 class="mb-0 bread">유기동물 봤어요 - 상세보기</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section ftco-no-pt ftco-no-pb">
    <div class="container">
        <div class="row d-flex no-gutters">
            <div class="col-md-5 d-flex">
                <img src="/lookBoard/upload?filename=${detailForm.imgPath}" style='width: 100%; object-fit: contain'/>
            </div>
            <div class="col-md-7 pl-md-5 py-md-5">

                <div class="heading-section pt-md-5">
                    <h2 class="mb-4">${detailForm.title}</h2>
                    <span>
                        <small>조회수: ${detailForm.viewCount}</small>
                        <small style="float: right">${detailForm.wrTime}</small>
                    </span>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center">
                            <span class="flaticon-stethoscope"></span>
                        </div>
                        <div class="text pl-3">
                            <h4>종</h4>
                            <p>${detailForm.species}</p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center">
                            <span class="flaticon-customer-service"></span>
                        </div>
                        <div class="text pl-3">
                            <h4>품종</h4>
                            <p>${detailForm.kind}</p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center">
                            <span class="flaticon-emergency-call"></span>
                        </div>
                        <div class="text pl-3">
                            <h4>발견 장소</h4>
                            <p>${detailForm.location}</p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center">
                            <span class="flaticon-veterinarian"></span>
                        </div>
                        <div class="text pl-3">
                            <h4>상태</h4>
                            <p>${detailForm.animalState}</p>
                        </div>
                    </div>
                    <div class="col-md">
                        <p>${detailForm.content}</p>
                    </div>
                </div>

                <div class="modal-footer">
                    <c:if test="${authUser.getMNumber() eq detailForm.getMNumber() || authUser.grade eq '관리자'}">
                        <button type="button" class="btn btn-primary"
                                onclick="location.href='/lookBoard/auth/modify?laNumber=${detailForm.laNumber}'">수정
                        </button>
                        <button type="button" class="btn btn-danger"
                                onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                        {return location.href='/lookBoard/auth/delete?laNumber=${detailForm.laNumber}';}">
                            삭제
                        </button>
                    </c:if>
                    <button type="button" class="btn btn-secondary"
                            onclick="location.href='/lookBoard/list?sort=lno'">목록으로
                    </button>
                    <c:if test="${authUser.grade eq '관리자'}">
                        <button type="button" class="btn btn-dark"
                                onclick="location.href='/admin/board?kindOfBoard=look'">게시판 관리로
                        </button>
                    </c:if>
                </div>

            </div>
        </div>
    </div>
</section>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
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
<script src="/resources/petsitting-master/js/google-map.js"></script>
<script src="/resources/petsitting-master/js/main.js"></script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</html>