<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <h1 class="mb-0 bread">게시판 관리</h1>
            </div>
        </div>
    </div>
</section>

<!-- 게시판 List -->

<section class="ftco-section bg-light">
    <div class="container" style="max-width: 1600px;">
        <div style="text-align: center">
            <input type="radio" id="free" value="free" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq '자유'}">
                   checked
            </c:if>>
            <label for="free">자유 게시판 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="inquiry" value="inquiry" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq '문의'}">
                   checked
            </c:if>>
            <label for="inquiry">문의 게시판 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="review" value="review" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq '입양후기'}">
                   checked
            </c:if>>
            <label for="review">입양 후기 게시판 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="find" value="find" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq 'find'}">
                   checked
            </c:if>>
            <label for="find">반려 동물 찾아요 게시판 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="look" value="look" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq 'look'}">
                   checked
            </c:if>> <label for="look">유기 동물 봤어요 게시판 관리</label>
        </div>
        <div class="inner-main-body p-2 p-sm-3 collapse forum-content show">
            <!-- 목록 출력 -->
            <div class="row align-items-start">
                <div class="col-lg-12">
                    <div class="col text-center">
                        <table class="table table-hover bg-white">

                            <thead>
                            <tr class="table table-border">
                                <th>글번호</th>
                                <th>제목</th>
                                <th>아이디 (회원번호)</th>
                                <th>닉네임</th>
                                <th>작성일시</th>
                                <th>관리 버튼</th>
                            </tr>
                            </thead>

                            <c:forEach var="board" items="${boardForm.content}">
                                <tbody>
                                <tr>

                                    <c:if test="${param.kindOfBoard eq '자유' || param.kindOfBoard eq '문의'}">
                                        <td>${board.getBNumber()}</td>
                                        <td>
                                            <a href="/board/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}"
                                               class="text-body">
                                                    ${board.title}
                                            </a>
                                        </td>
                                    </c:if>

                                    <c:if test="${param.kindOfBoard eq '입양후기'}">
                                        <td>${board.getBNumber()}</td>
                                        <td>
                                            <a href="/adopt_review/detail?kindOfBoard=입양후기&bNumber=${board.getBNumber()}"
                                               class="text-body">
                                                    ${board.title}
                                            </a>
                                        </td>
                                    </c:if>

                                    <c:if test="${param.kindOfBoard eq 'find'}">
                                        <td>${board.faNumber}</td>
                                        <td>
                                            <a href="/findBoard/detail?faNumber=${board.faNumber}" class="text-body">
                                                    ${board.title}
                                            </a>
                                        </td>
                                    </c:if>

                                    <c:if test="${param.kindOfBoard eq 'look'}">
                                        <td>${board.laNumber}</td>
                                        <td>
                                            <a href="/lookBoard/detail?laNumber=${board.laNumber}" class="text-body">
                                                    ${board.title}
                                            </a>
                                        </td>
                                    </c:if>
                                    <td>${board.memberId} (${board.getMNumber()})</td>
                                    <td>${board.name}</td>
                                    <td>${board.wrTime}</td>
                                    <td>
                                        <c:if test="${param.kindOfBoard eq '자유' || param.kindOfBoard eq '문의'}">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="location.href='/board/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}'">상세</button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="location.href='/board/auth/modify?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}'">수정</button>
                                            <button type="button" class="btn btn-danger"
                                                   onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                           { return location.href='/board/auth/delete?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}';}">삭제</button>
                                        </c:if>
                                        <c:if test="${param.kindOfBoard eq '입양후기'}">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="location.href='/adopt_review/detail?kindOfBoard=입양후기&bNumber=${board.getBNumber()}'">상세</button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="location.href='/adopt_review/auth/modify?kindOfBoard=입양후기&bNumber=${board.getBNumber()}'">수정</button>
                                            <button type="button" class="btn btn-danger"
                                                   onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                           { return location.href='/adopt_review/auth/delete?kindOfBoard=입양후기&bNumber=${board.getBNumber()}';}">삭제</button>
                                        </c:if>
                                        <c:if test="${param.kindOfBoard eq 'find'}">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="location.href='/findBoard/detail?faNumber=${board.faNumber}'">상세</button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="location.href='/findBoard/auth/modify?faNumber=${board.faNumber}'">수정</button>
                                            <button type="button" class="btn btn-danger"
                                                   onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                           { return location.href='/findBoard/auth/delete?faNumber=${board.faNumber}';}">삭제</button>
                                        </c:if>
                                        <c:if test="${param.kindOfBoard eq 'look'}">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="location.href='/lookBoard/detail?laNumber=${board.laNumber}'">상세</button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="location.href='/lookBoard/auth/modify?laNumber=${board.laNumber}'">수정</button>
                                            <button type="button" class="btn btn-danger"
                                                   onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                           { return location.href='/lookBoard/auth/delete?laNumber=${board.laNumber}';}">삭제</button>
                                        </c:if>
                                    </td>
                                </tr>
                                </tbody>
                            </c:forEach>

                        </table>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" onclick="location.href='/admin'">
                                관리자 페이지로
                            </button>
                            <button type="button" class="btn btn-primary" style="float: right"
                                    <c:if test="${param.kindOfBoard eq '자유' || param.kindOfBoard eq '문의'}">
                                        onclick="location.href='/board/auth/write?kindOfBoard=${param.kindOfBoard}'"
                                    </c:if>
                                    <c:if test="${param.kindOfBoard eq '입양후기'}">
                                        onclick="location.href='/adopt_review/auth/write?kindOfBoard=입양후기'"
                                    </c:if>
                                    <c:if test="${param.kindOfBoard eq 'find'}">
                                        onclick="location.href='/findBoard/auth/write'"
                                    </c:if>
                                    <c:if test="${param.kindOfBoard eq 'look'}">
                                        onclick="location.href='/lookBoard/auth/write'"
                                    </c:if>>글쓰기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 페이징 처리 -->
        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <li>
                            <c:if test="${boardForm.startPage > 5}">
                                <a href="/admin/board?kindOfBoard=${param.kindOfBoard}&pageNo=${boardForm.startPage - 5}">&lt;</a>
                            </c:if>
                        </li>
                        <c:forEach var="pageNo" begin="${boardForm.startPage}" end="${boardForm.endPage}">
                            <c:if test="${boardForm.currentPage eq pageNo}">
                                <li class="active">
                                    <a href="/admin/board?kindOfBoard=${param.kindOfBoard}&pageNo=${pageNo}">${pageNo}</a>
                                </li>
                            </c:if>
                            <c:if test="${boardForm.currentPage ne pageNo}">
                                <li>
                                    <a href="/admin/board?kindOfBoard=${param.kindOfBoard}&pageNo=${pageNo}">${pageNo}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <li>
                            <c:if test="${boardForm.endPage < boardForm.totalPages}">
                                <a href="/admin/board?kindOfBoard=${param.kindOfBoard}&pageNo=${boardForm.startPage + 5}">&gt;</a>
                            </c:if>
                        </li>
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
        if (kind == "free") {
            window.location.href = "/admin/board?kindOfBoard=자유";
        } else if (kind == "inquiry") {
            window.location.href = "/admin/board?kindOfBoard=문의";
        } else if (kind == "review") {
            window.location.href = "/admin/board?kindOfBoard=입양후기";
        } else if (kind == "find") {
            window.location.href = "/admin/board?kindOfBoard=find";
        } else if (kind == "look") {
            window.location.href = "/admin/board?kindOfBoard=look";
        }
    }
</script>

<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>
</body>
</html>