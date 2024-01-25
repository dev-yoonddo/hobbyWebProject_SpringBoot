<%@page import="java.util.stream.Stream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/error/errorPage.jsp"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width = device-width , initial-scale = 1, user-scalable =yes , maximum-scale = 1 , minimum-scale = 1">
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
<script type="text/javascript" src="/resources/js/script.js"></script>
</head>
<style>
section{
	height: auto;
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
.inquiry{
	padding-bottom: 100px;
}
#view-table{
	width: 1000px;
	height: 600px;
	border-collapse: collapse;
	border: 1px solid #C0C0C0;
	font-size: 12pt;
	display: table;
	table-layout: fixed;
}
table caption{
	font-size:0;
	text-indent:-9999px;
}
#tr2{
	height: 450px;
}
#tr2 >td , #tr3 > td{
	padding-top: 50px;
}
.td{
	width: 15%;
	text-align: center;
	font-size: 13pt;
	margin-left: 10px;
}
.td span{	
	padding: 10px 20px;
	border-radius: 20px;
	background-color: #CCE5FF;
}
#view-title{
	font-weight: bold;
	font-size: 25pt;
	color: #646464;
	font-family: 'Noto Sans KR', sans-serif;
	animation: fadeInLeft 2s;
}

@keyframes fadeInLeft {
    0% {
        opacity: 0;
        transform: translate3d(-100%, 0, 0);
    }
    to {
        opacity: 1;
        transform: translateZ(0);
    }

}

#tb-top{
	display: flex;
	justify-content: center;
}
#tb-top-1{
	width: 50%;
	height: 40px;
	padding: 0;
	margin: 0;
	display: flex;
	float: left;
}
#tb-top-2{
	width: 50%;
	height: 40px;
	padding-bottom: 20px;
	margin: 0;
	float: right;
}
#user-item{
	margin: 0;
	padding: 10px;
	justify-content: center;	
}
#count-item{
	border-radius: 20px;
	background-color: #E0E0E0;
	display: flex;
	float: right;
}
#count{
	display: flex;
	justify-content: center;
	padding: 8px 60px;
}
#count span{
	color: #282557;
	font-size:17pt;
	height: 25px;
	margin: 0 auto;
	padding-right: 10px;
}
.fa-heart{
	cursor: pointer;
}
#cmt-btn{
	width: 40px;
	height: 20px;
	padding: 0;
	margin: 0px 15px;
}
#cmt-btn span{
	font-size: 10pt;
	padding: 6px;
	color: #ffffff;
	background-color: #323232;
	border: 1px solid #323232;
}
#cmt-btn::before {
  	background-color: #323232;
}

#cmt-btn span:hover {
	color: #323232;
	background-color: #ffffff
}
#row2{
	display: flex;
}
#fileList{
	width: 45%;
	height: 30px;
	display: flex; 
	justify-content: center; 
	align-items: center; 
	padding: 10px;
	margin: 20px;
	border-radius: 50px;
	background-color: #CCE5FF;
}
#btnList{
	width: 55%;
	float: right;
}#file_form{
	display: flex;
}
#filename{
/*파일이름이 길면 ...으로 생략*/
	overflow:hidden;
	white-space:nowrap;
	text-overflow:ellipsis;
	max-width:180px;
}
#filename:hover{
	font-weight: bold;
	color: #606060;
}
#view-file-1 , #view-file-2{
	position: relative;
}
#view-file-2{
	display: none;
}
#close-file{
	position: absolute;
    right: 10%;
    background-color: #646464;
    color: white;
    font-weight: bold;
    font-size: 12pt;
    width: 25px;
    height: 25px;
    border-radius: 50%;
    border-width: 0;
    display: block;
    cursor: pointer;
    outline-style: none;
}
#close-file:hover{
	background-color: white;
    color: #646464;
}
.td-content{
	padding:20px;
	padding-left: 0;
}
@media screen and (max-width:650px) {
	.board-container{
		max-width:  400px;
	}
	.inquiry{
		max-width:  400px;
	}
	#view-table , thead , tbody , .tr{
		max-width:  400px;
	}
	#view-table{
		table-layout:fixed; border-spacing:0;
	}
	#tr1 , #tr2{
		max-width:  400px;
	}
	#user-item{
		width: 150px;
		font-size: 11pt;
		margin-bottom: 10px;
	}
	#cmt-write{
		max-width: 400px;
		height: 200px;
	}
	#cmt-line{
		max-width: 400px;
	}
	#count{
		padding: 8px 20px;
	}
	#count span{
		font-size:15pt;
	}
	.btn-blue{
		margin: 7px;
	}
	.btn-blue span{
		font-size: 11pt;
	}
	.cmt-table input{
		max-width: 400px;
	}
	.cmt-view , .cmt-list , .cmt-table{
		max-width: 400px;		
	}
	#list , #update, #btn-del, #cmt-cpl{
		width: 60px;
	}
	#row2{
		display: inline;
	}
	#fileList{
		width: 380px;
		padding: 10px;
		margin: 0;
		float:inherit;
	}
	#btnList{
		width: 400px;
		float:inherit;
	}
	.td{
		width: 25%;
	}
	#tr1{
		height: 100px;
	}
	#tr2{
		height: auto; 
	}
	#close-file{
		top: 0;
	}
	#view-file-1{
		display: none;
	}
	#view-file-2{
		width: 350px;
		height: 300px;
		display: block;
		padding: 15px;
	}
	img{
		width: auto;
		height: auto;
		max-width: 380px;
		max-height: 300px;
		float: right;
	}
}
</style>
<body>
<%
    PrintWriter script = response.getWriter();
	//userID 가져오기
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String)session.getAttribute("userID");
	}
	/*/cmtID 가져오기
	int cmtID = 0;
	if(request.getParameter("cmtID")!=null)
		cmtID = Integer.parseInt(request.getParameter("cmtID"));
	//boardID 가져오기
	int boardID = 0;
	if(request.getParameter("boardID") != null){
		boardID = Integer.parseInt(request.getParameter("boardID"));
	}else{
		boardID = Integer.parseInt((String)(request.getAttribute("boardID")));
	}
	//글이 유효하다면 1이상의 숫자가 반환되기 때문에 boardID == 0일때  글이 유효하지 않다는 알림창 띄우기
	if(boardID == 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글입니다.')");
		script.println("history.back()");
		script.println("</script>");
	}
	
	//해당 글이 공지사항인지 구분한다.
	boolean notice = false;
	if((board.getBoardCategory()).equals("NOTICE")){
		notice = true;
	}
	*/
%>
<!-- 삭제된 게시글이면 이전 페이지로 돌아가기-->
<c:if test="${not empty avlb and avlb == '0'}">
<script>
    alert('삭제된 게시글입니다');
    history.back();
</script>
</c:if>

<!-- header -->
<header id="header">
	<jsp:include page="/resources/header/header.jsp"/>
</header>
<!-- header -->

<!-- section -->
<section>
<c:set var="userID" value="<%=userID%>"/>
	<div class="board-container" id="container">
		<div class="inquiry">
			<div class="row"><br>
				<c:set var="vo" value="${vo}"/>
				<c:set var="category" value="${(vo.boardCategory).toLowerCase()}"/>
				<c:set var="date" value="${fn:substring(vo.boardDate,0,16)}"/>

				<!-- 공지사항은 카테고리가 아닌 제목을 출력한다. -->
				<c:choose>
					<c:when test="${vo.boardCategory == 'NOTICE'}">
						<a id="view-title">${vo.boardTitle}</a><br><br>
					</c:when>
					<c:otherwise>
						<a id="view-title">${vo.boardCategory}</a><br><br>
					</c:otherwise>
				</c:choose>

				<div id="tb-top">
					<div id="tb-top-1">
						<div id="user-item">
						<span>작성자 ${vo.userID}</span>
						<span>&nbsp;&nbsp;|&nbsp;&nbsp; ${date}</span>
						</div>
					</div>
					
					<div id="tb-top-2">
						<div id="count-item">
							<div id="count">
								<span id="heart-count">
								<c:choose>
									<c:when test="${exist == 'Y' }">								
							    	<i id="heart2" class="fa-solid fa-heart" onclick="heartAction('exist')"></i>&nbsp;${vo.heartCount}
							    	</c:when>
							    	<c:otherwise>
							    	<i id="heart1" class="fa-regular fa-heart" onclick="heartAction('none')"></i>&nbsp;${vo.heartCount}
							    	</c:otherwise>
								</c:choose>
								</span>&nbsp;&nbsp;
								<span><i class="fa-solid fa-eye"></i>&nbsp;${vo.viewCount}</span>
							</div>
						</div>
					</div>
				</div>
				<table id="view-table">
				<c:choose>
					<c:when test="${vo.boardCategory == 'NOTICE'}">
						<tr class="tr" id="tr3" height="75%" valign="top">
							<td style="padding: 30px; word-break: break-all;" class="td-content">${vo.boardContent}</td>
							<!-- 파일이 이미지일때만 서버에 업로드 된 파일 노출하기 -->
							
							<c:if test="${not empty vo.filename and vo.filename.endsWith('.jpg') or vo.filename.endsWith('.JPG') or vo.filename.endsWith('.png') or vo.filename.endsWith('.PNG')}">
								<td class="view-file" id="view-file-1" width="35%">
									<button id="close-file" onclick="closeFile()">X</button>
									<img src="/upload/${vo.fileRealname}" width="300px">
								</td>
							</c:if>
						</tr>
					</c:when>
					<c:otherwise>
						<tr class="tr" id="tr1" height="150px" style="border-bottom: 1px solid #C0C0C0;">
								<td class="td"><span>제목</span></td>
								<td>${vo.boardTitle}</td>
						</tr>
						<tr class="tr" id="tr2" valign="top">
							<td class="td" ><span>내용</span></td>
							<!-- 특수문자 처리 -->
							<td class="td-content" style="word-break: break-all; margin: 10px;">${vo.boardContent}</td>
							<!-- 파일이 이미지일때만 서버에 업로드 된 파일 노출하기 -->
							<c:if test="${not empty vo.filename and vo.filename.endsWith('.jpg') or vo.filename.endsWith('.JPG') or vo.filename.endsWith('.png') or vo.filename.endsWith('.PNG')}">
								<td class="view-file" id="view-file-1" width="35%">
									<button id="close-file" onclick="closeFile()">X</button>
									<img src="/upload/${vo.fileRealname}" width="300px"/>
								</td>
							</c:if>
						</tr>
					</c:otherwise>
				</c:choose>
				<!-- 화면이 축소되면 테이블 구성 변경 -->
				<c:if test="${not empty vo.filename and vo.filename.endsWith('.jpg') or vo.filename.endsWith('.JPG') or vo.filename.endsWith('.png') or vo.filename.endsWith('.PNG')}">
					<tr class="tr" id="view-file-2" height="200px">
						<td width="300px">
							<img src="/upload/${vo.fileRealname}" />
						</td>
						<td width="50px;">
							<button id="close-file" onclick="closeFile()">X</button>
						</td>
					</tr>
				</c:if>
			</table>
			</div><br>
			
			<div id="row2" >
				<div id="fileList">
					<span class="files">첨부 파일 :&nbsp;</span>
					<c:choose>
						<c:when test="${not empty vo.filename}">
							<!-- form태그로 전송하는 방법 -->
							<form id="download_form" action="<%=request.getContextPath()%>/community/download/${vo.boardID}" method="get">
							<!-- 텍스트 클릭시 다운로드를 하기 위해 onclick으로 javascript를 이용해 submit기능을 대신한다. -->
								<div class="files" id="file_form" onclick="submit()" style="width: 300px; cursor: pointer;"> 
									<div id="filename">${vo.filename}</div>(다운로드 ${vo.fileDownCount}회)
									<input hidden="hidden" name="file" value="${vo.fileRealname}">
									<input hidden="hidden" name="boardID" value="${vo.boardID}">
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<span class="files">첨부된 파일이 없습니다.</span>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div id="btnList">
				<c:choose>
					<c:when test="${vo.boardCategory == 'NOTICE'}">
						<button type="button" id="list" class="btn-blue" onclick="location.href='history.back()'"><span></span></button>
					</c:when>
					<c:otherwise>
						<button type="button" id="list" class="btn-blue" onclick="location.href='/community/${category}'"><span>목록</span></button>
					</c:otherwise>
				</c:choose>
				<c:if test="${not empty userID}">
					<button type="button" class="btn-blue" id="cmt-write-btn" onclick="cmtAction()"><span>댓글쓰기</span></button>
					<c:if test="${vo.userID == userID}">
						<button type="button" class="btn-blue" id="update" onclick="location.href='/community/newpost/${vo.boardID}'"><span>수정</span></button>
						<button type="button" class="btn-blue" id="btn-del" onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='/community/delpost/${vo.boardID}?category=${vo.boardCategory}'}"><span>삭제</span></button>
					</c:if>
					<c:if test="${vo.userID == 'manager'}">
						<button type="button" class="btn-blue" id="btn-del" onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='/community/delpost/${vo.boardID}?category=${vo.boardCategory}'}"><span>삭제</span></button>
					</c:if>
				</c:if>
				</div>
			</div>
		</div>
		<div id="cmtSection">
			<h5 style="font-size: 15pt; color: #646464; float: left;">댓글 (${cmtlist.size()})<br></h5><hr id="cmt-line" style="width: 1000px;"><br>
			
	        <!-- 답변쓰기 버튼을 눌렀을 때만 답변쓰기 섹션이 나타나도록 설정 -->
			<div id="cmt-write" style="display: none; width: 600px; height: 220px;"> 
		          <input type="hidden" name="boardID" value="${vo.boardID}">
			          <table class="cmt-table" style="height: 100px; border-style: none;">
			             <tbody>
			                <tr>
			                   <td><input type="text" placeholder="댓글을 입력하세요" name="cmtContent" id="cmtContent" maxlength="60" style="width: 600px; height: 150px; font-size: 12pt;"></td>
			                </tr>
			             </tbody>
			          </table>
			      <button type="submit" class="btn-blue" id="cmt-cpl" onclick="registCmt(cmtContent)"><span>완료</span></button>
		  	</div><br><br>
		   
			<!-- 댓글 리스트 -->
			<div class="cmt-view" style="height: auto;">
				<div class="row" style="width: 600px; height: auto;">
				<c:if test="${not empty cmtlist}">
					<c:forEach var="cmt" items="${cmtlist}">
					<!-- cmtAvailable = 1인 값만 출력 -->
						<c:set var="cmtDate" value="${fn:substring(cmt.cmtDate,0,16)}"/>
						
						    <div class="cmt-list" style="width: 600px; height: 110px;">
						    <div style="display: flex;">
						    	<div class="cmt-icon" style="justify-content: center; padding: 10px;">
				               		<i style="font-size: 30pt;"class="fa-regular fa-face-smile"></i>
				               	</div>
			               		<table class="cmt-table" style="width: 600px;">
			               		<tr style="height: 30px; table-layout:fixed; ">
			               			<td align="left" style="width:30%;">${cmt.userID}</td>
			               			<td align="right" style="width:70%;">${cmtDate }</td>
			               		</tr>
			               		<tr style="height: auto; font-weight: 550;">
			               			<td colspan="2">${cmt.cmtContent}</td>
			               		</tr>		               		
					           	</table>
						    </div>
						    <c:if test="${not empty userID and userID == cmt.userID or userID == 'manager'}">
						         <button type="button" class="btn-blue" id="cmt-btn" onclick="deleteCmt(${cmt.cmtID})"><span>삭제</span></button>
						    </c:if>
							</div>
					</c:forEach>
					</c:if>
					<c:if test="${empty cmtlist }">
						아직 댓글이 없어요
					</c:if>
		         </div>
			</div>
		</div>
	</div>
</section>
<!-- section end -->

<!-- footer -->
<footer>
<hr>
<div class="inform">
	<ul>
		<li>06223 서울특별시 강남구 역삼로1004길 (역삼동, 대박타워) ㈜TOGETHER 대표이사 : 정윤서 | 사업자 등록번호: 222-22-22222｜통신판매업신고: 강남 1004호</li>
		<li>｜개인정보 보호책임자 : 정윤서 | 문의 : Webmaster@TOGETHER.co.kr | Copyright ⓒ TOGETHER. All rights reserved.</li>
		<li>㈜투게더의 사전 서면동의 없이 사이트(PC, 모바일)의 일체의 정보, 콘텐츠 및 UI 등을 상업적 목적으로 전재, 전송, 스크래핑 등 무단 사용할 수 없습니다.</liz>
	</ul>
</div>
</footer>
<!-- footer end -->

<script>
var userID = '${userID}';
var boardID = '${vo.boardID}';

//비동기식 댓글달기
function registCmt(content){
	var data = {
		content: content.value,
		userID : userID,
		boardID : boardID
	};
	
	$.ajax({
	    type: 'POST',
	    //url: 'https://toogether.me/spotRegistAction',
	    url: '/cmt',
	    data: data,
	    success: function (response) {
	    	if (response === 'success') {
	         	//alert('성공');
	        	reload(); //새로고침하는 메서드 실행
	    	}else{
	         	//alert('실패');
	    	}
	    },
	    error: function (xhr, status, error) {
	        alert('오류');
	    }
	});
}

//비동기식 댓글삭제
function deleteCmt(cmtID){
	var data = {
		cmtID : cmtID,
		userID : userID,
		boardID : boardID
	};
	console.log(cmtID);
	if(confirm('댓글을 삭제하시겠습니까?') == true){
		$.ajax({
		    type: 'POST',
		    //url: 'https://toogether.me/spotRegistAction',
		    url: '/cmt/delete',
		    data: data,
		    success: function (response) {
		    	if (response === 'success') {
		         	//alert('성공');
		        	reload(); //새로고침하는 메서드 실행
		    	}else{
		         	//alert('실패');
		    	}
		    },
		    error: function (xhr, status, error) {
		        alert('오류');
		    }
		});
	}
}
//하트 클릭 또는 취소
function heartAction(value){
	console.log(userID);
	console.log(value);
	if(userID === null || userID === ''){
		alert('로그인이 필요합니다');
		location.href='/login';
	}else{
		var data = {
	          userID : userID,
	          boardID : boardID,
	          value : value
	    };
	    $.ajax({
	        type: 'POST',
	        url: '/community/post/heart',
	        data: data,
	        success: function (response) {
		    	if (response === 'success') {
		    		//alert('성공');
		    		reload(); //새로고침
		    	}else{
		    		//alert('실패');
		    	}
	        },
	        error: function (xhr, status, error) {
	            //console.error('Spot registration error:', error);
	            alert('오류');
	        }
	    });
	}
}
//요소 새로고침
function reload(){
		$('#heart-count').load(location.href+' #heart-count');
}

//댓글쓰기를 클릭하면 댓글 입력 창 보이기
function cmtAction(){
	document.getElementById('cmt-write').style.display = 'block';
	document.getElementById('cmt-write-btn').style.display = 'none';
}

//파일 다운로드 submit
function submit(){
	let form = document.getElementById("download_form"); //form sumbit을 위해 form id를 가져온다.
    form.submit();
}
//파일 숨기기
function closeFile(){
	document.getElementById('view-file-1').style.display = 'none';
	document.getElementById('view-file-2').style.display = 'none';
}
</script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
</body>
</html>