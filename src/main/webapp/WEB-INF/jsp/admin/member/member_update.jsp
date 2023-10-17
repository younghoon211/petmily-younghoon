<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" type="text/css" href="styles.css">

    <script src="https://twitter.github.io/typeahead.js/js/handlebars.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

    <style>
        .error {
            color: #dc3545;
            font-size: 12px;
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
            <h2>회원정보 수정</h2>
        </div>
        <br>
        <div class="row no-gutters" style="margin: 0 auto; width:50%">
            <div class="contact-wrap w-100 p-md-5 p-4">
                <form action="/admin/member/update" method="POST" class="contactForm" id="form">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <label class="label">아이디</label>
                            <input class="form-control" type="text"
                                   id="id" name="id"
                                   value="${updateForm.id}"
                                   placeholder="3-15자, 소문자+숫자 형식 권장"
                                   maxlength="20">
                            <span class="idMsg"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">비밀번호</label>
                            <input class="form-control" type="text"
                                   id="pw" name="pw"
                                   value="${updateForm.pw}"
                                   placeholder="8-16자, 영문+숫자+특수문자 형식 권장"
                                   maxlength="20"
                                   oninput="this.value = this.value.replace(/[ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');">
                            <span class="pwMsg"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">닉네임</label>
                            <input class="form-control" type="text"
                                   id="name" name="name"
                                   value="${updateForm.name}"
                                   placeholder="3-15자 권장"
                                   maxlength="20">
                            <span class="nameMsg"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">생년월일</label>
                            <input class="form-control" type="date"
                                   id="birth" name="birth"
                                   value="${updateForm.birth}"
                                   min="1900-01-01" max="2099-12-31">
                            <span class="birthMsg"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">성별</label>
                            <select id="gender" name="gender" class="form-control gender" required>
                                <option value="M">남자</option>
                                <option value="F">여자</option>
                            </select>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">이메일</label>
                            <input class="form-control" type="text"
                                   id="email" name="email"
                                   value="${updateForm.email}"
                                   placeholder="pet@petmily.com 형식 권장"
                                   maxlength="40">
                            <span class="emailMsg"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">연락처</label>
                            <input class="form-control" type="tel"
                                   id="phone" name="phone"
                                   placeholder="01012345678 형식 권장"
                                   value="${updateForm.phone}"
                                   maxlength="11">
                            <span class="phoneMsg"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">회원 등급</label>
                            <input type="text"
                                   class="form-control"
                                   value="${updateForm.grade}" readonly>
                        </div>
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>&nbsp;
                        <button type="submit" class="btn btn-primary">회원정보 수정</button>
                    </div>

                    <input name="mNumber" value="${updateForm.getMNumber()}" hidden>

                    <%-- 유효성 검증 --%>
                    <input id="idValid" hidden>
                    <input id="pwValid" hidden>
                    <input id="nameValid" hidden>
                    <input id="birthValid" hidden>
                    <input id="emailValid" hidden>
                    <input id="phoneValid" hidden>
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

<script>
    $(document).ready(function () {
        const fieldIds = ['#id', '#pw', '#name', '#birth', '#email', '#phone'];
        const mNumber = "${param.mNumber}";

        $('#gender').val("${updateForm.gender}");

        $("#form").off().on("submit", function (event) {
            if (hasEmpty() || hasErrors()) {
                event.preventDefault();

                if (hasEmpty()) {
                    focusAtEmpty();
                } else {
                    focusAtErrors();
                }
            } else if (hasEmailAlready() && hasPhoneAlready()) {
                if (!confirm("이미 해당 이메일과 연락처를 사용중인 회원이 있습니다.\n정말 해당 정보로 회원을 새로 추가하시겠습니까?")) {
                    event.preventDefault();
                }
            } else if (hasEmailAlready()) {
                if (!confirm("이미 해당 이메일을 사용중인 회원이 있습니다.\n정말 해당 정보로 회원을 새로 추가하시겠습니까?")) {
                    event.preventDefault();
                }
            } else if (hasPhoneAlready()) {
                if (!confirm("이미 해당 연락처를 사용중인 회원이 있습니다.\n정말 해당 정보로 회원을 새로 추가하시겠습니까?")) {
                    event.preventDefault();
                }
            }
        });

        $('#id').off().on('input', function () {
            const id = $('#id').val().trim();
            const idMsg = $('.idMsg').addClass('error');

            if (!id) {
                idMsg.text("아이디를 입력하세요.");
                $('#idValid').val("error");
            } else {
                validId();
            }
        });

        $('#pw').off().on('input', function () {
            const pw = $('#pw').val().trim();
            const pwMsg = $('.pwMsg').addClass('error');

            if (!pw) {
                pwMsg.text("비밀번호를 입력하세요 (한글입력 불가).");
                $('#pwValid').val("error");
            } else {
                pwMsg.text("");
                $('#pwValid').val("");
            }
        });

        $('#name').off().on('input', function () {
            const name = $('#name').val().trim();
            const nameMsg = $('.nameMsg').addClass('error');

            if (!name) {
                nameMsg.text("닉네임을 입력하세요.");
                $('#nameValid').val("error");
            } else {
                nameMsg.text("");
                $('#nameValid').val("");
            }
        });

        $('#birth').off().on('input', function () {
            const birth = $('#birth').val().trim();
            const birthMsg = $('.birthMsg').addClass('error');

            if (!birth) {
                birthMsg.text("올바른 형식으로 입력하세요.");
                $('#birthValid').val("error");
            } else {
                birthMsg.text("");
                $('#birthValid').val("");
            }
        });

        $('#email').off().on('input', function () {
            const email = $('#email').val().trim();
            const emailMsg = $('.emailMsg').addClass('error');

            if (!email) {
                emailMsg.text("이메일을 입력하세요.");
                $('#emailValid').val("error");
            } else {
                validEmail();
            }
        });

        $('#phone').off().on('input', function () {
            const phone = $('#phone').val().trim();
            const phoneMsg = $('.phoneMsg').addClass('error');

            if (!phone) {
                phoneMsg.text("번호를 입력하세요.");
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
                        $('.pwMsg').addClass('error').text("비밀번호를 입력하세요 (한글입력 불가).");
                    } else if (fieldId === '#name') {
                        $('.nameMsg').addClass('error').text("닉네임을 입력하세요.");
                    } else if (fieldId === '#birth') {
                        $('.birthMsg').addClass('error').text("생일을 입력하세요.");
                    } else if (fieldId === '#email') {
                        $('.emailMsg').addClass('error').text("이메일을 입력하세요.");
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

        function hasEmailAlready() {
            return $('#emailValid').val() === "already";
        }

        function hasPhoneAlready() {
            return $('#phoneValid').val() === "already";
        }

        function validId() {
            const id = $('#id').val().trim();
            const idMsg = $('.idMsg').addClass('error');

            $.ajax({
                type: 'POST',
                url: '/admin/member/update/idValid',
                data: {id: id, mNumber: mNumber},
                success: function (result) {
                    console.log("id result=" + result);

                    if (result === "SUCCESS") {
                        idMsg.text("");
                        $('#idValid').val("");
                    } else {
                        idMsg.text("이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");
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
                url: '/admin/member/update/emailValid',
                data: {email: email, mNumber: mNumber},
                success: function (result) {
                    console.log("email result=" + result);

                    if (result === "SUCCESS") {
                        emailMsg.text("");
                        $('#emailValid').val("");
                    } else {
                        emailMsg.text("이미 사용중인 이메일입니다 (가능하지만 권장x).");
                        $('#emailValid').val("already");
                    }
                }
                , error: function () {
                    console.log("email error");
                }
            });
        }

        function validPhone() {
            const phoneMsg = $('.phoneMsg').addClass('error');
            const phone = $('#phone').val().trim();

            $.ajax({
                type: 'POST',
                url: '/admin/member/update/phoneValid',
                data: {phone: phone, mNumber: mNumber},
                success: function (result) {
                    console.log("phone result=" + result);

                    if (result === "SUCCESS") {
                        phoneMsg.text("");
                        $('#phoneValid').val("");
                    } else {
                        phoneMsg.text("이미 사용중인 번호입니다 (가능하지만 권장x).");
                        $('#phoneValid').val("already");
                    }
                }
                , error: function () {
                    console.log("phone error");
                }
            });
        }
    });
</script>

<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>

</body>
</html>