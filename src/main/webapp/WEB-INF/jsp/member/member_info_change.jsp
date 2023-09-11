<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Petmily - Don't buy, Do Adopt</title>

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

    <style>
        .field-error {
            color: #dc3545;
            border-color: #dc3545;
            font-size: 13px;
            vertical-align: top;
        }
    </style>
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
                <h1 class="mb-0 bread">회원정보 변경</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="row justify-content-center">
            <h2>회원정보 변경</h2>
        </div>
        <br>
        <div class="row no-gutters" style="margin: 0 auto; width:50%">
            <div class="contact-wrap w-100 p-md-5 p-4">

                <form action="/member/auth/changeInfo" method="POST"
                      class="contactForm">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="id">아이디</label>
                                <input type="text"
                                       class="form-control" name="id" id="id"
                                       value="${empty param.id ? member.id : param.id}"
                                       minlength="3" maxlength="15"
                                       readonly>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="name">닉네임</label>
                                <input type="text" placeholder="닉네임"
                                       class="form-control" name="name" id="name"
                                       minlength="3" maxlength="15"
                                       value="${empty param.name ? member.name : param.name}"
                                       required>
                                <spring:hasBindErrors name="memberChangeForm">
                                    <c:if test="${errors.hasFieldErrors('name')}">
                                        <span class="field-error"><form:errors path="memberChangeForm.name"/></span>
                                    </c:if>
                                </spring:hasBindErrors>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="pw">비밀번호</label>
                                <input type="password" placeholder="비밀번호 (8-16자, 영문+숫자+특수문자)"
                                       class="form-control" name="pw" id="pw"
                                       minlength="8" maxlength="16" required
                                       value="${empty param.pw ? member.pw : param.pw}">
                                <spring:hasBindErrors name="memberChangeForm">
                                    <c:if test="${errors.hasFieldErrors('pw')}">
                                        <span class="field-error"><form:errors path="memberChangeForm.pw"/></span>
                                    </c:if>
                                </spring:hasBindErrors>
                            </div>
                        </div>


                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="email">이메일</label>
                                <input type="email"
                                       class="form-control" name="email" id="email"
                                       placeholder="이메일 주소 (예: petmily@naver.com)" minlength="5" maxlength="30"
                                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,6}$"
                                       value="${empty param.email ? member.email : param.email}"
                                       required
                                <spring:hasBindErrors name="memberChangeForm">
                                       <c:if test="${errors.hasFieldErrors('email')}">style="border-color: #dc3545"</c:if>
                                </spring:hasBindErrors>
                                >
                                <spring:hasBindErrors name="memberChangeForm">
                                    <c:if test="${errors.hasFieldErrors('email')}">
                                        <span class="field-error"><form:errors path="memberChangeForm.email"/></span>
                                    </c:if>
                                </spring:hasBindErrors>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="phone">연락처</label>
                                <input type="tel" class="form-control" name="phone" id="phone"
                                       maxlength="11" placeholder="연락처 (예: 01012345678)"
                                       value="${empty param.phone ? member.phone : param.phone}"
                                       pattern="^010\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                                       required
                                <spring:hasBindErrors name="memberChangeForm">
                                       <c:if test="${errors.hasFieldErrors('phone')}">style="border-color: #dc3545"</c:if>
                                </spring:hasBindErrors>
                                >
                                <spring:hasBindErrors name="memberChangeForm">
                                    <c:if test="${errors.hasFieldErrors('phone')}">
                                        <span class="field-error"><form:errors path="memberChangeForm.phone"/></span>
                                    </c:if>
                                </spring:hasBindErrors>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>&nbsp;&nbsp;
                        <button type="submit" class="btn btn-primary">변경하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br>
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