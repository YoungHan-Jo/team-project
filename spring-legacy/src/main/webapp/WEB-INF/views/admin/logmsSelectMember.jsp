<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

</head>

<body>
    	<button type="button" id="btn">버튼</button>
		<button onclick="history.go(-1);">되돌아가기</button>
		<br><br>
		<table border="1">
			<thead>
				<tr>
					<th>아이디</th><th>이름</th><th>성별</th><th>이메일</th>
				</tr>
			</thead>
			<tbody>
				<tr><td colspan="4">버튼을 클릭하세요.</td></tr>
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
						<td>\${member.id}</td>
						<td>\${member.name}</td>
						<td>\${member.gender}</td>
						<td>\${member.email}</td>
					</tr>
				`;
			} // for
		} else { // array == null || array.length == 0
			str += `
				<tr><td colspan="4">데이터가 없습니다.</td></tr>
			`;
		}
		$('table > tbody').html(str);
	} // showData


	
	$('button#btn').on('click', function () {
		
		// ajax 함수 호출 - 비동기 자바스크립트 통신
		$.ajax({
			url: '/api/members',
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