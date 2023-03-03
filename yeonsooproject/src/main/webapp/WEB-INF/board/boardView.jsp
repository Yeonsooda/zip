<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- summernote -->
<link rel="stylesheet" href="/yeonsooproject/css/summernote/summernote-lite.css">	
<style type="text/css">
	#boardViewTable{
		width: 500px;
		height: 500px;
		text-align: center;
		border: 1px black solid;
		border-spacing: 0;
	}	
				
	tr, td{
		text-align: center;			
		border: 1px black solid;
	}

</style>
</head>
<body>
<!-- ajax 데이터 전달 시 사용할 게시글 번호 숨김  -->
<input name="BBRD_NO" type="hidden" value="${requestScope.BBRD_NO}"/>
<!-- 댓글 작성/작성 취소/수정/수정 취소 시 페이지 이동 기준값으로 사용   -->
<input id="pageSort" type="hidden" value="boardView"/>
<table id="boardViewTable">
	<tr>
		<td>No</td>
		<td>
			<div id="BBRD_NO" style="width: 350px;"></div>
		</td>
	</tr>
	<tr>
		<td>Title</td>
		<td>
			<div id="BBRD_TTL" style="width: 350px;"></div>
		</td>
	</tr>
	<tr>
		<td>Writer</td>
		<td>
			<div id="BBRD_WRTER_NM" style="width: 350px;"></div>
		</td>
	</tr>
	<tr>
		<td>Date</td>
		<td>
			<div id="BBRD_WR_DTTM" style="width: 350px;"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div id="BBRD_CNTNS" style="width: 490px; height: 350px; overflow:auto"></div>
		</td>
	</tr>
</table>
<div style="width: 500px; text-align:center; margin-top: 10px;">
	<input type="button" id="boardViewToUpdateFormBtn" value="수정">
	<input type="button" id="boardDeleteBtn" value="삭제">
	<input type="button" id="boardViewToListBtn" value="목록">
</div>

<!-- 댓글 구역  -->
<br>
<jsp:include page="/WEB-INF/comment/commentDisplay.jsp"/>

<!-- <script type="text/javascript"src="http://code.jquery.com/jquery-3.6.0.min.js"></script> -->
<script src="/yeonsooproject/js/common/jquery-3.6.0.min.js"></script>
<!-- summernote -->
<script src="/yeonsooproject/js/summernote/summernote-lite.js"></script>
<script src="/yeonsooproject/js/summernote/lang/summernote-ko-KR.js"></script>	
<!-- javascript 분리 -->
<script src="/yeonsooproject/js/board/boardView.js"></script>	
</body>
</html>