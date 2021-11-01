<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
// 	application.setAttribute("키", "값");
// 	String value1 = (String) application.getAttribute("키");
	
// 	session.setAttribute("키", "값");
// 	String value2 = (String) session.getAttribute("키");
	
// 	request.setAttribute("키", "값");
// 	String value3 = (String) request.getAttribute("키");
	
// 	request.getParameter("키"); // request.getAttribute("키"); 와 구분에 주의!
	
// 	pageContext.setAttribute("키", "값");
// 	String value4 = (String) pageContext.getAttribute("키");
%>
<%--
 □ EL(Expression Language) : JSP에서 데이터 출력 위주로 사용되는 표현언어 

 □ 톰캣(WAS)의 영역객체 4가지 (Map 컬렉션 처럼 "키"-"값" 쌍으로 데이터를 관리할 수 있는 객체)
   application : 웹프로그램 한개당 유지됨. EL 표현은 ${ applicationScope } 
   session : 사용자 한명당 유지됨. EL 표현은 ${ sessionScope }
   request : 사용자 요청 한개당 유지됨. EL 표현은 ${ requestScope }
   pageContext : JSP 페이지 한개 처리할동안 유지됨. EL 표현은 ${ pageScope }
   
 □ 영역(scope)객체의 수명주기
   applicationScope > sessionScope > requestScope > pageScope
   
 □ EL언어에서 영역(scope)객체 검색순서   ${ sessionScope.id }    ${ id }
   pageScope → requestScope → sessionScope → applicationScope
 --%>

  <!-- Navbar -->
  <nav class="nav-extended light-blue lighten-1">
	<div class="container right-align" style="font-size: 13px; height: 40px;">
	
	<c:choose>
		<c:when test="${ empty sessionScope.id }">
			<a href="/member/login">로그인</a>&nbsp; | &nbsp;
      		<a href="/member/join">회원가입</a>
		</c:when>
		<c:otherwise>
			<span>${ sessionScope.id }님</span>
			<a href="/member/logout">로그아웃</a>
		</c:otherwise>
	</c:choose>
    
    </div>
    
    <div class="nav-wrapper container">
      <a id="logo-container" href="/index" class="brand-logo">
      	<i class="material-icons">android</i>Logo
      </a>
      
      <ul class="right hide-on-med-and-down">
      
      <c:if test="${ not empty sessionScope.id }">
      	<!-- 로그인 상태일때 -->
        <!-- Dropdown Trigger -->
        <li>
        	<a href="#!" class="dropdown-trigger" data-target="dropdownMember">
        		내정보<i class="material-icons right">arrow_drop_down</i>
        	</a>
        </li>
      </c:if>
      
        <!-- 로그인 관계없이 보이는 메뉴 -->
        <li>
        	<a href="#!" class="dropdown-trigger" data-target="dropdownBoard">
        		게시판<i class="material-icons right">arrow_drop_down</i>
        	</a>
        </li>
        <li>
        	<a href="#!" class="dropdown-trigger" data-target="dropdownChat">
        		채팅<i class="material-icons right">arrow_drop_down</i>
        	</a>
        </li>
      </ul>
    </div>
  </nav>
  <!-- end of Navbar -->
  
  
  <!--  Navbar Dropdown Structure -->
  <!-- 내정보 서브메뉴 -->
  <ul id="dropdownMember" class="dropdown-content">
    <li><a href="/member/passwd">비밀번호 변경</a></li>
    <li><a href="/member/modify">내정보 수정</a></li>
    <li class="divider"></li>
    <li><a href="/member/remove">회원탈퇴</a></li>
  </ul>
  
  <!-- 게시판 서브메뉴 -->
  <ul id="dropdownBoard" class="dropdown-content">
    <li><a href="/board/list">게시판</a></li>
    <li class="divider"></li>
    <li><a href="#!">자료실</a></li>
  </ul>
  
  <!-- 채팅 서브메뉴 -->
  <ul id="dropdownChat" class="dropdown-content">
    <li><a href="#!">간단한 채팅</a></li>
    <li class="divider"></li>
    <li><a href="#!">채팅방 목록</a></li>
  </ul>
  <!--  end of Navbar Dropdown Structure -->
  

  
  
  