/* 글 정보 가져오기*/
$(document).ready(function(){
	$.ajax({
		type: 'post',
		data: {'BBRD_NO': $('input[name=BBRD_NO]').val()},
		url: '/yeonsooproject/board/getBoard',
		success: function(data){
			//alert(JSON.stringify(data));
			$('#BBRD_NO').html(data.BBRD_NO);
			$('#BBRD_TTL').html(data.BBRD_TTL);
			$('#BBRD_WRTER_NM').html(data.BBRD_WRTER_NM);
			$('#BBRD_WR_DTTM').html(data.BBRD_WR_DTTM);
			$('#BBRD_CNTNS').html(data.BBRD_CNTNS);
		},
		error: function(err){
			console.log(err);
		}
	});
});

/* 글 수정 버튼 클릭 시  */
$('#boardViewToUpdateFormBtn').click(function(){
	location.href='/yeonsooproject/board/boardUpdateForm?BBRD_NO='+$('input[name=BBRD_NO]').val();//${requestScope.BBRD_NO}
});
/* 글 삭제 버튼 버튼 클릭 시 */
$('#boardDeleteBtn').click(function(){
	if(confirm('정말로 게시글을 삭제하시겠습니까?\n삭제 시 해당 게시글의 댓글도 삭제 처리 됩니다.')){
		$.ajax({
			type: 'post',
			data: {'BBRD_NO': $('input[name=BBRD_NO]').val()},
			url: '/yeonsooproject/board/boardDelete',
			success: function(){
				alert('글 삭제 완료!');
				location.href="/yeonsooproject/?pg=1"
			},
			erreor: function(err){
				console.log(err);
			}
		});//ajax
	}
})
/* 목록 버튼 클릭 시 */
$('#boardViewToListBtn').click(function(){
	/* 페이지 값 넣기 */
	location.href='/yeonsooproject/?pg=1';
});