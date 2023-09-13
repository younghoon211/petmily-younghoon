<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Petmily - Don't buy, Do Adopt</title>

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
                <h1 class="mb-0 bread">회원 탈퇴</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="row justify-content-center">
            <h2>회원 탈퇴</h2>
        </div>
        <br>
        <div class="row no-gutters" style="margin: 0 auto; width:50%">
            <div class="contact-wrap w-100 p-md-5 p-4">

                <form action="/member/auth/withdraw" method="POST" class="contactForm">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label">아이디</label>
                                <input type="text" class="form-control" value="${authUser.id}" readonly>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="label">비밀번호</label>
                                <input type="password" class="form-control" name="pw" id="pw"
                                       placeholder="비밀번호를 입력해주세요." autofocus>
                                <span id="message" style="color: red; display: none; font-size: xx-small"></span>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>&nbsp;&nbsp;

                        <button type="button" id="withdrawButton" class="btn btn-danger">탈퇴하기</button>

                    </div>
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
    $("#withdrawButton").on("click", function () {
        const pw = $("#pw").val();
        const message = $("#message");

        if (!pw) {
            message.text("비밀번호를 입력하세요.").show();
            return;
        }

        if (confirm('회원 탈퇴 시 모든 정보가 삭제됩니다. 정말로 탈퇴하시겠습니까?')) {
            $.ajax({
                type: 'POST',
                url: '/member/auth/withdraw',
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({pw: pw}),
                dataType: 'text',
                success: function (result) {
                    if (result === 'SUCCESS') {
                        console.log("SUCCESS")
                        alert("탈퇴가 완료되었습니다.")
                        location.href = "/";
                    } else if (result === 'NOT_CORRECT') {
                        console.log("NOT_CORRECT");
                        alert("비밀번호가 일치하지 않습니다.")
                    }
                }
            });
        }
    });
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>

</body>
</html>