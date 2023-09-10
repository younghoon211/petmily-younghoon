<%@ page contentType="text/html; charset=utf-8" %>
<script>
    alert("임시보호 신청이 완료되었습니다.");
    location.href = "/abandonedAnimal/detail?abNumber=${adoptTempSubmitForm.abNumber}";
</script>