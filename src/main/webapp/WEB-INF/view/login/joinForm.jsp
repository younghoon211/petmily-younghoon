<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Petmily-Don't buy, Do Adopt</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!-- Loding font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

        <!-- Custom Styles -->
        <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
    <link rel="stylesheet" href="/resources/css/join.css">
    <script src="/resources/js/joinCheck.js"></script>
</head>
<body>

<div class="section">
    <div class="container">
        <div class="form">
            <div class="left-side">
<span class="brand">
                        <a href="${pageContext.request.contextPath}/"
                           style="text-decoration: none; color: black">
                            <h4><b>Petmily</b></h4><br>
                        </a>
                	</span>

    <div>
        <form action="/join" method="post">
            <div class="form-inputs">
                            <input type="text" name="id" placeholder="아이디 (대문자 및 특수문자 불가)" required="required"
                                   pattern="^[a-z0-9]+$"
                                   oninput="this.value = this.value.replace(/[^0-9a-z]/g, '');">
                        <div class="password">
                            <input id="pw" name="pw" type="password" placeholder="비밀번호" required="required">
                            <input id="passwordCheck" name="confirmPw" type="password" placeholder="비밀번호 확인"
                                       required="required">
                        </div>
                            <input type="text" name="name" placeholder="이름" required="required">

                            <input type="date" name="birth" placeholder="생년월일" required="required" min="1900-01-01" max="2023-12-31">

                            <select name="gender" class="form-control" style="font-size: 15px; padding-left: 10px">
                                <option value="" selected>성별</option>
                                <option value="M">남자</option>
                                <option value="F">여자</option>
                            </select>

                            <input type="text" name="email" placeholder="이메일 주소 (예: petmily@naver.com)" required="required"
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">

                            <input type="tel" name="phone" placeholder="연락처 (예: 010-1234-5678)" required="required"
                                   pattern="^010-\d{4}-\d{4}$"
                                   oninput="this.value = this.value.replace(/[^0-9-]/g, '');">

                        <div class="login">
                            <br><button type="submit" class="btn btn-lg btn-block btn-success">회원가입</button>
                        </div><br>
                            <p class="login-text">계정이 이미 있습니까? <a href="/login">login</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>