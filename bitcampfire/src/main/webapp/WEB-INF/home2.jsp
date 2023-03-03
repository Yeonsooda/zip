<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <title>bitcampfire</title>

	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/semiproject/css/user/application.css">
</head>			
<body>
	<div class="layout-container">
		<div class="main index">

		<!-- 연수 테스트용(220713) -->
		<input type="hidden" value="${sessionScope.memName }"/>
		<input type="hidden" value="${sessionScope.memNickname }"/>
		<input type="hidden" value="${sessionScope.memEmail }"/>
		<input type="hidden" value="${sessionScope.memAccessToken }"/>
		<input type="hidden" value="${sessionScope.memSocial }"/>
		<input type="hidden" value="${sessionScope.memKakaoId }"/>
		<input type="hidden" value="${sessionScope.memGrade}"/> <!-- 연수 추가(0724) : 관리자 여부 구분용  -->
		
			<!-- sidebar -->
			<jsp:include page="/WEB-INF/user/userSideBar.jsp" />
			<!-- sidebar -->
			<!-- main_banner -->
			<div class="main-banner-wrapper">
				<div class="main-banner">
					<a href="https://aihub.or.kr/" target="_i"><img src="/semiproject/img/okky_main_top_ad.jpg"></a>
				</div>
			</div>
			<!-- main_banner -->

			<!-- conten tscaffold-list clearfix -->
			<div id="index" class="content scaffold-list clearfix" role="main">
			
			</div>
			<!-- conten tscaffold-list clearfix -->

			<div class="right-banner-wrapper">	
				<img  style="width: 200px;" src="/semiproject/img/undo.gif" alt="right_banner">
				<img  style="width: 200px;" src="/semiproject/img/face.gif" alt="right_banner">
				<img  style="width: 200px;" src="/semiproject/img/CavernousWhiteBufflehead-max-1mb.gif" alt="right_banner">
			</div>	
			
			<!-- footer -->
			<jsp:include page="/WEB-INF/global/footer.jsp"/>
			<!-- footer -->
				
		</div><!-- main-index -->
	</div><!-- layout-container -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/semiproject/js/index.js"></script>
</body>
</html>
