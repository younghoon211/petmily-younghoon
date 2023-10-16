<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<%-- 현재 페이지 --%>
<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../resources/petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <h1 class="mb-0 bread">내가 쓴 게시글</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">

        <!-- 라디오체크 게시판 선택 -->
        <div style="text-align: center">
            <input type="radio" id="find" name="type" onclick="redirectTo('/member/auth/myPost/find')"
            <c:if test="${type eq 'find'}">
                   checked
            </c:if>>
            <label for="find">반려동물 찾아요</label>
            &nbsp;&nbsp;&nbsp;

            <input type="radio" id="look" name="type" onclick="redirectTo('/member/auth/myPost/look')"
            <c:if test="${type eq 'look'}">
                   checked
            </c:if>> <label for="look">유기동물 봤어요</label>
            &nbsp;&nbsp;&nbsp;

            <input type="radio" id="freeBoard" name="type"
                   onclick="redirectTo('/member/auth/myPost/board?kindOfBoard=free')"
            <c:if test="${type eq 'board' && param.kindOfBoard eq 'free'}">
                   checked
            </c:if>>
            <label for="freeBoard">자유게시판</label>
            &nbsp;&nbsp;&nbsp;

            <input type="radio" id="inquiryBoard" name="type"
                   onclick="redirectTo('/member/auth/myPost/board?kindOfBoard=inquiry')"
            <c:if test="${type eq 'board' && param.kindOfBoard eq 'inquiry'}">
                   checked
            </c:if>>
            <label for="inquiryBoard">문의게시판</label>
            &nbsp;&nbsp;&nbsp;

            <input type="radio" id="adoptReview" name="type" onclick="redirectTo('/member/auth/myPost/adoptReview')"
            <c:if test="${type eq 'adoptReview'}">
                   checked
            </c:if>>
            <label for="adoptReview">입양후기 게시판</label>
            &nbsp;&nbsp;&nbsp;
        </div>
        <br>
        <div class="inner-main-body p-2 p-sm-3 collapse forum-content show">
            <div class="container">

                <!-- 목록 출력 -->
                <c:forEach var="board" items="${myPost.content}">
                    <div class="card mb-2">
                        <div class="card-body p-2 p-sm-3">
                            <div class="media forum-item">

                                <!-- 글 번호 -->
                                <div class="media-body">
                                    <small><i class="far fa-eye"></i>글번호 <c:out value="${board.getBNumber()}"/></small>

                                    <!-- 제목 -->
                                    <div class="text-secondary">
                                        <c:if test="${param.kindOfBoard eq 'free'}">
                                            <a href="<c:out value='/board/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}'/>"
                                               class="text-body" style="font-size: 1.3em;"><c:out value="${board.title}"/></a>
                                            <span style="font-size: 0.9em; color: red">[<c:out value="${board.replyCount}"/>]</span>
                                        </c:if>
                                        <c:if test="${param.kindOfBoard eq 'inquiry'}">
                                            <c:choose>
                                                <c:when test="${authUser.grade ne '관리자' and authUser.getMNumber() ne board.getMNumber() and board.checkPublic eq 'N'}">
                                                    <a class="text-body"
                                                       style="font-size: 1.3em;"><c:out value="${board.title}"/></a>
                                                    <span style="font-size: 0.9em; color: red">[<c:out value="${board.replyCount}"/>]</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="<c:out value='/board/detail?kindOfBoard=${param.kindOfBoard}&bNumber=${board.getBNumber()}'/>"
                                                       class="text-body"
                                                       style="font-size: 1.3em;"><c:out value="${board.title}"/></a>
                                                    <span style="font-size: 0.9em; color: red">[<c:out value="${board.replyCount}"/>]</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </div>

                                    <!-- 작성자, 작성 날짜 -->
                                    <div class="text-muted">
                                        <small><a href="javascript:void(0)">by <c:out value="${board.name}"/></a><span>
									<i class="far fa-comment ml-2"></i>
									</span>date <c:out value="${board.wrTime}"/> </small>
                                    </div>

                                </div>

                                <!-- list 공개 / 비공개 -->
                                <div class="text-muted small text-center align-self-center">
                                    <c:if test="${param.kindOfBoard eq 'inquiry'}">
                                        <c:if test="${board.checkPublic eq 'Y'}">
                                            <span><i class="far fa-comment ml-2"></i></span>
                                            <a class="text-body" style="font-size: 1.3em;">공개</a>
                                        </c:if>
                                        <c:if test="${board.checkPublic eq 'N'}">
                                            <span><i class="far fa-comment ml-2"></i></span>
                                            <a class="text-body" style="font-size: 1.3em;">비공개</a>
                                        </c:if>
                                    </c:if>

                                    <div>
                                        조회수 : <c:out value="${board.viewCount}"/>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="window.location.href='/member/auth/mypage'">돌아가기</button>
                </div>

                <!-- 페이징 처리 -->
                <div class="row mt-5">
                    <div class="col text-center">
                        <div class="block-27">
                            <ul>
                                <li>
                                    <c:if test="${myPost.startPage > 5}">
                                        <a href="/member/auth/myPost/board?kindOfBoard=${param.kindOfBoard}&pageNo=${myPost.startPage - 5}">&lt;</a>
                                    </c:if>
                                </li>
                                <c:forEach var="pNo" begin="${myPost.startPage}" end="${myPost.endPage}">
                                    <c:if test="${myPost.currentPage eq pNo}">
                                        <li class="active">
                                            <a href="/member/auth/myPost/board?kindOfBoard=${param.kindOfBoard}&pageNo=${pNo}">${pNo}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${myPost.currentPage ne pNo}">
                                        <li>
                                            <a href="/member/auth/myPost/board?kindOfBoard=${param.kindOfBoard}&pageNo=${pNo}">${pNo}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <li>
                                    <c:if test="${myPost.endPage < myPost.totalPages}">
                                        <a href="/member/auth/myPost/board?kindOfBoard=${param.kindOfBoard}&pageNo=${myPost.startPage + 5}">&gt;</a>
                                    </c:if>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 게시판 List 끝 -->

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
    function redirectTo(url) {
        window.location.href = url;
    }
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>