<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<%@ include file="../include/header.jspf" %>

<%-- 현재 페이지 --%>
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <p class="breadcrumbs mb-2"><span>Look Animal Board - Modify<i class="ion-ios-arrow-forward"></i></span>
                </p>
                <h1 class="mb-0 bread">유기동물 봤어요 - 글 수정하기</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <form class="form" method="post" action="/lookBoard/auth/modify"
              enctype="multipart/form-data">
            <div class="modal-body">

                <c:if test="${authUser.grade eq '관리자'}">
                    <div class="form-group">
                        <label for="mNumber">
                            회원번호 / 아이디 / 닉네임
                        </label>
                        <select name="mNumber" id="mNumber" class="form-control">
                            <c:forEach var="m" items="${memberList}">
                                <option value="${m.getMNumber()}">${m.getMNumber()} / ${m.id} / ${m.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="wrTime">작성일시 (여기를 클릭 후 스페이스바를 누르세요)</label>
                        <input type="datetime-local" name="wrTime" class="form-control" id="wrTime"
                               value="${modifyForm.wrTime}" max="2099-12-30 00:00"
                               required>
                    </div>
                </c:if>
                <c:if test="${authUser.grade eq '일반'}">
                    <input type="hidden" name="mNumber" value="${modifyForm.getMNumber()}">
                    <input type="hidden" name="wrTime" value="${modifyForm.wrTime}">
                </c:if>

                <hr width="100%" style="color: #9e9e9e">
                <div class="row justify-content-start">
                    <div class="col-md-6 col-lg-4 form-check">
                        <c:choose>
                            <c:when test="${modifyForm.species eq '개'}">
                                <div>종<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput" value="개" checked><label
                                            for="speciesInput">개</label></span>&emsp;&emsp;&emsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput2" value="고양이"><label
                                            for="speciesInput2">고양이</label></span>&emsp;&emsp;&emsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput3" value="기타"><label
                                            for="speciesInput3">기타</label></span>
                                </div>
                            </c:when>
                            <c:when test="${modifyForm.species eq '고양이'}">
                                <div>종<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput4" value="개"><label
                                            for="speciesInput4">개</label></span>&emsp;&emsp;&emsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput5" value="고양이" checked><label
                                            for="speciesInput5">고양이</label></span>&emsp;&emsp;&emsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput6" value="기타"><label
                                            for="speciesInput6">기타</label></span>
                                </div>
                            </c:when>
                            <c:when test="${modifyForm.species eq '기타'}">
                                <div>종<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput7" value="개">
                                                    <label for="speciesInput7">개</label></span>&emsp;&emsp;&emsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput8" value="고양이"><label
                                            for="speciesInput8">고양이</label></span>&emsp;&emsp;&emsp;
                                    <span><input type="radio" class="form-check-input" name="species"
                                                 id="speciesInput9" value="기타" checked><label
                                            for="speciesInput9">기타</label></span>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>

                    <div class="form-group col-md-6 col-lg-4">
                        <label for="kindInput">품종</label>
                        <input type="text"
                               class="form-control"
                               name="kind" id="kindInput"
                               value="${modifyForm.kind}"
                               placeholder="모르는 경우 '모름' 작성"
                               maxlength="14"
                               required>
                    </div>

                    <div class="form-group col-md-6 col-lg-4">
                        <label for="locationInput">발견 장소</label>
                        <input type="text"
                               class="form-control"
                               name="location"
                               id="locationInput"
                               value="${modifyForm.location}"
                               placeholder="모르는 경우 '모름' 작성"
                               maxlength="14"
                               required>
                    </div>
                </div>

                <hr width="100%" style="color: #9e9e9e">

                <div class="form-group">
                    <label for="title">제목</label>
                    <input id="title" type="text" class="form-control" name="title"
                           value="${modifyForm.title}" maxlength="18" placeholder="제목을 입력해주세요 (18자 이내)" required>
                </div>

                <div>
                    <label for="content">내용</label>
                    <textarea id="content" rows="20" name="content" class="form-control"
                              placeholder="내용을 입력해주세요 (360자 이내)"
                              maxlength="360" required>${modifyForm.content}</textarea>
                </div>

                <div class="custom-file form-control-sm mt-3" >
                    <input type="file" name="file" id="file" accept="image/*"><br>
                    <c:if test="${modifyForm.imgPath ne 'no_image.png'}">
                        <small><span>업로드 된 이미지 파일명: <b>${modifyForm.imgPath}</b></span></small>
                    </c:if>
                    <c:if test="${modifyForm.imgPath eq 'no_image.png'}">
                        <small><span style="color: red">이미지 파일이 업로드 되지 않은 글입니다.</span></small>
                    </c:if>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
                <button type="submit" class="btn btn-primary">수정</button>
            </div>
            <input type="hidden" name="laNumber" value="${modifyForm.laNumber}">
        </form>
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
    document.getElementById("mNumber").value = "${modifyForm.getMNumber()}";
</script>
<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>