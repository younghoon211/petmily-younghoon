<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
    <link rel="stylesheet" href="/resources/css/join.css">
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
            color: #008000;
            font-size: 12px;
        }

        .error {
            color: #dc3545;
            font-size: 12px;
        }
    </style>
</head>
<body>

<section class="bg-light">
    <div class="container">
        <div class="row no-gutters" style="margin: 0 auto; width:50%">
            <div class="contact-wrap w-100 p-md-5 p-4">
                <div style="text-align: center">
                    <a href="/">
                        <h4><span class="flaticon-pawprint-1 mr-2" style="color: #00bd56"></span><b>Petmily</b></h4><br>
                    </a>
                </div>

                <form action="/join" method="post" class="contactForm">
                    <div class="form-inputs">
                        <div class="row">

                            <div class="col-md-12">
                                <div class="form-group">
                                    <span class="label">아이디</span>
                                    <input type="text"
                                           class="form-control font"
                                           id="id" name="id"
                                           placeholder="3-15자, 소문자, 숫자"
                                           maxlength="15" autofocus
                                           style="font-weight: normal">
                                    <span class="idMsg"></span>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group pw">
                                    <span class="label">비밀번호</span>
                                    <input type="password" class="form-control"
                                           id="pw" name="pw"
                                           placeholder="8-16자, 영문+숫자+특수문자"
                                           maxlength="16"
                                           style="font-weight: normal">
                                    <span class="pwMsg"></span>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group pw">
                                    <span class="label">비밀번호 확인</span>
                                    <input type="password" class="form-control"
                                           id="pwCheck" name="confirmPw"
                                           placeholder="비밀번호를 한번 더 입력해주세요."
                                           maxlength="16"
                                           style="font-weight: normal">
                                    <span class="pwCheckMsg"></span>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group">
                                    <span class="label">닉네임</span>
                                    <input type="text" class="form-control"
                                           id="name" name="name"
                                           placeholder="3-15자 (특수문자 불가)"
                                           maxlength="15"
                                           style="font-weight: normal">
                                    <span class="nameMsg"></span>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group">
                                    <span class="label">생년월일</span>
                                    <input type="date" class="form-control"
                                           id="birth" name="birth"
                                           min="1900-01-01" max="2030-12-31"
                                           style="font-weight: normal">
                                    <span class="birthMsg"></span>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <span class="label">성별</span>
                                <select name="gender" class="form-control">
                                    <option value="M">남자</option>
                                    <option value="F">여자</option>
                                </select>
                            </div>

                            <div class="col-md-12"><br>
                                <div class="form-group">
                                    <span class="label">이메일</span>
                                    <div style="display: flex; align-items: center">
                                        <input type="text" class="form-control"
                                               id="email" name="email"
                                               placeholder="ex) pet@petmily.com"
                                               maxlength="30"
                                               style="font-weight: normal">&nbsp;&nbsp;&nbsp;
                                        <button id="emailAuthBtn" type="button" class="btn btn-outline-success">인증하기
                                        </button>
                                    </div>
                                    <span class="emailMsg"></span>

                                    <div style="display: none" id="inputAuthCode">
                                        <div style="display: flex; align-items: center">
                                            <input type="text" class="form-control"
                                                   id="emailAuth"
                                                   placeholder="인증번호를 입력해주세요."
                                                   maxlength="6"
                                                   oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                                                   style="font-weight: normal">
                                        </div>
                                    </div>
                                    <span class="emailAuthMsg"></span>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group">
                                    <span class="label">연락처</span>
                                    <input type="tel" class="form-control"
                                           id="phone" name="phone"
                                           placeholder="ex) 01012345678"
                                           maxlength="11"
                                           oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                                           style="font-weight: normal">
                                    <span class="phoneMsg"></span>
                                </div>
                            </div>

                            <div class="login col-md-7" style="margin: auto">
                                <br>
                                <button type="submit" id="submit" class="btn btn-lg btn-block btn-success">회원가입</button>
                            </div>

                            <div class="login-text" style="font-size: smaller; text-align: center">
                                <br>계정이 이미 있으신가요? <a href="/login">Login</a>
                            </div>

                        </div>
                    </div>

                    <%-- 유효성 검증 --%>
                    <input id="idValid" hidden>
                    <input id="pwValid" hidden>
                    <input id="pwCheckValid" hidden>
                    <input id="nameValid" hidden>
                    <input id="birthValid" hidden>
                    <input id="emailValid" hidden>
                    <input id="emailAuthValid" hidden>
                    <input id="phoneValid" hidden>
                </form>
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

<script>
    $(document).ready(function () {
        const fieldIds = ['#id', '#pw', '#pwCheck', '#name', '#birth', '#email', '#emailAuth', '#phone'];
        let authCode;

        $('#submit').off().on('click', function (event) {
            const isNotSamePwCheck = ($('#pw').val().trim() !== $('#pwCheck').val().trim());

            if (hasEmpty() || hasErrors()) {
                event.preventDefault();

                if (hasEmpty()) {
                    focusAtEmpty();
                } else {
                    focusAtErrors();
                }
            } else if (isNotSamePwCheck) {
                event.preventDefault();
                $('.pwCheckMsg').addClass('error').text("비밀번호와 확인이 일치하지 않습니다.");
                $('#pwCheck').focus();
            } else {
                $("form").submit();
            }
        });

        $('#id').off().on('input', function () {
            const id = $('#id').val().trim();
            const idMsg = $('.idMsg').addClass('error');
            const idRegex = /^[0-9a-z]+$/g

            if (!id) {
                idMsg.text("아이디를 입력하세요.");
                $('#idValid').val("error");
            } else if (id.length < 3 || id.length > 15) {
                idMsg.text("3-15자 이내로 입력해주세요.");
                $('#idValid').val("error");
            } else if (!idRegex.test(id)) {
                idMsg.text("소문자, 숫자로 입력하세요.");
                $('#idValid').val("error");
            } else {
                validId();
            }
        });

        $('#pw').off().on('input', function () {
            const pw = $('#pw').val().trim();
            const pwMsg = $('.pwMsg').addClass('error');
            const pwRegex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]+$/;

            if (!pw) {
                pwMsg.text("비밀번호를 입력하세요.");
            } else if (pw.length < 8 || pw.length > 16) {
                pwMsg.text("8-16자 이내로 입력해주세요.");
                $('#pwValid').val("error");
            } else if (!pwRegex.test(pw)) {
                pwMsg.text("영문+숫자+특수문자로 입력하세요.");
                $('#pwValid').val("error");
            } else {
                pwMsg.removeClass('error').addClass('success').text("사용 가능한 비밀번호입니다.");
                $('#pwValid').val("");
            }
        });

        $('#pwCheck').off().on('input', function () {
            const pw = $('#pw').val().trim();
            const pwCheck = $('#pwCheck').val().trim();
            const pwCheckMsg = $('.pwCheckMsg').addClass('error');

            if (!pwCheck) {
                pwCheckMsg.text("비밀번호 확인을 입력하세요.");
            } else if (pw !== pwCheck) {
                pwCheckMsg.text("비밀번호와 확인이 일치하지 않습니다.");
                $('#pwCheckValid').val("error");
            } else {
                pwCheckMsg.removeClass('error').addClass('success').text("비밀번호와 확인이 일치합니다.");
                $('#pwCheckValid').val("");
            }
        });

        $('#name').off().on('input', function () {
            const name = $('#name').val().trim();
            const nameMsg = $('.nameMsg').addClass('error');
            const nameRegex = /^[0-9a-zA-Z가-힣]+$/g;

            if (!name) {
                nameMsg.text("닉네임을 입력하세요.");
            } else if (name.length < 3 || name.length > 15) {
                nameMsg.text("3-15자 이내로 입력해주세요.");
                $('#nameValid').val("error");
            } else if (!nameRegex.test(name)) {
                nameMsg.text("한글, 영문, 숫자로 입력하세요.");
                $('#nameValid').val("error");
            } else {
                nameMsg.removeClass('error').addClass('success').text("멋진 닉네임이네요!");
                $('#nameValid').val("");
            }
        });

        $('#birth').off().on('input', function () {
            const birth = $('#birth').val().trim();
            const birthMsg = $('.birthMsg').addClass('error');

            if (!birth) {
                birthMsg.text("올바른 형식으로 입력하세요.");
            } else {
                birthMsg.text("");
                $('#birthValid').val("");
            }
        });

        $('#email').off().on('input', function () {
            const email = $('#email').val().trim();
            const emailMsg = $('.emailMsg').addClass('error');
            const emailRegex = /^[0-9a-zA-Z]+([-_.]*[0-9a-zA-Z])*@[0-9a-zA-Z]+([.]*[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/g;

            if (!email) {
                emailMsg.text("이메일을 입력하세요.");
                $('#emailValid').val("error");
            } else if (email.length < 5 || email.length > 30) {
                emailMsg.text("5-30자 이내로 입력해주세요.");
                $('#emailValid').val("error");
            } else if (!emailRegex.test(email)) {
                emailMsg.text("이메일 형식을 지켜주세요.");
                $('#emailValid').val("error");
            } else {
                validEmail();
            }
        });

        $('#emailAuthBtn').off().on('click', function (event) {
            if ($('#emailValid').val() === "success") {
                $('#inputAuthCode').show();
                $('.emailMsg').removeClass('error').addClass('success').text("인증코드가 발송되었습니다.");
                $('#email').prop('readonly', true);

                alert("인증코드가 발송되었습니다.\n이메일 확인 후 인증코드를 입력하세요.");

                authEmail();
                $('#emailAuth').focus();
                $('#emailValid').val("send");
            } else if ($('#emailValid').val() === "send") {
                alert("발송된 인증코드를 입력해주세요.");

                $('#emailAuth').focus();
                $('.emailAuthMsg').addClass('error').text("인증코드를 입력하세요.");
            } else if ($('#emailValid').val === "error") {
                event.preventDefault();
            } else if (!$('#email').val()) {
                $('.emailMsg').removeClass('success').addClass('error').text("이메일을 입력하세요.");
            }
        });

        $('#emailAuth').off().on('input', function () {
            const inputCode = $('#emailAuth').val().trim();
            const emailAuthMsg = $('.emailAuthMsg').addClass('error');

            if (inputCode === authCode) {
                emailAuthMsg.removeClass('error').addClass('success').text("인증코드가 일치합니다.");
                $('#emailAuthValid').val("");
                $('#emailAuth').prop('readonly', true);
            } else if (!inputCode) {
                emailAuthMsg.text("인증코드를 입력해주세요.");
                $('#emailAuthValid').val("error");
            } else {
                emailAuthMsg.text("인증코드가 일치하지 않습니다.");
                $('#emailAuthValid').val("error");
            }
        });


        $('#phone').off().on('input', function () {
            const phone = $('#phone').val().trim();
            const phoneMsg = $('.phoneMsg').addClass('error');
            const phoneRegex = /^(010)(\d{8})$/g;

            if (!phone) {
                phoneMsg.text("번호를 입력하세요.");
                $('#phoneValid').val("error");
            } else if (phone.length !== 11 || !phoneRegex.test(phone)) {
                phoneMsg.text("올바른 형식(예: 01012345678)으로 입력하세요.");
                $('#phoneValid').val("error");
            } else {
                validPhone();
            }
        });

        function hasEmpty() {
            return fieldIds.some((fieldId) => !$(fieldId).val().trim());
        }

        function hasErrors() {
            return fieldIds.some((fieldId) => $(fieldId + 'Valid').val() === "error");
        }

        function focusAtEmpty() {
            for (const fieldId of fieldIds) {
                if (!$(fieldId).val().trim()) {

                    $(fieldId).focus();

                    if (fieldId === '#id') {
                        $('.idMsg').addClass('error').text("아이디를 입력하세요.");
                    } else if (fieldId === '#pw') {
                        $('.pwMsg').addClass('error').text("비밀번호를 입력하세요.");
                    } else if (fieldId === '#pwCheck') {
                        $('.pwCheckMsg').addClass('error').text("비밀번호 확인을 입력하세요.");
                    } else if (fieldId === '#name') {
                        $('.nameMsg').addClass('error').text("닉네임을 입력하세요.");
                    } else if (fieldId === '#birth') {
                        $('.birthMsg').addClass('error').text("생일을 입력하세요.");
                    } else if (fieldId === '#email') {
                        $('.emailMsg').addClass('error').text("이메일을 입력하세요.");
                    } else if (fieldId === '#emailAuth') {
                        $('.emailAuthMsg').addClass('error').text("인증코드를 입력하세요.");
                    } else if (fieldId === '#phone') {
                        $('.phoneMsg').addClass('error').text("번호를 입력하세요.");
                    }

                    break;
                }
            }
        }

        function focusAtErrors() {
            for (const fieldId of fieldIds) {
                const valid = fieldId + 'Valid';

                if ($(valid).val() === "error") {
                    $(fieldId).focus();
                    break;
                }
            }
        }

        function validId() {
            const id = $('#id').val().trim();
            const idMsg = $('.idMsg').addClass('error');

            $.ajax({
                type: 'POST',
                url: '/join/idValid',
                data: {id: id},
                success: function (result) {
                    console.log("id result=" + result);

                    if (result === "SUCCESS") {
                        idMsg.removeClass('error').addClass('success').text("멋진 아이디네요!");
                        $('#idValid').val("");
                    } else {
                        idMsg.text("이미 사용중인 아이디입니다.");
                        $('#idValid').val("error");
                    }
                }
                , error: function () {
                    console.log("id error");
                }
            });
        }

        function validEmail() {
            const email = $('#email').val().trim();
            const emailMsg = $('.emailMsg').addClass('error');

            $.ajax({
                type: 'POST',
                url: '/join/emailValid',
                data: {email: email},
                success: function (result) {
                    console.log("email result=" + result);

                    if (result === "SUCCESS") {
                        emailMsg.removeClass('error').addClass('success').text("사용 가능한 이메일입니다.");
                        $('#emailValid').val("success");
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

        function authEmail() {
            const email = $('#email').val().trim();

            $.ajax({
                type: 'POST',
                url: '/join/sendMailAuthCode',
                data: {email: email},
                success: function (result) {
                    console.log("authCode=" + result);

                    authCode = result;
                }
                , error: function () {
                    console.log("sendEmailCode error");
                }
            });
        }

        function validPhone() {
            const phoneMsg = $('.phoneMsg').addClass('error');
            const phone = $('#phone').val().trim();

            $.ajax({
                type: 'POST',
                url: '/join/phoneValid',
                data: {phone: phone},
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
    });
</script>

</body>
</html>