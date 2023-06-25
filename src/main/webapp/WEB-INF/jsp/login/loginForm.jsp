<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/login.css">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Loding font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">
    <script>
        window.onload = function() {
            var input = document.getElementById('myInput');
            input.focus();

            setInterval(function() {
                input.classList.toggle('blink');
            }, 500);
        };
    </script>
</head>
<body>

<style>
    .error {
        color: red;
        font-size: 11.5px;
        position: fixed;
    }
</style>

<div class="section">
    <div class="container">
        <div class="form">
            <div class="left-side">
                <form action="/login" method="post">
                    <div class="login" id="login">
                        <b><a href="${pageContext.request.contextPath}/"
                              style="text-decoration: none; color: black"><h2>Petmily</h2></a></b>
                    </div>
                    <div class="form-inputs">
                        <input type="text" name="id" placeholder="아이디" required="required" style="padding-left: 10px"
                        value="${param.id}"/>
                        <div class="password">
                            <input type="password" name="pw" placeholder="비밀번호" required="required"
                                   style="padding-left: 10px"
                                    <c:if test="${param.error == 'true'}">
                                        id="myInput"
                                    </c:if>
                            />
                        </div>
                        <span class="error">
                            <c:if test="${param.error == 'true'}"><strong>아이디 또는 비밀번호가 일치하지 않습니다.</strong><br></c:if></span>
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
</body>
</html>