<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
<link href="/resources/css/sidemenu.css" rel="stylesheet"/>
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<!-- main -->
	<main id="main">

		<!-- sidemenu -->
		<jsp:include page="/WEB-INF/views/include/sidemenu.jsp"></jsp:include>

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