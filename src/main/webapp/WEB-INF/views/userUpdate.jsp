<%@page import="javax.security.auth.callback.ConfirmationCallback"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/errorPage.jsp"%>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width = device-width , initial-scale = 1, user-scalable = no, maximum-scale = 1 , minimum-scale = 1">
<meta charset="UTF-8">
<title>TOGETHER</title>
<link rel="icon" href="/resources/image/logo.png">
<link rel="stylesheet" href="/resources/css/main.css?after">
<link rel="stylesheet" href="/resources/css/member.css?after">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/leaflet@1.6.0/dist/leaflet.css"/>
<link href="https://fonts.googleapis.com/css?family=Teko:300,400,500,600,700&display=swap" rel="stylesheet">
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet">
<script defer src="option/jquery/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/resources/js/script.js"></script>
<script type="text/javascript" src="/resources/js/userdata.js"></script>
<script type="text/javascript" src="/resources/js/checkPW.js"></script>
<script src="https://kit.fontawesome.com/f95555e5d8.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<style>
header{
	top: 0;
}
section{
	top: 0;
	height: auto;
	margin-bottom: 200px;
}
.menu-bar{
	width: auto;
	height: auto;
	top: 150px;
	font-size: 15pt;
}
.sidemenu{
	width: 200px;
  	height: 50px;
	left: -150px;
	background-color: #E0EBFF;
	transition: left 1s;
	position: fixed;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	z-index: 500;
}
#menu1{
	top: 150px;
}
#menu2{
  	top: 210px;
}
#menu3{
  	top: 270px;
}
#menu1:hover , #menu2:hover, #menu3:hover{
 	left: 0;
  	transition: left 1s;
}

#menu1 > ul , #menu2 > ul, #menu3 > ul{
	position: relative;
  	float: right;
  	list-style-type: none;
  	display: flex;
	align-items: center;
}

#menu1 > li , #menu2 > li, #menu3 > li{
	width: auto;
	height: auto;
	margin: 0 auto;
	float: right;
}
.i{
	font-size: 20pt;
	margin-left: 20px;
	margin-top: 5px;

}
#more-btn{
	cursor: pointer;
}
td{
	table-layout: fixed;
	height: 20px;
	border-bottom: solid 1px #C0C0C0;
	text-align: left;
}
#click-view{
	overflow:hidden;
	white-space:nowrap;
	text-overflow:ellipsis;
	max-width:100px;
}
#click-view:hover{
	text-decoration: underline;
}
.btn-blue{
	width: 45px;
	height: 20px;
	font-size: 13pt;
	margin: 0;
	padding: 0;
	margin-right: 50px;
	margin-top: 12px; 
	float: left;
}
.btn-blue span{
	height: 15px;
	float: center;
	padding: 5px;
}
h3{
	width: 150px;
	float: left;
}
.userDataBoard{
	min-height: 45px;
	margin-bottom: 20px;
}
.view-head{
	height: 20px;
	border-bottom: solid 2px #C0C0C0;
}

table{
	font-size: 10pt; 
	color: black; 
	width: 450px; 
	text-align: left; 
}
tr{
	align-items: center;
}
.data-tb{
	font-size: 10pt; 
	color: black; 
	width: 450px; 
	text-align: left;
}
#more-btn ,#more-btn-2, #more-btn-3, #more-btn-4, #more-btn-5, #more-btn-6{
	float: right;
	margin-right: 20px;
	font-size: 11pt;
	font-weight: bold;
}

/* select box */
.select , .select1{
	position: relative;
	width: 500px;
}
.select .option-list , .select1 .option-list1{
	position: absolute;
	top: 100%;
	left: 0;
	width: 100%;
	overflow: hidden;
	max-height: 0;
	background-color: white;
}

.select.active .option-list , .select1.active .option-list1{
	max-height: none;
	border: solid 3px #E0EBFF;
}
#select-sec , #select-sec1{
	width: 300px;
	height: 45px;
	font-size: 11pt;
	font-weight: bold;
	color: #6e6e6e;
	font-family: 'Nanum Gothic', sans-serif;
	display: flex;
}
#select-sec .select , #select-sec1 .select1{
	border-style: solid;
	border-color: #E0EBFF;
	color: #6e6e6e;
	border-radius: 15px;
	padding: 10px;
	cursor: pointer;
}

#select-sec .select .text , #select-sec1 .select1 .text1{
	font-size: 12pt;
	font-weight: bold;
	color: #6e6e6e;
	display: flex;
}
.option , .option1{
	display: flex;
}
span{
	margin: 0 auto;
}

#select-sec .select .option-list , #select-sec1 .select1 .option-list1 {
  	list-style: none;
 	 padding: 0;
  	border-radius: 15px;
}
#select-sec .select .option-list .option , #select-sec1 .select1 .option-list1 .option1{
  	padding: 15px;
}
#select-sec .select .option-list .option:hover , #select-sec1 .select1 .option-list1 .option1:hover{
	border-radius: 15px;
	background-color: #E0EBFF;
}
#dl-btn, #dl-btn2{
	border: 0;
	outline: 0;
	width: 50px;
	height: 45px;
	border-radius: 15px;
	border-color: #86A5FF;
	background-color: #E0EBFF;
	margin-left: 15px;
	cursor: pointer;
}
#dl{
	font-size: 12pt;
	font-weight: bold;
	color: 6e6e6e;
	font-family: 'Nanum Gothic', sans-serif;
}
.none-list{
	text-align: center; 
	padding: 10px; 
}
#delete-sec , #delete-sec1{
	height: 10%;
	bottom: 0;
	position: absolute;
}

#event{
	width: 70px;
	height: 70px;
	float: right;
	position: fixed;
	right: 50px;
	z-index: 500;
	text-align: center;
	font-weight: bold;
	display: flex;
	padding: 5px;
	margin: 0 auto;
	align-items: center;
	justify-content: center;
	border-radius: 100%;
	background-color: #4646CD;
	color: white;
}

/*화면 축소 시*/
@media screen and (max-width:650px) {
	#userInfo{
		width: 400px;
		padding: 20px;
	}
	#userSet{
		width: 400px;
		padding: 20px;
	
	}
	#userMsg{
		width: 400px;
		padding: 20px;
	}
	table , .data-tb{
		width: 350px;
	}
	.sidemenu{
		height: 35px;
	}
	#menu1{
		top: 80px;
	}
	#menu2{
	  	top: 120px;
	}
	#menu3{
		top: 160px;
	}
 
}
</style>
<body>
<%
	PrintWriter script = response.getWriter();
	String userID=null;
	if(session.getAttribute("userID")!=null){
		userID=(String)session.getAttribute("userID");
	}
	if(userID == null){
		script.println("<script>");
		script.println("alert('로그인이 필요합니다.')");
		script.println("location.href='/login'");
		script.println("</script>");
	}
	
%>

<header>
<div id="header" class="de-active"> <!-- userUpdate페이지는 header의 구성이 다르기 때문에 따로 작성한다. -->
	<nav class="navbar">
		<nav class="navbar_left">
			<div class="navbar_logo">
				<a href="mainPage" id="mainlogo" >TOGETHER</a>
			</div>
			<ul class="navbar_menu" style="float: left;">
				<li><a href="community" class ="menu">COMMUNITY</a></li>
				<% 
					if(userID == null){
				%>
				<li><a id="go-group-1" class="menu">GROUP</a></li>
				<li><a id="go-spot-1" class ="menu">SPOT</a></li>
				<%
					} else { 
				%>
				<li><a id="go-group-2" class="menu" onclick="location.href='groupPage'">GROUP</a></li>
				<li><a id="go-spot-2" class ="menu" onclick="location.href='spot'">SPOT</a></li>
				<%
					}
				%>
			</ul>
		</nav>
			<ul class="navbar_login" >
				<%
					if(userID == null){
				%>	
				<li><a href="/login">LOGIN</a></li>
				<li><a href="/join">JOIN</a></li>
				<%
					}else{
				%>				
				<li><a onclick="qna()" style="font-size: 15pt; cursor: pointer;"><i class="fa-solid fa-circle-question" style=""></i></a></li>
				<li><a href="/logout">LOGOUT</a></li>
				<%
					}
				%>
			</ul>
			<a onclick="toggleAct()" class="navbar_toggleBtn">
				<i class="fa-solid fa-bars"></i>
			</a>
	</nav>
</div>
</header>
<section>
	<!-- 사이드바
	<div class="menu-bar">
		<div id="menu1" class="sidemenu">
			<ul>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;정보수정</li>
				<li class="i"><i class="fa-solid fa-angles-right"></i></li>
			</ul>
		</div>
		<div id="menu2" class="sidemenu">
			<ul>
				<li>데이터관리</li>
				<li class="i"><i class="fa-solid fa-angles-right"></i></li>
			</ul>
		</div>
		<div id="menu3" class="sidemenu">
			<ul>
				<li>메시지관리</li>
				<li class="i"><i class="fa-solid fa-angles-right"></i></li>
			</ul>
		</div>
	</div> 
	-->
	<!-- 정보 수정하기 -->
	<div id="userInfo">
	 	<div>
	        <h2>정보 수정하기</h2>
	        <form method="post" action="/user/update" id="user-update" onsubmit="return userDataCheck(this)">
			<input type="text" value="${vo.userID}" name="userID" id="userID" maxlength="20" readOnly="true">
			<input type="text" value="${vo.userName}" name="userName" id="userName"maxlength="20">
			<input type="text" value="${vo.userEmail}"  name="userEmail" id="userEmail"maxlength="20" onkeyup="emailCheck('${emailList}')">
			<input type="text" value="${vo.userBirth}" name="userBirth" id="userBirth" maxlength="20" >
			<input type="text" value="${vo.userPhone}" name="userPhone" id="userPhone" maxlength="20">
			<input type="password" name="userPassword" id="userPassword" maxlength="20" placeholder="비밀번호 입력" onkeyup="passwordCheck2()">
	        <input type="password" name="userPassword1" id="userPassword1" placeholder="비밀번호 확인" onkeyup="passwordCheck2()">
	        <input type="hidden" value="${vo.userPassword}" name="password" id="password">
	            <div id="check">
					<h5 id="checkMessage"></h5>
				</div>
	        <input type="submit" value="update">
	        </form>
	        <button type="button" id="user-delete" onclick="deleteUser(userID)">회원 탈퇴하기</button>
	    </div>
	</div>
	
</section>
<script>

/*check email
window.addEventListener('DOMContentLoaded', function emailChecked(result){
	if(result == false){
		var emailck = confirm('이메일 인증이 필요합니다. 인증 메일을 발송 하시겠습니까?');
		if(emailck == true){
			location.href = "emailSendAction.jsp";
		}else{
			break;
		}
	}
});*/

</script>

<script type="text/javascript" src="js/checkPW.js"></script>
</body>
</html>