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

              <h5>회원가입</h5>
              <div class="divider" style="margin: 30px 0;"></div>

              <form action="/member/join" method="POST" id="frm" name="frm">
                <div class="row">
                  <div class="input-field col s12 m8 l9">
                    <i class="material-icons prefix">account_box</i>
                    <input id="id" type="text" name="id" data-length="20">
                    <label for="id">아이디</label>
                    <span class="helper-text" data-error="" data-success=""></span>
                  </div>
                  <div class="col s12 m4 l3">
                  	<button type="button" class="btn-small waves-effect waves-light" id="btnIdDupChk">ID 중복확인</button>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">lock</i>
                    <input id="passwd" type="password" class="validate" name="passwd" data-length="10">
                    <label for="passwd">비밀번호</label>
                    <span class="helper-text" data-error="비밀번호는 10글자까지만 가능합니다." data-success="OK!">Helper text</span>
                  </div>

                  <div class="input-field col s12">
                    <i class="material-icons prefix">check</i>
                    <input id="passwd2" type="password" data-length="10">
                    <label for="passwd2">비밀번호 재확인</label>
                    <span class="helper-text"></span>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">person</i>
                    <input id="name" type="text" class="validate" name="name">
                    <label for="name">이름</label>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field">
                    <i class="material-icons prefix">event</i>
                    <input type="date" id="birthday" name="birthday">
                    <label for="birthday">생년월일</label>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field">
                    <i class="material-icons prefix">wc</i>
                    <select name="gender">
                      <option value="" disabled selected>성별을 선택하세요.</option>
                      <option value="M">남자</option>
                      <option value="F">여자</option>
                      <option value="N">선택 안함</option>
                    </select>
                    <label>성별</label>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">mail</i>
                    <input id="email" type="email" class="validate" name="email">
                    <label for="email">본인 확인 이메일</label>
                  </div>
                </div>

                <p class="row center">
                  알림 이메일 수신 : &nbsp;&nbsp;
                  <label>
                    <input name="recvEmail" value="Y" type="radio" checked />
                    <span>예</span>
                  </label>
                  &nbsp;&nbsp;
                  <label>
                    <input name="recvEmail" value="N" type="radio" />
                    <span>아니오</span>
                  </label>
                </p>

                <br>
                <div class="row center">
                  <button class="btn waves-effect waves-light" type="submit">회원가입
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
	// 입력 문자개수 보이기 기능 활성화
	$('input#id, input#passwd, input#passwd2').characterCounter();
	
	// 아이디 중복확인 버튼 클릭 이벤트 연결
	$('button#btnIdDupChk').on('click', function () {
		// 사용자가 입력한 아이디 문자열 가져오기
		var id = $('input#id').val();
		console.log('id : ' + id);
		
		// id가 공백이면 '아이디 입력하세요' 포커스 주기
		if (id == '') { // id.length == 0
			alert('아이디 입력하세요');
			$('input#id').focus();
			return;
		}
		
		// id 중복확인 자식창 열기  window.open();
		open('/member/joinIdDupChk?id=' + id, 'idDupChk', 'width=500,height=400');
	});
	
	
	// ajax로 회원가입 아이디 중복여부 확인하기
	$('input#id').on('focusout', function () {
		
		var id = $(this).val();
		if (id.length == 0) {
			return;
		}
		
		var $inputId = $(this);
		var $span = $(this).next().next();
		
		// ajax 함수 호출
		$.ajax({
			url: '/api/members/' + id,
			method: 'GET',
			success: function (data) {
				console.log(data);
				console.log(typeof data);
				
				if (data.count == 0) {
					$span.html('사용가능한 아이디 입니다').css('color', 'green');
					$inputId.removeClass('invalid').addClass('valid');
				} else { // data.count == 1
					$span.html('이미 사용중인 아이디 입니다').css('color', 'red');
					$inputId.removeClass('valid').addClass('invalid');
				}
			} // success
		});
	});
	
	
	// #passwd2 요소에 포커스 아웃 이벤트 연결
	$('input#passwd2').on('focusout', function () {
		var passwd = $('input#passwd').val();
		var passwd2 = $(this).val();
		
		var $span = $(this).closest('div.input-field').find('span.helper-text');
		
		if (passwd == passwd2) {
			$span.html('비밀번호 일치함').css('color', 'green');
			$(this).removeClass('invalid').addClass('valid');
		} else {
			$span.html('비밀번호 불일치함').css('color', 'red');
			$(this).removeClass('valid').addClass('invalid');
		}
	});
  
  
    $('form#frm').on('submit', function (event) {
    	// 아이디 입력값 글자개수 검증
    	var id = $('#id').val();
    	if (id.length < 3 || id.length > 13) {
    		event.preventDefault(); // form태그의 기본동작 막기
    		
    		alert('아이디는 3글자 이상 13글자 이하만 가능합니다.');
    		$('#id').select();
    		return;
    	}
    	
    });
  </script>
</body>

</html>





