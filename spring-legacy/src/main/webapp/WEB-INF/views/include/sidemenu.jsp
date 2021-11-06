<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
#myBox {

	font-size: 70%;
	line-height: 1;
	border: 0;
	padding: 0;
	outline: none;
	box-sizing: border-box;
	vertical-align: baseline;
}
#myBox #menu {
	padding: 13px 16px;
	width: 150px;
	margin: 0 auto;
	position: relative;
	background: #112D4E;
	color: #fff;
	border-left: 4px solid #6c6d70;
	font-size: 1.5em;
	text-align: center;
	transition: all 0.15s linear;
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
}
#myBox #menu ul {
	position: absolute;
	top: 100%;
	left: -4px;
	width: 150px;
	padding: 5px 0px;
	border-left: 4px solid #8e9196;
	background: #fff;
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
	list-style: none;
}
#myBox #menu ul li a {
	font-size: 0.85em;
	text-decoration: none;
	display: block;
	color: #3F72AF;
	padding: 7px 15px;
}
#myBox #menu ul li a:hover {
	color: #6fa0e9;
	background: #DBE2EF;
}
</style>



<!-- Menu Box -->
<div id="myBox">
	<div id="wrap">
		<div id="menu">
			<a>내 정보</a>
                  <ul>
                      <li><a href="/member/modify">정보 수정</a></li>
                      <li><a href="/member/passwd">비밀번호 변경</a></li>
                      <li><a href="/member/remove">회원 탈퇴</a></li>
                      <li><a href="/member/myboardList">내가 쓴 게시물</a></li>
                      <li><a href="/member/myCommentList">내가 쓴 댓글</a></li>
                      <li><a href="/member/myQuizList">내가 만든 퀴즈</a></li>
                      <li><a href="/member/myPrevQuizList">내가 푼 퀴즈</a></li>
                  </ul>
		</div>
	</div>
</div>
<!-- End Menu Box -->