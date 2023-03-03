<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#commentWriteTable{
		width: 500px;
		text-align: center;		
		border: none;
		overflow: auto;
	}		
	#commentWriteTable tr, #commentWriteTable tr td{
		text-align: center;		
		border: none;
		max-width: 490px;
	}
	textarea{
		width: 95%;
		height: 75px;
		resize: none;
	}
	button[aria-label="동영상"]{
		display: none;
	}
</style>
</head>
<body>
<div id="commentWriteDiv" style="width: 500px; border: 1px solid lightgray; background-color: whitesmoke;">
	<form id="commentWriteForm">
		<table id="commentWriteTable">
			<tr style="height:20px;">
				<td>Writer</td>
				<td>
					<input type="text" id="CMMT_WRTER_NM" style="width: 95%; border: 1px solid lightgray;"/>
				</td>
			</tr>
			<tr style="height:80px;" id="summernoteTextArea">
				<td colspan="2">
					<textarea id="summernote" name="CMMT_CNTNS" style="border: 1px solid lightgray;"></textarea>
				</td>
			</tr>
		</table>
	</form>
	<div style="width: 500px; margin: 5px 0px 10px 363px;">
		<input type="button" id="commentWriteBtn" value="댓글 쓰기" style="border: 1px solid lightgray; background-color: white;">
		<input type="button" id="commentResetBtn" value="취소" style="border: 1px solid lightgray; background-color: white;">
	</div>
</div>

<!-- <script type="text/javascript"src="http://code.jquery.com/jquery-3.6.0.min.js"></script> -->
<script src="/yeonsooproject/js/common/jquery-3.6.0.min.js"></script>
<!-- javascript 분리 -->
<script src="/yeonsooproject/js/comment/commentWriteForm.js"></script>		
</body>
</html>