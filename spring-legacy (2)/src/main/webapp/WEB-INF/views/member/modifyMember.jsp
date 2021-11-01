<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 문자열 → Date 객체 변환 --%>
<fmt:parseDate value="${ member.birthday }" pattern="yyyyMMdd" var="dateBirthday"  />

<%-- Date 객체 → 문자열 변환 --%>
<fmt:formatDate value="${ pageScope.dateBirthday }" pattern="yyyy-MM-dd" var="strBirthday" />
    
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

              <h5>내정보 수정</h5>
              <div class="divider" style="margin: 30px 0;"></div>

              <form action="/member/modify" method="POST" id="frm">
                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">account_box</i>
                    <input id="id" type="text" class="validate" name="id" value="${ member.id }" readonly>
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

                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">person</i>
                    <input id="name" type="text" class="validate" name="name" value="${ member.name }">
                    <label for="name">이름</label>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field">
                    <i class="material-icons prefix">event</i>
                    <input type="date" id="birthday" name="birthday" value="${ pageScope.strBirthday }">
                    <label for="birthday">생년월일</label>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field">
                    <i class="material-icons prefix">wc</i>
                    <select name="gender">
                      <option value="" disabled  ${ empty member.gender ? 'selected' : '' }>성별을 선택하세요.</option>
                      <option value="M"  ${ member.gender eq 'M' ? 'selected' : '' }>남자</option>
                      <option value="F"  ${ member.gender eq 'F' ? 'selected' : '' }>여자</option>
                      <option value="N"  ${ member.gender eq 'N' ? 'selected' : '' }>선택 안함</option>
                    </select>
                    <label>성별</label>
                  </div>
                </div>

                <div class="row">
                  <div class="input-field col s12">
                    <i class="material-icons prefix">mail</i>
                    <input id="email" type="email" class="validate" name="email" value="${ member.email }">
                    <label for="email">본인 확인 이메일</label>
                  </div>
                </div>

                <p class="row center">
                  알림 이메일 수신 : &nbsp;&nbsp;
                  <label>
                    <input name="recvEmail" value="Y" type="radio"  ${ member.recvEmail eq 'Y' ? 'checked' : '' } />
                    <span>예</span>
                  </label>
                  &nbsp;&nbsp;
                  <label>
                    <input name="recvEmail" value="N" type="radio"  ${ member.recvEmail eq 'N' ? 'checked' : '' } />
                    <span>아니오</span>
                  </label>
                </p>

                <br>
                <div class="row center">
                  <button class="btn waves-effect waves-light" type="submit">수정
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
  
  </script>
</body>

</html>





