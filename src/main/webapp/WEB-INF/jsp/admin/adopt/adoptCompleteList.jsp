<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
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
	<link rel="stylesheet" href="/css/bootstrap.css">
</head>


<!-- 헤더 -->

<%@ include file="/WEB-INF/jsp/include/header.jspf" %>

<!-- 현재 페이지 -->
<body>

<section class="hero-wrap hero-wrap-2" style="background-image: url('/resources/petsitting-master/images/bg_2.jpg');"
		 data-stellar-background-ratio="0.5">
	<div class="overlay"></div>
	<div class="container">
		<div class="row no-gutters slider-text align-items-end">
			<div class="col-md-9 ftco-animate pb-5">
				<h1 class="mb-0 bread">입양 완료 리스트</h1>
			</div>
		</div>
	</div>
</section>

<!-- 게시판 List -->

<section class="ftco-section bg-light">
	<div class="container">

		<div style="text-align: center">
			<c:set var="URI" value="${requestScope['javax.servlet.forward.request_uri']}"/>
			<input type="radio" id="adopt" value="adopt" onclick="selectKind(this.value)"
			<c:if test="${URI == '/admin/adopt'}">
				   checked
			</c:if>>
			<label for="adopt">입양 관리</label>
			&nbsp;&nbsp;&nbsp;
			<input type="radio" id="wait" value="wait" onclick="selectKind(this.value)"
			<c:if test="${URI == '/admin/adopt/wait'}">
				   checked
			</c:if>>
			<label for="wait">입양 승인 관리</label>
			&nbsp;&nbsp;&nbsp;
			<input type="radio" id="complete" value="complete" onclick="selectKind(this.value)"
			<c:if test="${URI == '/admin/adopt/complete'}">
				   checked
			</c:if>>
			<label for="complete">입양 완료된 리스트</label>
			&nbsp;&nbsp;&nbsp;
			<input type="radio" id="refuse" value="refuse" onclick="selectKind(this.value)"
			<c:if test="${URI == '/admin/adopt/refuse'}">
				   checked
			</c:if>>
			<label for="refuse">입양 거절된 리스트</label>
		</div>
		<br/>

		<!-- 목록 출력 -->
		<div class="row align-items-center">
			<div class="col-lg-12">
				<div class="col text-center">
					<table class="table table-hover bg-white">
						<thead>
						<tr class="table table-border">
							<th>입양번호</th>
							<th>아이디 (번호)</th>
							<th>동물이름 (번호)</th>
							<th>닉네임</th>
							<th>거주지</th>
							<th>결혼여부</th>
							<th>직업</th>
						</tr>
						</thead>
						<c:forEach var="adopt" items="${adopt.content}">
							<tbody>
							<th>${adopt.adNumber}</th>
							<th>${adopt.memberId} (${adopt.getMNumber()})</th>
							<th>${adopt.animalName} (${adopt.abNumber})</th>
							<th>${adopt.memberName}</th>
							<th>${adopt.residence}</th>
							<th>${adopt.maritalStatus}</th>
							<th>${adopt.job}</th>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		<!-- 페이징 처리 -->

		<div class="row mt-5">
			<div class="col text-center">
				<div class="block-27">
					<ul>
						<li>
							<c:if test="${adopt.startPage > 5}">
								<a href="/admin/adopt_temp/complete/adopt?pageNo=${adopt.startPage - 5}">&lt;</a>
							</c:if>
						</li>
						<li>
							<c:forEach var="pbNum" begin="${adopt.startPage}" end="${adopt.endPage}">
							<c:if test="${adopt.currentPage == pbNum}">
						<li class="active">
							<a href="/admin/adopt_temp/complete/adopt?pageNo=${pbNum}">${pbNum}</a>
						</li>
						</c:if>
						<c:if test="${adopt.currentPage != pbNum}">
							<li>
								<a href="/admin/adopt_temp/complete/adopt?pageNo=${pbNum}">${pbNum}</a>
							</li>
						</c:if>
						</c:forEach>
						</li>
						<li>
							<c:if test="${adopt.endPage < adopt.totalPages}">
								<a href="/admin/adopt_temp/complete/adopt?pageNo=${adopt.startPage + 5}">&gt;</a>
							</c:if>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</section>
<!-- 게시판 List 끝 -->

<script>
	function selectKind(kind) {
		if (kind == "adopt") {
			window.location.href = "/admin/adopt";
		} else if (kind == "wait") {
			window.location.href = "/admin/adopt/wait";
		} else if (kind == "complete") {
			window.location.href = "/admin/adopt/complete";
		} else if (kind == "refuse") {
			window.location.href = "/admin/adopt/refuse";
		}
	}
</script>

<!-- 풋터 -->
<%@ include file="/WEB-INF/jsp/include/footer.jspf" %>

<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
	<svg class="circular" width="48px" height="48px">
		<circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
		<circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
				stroke="#F96D00"/>
	</svg>
</div>

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

</body>
</html>