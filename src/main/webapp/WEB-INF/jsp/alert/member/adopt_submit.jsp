<%@ page contentType="text/html; charset=utf-8" %>
<script>
    alert("입양 신청이 완료되었습니다.");
    window.location.href = "/abandonedAnimal/detail?abNumber=${adoptTempSubmitForm.abNumber}";
</script>