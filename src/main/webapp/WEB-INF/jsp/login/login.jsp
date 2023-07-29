<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Petmily - Don't buy, Do Adopt</title>

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
            font-size: 11.5px;
            position: fixed;
        }
    </style>
</head>

<body>
<div class="section">
    <div class="container">
        <div class="form">
            <div class="left-side">
                <form action="/login" method="post">
                    <div class="login" id="login">
                        <a class="navbar-brand" href="/">
                            <h4><span class="flaticon-pawprint-1 mr-2"></span><b>Petmily</b></h4>
                        </a>
                    </div>
                    <div class="form-inputs">
                        <input type="text" name="id" placeholder="아이디" required style="padding-left: 10px"
                               value="${param.id}" maxlength="15"
                        <c:if test="${param.error ne 'true'}">
                               autofocus
                        </c:if>
                        >
                        <div class="password">
                            <input type="password" name="pw" placeholder="비밀번호" required
                                   style="padding-left: 10px" maxlength="16"
                            <c:if test="${param.error eq 'true'}">
                                   id="myInput"
                            </c:if>
                            >
                        </div>
                        <span class="error">
                            <c:if test="${param.error eq 'true'}"><strong>아이디 또는 비밀번호가 일치하지 않습니다.</strong><br></c:if></span>
                        <br>
                        <div class="login">
                            <div>
                                <button type="submit" class="btn btn-lg btn-block btn-success">로그인</button>
                                <br>
                            </div>
                            <p class="login-text">계정이 없으신가요? <a href="/join">회원가입</a></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    window.onload = function () {
        var input = document.getElementById('myInput');
        input.focus();

        setInterval(function () {
            input.classList.toggle('blink');
        }, 500);
    };
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>