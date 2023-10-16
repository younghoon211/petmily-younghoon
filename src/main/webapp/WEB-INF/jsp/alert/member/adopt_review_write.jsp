<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    alert("입양후기가 등록되었습니다.");

    <c:if test="${authUser.grade eq '관리자'}">
        window.location.href = "/admin/board?kindOfBoard=adoptReview";
    </c:if>
    <c:if test="${authUser.grade eq '일반'}">
        window.location.href = "/adoptReview/list?kindOfBoard=adoptReview&sort=adoptReviewNo";
    </c:if>
</script>