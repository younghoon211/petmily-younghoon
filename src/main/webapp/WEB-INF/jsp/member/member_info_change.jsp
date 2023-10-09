<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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

    <script src="https://twitter.github.io/typeahead.js/js/handlebars.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

    <style>
        .success {
            font-size: xx-small;
            color: #008000;
        }

        .error {
            font-size: xx-small;
            color: #dc3545;
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

                <form action="/member/auth/changeInfo" method="post" class="contactForm">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <span class="label">아이디</span>
                                <input type="text"
                                       class="form-control" id="id"
                                       value="${authUser.id}"
                                       readonly>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <span class="label">성별</span>
                                <input type="text"
                                       class="form-control" id="gender"
                                <c:if test="${authUser.gender eq 'M'}">
                                       value="남자"
                                </c:if>
                                <c:if test="${authUser.gender eq 'F'}">
                                       value="여자"
                                </c:if>
                                       readonly>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <span class="label">생년월일</span>
                                <input type="date"
                                       class="form-control" id="birth"
                                       value="${authUser.birth}"
                                       readonly>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <span class="label">닉네임</span>
                                <input type="text"
                                       class="form-control" id="name" name="name"
                                       placeholder="한글, 영문, 숫자만 입력 가능합니다." maxlength="15"
                                       value="${member.name}">
                                <span class="nameMsg"></span>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <span class="label">이메일</span>
                                <input type="email"
                                       class="form-control " id="email" name="email"
                                       placeholder="ex) pet@petmily.com" maxlength="30"
                                       value="${member.email}">
                                <span class="emailMsg"></span>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <span class="label">연락처</span>
                                <input type="tel" class="form-control" id="phone" name="phone"
                                       maxlength="11" placeholder="ex) 01012345678"
                                       value="${member.phone}">
                                <span class="phoneMsg"></span>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>&nbsp;&nbsp;
                        <button type="submit" id="submit" class="btn btn-primary">변경하기</button>
                    </div>

                    <input name="mNumber" value="${authUser.getMNumber()}" hidden>

                    <%-- 유효성 검증 --%>
                    <input id="nameValid" hidden>
                    <input id="emailValid" hidden>
                    <input id="phoneValid" hidden>
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

<script>
    $(document).ready(function () {
        $('#submit').on('click', function (event) {
            let nameError = $('#nameValid').val() === "error";
            let emailError = $('#emailValid').val() === "error";
            let phoneError = $('#phoneValid').val() === "error";

            if (nameError) {
                event.preventDefault();
                $('#name').focus();
            } else if (emailError) {
                event.preventDefault();
                $('#email').focus();
            } else if (phoneError) {
                event.preventDefault();
                $('#phone').focus();
            } else {
                $("form").submit();
            }
        });

        $('#name').on('input', function () {
            const initName = "${member.name}";
            let name = $('#name').val().trim();
            let nameMsg = $('.nameMsg').addClass('error');
            const nameRegex = /^[0-9a-zA-Z가-힣]+$/g;

            if (initName === name) {
                nameMsg.text("");
                $('#nameValid').val("");
            } else if (!name) {
                nameMsg.text("닉네임을 입력하세요.");
                $('#nameValid').val("error");
            } else if (name.length < 3 || name.length > 15) {
                nameMsg.text("3-15자 이내로 입력해주세요.");
                $('#nameValid').val("error");
            } else if (!nameRegex.test(name)) {
                nameMsg.text("닉네임 형식을 지켜주세요.");
                $('#nameValid').val("error");
            } else {
                nameMsg.removeClass('error').addClass('success').text("멋진 닉네임이네요!");
                $('#nameValid').val("");
            }
        });

        $('#email').on('input', function () {
            const initEmail = "${member.email}";
            let email = $('#email').val().trim();
            let emailMsg = $('.emailMsg').addClass('error');
            const emailRegex = /^[0-9a-zA-Z]+([-_.]*[0-9a-zA-Z])*@[0-9a-zA-Z]+([.]*[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/g;

            if (initEmail === email) {
                emailMsg.text("");
                $('#emailValid').val("");
            } else if (!email) {
                emailMsg.text("이메일을 입력하세요.");
                $('#emailValid').val("error");
            } else if (email.length < 5 || email.length > 30) {
                emailMsg.text("5-30자 이내로 입력해주세요.");
                $('#emailValid').val("error");
            } else if (!emailRegex.test(email)) {
                emailMsg.text("이메일 형식을 지켜주세요.");
                $('#emailValid').val("error");
            } else {
                emailAjax();
            }
        });

        $('#phone').on('input', function () {
            const initPhone = "${member.phone}";
            let phone = $('#phone').val().trim();
            let phoneMsg = $('.phoneMsg').addClass('error');
            const phoneRegex = /^(010)(\d{8})$/g;

            if (initPhone === phone) {
                phoneMsg.text("");
                $('#phoneValid').val("");
            } else if (!phone) {
                phoneMsg.text("번호를 입력하세요.");
                $('#phoneValid').val("error");
            } else if (phone.length !== 11 || !phoneRegex.test(phone)) {
                phoneMsg.text("올바른 형식(예: 01012345678)으로 입력하세요.");
                $('#phoneValid').val("error");
            } else {
                phoneAjax();
            }
        });
    });

    function emailAjax() {
        let emailMsg = $('.emailMsg').addClass('error');

        $.ajax({
            type: 'POST',
            url: '/member/auth/changeInfo/emailValid',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({email: $('#email').val().trim()}),
            success: function (result) {
                console.log("email result=" + result);

                if (result === "SUCCESS") {
                    emailMsg.removeClass('error').addClass('success').text("사용 가능한 이메일입니다.");
                    $('#emailValid').val("");
                } else {
                    emailMsg.text("이미 사용중인 이메일입니다.");
                    $('#emailValid').val("error");
                }
            }
            , error: function () {
                console.log("email error");
            }
        });
    }

    function phoneAjax() {
        let phoneMsg = $('.phoneMsg').addClass('error');

        $.ajax({
            type: 'POST',
            url: '/member/auth/changeInfo/phoneValid',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({phone: $('#phone').val()}),
            dataType: 'text',
            success: function (result) {
                console.log("phone result=" + result);

                if (result === "SUCCESS") {
                    phoneMsg.removeClass('error').addClass('success').text("사용 가능한 번호입니다.");
                    $('#phoneValid').val("");
                } else {
                    phoneMsg.text("이미 사용중인 번호입니다.");
                    $('#phoneValid').val("error");
                }
            }
            , error: function () {
                console.log("phone error");
            }
        });
    }
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>

</body>
</html>