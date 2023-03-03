<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#boardListTable{
		width: 800px;
		text-align:center;
		border: 1px black solid;
		border-spacing: 0;
	}						
	tr, td{	
		border: 1px black solid;
	}
	#currentPagingNo{
		color: blue;
		font-weight: bold;
		text-decoration: underLine;
	}
</style>
</head>
<body>
<!-- 페이징 처리에 필요한 페이지 값을 숨겨둠 -->
<input type="hidden" id="totalBoardPg" value="${requestScope.pg}"/>
<!-- 검색 시 사용 -->
<input type="hidden" id="searchOption_param" value="${param.searchOption}"/>
<input type="hidden" id="searchKeyword_param" value="${param.searchKeyword}"/>
<input type="hidden" id="searchBoardPg_param" value="${param.pg}"/>
<div style="width: 800px;">
	<input type="button" value="글쓰기" style="float: right; margin-bottom: 10px;" onClick="location.href='/yeonsooproject/board/boardWriteForm'"/>
</div>
<table id="boardListTable">
	<tr>
		<td width="50px">No</td>
		<td width="400px">Title</td>
		<td width="150px">Writer</td>
		<td width="200px">Date</td>
	</tr>
	<!-- 동적 생성  -->
</table>

<!-- 검색 기능 -->
<form id="searchForm">
<!-- 검색 후 페이징 처리에 필요한 페이지 값 기본 '1'로 설정하여 숨겨둠 -->		
<input type="hidden" name="searchBoardPg" value="1"/>
	<div style="width: 800px; text-align: center; margin-top: 20px;">
		<select id="searchOption" name="searchOption">
			<option value="BBRD_NO">No</option>
			<option value="BBRD_TTL">Title</option>
			<option value="BBRD_WRTER_NM">Writer</option>
			<option value="BBRD_WR_DTTM">Date</option>
		</select>
		<input type="text" id="searchKeyword" name="searchKeyword" /> <!-- value="" -->
		<input type="button" id="searchBtn" value="검색"/>
	</div>
</form>

<!-- 페이징 처리 -->
<div style="width: 800px; text-align: center; margin-top: 20px;">
	<div id="boardPagingDiv"></div>
</div>

<!-- <script type="text/javascript"src="http://code.jquery.com/jquery-3.6.0.min.js"></script> -->
<script src="/yeonsooproject/js/common/jquery-3.6.0.min.js"></script>
<!-- javascript 분리 -->
<script src="/yeonsooproject/js/board/boardList.js"></script>	
</body>
</html>