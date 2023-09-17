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
<br><br>
<div class="container survey">
    <h1 id="title" class="text-center">입양 / 임시보호 신청서</h1><br>

    <br><br>
    <div class="container">

        <form action="/abandonedAnimal/auth/adoptTemp?abNumber=${param.abNumber}" method="post">
            <div class="form-group">
                <label>가족이 될 동물</label>
                <input type="text" class="form-control" value="${abAnimal.name}" readonly>
            </div>
            <div class="form-group">
                <label>회원 닉네임</label>
                <input type="text" class="form-control" value="${authUser.name}" readonly>
            </div>
            <br>
            <div class="radiobuttons">
                <p>1. 선택</p>
                <ul style="list-style: none;">
                    <c:if test="${abAnimal.animalState ne '임보' && temp.status ne '처리중'}">
                        <li class="radio">
                            <input name="adoptOrTemp" value="adopt" id="adopt"
                                   type="radio" class="userRatings" required checked>
                            <label for="adopt">&nbsp;입양</label>
                        </li>
                        <li class="radio">
                            <input name="adoptOrTemp" value="temp" id="temp"
                                   type="radio" class="userRatings" required>
                            <label for="temp">&nbsp;임시보호</label>
                        </li>
                    </c:if>

                    <c:if test="${abAnimal.animalState eq '임보' && temp.status ne '처리중'}">
                        <li class="radio">
                            <input name="adoptOrTemp" value="adopt"
                                   type="radio" class="userRatings" required checked>
                            <label>&nbsp;입양</label>
                            <span style="color: red">&nbsp;${abAnimal.name}(이)는 현재 임시보호중이기 때문에 입양 신청만 가능합니다.</span>
                        </li>
                        <li class="radio">
                            <input name="adoptOrTemp" value="temp"
                                   type="radio" class="userRatings" required disabled>
                            <label>&nbsp;임시보호</label>
                        </li>
                    </c:if>

                    <c:if test="${abAnimal.animalState ne '임보' && temp.status eq '처리중'}">
                        <li class="radio">
                            <input name="adoptOrTemp" value="adopt"
                                   type="radio" class="userRatings" required checked>
                            <label>&nbsp;입양</label>
                            <span style="color: red">&nbsp;${abAnimal.name}(이)는 현재 임시보호 신청 처리중이기 때문에 입양 신청만 가능합니다.</span>
                        </li>
                        <li class="radio">
                            <input name="adoptOrTemp" value="temp"
                                   type="radio" class="userRatings" required disabled>
                            <label>&nbsp;임시보호</label>
                        </li>
                    </c:if>
                </ul>
            </div>

            <div id="tempInfo">
                <!-- '임보' 선택한 경우 여기에  임보 시작날짜, 기간 폼 -->
            </div>

            <div class="form-group">
                <label>2. 거주 지역</label>
                <select name="residence" class="form-control" required>
                    <c:forEach var="residence" items="${residences}">
                        <option value="${residence}">${residence}</option>
                    </c:forEach>
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
            <div class="modal-footer" style="justify-content: center">
                <button type="button" class="btn btn-secondary" onclick="location.href='/abandonedAnimal/detail?abNumber=${param.abNumber}'">취소</button>
                <button type="submit" class="btn btn-primary">신청하기</button>
            </div>

            <input name="mNumber" value="${authUser.getMNumber()}" hidden>
            <input name="abNumber" value="${param.abNumber}" hidden>
        </form>
    </div>
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

<script>
    $(document).ready(function () {

        let tempInfo = document.getElementById('tempInfo');

        $("input[name='adoptOrTemp']").change(function () {
            let selectedState = $(this).val();
            showOrHideForm(selectedState);
        });

        function showOrHideForm(animalState) {
            if (animalState === "temp") {
                tempInfo.innerHTML = tempInfoHtml;
            } else {
                tempInfo.innerHTML = "";
            }
        }

        const tempInfoHtml = `
                                <div class="form-group">
                                    <label>임시보호 시작 날짜</label>
                                        <input type="date" name="tempDate" min="1900-01-01" max="2099-12-31" class="form-control" required>
                                </div>

                                <div class="form-group">
                                    <label>임시보호 기간<small style="color: red">&nbsp; ※ 임시 보호 가능한 최소 기간은 1개월입니다.</small></label>
                                        <input type="text" name="tempPeriod" class="form-control" placeholder="임시보호 개월수를 입력해주세요." min="1">
                                </div>

        `;
    });
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>