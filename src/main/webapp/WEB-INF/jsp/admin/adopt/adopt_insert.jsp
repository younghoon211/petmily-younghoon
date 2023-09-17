<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Merriweather"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway"
          rel="stylesheet">
    <link rel="stylesheet" href="/resources/petsitting-master/css/animate.css">
    <link rel="stylesheet"
          href="/resources/petsitting-master/css/owl.carousel.min.css">
    <link rel="stylesheet"
          href="/resources/petsitting-master/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/magnific-popup.css">
    <link rel="stylesheet"
          href="/resources/petsitting-master/css/bootstrap-datepicker.css">
    <link rel="stylesheet"
          href="/resources/petsitting-master/css/jquery.timepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/flaticon.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/style.css">

    <style>
        textarea {
            width: 82%;
        }

        .radiobuttons {
            display: flex;
        }

        .survey {
            font-family: 'Raleway', sans-serif;
            margin-top: 25px;
        }

        h1 {
            font-weight: 400;
            font-family: 'Merriweather', serif
        }

        @media ( max-width: 500px) {
            .checkboxlabel {
                display: block;
            }
        }
    </style>
</head>

<body>
<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none"
                stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none"
                stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/>
    </svg>
</div>

<!-- header -->
<%@ include file="../../include/header.jspf" %>

<%-- 현재 페이지 --%>
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../../resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <p class="breadcrumbs mb-2">
                    <span class="mr-2"><span>Adopt Insert<i class="ion-ios-arrow-forward"></i></span></span>
                </p>
                <h1 class="mb-0 bread">입양 추가</h1>
            </div>
        </div>
    </div>
</section>
<br>
<div class="container survey">
    <h1 id="title" class="text-center">입양 추가</h1>
    <br>
    <form action="/admin/adopt/insert" method="post">

        <div class="form-group">
            <label>회원ID / 닉네임 (회원번호)</label>
            <select name="mNumber" class="form-control" required>
                <c:forEach var="m" items="${members}">
                    <option value="${m.getMNumber()}">${m.id} / ${m.name} (${m.getMNumber()})</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>동물이름 (동물번호) <span
                    style="color: red"><small>&nbsp;※ 입양/임보 완료됐거나, 승인 대기중인 동물 제외 (유기동물 관리에서 수정 가능)</small></span></label>
            <select name="abNumber" class="form-control" required>
                <c:forEach var="ab" items="${onlyProtectedAnimals}">
                    <option value="${ab.abNumber}">${ab.name} (${ab.abNumber})</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>거주지</label>
            <select name="residence" class="form-control" required>
                <c:forEach var="residence" items="${residences}">
                    <option value="${residence}">${residence}</option>
                </c:forEach>
            </select>
        </div>

        <br>
        <div class="radiobuttons">
            <p>결혼여부</p>
            <ul style="list-style: none;">
                <li class="radio">
                    <input name="maritalStatus" value="married" id="married"
                           type="radio" class="userRatings" required checked>
                    <label for="married">&nbsp;기혼</label></li>
                <li class="radio">
                    <input name="maritalStatus" value="single" id="single"
                           type="radio" class="userRatings" required>
                    <label for="single">&nbsp;미혼</label>
                </li>
            </ul>
        </div>

        <div class="form-group">
            <label for="job">직업</label>
            <input id="job" type="text" name="job" class="form-control" placeholder="직업을 입력해주세요." maxlength="14"
                   required>
        </div>

        <div class="form-group">
            <label>상태</label>
            <select name="status" class="form-control" required>
                <option>완료</option>
                <option>거절</option>
                <option>처리중</option>
            </select>
        </div>
        <div class="modal-footer justify-content-center">
            <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
            <button type="submit" class="btn btn-primary">입양 추가</button>
        </div>
        <br>
    </form>
</div>
<div class="info-wrap w-100 p-5 img"></div>

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

<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>
</body>
</html>