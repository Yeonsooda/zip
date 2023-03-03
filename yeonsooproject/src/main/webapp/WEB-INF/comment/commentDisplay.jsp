<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- commentWriteForm include -->
<jsp:include page="/WEB-INF/comment/commentWriteForm.jsp"/>
<br>
<!-- commentList 동적 생성(수정 폼 따로 없이 리스트에서 수정할 수 있도록 함) -->
<jsp:include page="/WEB-INF/comment/commentList.jsp"/>
</body>
</html>