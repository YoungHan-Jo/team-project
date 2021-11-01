<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>회원정보 삭제하기</h1>
<hr>

<input type="text" name="id" id="id">
<button type="button" id="btn">회원탈퇴</button>
<p id="message"></p>


<script src="/resources/js/jquery-3.6.0.js"></script>
<script>
	$('#btn').on('click', function () {
		
		var id = $('#id').val();

		// ajax 함수 호출
		$.ajax({
			url: '/api/members/' + id,
			method: 'DELETE',
			success: function (data) { // success 함수는 응답 상태코드가 200 정상일때 호출됨
				console.log(data);
				
				$('p#message').html(id + ' : 회원정보 삭제 ' + data)
							  .css('color', 'green');
			}, // success
			error: function (request, status, error) { // 응답 상태코드가 400, 500번대에 해당할때 호출됨
				alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
				
				$('p#message').html(id + ' : 회원정보 삭제 ' + request.responseText)
	              .css('color', 'red');
			}
		});
	});
</script>
</body>
</html>




