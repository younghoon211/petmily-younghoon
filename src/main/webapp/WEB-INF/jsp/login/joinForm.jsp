<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Petmily-Don't buy, Do Adopt</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%--    <script src="/resources/js/joinCheck.js"></script>--%>
    <%--    <script type="text/javascript" src="/resources/petsitting-master/js/jquery-3.2.1.min.js"></script>--%>
    <%--    <script type="text/javascript">--%>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Loding font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

    <!-- Custom Styles -->
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
    <link rel="stylesheet" href="/resources/css/join.css">

    <style>
        /*.container {*/
        /*    max-width: 560px;*/
        /*}*/
        .field-error {
            color: #dc3545;
            font-size: 13px;
            vertical-align: top;
        }
    </style>
</head>
<body>

<div class="section">
    <div class="container">
        <div class="form">
            <div class="left-side"> <!-- 간격조정 : left-side margin -->
                <span class="brand">
                        <a href="${pageContext.request.contextPath}/"
                           style="text-decoration: none; color: black">
                            <h4><b>Petmily</b></h4><br>
                        </a>
                	</span>
                <form action="/join" method="post">
                    <div class="form-inputs">
                        <div>
                            <input type="text" name="id" placeholder="아이디 (3-15자, 소문자+숫자)" pattern="^[a-z0-9]+$"
                                   minlength="3" maxlength="15" required="required"
                                   value="<c:out value="${param.id}"/>">
                            <spring:hasBindErrors name="joinRequest">
                                <c:if test="${errors.hasFieldErrors('id')}">
                                    <span class="field-error"><form:errors path="joinRequest.id"/></span>
                                </c:if>
                            </spring:hasBindErrors>
                        </div>
                        <div class="password">
                            <input id="pw" name="pw" type="password" placeholder="비밀번호 (8-16자, 영문+숫자+특수문자)"
                                   minlength="8" maxlength="16" required="required"
                                   value="<c:out value="${param.pw}"/>">
                            <spring:hasBindErrors name="joinRequest">
                                <c:if test="${errors.hasFieldErrors('pw')}">
                                    <span class="field-error"><form:errors path="joinRequest.pw"/></span>
                                </c:if>
                            </spring:hasBindErrors>

                            <input id="passwordCheck" name="confirmPw" type="password" placeholder="비밀번호 확인"
                                   minlength="8" maxlength="16" required="required"
                                   value="<c:out value="${param.confirmPw}"/>">
                            <spring:hasBindErrors name="joinRequest">
                                <c:if test="${errors.hasFieldErrors('confirmPw')}">
                                    <span class="field-error"><form:errors path="joinRequest.confirmPw"/></span>
                                </c:if>
                            </spring:hasBindErrors>
                        </div>
                        <div>
                            <input type="text" name="name" placeholder="이름 (닉네임)"
                                   minlength="3" maxlength="15" required="required"
                                   value="<c:out value="${param.name}"/>">
                        </div>
                        <div>
                            <input type="date" name="birth" placeholder="생년월일"
                                   min="1900-01-01" max="2023-12-31" required="required"
                                   value="${param.birth}">
                        </div>
                        <div>
                            <select name="gender" class="form-control" style="font-size: 14.5px; padding-left: 10px">
                                <option value="<c:out value="UNSELECTED"/>">성별</option>
                                <option value="<c:out value="M"/>"
                                        <c:if test="${param.gender eq 'M'}">selected="selected"</c:if>>남자
                                </option>
                                <option value="<c:out value="F"/>"
                                        <c:if test="${param.gender eq 'F'}">selected="selected"</c:if>>여자
                                </option>
                            </select>
                            <spring:hasBindErrors name="joinRequest">
                                <c:if test="${errors.hasFieldErrors('gender')}">
                                    <span class="field-error"><form:errors path="joinRequest.gender"/></span>
                                </c:if>
                            </spring:hasBindErrors>
                        </div>
                        <div>
                            <input type="text" name="email" placeholder="이메일 주소 (예: petmily@naver.com)"
                                   maxlength="30" required="required" value="<c:out value="${param.email}"/>"
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                        </div>
                        <div>
                            <input type="tel" name="phone" placeholder="연락처 (예: 01012345678)" required="required"
                                   maxlength="11" value="<c:out value="${param.phone}"/>"
                                   oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                            <%--                                pattern="^010\d{8}$"--%>
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
</body>
</html>