<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/resources/css/login.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
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
        .error {
            color: red;
            font-size: 12px;
            position: fixed;
        }
    </style>
</head>

<body>
<div class="section">
    <div class="container">
        <div class="form">
            <div class="left-side">
                <form action="/login" method="post" id="form">
                    <div style="padding-bottom: 15%">
                        <div class="login" id="login">
                            <a class="navbar-brand" href="/">
                                <h4><span class="flaticon-pawprint-1 mr-2" style="color: #00bd56"></span><b>Petmily</b>
                                </h4>
                            </a>
                        </div>
                        <div class="form-inputs">
                            <div>
                                <input type="text" name="id" id="id" placeholder="아이디"
                                       style="padding-left: 10px" maxlength="15"
                                       value="${rejectedId}"
                                <c:if test="${empty rejectedId}">
                                       autofocus
                                </c:if>
                                >
                            </div>
                            <div class="password">
                                <input type="password" name="pw" id="pw" placeholder="비밀번호"
                                       style="padding-left: 10px" maxlength="16"
                                <c:if test="${not empty rejectedId}">
                                       autofocus
                                </c:if>
                                >
                            </div>
                            <div class="error">
                                <span id="requiredMsg" style="display: none"></span>
                                <c:if test="${not empty rejectedId}">
                                    <span id="notCorrectMsg">아이디 또는 비밀번호가 일치하지 않습니다.</span>
                                </c:if>
                            </div>
                            <br>
                            <br style="line-height:50%">
                            <div class="login">
                                <button type="submit" class="btn btn-lg btn-block btn-success">로그인
                                </button>
                                <br style="line-height:50%">
                                <p class="login-text" style="position: fixed">
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="/findId" style="color: #008000">아이디 찾기</a>&nbsp;<span style="color: #9e9e9e">|</span>
                                    <a href="/resetPw" style="color: #008000">비밀번호 찾기</a>&nbsp;<span style="color: #9e9e9e">|</span>
                                    <a href="/join" style="color: #008000">회원가입</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

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
        $("#form").off().on("submit", function(event) {
            const id = $("#id");
            const pw = $("#pw");
            const requiredMsg = $("#requiredMsg");
            const notCorrectMsg = $("#notCorrectMsg");
            let errorMsg = "";

            if (!id.val() && !pw.val()) {
                errorMsg = "아이디와 비밀번호를 입력하세요.";
                id.focus();
            } else if (!id.val()) {
                errorMsg = "아이디를 입력하세요.";
                id.focus();
            } else if (!pw.val()) {
                errorMsg = "비밀번호를 입력하세요.";
                pw.focus();
            }

            if (errorMsg) {
                event.preventDefault();
                notCorrectMsg.hide();
                requiredMsg.text(errorMsg).show();
            }
        });
    });
</script>

</body>
</html>