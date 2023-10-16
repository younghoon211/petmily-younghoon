<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <c:if test="${param.kindOfBoard eq 'free'}">
                   checked
            </c:if>>
            <label for="free">자유게시판 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="inquiry" value="inquiry" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq 'inquiry'}">
                   checked
            </c:if>>
            <label for="inquiry">문의게시판 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="review" value="review" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq 'adoptReview'}">
                   checked
            </c:if>>
            <label for="review">입양후기 게시판 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="find" value="find" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq 'find'}">
                   checked
            </c:if>>
            <label for="find">반려동물 찾아요 게시판 관리</label>
            &nbsp;&nbsp;&nbsp;
            <input type="radio" id="look" value="look" onclick="selectKind(this.value)"
            <c:if test="${param.kindOfBoard eq 'look'}">
                   checked
            </c:if>> <label for="look">유기동물 봤어요 게시판 관리</label>
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

                                    <c:if test="${param.kindOfBoard eq 'free' || param.kindOfBoard eq 'inquiry'}">
                                        <td>${board.getBNumber()}</td>
                                        <td>
                                            <a href="/board/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}"
                                               class="text-body">
                                                    <c:out value="${board.title}"/>
                                            </a>
                                        </td>
                                    </c:if>

                                    <c:if test="${param.kindOfBoard eq 'adoptReview'}">
                                        <td>${board.getBNumber()}</td>
                                        <td>
                                            <a href="/adoptReview/detail?kindOfBoard=adoptReview&bNumber=${board.getBNumber()}"
                                               class="text-body">
                                                    <c:out value="${board.title}"/>
                                            </a>
                                        </td>
                                    </c:if>

                                    <c:if test="${param.kindOfBoard eq 'find'}">
                                        <td>${board.faNumber}</td>
                                        <td>
                                            <a href="/findBoard/detail?faNumber=${board.faNumber}" class="text-body">
                                                    <c:out value="${board.title}"/>
                                            </a>
                                        </td>
                                    </c:if>

                                    <c:if test="${param.kindOfBoard eq 'look'}">
                                        <td>${board.laNumber}</td>
                                        <td>
                                            <a href="/lookBoard/detail?laNumber=${board.laNumber}" class="text-body">
                                                    <c:out value="${board.title}"/>
                                            </a>
                                        </td>
                                    </c:if>
                                    <td><c:out value="${board.memberId} (${board.getMNumber()})"/></td>
                                    <td><c:out value="${board.name}"/></td>
                                    <td><c:out value="${board.wrTime}"/></td>
                                    <td>
                                        <c:if test="${param.kindOfBoard eq 'free' || param.kindOfBoard eq 'inquiry'}">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="window.location.href='/board/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}'">
                                                상세
                                            </button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="window.location.href='/board/auth/modify?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}'">
                                                수정
                                            </button>
                                            <button type="button" class="btn btn-danger"
                                                    onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                            { return window.location.href='/board/auth/delete?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}';}">
                                                삭제
                                            </button>
                                        </c:if>
                                        <c:if test="${param.kindOfBoard eq 'adoptReview'}">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="window.location.href='/adoptReview/detail?kindOfBoard=adoptReview&bNumber=${board.getBNumber()}'">
                                                상세
                                            </button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="window.location.href='/adoptReview/auth/modify?kindOfBoard=adoptReview&bNumber=${board.getBNumber()}'">
                                                수정
                                            </button>
                                            <button type="button" class="btn btn-danger"
                                                    onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                            { return window.location.href='/adoptReview/auth/delete?kindOfBoard=adoptReview&bNumber=${board.getBNumber()}';}">
                                                삭제
                                            </button>
                                        </c:if>
                                        <c:if test="${param.kindOfBoard eq 'find'}">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="window.location.href='/findBoard/detail?faNumber=${board.faNumber}'">
                                                상세
                                            </button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="window.location.href='/findBoard/auth/modify?faNumber=${board.faNumber}'">
                                                수정
                                            </button>
                                            <button type="button" class="btn btn-danger"
                                                    onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                            { return window.location.href='/findBoard/auth/delete?faNumber=${board.faNumber}';}">
                                                삭제
                                            </button>
                                        </c:if>
                                        <c:if test="${param.kindOfBoard eq 'look'}">
                                            <button type="button" class="btn btn-dark"
                                                    onclick="window.location.href='/lookBoard/detail?laNumber=${board.laNumber}'">
                                                상세
                                            </button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="window.location.href='/lookBoard/auth/modify?laNumber=${board.laNumber}'">
                                                수정
                                            </button>
                                            <button type="button" class="btn btn-danger"
                                                    onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                            { return window.location.href='/lookBoard/auth/delete?laNumber=${board.laNumber}';}">
                                                삭제
                                            </button>
                                        </c:if>
                                    </td>
                                </tr>
                                </tbody>
                            </c:forEach>

                        </table>

                        <div class="modal-footer">
                            <!-- 돌아가기 -->
                            <button type="button" class="btn btn-dark" onclick="window.location.href='/admin'">
                                관리자 페이지로
                            </button>

                            <!-- 글쓰기 -->
                            <button type="button" class="btn btn-primary" style="float: right"
                                    <c:if test="${param.kindOfBoard eq 'free' || param.kindOfBoard eq 'inquiry'}">
                                        onclick="window.location.href='/board/auth/write?kindOfBoard=${param.kindOfBoard}'"
                                    </c:if>
                                    <c:if test="${param.kindOfBoard eq 'adoptReview'}">
                                        onclick="window.location.href='/adoptReview/auth/write?kindOfBoard=adoptReview'"
                                    </c:if>
                                    <c:if test="${param.kindOfBoard eq 'find'}">
                                        onclick="window.location.href='/findBoard/auth/write'"
                                    </c:if>
                                    <c:if test="${param.kindOfBoard eq 'look'}">
                                        onclick="window.location.href='/lookBoard/auth/write'"
                                    </c:if>>글쓰기
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 검색 -->
        <div style="display: flex; justify-content: center;">
            <form action="/admin/board" method="get">
                <input name="kindOfBoard" value="${param.kindOfBoard}" hidden>
                <div class="form-group row">

                    <c:if test="${param.kindOfBoard eq 'free' || param.kindOfBoard eq 'inquiry' || param.kindOfBoard eq 'adoptReview'}">
                        <div class="col">
                            <select name="condition" class="form-control">
                                <option value="title" <c:if test="${param.condition eq 'title'}">selected</c:if>>제목
                                </option>
                                <option value="content" <c:if test="${param.condition eq 'content'}">selected</c:if>>내용
                                </option>
                                <option value="titleAndContent"
                                        <c:if test="${param.condition eq 'titleAndContent'}">selected</c:if>>제목+내용
                                </option>
                                <option value="writer" <c:if test="${param.condition eq 'writer'}">selected</c:if>>작성자
                                </option>
                            </select>
                        </div>
                    </c:if>

                    <c:if test="${param.kindOfBoard eq 'find' || param.kindOfBoard eq 'look'}">
                        <div class="col">
                            <select name="species" class="form-control">
                                <c:forEach var="animal" items="${['allSpecies', '개', '고양이', '기타']}">
                                    <option value="${animal}" <c:if test="${param.species eq animal}">selected</c:if>>
                                        <c:out value="${animal eq 'allSpecies' ? '모든 동물' : animal}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>

                    <c:if test="${param.kindOfBoard eq 'find'}">
                        <div class="col">
                            <select name="animalState" class="form-control">
                                <c:forEach var="state" items="${['allAnimalState', '실종', '매칭됨', '완료']}">
                                    <option value="${state}" <c:if test="${param.animalState eq state}">selected</c:if>>
                                        <c:out value="${state eq 'allAnimalState' ? '모든 상태' : state}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>

                    <c:if test="${param.kindOfBoard eq 'look'}">
                        <div class="col">
                            <select name="animalState" class="form-control">
                                <c:forEach var="state" items="${['allAnimalState', '보호', '매칭됨', '완료']}">
                                    <option value="${state}" <c:if test="${param.animalState eq state}">selected</c:if>>
                                        <c:out value="${state eq 'allAnimalState' ? '모든 상태' : state}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>

                    <div class="col">
                        <input type="text" name="keyword" class="form-control" placeholder="검색어"
                               value="${keyword eq 'allKeyword' ? '' : param.keyword}">
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
                        <!-- 조건부 검색에 조건 또는 검색값 중 하나라도 있을 시 -->
                        <c:if test="${not empty param.species || not empty param.animalState || not empty param.keyword}">
                            <li>
                                <c:if test="${boardForm.startPage > 5}">
                                    <a href="/admin/board?kindOfBoard=${param.kindOfBoard}&species=${param.species}&animalState=${param.animalState}&keyword=${param.keyword}&pageNo=${boardForm.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <c:forEach var="pageNo" begin="${boardForm.startPage}" end="${boardForm.endPage}">
                                <c:if test="${boardForm.currentPage eq pageNo}">
                                    <li class="active">
                                        <a href="/admin/board?kindOfBoard=${param.kindOfBoard}&species=${param.species}&animalState=${param.animalState}&keyword=${param.keyword}&pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                                <c:if test="${boardForm.currentPage ne pageNo}">
                                    <li>
                                        <a href="/admin/board?kindOfBoard=${param.kindOfBoard}&species=${param.species}&animalState=${param.animalState}&keyword=${param.keyword}&pageNo=${pageNo}">${pageNo}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li>
                                <c:if test="${boardForm.endPage < boardForm.totalPages}">
                                    <a href="/admin/board?kindOfBoard=${param.kindOfBoard}&species=${param.species}&animalState=${param.animalState}&keyword=${param.keyword}&pageNo=${boardForm.startPage + 5}">&gt;</a>
                                </c:if>
                            </li>
                        </c:if>

                        <!-- 조건부 검색에 조건, 검색값 전부 없을 시 -->
                        <c:if test="${empty param.species && empty param.animalState && empty param.keyword}">
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
        if (kind == "free") {
            window.location.href = "/admin/board?kindOfBoard=free";
        } else if (kind == "inquiry") {
            window.location.href = "/admin/board?kindOfBoard=inquiry";
        } else if (kind == "review") {
            window.location.href = "/admin/board?kindOfBoard=adoptReview";
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