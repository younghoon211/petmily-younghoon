<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" type="text/css" href="styles.css">
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
        <div class="row no-gutters" style="margin: 0 auto; width:50%">
            <div class="contact-wrap w-100 p-md-5 p-4">
                <form action="/admin/member/modify" method="POST" class="contactForm">
                    <div class="row">

                        <input type="hidden" name="mNumber" value="${modifyForm.getMNumber()}">

                        <div class="col-md-12 form-group">
                            <label class="label">아이디</label>
                            <input type="text"
                                   class="form-control" name="id"
                                   value="${modifyForm.id}"
                                   id="inputId"
                                   readonly>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">비밀번호</label>
                            <input type="text"
                                   class="form-control" name="pw" maxlength="16"
                                   value="${modifyForm.pw}" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">닉네임</label>
                            <input type="text"
                                   class="form-control" name="name"
                                   value="${modifyForm.name}" maxlength="10" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">생년월일</label>
                            <input class="form-control" type="date" name="birth" placeholder="생년월일"
                                   min="1900-01-01" max="2099-12-31" value="${modifyForm.birth}" required>
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
                            <input type="email" maxlength="30"
                                   class="form-control" name="email"
                                   value="${modifyForm.email}" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">연락처</label>
                            <input type="tel" class="form-control" name="phone"
                                   value="${modifyForm.phone}" pattern="^010\d{8}$" maxlength="11"
                                   oninput="this.value = this.value.replace(/[^0-9]/g, '');" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="label">회원 등급</label>
                            <input type="text"
                                   class="form-control" name="grade"
                                   value="${modifyForm.grade}" readonly>
                        </div>
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>&nbsp;
                        <button type="submit" class="btn btn-primary">회원정보 수정</button>
                    </div>
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
    document.getElementById("gender").value = "${modifyForm.gender}";
</script>

<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>
</body>
</html>