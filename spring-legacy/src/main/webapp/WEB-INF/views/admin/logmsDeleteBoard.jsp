<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

<h1>게시글 삭제</h1>
<br>
<hr>

<input type="number" name="num" min="1" id="bno">
<button type="button" id="btnDel">글삭제</button>
<button type="button" onclick="history.go(-1);">되돌아가기</button>

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