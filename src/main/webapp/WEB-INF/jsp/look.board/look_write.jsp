<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                <p class="breadcrumbs mb-2"><span>Look Animal Board - Write<i class="ion-ios-arrow-forward"></i></span>
                </p>
                <h1 class="mb-0 bread">유기동물 봤어요 - 글 작성하기</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <form class="form" method="post" action="/lookBoard/auth/write"
              enctype="multipart/form-data">
            <div class="modal-body">

                <c:if test="${authUser.grade eq '관리자'}">
                    <div>
                        <label for="mNumber">
                            회원번호 / 아이디 / 닉네임
                        </label>
                        <select name="mNumber" id="mNumber" class="form-control">
                            <c:forEach var="m" items="${memberList}">
                                <option value="${m.getMNumber()}">${m.getMNumber()} / ${m.id} / ${m.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <c:if test="${authUser.grade eq '일반'}">
                    <input type="hidden" name="mNumber" value="${authUser.getMNumber()}">
                </c:if>

                <div class="form-group col-md-auto col-lg-auto">
                    <small style="color: red">※ 반려동물 찾아요 게시판의 게시글 중 종, 품종, 발견 장소와 일치하는 글이 있을 시 해당 게시글과 자동 매칭됩니다.</small>
                </div>

                <hr width="100%" style="color: #9e9e9e">

                <div class="row justify-content-start">
                    <div class="col-md-6 col-lg-4 form-check">
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
                    </div>
                    <div class="form-group col-md-6 col-lg-4">
                        <label for="kindInput">품종</label>
                        <input type="text"
                               class="form-control"
                               name="kind" id="kindInput"
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
                               placeholder="모르는 경우 '모름' 작성"
                               maxlength="14"
                               required>
                    </div>
                </div>

                <hr width="100%" style="color: #9e9e9e">

                <div class="form-group">
                    <label for="title">제목</label>
                    <input id="title" type="text" class="form-control" name="title"
                           maxlength="18" placeholder="제목을 입력해주세요 (18자 이내)" required>
                </div>

                <div>
                    <label for="content">내용</label>
                    <textarea id="content" rows="20" name="content" class="form-control"
                              placeholder="내용을 입력해주세요 (360자 이내)"
                              maxlength="360" required></textarea>
                </div>

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
            </div>

            <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            <c:if test="${authUser.grade eq '일반'}">
                                onclick="hrefLook()"
                            </c:if>
                            <c:if test="${authUser.grade eq '관리자'}">
                                onclick="history.back()"
                            </c:if>
                    >취소
                    </button>
                <button type="submit" class="btn btn-primary">글 등록</button>
            </div>
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

    function hrefLook() {
        window.location.href = '/lookBoard/list?sort=lno';
    }
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>