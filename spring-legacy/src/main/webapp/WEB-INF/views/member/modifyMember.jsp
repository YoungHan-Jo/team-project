<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 문자열 →  Date 객체 변환  -->
<fmt:parseDate value="${member.birthday}" pattern="yyyymmdd" var="dateBirthday"/>
<!-- Date 객체 → 문자열 변환  -->
<fmt:formatDate value="${pageScope.dateBirthday}" pattern="yyyy-mm-dd" var="strBirthday" />

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

.form-check {
    display: inline-block;
    margin-left: 5px;
    margin-right: 8px;
}

</style>
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<!-- main -->
	<main id="main" style="background-color: #DBE2EF;">

		<!-- Menu Box -->
		<div id="myBox">
			<div id="wrap">
				<div id="menu">
					<a>내 정보</a>
                    <ul>
                        <li><a href="/member/modify">정보 수정</a></li>
                        <li><a href="/member/passwd">비밀번호 변경</a></li>
                        <li><a href="/member/remove">회원 탈퇴</a></li>
                    </ul>
				</div>
			</div>
		</div>
		<!-- End Menu Box -->

		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">
				<div class="row d-flex justify-content-center align-items-center" style="margin: 33px auto;">
					<div class="col col-lg-6 mb-4 mb-lg-0">

						<form action="/member/modify" method="POST" enctype="multipart/form-data">
							<div class="card text-center text-white shadow" style="background: linear-gradient(to right bottom, #112D4E, #3F72AF); border-radius: 1rem;">
							    <div class="row p-3">
							        <div class="col-md-4" style="margin-top: 100px;">
								        <c:choose>
	                                        <c:when test="${ not empty profileImg }">
	                                            <c:set var="fileCallPath" value="${ profileImg.uploadpath }/${profileImg.memberId}/${ profileImg.uuid }_${ profileImg.filename }" />
	                                            <img src="/display?fileName=${fileCallPath}" class="img-fluid" style="width: 180px; height: 180px; border-radius: 100px;" />
	                                        </c:when>
	                                        <c:otherwise>
	                                            <img src="/resources/img/unknown.png" class="img-fluid" style="width: 180px; height: 180px; border-radius: 100px;" />
	                                        </c:otherwise>  
	                                    </c:choose>
							            <input class="form-control form-control-sm" id="formFileSm" name="multipartFile" type="file">
							        </div>
							        <div class="col-md-8">
							            <div class="card-body p-4">
							                <h1><b>내 정보 수정</b></h1>
							                <hr class="mt-0 mb-4">
							                <div class="row pt-1">
							                    <div class="col-6 mb-3 input-group-sm">
							                        <h6 style="font-weight: bold; color: #BBE1FA;">아이디</h6>
							                        <input id="id" class="form-control" name="id" type="text" value="${member.id}" readonly>
							                    </div>
							                    <div class="col-6 mb-3 input-group-sm">
							                        <h6 style="font-weight: bold; color: #BBE1FA;">이름</h6>
							                        <input id="name" class="form-control" name="name" value="${member.name}" type="text">
							                    </div>
							                    <div class="col-6 mb-3 input-group-sm">
							                        <h6 style="font-weight: bold; color: #BBE1FA;">생년월일</h6>
							                        <input id="birthday" class="form-control" name="birthday" value="${strBirthday}" type="date">
							                    </div>
							                    <div class="col-6 mb-3 input-group-sm">
							                        <h6 style="font-weight: bold; color: #BBE1FA;">성별</h6>
							                        <div class="form-check">
							                            <input class="form-check-input" type="radio" name="gender" id="gender" value="M" <c:if test="${member.gender eq 'M'}">checked</c:if>>
							                            <label class="form-check-label">남자</label>
							                        </div>
							                        <div class="form-check">
							                            <input class="form-check-input" type="radio" name="gender" id="gender" value="F" <c:if test="${member.gender eq 'F'}">checked</c:if>>
							                            <label class="form-check-label">여자</label>
							                        </div>
							                    </div>
							                    <div class="col-6 mb-3 input-group-sm">
							                        <h6 style="font-weight: bold; color: #BBE1FA;">이메일</h6>
							                        <input id="email" class="form-control" name="email" value="${member.email}" type="email">
							                    </div>
							                    <div class="col-6 mb-3">
                                                    <h6 style="font-weight: bold; color: #BBE1FA;">이메일 수신 여부</h6>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="recvEmail" id="recvEmail" value="Y" <c:if test="${member.recvEmail eq 'Y'}">checked</c:if>>
                                                        <label class="form-check-label">예</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="recvEmail" id="recvEmail" value="N" <c:if test="${member.recvEmail eq 'N'}">checked</c:if>>
                                                        <label class="form-check-label">아니오</label>
                                                    </div>                                                        
                                                </div>
                                                <hr class="mt-4 mb-0">
							                    <div class="col-5 mt-4">
					                               <h6 style="font-weight: bold; color: #BBE1FA;">비밀번호 확인</h6>
							                    </div>
							                    <div class="col-7 mt-3 input-group-sm">
                                                    <input type="password" class="form-control" id="passwd" required>
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</div>
							<div>
								<button type="submit" class="btn btn-primary btn-sm mt-2">수정하기</button>
								<button type="reset" class="btn btn-danger btn-sm mt-2">초기화</button>
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

</body>

</html>