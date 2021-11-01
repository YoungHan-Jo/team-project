<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html lang="ko">

<head>
  <jsp:include page="/WEB-INF/views/include/head.jsp" />
  <style>
    time.comment-date {
      font-size: 13px;
    }
  </style>
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
      <!-- page content  -->
      <div class="section">

        <div class="card-panel">
          <div class="row">
            <div class="col s12" style="padding: 0 2%;">

              <h5>게시판 상세보기</h5>
              <div class="divider" style="margin: 30px 0;"></div>

              <table class="striped" id="boardList">
                  <tr>
                    <th class="center-align">제목</th>
                    <td colspan="5">${ board.subject }</td>
                  </tr>
                  <tr>
                    <th class="center-align">작성자</th>
                    <td>${ board.mid }</td>
                    <th class="center-align">작성일</th>
                    <td><fmt:formatDate value="${ board.regDate }" pattern="yyyy.MM.dd HH:mm:ss" /></td>
                    <th class="center-align">조회수</th>
                    <td>${ board.readcount }</td>
                  </tr>
                  <tr>
                    <th class="center-align">내용</th>
                    <td colspan="5">
                      <pre>${ board.content }</pre>
                    </td>
                  </tr>
                  <tr>
                    <th class="center-align">첨부파일</th>
                    <td colspan="5">
                    
                    <c:choose>
                    	<%-- 첨부파일이 있으면 --%>
                    	<c:when test="${ fn:length(attachList) gt 0 }"><%-- attachCount gt 0 --%>
                    	
                    		<c:forEach var="attach" items="${ attachList }">
								<c:if test="${ attach.filetype eq 'O' }"><%-- 일반파일 --%>
									<%-- 다운로드할 일반파일 경로 --%>
                   					<c:set var="fileCallPath" value="${ attach.uploadpath }/${ attach.uuid }_${ attach.filename }" />
                   					<li>
	                       				<a href="/download?fileName=${ fileCallPath }">
		                       				<i class="material-icons">file_present</i>
		                       				${ attach.filename }
	                       				</a>
	                       			</li>
								</c:if>                   		
                    			<c:if test="${ attach.filetype eq 'I' }"><%-- 이미지파일 --%>
                    				<%-- 썸네일 이미지 경로 --%>
	                   				<c:set var="fileCallPath" value="${ attach.uploadpath }/s_${ attach.uuid }_${ attach.filename }" />
	                   				<%-- 원본 이미지 경로 --%>
	                   				<c:set var="fileCallPathOrigin" value="${ attach.uploadpath }/${ attach.uuid }_${ attach.filename }" />
	                   				<li>
	                       				<a href="/display?fileName=${ fileCallPathOrigin }">
	                       					<img src="/display?fileName=${ fileCallPath }">
	                       				</a>
	                       			</li>
                    			</c:if>
                    		</c:forEach>
                    	
                    	</c:when>
                    	<c:otherwise>
                    		첨부파일 없음
                    	</c:otherwise>
                    </c:choose>

                    </td>
                  </tr>
              </table>


              <div class="section">
                <div class="row">
                  <div class="col s12 right-align">
                  
	                  <%-- 로그인 했을때 --%>
	                  <c:if test="${ not empty sessionScope.id }">
	                  	<%-- 로그인 아이디와 글작성자 아이디가 같을때 --%>
	                  	<c:if test="${ sessionScope.id eq board.mid }">
	                  		<a class="btn waves-effect waves-light" href="/board/modify?num=${ board.num }&pageNum=${ pageNum }">
		                      <i class="material-icons left">edit</i>글수정
		                    </a>
		                    <a class="btn waves-effect waves-light" onclick="remove(event)">
		                      <i class="material-icons left">delete</i>글삭제
		                    </a>
	                  	</c:if>
	                  
	                  	<a class="btn waves-effect waves-light" href="/board/reply?reRef=${ board.reRef }&reLev=${ board.reLev }&reSeq=${ board.reSeq }&pageNum=${ pageNum }">
	                      <i class="material-icons left">reply</i>답글
	                    </a>
	                  </c:if>
                  
                      <a class="btn waves-effect waves-light" href="/board/list?pageNum=${ pageNum }">
                        <i class="material-icons left">list</i>글목록
                      </a>
                  </div>
                </div>
              </div>

              
              <!-- comment area -->
              
              <!-- end of comment area -->

            </div>
          </div>
        </div>
        <!-- end of card-panel -->

      </div>
    </div>

  </div>

  <!-- footer area -->
  <jsp:include page="/WEB-INF/views/include/bottom.jsp" />
  <!-- end of footer area -->


  <!-- Scripts -->
  <jsp:include page="/WEB-INF/views/include/commonJs.jsp" />
  <script>
    // 글삭제 버튼을 클릭하면 호출되는 함수
  	function remove(event) {
    	event.preventDefault(); // a태그 기본동작 막기
    	
  		var isRemove = confirm('이 글을 삭제하시겠습니까?');
  		if (isRemove == true) {
  			location.href = '/board/remove?num=${ board.num }&pageNum=${ pageNum }';
  		}
  	}
  </script>
</body>

</html>






