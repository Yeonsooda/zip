/* 글 정보 가져오기*/
$(document).ready(function(){
	$.ajax({
		type: 'post',
		data: {'BBRD_NO': $('input[name=BBRD_NO]').val()},
		url: '/yeonsooproject/board/getBoard',
		success: function(data){
			//alert(JSON.stringify(data));
			$('#BBRD_NO').html(data.BBRD_NO);
			$('#BBRD_TTL').val(data.BBRD_TTL);
			$('#BBRD_WRTER_NM').val(data.BBRD_WRTER_NM);
			$('#BBRD_WR_DTTM').html(data.BBRD_WR_DTTM);
			/* 데이터 불러올 때 setTimeout 오류 해결을 위해 setTimeout을 넣어줌 */
			setTimeout(function(){
				CKEDITOR.instances.BBRD_CNTNS.setData(data.BBRD_CNTNS);
			}, 300);
			//$('#BBRD_CNTNS').html(data.BBRD_CNTNS);
		},
		error: function(err){
			console.log(err);
		}
	});
});

/* 저장 버튼 클릭 시  */
$('#boardUpdateBtn').click(function(){
	if($('#BBRD_TTL').val()==''){
		alert('제목을 입력하세요!')
	}else if($('#BBRD_WRTER_NM').val()==''){
		alert('작성자를 입력하세요!')
	}else{
		$.ajax({
			type: 'post',
			url: '/yeonsooproject/board/boardUpdate',
			data: {'BBRD_NO': $('#BBRD_NO').html(),
					'BBRD_TTL': $('#BBRD_TTL').val(),
					'BBRD_WRTER_NM': $('#BBRD_WRTER_NM').val(),
					'BBRD_CNTNS': CKEDITOR.instances.BBRD_CNTNS.getData()
					},
			success: function(){
				alert('게시글 수정 완료!');
				location.href='/yeonsooproject/board/boardView?BBRD_NO='+$('#BBRD_NO').html();
			},
			error: function(err){
				console.log(err);
			}						
		})//ajax
	}//else			
});

/* 취소 버튼 클릭 시  */

$('#updateFormResetBtn').click(function(){
	location.href='/yeonsooproject/board/boardUpdateForm?BBRD_NO='+$('input[name=BBRD_NO]').val();//${requestScope.BBRD_NO}
});

/* 목록 버튼 클릭 시 */
$('#updateFormToListBtn').click(function(){
	/* 페이지 값 넣기 */
	location.href='/yeonsooproject/?pg=1';
});