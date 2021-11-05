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
		<th>���������Է�</th>
		<td>
			<textarea rows="13" cols="40" name="content" id="content"></textarea>
		</td>
	</tr>
	
</table>
<br>
<button type="button" id="btnWrite">�۾���</button>
</form>

<script src="/resources/js/jquery-3.6.0.js"></script>

<script>

	
	// �۾��� ��ư Ŭ������ ��
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
				
				
				alert('���ο� �۾��� ����!');
				
				$('form#frm')[0].reset(); 

			} // success
		});
		
	});
	
</script>

</body>
</html>