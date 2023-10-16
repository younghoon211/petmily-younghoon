<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<!-- 현재 페이지 -->
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <c:if test="${param.kindOfBoard eq 'free'}">
                    <p class="breadcrumbs mb-2">
                        <span class="mr-2"><span>자유롭게 소통하는 게시판입니다<i class="ion-ios-arrow-forward"></i></span></span>
                    </p>
                    <h1 class="mb-0 bread">자유게시판</h1>
                </c:if>
                <c:if test="${param.kindOfBoard eq 'inquiry'}">
                    <p class="breadcrumbs mb-2">
                        <span class="mr-2"><span>문의사항이 있다면 글을 작성해주세요<i class="ion-ios-arrow-forward"></i></span></span>
                    </p>
                    <h1 class="mb-0 bread">문의게시판</h1>
                </c:if>
            </div>
        </div>
    </div>
</section>

<!-- 게시판 List -->
<section class="ftco-section bg-light">
    <div class="container">

        <div class="modal-header">
            <div class="float-left">
                <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=bno&condition=${param.condition}&keyword=${param.keyword}">
                    <button type="button" class="btn btn-primary">최신순</button>
                </a> &nbsp;
                <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=bnoAsc&condition=${param.condition}&keyword=${param.keyword}">
                    <button type="button" class="btn btn-primary">오래된순</button>
                </a> &nbsp;
                <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=viewCount&condition=${param.condition}&keyword=${param.keyword}">
                    <button type="button" class="btn btn-primary">조회순</button>
                </a> &nbsp;
            </div>
        </div> &nbsp; &nbsp;

        <br class="inner-main-body p-2 p-sm-3 collapse forum-content show">

        <!-- 목록 출력 -->
        <c:forEach var="board" items="${pageForm.content}">
            <div class="card mb-2">
                <div class="card-body p-2 p-sm-3">
                    <div class="media forum-item">

                        <!-- 글 번호 -->
                        <div class="media-body">
                            <small><i class="far fa-eye"></i> 글번호: <c:out value="${board.getBNumber()}"/></small>

                            <!-- 제목 -->
                            <div class="text-secondary">
                                <c:if test="${param.kindOfBoard eq 'free'}">
                                    <a href="<c:out value='/board/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}'/>"
                                       class="text-body" style="font-size: 1.3em;"><c:out value="${board.title}"/></a>
                                    <span style="font-size: 0.9em; color: red"><c:out value="[${board.replyCount}]"/></span>
                                </c:if>
                                <c:if test="${param.kindOfBoard eq 'inquiry'}">
                                    <c:choose>
                                        <c:when test="${authUser.grade ne '관리자' and authUser.getMNumber() ne board.getMNumber() and board.checkPublic eq 'N'}">
                                            <a class="text-body" style="font-size: 1.3em;"><c:out value="${board.title}"/></a>
                                            <span style="font-size: 0.9em; color: red"><c:out value="[${board.replyCount}]"/></span>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="<c:out value='/board/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}'/>"
                                               class="text-body" style="font-size: 1.3em;"><c:out value="${board.title}"/></a>
                                            <span style="font-size: 0.9em; color: red"><c:out value="[${board.replyCount}]"/></span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </div>

                            <!-- 작성자, 작성 날짜 -->
                            <div class="text-muted">
                                <small><a href="javascript:void(0)"><c:out value="${board.name}"/></a>&nbsp;&nbsp;&nbsp;<c:out value="${board.wrTime}"/>
                                </small>
                            </div>
                        </div>

                        <!-- list 공개 / 비공개 -->
                        <div class="text-muted small text-center align-self-center">
                            <c:if test="${param.kindOfBoard eq 'inquiry' && board.checkPublic eq 'N'}">
                                &#x1F512; 비공개
                            </c:if>
                            <div>조회수: <c:out value="${board.viewCount}"/></div>
                        </div>

                    </div>
                </div>
            </div>
        </c:forEach>

        <!-- 글쓰기 버튼  -->
        <br>
        <span class="modal-footer">
				<button type="button" class="btn btn-primary"
                        onclick="window.location.href='<c:out value="/board/auth/write?kindOfBoard=${param.kindOfBoard}"/>'">글쓰기</button>
			</span>

        <!-- 조건부 검색 -->
        <div style="display: flex; justify-content: center;">
            <form action="/board/list" method="get">
                <div class="form-group row">
                    <div class="col">
                        <select name="condition" class="form-control">
                            <option value="title" <c:if test="${param.condition eq 'title'}">selected</c:if>>제목</option>
                            <option value="content" <c:if test="${param.condition eq 'content'}">selected</c:if>>내용
                            </option>
                            <option value="titleAndContent"
                                    <c:if test="${param.condition eq 'titleAndContent'}">selected</c:if>>제목+내용
                            </option>
                            <option value="writer" <c:if test="${param.condition eq 'writer'}">selected</c:if>>작성자
                            </option>
                        </select>
                    </div>

                    <div class="col">
                        <input type="text" name="keyword" class="form-control" placeholder="검색어"
                               value="${keyword eq 'allKeyword' ? '' : param.keyword}">
                    </div>

                    <div class="col-md-auto">
                        <button type="submit" class="btn btn-primary">검색</button>
                    </div>
                </div>

                <input name="kindOfBoard" value="${param.kindOfBoard}" hidden>
                <input name="sort" value="${param.sort}" hidden>
            </form>
        </div>

        <!-- 페이징 처리 -->
        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <!-- 조건부 검색에 조건 또는 검색값 중 하나라도 있을 시 -->
                        <c:if test="${not empty param.condition || not empty param.keyword}">
                            <li>
                                <c:if test="${pageForm.startPage > 5}">
                                    <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&condition=${param.condition}&keyword=${param.keyword}&pageNo=${pageForm.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <c:forEach var="pageNo" begin="${pageForm.startPage}" end="${pageForm.endPage}">
                                <c:if test="${pageForm.currentPage eq pageNo}">
                                    <li class="active">
                                        <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&condition=${param.condition}&keyword=${param.keyword}&pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageForm.currentPage ne pageNo}">
                                    <li>
                                        <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&condition=${param.condition}&keyword=${param.keyword}&pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li>
                                <c:if test="${pageForm.endPage < pageForm.totalPages}">
                                    <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&condition=${param.condition}&keyword=${param.keyword}&pageNo=${pageForm.startPage + 5}">&gt;</a>
                                </c:if>
                            </li>
                        </c:if>

                        <!-- 조건부 검색에 조건, 검색값 둘다 없을 시 -->
                        <c:if test="${empty param.condition && empty param.keyword}">
                            <li>
                                <c:if test="${pageForm.startPage > 5}">
                                    <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&pageNo=${pageForm.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <c:forEach var="pageNo" begin="${pageForm.startPage}" end="${pageForm.endPage}">
                                <c:if test="${pageForm.currentPage eq pageNo}">
                                    <li class="active">
                                        <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageForm.currentPage ne pageNo}">
                                    <li>
                                        <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li>
                                <c:if test="${pageForm.endPage < pageForm.totalPages}">
                                    <a href="/board/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&pageNo=${pageForm.startPage + 5}">&gt;</a>
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

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>