<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>PETMILY</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap"
          rel="stylesheet">
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
<div class="hero-wrap js-fullheight" style="background-image: url('../../../resources/petsitting-master/images/bg_1.jpg');"
     data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center"
             data-scrollax-parent="true">
            <div class="col-md-11 ftco-animate text-center">
                <h1 class="mb-4">사지 말고, 입양하세요.<br>Don't buy, Do adopt.</h1>
            </div>
        </div>
    </div>
</div>

<section class="ftco-section bg-light ftco-no-pt ftco-intro">
    <div class="container">
        <div class="row">
            <div class="col-md-4 d-flex align-self-stretch px-4 ftco-animate">
                <div class="d-block services text-center">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="flaticon-blind"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">유기동물 조회</h3>
                        <p>가족을 찾고 계신가요? 수많은 아이들이 당신의 가족이 되길 기다리고 있습니다. 아이들에게 희망의 손길을 내밀어 주세요.</p>
                        <a href="/abandonedAnimal/list?sort=abNo"
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
                    <div class="media-body">
                        <h3 class="heading">반려동물 찾아요</h3>
                        <p>소중한 가족을 잃어버리셨나요? 반려동물 찾아요 게시판에 정보를 등록해주세요. 당신의 가족을 찾기 위한 도움을 제공합니다.</p>
                        <a href="/findBoard/list?sort=fno"
                           class="btn-custom d-flex align-items-center justify-content-center"><span
                                class="fa fa-chevron-right"></span><i class="sr-only">Read more</i></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 d-flex align-self-stretch px-4 ftco-animate">
                <div class="d-block services text-center">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="flaticon-grooming"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">유기동물 봤어요</h3>
                        <p>유기동물을 발견하셨나요? 유기동물 봤어요 게시판에 정보를 등록해주세요. 소중한 가족을 애타게 찾고 있습니다.</p>
                        <a href="/lookBoard/list?sort=lno"
                           class="btn-custom d-flex align-items-center justify-content-center"><span
                                class="fa fa-chevron-right"></span><i class="sr-only">Read more</i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section ftco-no-pt ftco-no-pb">
    <div class="container">
        <div class="row d-flex no-gutters">
            <div class="col-md-5 d-flex">
                <div class="img img-video d-flex align-self-stretch align-items-center justify-content-center justify-content-md-center mb-4 mb-sm-0"
                     style="background-image:url('../../../resources/petsitting-master/images/about-1.jpg');">
                </div>
            </div>
            <div class="col-md-7 pl-md-5 py-md-5">
                <div class="heading-section pt-md-5">
                    <h2 class="mb-4">반려동물, 이제는 누군가의 가족입니다.</h2>
                </div>
                <div class="row">
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-stethoscope"></span></div>
                        <div class="text pl-3">
                            <h4>책임감을 가져주세요</h4>
                            <p>한 해에만 수천마리의 동물들이 유기되고 있습니다.</p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-customer-service"></span></div>
                        <div class="text pl-3">
                            <h4>입양 절차가 궁금해요!</h4>
                            <p>유기동물 입양 장려 기관인 저희 팻밀리가 도와드립니다.</p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-emergency-call"></span></div>
                        <div class="text pl-3">
                            <h4>문의해주세요</h4>
                            <p>임시보호, 후원 등 궁금한 점을 물어보세요.</p>
                        </div>
                    </div>
                    <div class="col-md-6 services-2 w-100 d-flex">
                        <div class="icon d-flex align-items-center justify-content-center"><span
                                class="flaticon-veterinarian"></span></div>
                        <div class="text pl-3">
                            <h4>여러분을 기다립니다</h4>
                            <p>아이들을 사랑으로 보듬어주세요.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-counter" id="section-counter">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-lg-3 d-flex justify-content-center counter-wrap ftco-animate">
                <div class="block-18 text-center">
                    <div class="text">
                        <strong class="number" data-number="214">0</strong>
                    </div>
                    <div class="text">
                        <span>오늘 구조된 동물</span>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3 d-flex justify-content-center counter-wrap ftco-animate">
                <div class="block-18 text-center">
                    <div class="text">
                        <strong class="number" data-number="16721">0</strong>
                    </div>
                    <div class="text">
                        <span>가족을 기다려요</span>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3 d-flex justify-content-center counter-wrap ftco-animate">
                <div class="block-18 text-center">
                    <div class="text">
                        <strong class="number" data-number="158">0</strong>
                    </div>
                    <div class="text">
                        <span>입양</span>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3 d-flex justify-content-center counter-wrap ftco-animate">
                <div class="block-18 text-center">
                    <div class="text">
                        <strong class="number" data-number="14">0</strong>
                    </div>
                    <div class="text">
                        <span>안락사</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light ftco-faqs">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 order-md-last">
                <div class="img img-video d-flex align-self-stretch align-items-center justify-content-center justify-content-md-center mb-4 mb-sm-0"
                     style="background-image:url('../../../resources/petsitting-master/images/about-4.jpg');">
                </div>
                <div class="d-flex mt-3">
                    <div class="img img-2 mr-md-2"
                         style="background-image:url('../../../resources/petsitting-master/images/about-5.jpg');"></div>
                    <div class="img img-2 ml-md-2"
                         style="background-image:url('../../../resources/petsitting-master/images/about-6.jpg');"></div>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="heading-section mb-5 mt-5 mt-lg-0">
                    <h2 class="mb-3">자주 하는 질문 모음</h2>
                </div>
                <div id="accordion" class="myaccordion w-100" aria-multiselectable="true">
                    <div class="card">
                        <div class="card-header p-0" id="headingOne">
                            <h2 class="mb-0">
                                <button type="button" href="#collapseOne"
                                        class="d-flex py-3 px-4 align-items-center justify-content-between btn btn-link"
                                        data-parent="#accordion" data-toggle="collapse" aria-expanded="true"
                                        aria-controls="collapseOne">
                                    <p class="mb-0">입양 절차는 어떻게 진행되나요?</p>
                                    <i class="fa" aria-hidden="true"></i>
                                </button>
                            </h2>
                        </div>
                        <div class="collapse show" id="collapseOne" role="tabpanel" aria-labelledby="headingOne">
                            <div class="card-body py-3 px-0">
                                <ol>
                                    <li>입양신청서 작성 > 마이페이지 결과 확인 > 방문면담 > 입양동의서 작성 후 입양 순으로 진행됩니다.
                                    </li>
                                    <li>입양 자격 심사 등 추가적인 조건이 생길 수 있습니다.</li>
                                    <li>입양 이후 지속해서 기관의 연락이 갈 수 있습니다.</li>

                                </ol>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-header p-0" id="headingTwo" role="tab">
                            <h2 class="mb-0">
                                <button type="button" href="#collapseTwo"
                                        class="d-flex py-3 px-4 align-items-center justify-content-between btn btn-link"
                                        data-parent="#accordion" data-toggle="collapse" aria-expanded="false"
                                        aria-controls="collapseTwo">
                                    <p class="mb-0">후원하던 동물의 보호소가 사라졌어요! 제가 후원하던 동물은 어떻게 되나요?</p>
                                    <i class="fa" aria-hidden="true"></i>
                                </button>
                            </h2>
                        </div>
                        <div class="collapse" id="collapseTwo" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="card-body py-3 px-0">
                                <ol>
                                    <li>해당 기관의 관리에 따라 다른 보호소로 이동된 뒤 다시 지원하게 됩니다.</li>
                                    <li>보호소가 이동되면 후원자님께 이전된 보호소 정보를 다시 전달드립니다.</li>
                                </ol>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-header p-0" id="headingThree" role="tab">
                            <h2 class="mb-0">
                                <button type="button" href="#collapseThree"
                                        class="d-flex py-3 px-4 align-items-center justify-content-between btn btn-link"
                                        data-parent="#accordion" data-toggle="collapse" aria-expanded="false"
                                        aria-controls="collapseThree">
                                    <p class="mb-0">반려동물 찾아요 게시판에 쓴 글은 언제 결과를 받아볼 수 있나요?</p>
                                    <i class="fa" aria-hidden="true"></i>
                                </button>
                            </h2>
                        </div>
                        <div class="collapse" id="collapseThree" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="card-body py-3 px-0">
                                <ol>
                                    <li>반려동물 찾아요 게시판에 작성해 주신 내용을 바탕으로
                                        유기동물 봤어요 게시판과 실시간으로 매칭하여 그 결과를 마이페이지에 보여드리고 있습니다.</li>
                                    <li>마이페이지 > 찾아요/봤어요 매칭 결과 > 매칭된 찾아요 게시글에서 확인 가능합니다.</li>
                                    <li>본인의 반려동물이 맞다면 저희 기관에 연락주세요. 확인 후, 보호중인 분과의 만남을 주선해 드립니다.</li>
                                </ol>
                            </div>
                        </div>
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

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>

</body>
</html>