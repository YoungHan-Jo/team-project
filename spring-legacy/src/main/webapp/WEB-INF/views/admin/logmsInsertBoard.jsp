<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<h5>�Խ��� ���۾���</h5>
<hr>

<form id="frm" enctype="multipart/form-data">
<table border="1">
	<tr>
		<th>�ۼ��� ���̵�</th>
		<td>
			<input type="text" name="mid" id="id">
		</td>
	</tr>
	<tr>
		<th>������</th>
		<td>
			<input type="text" name="subject" id="subject">
		</td>
	</tr>
	<tr>
		<th>�۳���</th>
		<td>
			<textarea rows="13" cols="40" name="content" id="content"></textarea>
		</td>
	</tr>



		<br>
		<div>
			<button type="submit">���۵��</button>
			&nbsp;&nbsp;
			<button type="reset">�ʱ�ȭ</button>
			&nbsp;&nbsp;
			<button type="button" onclick="location.href = '/board/list'">�۸��</button>
		</div>
	</form>






</body>
</html>