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
         style="background-image: url(../../../resources/petsitting-master/images/bg_2.jpg);"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <p class="breadcrumbs mb-2">
                    <span class="mr-2"><span>보호 또는 임시보호중인 동물을 조회하는 게시판입니다<i
                            class="ion-ios-arrow-forward"></i></span></span>
                </p>
                <h1 class="mb-0 bread">유기동물 조회</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container" style="max-width: 1400px;">
        <div class="modal-header">
            <div class="form-group row">
                <div>
                    <c:if test="${empty param.species && empty param.gender && empty param.animalState}">
                        <c:forEach var="sort" items="${['abNo', 'abNoAsc']}">
                            <a href="<c:out value='/abandonedAnimal/list?sort=${sort}'/>">
                                <button type="button" class="btn btn-primary">
                                    <c:choose>
                                        <c:when test="${sort eq 'abNo'}">최신순</c:when>
                                        <c:when test="${sort eq 'abNoAsc'}">오래된순</c:when>
                                    </c:choose>
                                </button>&nbsp;
                            </a>
                        </c:forEach>
                    </c:if>

                    <c:if test="${not empty param.species && not empty param.gender && not empty param.animalState}">
                        <c:set var="linkParams"
                               value="?species=${param.species}&gender=${param.gender}&animalState=${param.animalState}&keyword=${param.keyword}&sort="/>
                        <c:forEach var="sort" items="${['abNo', 'abNoAsc']}">
                            <a href="<c:out value='/abandonedAnimal/list${linkParams}${sort}'/>">
                                <button type="button" class="btn btn-primary">
                                    <c:choose>
                                        <c:when test="${sort eq 'abNo'}">최신순</c:when>
                                        <c:when test="${sort eq 'abNoAsc'}">오래된순</c:when>
                                    </c:choose>
                                </button>&nbsp;
                            </a>
                        </c:forEach>
                    </c:if>
                </div>

            </div>
        </div>
        <br>

        <div class="row">
            <c:forEach var="abAnimal" items="${pageForm.content}">
                <div class="col-md-6 col-lg-3 ftco-animate"
                     onclick="window.location.href='<c:out value="/abandonedAnimal/detail?abNumber=${abAnimal.abNumber}"/>'">
                    <div class="staff">
                        <div class="img-wrap d-flex align-items-stretch">
                            <div class="img align-self-stretch"
                                 style="background-image: url('<c:out value="/abandonedAnimal/upload?filename=${abAnimal.imgPath}"/>')"></div>
                        </div>

                        <div class="text pt-3 px-3 pb-4 text-center">
                            <h3><c:out value="${abAnimal.name}"/></h3>
                            <span class="position mb-2"><c:out value="${abAnimal.location}"/></span>
                            <div class="faded">
                                <p><c:out value="${abAnimal.admissionDate}"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <%-- 검색 --%>
        <br>
        <div style="display: flex; justify-content: center;">
            <form action="/abandonedAnimal/list" method="get">
                <div class="form-group row">
                    <div class="col">
                        <select name="species" class="form-control">
                            <c:forEach var="animal" items="${['allSpecies', '개', '고양이', '기타']}">
                                <option value="${animal}" <c:if test="${param.species eq animal}">selected</c:if>>
                                    <c:out value="${animal eq 'allSpecies' ? '모든 동물' : animal}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <select name="gender" class="form-control">
                            <option value="allGender" <c:if test="${param.gender eq 'allGender'}">selected</c:if>>모든 성별
                            </option>
                            <option value="-" <c:if test="${param.gender eq '-'}">selected</c:if>>모름</option>
                            <option value="M" <c:if test="${param.gender eq 'M'}">selected</c:if>>수컷</option>
                            <option value="F" <c:if test="${param.gender eq 'F'}">selected</c:if>>암컷</option>
                        </select>
                    </div>

                    <div class="col">
                        <select name="animalState" class="form-control">
                            <option value="allAnimalState"
                                    <c:if test="${param.animalState eq 'allAnimalState'}">selected</c:if>>모든 상태
                            </option>
                            <option value="보호" <c:if test="${param.animalState eq '보호'}">selected</c:if>>보호중</option>
                            <option value="임보" <c:if test="${param.animalState eq '임보'}">selected</c:if>>임시보호중</option>
                        </select>
                    </div>

                    <div class="col">
                        <input type="text" name="keyword" class="form-control" placeholder="검색어"
                               value="${param.keyword eq 'allKeyword' ? '' : param.keyword}">
                    </div>

                    <div class="col-md-auto">
                        <button type="submit" class="btn btn-primary">검색
                        </button>
                    </div>
                </div>
                <input name="sort" value="${param.sort}" hidden>
            </form>
        </div>
        <%-- 검색 끝 --%>

        <div class=" row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>

                        <c:if test="${not empty param.species && not empty param.animalState}">
                            <li>
                                <c:if test="${pageForm.startPage > 5}">
                                    <a href="/abandonedAnimal/list?species=${param.species}&gender=${param.gender}&animalState=${param.animalState}&keyword=${param.keyword}&sort=${param.sort}&pageNo=${pageForm.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <c:forEach var="pNo" begin="${pageForm.startPage}" end="${pageForm.endPage}">
                                <c:if test="${pageForm.currentPage eq pNo}">
                                    <li class="active">
                                        <a href="/abandonedAnimal/list?species=${param.species}&gender=${param.gender}&animalState=${param.animalState}&keyword=${param.keyword}&sort=${param.sort}&pageNo=${pNo}">${pNo}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageForm.currentPage ne pNo}">
                                    <li>
                                        <a href="/abandonedAnimal/list?species=${param.species}&gender=${param.gender}&animalState=${param.animalState}&keyword=${param.keyword}&sort=${param.sort}&pageNo=${pNo}">${pNo}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li>
                                <c:if test="${pageForm.endPage < pageForm.totalPages}">
                                    <a href="/abandonedAnimal/list?species=${param.species}&gender=${param.gender}&animalState=${param.animalState}&keyword=${param.keyword}&sort=${param.sort}&pageNo=${pageForm.startPage + 5}">&gt;</a>
                                </c:if>
                            </li>
                        </c:if>

                        <c:if test="${empty param.species && empty param.animalState}">
                            <li>
                                <c:if test="${pageForm.startPage > 5}">
                                    <a href="/abandonedAnimal/list?sort=${param.sort}&pageNo=${pageForm.startPage - 5}">&lt;</a>
                                </c:if>
                            </li>
                            <c:forEach var="pNo" begin="${pageForm.startPage}"
                                       end="${pageForm.endPage}">
                                <c:if test="${pageForm.currentPage eq pNo}">
                                    <li class="active">
                                        <a href="/abandonedAnimal/list?sort=${param.sort}&pageNo=${pNo}">${pNo}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageForm.currentPage ne pNo}">
                                    <li>
                                        <a href="/abandonedAnimal/list?sort=${param.sort}&pageNo=${pNo}">${pNo}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li>
                                <c:if test="${pageForm.endPage < pageForm.totalPages}">
                                    <a href="/abandonedAnimal/list?sort=${param.sort}&pageNo=${pageForm.startPage + 5}">&gt;</a>
                                </c:if>
                            </li>
                        </c:if>

                    </ul>
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
<%@ include file="../include/footer.jspf" %>
</body>
</html>