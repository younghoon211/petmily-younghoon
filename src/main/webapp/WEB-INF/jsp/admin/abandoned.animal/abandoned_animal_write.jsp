<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Petmily - Don't buy, Do Adopt</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/animate.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/magnific-popup.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/jquery.timepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/flaticon.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/style.css">
</head>

<body>
<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"></circle>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"></circle>
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
                <p class="breadcrumbs mb-2"><span>Abandoned Animal - Write<i class="ion-ios-arrow-forward"></i></span>
                </p>
                <h1 class="mb-0 bread">유기동물 관리 - 글 작성하기</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-lg-12">

                <!-- 폼 시작 -->

                <form class="form" method="post" action="/admin/abandonedAnimal/write" enctype='multipart/form-data'>
                    <div class="modal-body">

                        <div class="row justify-content-start">
                            <div class="form-group col-md-6 col-lg-6">
                                <label for="name">동물이름</label>
                                <input type="text" class="form-control" name="name" id="name" placeholder="이름을 입력해주세요"
                                       autofocus maxlength="10" required>
                            </div>
                            <div class="form-group col-md-6 col-lg-6">
                                <label for="admissionDateInput">입소 날짜</label>
                                <input type="date" class="form-control" name="admissionDate" id="admissionDateInput"
                                       min="1900-01-01" max="2099-12-31" required>
                            </div>
                        </div>

                        <hr color="#eee" width="100%">

                        <div class="form-group col-md-6 col-lg-6">
                            <label for="speciesInput"><b>종</b></label><br>
                            <div id="speciesInput" class="form-check form-check-inline">
                                <span><input type="radio" class="form-check-input" name="species" id="speciesInput1" value="개" checked><label for="speciesInput1">개</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="species" id="speciesInput2" value="고양이"><label for="speciesInput2">고양이</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="species" id="speciesInput3" value="기타"><label for="speciesInput3">기타</label></span>
                            </div>
                        </div>
                        <div class="form-group col-md-6 col-lg-6">
                            <label for="genderInput"><b>성별</b></label><br>
                            <div id="genderInput" class="form-check form-check-inline">
                                <span><input type="radio" class="form-check-input" name="gender" id="genderInput1" value="M" checked><label for="genderInput1">수컷</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="gender" id="genderInput2" value="F"><label for="genderInput2">암컷</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="gender" id="genderInput3" value="-"><label for="genderInput3">모름</label></span>
                            </div>
                        </div>
                        <div class="form-group col-md-6 col-lg-6">
                            <label for="animalState"><b>상태</b></label><br>
                            <div id="animalState" class="form-check form-check-inline">
                                <span><input type="radio" class="form-check-input" name="animalState" id="animalState1" value="입양" checked><label for="animalState1">입양</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="animalState" id="animalState2" value="임보"><label for="animalState2">임시보호</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="animalState" id="animalState3" value="보호"><label for="animalState3">보호</label></span>
                            </div>
                        </div>

                        <hr color="#eee" width="100%">

                        <div class="row justify-content-start">
                            <div class="form-group col-md-6 col-lg-4">
                                <label for="kindInput">품종</label>
                                <input type="text" class="form-control" name="kind" id="kindInput" placeholder="모르는 경우 '모름' 작성" maxlength="14" required>
                            </div>
                            <div class="form-group col-md-6 col-lg-4">
                                <label for="ageInput">나이</label>
                                <input type="number" class="form-control" name="age" id="ageInput" placeholder="숫자만 입력해주세요" min="1" max="100" required>
                            </div>
                            <div class="form-group col-md-6 col-lg-4">
                                <label for="weightInput">몸무게</label>
                                <input type="number" class="form-control" name="weight" id="weightInput" placeholder="숫자만 입력해주세요" min="0" max="1000" step="any" required>
                            </div>
                        </div>

                        <hr color="#eee" width="100%">

                        <div class="row justify-content-between">
                            <div class="form-group col-md-6 col-lg-4">
                                <label for="locationInput">발견 장소</label>
                                <input type="text" class="form-control" name="location" id="locationInput" placeholder="모르는 경우 '모름' 작성" maxlength="14" required>
                            </div>

                            <div class="form-group col-md-6 col-lg-4">
                                <label for="shelter">
                                    보호중인 보호소
                                </label>
                                <select name="sNumber" id="shelter" class="form-control">
                                    <c:forEach var="s" items="${shelterList}">
                                        <option value="${s.getSNumber()}">${s.groupName} (${s.location}) - ${s.getSNumber()}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group col-md-6 col-lg-4">
                                <label>특이사항</label>
                                <input type="text" class="form-control" name="uniqueness" placeholder="특이사항" maxlength="30" required>
                            </div>
                        </div>

                        <hr color="#eee" width="100%">

                        <textarea rows="5" class="form-control" name="description" id="description" placeholder=" 소개글" maxlength="30" required></textarea>

                        <!-- 첨부파일 -->
                        <div class="custom-file form-control-sm mt-3">
                            <input type="file" name="file" id="file" accept="image/*">
                            <br>
                            <div id="fileDel">
                                <br>
                                <button type="button" class="btn-danger">파일 삭제</button>
                                <br><br>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
                            <button type="submit" class="btn btn-primary">유기동물 추가</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</section>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
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
<script src="/resources/petsitting-master/js/google-map.js"></script>
<script src="/resources/petsitting-master/js/main.js"></script>

<script>
    $(document).ready(function () {

        let fileDel = $("#fileDel");
        let file = $("#file");

        fileDel.hide();

        file.change(function () {
            let selectedFile = $(this).val();

            if (selectedFile !== null) {
                fileDel.show();
            }
        });

        fileDel.on("click", function () {
            const isConfirmed = confirm("정말로 삭제하시겠습니까?");

            if (isConfirmed) {
                file.val(null);
                fileDel.hide();
            }
        });

    });
</script>

<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>
</body>
</html>