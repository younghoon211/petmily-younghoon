<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    alert("입양후기가 등록되었습니다.");

    <c:if test="${authUser.grade eq '관리자'}">
        location.href = "/admin/board?kindOfBoard=입양후기";
    </c:if>
    <c:if test="${authUser.grade eq '일반'}">
        location.href = "/adopt_review/list?kindOfBoard=입양후기&sort=adoptReviewNo";
    </c:if>
</script>