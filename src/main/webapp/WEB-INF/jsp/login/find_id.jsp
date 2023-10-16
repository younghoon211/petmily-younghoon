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
</head>
<body>

<div class="container">
    <form class="contactForm">
        <br><br><br><br><br><br><br><br><br><br>
        <div class="form-inputs">
            <div class="row no-gutters" style="margin: 0 auto; width:30%">
                <div style="text-align: center">
                    <a href="/">
                        <h4><span class="flaticon-pawprint-1 mr-2"
                                  style="color: #00bd56"></span><b>Petmily</b></h4><br>
                    </a>
                </div>
                <div class="col-md-12"><br>
                    <div class="form-group">
                        <span class="label">이메일</span>
                        <input type="text" class="form-control"
                               id="email"
                               placeholder="이메일 주소를 입력하세요."
                               maxlength="30"
                               style="font-weight: normal" autofocus>
                    </div>
                </div>

                <div class="login col-md-7" style="margin: auto">
                    <br><br>
                    <button type="button" id="submit" class="btn btn-lg btn-block btn-success">아이디 찾기
                    </button>
                </div>
                <div class="login-text" style="font-size: smaller; text-align: center">
                    <br>아이디가 생각나셨나요? <a href="/login">Login</a>
                </div>
            </div>
        </div>
    </form>
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
        $('#submit').off('click').on('click', function () {
            const email = $('#email').val().trim();

            if (!email) {
                $('#email').focus();
                alert("이메일을 입력하세요.");
            } else {
                sendMail();
            }
        });

        function sendMail() {
            const email = $('#email').val().trim();

            $.ajax({
                type: 'POST',
                url: '/findId',
                data: {email: email},
                success: function (result) {
                    console.log("email result=" + result);

                    if (result === "SUCCESS") {
                        alert("이메일로 아이디를 발송했습니다.\n이메일 확인 후 로그인하세요.");
                        window.location.replace('/login');

                        sendEmailVal();
                    } else {
                        alert("존재하지 않는 회원 정보입니다.\n이메일 주소를 다시 한번 확인하세요.");
                        $('#email').focus();
                    }
                },

                error: function () {
                    console.log("email error");
                }
            });
        }

        function sendEmailVal() {
            const email = $('#email').val().trim();

            $.ajax({
                type: 'POST',
                url: '/findId/sendMail',
                data: {email: email}
            });
        }
    });
</script>

</body>
</html>
