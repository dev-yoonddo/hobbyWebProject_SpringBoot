<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/errorPage.jsp"%>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width = device-width , initial-scale = 1, user-scalable = yes, maximum-scale = 1 , minimum-scale = 1">
<meta charset="UTF-8">
<title>TOGETHER</title>
<link rel="icon" href="/resources/image/logo.png">
<link rel="stylesheet" href="/resources/css/main.css?after">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/leaflet@1.6.0/dist/leaflet.css"/>
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=IBM+Plex+Sans+KR:wght@300;600&family=Jua&family=Merriweather:wght@700&family=Nanum+Gothic&family=Nanum+Gothic+Coding&family=Noto+Sans+KR:wght@400&family=Noto+Serif+KR:wght@200&display=swap" rel="stylesheet">
<script defer src="option/jquery/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://kit.fontawesome.com/f95555e5d8.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script type="text/javascript" src="/resources/js/script.js"></script>
</head>
<style>
section{
	height: 700px;
	display: flex;
	margin: 0;
	padding: 0;
	padding-top: 100px;
	margin-bottom: 150px;
	font-family: 'Nanum Gothic', monospace;
	font-weight: 500;
}
.board-container{
	width: 1000px;
	margin: 0 auto;
}
.write-table{
	width: 1000px;
}
select{
	width: 200px;
	height: 40px;
	margin-bottom: 10px;
	text-align: center;
	font-size: 15pt;
	font-weight: 500;
	color: #B3C1EE;

}
option{
	color: #B3C1EE;
	height: 40px;
}
textarea{
	width: 1000px;
	font-size: 13pt;
	font-family: 'Nanum Gothic', monospace;
	border: none;
	resize: none;
	padding: 10px;
}
.inquiry{
	padding-bottom: 100px;
}
#view-table{
	width: 1000px;
	height: 500px;
	border-collapse: collapse;
	border: 1px solid #C0C0C0;
	font-size: 12pt;
}
.btn-blue{
	position: relative;
	display: inline-block;
	width: 90px;
	height: 50px;
	background-color: transparent;
	border: none; 
	cursor: pointer;
	margin: 0;
	float: right;
}

.btn-blue span {         
	position: relative;
	display: inline-block;
	font-size: 12pt;
	font-weight: bold;
	letter-spacing: 2px;
	border-radius: 20px;
	width: 100%;
	padding: 10px;
	transition: 0.5s; 
	color: #ffffff;
	background-color: #7D95E5;
	border: 1px solid #7D95E5;
	font-family: 'Nanum Gothic Coding', monospace;
}

.btn-blue::before {
	background-color: #7D95E5;
}

.btn-blue span:hover {
	color: #7D95E5;
	background-color: #ffffff
}
#write-bottom{
	display: flex;
	height: 50px;
	padding: 20px;
}
#file{
	display: flex;
	width: 65%;
	border-radius: 50px;
	background-color: #CCE5FF;
	padding: 10px 25px;
	align-items: center;
}
#file > input{
	font-size: 11pt;
}
#file > #txt{
	width: 70px;
}
#filename{
/*파일이름이 길면 ...으로 생략*/
	overflow:hidden;
	white-space:nowrap;
	text-overflow:ellipsis;
	max-width:300px;
}
#fileupload{
	width: 10px;
	visibility: hidden;
}
#click{
	height: 25px;
	background-color: #7D95E5;
	color: white;
	border-radius: 5px;
	display: flex;
	align-items: center;
	justify-content: center;
}
#btn{
	width: 35%;
}
@media screen and (max-width:900px) {
	.board-container , .write-table , form, textarea, table, tbody, tr, th, td{
		max-width: 650px;
	}
	#filename{
		max-width:200px;
		
	}
}

@media screen and (max-width:650px) {
	.board-container , .write-table , form, textarea, table, tbody, tr, th, td{
		max-width: 350px;
	}
	thead{
		display: none;
	}
	textarea{
		padding: 0;
		float: left;
	}
	td{
		padding: 10px;
	}
	.btn-blue{
		width: 100px;
	}
	#write-bottom{
		display: inline;
		padding-top: 10px;
	}
	#file{
		width: 330px;
	}
	#filename{
		max-width:150px;
	}
	#click{
		width: 80px;
	}
	#btn{
		width: 350px;
		float:right;
		margin-top: 10px;
	}
}
</style>
<body>
<%
String userID = null;
if(session.getAttribute("userID")!=null){
	userID=(String)session.getAttribute("userID");
}
%>
<!-- header start-->
<header id="header">
<jsp:include page="/resources/header/header.jsp"/>
</header>
	<!-- header end-->
	
<section>
	
	<div class="board-container">
	<h3 style="font-weight: bold; color: #646464;">
	<%=userID%> 님 안녕하세요
	</h3>
	<br>
	<form action="/community/newpost/${vo.boardID}" enctype="multipart/form-data" method="POST">
		<div class="right-row">
		    <!--form method에 get,post만 적용되기 때문에 input을 따로 삽입한다.-->
		    <input type="hidden" name="_method" value="PUT"/>
			<input type="hidden" name="boardID" value="${vo.boardID}"/>
			<input type="hidden" name="userID" value="<%=userID%>">
		
				<div class="category-sel" style="display: flex;">
				<select name="boardCategory">
					<option value="0">CATEGORY</option>
					<option value="SPORTS" >SPORTS</option>
					<option value="LEISURE" >LEISURE</option>
					<option value="MUSIC" >MUSIC</option>
					<option value="OTHER" >OTHER</option>
				</select>
				
				</div>
				<table class="write-table" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr translate="yes">
							<th style="background-color: #DBE2F7; text-align: center; color: #464646; height: 40px;">글 작성</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><textarea placeholder="제목을 입력하세요" name="boardTitle" maxlength="50">${vo.boardTitle}</textarea></td>
						</tr>
						<tr>
							<td><textarea placeholder="내용을 입력하세요" name="boardContent" maxlength="2048" style="height: 350px; ">${vo.boardContent}</textarea></td>
						</tr>
					</tbody>
				</table>
				<div id="write-bottom">
					<c:choose>
					<c:when test="${not empty vo.filename}">
					<div id="file">
						<span id="beforeFile">기존 파일 : ${vo.filename}</span>
						<span id="afterFile">┃ 파일첨부 :&nbsp;&nbsp;
							<label for="fileupload" id="click" class="btn-blue">click !</label>&nbsp;&nbsp;
							<div id="filename"></div>
							<input type="file" id="fileupload" name="file" onchange="filename(this)" >
						</span>
					</div>
					</c:when>
					<c:otherwise>
						<div id="file2">파일첨부 :&nbsp;&nbsp;
							<label for="fileupload" id="click" class="btn-blue">click !</label>&nbsp;&nbsp;
							<div id="filename"></div>
							<input type="file" id="fileupload" name="file" onchange="filename(this)" >
						</div>
					</c:otherwise>
					</c:choose>
					<div id="btn">
						<button type="submit" class="btn-blue" value="글쓰기">
						<span>작성하기</span>
						</button>
					</div>
				</div>
		</div>
			</form>		
	</div>
</section>
<script type="text/javascript">
//카테고리가 글 수정시 선택되어있도록 한다.
var bdcategory = "${vo.boardCategory}";

let selectBox = document.getElementsByName('boardCategory')[0];

for (let i = 0; i < selectBox.options.length; i++) {
  if ((selectBox.options[i].value) === bdcategory) {
    selectBox.options[i].setAttribute('selected', 'selected');
    break;
  }
}

function filename(input){
	var file = input.files[0];	//선택된 파일 가져오기
    //미리 만들어 놓은 div에 text(파일 이름) 추가
    var name = document.getElementById('filename');
    name.textContent = file.name;
}
</script>
	<!-- 부트스트랩 참조 영역 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>