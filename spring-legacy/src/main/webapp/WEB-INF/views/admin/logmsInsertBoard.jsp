<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<h5>게시판 새글쓰기</h5>
<hr>

<form id="frm" enctype="multipart/form-data">
<table border="1">
	<tr>
		<th>작성자 아이디</th>
		<td>
			<input type="text" name="mid" id="id">
		</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td>
			<input type="text" name="subject" id="subject">
		</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td>
			<textarea rows="13" cols="40" name="content" id="content"></textarea>
		</td>
	</tr>



		<br>
		<div>
			<button type="submit">새글등록</button>
			&nbsp;&nbsp;
			<button type="reset">초기화</button>
			&nbsp;&nbsp;
			<button type="button" onclick="location.href = '/board/list'">글목록</button>
		</div>
	</form>






</body>
</html>