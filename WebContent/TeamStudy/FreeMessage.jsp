<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 수정 삭제 입력 모두 여기 페이지로 이동하도록 -->
<c:choose>
   <c:when test="${WHERE eq 'INS'}">
      <c:set var="successMessage" value="입력에 성공했다" />
      <c:set var="failMessage" value="입력에 실패했다" />
      <c:set var="successMoveURL" value=/FreeBoard/FreeList.kosmo" />
   </c:when>
   <c:when test="${WHERE eq 'EDT'}">
      <c:set var="successMessage" value="수정에 성공했다" />
      <c:set var="failMessage" value="수정에 실패했다" />
      <c:set var="successMoveURL" value="/DataRoom/View.kosmo?no=${no}" />
   </c:when>
   <c:otherwise>
      <c:set var="successMessage" value="삭제에 성공했다" />
      <c:set var="failMessage" value="삭제에 실패했다" />
      <c:set var="successMoveURL" value="/FreeBoard/FreeList.kosmo" />
   </c:otherwise>
</c:choose>

<script>

   <c:choose>
      <c:when test="${successFail eq 1}">
         alert("${successMessage}");
         location.replace("<c:url value='${successMoveURL}'/>");
      </c:when>
   
      <c:when test="${successFail eq 0}">
         alert("${failMessage}");
         history.back();
      </c:when>
      <c:otherwise>
         alert("파일 업로드 용량을 초과했어요!");
         history.back();   
      </c:otherwise>
   </c:choose>
</script>

