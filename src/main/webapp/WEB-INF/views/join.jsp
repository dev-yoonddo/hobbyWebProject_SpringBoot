<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.toogether.vo.UserVO"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.security.SecureRandom"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width = device-width , initial-scale = 1, user-scalable = no, maximum-scale = 1 , minimum-scale = 1">
<meta charset="UTF-8">
<title>Join</title>
<link rel="icon" href="image/logo.png">
<link rel="stylesheet" href="/resources/css/main.css?after">
<link rel="stylesheet" href="/resources/css/member.css?after">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/leaflet@1.6.0/dist/leaflet.css"/>
<link href="https://fonts.googleapis.com/css?family=Teko:300,400,500,600,700&display=swap" rel="stylesheet">
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet">
<script src="option/jquery/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/resources/js/script.js"></script>

<script src="https://kit.fontawesome.com/f95555e5d8.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

</head>
<style>

</style>
<body>

<header id="header">
<jsp:include page="/resources/header/header.jsp"/>
</header>
<section>
 <div class="join-wrapper">
 	<div>
 	<!-- 서버에서 전달받은 공개키를 hidden에 설정한다. 
	<input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}" />
	<input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}" />
	 -->
        <h2>회원가입</h2>
       	<c:if test="${joinError == 'fail'}">
        ${vo}
        </c:if>
        <form method="post" action="/join" id="join-form" role="form" onsubmit="return userDataCheck(this)">
            <div id="input-id">
	         	<input type="text" name="userID" class="join-input" id="userID"  placeholder="아이디 입력">	            
	            <div class="btn-blue" id="checkID" onclick="checkID(userID)"><span>중복체크</span></div>
	            <div id="checkOK"><i class="fa-solid fa-square-check fa-3x"></i></div>
            </div>
            <input type="text" name="userName" class="join-input" id="userName"  placeholder="이름 입력">
            <input type="text" name="userEmail" class="join-input" id="userEmail" placeholder="이메일 입력" onkeyup="emailCheck('${emailList}')">
            <input type="text" name="userBirth" class="join-input" id="userBirth" placeholder="생년월일 입력">
            <input type="text" name="userPhone" class="join-input" id="userPhone" placeholder="핸드폰번호 입력">
            <input type="password" name="userPassword" class="join-input" id="userPassword" placeholder="비밀번호 입력" onkeyup="passwordCheck2()">
            <input type="password" name="userPassword1" class="join-input" id="userPassword1" placeholder="비밀번호 확인" onkeyup="passwordCheck2()">
            <div id="check">
				<h5 id="checkMessage">
					<c:if test="${joinError == 'fail'}">
				    	<span id="error-text">이미 존재하는 아이디입니다.</span>
				    </c:if>
				</h5>
			</div>
            <input type="submit" class="join-input" value="join">
        </form>
       
        <!-- 
        <form id="frm" name="frm" method="post" action="/login">
			<input type="hidden" name="securedUser_Id" id="securedUser_Id"value="" />
			<input type="hidden" name="securedUser_Pwd" id="securedUser_Pwd" value="" />
		</form>
		 -->
    </div>

</div>
</section>
<script type="text/javascript" src="/resources/js/checkPW.js"></script>
</body>
</html>