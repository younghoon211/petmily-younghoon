<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>PETMILY</title>

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

    <style>
        textarea {
            width: 82%;
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
                <p class="breadcrumbs mb-2"><span>Abandoned Animal - Update<i class="ion-ios-arrow-forward"></i></span>
                </p>
                <h1 class="mb-0 bread">유기동물 관리 - 글 수정하기</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-lg-12">
                <h1 id="title" class="text-center">유기동물 수정</h1><br><br>

                <!-- 폼 시작 -->

                <form class="form" method="post" action="/admin/abandonedAnimal/update?abNumber=${updateForm.abNumber}"
                      enctype='multipart/form-data'>
                    <div class="modal-body">

                        <div class="row justify-content-start">
                            <div class="form-group col-md-6 col-lg-6">
                                <label>이름</label>
                                <input type="text" class="form-control" name="name" placeholder="이름을 입력해주세요"
                                       value="${updateForm.name}" maxlength="10" required>
                            </div>
                            <div class="form-group col-md-6 col-lg-6">
                                <label>입소날짜</label>
                                <input type="date" class="form-control" value="${updateForm.admissionDate}"
                                       name="admissionDate" min="1900-01-01" max="2099-12-31" required>
                            </div>
                        </div>

                        <hr color="#eee" width="100%">

                        <div class="form-group col-md-6 col-lg-6">
                            <label>종</label><br>
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${updateForm.species eq '개'}">
                                        <span><input type="radio" class="form-check-input" name="species" value="개"
                                                     id="speciesInput1" checked><label
                                                for="speciesInput1">개</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="species" value="고양이"
                                                     id="speciesInput2"><label for="speciesInput2">고양이</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="species" value="기타"
                                                     id="speciesInput3"><label for="speciesInput3">기타</label></span>
                                    </c:when>
                                    <c:when test="${updateForm.species eq '고양이'}">
                                        <span><input type="radio" class="form-check-input" name="species" value="개"
                                                     id="speciesInput4"><label for="speciesInput4">개</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="species" value="고양이"
                                                     id="speciesInput5" checked><label
                                                for="speciesInput5">고양이</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="species" value="기타"
                                                     id="speciesInput6"><label for="speciesInput6">기타</label></span>
                                    </c:when>
                                    <c:when test="${updateForm.species eq '기타'}">
                                        <span><input type="radio" class="form-check-input" name="species" value="개"
                                                     id="speciesInput7"><label for="speciesInput7">개</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="species" value="고양이"
                                                     id="speciesInput8"><label for="speciesInput8">고양이</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="species" value="기타"
                                                     id="speciesInput9" checked><label
                                                for="speciesInput9">기타</label></span>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="form-group col-md-6 col-lg-6">
                            <label>성별</label><br>
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${fn:contains(updateForm.gender, 'M')}">
                                        <span><input type="radio" class="form-check-input" name="gender" value="M"
                                                     id="genderInput1" checked><label
                                                for="genderInput1">수컷</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="gender" value="F"
                                                     id="genderInput2"><label for="genderInput2">암컷</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="gender" value="-"
                                                     id="genderInput3"><label for="genderInput3">모름</label></span>
                                    </c:when>
                                    <c:when test="${fn:contains(updateForm.gender, 'F')}">
                                        <span><input type="radio" class="form-check-input" name="gender" value="M"
                                                     id="genderInput4"><label for="genderInput4">수컷</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="gender" value="F"
                                                     id="genderInput5" checked><label
                                                for="genderInput5">암컷</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="gender" value="-"
                                                     id="genderInput6"><label for="genderInput6">모름</label></span>
                                    </c:when>
                                    <c:when test="${fn:contains(updateForm.gender, '-')}">
                                        <span><input type="radio" class="form-check-input" name="gender" value="M"
                                                     id="genderInput7"><label for="genderInput7">수컷</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="gender" value="F"
                                                     id="genderInput8"><label for="genderInput8">암컷</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="gender" value="-"
                                                     id="genderInput9" checked><label
                                                for="genderInput9">모름</label></span>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="form-group col-md-6 col-lg-6">
                            <label>상태</label><br>
                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${updateForm.animalState eq '입양'}">
                                        <span><input type="radio" class="form-check-input" name="animalState" value="입양"
                                                     id="animalState1" checked><label for="animalState1">입양</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="animalState" value="임보"
                                                     id="animalState2"><label for="animalState2">임시보호</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="animalState" value="보호"
                                                     id="animalState3"><label for="animalState3">보호</label></span>
                                    </c:when>
                                    <c:when test="${updateForm.animalState eq '임보'}">
                                        <span><input type="radio" class="form-check-input" name="animalState" value="입양"
                                                     id="animalState4"><label for="animalState4">입양</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="animalState" value="임보"
                                                     id="animalState5" checked><label
                                                for="animalState5">임시보호</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="animalState" value="보호"
                                                     id="animalState6"><label for="animalState6">보호</label></span>
                                    </c:when>
                                    <c:when test="${updateForm.animalState eq '보호'}">
                                        <span><input type="radio" class="form-check-input" name="animalState" value="입양"
                                                     id="animalState7"><label for="animalState7">입양</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="animalState" value="임보"
                                                     id="animalState8"><label for="animalState8">임시보호</label></span>&emsp;
                                        <span><input type="radio" class="form-check-input" name="animalState" value="보호"
                                                     id="animalState9" checked><label for="animalState9">보호</label></span>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>

                        <div id="adoptInfo">
                            <!-- '입양' 선택한 경우 여기에 입양 관련 정보 선택 폼 -->
                        </div>

                        <div id="tempInfo">
                            <!-- '임보' 선택한 경우 여기에 임보 관련 정보 선택 폼 -->
                        </div>

                        <hr color="#eee" width="100%">

                        <div class="row justify-content-start">
                            <div class="form-group col-md-6 col-lg-4">
                                <label>품종</label>
                                <input type="text" class="form-control" name="kind" placeholder="모르는 경우 '모름' 작성"
                                       value="${updateForm.kind}" maxlength="14" required>
                            </div>
                            <div class="form-group col-md-6 col-lg-4">
                                <label>나이</label>
                                <input type="number" class="form-control" name="age" placeholder="숫자만 입력해주세요"
                                       value="${updateForm.age}" min="1" max="100" required>
                            </div>
                            <div class="form-group col-md-6 col-lg-4">
                                <label>몸무게</label>
                                <input type="number" class="form-control" name="weight" placeholder="숫자만 입력해주세요"
                                       value="${updateForm.weight}" min="0" max="1000" step="any" required>
                            </div>
                        </div>

                        <hr color="#eee" width="100%">

                        <div class="row justify-content-between">
                            <div class="form-group col-md-6 col-lg-4">
                                <label>발견 장소</label>
                                <input type="text" class="form-control" name="location" placeholder="모르는 경우 '모름' 작성"
                                       value="${updateForm.location}" maxlength="14" required>
                            </div>

                            <div class="form-group col-md-6 col-lg-4">
                                <div id="shelter">
                                    <!-- '보호' 선택한 경우 여기에 보호소 리스트 -->
                                </div>
                            </div>

                            <div class="form-group col-md-6 col-lg-4">
                                <label>특이사항</label>
                                <input type="text" class="form-control" name="uniqueness" placeholder="특이사항"
                                       value="${updateForm.uniqueness}" maxlength="30" required>
                            </div>
                        </div>

                        <hr color="#eee" width="100%">

                        <textarea rows="5" class="form-control" name="description" id="description" placeholder=" 소개글"
                                  maxlength="30" required>${updateForm.description}</textarea>

                        <!-- 첨부파일 -->
                        <div class="custom-file form-control-sm mt-3">
                            <c:if test="${not empty updateForm.imgPath && updateForm.imgPath ne 'no_image.png'}">
                                <input type="file" name="file" id="file1" accept="image/*">

                                <div id="fileDel1">
                                    <br>
                                    <button type="button" class="btn-danger">파일 삭제</button>
                                    <br><br>
                                </div>

                                <div id="fileName">
                                    <small>업로드 된 이미지 파일명: <b>${updateForm.imgPath}</b></small>
                                </div>

                                <div id="initFileDel">
                                    <button type="button" class="btn-danger">기존파일 삭제</button>
                                    <br><br>
                                </div>

                                <div id="notUpload1">
                                    <small style="color: red">이미지 파일이 업로드 되지 않은 글입니다.</small>
                                </div>
                            </c:if>

                            <c:if test="${updateForm.imgPath eq 'no_image.png'}">
                                <input type="file" name="file" id="file2" accept="image/*">
                                <br>

                                <div id="fileDel2">
                                    <br>
                                    <button type="button" class="btn-danger">파일 삭제</button>
                                    <br><br>
                                </div>

                                <div id="notUpload2">
                                    <small><span style="color: red">이미지 파일이 업로드 되지 않은 글입니다.
                                        </span></small>
                                </div>
                            </c:if>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
                        <button type="submit" class="btn btn-primary">수정 등록</button>
                    </div>

                    <input name="abNumber" value="${updateForm.abNumber}" hidden>
                    <input name="imgPath" id="imgPath" hidden>

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

        // 입양, 임보 작성 폼 및 보호소 선택
        const adoptInfo = document.getElementById('adoptInfo');
        const tempInfo = document.getElementById('tempInfo');
        const shelter = document.getElementById('shelter');
        const animalStateInputs = $("input[name='animalState']");

        showOrHideForm(animalStateInputs.filter(':checked').val());

        animalStateInputs.change(function () {
            const selectedState = $(this).val();
            showOrHideForm(selectedState);
        });

        function showOrHideForm(animalState) {
            adoptInfo.innerHTML = "";
            tempInfo.innerHTML = "";
            shelter.innerHTML = "";

            if (animalState === "보호") {
                shelter.innerHTML = selectShelterHtml;
            } else if (animalState === "입양") {
                adoptInfo.innerHTML = adoptInfoHtml;
                shelter.innerHTML = notShelterHtml;
            } else if (animalState === "임보") {
                tempInfo.innerHTML = tempInfoHtml;
                shelter.innerHTML = notShelterHtml;
            }
        }

        // 파일 첨부 기능
        <c:if test="${not empty updateForm.imgPath && updateForm.imgPath ne 'no_image.png'}">
            $("#notUpload1").hide();
            $("#fileDel1").hide();
            hasImage();
        </c:if>

        <c:if test="${updateForm.imgPath eq 'no_image.png'}">
            $("#fileDel2").hide();
            noImage();
        </c:if>
    });

    // 입양, 임보 작성 폼 및 보호소 선택 폼
    const adoptInfoHtml = `
                        <span style="color: red; font-size: larger">&nbsp;※ 입양할 회원 정보 추가</span>
                        <br>
                        <div class="row justify-content-start">
                            <div class="form-group col-md-6 col-lg-4">
                                <label>입양할 회원ID / 닉네임 (회원번호)</label>
                                <select name="mNumber" class="form-control" required>
                                    <c:forEach var="m" items="${members}">
                                        <option value="${m.getMNumber()}" ${selectedAdopt.getMNumber() eq m.getMNumber() ? 'selected' : ''}>${m.id} / ${m.name} (${m.getMNumber()})
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="row justify-content-start">
                        <div class="col-md-6 col-lg-4">
                            <label for="maritalStatus">결혼여부</label><br>
                            <div id="maritalStatus" class="form-check form-check-inline">
                                <c:if test="${selectedAdopt.maritalStatus eq 'married' || (selectedAdopt.maritalStatus ne 'married' && selectedAdopt.maritalStatus ne 'single')}">
                                <span><input type="radio" class="form-check-input" name="maritalStatus" id="married"
                                             value="married" checked><label for="married">기혼</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="maritalStatus" id="single"
                                             value="single"><label for="single">미혼</label></span>&emsp;
                                </c:if>
                                <c:if test="${selectedAdopt.maritalStatus eq 'single'}">
                                <span><input type="radio" class="form-check-input" name="maritalStatus" id="married"
                                             value="married"><label for="married">기혼</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="maritalStatus" id="single"
                                             value="single" checked><label for="single">미혼</label></span>&emsp;
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group col-md-6 col-lg-4">
                            <label>거주지</label>
                            <select name="residence" class="form-control" required>
                                <c:forEach var="residence" items="${residences}">
                              <option value="${residence}" ${selectedAdopt.residence eq residence ? 'selected' : ''}>${residence}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-6 col-lg-4">
                            <label for="job">직업</label>
                            <input id="job" type="text" name="job" class="form-control" placeholder="직업을 입력해주세요."
                                   value="${selectedAdopt.job}" maxlength="14" required>
                        </div>
                    </div>
    `;

    const tempInfoHtml = `
                        <span style="color: red; font-size: larger">&nbsp;※ 임시보호 정보 및 회원 정보 추가</span>
                        <br>
                        <div class="row justify-content-start">
                            <div class="form-group col-md-6 col-lg-4">
                                <label>임시보호할 회원ID / 닉네임 (회원번호)</label>
                                <select name="mNumber" class="form-control" required>
                                    <c:forEach var="m" items="${members}">
                                        <option value="${m.getMNumber()}" ${selectedTemp.getMNumber() eq m.getMNumber() ? 'selected' : ''}>${m.id} / ${m.name} (${m.getMNumber()})
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group col-md-6 col-lg-4">
                                <label>임시보호 시작 날짜</label>
                                <input type="date" name="tempDate" class="form-control" min="1900-01-01"
                                       max="2099-12-31" value="${selectedTemp.tempDate}" required>
                            </div>

                            <div class="form-group col-md-6 col-lg-4">
                                <label>임시보호 기간 <span
                                        style="color: red"><small>&nbsp;※ 회원들에게는 임시보호 최소기간 1개월로 명시</small></span></label>
                                <input type="number" name="tempPeriod" class="form-control" placeholder="개월수를 입력해주세요."
                                       min="1" max="100" value="${selectedTemp.tempPeriod}" required>
                            </div>
                        </div>

                        <div class="row justify-content-start">
                        <div class="col-md-6 col-lg-4">
                            <label for="maritalStatus">결혼여부</label><br>
                            <div id="maritalStatus" class="form-check form-check-inline">
                                <c:if test="${selectedTemp.maritalStatus eq 'married' || (selectedTemp.maritalStatus ne 'married' && selectedTemp.maritalStatus ne 'single')}">
                                <span><input type="radio" class="form-check-input" name="maritalStatus" id="married"
                                             value="married" checked><label for="married">기혼</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="maritalStatus" id="single"
                                             value="single"><label for="single">미혼</label></span>&emsp;
                                </c:if>
                                <c:if test="${selectedTemp.maritalStatus eq 'single'}">
                                <span><input type="radio" class="form-check-input" name="maritalStatus" id="married"
                                             value="married"><label for="married">기혼</label></span>&emsp;
                                <span><input type="radio" class="form-check-input" name="maritalStatus" id="single"
                                             value="single" checked><label for="single">미혼</label></span>&emsp;
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group col-md-6 col-lg-4">
                            <label>거주지</label>
                            <select name="residence" class="form-control" required>
                                <c:forEach var="residence" items="${residences}">
                              <option value="${residence}" ${selectedTemp.residence eq residence ? 'selected' : ''}>${residence}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-6 col-lg-4">
                            <label for="job">직업</label>
                            <input id="job" type="text" name="job" class="form-control" placeholder="직업을 입력해주세요."
                                   value="${selectedTemp.job}" maxlength="14" required>
                        </div>
                    </div>
    `;

    const selectShelterHtml = `
                        <label for="shelter">
                            보호소 이름 (지역) - 번호
                        </label>
                        <select name="sNumber" id="shelter" class="form-control">
                            <c:forEach var="s" items="${shelters}">
                            <option value="${s.getSNumber()}"
                                <c:if test="${updateForm.getSNumber() eq s.getSNumber()}">selected</c:if>
                            >${s.groupName} (${s.location}) - ${s.getSNumber()}</option>
                            </c:forEach>
                        </select>
    `;

    const notShelterHtml = `
                    <label for="notShelter">
                        <span style="color: red"><small>&nbsp;※ 입양 또는 임보중인 동물은 보호소 선택이 불가합니다.</small></span>
                    </label>
                    <select id="notShelter" class="form-control" disabled>
                        <option>입양/임보중</option>
                    </select>
                    <input name="sNumber" value="0" hidden>
    `;

    // 파일 첨부 기능
    function hasImage() {
        const fileName = $("#fileName");
        const initFileDel = $("#initFileDel");
        const notUpload1 = $("#notUpload1");
        const fileDel1 = $("#fileDel1");
        const file1 = $("#file1");

        file1.change(function () {
            const selectedFile = $(this).val();

            if (selectedFile !== null) { // 파일 첨부한 경우
                fileName.hide();
                initFileDel.hide();
                notUpload1.hide();
                fileDel1.show();
            }
        });

        fileDel1.off().on("click", function () {
            const isConfirmed = confirm("정말로 삭제하시겠습니까?");

            if (isConfirmed) {
                file1.val(null);
                notUpload1.show();
                fileDel1.hide();
            }
        });

        initFileDel.off().on("click", function () {
            const isConfirmed = confirm("기존 파일을 정말로 삭제하시겠습니까?");

            if (isConfirmed) {
                file1.val(null);
                fileName.hide();
                initFileDel.hide();
                notUpload1.show();

                $(document).on('submit', 'form', function () {
                    deleteImage('${updateForm.imgPath}');
                });
            }
        });
    }

    function noImage() {
        const fileDel2 = $("#fileDel2");
        const notUpload2 = $("#notUpload2");
        const file2 = $("#file2");

        file2.change(function () {
            const selectedFile = $(this).val();

            if (selectedFile !== null) { // 파일 첨부한 경우
                fileDel2.show();
                notUpload2.hide();
            }
        });

        fileDel2.off().on("click", function () {
            const isConfirmed = confirm("정말로 삭제하시겠습니까?");
            if (isConfirmed) {
                file2.val(null);
                fileDel2.hide();
                notUpload2.show();
            }
        });
    }

    function deleteImage(imgPath) {
        console.log("imgPath=" + imgPath);

        $.ajax({
            type: 'DELETE',
            url: '/admin/' + imgPath,
            data: {imgPath: imgPath},
            dataType: 'text',
            success: function (result) {
                if (result === "SUCCESS") {
                    console.log("이미지 삭제 요청 성공");
                }
            }
        });

        $("#imgPath").val("no_image.png");
    }
</script>

<%-- footer --%>
<%@ include file="../../include/footer.jspf" %>
</body>
</html>