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

        .gender {
            font-size: 14.5px;
            padding-left: 10px
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
<%@ include file="../../include/header.jspf" %>

<%-- 현재 페이지 --%>
<section class="ftco-section bg-light">
    <div class="container">
        <div class="row justify-content-center">
            <h2>회원정보 추가</h2>
        </div>
        <div class="row no-gutters" style="margin: 0 auto; width:50%">
            <div class="contact-wrap w-100 p-md-5 p-4">
                <form action="/admin/member/insert" method="post" class="contactForm">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <label class="label">아이디</label>
                            <input class="form-control" type="text" name="id" placeholder="아이디 (회원 기준: 3-15자, 소문자+숫자)"
                                   pattern="^[a-z0-9]+$"
                                   maxlength="15" autofocus required>
                        </div>
                        <div class="col-md-12 form-group password">
                            <label class="label">비밀번호</label>
                            <input class="form-control" id="pw" name="pw" type="text"
                                   placeholder="비밀번호 (회원 기준: 8-16자, 영문+숫자+특수문자)"
                                   maxlength="16" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">닉네임</label>
                            <input class="form-control" type="text" name="name" placeholder="닉네임"
                                   maxlength="10" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">생년월일</label>
                            <input class="form-control" type="date" name="birth" placeholder="생년월일"
                                   min="1900-01-01" max="2099-12-31" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">성별</label>
                            <select name="gender" class="form-control gender">
                                <option value="M">남자</option>
                                <option value="F">여자</option>
                            </select>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">이메일</label>
                            <input class="form-control" type="text" name="email"
                                   placeholder="회원 기준: ex) petmily@naver.com"
                                   maxlength="30" value="${param.email}"
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,6}$" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">연락처</label>
                            <input class="form-control" type="tel" name="phone" placeholder="ex) 01012345678"
                                   pattern="^010\d{8}$"
                                   maxlength="11" value="${param.phone}" required
                                   oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                        </div>
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>&nbsp;
                        <button type="submit" class="btn btn-primary">회원정보 추가</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="info-wrap w-100 p-5 img"></div>
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