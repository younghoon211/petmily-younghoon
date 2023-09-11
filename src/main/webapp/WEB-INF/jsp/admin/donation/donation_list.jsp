<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <link rel="stylesheet" href="/css/bootstrap.css">
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
<%@ include file="../../include/header.jspf" %>

<%-- 현재 페이지 --%>
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../../resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <h1 class="mb-0 bread">후원 관리</h1>
            </div>
        </div>
    </div>
</section>

<!-- 후원 관리 List -->
<section class="ftco-section bg-light">
    <div class="container" style="max-width: 1600px;">

        <!-- 목록 출력 -->
        <div class="row align-items-center">
            <div class="col-lg-12">
                <div class="col text-center">
                    <table class="table table-hover bg-white">

                        <thead>
                        <tr class="table table-border">
                            <th>후원번호</th>
                            <th>후원받은 동물 (번호)</th>
                            <th>아이디 (번호)</th>
                            <th>닉네임</th>
                            <th>후원금액</th>
                            <th>은행</th>
                            <th>예금주</th>
                            <th>계좌번호</th>
                            <th>관리버튼</th>
                        </tr>
                        </thead>
                        <c:forEach var="donation" items="${pageForm.content}">
                            <tbody>

                            <tr>
                                <td>${donation.getDNumber()}</td>
                                <td>${donation.animalName} (${donation.abNumber})</td>
                                <td>${donation.memberId} (${donation.getMNumber()})</td>
                                <td>${donation.memberName}</td>
                                <td><fmt:formatNumber value="${donation.donaSum}" pattern="#,##0" /></td>
                                <td>${donation.bank}</td>
                                <td>${donation.accountHolder}</td>
                                <td>${donation.accountNumber}</td>
                                <td>
                                    <button type="button" class="btn btn-primary"
                                            onclick="location.href='/admin/donation/update?dNumber=${donation.getDNumber()}'">
                                        수정
                                    </button>
                                    <button type="button" class="btn btn-danger"
                                            onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                    { return location.href='/admin/donation/delete?dNumber=${donation.getDNumber()}';}">
                                        삭제
                                    </button>
                                </td>
                            </tr>

                            </tbody>

                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <!-- 생성, 관리자 페이지 이동 버튼  -->
        <div class="modal-footer">
            <button type="button" class="btn btn-dark" onclick="location.href='/admin'">
                관리자 페이지로
            </button>
            <button type="button" class="btn btn-primary"
                    onclick="location.href='/admin/donation/insert'">후원 추가
            </button>
        </div>

        <!-- 검색 -->
        <div style="display: flex; justify-content: center;">
            <form action="/admin/donation" method="get">
                <div class="form-group row">
                    <div class="col">
                        <input type="text" name="keyword" class="form-control" placeholder="검색어"
                               value="${param.keyword eq 'allKeyword' ? '' : param.keyword}">
                    </div>
                    <div class="col-md-auto">
                        <button type="submit" class="btn btn-primary">검색</button>
                    </div>
                </div>
            </form>
        </div>

        <!-- 페이징 처리 -->
        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <li>
                            <c:if test="${pageForm.startPage > 5}">
                                <a href="/admin/donation/?pageNo=${pageForm.startPage - 5}">&lt;</a>
                            </c:if>
                        </li>
                        <li>
                            <c:forEach var="pNo" begin="${pageForm.startPage}"
                                       end="${pageForm.endPage}">
                            <c:if test="${pageForm.currentPage eq pNo}">
                        <li class="active">
                            <a href="/admin/donation?pageNo=${pNo}">${pNo}</a>
                        </li>
                        </c:if>
                        <c:if test="${pageForm.currentPage ne pNo}">
                            <li>
                                <a href="/admin/donation?pageNo=${pNo}">${pNo}</a>
                            </li>
                        </c:if>
                        </c:forEach>
                        </li>
                        <li>
                            <c:if test="${pageForm.endPage < pageForm.totalPages}">
                                <a href="/admin/donation?pageNo=${pageForm.startPage + 5}">&gt;</a>
                            </c:if>
                        </li>
                    </ul>
                </div>
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
<%@ include file="../../include/footer.jspf" %>
</body>
</html>