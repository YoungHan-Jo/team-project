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

<h1>게시글 보기</h1>
<button onclick="history.go(-1);">되돌아가기</button>
<hr>


<table border="1">
<thead>
	<tr>
		<th>번호</th>
        <th>작성자</th>
        <th>제목</th>
	</tr>
</thead>
<tbody>
	
</tbody>
</table>


<script src="/resources/js/jquery-3.6.0.js"></script>
<script>
	function getData(page) {
		$.ajax({
			url: '/api/boards/pages/' + page,
			method: 'GET',
			success: function (data) {
				console.log(typeof data);
				console.log(data);
				
				showData(data);
			}
		});
	} // getData
	
	
	function showData(array) {
		str = '';
		
		if (array.length > 0) {
			for (var board of array) {
				str += `
					<tr>
						<td>\${board.num}</td>
						<td>\${board.memberId}</td>
						<td>\${board.subject}</td>
					</tr>
				`;
			} // for
		} else {
			str += `
				<tr>
					<td colspan="4">게시판 글이 없습니다.</td>
				</tr>
			`;
		}
		
		$('tbody').append(str);
	} // showData
	
	
	
	// 페이지 로딩후 바로 실행하는 명령문 =====
	var page = 1;
	getData(page);
	page++;
	
	$(window).on('scroll', function () {
		console.log('scroll 이벤트 발생');
		
		// 스크롤 이벤트 문서 최하단 감지하면, 데이터 가져와서 화면에 덧붙이기
		if ($(window).scrollTop() + $(window).height() >= $(document).height()) {
			getData(page);
			page++;
		}
	});
</script>
</body>
</html>






