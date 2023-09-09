<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Petmily - Don't buy, Do Adopt</title>

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

    <h1 id="title" class="text-center">후원 관리 수정</h1>
    <p id="description" class="text-center"></p> <br><br>

    <form method="post" action="/admin/donation/update?dNumber=${updateForm.getDNumber()}">

        <label>후원받을 동물 (번호)</label>
        <select class="form-control" name="abNumber" required>
            <c:forEach var="ab" items="${abandonedAnimals}">
                <option value="${ab.abNumber}"
                        <c:if test="${updateForm.abNumber eq ab.abNumber}">selected</c:if>
                >${ab.name} (${ab.abNumber})</option>
            </c:forEach>
        </select><br>

        <label>회원 닉네임 (번호)</label>
        <select class="form-control" name="mNumber" required>
            <c:forEach var="m" items="${members}">
                <option value="${m.getMNumber()}"
                        <c:if test="${updateForm.getMNumber() eq m.getMNumber()}">selected</c:if>
                >${m.name} (${m.getMNumber()})
                </option>
            </c:forEach>
        </select><br>

        <div class="form-row">
            <div class="col">
                <label>은행명</label>
                <select name="bank" id="bank" class="form-control">
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
                <label>예금주 (동물이름/닉네임)</label>
                <input name="accountHolder"
                       type="text" class="form-control" value="${updateForm.accountHolder}"
                       required>
            </div>

            <div class="col">
                <label>계좌번호</label>
                <input name="accountNumber" type="text"
                       class="form-control" value="${updateForm.accountNumber}">
            </div>
        </div>
        <br>

        <div class="form-group">
            <label>후원금액 <small style="color: red">※ 회원들에게는 최소금액 10,000원이상으로 명시</small></label>
            <input type="text" name="donaSum" class="form-control"
                   value="${updateForm.donaSum}">
        </div>
        <br>

        <div class="modal-footer justify-content-center">
            <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
            <button id="submit" type="submit" class="btn btn-primary">후원정보 수정</button>
        </div>
        <br>
        <input type="hidden" name="dNumber" value="${updateForm.getDNumber()}">
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

<script>
    document.getElementById("bank").value = "${updateForm.bank}";
</script>

<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>
</body>
</html>