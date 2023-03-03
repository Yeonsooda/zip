/* 저장 버튼 클릭 시  */
$('#boardWriteBtn').click(function(){
	if($('#BBRD_TTL').val()==''){
		alert('제목을 입력하세요!')	
	}else if($('#BBRD_WRTER_NM').val()==''){
		alert('작성자를 입력하세요!')	
	}else if(CKEDITOR.instances.BBRD_CNTNS.getData()==''){
		alert('내용을 입력하세요!')	
	}
	else{
		$.ajax({
			type: 'post',
			url: '/yeonsooproject/board/boardWrite',
			data: {'BBRD_TTL': $('#BBRD_TTL').val(),
					'BBRD_WRTER_NM': $('#BBRD_WRTER_NM').val(),
					'BBRD_CNTNS': CKEDITOR.instances.BBRD_CNTNS.getData()},
			success: function(){
				alert('게시글 등록 완료!');
				/* 리스트 페이지로 이동 */
				location.href='/yeonsooproject/';
			},
			error: function(err){
				console.log(err);
			}							
		});
	}
});

/* 취소 버튼 클릭 시 */
$('#boardResetBtn').click(function(){
	$('#BBRD_TTL').val('');
	$('#BBRD_WRTER_NM').val('');
	CKEDITOR.instances.BBRD_CNTNS.setData('');
});	

/* 목록 버튼 클릭 시 */
$('#boardToListBtn').click(function(){
	location.href='/yeonsooproject/?pg=1';
});