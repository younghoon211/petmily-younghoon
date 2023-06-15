<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Petmily - Don't buy, Do Adopt</title>

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

<%@ include file="/WEB-INF/jsp/include/header.jspf" %>

<section class="hero-wrap hero-wrap-2"
         style="background-image: url('/resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <p class="breadcrumbs mb-2"><span>Look Animal Board - 현재 보호 중인 동물이 있다면 글을 작성해주세요<i class="ion-ios-arrow-forward"></i></span></p>
                <h1 class="mb-0 bread">유기동물 봤어요</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="modal-header">

            <!-- 검색 바 -->
            <form action="/lookBoard/list" method="get">
                <div class="form-group row">

                    <div class="col">
                        <select name="species" class="form-control">
                            <c:forEach var="animal" items="${['allSpecies', '개', '고양이', '기타']}">
                                <option value="${animal}" <c:if test="${species == animal}">selected</c:if>>
                                    <c:out value="${animal eq 'allSpecies' ? '모든 동물' : animal}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <select name="animalState" class="form-control">
                            <c:forEach var="state" items="${['allAnimalState', '실종', '매칭됨', '완료']}">
                                <option value="${state}" <c:if test="${animalState == state}">selected</c:if>>
                                    <c:out value="${state eq 'allAnimalState' ? '모든 상태' : state}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <input type="text" name="keyword" class="form-control" placeholder="검색어"
                               value="${keyword eq 'allKeyword' ? '' : keyword}">
                    </div>

                    <div class="col">
                        <input type="submit" class="btn btn-primary" value="검색">
                    </div>

                </div>
            </form>

        </div>
        <br>

        <div class="row d-flex">
            <c:forEach var="lookBoard" items="${Looks.content}">
                <div class="col-md-4 ftco-animate" id="d-flex-out">
                    <div class="blog-entry align-self-stretch" id="d-flex-in">
                        <a href="${pageContext.request.contextPath}/lookBoard/detail?laNumber=${lookBoard.laNumber}"
                           class="block-20 rounded"
                           style="background-image: url('/lookBoard/upload/?filename=${lookBoard.imgPath}');">
                        </a>
                        <div class="text p-4">
                            <div class="meta mb-2">
                                <div>종: ${lookBoard.species}</div>
                                <br/>
                                <div>상태: ${lookBoard.animalState}</div>
                                <br/>
                                <div>장소: ${lookBoard.location}</div>
                                <br/>
                                <div>작성일: ${lookBoard.wrTime}</div>
                                <br/>
                                <div>작성자: ${lookBoard.name}</div>
                            </div>
                            <h3 class="heading"><a href="${pageContext.request.contextPath}/lookBoard/detail?laNumber=${lookBoard.laNumber}">${lookBoard.title}</a></h3>
                            <div class="meta mb-2">
                                <div>조회수: ${lookBoard.viewCount}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <span class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="location.href='/lookBoard/auth/write'">글쓰기</button>
        </span>


        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <li>
                            <c:if test="${Looks.startPage > 5}">
                                <a href="${pageContext.request.contextPath}/lookBoard/list?pageNo=${Looks.startPage - 5}">&lt;</a>
                            </c:if>
                        </li>
                        <c:forEach var="pNo" begin="${Looks.startPage}" end="${Looks.endPage}">
                            <c:if test="${Looks.currentPage == pNo}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/lookBoard/list?pageNo=${pNo}">${pNo}</a>
                                </li>
                            </c:if>
                            <c:if test="${Looks.currentPage != pNo}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/lookBoard/list?pageNo=${pNo}">${pNo}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <li>
                            <c:if test="${Looks.endPage < Looks.totalPages}">
                                <a href="${pageContext.request.contextPath}/lookBoard/list?pageNo=${Looks.startPage + 5}">&gt;</a>
                            </c:if>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>

<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"></circle>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"></circle>
    </svg>
</div>

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

</html>