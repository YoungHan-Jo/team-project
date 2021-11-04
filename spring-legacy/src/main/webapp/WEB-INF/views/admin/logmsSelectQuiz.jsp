<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<h1>퀴즈관리자</h1>
   	<button type="button" id="btn">퀴즈목록보기</button>
	<button onclick="history.go(-1);">되돌아가기</button>
	<br><br>
	<table border="1">
		<thead>
			<tr>
				<th>퀴즈유형</th><th>퀴즈번호</th><th>질문</th><th>정답</th>
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
						<td>\${quiz.bunchNum}</td>
						<td>\${quiz.questionNum}</td>
						<td>\${quiz.question}</td>
						<td>\${quiz.answer}</td>
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