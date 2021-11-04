<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<h1>���������</h1>
   	<button type="button" id="btn">�����Ϻ���</button>
	<button onclick="history.go(-1);">�ǵ��ư���</button>
	<br><br>
	<table border="1">
		<thead>
			<tr>
				<th>��������</th><th>�����ȣ</th><th>����</th><th>����</th>
			</tr>
		</thead>
		<tbody>
			<tr><td colspan="4">��ư�� Ŭ���ϼ���.</td></tr>
		</tbody>
	</table>

<script src="/resources/js/jquery-3.6.0.js"></script>
<script>
	function showData(array) {
		var str = '';
		
		if (array != null && array.length > 0) {
			
			for (var member of array) {
				str += `
					<tr>
						<td>\${quiz.bunchNum}</td>
						<td>\${quiz.questionNum}</td>
						<td>\${quiz.question}</td>
						<td>\${quiz.answer}</td>
					</tr>
				`;
			} // for
		} else { // array == null || array.length == 0
			str += `
				<tr><td colspan="4">�����Ͱ� �����ϴ�.</td></tr>
			`;
		}
		$('table > tbody').html(str);
	} // showData


	
	$('button#btn').on('click', function () {
		
		// ajax �Լ� ȣ�� - �񵿱� �ڹٽ�ũ��Ʈ ���
		$.ajax({
			url: '/api/quizs/' + bunchNum,
			method: 'GET',
			success: function (data) {
				console.log(data);
				console.log(typeof data);
				
				showData(data);
			}
		});

	});
</script>

</body>
</html>