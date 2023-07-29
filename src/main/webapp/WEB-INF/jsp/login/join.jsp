<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Petmily - Don't buy, Do Adopt</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
    <link rel="stylesheet" href="/resources/css/join.css">
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

    <style>
        .field-error {
            color: #dc3545;
            border-color: #dc3545;
            font-size: 13px;
            vertical-align: top;
        }

        .gender {
            font-size: 14.5px;
            padding-left: 10px
        }
    </style>
</head>
<body>
<div class="section">
    <div class="container">
        <div class="form">
            <div class="left-side">
                <form action="/join" method="post">
                    <div class="form-inputs">
                        <div style="text-align: center">
                            <a href="/"
                               style="text-decoration: none; color: black">
                                <h4><span class="flaticon-pawprint-1 mr-2"></span><b>Petmily</b></h4><br>
                            </a>
                        </div>
                        <div>
                            <input type="text" name="id" placeholder="아이디 (3-15자, 소문자+숫자)" pattern="^[a-z0-9]+$"
                                   minlength="3" maxlength="15" required
                                   value="${param.id}"
                            <spring:hasBindErrors name="memberJoinForm">
                                   <c:if test="${errors.hasFieldErrors('id')}">style="border-color: #dc3545"</c:if>
                            </spring:hasBindErrors>>

                            <spring:hasBindErrors name="memberJoinForm">
                                <c:if test="${errors.hasFieldErrors('id')}">
                                    <span class="field-error"><form:errors path="memberJoinForm.id"/></span>
                                </c:if>
                            </spring:hasBindErrors>
                        </div>
                        <div class="password">
                            <input id="pw" name="pw" type="password" placeholder="비밀번호 (8-16자, 영문+숫자+특수문자)"
                                   minlength="8" maxlength="16" required
                                   value="<c:out value="${param.pw}"/>"
                            <spring:hasBindErrors name="memberJoinForm">
                                   <c:if test="${errors.hasFieldErrors('pw')}">style="border-color: #dc3545"</c:if>
                            </spring:hasBindErrors>>

                            <spring:hasBindErrors name="memberJoinForm">
                                <c:if test="${errors.hasFieldErrors('pw')}">
                                    <span class="field-error"><form:errors path="memberJoinForm.pw"/></span>
                                </c:if>
                            </spring:hasBindErrors>

                            <input id="passwordCheck" name="confirmPw" type="password" placeholder="비밀번호 확인"
                                   minlength="8" maxlength="16" required
                                   value="<c:out value="${param.confirmPw}"/>"
                            <spring:hasBindErrors name="memberJoinForm">
                                   <c:if test="${errors.hasFieldErrors('confirmPw')}">style="border-color: #dc3545"</c:if>
                            </spring:hasBindErrors>>

                            <spring:hasBindErrors name="memberJoinForm">
                                <c:if test="${errors.hasFieldErrors('confirmPw')}">
                                    <span class="field-error"><form:errors path="memberJoinForm.confirmPw"/></span>
                                </c:if>
                            </spring:hasBindErrors>
                        </div>
                        <div>
                            <input type="text" name="name" placeholder="이름 (닉네임)"
                                   minlength="3" maxlength="15" required
                                   value="${param.name}">
                        </div>
                        <div>
                            <input type="date" name="birth" placeholder="생년월일"
                                   min="1900-01-01" max="2030-12-31" required
                                   value="${param.birth}">
                        </div>
                        <div>
                            <select name="gender" class="form-control; gender"
                                    <spring:hasBindErrors name="memberJoinForm">
                                        <c:if test="${errors.hasFieldErrors('gender')}">style="border-color: #dc3545"</c:if>
                                    </spring:hasBindErrors>
                            >
                                <option value="UNSELECTED">성별</option>
                                <option value="M"
                                        <c:if test="${param.gender eq 'M'}">selected</c:if>>남자
                                </option>
                                <option value="F"
                                        <c:if test="${param.gender eq 'F'}">selected</c:if>>여자
                                </option>
                            </select>
                            <spring:hasBindErrors name="memberJoinForm">
                                <c:if test="${errors.hasFieldErrors('gender')}">
                                    <span class="field-error"><form:errors path="memberJoinForm.gender"/></span>
                                </c:if>
                            </spring:hasBindErrors>
                        </div>
                        <div>
                            <input type="text" name="email" placeholder="이메일 주소 (예: petmily@naver.com)"
                                   maxlength="30" value="${param.email}"
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,6}$" required
                            <spring:hasBindErrors name="memberJoinForm">
                                   <c:if test="${errors.hasFieldErrors('email')}">style="border-color: #dc3545"</c:if>
                            </spring:hasBindErrors>
                            >
                            <spring:hasBindErrors name="memberJoinForm">
                                <c:if test="${errors.hasFieldErrors('email')}">
                                    <span class="field-error"><form:errors path="memberJoinForm.email"/></span>
                                </c:if>
                            </spring:hasBindErrors>
                        </div>
                        <div>
                            <input type="tel" name="phone" placeholder="연락처 (예: 01012345678)" pattern="^010\d{8}$"
                                   maxlength="11" value="${param.phone}" required
                                   oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                            <spring:hasBindErrors name="memberJoinForm">
                                   <c:if test="${errors.hasFieldErrors('phone')}">style="border-color: #dc3545"</c:if>
                            </spring:hasBindErrors>
                            >
                            <spring:hasBindErrors name="memberJoinForm">
                                <c:if test="${errors.hasFieldErrors('phone')}">
                                    <span class="field-error"><form:errors path="memberJoinForm.phone"/></span>
                                </c:if>
                            </spring:hasBindErrors>
                        </div>
                        <div class="login">
                            <br>
                            <button type="submit" class="btn btn-lg btn-block btn-success">회원가입</button>
                        </div>
                        <br>
                        <p class="login-text">계정이 이미 있습니까? <a href="/login">login</a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%-- footer --%>
<%@ include file="../include/footer.jspf" %>
</body>
</html>