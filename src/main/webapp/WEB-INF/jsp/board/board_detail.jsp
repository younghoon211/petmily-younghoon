<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <script src="https://twitter.github.io/typeahead.js/js/handlebars.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
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
                <c:if test="${param.kindOfBoard eq 'free'}">
                    <p class="breadcrumbs mb-2">
                        <span class="mr-2"><span>Free Board - Detail<i class="ion-ios-arrow-forward"></i></span></span>
                    </p>
                    <h1 class="mb-0 bread">자유게시판 - 상세보기</h1>
                </c:if>
                <c:if test="${param.kindOfBoard eq 'inquiry'}">
                    <p class="breadcrumbs mb-2">
                        <span class="mr-2"><span>Inquiry Board - Detail<i
                                class="ion-ios-arrow-forward"></i></span></span>
                    </p>
                    <h1 class="mb-0 bread">문의게시판 - 상세보기</h1>
                </c:if>
            </div>
        </div>
    </div>
</section>

<!-- content 상세보기 -->
<section class="ftco-section bg-light">
    <div class="container">

        <!-- content 내용 출력 -->
        <div class="card mb-2">
            <div class="card-body">
                <div class="media forum-item">
                    <div class="media-body ml-3">
                        <b> <span style="font-size: 2em;">${detailForm.title}</span> </b>
                        <h6 class="mt-1"></h6><br>
                        <small><a href="javascript:void(0)">${detailForm.name}</a></small>
                        <small style="float: right">조회수: ${detailForm.viewCount}&nbsp;&nbsp;&nbsp;&nbsp;${detailForm.wrTime} </small>

                        <c:if test="${param.kindOfBoard eq 'inquiry'}">
                            <c:if test="${detailForm.checkPublic eq 'Y'}">
                                <span><small><i class="far fa-comment ml-2"></i> 공개</small></span>
                            </c:if>
                            <c:if test="${detailForm.checkPublic eq 'N'}">
                                <span><small><i class="far fa-comment ml-2"></i> 비공개</small></span>
                            </c:if>
                        </c:if>

                        <div class="modal-footer"></div>

                        <!-- content 내용 -->
                        <div class="mt-3 font-size-lg">${detailForm.content}</div>
                        <h1 class="mt-1"></h1>

                        <div class="modal-header"></div>
                        <div class="modal-footer">

                            <!-- content 수정, 삭제 -->
                            <c:if test="${authUser.getMNumber() eq detailForm.getMNumber() || authUser.grade eq '관리자'}">
                                <button type="button" class="btn btn-primary"
                                        onclick="location.href='/board/auth/modify?kindOfBoard=${param.kindOfBoard}&bNumber=${detailForm.getBNumber()}'">
                                    수정
                                </button>
                                <button type="button" class="btn btn-danger"
                                        onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                {return location.href='/board/auth/delete?kindOfBoard=${param.kindOfBoard}&bNumber=${detailForm.getBNumber()}';}">
                                    삭제
                                </button>
                            </c:if>

                            <!-- 목록으로 버튼 -->
                            <button type="button" class="btn btn-secondary"
                                    onclick="location.href='/board/list?kindOfBoard=${param.kindOfBoard}&sort=bno'">목록으로
                            </button>
                            <c:if test="${authUser.grade eq '관리자'}">
                                <button type="button" class="btn btn-dark"
                                        onclick="location.href='/admin/board?kindOfBoard=${param.kindOfBoard}'">게시판 관리로
                                </button>
                            </c:if>
                        </div>

                        <%-- 댓글 리스트 --%>
                        <div style="list-style: non">
                            <ul class="timeline">
                                <li class="time-label" id="replyLi" style="list-style-type: none"></li>
                            </ul>
                        </div>

                        <!-- 댓글 작성창 -->
                        <c:if test="${authUser ne null}">
                            <div class="card mb-2" id="message">
                                <div class="card-body">
                                    <div class="col-md-8 col-lg-12">
                                        <div class="comment-wrapper">
                                            <div class="panel panel-info">
                                                <div class="panel-body">

                                                    <div class="form-group">
                                                        <label for="message">댓글</label>
                                                        <textarea name="reply" id="message1" cols="30" rows="3"
                                                                  class="form-control" maxlength="300"
                                                                  placeholder="댓글을 작성해주세요."></textarea>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" id="replyAddBtn"
                                                                class="btn btn-outline-success addBtn">
                                                            댓글등록
                                                        </button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>
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

<script id="template" type="text/x-handlebars-template">
    {{#each this.reverse}}
    <li class="replyObj" data-brNumber={{brNumber}} style="list-style-type: none">
        <div class="timeline-item">
            <div class="timeline-header">
                <strong>{{writer}}</strong>&nbsp;
                <span style="color: lightgray">{{wrTime}}</span>
            </div>
            <span id="reply" class="timeline-body">{{reply}}</span>
            <span class="timeline-footer" style="white-space: nowrap">
                {{#if hasPermission}}
                <div style="float: right">
                    <button type="button" class="btn btn-outline-success" id="editBtn">댓글수정</button>
                    <button type="button" class="btn btn-outline-danger" id="deleteBtn">댓글삭제</button>
                </div><br><br>
                {{/if}}
            </span>
        </div>
        <hr/>
    </li>
    {{/each}}
</script>

<script>
    $(document).ready(function () {
        const bNumber = "${detailForm.getBNumber()}";
        getPage("/replies/" + bNumber);

        //작성
        $("#replyAddBtn").on("click", function () {
            let mNumber = "${authUser.getMNumber()}";
            console.log(mNumber);
            let replytextObj = $("#message1");
            let reply = replytextObj.val();

            $.ajax({
                type: 'post',
                url: '/replies/' + bNumber,
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({bNumber: bNumber, mNumber: mNumber, reply: reply}),
                dataType: 'text',
                success: function (result) {
                    console.log("result: " + result);

                    if (result === 'SUCCESS') {
                        getPage("/replies/" + bNumber);
                        replytextObj.val("");
                    }
                }
            });
        });

        // 수정
        $(document).on("click", "#editBtn", function () {
            let replyObj = $(this).closest(".replyObj");
            let replyText = replyObj.find(".timeline-body").text();
            let brNumber = replyObj.attr("data-brNumber");

            console.log("수정 brNumber= " + brNumber);

            let editArea = $("<textarea>", {
                "class": "form-control",
                "style": "height: 100px; resize: none",
                "text": replyText,
                "maxLength": "300",
                "placeholder": "댓글을 작성해주세요.",
                "cols": "30", "rows": "3",
            });

            let saveBtn = $("<button>", {
                "type": "button",
                "class": "btn btn-outline-success saveEditBtn",
                "text": "수정하기",
            });

            let cancelBtn = $("<button>", {
                "type": "button",
                "class": "btn btn-outline-danger cancelEditBtn",
                "text": "취소",
            });

            replyObj.find(".timeline-body").empty().append(editArea).append('<br>');
            replyObj.find(".timeline-footer").empty().append(saveBtn).append('&nbsp;').append(cancelBtn).append('<br>');

            saveBtn.on("click", function () {
                let editedReply = editArea.val();

                $.ajax({
                    type: 'patch',
                    url: '/replies/' + bNumber,
                    headers: {
                        "Content-Type": "application/json"
                    },
                    data: JSON.stringify({brNumber: brNumber, reply: editedReply}),
                    dataType: 'text',
                    success: function (result) {
                        if (result === 'SUCCESS') {
                            console.log("수정 result = " + result);
                            getPage("/replies/" + bNumber);
                        }
                    }
                });
            });

            cancelBtn.on("click", function () {
                replyObj.find(".timeline-body").text(replyText);
                replyObj.find(".timeline-footer").empty()
                    .append('<br><div style="float: right">' +
                        '<button type="button" class="btn btn-outline-success" id="editBtn">댓글수정</button>&nbsp;' +
                        '<button type="button" class="btn btn-outline-danger" id="deleteBtn">댓글삭제</button>' +
                        '</div><br><br>');
            });
        });

        // 삭제
        $(document).on("click", "#deleteBtn", function () {
            let replyObj = $(this).closest(".replyObj");
            let brNumber = replyObj.attr("data-brNumber");

            console.log("삭제 brNumber=" + brNumber);

            let isConfirmed = confirm("정말로 삭제하시겠습니까?");
            if (isConfirmed) {
                $.ajax({
                    type: 'delete',
                    url: '/replies/' + brNumber,
                    headers: {
                        "Content-Type": "application/json"
                    },
                    dataType: 'text',
                    success: function (result) {
                        console.log("삭제 result = " + result);
                        if (result === 'SUCCESS') {
                            getPage("/replies/" + bNumber);
                        }
                    },
                });

                replyObj.remove();
            }
        });
    });

    function getPage(pageInfo) {
        $.getJSON(pageInfo, function (data) {
            printData(data, $("#replyLi"), $('#template'));
        });
    }

    let printData = function (replyData, target, templateObject) {
        let template = Handlebars.compile(templateObject.html());
        let html = template(replyData);

        $(".replyObj").remove();
        target.after(html);
    }
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>