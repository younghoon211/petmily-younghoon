<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="/resources/petsitting-master/css/animate.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/magnific-popup.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/jquery.timepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/flaticon.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/style.css">

    <style>
        textarea {
            width: 82%;
        }

        .survey {
            font-family: 'Raleway', sans-serif;
            margin-top: 25px;
        }

        h1 {
            font-weight: 400;
            font-family: 'Merriweather', serif
        }

        #description {
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
                <h1 class="mb-0 bread">후원 관리</h1>
            </div>
        </div>
    </div>
</section>
<br>

<div class="container survey">

    <h1 id="title" class="text-center">후원 관리 추가</h1>
    <p id="description" class="text-center"></p> <br><br>

    <form action="/admin/donation/insert" method="post" id="form">

        <label>후원받을 동물 (번호)</label>
        <select class="form-control" name="abNumber" required>
            <c:forEach var="ab" items="${abandonedAnimals}">
                <option value="${ab.abNumber}">${ab.name} (${ab.abNumber})</option>
            </c:forEach>
        </select><br>

        <label>회원 닉네임 (번호)</label>
        <select class="form-control" name="mNumber" required>
            <c:forEach var="m" items="${members}">
                <option value="${m.getMNumber()}">${m.name} (${m.getMNumber()})</option>
            </c:forEach>
        </select><br>

        <div class="form-row">
            <div class="col">
                <label>은행명</label> <select name="bank" class="form-control">
                <option>KEB하나은행</option>
                <option>SC제일은행</option>
                <option>국민은행</option>
                <option>신한은행</option>
                <option>외환은행</option>
                <option>우리은행</option>
                <option>한국씨티은행</option>
                <option>IBK기업은행</option>
                <option>농협</option>
                <option>수협</option>
                <option>한국산업은행</option>
                <option>한국수출입은행</option>
                <option>우체국</option>
                <option>토스</option>
                <option>카카오뱅크</option>
                <option>경남은행</option>
                <option>광주은행</option>
                <option>대구은행</option>
                <option>부산은행</option>
                <option>전북은행</option>
                <option>제주은행</option>
            </select>

            </div>
            <div class="col">
                <label>예금주</label>
                <input name="accountHolder" id="accountHolder"
                       type="text" class="form-control" placeholder="후원할 동물이름/닉네임"
                       required>
            </div>

            <div class="col">
                <label>계좌번호</label>
                <input name="accountNumber" type="text" id="accountNumber"
                       class="form-control" placeholder="계좌번호" required>
            </div>
        </div>
        <br><br>

        <div class="form-row">
            <div class="col">
                <label>
                    <input name="donaSum" value="10000"
                           type="radio" class="userRatings" checked> 10,000원
                </label>
            </div>
            <div class="col">
                <label>
                    <input name="donaSum" value="30000"
                           type="radio" class="userRatings"> 30,000원
                </label>
            </div>
            <div class="col">
                <label>
                    <input name="donaSum" value="50000"
                           type="radio" class="userRatings"> 50,000원
                </label>
            </div>
            <div class="col">
                <label>
                    <input name="donaSum" value="70000"
                           type="radio" class="userRatings"> 70,000원
                </label>
            </div>
            <div class="col">
                <label>
                    <input name="donaSum" value="100000"
                           type="radio" class="userRatings"> 100,000원
                </label>
            </div>
            <div class="col-md-auto">
                <input name="donaSum" type="text" id="customAmount" placeholder="직접 입력"
                       oninput="this.value = this.value.replace(/[^0-9]/g, '');"> 원
                <br><small style="color: red">회원들에겐 최소 10,000원이상으로 명시</small>
            </div>
        </div>
        <br><br>

        <div class="modal-footer justify-content-center">
            <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
            <button type="submit" class="btn btn-primary">후원정보 추가</button>
        </div>
        <br>
    </form>
    <div class="info-wrap w-100 p-5 img"></div>
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
        const radioButtons = $('input[type="radio"].userRatings');

        // "직접 입력"에 값 입력 시 라디오 버튼 체크 해제
        $('#customAmount').off().on('input', function () {
            radioButtons.prop('checked', false);
        });

        // 라디오 버튼 클릭시 "직접 입력" 값 초기화
        radioButtons.each(function () {
            $(this).off().on('click', function () {
                $('#customAmount').val("");
            });
        });

        // 후원금액 입력 검증
        $("#form").off().on("submit", function (event) {
            let radioChecked = false;
            const radioButtons = $("[name='donaSum']");

            radioButtons.each(function () {
                if ($(this).prop('checked')) {
                    radioChecked = true;
                    return false;
                }
            });

            const notInputAmount = !radioChecked && $('#customAmount').val().trim() === "" && $('#accountHolder').val().trim() !== "" && $('#accountNumber').val().trim() !== "";

            if (notInputAmount) {
                event.preventDefault();

                alert("후원 금액을 선택하거나 입력해주세요.");
                $('#customAmount').focus();
            }
        });
    });
</script>

<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>

</body>
</html>