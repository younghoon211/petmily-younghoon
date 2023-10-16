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
<div class="container">
    <form action="/resetPw" method="post" class="contactForm">

        <span id="br1"><br style="line-height: 250px"></span>
        <span id="br2" style="display: none"><br style="line-height: 170px"></span>

        <div class="form-inputs">
            <div class="row no-gutters" style="margin: 0 auto; width:30%">
                <div style="text-align: center">
                    <a href="/">
                        <h4><span class="flaticon-pawprint-1 mr-2"
                                  style="color: #00bd56"></span><b>Petmily</b></h4><br>
                    </a>
                </div>

                <div class="col-md-12 sendEmailForm">
                    <div class="form-group">
                        <span class="label">아이디</span>
                        <input type="text"
                               class="form-control font"
                               id="id" name="id"
                               placeholder="아이디를 입력해주세요."
                               maxlength="15" autofocus
                               style="font-weight: normal">
                        <br>
                        <span class="label">이메일</span>
                        <input type="text" class="form-control"
                               id="email"
                               placeholder="이메일 주소를 입력하세요."
                               maxlength="30"
                               style="font-weight: normal">
                    </div>

                    <div class="login col-md-7" style="margin: auto">
                        <br>
                        <button type="button" id="submitBtn1" class="btn btn-lg btn-block btn-success">비밀번호 재설정
                        </button>
                    </div>

                    <div class="login-text" style="font-size: smaller; text-align: center">
                        <br>비밀번호가 생각나셨나요? <a href="/login">Login</a>
                    </div>
                </div>


                <div class="authCodeForm col-md-12" style="display: none"><br>
                    <div class="form-group">
                        <span class="label">인증코드</span>
                        <div style="display: flex; align-items: center">
                            <input type="text" class="form-control"
                                   id="inputCode" name="inputCode"
                                   placeholder="인증코드를 입력해주세요."
                                   maxlength="6"
                                   oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                                   style="font-weight: normal">&nbsp;&nbsp;&nbsp;
                            <button id="authBtn" type="button" class="btn btn-outline-success">인증하기
                            </button>
                        </div>
                        <span class="authMsg"></span>
                    </div>
                </div>

                <div class="resetPwForm col-md-12" style="display: none">
                    <div class="form-group">
                        <span class="label">새 비밀번호</span>
                        <input type="password" class="form-control"
                               id="pw" name="pw"
                               placeholder="8-16자, 영문+숫자+특수문자"
                               maxlength="16"
                               style="font-weight: normal">
                        <span class="pwMsg"></span>
                    </div>


                    <div class="form-group">
                        <span class="label">새 비밀번호 확인</span>
                        <input type="password" class="form-control"
                               id="pwCheck" name="pwCheck"
                               placeholder="비밀번호를 한번 더 입력해주세요."
                               maxlength="16"
                               style="font-weight: normal">
                        <span class="pwCheckMsg"></span>
                    </div>

                    <div class="login col-md-7" style="margin: auto">
                        <br>
                        <button type="submit" id="submitBtn2" class="btn btn-lg btn-block btn-success">비밀번호 변경
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <input id="authCode" name="authCode" hidden>
        <input id="authCodeValid" hidden>
        <input id="pwValid" hidden>
        <input id="pwCheckValid" hidden>
    </form>
</div>
<%--</section>--%>

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
        let authCode;

        // ====================== 이메일 인증코드 보내는 폼 ======================
        // 이메일 전송 버튼
        $('#submitBtn1').off('click').on('click', function () {
            const id = $('#id').val().trim();
            const email = $('#email').val().trim();

            if (!id) {
                alert("아이디를 입력하세요.");
                $('#id').focus();
            } else if (!email) {
                alert("이메일을 입력하세요.");
                $('#email').focus();
            } else if (!id && !email) {
                alert("아이디와 이메일을 입력하세요.");
                $('#id').focus();
            } else {
                validAndSendMail();
            }
        });

        // ========================= 비밀번호 재설정 폼 =========================
        // 인증코드 인증 버튼
        $('#authBtn').off('click').on('click', function () {
            const inputCode = $('#inputCode').val().trim();

            if (!inputCode) {
                $('.authMsg').addClass('error').text("인증코드를 입력하세요.");
                $('#authCodeValid').val("error");
                $('#inputCode').focus();
            } else if (inputCode !== authCode) {
                $('.authMsg').addClass('error').text("인증코드가 일치하지 않습니다.");
                $('#authCodeValid').val("error");
                $('#inputCode').focus();
            } else {
                $('#inputCode').prop('readonly', true);
                $('.authMsg').removeClass('error').addClass('success').text("인증이 완료되었습니다.");
                $('#authCodeValid').val("");
                $('.resetPwForm').show();
                $('#pw').focus();
            }
        });

        // 비밀번호 재설정 버튼
        $('#submitBtn2').off('click').on('click', function (event) {
            const inputCode = $('#inputCode').val().trim();
            const pw = $('#pw').val().trim();
            const pwCheck = $('#pwCheck').val().trim();
            const authCodeError = $('#authCodeValid').val() === "error";
            const pwError = $('#pwValid').val() === "error";
            const pwCheckError = $('#pwCheckValid').val() === "error";

            const isNotSamePwCheck = (pw !== pwCheck);

            const hasEmpty = !authCode || !pw || !pwCheck;
            const hasErrors = authCodeError || pwError || pwCheckError;

            if (hasEmpty) {
                event.preventDefault();

                if (!inputCode) {
                    $('#inputCode').val("error");
                } else if (!pw) {
                    $('.pwMsg').addClass('error').text("새 비밀번호를 입력하세요.");
                    $('#pwValid').val("error");
                    $('#pw').focus();
                } else if (!pwCheck) {
                    $('.pwCheckMsg').addClass('error').text("새 비밀번호 확인을 입력하세요.");
                    $('#pwCheckValid').val("error");
                    $('#pwCheck').focus();
                }
            } else if (hasErrors) {
                event.preventDefault();

                if (authCodeError) {
                    $('#inputCode').focus();
                } else if (pwError) {
                    $('#pw').focus();
                } else if (pwCheckError) {
                    $('#pwCheck').focus();
                }
            } else if (isNotSamePwCheck) {
                event.preventDefault();
                $('.pwCheckMsg').addClass('error').text("새 비밀번호와 확인이 일치하지 않습니다.");
                $('#pwCheck').focus();
            } else {
                $('form').submit();
            }
        });

        // 새 비밀번호 검증
        $('#pw').off().on('input', function () {
            const pw = $('#pw').val().trim();
            const pwMsg = $('.pwMsg').addClass('error');
            const pwRegex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]+$/;

            if (!pw) {
                pwMsg.text("새 비밀번호를 입력하세요.");
                $('#pwValid').val("error");
            } else if (pw.length < 8 || pw.length > 16) {
                pwMsg.text("8-16자 이내로 입력해주세요.");
                $('#pwValid').val("error");
            } else if (!pwRegex.test(pw)) {
                pwMsg.text("영문+숫자+특수문자로 입력하세요.");
                $('#pwValid').val("error");
            } else {
                validDupPw();
            }
        });

        // 새 비밀번호 확인 검증
        $('#pwCheck').off().on('input', function () {
            const pw = $('#pw').val().trim();
            const pwCheck = $('#pwCheck').val().trim();
            const pwCheckMsg = $('.pwCheckMsg').addClass('error');

            if (!pwCheck) {
                pwCheckMsg.addClass('error').text("새 비밀번호 확인을 입력하세요.");
                $('#pwCheckValid').val("error");
            } else if (pw !== pwCheck) {
                pwCheckMsg.addClass('error').text("새 비밀번호와 확인이 일치하지 않습니다.");
                $('#pwCheckValid').val("error");
            } else {
                pwCheckMsg.removeClass('error').addClass('success').text("새 비밀번호와 확인이 일치합니다.");
                $('#pwCheckValid').val("");
            }
        });

        function validAndSendMail() {
            const id = $('#id').val().trim();
            const email = $('#email').val().trim();

            $.ajax({
                type: 'POST',
                url: '/resetPw/validIdAndEmail',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({id: id, email: email}),
                success: function (result) {
                    console.log("infoAuth result=" + result);

                    if (result === "SUCCESS") {
                        alert("이메일로 인증코드를 발송했습니다.\n인증코드를 확인 후 비밀번호를 변경하세요.");

                        $('.sendEmailForm').hide();
                        $('#br1').hide();

                        $('.authCodeForm').show();
                        $('#br2').show();
                        $('#inputCode').focus();

                        sendMail();
                    } else {
                        alert("아이디 또는 이메일이 일치하지 않습니다.");
                    }
                },

                error: function () {
                    console.log("validAndSendMail error");
                }
            });
        }

        function sendMail() {
            const email = $('#email').val().trim();

            $.ajax({
                type: 'POST',
                url: '/resetPw/sendMail',
                data: {email: email},
                success: function (result) {
                    if (result) {
                        authCode = result;
                        console.log("authCode=" + authCode);

                        // 서버 검증용 데이터 보내기
                        $('#authCode').val(authCode);
                    }
                },

                error: function () {
                    console.log("sendMail error");
                }
            });
        }

        function validDupPw() {
            const pw = $('#pw').val().trim();
            const id = $('#id').val().trim();

            $.ajax({
                type: 'POST',
                url: '/resetPw/validDupOldPw',
                data: {id: id},
                success: function (oldPw) {
                    if (pw === oldPw) {
                        $('.pwMsg').addClass('error').text("기존에 사용하던 비밀번호입니다.");
                        $('#pwValid').val("error");
                    } else {
                        $('.pwMsg').removeClass('error').addClass('success').text("사용 가능한 비밀번호입니다.");
                        $('#pwValid').val("");
                    }
                },

                error: function () {
                    console.log("validDupPw error");
                }
            });
        }
    });
</script>

</body>
</html>
