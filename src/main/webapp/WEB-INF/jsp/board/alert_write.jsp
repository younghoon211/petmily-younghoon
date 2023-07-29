<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    <c:if test="${authUser.grade eq '관리자'}">
        alert("게시글이 추가되었습니다.");
        location.href = "/admin/board?kindOfBoard=${param.kindOfBoard}";
    </c:if>
    <c:if test="${authUser.grade eq '일반'}">
        alert("게시글이 등록되었습니다.");
        location.href = "/board/list?kindOfBoard=${param.kindOfBoard}&sort=bno";
    </c:if>
</script>