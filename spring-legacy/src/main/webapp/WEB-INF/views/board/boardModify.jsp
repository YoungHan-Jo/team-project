<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<main id="main">
		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">
				<div class="card">
					<div class="col-sm-12 col-md-8 col-lg-10 p-5">
						<h5 class="fw-bold">게시판 글수정</h5>
						<div class="divider" style="margin: 30px 0;"></div>

						<form action="/board/modify" method="POST" enctype="multipart/form-data">
							<input type="hidden" name="pageNum" value="${ pageNum }"> <input type="hidden" name="num" value="${ board.num }">

							<div class="row p-2">
								<div class="col-sm-1">
									<label for="id">아이디</label>
								</div>
								<div class="col-sm-11">
									<input id="id" class="form-control form-control-sm col-md-8" type="text" name="memberId" value="${ sessionScope.id }" readonly>
								</div>
							</div>


							<div class="row p-2">
								<div class="col-sm-1">
									<label for="title">제목</label>
								</div>
								<div class="col-sm-11">
									<input type="text" id="title" class="validate form-control form-control-sm" name="subject" value="${ board.subject }">
								</div>
							</div>

							<div class="row p-2">
								<div class="col-sm-1">
									<label for="textarea1">내용</label>
								</div>
								<div class="col-sm-11 form-floating">
									<textarea class="form-control" id="textarea1" name="content" style="height: 100px">${ board.content }</textarea>
								</div>
							</div>

							<div class="row p-2">
								<div class="col-sm-11">
									<button type="button" id="btnAddFile">파일 추가</button>
								</div>
							</div>

							<div class="row" id="oldFileBox">
								<c:forEach var="attach" items="${ attachList }">
									<input type="hidden" name="oldfile" value="${ attach.uuid }">
									<div>
										<span class="filename">${ attach.filename }</span>
										<button class="delete-oldfile">취소</button>
									</div>
								</c:forEach>
							</div>
							
							<div class="row" id="newFileBox"></div>

							<br>
							<div>
								<button type="submit" class="btn btn-outline-success">수정</button>
								&nbsp;&nbsp;
								<button type="reset" class="btn btn-outline-warning">초기화</button>
								&nbsp;&nbsp;
								<button type="button" class="btn btn-outline-info" onclick="location.href = '/board/list?pageNum=${ pageNum }'">글목록</button>
							</div>
						</form>

					</div>
				</div>
			</div>
		</section>
		<!-- End Why Us Section -->

	</main>
	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- Top Button -->
	<div id="preloader"></div>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"> <i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
	<script>
  	var fileCount = ${ fn:length(attachList) };  // 현재 첨부된 파일 갯수
  	
  
    $('button#btnAddFile').on('click', function () {
    	if (fileCount >= 5) {
    		alert(`첨부파일은 최대 5개 까지만 첨부할 수 있습니다.`);
    		return;
    	}
    	
    	var str = `
    		<div class="input-group">
    		  <input type="file" class="form-control" name="files"  aria-describedby="inputGroupFileAddon04" aria-label="Upload">
    		  <button class="btn btn-outline-secondary delete-addfile" type="button" id="inputGroupFileAddon04"><i class="bi bi-x"></i></button>
    		</div>
    		<div class="divider" style="margin: 10px 0;"></div>
    	`;
    	
    	$('div#newFileBox').append(str);
    	fileCount++;
    });
    
   
    $('div#newFileBox').on('click', 'button.delete-addfile', function () {
    	$(this).parent().remove();
    	fileCount--;
    });
    
    
    $('button.delete-oldfile').on('click', function () {
    	$(this).parent().prev().prop('name', 'delfile');
    	$(this).parent().remove();
    	fileCount--;
    });
  </script>

</body>
</html>