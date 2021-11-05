<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<h1>���� �߰��ϱ�</h1>
<hr>

	<form id="frm">
		<div id='quizBox'>
			<div style="border: solid 1px;">
				<label>���� �̸�</label> <input type="text" name="title" > <br>
				<label>����</label><input type="text" name="questions" > <br> 
				<label>1</label> <input type="text" name="numOnes" > <br> 
				<label>2</label> <input type="text" name="numTwos" > <br> 
				<label>3</label> <input type="text" name="numThrees" > <br> 
				<label>4</label> <input type="text" name="numFours" > <br> 
				<label>����</label> <input type="text" name="answers" > <br>
			</div>
			<br>
		</div>
		<div class="row">
			<button type="button" id="btn-addQuiz">+ ���� �߰�</button>
		</div>
		<button type="button" onclick="history.go(-1);">�ǵ��ư���</button>
	</form>

<script>
	$('#btn-addQuiz').on('click', function() {
		var obj = $('form#frm').serializeObject();
		console.log(obj);
		console.log(typeof obj);
		
		var strJson = JSON.stringify(obj);
		console.log(strJson);
		console.log(typeof strJson);
		
		$.ajax({
			url: '/api/quizs',
			method: 'POST',
			data: strJson,
			contentType: 'application/json; charset=UTF-8',
			success: function (data) {
				console.log(data);
				
				if (data.result == 'success') {
					alert('ȸ������ ����!');
				}
			} // success
		});			 
	});
</script>

</body>
</html>