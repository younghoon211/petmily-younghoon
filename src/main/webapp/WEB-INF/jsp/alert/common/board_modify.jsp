<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    alert("게시글이 수정되었습니다.");
    location.href = "/board/detail?kindOfBoard=${boardModifyForm.kindOfBoard}&bNumber=${boardModifyForm.getBNumber()}";
</script>