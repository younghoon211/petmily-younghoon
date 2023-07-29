<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Petmily - Don't buy, Do Adopt</title>

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

        .arr {
            margin: auto;
            display: block;
            width: 100px;
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
<%@ include file="../include/header.jspf" %>

<%-- 현재 페이지 --%>
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <p class="breadcrumbs mb-2">
                    <span class="mr-2"><span>Adopt / Temp - Submit<i class="ion-ios-arrow-forward"></i></span></span>
                </p>
                <h1 class="mb-0 bread">입양 / 임시보호하기</h1>
            </div>
        </div>
    </div>
</section>

<!-- servey form -->
<div class="container survey">
    <h1 id="title" class="text-center">입양 / 임시보호 신청서</h1>
    <p class="text-left">* 임시 보호의 최소 기간은 2개월입니다.</p>

    <form action="/abandoned_animal/auth/adopt_temp?abNumber=${param.abNumber}" method="post">

        <div class="form-group">
            <label>* 가족이 될 동물</label>
            <input type="text" class="form-control" value="${animal.name}" readonly>
        </div>
        <div class="form-group">
            <label>* 닉네임</label>
            <input type="text" class="form-control" value="${memberName}" readonly>
        </div>
        <br>
        <div class="radiobuttons">
            <p>1. 선택</p>
            <ul style="list-style: none;">
                <c:if test="${animal.animalState ne '임보'}">
                    <li class="radio">
                        <input name="adoptOrTemp" value="adopt" id="adopt"
                               type="radio" class="userRatings" required checked>
                        <label for="adopt">&nbsp;입양</label>
                    </li>
                    <li class="radio">
                        <input name="adoptOrTemp" value="temp" id="temp"
                               type="radio" class="userRatings" required>
                        <label
                                for="temp">&nbsp;임시보호</label>
                    </li>
                </c:if>
                <c:if test="${animal.animalState eq '임보'}">
                    <li class="radio">
                        <input name="adoptOrTemp" value="adopt"
                               type="radio" class="userRatings" required checked>
                        <label>&nbsp;입양</label>
                        <span style="color: red">&nbsp;${animal.getName()}(이)는 현재 임시보호중이기 때문에 입양만 가능합니다.</span>
                    </li>
                    <li class="radio">
                        <input name="adoptOrTemp" value="temp"
                               type="radio" class="userRatings" required disabled>
                        <label>&nbsp;임시보호</label>
                    </li>
                </c:if>
            </ul>
        </div>
        <br>

        <div class="form-group">
            <label>2. 거주 지역</label>
            <select name="residence" class="form-control" required>
                <option>서울특별시</option>
                <option>경기도</option>
                <option>인천광역시</option>
                <option>대전광역시</option>
                <option>대구광역시</option>
                <option>광주광역시</option>
                <option>부산광역시</option>
                <option>울산광역시</option>
                <option>세종특별자치시</option>
                <option>강원특별자치도</option>
                <option>제주특별자치도</option>
                <option>충청북도</option>
                <option>충청남도</option>
                <option>경상북도</option>
                <option>경상남도</option>
                <option>전라북도</option>
                <option>전라남도</option>
            </select>
        </div>

        <br>
        <div class="radiobuttons">
            <p>3. 결혼 여부</p>
            <ul style="list-style: none;">
                <li class="radio">
                    <input name="maritalStatus" value="married" id="married"
                           type="radio" class="userRatings" required checked>
                    <label for="married">&nbsp;기혼</label>
                </li>
                <li class="radio">
                    <input name="maritalStatus" value="single" id="single"
                           type="radio" class="userRatings" required>
                    <label for="single">&nbsp;미혼</label>
                </li>
            </ul>
        </div>

        <div class="form-group">
            <label id="job-label">4. 직업</label>
            <input id="job" type="text" name="job" class="form-control"
                   placeholder="직업을 입력해주세요." required>
        </div>

        <br>
        <br>
        <div class="arr">
            <button id="submit" type="submit" class="btn btn-primary">신청하기</button>
        </div>
        <br>
        <br>

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
<script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="/resources/petsitting-master/js/google-map.js"></script>
<script src="/resources/petsitting-master/js/main.js"></script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>