<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CKEditor -->
<script src="https://cdn.ckeditor.com/4.20.0/standard/ckeditor.js"></script>
<style type="text/css">
	#boardWriteTable{
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
<table id="boardWriteTable">
	<tr>
		<td>Title</td>
		<td>
			<input type="text" id="BBRD_TTL" style="width: 350px"/>
		</td>
	</tr>
	<tr>
		<td>Writer</td>
		<td>
			<input type="text" id="BBRD_WRTER_NM" style="width: 350px;"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<textarea id="BBRD_CNTNS"></textarea>
			<script type="text/javascript">
				CKEDITOR.replace("BBRD_CNTNS",
					{
						/* 에디터 높이 */
						height: 300,
						/* 이미지 업로드 컨트롤러 실행 */
						filebrowserUploadUrl:'/yeonsooproject/board/uploadImage'
					}
				);
			</script>
		</td>
	</tr>
</table>
<div style="width: 500px; text-align:center; margin-top: 10px;">
	<input type="button" id="boardWriteBtn" value="저장">
	<input type="button" id="boardResetBtn" value="취소">
	<input type="button" id="boardToListBtn" value="목록">
</div>

<!-- <script type="text/javascript"src="http://code.jquery.com/jquery-3.6.0.min.js"></script> -->
<script src="/yeonsooproject/js/common/jquery-3.6.0.min.js"></script>
<!-- javascript 분리 -->
<script src="/yeonsooproject/js/board/boardWriteForm.js"></script>	
</body>
</html>