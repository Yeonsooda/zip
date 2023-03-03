/* 페이징 처리 */
function boardPaging(pagination){
	var searchKeyword = $('input[name=searchKeyword]').val();
	
	if(searchKeyword==''){
		location.href="/yeonsooproject/?pg="+pagination;
	}else{		
		$('input[name=searchBoardPg]').val(pagination);			
		$('#searchBtn').trigger('click');			
	}
}

/* 검색 버튼 클릭 시 페이지 이동=> searchOption, searchKeyword, searchBoardPg 값을 param으로 받아서 페이징 처리 및 검색에 사용*/
$('#searchBtn').click(function(){
	//검색어가 없으면 첫 페이지로
	if($('#searchKeyword').val()==''){
		alert('검색어를 입력하세요!');
		location.href="/yeonsooproject/?pg=1";
	}else{
		location.href="/yeonsooproject/?searchOption="+$('select[name=searchOption]').val()+"&searchKeyword="+encodeURI($('input[name=searchKeyword]').val())+"&pg="+$('input[name=searchBoardPg]').val();
	}
});

$(document).ready(function(){
	/* 검색 조건이 있을 때 */
	if($('#searchOption_param').val()!='' && $('#searchKeyword_param').val()!=''){
		$('#searchOption').val($('#searchOption_param').val()).attr("selected", "selected");
		$('#searchKeyword').val($('#searchKeyword_param').val());	
		$('input[name=searchBoardPg]').val($('#searchBoardPg_param').val());
		$.ajax({
			type: 'post',
			url: '/yeonsooproject/board/boardSearch',
			data: $('#searchForm').serialize(),
				/* {'searchOption': $('select[name=searchOption]').val(),
				'searchKeyword': $('input[name=searchKeyword]').val(),
				'searchBoardPg': $('input[name=searchBoardPg]').val()}, */
			success: function(data){			
				//alert(JSON.stringify(data.list));
				if(data.list == ''){
					alert("검색하신 조건과 일치하는 글이 없습니다!");
					location.href="/yeonsooproject/?pg=1";
				}else{
					$('#boardListTable tr:gt(0)').remove();
					
					$.each(data.list, function(index, items){
						//console.log(items.BBRD_NO);
						$('<tr/>').append($('<td/>', {
							align: 'center',							
							text: items.BBRD_NO
						})).append($('<td/>', {							
							}).append($('<a/>', {								
								href: '/yeonsooproject/board/boardView?BBRD_NO='+items.BBRD_NO,								
								text: items.BBRD_TTL,								
							}))						
						).append($('<td/>', {
							align: 'center',
							text: items.BBRD_WRTER_NM							
						})).append($('<td/>', {
							align: 'center',
							text: items.BBRD_WR_DTTM
						})).appendTo($('#boardListTable'));									
					});//each
				}//else
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			},
			error: function(err){
				console.log(err);
			}
		});//ajax
	/* 검색 조건이 없을 때 */
	}else{
		$.ajax({
			type: 'post',
			url: '/yeonsooproject/board/getBoardList',
			data: {'pg': $('#totalBoardPg').val()},
			dataType: 'json',
			success: function(data){
				//alert(JSON.stringify(data.boardPaging));
				$.each(data.list, function(index, items){
					//console.log(items.BBRD_NO);
					$('<tr/>').append($('<td/>', {
						align: 'center',							
						text: items.BBRD_NO
					})).append($('<td/>', {							
						}).append($('<a/>', {								
							href: '/yeonsooproject/board/boardView?BBRD_NO='+items.BBRD_NO,								
							text: items.BBRD_TTL,								
						}))						
					).append($('<td/>', {
						align: 'center',
						text: items.BBRD_WRTER_NM							
					})).append($('<td/>', {
						align: 'center',
						text: items.BBRD_WR_DTTM
					})).appendTo($('#boardListTable'));					
				});//each
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			},
			error: function(err){
				console.log(err);
			}
		});//ajax
	}//else		
});