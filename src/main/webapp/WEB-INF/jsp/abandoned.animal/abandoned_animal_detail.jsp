<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/animate.css">
    <link rel="stylesheet"
          href="/resources/petsitting-master/css/owl.carousel.min.css">
    <link rel="stylesheet"
          href="/resources/petsitting-master/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/magnific-popup.css">
    <link rel="stylesheet"
          href="/resources/petsitting-master/css/bootstrap-datepicker.css">
    <link rel="stylesheet"
          href="/resources/petsitting-master/css/jquery.timepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/flaticon.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
                <p class="breadcrumbs mb-2">
                    <span class="mr-2"><span>Abandoned Animal - Detail<i
                            class="ion-ios-arrow-forward"></i></span></span>
                </p>
                <h1 class="mb-0 bread">유기동물 - 상세보기</h1>
            </div>
        </div>
    </div>
</section>
<div class="info-wrap w-100 p-5 img"></div>
<section class="ftco-section ftco-no-pt ftco-no-pb">
    <div class="container">
        <div class="row d-flex no-gutters">
            <div class="col-md-5 d-flex">
                <img src="<c:out value='/abandonedAnimal/upload?filename=${detailForm.imgPath}'/>"
                     style='width: 100%; object-fit: contain'/>
            </div>
            <div class="col-md-7 pl-md-5 py-md-5">
                <div class="heading-section pt-md-5">
                    <h2 class="mb-4"><c:out value="${detailForm.name}"/></h2>
                </div>
                <div class="row">
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-stethoscope"></span></div>
                        <div class="text pl-3">
                            <h4>종</h4>
                            <p><c:out value="${detailForm.species}"/> (<c:out value="${detailForm.kind}"/>)</p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-customer-service"></span></div>
                        <div class="text pl-3">
                            <h4>나이</h4>
                            <p><c:out value="${detailForm.age}"/>살</p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-emergency-call"></span></div>
                        <div class="text pl-3">
                            <h4>몸무게 / 성별</h4>
                            <p><c:out value="${detailForm.weight}kg / "/>
                                <c:if test="${detailForm.gender eq 'M'}">수컷</c:if>
                                <c:if test="${detailForm.gender eq 'F'}">암컷</c:if>
                                <c:if test="${detailForm.gender eq '-'}">모름</c:if>
                            </p>

                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-veterinarian"></span></div>
                        <div class="text pl-3">
                            <h4>상태</h4>
                            <p><c:out value="${detailForm.animalState}"/></p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-veterinarian"></span></div>
                        <div class="text pl-3">
                            <h4>발견 장소</h4>
                            <p><c:out value="${detailForm.location}"/></p>
                        </div>
                    </div>

                    <c:if test="${detailForm.animalState eq '보호'}">
                        <div class="col-md-6 services-2 w-100 d-flex">
                            <div class="icon d-flex align-items-center justify-content-center"><span
                                    class="flaticon-veterinarian"></span></div>
                            <div class="text pl-3">
                                <h4>보호중인 보호소</h4>
                                <p><c:out value="${detailForm.groupName} (${detailForm.shelterLocation})"/></p>
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="row mb-5 pb-5">
            <div class="col-md-4 d-flex align-self-stretch px-4 ftco-animate">
                <div class="d-block services text-center">
                    <div class="media-body p-4">
                        <h3 class="heading">특이사항</h3>
                        <p><c:out value="${detailForm.uniqueness}"/></p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 d-flex align-self-stretch px-4 ftco-animate">
                <div class="d-block services text-center">
                    <div class="media-body p-4">
                        <h3 class="heading">소개글</h3>
                        <p><c:out value="${detailForm.description}"/></p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 d-flex align-self-stretch px-4 ftco-animate">
                <div class="d-block services text-center">
                    <div class="media-body p-4">
                        <h3 class="heading">입소 날짜</h3>
                        <p><c:out value="${detailForm.admissionDate}"/></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-5 pt-4">
            <div class="col-md-4 d-flex align-self-stretch px-4 ftco-animate">
                <div class="d-block services text-center">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="flaticon-blind"></span>
                    </div>
                    <div class="media-body p-4">
                        <h3 class="heading">후원하기</h3>
                        <p><small>작은 나눔은 큰 힘이 됩니다.</small></p>
                        <a href="<c:out value='/abandonedAnimal/auth/donate?abNumber=${param.abNumber}'/>"
                           class="btn-custom d-flex align-items-center justify-content-center"><span
                                class="fa fa-chevron-right"></span><i class="sr-only">Read more</i></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 d-flex align-self-stretch px-4 ftco-animate">
                <div class="d-block services text-center">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="flaticon-dog-eating"></span>
                    </div>
                    <div class="media-body p-4">
                        <h3 class="heading">입양/임시보호하기</h3>
                        <p><small>사랑의 손길을 내밀어주세요.</small></p>
                        <a
                                <c:if test="${adopt.status ne '처리중'}">
                                    href="<c:out value='/abandonedAnimal/auth/adoptTemp?abNumber=${param.abNumber}'/>"
                                </c:if>
                                <c:if test="${adopt.status eq '처리중'}">
                                    href="" id="adoptWait"
                                </c:if>
                                class="btn-custom d-flex align-items-center justify-content-center"><span
                                class="fa fa-chevron-right"></span><i class="sr-only">Read
                            more</i></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 d-flex align-self-stretch px-4 ftco-animate">
                <div class="d-block services text-center">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="flaticon-grooming"></span>
                    </div>
                    <div class="media-body p-4">
                        <h3 class="heading">봉사하기</h3>
                        <p><small>여러분을 기다리고 있어요!</small></p>
                        <a
                                <c:if test="${detailForm.animalState eq '보호'}">
                                    href="<c:out value='/abandonedAnimal/auth/volunteer?abNumber=${param.abNumber}'/>"
                                </c:if>
                                <c:if test="${detailForm.animalState ne '보호'}">
                                    href="" id="notProtected"
                                </c:if>
                                class="btn-custom d-flex align-items-center justify-content-center"><span
                                class="fa fa-chevron-right"></span><i class="sr-only">Read
                            more</i></a>
                    </div>
                </div>
            </div>
        </div>
        <br><br><br><br>
        <div class="modal-footer">
            <c:if test="${authUser.grade eq '관리자'}">
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href='/admin/abandonedAnimal/update?abNumber=${param.abNumber}'">
                    수정
                </button>
                <button type="button" class="btn btn-danger"
                        onclick="if(confirm('삭제 시 해당 동물과 관련된 모든 정보(입양/임보/후원)가 삭제됩니다.'))
                                { if(confirm('정말로 삭제하시겠습니까?')) return window.location.href='/abandonedAnimal/list?sort=abNo';}">
                    삭제
                </button>
                <button type="button" class="btn btn-dark"
                        onclick="window.location.href='/admin/abandonedAnimal'">유기동물 관리로
                </button>
            </c:if>
            <button type="button" class="btn btn-secondary"
                    onclick="window.location.href='/abandonedAnimal/list?sort=abNo'">목록으로
            </button>
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
        $('#adoptWait').click(function (event) {
            event.preventDefault();

            $.ajax({
                type: 'GET',
                url: '/abandonedAnimal/auth/adoptTemp?abNumber=${param.abNumber}',
                success: function () {
                    alert('해당 동물은 현재 입양 신청 처리중입니다.');
                },
            });
        });

        $('#notProtected').click(function (event) {
            event.preventDefault();

            $.ajax({
                type: 'GET',
                url: '/abandonedAnimal/auth/volunteer?abNumber=${param.abNumber}',
                success: function () {
                    alert('보호중인 동물에게만 봉사 가능합니다.');
                },
            });
        });
    });
</script>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>

