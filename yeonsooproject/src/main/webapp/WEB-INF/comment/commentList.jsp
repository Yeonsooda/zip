<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">	
	#currentPagingNo{
		color: blue;
		font-weight: bold;
		text-decoration: underLine;
	}
</style>
</head>
<body>
<!-- 댓글 페이징 처리에 필요한 페이지 값 기본 '1'로 설정하여 숨겨둠 -->	
<input type="hidden" id="commentPg" value="1">
<!-- 페이징 처리 후 전달된 값을 url에 넣어주고 해당 값을 숨겨서 페이징 처리에 이용 -->
<input type="hidden" id="commentPg_param" value="${param.commentPg}">
<form id="commentListForm" style="width: 500px;">
<!-- commentList 동적 생성  -->
</form>
<!-- 페이징 처리  -->
<div id="commentPagingDiv" style="width: 500px; text-align: center;"></div>

<!-- <script type="text/javascript"src="http://code.jquery.com/jquery-3.6.0.min.js"></script> -->
<script src="/yeonsooproject/js/common/jquery-3.6.0.min.js"></script>
<!-- javascript 분리 -->
<script src="/yeonsooproject/js/comment/commentList.js"></script>	
</body>
</html>