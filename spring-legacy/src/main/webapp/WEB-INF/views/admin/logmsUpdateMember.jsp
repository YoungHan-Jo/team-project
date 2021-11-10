<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원정보 수정하기</h1>
	<hr>

	<form id="frm">
		아이디: <input type="text" name="id"><br> 비밀번호: <input
			type="password" name="passwd"><br> 이름: <input
			type="text" name="name"><br> 생년월일: <input type="date"
			name="birthday"><br> 성별: <select name="gender">
			<option value="" disabled selected>성별을 선택하세요.</option>
			<option value="M">남자</option>
			<option value="F">여자</option>
			<option value="N">선택 안함</option>
		</select> <br> 이메일: <input type="email" name="email"><br> 이메일
		수신여부: <label><input name="recvEmail" value="Y" type="radio"
			checked /> 예</label> <label><input name="recvEmail" value="N"
			type="radio" /> 아니오</label> <br>
		<button type="button" id="btn">회원수정</button>
	</form>
	<button type="button" onclick="history.go(-1);">되돌아가기</button>

	<script src="/resources/js/jquery-3.6.0.js"></script>
	<script src="/resources/js/jquery.serializeObject.min.js"></script>
	<script>
		$('#btn').on('click', function() {

			var id = $('input[name="id"]').val();

			var obj = $('form#frm').serializeObject();
			console.log(obj);
			console.log(typeof obj);

			var strJson = JSON.stringify(obj);
			console.log(strJson);
			console.log(typeof strJson);

			// ajax 함수 호출
			$.ajax({
				url : '/api/members/' + id,
				method : 'PUT',
				data : strJson,
				contentType : 'application/json; charset=UTF-8',
				success : function(data) {
					console.log(data);

					alert(data.result);
				}
			});
		});
	</script>
</body>
</html>




