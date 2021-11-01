<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>ID 중복확인</h2>
<hr>
<%-- count 1: 아이디 중복, 0: 아이디 중복아님 --%>
<%-- 비교연산자 표현: == "eq"  != "ne"  > "gt"  < "lt"  >= "ge"  <= "le" --%>
<c:choose>
	<c:when test="${ count eq 1 }">
		<p>아이디 중복, 이미 사용중인 ID 입니다.</p>
	</c:when>
	<c:otherwise><!-- count == 0 -->
		<p>${ id } 는 사용가능한 ID 입니다.</p>
		<button type="button" id="btnUseId">ID 사용</button>
	</c:otherwise>
</c:choose>

<form action="/member/joinIdDupChk" method="get" name="frm">
	<input type="text" name="id" value="${ id }">
	<button type="submit">ID 중복확인</button>
</form>

<script src="/resources/js/jquery-3.6.0.js"></script>
<script>
	$('button#btnUseId').on('click', function () {
		// 검색한 ID값을 부모창의 id 입력상자에 넣기
		window.opener.document.frm.id.value = frm.id.value;
		// 현재창 닫기  window.close();
		close();
	});
</script>
</body>
</html>








