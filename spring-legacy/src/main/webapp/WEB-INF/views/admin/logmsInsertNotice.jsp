<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert notice here</title>
</head>
<body>
	
<form id="frm" enctype="multipart/form-data">
	번호 		: <input type="number" name="num"><br>
	선택문제 	: <input type="text" name="subject"><br>
	공지사항 	: 
	<textarea rows="13" cols="40" name="content" id="content"></textarea>
	<br>
	날짜 		: <input type="date" name="regDate"><br>
	
	<button type="button" id="btn">공지사항입력</button>
	<button type="button" onclick="history.go(-1);">되돌아가기</button>
</form>
	
<script src="/resources/js/jquery-3.6.0.js"></script>
<script src="/resources/js/jquery.serializeObject.min.js"></script>
<script>
	$('#btn').on('click', function () {

		var obj = $('form#frm').serializeObject();
		console.log(obj);
		console.log(typeof obj);
		
		var strJson = JSON.stringify(obj);
		console.log(strJson);
		console.log(typeof strJson);
		
		// ajax 함수 호출
		$.ajax({
			url: '/api/notices',
			method: 'POST',
			data: strJson,
			contentType: 'application/json; charset=UTF-8',
			success: function (data) {
				console.log(data);
				
				if (data.result == 'success') {
					alert('공지사항 입력 성공!');
				}
			} // success
		});
		
	});
</script>

</body>
</html>