<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>글 삭제</h1>
<hr>

<input type="number" name="num" min="1" id="bno">
<button type="button" id="btnDel">글삭제</button>

<script src="/resources/js/jquery-3.6.0.js"></script>
<script>
	$('#btnDel').on('click', function () {
		
		var bno = $('#bno').val();
		console.log('bno : ' + bno);
		
		$.ajax({
			url: '/api/boards/' + bno,
			method: 'DELETE',
			success: function (data) {
				console.log(typeof data);
				console.log(data);
				
				if (data.result == 'success') {
					alert(bno + '번 글 삭제 성공!')
				}
			}
		});
	});
</script>
</body>
</html>






