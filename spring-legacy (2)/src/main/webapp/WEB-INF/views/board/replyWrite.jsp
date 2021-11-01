<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">

<head>
  <jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>

<body>

  <!-- Navbar area -->
  <jsp:include page="/WEB-INF/views/include/top.jsp" />
  <!-- end of Navbar area -->


  <!-- Page Layout here -->
  <div class="row container">

	<!-- left menu area -->
	<jsp:include page="/WEB-INF/views/include/left.jsp" />
    <!-- end of left menu area -->
    

    <div class="col s12 m8 l9">
      <!-- Teal page content  -->
      <div class="section">

        <!-- card panel -->
        <div class="card-panel">
          <div class="row">
            <div class="col s12" style="padding: 0 5%;">

              <h5>게시판 답글쓰기</h5>
              <div class="divider" style="margin: 30px 0;"></div>

              <form action="/board/reply" method="POST" enctype="multipart/form-data">
              	<input type="hidden" name="pageNum" value="${ pageNum }">
              	<input type="hidden" name="reRef" value="${ reRef }">
              	<input type="hidden" name="reLev" value="${ reLev }">
              	<input type="hidden" name="reSeq" value="${ reSeq }">
              
                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">account_box</i>
                    <input id="id" type="text" name="mid" value="${ sessionScope.id }" readonly>
                    <label for="id">아이디</label>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field">
                    <i class="material-icons prefix">subtitles</i>
                    <input type="text" id="title" class="validate" name="subject">
                    <label for="title">제목</label>
                  </div>
                </div>
                <div class="row">
                  <div class="input-field">
                    <i class="material-icons prefix">subject</i>
                    <textarea id="textarea1" class="materialize-textarea" name="content"></textarea>
                    <label for="textarea1">내용</label>
                  </div>
                </div>

				<div class="row">
				  <div class="col s12">
					<button type="button" class="btn-small waves-effect waves-light" id="btnAddFile">파일 추가</button>	
				  </div>
				</div>
				
                <div class="row" id="fileBox">
                  <div class="col s12">
                    <input type="file" name="files">
                    <button class="waves-effect waves-light btn-small file-delete"><i class="material-icons">clear</i></button>
                  </div>
                </div>

                <br>
                <div class="row center">
                  <button class="btn waves-effect waves-light" type="submit">답글등록
                    <i class="material-icons right">create</i>
                  </button>
                  &nbsp;&nbsp;
                  <button class="btn waves-effect waves-light" type="reset">초기화
                    <i class="material-icons right">clear</i>
                  </button>
                  &nbsp;&nbsp;
                  <button class="btn waves-effect waves-light" type="button" onclick="location.href = '/board/boardList.jsp'">글목록
                    <i class="material-icons right">list</i>
                  </button>
                </div>
              </form>

            </div>
          </div>
        </div>
        <!-- end of card panel -->
        
      </div>
    </div>

  </div>

  <!-- footer area -->
  <jsp:include page="/WEB-INF/views/include/bottom.jsp" />
  <!-- end of footer area -->


  <!-- Scripts -->
  <jsp:include page="/WEB-INF/views/include/commonJs.jsp" />
  <script>
  	var fileCount = 1;
  
    $('button#btnAddFile').on('click', function () {
    	if (fileCount >= 5) {
    		alert('첨부파일은 최대 5개 까지만 첨부할 수 있습니다.');
    		return;
    	}
    	
    	var str = `
    		<div class="col s12">
	            <input type="file" name="files">
	            <button class="waves-effect waves-light btn-small file-delete"><i class="material-icons">clear</i></button>
            </div>
    	`;
    	
    	$('div#fileBox').append(str);
    	
    	fileCount++;
    });
    
    // 동적 이벤트 연결 (이벤트 등록을 이미 존재하는 요소에게 위임하는 방식)
    $('div#fileBox').on('click', 'button.file-delete', function () {
    	
    	//$(this).closest('div').remove(); // empty()와 구분 유의!
    	$(this).parent().remove();
    	
    	fileCount--;
    });
    
  </script>
</body>

</html>






