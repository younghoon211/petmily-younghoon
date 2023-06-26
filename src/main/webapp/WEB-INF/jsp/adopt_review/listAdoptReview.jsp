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


<!-- 헤더 -->
<%@ include file="/WEB-INF/jsp/include/header.jspf" %>

<!-- 현재 페이지 -->
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('/resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <c:if test="${param.kindOfBoard eq '입양후기'}">
                    <p class="breadcrumbs mb-2">
                        <span class="mr-2"><span>Adopt Review Board<i class="ion-ios-arrow-forward"></i></span></span>
                    </p>
                    <h1 class="mb-0 bread">입양후기 게시판</h1>
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
                <a href="/adopt_review/list?kindOfBoard=입양후기&sort=adoptReviewNo&searchType=${param.searchType}&keyword=${param.keyword}">
                    <button class="btn btn-primary" type="button">최신순</button>
                </a> &nbsp;
                <a href="/adopt_review/list?kindOfBoard=입양후기&sort=adoptReviewNoAsc&searchType=${param.searchType}&keyword=${param.keyword}">
                    <button class="btn btn-primary" type="button">오래된순</button>
                </a> &nbsp;
                <a href="/adopt_review/list?kindOfBoard=입양후기&sort=viewCount&searchType=${param.searchType}&keyword=${param.keyword}">
                    <button class="btn btn-primary" type="button">조회순</button>
                </a> &nbsp;
            </div>
        </div> &nbsp; &nbsp;

        <br class="inner-main-body p-2 p-sm-3 collapse forum-content show">
        <div class="row d-flex">
            <c:forEach var="board" items="${boardList.content}">
                <div class="col-md-4">
                    <div class="blog-entry align-self-stretch">
                        <a href="/adopt_review/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}"
                           class="block-20 rounded"
                           style="background-image: url('/adopt_review/upload?filename=${board.imgPath}')"></a>
                        <div class="text p-4">
                            <div class="meta mb-2">
                                <div><i class="far fa-eye"></i>date ${board.wrTime}</div>
                                <br/>
                                <div><i class="far fa-eye"></i>by<a href="javascript:void(0)"> ${board.name}</a></div>
                            </div>
                            <a href="/adopt_review/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}">
                                <h3 class="heading">${board.title}</h3></a>
                            <div class="meta mb-2">
                                <div>조회수: ${board.viewCount}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- 글쓰기 버튼  -->
        <span class="modal-footer">
      	<button type="button" class="btn btn-primary"
                onclick="location.href='/adopt_review/auth/write?kindOfBoard=${param.kindOfBoard}'">글쓰기</button>
    </span>

        <!-- 검색 바 -->
        <div style="display: flex; justify-content: center;">
            <form action="/adopt_review/list" method="get">
                <div class="form-group row">
                    <input type="hidden" name="kindOfBoard" value="입양후기">
                    <input type="hidden" name="sort" value="${param.sort}"/>

                    <div class="col">
                        <select name="searchType" class="form-control">
                            <option value="title" <c:if test="${param.searchType == 'title'}">selected</c:if>>제목
                            </option>
                            <option value="content" <c:if test="${param.searchType == 'content'}">selected</c:if>>내용
                            </option>
                            <option value="titleAndContent"
                                    <c:if test="${param.searchType == 'titleAndContent'}">selected</c:if>>제목+내용
                            </option>
                            <option value="writer" <c:if test="${param.searchType == 'writer'}">selected</c:if>>작성자
                            </option>
                        </select>
                    </div>

                    <div class="col">
                        <input type="text" name="keyword" class="form-control" placeholder="검색어"
                               value="${keyword eq 'allKeyword' ? '' : param.keyword}">
                    </div>

                    <div class="col">
                        <input type="submit" class="btn btn-primary" value="검색">
                    </div>
                </div>
            </form>
        </div>

        <!-- 페이징 처리 -->
        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <!-- 조건부 검색에 조건 또는 검색값 중 하나라도 있을 시 -->
                        <c:if test="${not empty param.searchType || not empty param.keyword}">
                            <li>
                                <c:if test="${boardList.startPage > 5}">
                                    <a href="${pageContext.request.contextPath}/adopt_review/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&searchType=${param.searchType}&keyword=${param.keyword}&pbNumber=${boardList.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <li>
                            <c:forEach var="pbNum" begin="${boardList.startPage}" end="${boardList.endPage}">
                                <c:if test="${boardList.currentPage == pbNum}">
                                    <li class="active">
                                        <a href="${pageContext.request.contextPath}/adopt_review/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&searchType=${param.searchType}&keyword=${param.keyword}&pbNumber=${pbNum}">${pbNum}</a>
                                    </li>
                                </c:if>
                                <c:if test="${boardList.currentPage != pbNum}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/adopt_review/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&searchType=${param.searchType}&keyword=${param.keyword}&pbNumber=${pbNum}">${pbNum}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            </li>
                            <li>
                                <c:if test="${boardList.endPage < boardList.totalPages}">
                                    <a href="${pageContext.request.contextPath}/adopt_review/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&searchType=${param.searchType}&keyword=${param.keyword}&pbNumber=${boardList.startPage + 5}">&gt;</a>
                                </c:if>
                            </li>
                        </c:if>

                        <!-- 조건부 검색에 조건, 검색값 전부 없을 시 -->
                        <c:if test="${empty param.searchType && empty param.keyword}">
                            <li>
                                <c:if test="${boardList.startPage > 5}">
                                    <a href="${pageContext.request.contextPath}/adopt_review/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&pbNumber=${boardList.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <li>
                            <c:forEach var="pbNum" begin="${boardList.startPage}" end="${boardList.endPage}">
                                <c:if test="${boardList.currentPage == pbNum}">
                                    <li class="active">
                                        <a href="${pageContext.request.contextPath}/adopt_review/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&pbNumber=${pbNum}">${pbNum}</a>
                                    </li>
                                </c:if>
                                <c:if test="${boardList.currentPage != pbNum}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/adopt_review/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&pbNumber=${pbNum}">${pbNum}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            </li>
                            <li>
                                <c:if test="${boardList.endPage < boardList.totalPages}">
                                    <a href="${pageContext.request.contextPath}/adopt_review/list?kindOfBoard=${param.kindOfBoard}&sort=${param.sort}&pbNumber=${boardList.startPage + 5}">&gt;</a>
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

<!-- 풋터 -->
<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>

<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"/>
    </svg>
</div>

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

</body>
</html>