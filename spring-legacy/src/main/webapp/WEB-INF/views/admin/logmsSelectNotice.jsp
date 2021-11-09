<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table {
		width: 600px;
	}
</style>
</head>
<body>

<h1>공지사항</h1>
<button type="button" id="btn">버튼</button>
<button onclick="history.go(-1);">되돌아가기</button>
		<br><br>
		<table border="1">
			<thead>
				<tr>
					<th>번호</th><th>선택번호</th><th>공지사항내용</th><button type="button" id="btnDelete">삭제</button>
				</tr>
			</thead>
			<tbody>
				<tr><td colspan="3"></td></tr>
			</tbody>
		</table>

<script src="/resources/js/jquery-3.6.0.js"></script>
<script>



	function showData(array) {
		str = '';
		
		if (array.length > 0) {
			for (var notice of array) {
				str += `
					<tr>
						<td>\${notice.num}</td>
						<td>\${notice.subject}</td>
						<td>\${notice.content}</td>
					</tr>
				`;
			} // for
		} else {
			str += `
				<tr>
					<td colspan="3">공지사항 글이 없습니다.</td>
				</tr>
			`;
		}
		
		$('tbody').append(str);
	} // showData
	


	
	$('button#btn').on('click', function () {
		
		// ajax 함수 호출 - 비동기 자바스크립트 통신
		$.ajax({
			url: '/api/notices',
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






