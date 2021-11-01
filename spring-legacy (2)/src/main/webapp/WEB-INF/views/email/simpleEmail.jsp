<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>관리자 화면 - Simple Email 전송하기</h1>
<hr>

<form action="/email/simple-mail" method="post">
	받는 사람 : <input type="text" name="receiver" placeholder="여러명 입력시 aa@a.com, bb@b.com, ..."><br>
	메일 제목 : <input type="text" name="subject"><br>
	메일 내용 : <textarea rows="5" cols="40" name="msg"></textarea><br>
	<button type="submit">메일 전송</button>
</form>

</body>
</html>






