<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		String thisPage = request.getParameter("thisPage");
	%>
	<div id="header" class="de-active">
		<nav class="navbar">
			<nav class="navbar_left">
				<div class="navbar_logo">
					<a href="/" id="mainlogo">TOGETHER</a>
				</div>
				<ul class="navbar_menu" style="float: left;">
					<li><a href="/community" class="menu">COMMUNITY</a></li>

					<li><a onclick="check()" class="menu" >SPOT</a></li>
				</ul>
			</nav>
			<ul class="navbar_login">
				<%
					if (userID == null) {
				%>
				<li><a href="/login">LOGIN</a></li>
				<li><a href="/join">JOIN</a></li>
				<%
					} else {
				%>
				<li><a href="/user/update"><i class="fa-solid fa-gear"
						id="updateicon"></i></a></li>
				<li><a href="/logout">LOGOUT</a></li>
				<%
					}
				%>
			</ul>
			<a class="navbar_toggleBtn" id="toggleicon"> <i
				class="fa-solid fa-bars"></i>
			</a> <input type="checkbox" id="toggleDeActive" hidden="hidden">
		</nav>
	</div>
<script>
function check(){
    location.href='/check-login';
}
//let msg = "${msg}";
//if (msg != "") {
//    console.log(msg);
//   let ok = confirm(msg);
//   if(ok == true){
//    location.href='login';
//    }else{
//    history.back();
//    }
    //window.location.replace("login");
//}
</script>
</body>
</html>