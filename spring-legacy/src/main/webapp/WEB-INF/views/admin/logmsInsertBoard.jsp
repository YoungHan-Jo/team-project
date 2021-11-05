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
		<th>공지사항입력</th>
		<td>
			<textarea rows="13" cols="40" name="content" id="content"></textarea>
		</td>
	</tr>
	
</table>
<br>
<button type="button" id="btnWrite">글쓰기</button>
</form>

<script src="/resources/js/jquery-3.6.0.js"></script>

<script>

	
	// 글쓰기 버튼 클릭했을 때
	$('#btnWrite').on('click', function () {
		
		var form = $('form#frm')[0]; 
		
		
		var formData = new FormData(form); 
		console.log(formData);
		console.log(typeof formData);
		
		
		$.ajax({
			url: '/api/boards',
			method: 'POST',
			data: formData,
			success: function (data) {
				console.log(data);
				
				
				alert('새로운 글쓰기 성공!');
				
				$('form#frm')[0].reset(); 

			} // success
		});
		
	});
	
</script>

</body>
</html>