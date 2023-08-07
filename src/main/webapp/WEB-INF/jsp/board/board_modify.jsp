<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <c:if test="${param.kindOfBoard eq '자유'}">
                    <p class="breadcrumbs mb-2"><span>Free Board - Modify<i class="ion-ios-arrow-forward"></i></span>
                    </p>
                    <h1 class="mb-0 bread">자유 게시판 - 글 수정하기</h1>
                </c:if>
                <c:if test="${param.kindOfBoard eq '문의'}">
                    <p class="breadcrumbs mb-2"><span>Inquiry Board - Modify<i class="ion-ios-arrow-forward"></i></span>
                    </p>
                    <h1 class="mb-0 bread">문의 게시판 - 글 수정하기</h1>
                </c:if>
            </div>
        </div>
    </div>
</section>

<!-- modifyForm 시작 -->
<section class="ftco-section bg-light">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="contact">

                    <!-- form 시작 -->
                    <form id="boardModify" class="form" method="post" action="/board/auth/modify">
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


                            <!-- title, content, bNumber, kindOfBoard -->
                            <div class="form-group">
                                <div>
                                    <label for="title">제목</label>
                                    <input id="title" type="text" class="form-control" name="title"
                                           value="${modifyForm.title}"
                                           maxlength="30" placeholder="제목을 입력해주세요" required>
                                </div>
                            </div>
                            <div>
                                <label for="content">내용</label>
                                <textarea id="content" rows="20" name="content" class="form-control"
                                          placeholder="내용을 입력해주세요"
                                          maxlength="1300" required>${modifyForm.content}</textarea>
                            </div>

                        </div>

                        <!-- checkPublic 공개 / 비공개 여부  -->

                        <div class="modal-footer">
                            <c:if test="${param.kindOfBoard eq '자유'}">
                                <input type="hidden" name="checkPublic" value="Y">
                            </c:if>
                            <c:if test="${param.kindOfBoard eq '문의'}">
                                <c:choose>
                                    <c:when test="${modifyForm.checkPublic eq 'Y'}">
                                        <div>
                                            <input type="radio" name="checkPublic" value="Y" id="Y" checked> <label
                                                for="Y">공개</label>&ensp;
                                        </div>
                                        <div>
                                            <input type="radio" name="checkPublic" value="N" id="N"> <label
                                                for="N">비공개</label>&ensp;
                                        </div>
                                    </c:when>
                                    <c:when test="${modifyForm.checkPublic eq 'N'}">
                                        <div>
                                            <input type="radio" name="checkPublic" value="Y" id="Y2"> <label
                                                for="Y2">공개</label>&ensp;
                                        </div>
                                        <div>
                                            <input type="radio" name="checkPublic" value="N" id="N2"
                                                   checked> <label for="N2">비공개</label>&ensp;
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:if>
                            <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
                            <button type="submit" class="btn btn-primary">글 수정 등록</button>
                        </div>
                        <input type="hidden" name="bNumber" value="${modifyForm.getBNumber()}">
                        <input type="hidden" name="kindOfBoard" value="${modifyForm.kindOfBoard}">
                    </form>

                </div>
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
    document.getElementById("mNumber").value = "${modifyForm.getMNumber()}";
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>