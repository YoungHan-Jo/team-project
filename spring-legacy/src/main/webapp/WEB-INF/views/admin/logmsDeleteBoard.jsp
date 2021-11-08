<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

<h1>�Խñ� ����</h1>
<br>
<hr>

<input type="number" name="num" min="1" id="bno">
<button type="button" id="btnDel">�ۻ���</button>
<button type="button" onclick="history.go(-1);">�ǵ��ư���</button>

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
					alert(bno + '�� �� ���� ����!')
				}
			}
		});
	});
</script>

</body>
</html>