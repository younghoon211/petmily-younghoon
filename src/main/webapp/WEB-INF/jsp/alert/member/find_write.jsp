<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    alert("게시글이 등록되었습니다.");
    <c:if test="${authUser.grade eq '관리자'}">
        window.location.href = "/admin/board?kindOfBoard=find";
    </c:if>
    <c:if test="${authUser.grade eq '일반'}">
        window.location.href = "/findBoard/list?sort=fno";
    </c:if>
</script>