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
			success: function (data) {
				console.log(data);
				
				if (data.isDeleted == true) {
					$('p#message').html(id + ' : 회원정보 삭제 성공')
								  .css('color', 'green');
				} else {
					$('p#message').html(id + ' : 회원정보 삭제 실패')
					              .css('color', 'red');
				}
			} // success
		});
	});
</script>
</body>
</html>




