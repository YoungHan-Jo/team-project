<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />

<style>

#myBox {
    position: absolute;
    margin-top: 100px;
    font-size: 70%;
    line-height: 1;
    border: 0;
    padding: 0;
    outline: none;
    box-sizing: border-box;
    vertical-align: baseline;
}

#myBox #wrap {
    display: block;
    width: 900px;
    margin: 0 auto;
    padding-top: 35px;
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
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<!-- main -->
	<main id="main">

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
                        <li><a href="/member/quizCheckList">내가 푼 퀴즈</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- End Menu Box -->

		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">
				<div class="row d-flex justify-content-center align-items-center" style="margin: 94px auto;">
					<div class="col col-lg-6 mb-4 mb-lg-0">

						<form action="/member/passwd" method="POST" id="frm">
							<div class="card text-center text-white shadow" style="background: linear-gradient(to right bottom, #112D4E, #3F72AF); border-radius: 1rem;">
							    <div class="row p-3">
							        <div class="col-md-12">
							            <div class="card-body p-4">
							                <h1><b>비밀번호 변경</b></h1>
							                <hr class="mt-0 mb-4">
							                <div class="row pt-1">
							                    <div class="col-6 mb-3 input-group-sm">
							                        <h6 style="font-weight: bold; color: #BBE1FA;">아이디</h6>
							                        <input id="id" name="id" class="form-control" type="text" value="${id}" readonly>
							                    </div>
							                    <div class="col-6 mb-3 input-group-sm">
                                                    <h6 style="font-weight: bold; color: #BBE1FA;">기존 비밀번호</h6>
                                                    <input id="passwd" name="passwd" type="password" class="form-control">
                                                </div>
							                    <div class="col-6 mt-3 input-group-sm">
					                                <h6 style="font-weight: bold; color: #BBE1FA;">새 비밀번호</h6>
                                                    <input id="npasswd" name="npasswd" type="password" class="form-control">
							                    </div>
							                    <div class="col-6 mt-3 input-group-sm">
					                                <h6 style="font-weight: bold; color: #BBE1FA;">새 비밀번호 재확인</h6>
                                                    <input id="npasswd2" name="npasswd2" type="password" class="form-control">
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</div>
							<div>
								<button type="submit" class="btn btn-primary btn-sm mt-2">변경하기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
		<!-- End Why Us Section -->

	</main>
	<!-- End main -->

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- Top Button -->
	<div id="preloader"></div>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
	
    <script>
    
    $('input#npasswd2').on('focusout', function () {
    	
        const npasswd = $('input#npasswd').val();
        const npasswd2 = $(this).val();
        
        if (npasswd != npasswd2) {
            alert('비밀번호가 일치하지 않습니다.');
            $('input#npasswd').focus();
        }
    });
    

    $('form#frm').on('submit',function(event){
    	
        var result = confirm("비밀번호를 변경하시겠습니까");
        
        if(result == false){
        	event.preventDefault();
        }

    });
	    
    </script>

</body>

</html>