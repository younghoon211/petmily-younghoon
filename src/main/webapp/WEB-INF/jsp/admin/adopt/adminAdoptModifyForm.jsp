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
    <link
            href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap"
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

<%@ include file="/WEB-INF/jsp/include/header.jspf" %>

<body>

<section class="hero-wrap hero-wrap-2"
         style="background-image: url('/resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <p class="breadcrumbs mb-2">
                    <span class="mr-2"><span>Adopt Modify<i class="ion-ios-arrow-forward"></i></span></span>
                </p>
                <h1 class="mb-0 bread">입양 수정</h1>
            </div>
        </div>
    </div>
</section>

<div class="container survey">
    <h1 id="title" class="text-center">입양 수정</h1>
    <br>
    <form action="/admin/adopt/modify" method="post">

        <div class="form-group">
            <label>회원ID / 회원이름 (회원번호)</label>
            <select name="mNumber" class="form-control" required>
                <c:forEach var="m" items="${member}">
                    <option value="${m.getMNumber()}"
                            <c:if test="${adopt.getMNumber() eq m.getMNumber()}">selected="selected"</c:if>
                    >${m.id} / ${m.name} (${m.getMNumber()})
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>동물이름 (동물번호)</label>
            <select name="abNumber" class="form-control" required>
                <c:forEach var="ab" items="${abandonedAnimal}">
                    <option value="${ab.abNumber}"
                            <c:if test="${adopt.abNumber eq ab.abNumber}">selected="selected"</c:if>>
                            ${ab.name} (${ab.abNumber})
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>거주지</label>
            <select id="residence" name="residence" class="form-control" required>
                <option>서울특별시</option>
                <option>경기도</option>
                <option>인천광역시</option>
                <option>강원도</option>
                <option>경상북도</option>
                <option>경상남도</option>
                <option>부산광역시</option>
                <option>대구광역시</option>
                <option>울산광역시</option>
                <option>전라남도</option>
                <option>전라북도</option>
                <option>광주광역시</option>
                <option>충청남도</option>
                <option>충청북도</option>
                <option>대전광역시</option>
                <option>세종특별자치시</option>
                <option>제주특별자치도</option>
            </select>
        </div>

        <br>
        <div class="radiobuttons">
            <p>결혼여부</p>
            <ul style="list-style: none;">
                <c:if test="${adopt.maritalStatus == 'married'}">
                    <li class="radio">
                        <input name="maritalStatus" value="married" id="married"
                               type="radio" class="userRatings" required checked>
                        <label for="married">&nbsp;기혼</label></li>
                    <li class="radio">
                        <input name="maritalStatus" value="single" id="single"
                               type="radio" class="userRatings" required>
                        <label for="single">&nbsp;미혼</label>
                    </li>
                </c:if>
                <c:if test="${adopt.maritalStatus == 'single'}">
                    <li class="radio">
                        <input name="maritalStatus" value="married" id="married2"
                               type="radio" class="userRatings" required>
                        <label for="married2">&nbsp;기혼</label></li>
                    <li class="radio">
                        <input name="maritalStatus" value="single" id="single2"
                               type="radio" class="userRatings" required checked>
                        <label for="single2">&nbsp;미혼</label>
                    </li>
                </c:if>
            </ul>
        </div>

        <div class="form-group">
            <label>직업</label>
            <input type="text" name="job" class="form-control" placeholder="직업을 입력해주세요." value="${adopt.job}"
                   required>
        </div>

        <div class="form-group">
            <label>상태</label>
            <select id="status" name="status" class="form-control" required>
                <option>완료</option>
                <option>거절</option>
                <option>처리중</option>
            </select>
        </div>

        <br/>
        <br/>
        <div class="arr">
            <button id="submit" type="submit" class="btn btn-secondary">제출</button>
        </div>
        <br/>
        <br/>
        <input type="hidden" name="adNumber" value="${adopt.adNumber}">
    </form>
</div>

<script>
    document.getElementById("residence").value = "${adopt.residence}";
    document.getElementById("status").value = "${adopt.status}";
</script>

<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>

<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none"
                stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none"
                stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/>
    </svg>
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
</body>
</html>