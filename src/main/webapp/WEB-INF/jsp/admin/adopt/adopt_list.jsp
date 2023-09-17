<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                <h1 class="mb-0 bread">입양 관리</h1>
            </div>
        </div>
    </div>
</section>

<!-- 게시판 List -->

<section class="ftco-section bg-light">
    <div class="container" style="max-width: 1600px;">

        <div style="text-align: center">
            <c:set var="URI" value="${requestScope['javax.servlet.forward.request_uri']}"/>
            <input type="radio" id="adopt" value="adopt" onclick="selectKind(this.value)"
            <c:if test="${URI eq '/admin/adopt'}">
                   checked
            </c:if>>
            <label for="adopt">입양 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="wait" value="wait" onclick="selectKind(this.value)"
            <c:if test="${URI eq '/admin/adopt/wait'}">
                   checked
            </c:if>>
            <label for="wait">입양 승인 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="complete" value="complete" onclick="selectKind(this.value)"
            <c:if test="${URI eq '/admin/adopt/complete'}">
                   checked
            </c:if>>
            <label for="complete">입양 완료된 리스트</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="refuse" value="refuse" onclick="selectKind(this.value)"
            <c:if test="${URI eq '/admin/adopt/refuse'}">
                   checked
            </c:if>>
            <label for="refuse">입양 거절된 리스트</label>
        </div>
        <br>
        <!-- 목록 출력 -->
        <div class="row align-items-center">
            <div class="col-lg-12">
                <div class="col text-center">
                    <table class="table table-hover bg-white">

                        <thead>
                        <tr class="table table-border">
                            <th>입양번호</th>
                            <th>아이디 (번호)</th>
                            <th>동물이름 (번호)</th>
                            <th>닉네임</th>
                            <th>거주지</th>
                            <th>결혼여부</th>
                            <th>직업</th>
                            <th>입양상태</th>
                            <th>관리버튼</th>
                        </tr>
                        </thead>
                        <c:forEach var="adopt" items="${pageForm.content}">
                            <tbody>
                            <td>${adopt.adNumber}</td>
                            <td>${adopt.memberId} (${adopt.getMNumber()})</td>
                            <td>${adopt.animalName} (${adopt.abNumber})</td>
                            <td>${adopt.memberName}</td>
                            <td>${adopt.residence}</td>
                            <td>${adopt.maritalStatus}</td>
                            <td>${adopt.job}</td>
                            <td>${adopt.status}</td>
                            <td>
                                <button type="button" class="btn btn-primary"
                                        onclick="location.href='/admin/adopt/update?adNumber=${adopt.adNumber}'">수정
                                </button>
                                <button type="button" class="btn btn-danger"
                                        onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                { return location.href='/admin/adopt/delete?adNumber=${adopt.adNumber}';}">
                                    삭제
                                </button>
                            </td>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <!-- 입양 추가 버튼  -->
        <div class="modal-footer">
            <button type="button" class="btn btn-dark" onclick="location.href='/admin'">
                관리자 페이지로
            </button>
            <button type="button" class="btn btn-primary"
                    onclick="location.href='/admin/adopt/insert'">입양 추가
            </button>
        </div>

        <!-- 검색 -->
        <div style="display: flex; justify-content: center;">
            <form action="/admin/adopt" method="get">
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

        <!-- 페이징 -->
        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <!-- 검색값 있을 시 -->
                        <c:if test="${not empty param.keyword}">
                            <li>
                                <c:if test="${pageForm.startPage > 5}">
                                    <a href="/admin/adopt?keyword=${param.keyword}&pageNo=${pageForm.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <li>
                            <c:forEach var="pageNo" begin="${pageForm.startPage}" end="${pageForm.endPage}">
                                <c:if test="${pageForm.currentPage eq pageNo}">
                                    <li class="active">
                                        <a href="/admin/adopt?keyword=${param.keyword}&pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageForm.currentPage ne pageNo}">
                                    <li>
                                        <a href="/admin/adopt?keyword=${param.keyword}&pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            </li>
                            <li>
                                <c:if test="${pageForm.endPage < adopt.totalPages}">
                                    <a href="/admin/adopt?keyword=${param.keyword}&pageNo=${pageForm.startPage + 5}">&gt;</a>
                                </c:if>
                            </li>
                        </c:if>

                        <!-- 검색값 없을 시 -->
                        <c:if test="${empty param.keyword}">
                            <li>
                                <c:if test="${pageForm.startPage > 5}">
                                    <a href="/admin/adopt?pageNo=${pageForm.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <li>
                            <c:forEach var="pageNo" begin="${pageForm.startPage}" end="${pageForm.endPage}">
                                <c:if test="${pageForm.currentPage eq pageNo}">
                                    <li class="active">
                                        <a href="/admin/adopt?pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageForm.currentPage ne pageNo}">
                                    <li>
                                        <a href="/admin/adopt?pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            </li>
                            <li>
                                <c:if test="${pageForm.endPage < adopt.totalPages}">
                                    <a href="/admin/adopt?pageNo=${pageForm.startPage + 5}">&gt;</a>
                                </c:if>
                            </li>
                        </c:if>
                    </ul>
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

<script>
    function selectKind(kind) {
        if (kind == "adopt") {
            window.location.href = "/admin/adopt";
        } else if (kind == "wait") {
            window.location.href = "/admin/adopt/wait";
        } else if (kind == "complete") {
            window.location.href = "/admin/adopt/complete";
        } else if (kind == "refuse") {
            window.location.href = "/admin/adopt/refuse";
        }
    }
</script>
<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>
</body>
</html>