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

        .font {
            font-size: x-small;
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
            <h2>비밀번호 변경</h2>
        </div>
        <br>
        <div class="row no-gutters" style="margin: 0 auto; width:50%">
            <div class="contact-wrap w-100 p-md-5 p-4">

                <form action="/member/auth/changePw" method="post" class="contactForm" id="form">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="id"><span class="font">아이디</span></label>
                                <input type="text"
                                       class="form-control" id="id"
                                       value="${authUser.id}"
                                       readonly>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="oldPw"><span class="font">기존 비밀번호</span></label>
                                <input type="password"
                                       class="form-control" id="oldPw" name="oldPw"
                                       placeholder="기존 비밀번호를 입력해주세요." maxlength="16" autofocus>
                                <span class="oldPwMsg"></span>
                            </div>
                        </div>
                        <br>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="newPw"><span class="font">새 비밀번호</span></label>
                                <input type="password"
                                       class="form-control" id="newPw" name="newPw"
                                       placeholder="8-16자, 영문+숫자+특수문자" maxlength="16">
                                <span class="newPwMsg"></span>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label" for="newPwCheck"><span class="font">새 비밀번호 확인</span></label>
                                <input type="password"
                                       class="form-control" id="newPwCheck" name="newPwCheck"
                                       placeholder="새 비밀번호를 한번 더 입력해주세요." maxlength="16">
                                <span class="newPwCheckMsg"></span>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>&nbsp;&nbsp;
                        <button type="submit" class="btn btn-primary">변경하기</button>
                    </div>

                    <input name="mNumber" value="${authUser.getMNumber()}" hidden>

                    <%-- 유효성 검증 --%>
                    <input id="oldPwValid" hidden>
                    <input id="newPwValid" hidden>
                    <input id="newPwCheckValid" hidden>
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
        $("#form").off().on("submit", function (event) {
            const oldPw = $('#oldPw').val().trim();
            const newPw = $('#newPw').val().trim();
            const newPwCheck = $('#newPwCheck').val().trim();

            const oldPwError = $('#oldPwValid').val() === "error";
            const newPwError = $('#newPwValid').val() === "error";
            const newPwCheckError = $('#newPwCheckValid').val() === "error";

            const hasEmpty = !oldPw || !newPw || !newPwCheck;
            const isNotSamePwCheck = ($('#newPw').val().trim() !== $('#newPwCheck').val().trim());

            if (hasEmpty) {
                event.preventDefault();

                if (!oldPw) {
                    $('.oldPwMsg').addClass('error').text("비밀번호를 입력하세요.");
                    $('#oldPw').focus();
                } else if (!newPw) {
                    $('.newPwMsg').addClass('error').text("새 비밀번호를 입력하세요.");
                    $('#newPw').focus();
                } else if (!newPwCheck) {
                    $('.newPwCheckMsg').addClass('error').text("새 비밀번호 확인을 입력하세요.");
                    $('#newPwCheck').focus();
                }
            } else if (oldPwError || newPwError || newPwCheckError) {
                event.preventDefault();
            } else if (isNotSamePwCheck) {
                event.preventDefault();

                $('.newPwCheckMsg').addClass('error').text("새 비밀번호와 확인이 일치하지 않습니다.");
                $('#newPwCheck').focus();
            } else {
                if (!confirm("비밀번호를 정말로 변경하시겠습니까?")) {
                    event.preventDefault();
                }
            }
        });

        $('#oldPw').off().on('input', function () {
            validOldPw();
        });

        $('#newPw').off().on('input', function () {
            const newPw = $('#newPw').val().trim();
            const newPwMsg = $('.newPwMsg').addClass('error');
            const pwRegex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]+$/;

            if (!newPw) {
                newPwMsg.text("비밀번호를 입력하세요.");
                $('#newPwValid').val("error");
            } else if (newPw.length < 8 || newPw.length > 16) {
                newPwMsg.text("8-16자 이내로 입력해주세요.");
                $('#newPwValid').val("error");
            } else if (!pwRegex.test(newPw)) {
                newPwMsg.text("영문+숫자+특수문자로 입력하세요.");
                $('#newPwValid').val("error");
            } else {
                validNewPw();
            }
        });

        $('#newPwCheck').off().on('input', function () {
            const newPw = $('#newPw').val().trim();
            const newPwCheck = $('#newPwCheck').val().trim();
            const newPwCheckMsg = $('.newPwCheckMsg').addClass('error');

            if (newPw !== newPwCheck) {
                newPwCheckMsg.addClass('error').text("비밀번호와 확인이 일치하지 않습니다.");
                $('#newPwCheckValid').val("error");
            } else {
                newPwCheckMsg.removeClass('error').addClass('success').text("새 비밀번호 확인이 일치합니다.");
                $('#newPwCheckValid').val("");
            }
        });
    });

    function validOldPw() {
        const oldPw = $('#oldPw').val().trim();
        const oldPwMsg = $('.oldPwMsg').addClass('error');

        $.ajax({
            type: 'POST',
            url: '/member/auth/changePw/validOldPw',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({oldPw: oldPw}),
            success: function (result) {
                console.log("oldPw result=" + result);

                if (result === "SUCCESS") {
                    oldPwMsg.removeClass('error').addClass('success').text("올바른 비밀번호입니다.");
                    $('#oldPwValid').val("");
                } else {
                    oldPwMsg.text("비밀번호가 틀렸습니다.");
                    $('#oldPwValid').val("error");
                }
            }
            , error: function () {
                console.log("oldPw error");
            }
        });
    }

    function validNewPw() {
        const newPw = $('#newPw').val().trim();
        const newPwMsg = $('.newPwMsg').addClass('error');

        $.ajax({
            type: 'POST',
            url: '/member/auth/changePw/validNewPw',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({newPw: newPw}),
            success: function (result) {
                console.log("newPw result=" + result);

                if (result === "SUCCESS") {
                    newPwMsg.removeClass('error').addClass('success').text("사용 가능한 비밀번호입니다.");
                    $('#newPwValid').val("");
                } else {
                    newPwMsg.text("기존에 사용하던 비밀번호입니다.");
                    $('#newPwValid').val("error");
                }
            }
            , error: function () {
                console.log("newPw error");
            }
        });
    }
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>

</body>
</html>