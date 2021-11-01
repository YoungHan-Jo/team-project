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


  <!-- Page Layout -->
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

              <h5>회원탈퇴</h5>
              <div class="divider" style="margin: 30px 0;"></div>

              <form action="/member/remove" method="POST" id="frm">
                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">account_box</i>
                    <input id="id" type="text" name="id" value="${ sessionScope.id }" readonly>
                    <label for="id">아이디</label>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">lock</i>
                    <input id="passwd" type="password" class="validate" name="passwd">
                    <label for="passwd">비밀번호</label>
                  </div>
                </div>

                <br>
                <div class="row center">
                  <button class="btn waves-effect waves-light" type="submit">회원탈퇴
                    <i class="material-icons right">create</i>
                  </button>
                  &nbsp;&nbsp;
                  <button class="btn waves-effect waves-light" type="reset">초기화
                    <i class="material-icons right">clear</i>
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
  <!-- end of Page Layout -->

  <!-- footer area -->
  <jsp:include page="/WEB-INF/views/include/bottom.jsp" />
  <!-- end of footer area -->


  <!--  Scripts-->
  <jsp:include page="/WEB-INF/views/include/commonJs.jsp" />
  <script>
    $('form#frm').on('submit', function (event) {
    	// alert() - 알림, confirm() - 확인 취소 버튼2개, prompt() - 직접 텍스트상자 입력
		var isDelete = confirm('정말 회원탈퇴 하시겠습니까?'); // true/false 리턴
		console.log('isDelete : ' + isDelete);
		
		if (isDelete == false) {
			event.preventDefault(); // form태그의 기본동작 막기
		}
    });
  </script>
</body>

</html>





