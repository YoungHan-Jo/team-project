<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header id="header" class="fixed-top">
	<div class="container d-flex align-items-center">

		<h1 class="logo me-auto">
			<a href="/index">LOGO</a>
		</h1>

        <!-- navbar -->
		<nav id="navbar" class="navbar order-last order-lg-0">
			<ul>
				<li><a class="nav-link scrollto" href="/quiz/list">QUIZ</a></li>
				<li><a class="nav-link scrollto" href="/board/list">BOARD</a></li>
				<c:if test="${ not empty sessionScope.id }">
					<li><a class="nav-link scrollto" href="/admin/logmsmain">Admin</a></li>
				</c:if>
				
			</ul>
			<i class="bi bi-list mobile-nav-toggle"></i>
		</nav>
		<!-- End navbar -->
		
        <c:choose>
            <c:when test="${empty sessionScope.id}">
                <a href="/member/account" class="appointment-btn scrollto"><span class="d-none d-md-inline">LOGIN</span></a>
            </c:when>
            <c:otherwise>
                <c:set var="fileCallPath" value="${ profileImg.uploadpath }/${ profileImg.memberId }/s_${ profileImg.uuid }_${ profileImg.filename }" />
                <div class="box mx-3">
                    <c:choose>
                        <c:when test="${not empty profileImg }">
                            <img class="profile" src="/display?fileName=${fileCallPath}" style="width:50px; height:50px; border-radius: 100px;">
                        </c:when>
                        <c:otherwise>
                            <img class="profile" src="/resources/img/unknown.png" style="width:50px; height:50px; border-radius: 100px;" />
                        </c:otherwise>
                    </c:choose>
                    <a href="/member/info" style="color: inherit; position:relative; bottom: 20px;">${sessionScope.id}님</a>
                </div>
            </c:otherwise>
        </c:choose>

	</div>
</header>