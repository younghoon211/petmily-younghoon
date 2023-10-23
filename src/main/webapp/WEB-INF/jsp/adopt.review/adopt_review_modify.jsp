<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/css/freeBoard.css">
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
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"/>
    </svg>
</div>

<!-- header -->
<%@ include file="../include/header.jspf" %>

<!-- 현재 페이지 -->
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <c:if test="${param.kindOfBoard eq 'adoptReview'}">
                    <p class="breadcrumbs mb-2">
                        <span class="mr-2"><span>Adopt Review Board - Modify<i class="ion-ios-arrow-forward"></i></span></span>
                    </p>
                    <h1 class="mb-0 bread">입양후기 게시판 - 글 수정</h1>
                </c:if>
            </div>
        </div>
    </div>
</section>

<!-- modifyForm 시작 -->
<section class="ftco-section bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-lg-12">
                <form class="form" method="post" action="/adoptReview/auth/modify" enctype='multipart/form-data'
                      id="form">

                    <!-- 글 bNumber, title, content -->
                    <div class="modal-body">

                        <c:if test="${authUser.grade eq '관리자'}">
                            <div class="form-group">
                                <label for="mNumber">
                                    회원번호 / 아이디 / 닉네임
                                </label>
                                <select name="mNumber" id="mNumber" class="form-control">
                                    <c:forEach var="m" items="${memberList}">
                                        <option value="${m.getMNumber()}">${m.getMNumber()} / ${m.id}
                                            / ${m.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="wrTime">작성일시 <b>[이곳을 클릭 후 스페이스바를 누르세요]</b></label>
                                <input type="datetime-local" name="wrTime" class="form-control" id="wrTime"
                                       value="${modifyForm.wrTime}" max="2099-12-30 00:00"
                                       required>
                            </div>
                        </c:if>
                        <c:if test="${authUser.grade eq '일반'}">
                            <input name="mNumber" value="${modifyForm.getMNumber()}" hidden>
                            <input name="wrTime" value="${modifyForm.wrTime}" hidden>
                        </c:if>

                        <div class="form-group">
                            <div>
                                <label for="title">제목</label>
                                <input id="title" type="text" class="form-control" name="title"
                                       value="${modifyForm.title}" maxlength="18" placeholder="제목을 입력해주세요."
                                       required>
                            </div>
                        </div>
                        <div>
                            <label for="content">내용</label>
                            <textarea id="content" rows="20" name="content" class="form-control"
                                      placeholder="내용을 입력해주세요"
                                      maxlength="1300" required><c:out value="${modifyForm.content}"/></textarea>
                        </div>

                        <!-- 첨부파일 -->
                        <div class="custom-file form-control-sm mt-3">
                            <c:if test="${not empty modifyForm.imgPath && modifyForm.imgPath ne 'no_image.png'}">
                                <input type="file" name="file" id="file1" accept="image/*">

                                <div id="fileDel1">
                                    <br>
                                    <button type="button" class="btn-danger">파일 삭제</button>
                                    <br><br>
                                </div>

                                <div id="fileName">
                                    <small>업로드 된 이미지 파일명: <b><c:out value="${modifyForm.imgPath}"/></b></small>
                                </div>

                                <div id="initFileDel">
                                    <button type="button" class="btn-danger">기존파일 삭제</button>
                                    <br><br>
                                </div>

                                <div id="notUpload1">
                                    <small style="color: red">이미지 파일이 업로드 되지 않은 글입니다.</small>
                                </div>
                            </c:if>

                            <c:if test="${modifyForm.imgPath eq 'no_image.png'}">
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

                        <!-- 글 수정 등록, 취소  -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
                            <button type="submit" class="btn btn-primary">글 수정 등록</button>
                        </div>
                    </div>

                    <input name="bNumber" value="${modifyForm.getBNumber()}" hidden>
                    <input name="imgPath" id="imgPath" hidden>

                </form>
            </div>
        </div>
    </div>
</section>

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
        $("#mNumber").val(${modifyForm.getMNumber()});

        <c:if test="${not empty modifyForm.imgPath && modifyForm.imgPath ne 'no_image.png'}">
        $("#notUpload1").hide();
        $("#fileDel1").hide();
        hasImage();
        </c:if>

        <c:if test="${modifyForm.imgPath eq 'no_image.png'}">
        $("#fileDel2").hide();
        noImage();
        </c:if>
    });

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

                $("#form").off().on("submit", function () {
                    deleteImage('${modifyForm.imgPath}');
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
            url: '/adoptReview/' + imgPath,
            data: {imgPath: imgPath},
            dataType: 'text',
            success: function (result) {
                if (result === "SUCCESS") {
                    console.log("이미지 삭제 요청 성공");
                } else {
                    console.log("이미지 삭제 요청 실패 (이미지파일 존재x)")
                }
            }
        });

        $("#imgPath").val("no_image.png");
    }
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>