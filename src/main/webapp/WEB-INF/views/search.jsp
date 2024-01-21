
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.toogether.vo.BoardVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/error/errorPage.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width = device-width , initial-scale = 1, user-scalable = no, maximum-scale = 1 , minimum-scale = 1">
<meta charset="UTF-8">
<title>TOGETHER</title>
<link rel="icon" href="/resources/image/logo.png">
<link rel="stylesheet" href="/resources/css/main.css?after">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/leaflet@1.6.0/dist/leaflet.css"/>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=IBM+Plex+Sans+KR:wght@300;600&family=Jua&family=Merriweather:wght@700&family=Nanum+Gothic&family=Nanum+Gothic+Coding&family=Noto+Sans+KR:wght@400&family=Noto+Serif+KR:wght@200&display=swap" rel="stylesheet">
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet">
<script defer src="option/jquery/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/resources/js/script.js"></script>
<script src="https://kit.fontawesome.com/f95555e5d8.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<style>
body{
	display: block;
}
section{
	padding-top: 150px;
	padding-bottom: 150px;
	height: auto;
	display: flex;
}
.board-container{
	margin: 0 auto;
}
#search-title{
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 20pt;
	font-weight: 400;
	color: #505050;
    display: flex;
    align-items: center;
	position: relative;
    animation: fadeInLeft 2s;
}
#search-title h4 {
	margin-left: 30px;
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
table{
	width: auto;
	min-width: 1000px;
	text-align: center;
	border-collapse: collapse;
}
thead{
	height: 30px;
	font-size: 13pt;
}
tbody{
}
th{
	color: #6e6e6e;
	text-align: center;
	height: 35px;
	border-radius: 30px;
	background-color: #D9E1FC;
	margin-right: 10px;
}
th span{
	font-size: 14pt;
}
.board-head{
	padding: 20px;

}
.board-row{
	border-bottom: solid 1px #E0E0E0;
}

#row-btn-sec{
	width:auto;
	display: flex;
	margin: 0;
	padding: 0;
}
#more-btn{
	width: 100px;
	margin: 0 auto;
	font-size: 15pt;
	font-weight: bold;
	color: #404040;
	cursor: pointer;
}
#more-btn:hover{
	color: #E0E0E0;
}
#click-view{
	justify-content: center;
	align-items: center;
}
#click-view:hover{
	font-weight: 400;
	text-decoration: underline;
	color: #606060;
}
/* 공지 */
#notice-animated{
	width: auto;
	height: 30px;
	padding: 10px;
	border-color: #909090;
	border-top: thin solid black;
	border-bottom: thin solid black;
	margin-bottom: 10px;
	justify-content: center;
	position: relative;
	overflow-x: hidden;
	overflow-y: hidden;
}
#notice{
	width: auto;
	height: auto;
	position: absolute;
	white-space: nowrap;
	will-change: transform;
	animation: marquee 13s linear infinite;
	z-index: 5;
}
#notice-inner{
	height: 30px;
	display: flex;
	font-size: 15pt;
	align-items: center;
	margin: 10px 5px;
}
#notice-option:hover{
	font-weight: bold;
	color:#606060;
}
#notice-option:visited{
	color: purple;
}
/* 공지 애니메이션 */
@keyframes marquee {
  from { transform: translateY(20%); }
  to { transform: translateY(-100%); }
}

@media screen and (max-width:900px) {
	table{
		min-width: 600px;
	}
	th span{
		font-size: 12pt;
	}
	#search-title{
		display: block;
		margin-bottom: 50px;
	}
	#search-title h2{
		margin: 0;
	}
	#search-title h4{
		margin: 0;
		font-size: 16pt;
	}
	td{
		font-size: 12pt;
	}
	#click-view{
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		max-width:200px;
	}
}

@media screen and (max-width:650px) {
	table{
		min-width: 350px;
	}
	th span{
		font-size: 11pt;
	}
	#search-title{
		display: block;
		margin-bottom: 50px;
	}
	#search-title h2{
		margin: 0;
	}
	#search-title h4{
		margin: 0;
		font-size: 16pt;
	}
	.date{
		display: none;
	}
	td{
		font-size: 12pt;
	}
	.ttt{
		width: 100px;
	}
	#click-view{
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		max-width:100px;
	}
	.btn-blue span{
		font-size: 10pt;
	}
}
</style>
<body>
<%
	PrintWriter script = response.getWriter();
	String userID=null;
	int boardID = 0;
	String boardCategory = null;
	String avlb = null;
	if(session.getAttribute("userID")!=null){
		userID=(String)session.getAttribute("userID");
	}
%>
<header id="header">
	<jsp:include page="/resources/header/header.jsp"/>
</header>
<section>
	<c:set var="userID" value="<%=userID %>"/>
	<div class="board-container">
		<div id="search-title">
			<h2>${category.toUpperCase()}</h2><h4>함께 할 사람들과 이야기 나눠보세요</h4>
		</div><br>
		
		<!-- 게시글 리스트 -->
		<div class="row">
			<table>
				<thead>
					<tr class="board-head">
						<th class="ttt" style="width: 10%;"><span>조회수</span></th>
						<th class="tt" style="width: 30%;"><span>제목</span></th>
						<th class="ttt" style="width: 10%;"><span>작성자</span></th>
						<th class="ttt" style="width: 10%;"><span>좋아요</span></th>
						<th class="ttt" style="width: 10%;"><span>댓글</span></th>
						<th class="date" style="width: 25%;"><span>작성일</span></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty bdlist}">
						<tr>
							<td colspan="6">
								<marquee>테이블에 저장된 글이 없습니다.</marquee>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty bdlist}">
					<c:forEach var="vo" items="${bdlist}">
				        <tr class="board-row">
				        	<td>${vo.viewCount}</td>
				        	<td>
				        	<a id="click-view" onclick="view('${category}','${vo.boardTitle}','${vo.boardID}')">${vo.boardTitle}
				        	<c:if test="${not empty vo.filename}">
                                &nbsp;<i class="fa-solid fa-paperclip"></i>
                            </c:if>
                            </a>
                            </td>

				            <td>${vo.userID}</td>
				            <td>${vo.heartCount}</td>
				            <td>empty</td>
				            <c:set var="boardDate" value="${fn:substring(vo.boardDate,0,16)}"/>
				            <td class="date">
				            	${boardDate}
				            </td>            
				        </tr>
				    </c:forEach>
				    </c:if>
				</tbody>
			</table>
		</div>
		<c:if test="${bdlist.size() > 10}">
			<br><div id="row-btn-sec">
				<div id="more-btn">
					<a>
					<span>MORE</span>	    
					<i class="fa-solid fa-chevron-down"></i>
					</a>
				</div>
			</div>
		</c:if>
		<button type="button" class="btn-blue" id="search" onclick="location.href='/community'"><span>돌아가기</span></button>
		<c:if test="${not empty userID}">
			<button type="button" class="btn-blue" id="write" onclick="location.href='/community/newpost?category=${category}'"><span>글쓰기</span></button>
		</c:if>
	</div>
</section>
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
<script>
function view(category, title, id){
    console.log(category);
    console.log(id);
	location.href='/community/post/'+id.toString();
}
</script>
<script>
//게시글 더보기
$(document).ready(function(){
	$('.board-row').hide();
    $('.board-row').slice(0, 10).show(); // 초기갯수
    $("#more-btn").click(function(e){ // 클릭시 more
        if($('.board-row:hidden').length == 0){ // 컨텐츠 남아있는지 확인
            alert("마지막 글입니다."); // 컨텐츠 없을시 alert 창 띄우기 
        }
        e.preventDefault();
        $('.board-row:hidden').slice(0, 5).show('slow'); 
	});
});
</script>
</body>
</html>