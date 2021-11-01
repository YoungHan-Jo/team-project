<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>글 상세보기</h1>
<hr>

<table border="1">
	<tr>
		<th>글번호</th>
		<td>
			<input type="number" name="num" id="bno">
		</td>
	</tr>
	<tr>
		<th>작성자 아이디</th>
		<td>
			<span id="mid"></span>
		</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td>
			<span id="subject"></span>
		</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td>
			<pre id="content"></pre>
		</td>
	</tr>
	<tr>
		<th>파일</th>
		<td id="fileArea">
			
		</td>
	</tr>
</table>


<script src="/resources/js/jquery-3.6.0.js"></script>
<script>
	
	$('input#bno').on('change', function () {
		
		var bno = $(this).val();
		console.log(bno);
		
		$.ajax({
			url: '/api/boards/' + bno,
			method: 'GET',
			success: function (data) {
				console.log(typeof data);
				console.log(data);
				
				showData(data);
			} // success
		});
	});
	
	
	function showData(obj) {
		
		// 게시글 정보 보이기
		var board = obj.board;
		
		$('span#mid').html(board.mid);
		$('span#subject').html(board.subject);
		$('pre#content').html(board.content);
		
		// 첨부파일 정보 보이기
		var attachList = obj.attachList;
		
		var str = '';
		
		if (attachList.length > 0) {
			
			str += '<ul>'
			for (var attach of attachList) {
				if (attach.filetype == 'O') {
					// 다운로드할 일반파일 경로
       				var fileCallPath = attach.uploadpath + '/' + attach.filename;
					
					str += `
						<li>
	           				<a href="/board/download.jsp?fileName=\${fileCallPath}">
	               				\${attach.filename}
	           				</a>
	           			</li>
						`;
				} else if (attach.filetype == 'I') {
					// 썸네일 이미지 경로
       				var fileCallPath = attach.uploadpath + '/s_' + attach.filename;
       				// 원본 이미지 경로
       				var fileCallPathOrigin = attach.uploadpath + '/' + attach.filename;
					
					str += `
						<li>
	           				<a href="/board/display.jsp?fileName=\${fileCallPathOrigin}">
	           					<img src="/board/display.jsp?fileName=\${fileCallPath}">
	           				</a>
	           			</li>
						`;
				}
			} // for
			str += '</ul>'
			
		} else { // attachList.length == 0
			str = '첨부파일 없음';
		}
		
		$('td#fileArea').html(str);
		
	} // showData
</script>
</body>
</html>






