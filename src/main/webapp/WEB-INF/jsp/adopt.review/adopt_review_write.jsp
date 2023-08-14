<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Petmily - Don't buy, Do Adopt</title>

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
                        <span class="mr-2"><span>Adopt Review Board - Write<i class="ion-ios-arrow-forward"></i></span></span>
                    </p>
                    <h1 class="mb-0 bread">입양후기 게시판 - 글 작성</h1>
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

                <!-- form 시작 -->
                <form class="form" method="post" action="/adoptReview/auth/write"
                      enctype='multipart/form-data'>
                    <div class="modal-body">

                        <c:if test="${authUser.grade eq '관리자'}">
                            <div>
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
                            <hr width="100%" style="color: #9e9e9e">
                        </c:if>
                        <c:if test="${authUser.grade eq '일반'}">
                            <input type="hidden" name="mNumber" value="${mNumber}">
                        </c:if>

                        <!-- 글 title, content, kindOfBoard, wrTime -->
                        <div class="form-group">
                            <div>
                                <label for="title">제목</label>
                                <input id="title" type="text" class="form-control" name="title"
                                       placeholder="제목을 입력해주세요 (18자 이내)" autofocus maxlength="18" required>
                            </div>
                        </div>

                        <div>
                            <label for="content">내용</label>
                            <textarea id="content" rows="20" name="content" class="form-control"
                                      placeholder="내용을 입력해주세요" maxlength="1300" required></textarea>
                        </div>

                        <input type="hidden" name="kindOfBoard" value="${param.kindOfBoard}">

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
                    <!-- 취소, 등록 버튼  -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
                        <button type="submit" class="btn btn-primary">글 등록</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- modifyForm 끝 -->

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
<%@ include file="../include/footer.jspf" %>
</body>
</html>