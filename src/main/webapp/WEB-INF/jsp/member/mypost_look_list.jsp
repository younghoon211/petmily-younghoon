<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Petmily - Don't buy, Do Adopt</title>

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
                <h1 class="mb-0 bread">내가 쓴 게시글</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">

        <!-- 라디오체크 게시판 선택 -->
        <div style="text-align: center">
            <input type="radio" id="find" name="type" value="find" onclick="redirectTo('/member/auth/myPost/find')"
            <c:if test="${type eq 'find'}">
                   checked
            </c:if>>
            <label for="find">반려동물 찾아요</label>
            &nbsp;&nbsp;&nbsp;

            <input type="radio" id="look" name="type" value="look" onclick="redirectTo('/member/auth/myPost/look')"
            <c:if test="${type eq 'look'}">
                   checked
            </c:if>> <label for="look">유기동물 봤어요</label>
            &nbsp;&nbsp;&nbsp;

            <input type="radio" id="freeBoard" name="type" value="freeBoard"
                   onclick="redirectTo('/member/auth/myPost/board?kindOfBoard=자유')"
            <c:if test="${type eq 'board' && param.kindOfBoard eq '자유'}">
                   checked
            </c:if>>
            <label for="freeBoard">자유게시판</label>
            &nbsp;&nbsp;&nbsp;

            <input type="radio" id="inquiryBoard" name="type" value="inquiryBoard"
                   onclick="redirectTo('/member/auth/myPost/board?kindOfBoard=문의')"
            <c:if test="${type eq 'board' && param.kindOfBoard eq '문의'}">
                   checked
            </c:if>>
            <label for="inquiryBoard">문의게시판</label>
            &nbsp;&nbsp;&nbsp;

            <input type="radio" id="adoptReview" name="type" onclick="redirectTo('/member/auth/myPost/입양후기')"
            <c:if test="${type eq '입양후기'}">
                   checked
            </c:if>>
            <label for="adoptReview">입양후기 게시판</label>
            &nbsp;&nbsp;&nbsp;
        </div>

        <br>
        <div class="inner-main-body p-2 p-sm-3 collapse forum-content show">
            <section class="ftco-section bg-light">
                <div class="container">

                    <!-- 목록 출력 -->
                    <div class="row d-flex">
                        <c:forEach var="lookBoard" items="${myPost.content}">
                            <div class="col-md-4 ftco-animate" id="d-flex-out">
                                <div class="blog-entry align-self-stretch" id="d-flex-in">
                                    <a href="/lookBoard/detail?laNumber=${lookBoard.laNumber}"
                                       class="block-20 rounded"
                                       style="background-image: url('/lookBoard/upload?filename=${lookBoard.imgPath}');">
                                    </a>
                                    <div class="text p-4">
                                        <div class="meta mb-2">
                                            <div>종: ${lookBoard.species} / 품종: ${lookBoard.kind}</div>
                                            <br>
                                            <div>상태: ${lookBoard.animalState}</div>
                                            <br>
                                            <div>장소: ${lookBoard.location}</div>
                                            <br>
                                            <div>작성일: ${lookBoard.wrTime}</div>
                                            <br>
                                            <div>작성자: ${lookBoard.name}</div>
                                        </div>
                                        <h3 class="heading">
                                            <a href="/lookBoard/detail?laNumber=${lookBoard.laNumber}">${lookBoard.title}</a>
                                        </h3>
                                        <div class="meta mb-2">
                                            <div>조회수: ${lookBoard.viewCount}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- 페이징 처리 -->
                    <div class="row mt-5">
                        <div class="col text-center">
                            <div class="block-27">
                                <ul>
                                    <li>
                                        <c:if test="${myPost.startPage > 5}">
                                            <a href="/member/auth/myPost/look?pageNo=${myPost.startPage - 5}">&lt;</a>
                                        </c:if>
                                    </li>
                                    <c:forEach var="pNo" begin="${myPost.startPage}" end="${myPost.endPage}">
                                        <c:if test="${myPost.currentPage eq pNo}">
                                            <li class="active">
                                                <a href="/member/auth/myPost/look?pageNo=${pNo}">${pNo}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${myPost.currentPage ne pNo}">
                                            <li>
                                                <a href="/member/auth/myPost/look?pageNo=${pNo}">${pNo}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li>
                                        <c:if test="${myPost.endPage < myPost.totalPages}">
                                            <a href="/member/auth/myPost/look?pageNo=${myPost.startPage + 5}">&gt;</a>
                                        </c:if>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </section>
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

<script>
    function redirectTo(url) {
        window.location.href = url;
    }
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>