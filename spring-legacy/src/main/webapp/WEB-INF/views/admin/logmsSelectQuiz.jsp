<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<h1>���������</h1>
   	<button type="button" id="btn6">�����Ϻ���</button>
	<button onclick="history.go(-1);">�ǵ��ư���</button>
	<br><br>
	<table border="1">
		<thead>
			<tr>
				<th>�����ȣ</th><th>��������</th><th>�ۼ���</th><th>�ð�</th>
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
			
			for (var bunch of array) {
				str += `
					<tr>
						<td>\${bunch.num}</td>
						<td>\${bunch.title}</td>
						<td>\${bunch.memberId}</td>
						<td>\${bunch.regDate}</td>
						<td>
							
							<button onclick="location.href='/quiz/modify?bunchNum=\${ bunch.num }'">����</button>
						<td>
						<td>
							<a href="/quiz/delete?bunchNum=\${bunch.num}">����</a>
						<td>
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


	
	$('button#btn6').on('click', function () {
		
		// ajax �Լ� ȣ�� - �񵿱� �ڹٽ�ũ��Ʈ ���
		$.ajax({
			url: '/api/bunches',
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