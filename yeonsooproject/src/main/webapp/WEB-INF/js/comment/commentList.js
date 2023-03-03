/* 페이징 처리 */
function commentPaging(pagination){
	//commentPg 기본값은 1 => 해당 값을 commentPg값으로 변경해줌
	$('#commentPg').val(pagination);
	//변경된 commentPg값을 url로 넣어주고 페이징 처리 데이터로 사용
	location.href="/yeonsooproject/board/boardView?BBRD_NO="+$('input[name=BBRD_NO]').val()+"&commentPg="+pagination;		
};

/* 댓글 리스트 및 정보 가져오기*/
$(document).ready(function(){
	/* commentPg 기본값은 1 => 페이징 처리 시작되면 페이지 번호를 url에 넣고 param으로 받아오기 */
	if($('#commentPg_param').val()!=''){
		/*commentPg_param값이 있을때만 commentPg 기본값 1을 현재 페이지 값으로 변경해줌*/
		$('#commentPg').val($('#commentPg_param').val());
	}
	//alert($('input[name=BBRD_NO]').val());
	$.ajax({
		type: 'post',
		url: '/yeonsooproject/comment/getCommentList',
		data: {'commentPg': $('#commentPg').val(),
				'CMMT_BBRD_NO': $('input[name=BBRD_NO]').val(),				
				},
		success: function(data){
			//alert(JSON.stringify(data));
			//alert(JSON.stringify(data.commentPaging));
			$.each(data.list, function(index, items){
				//console.log(items.CMMT_BBRD_NO);
				$('<div/>', {
					id: 'commentListDiv'+items.CMMT_NO,
					style: 'width: 500px; border: none; border-top: 1px solid lightgray; border-bottom: 1px solid lightgray; margin-bottom:20px; font-size: 10pt;'
				}).append($('<table/>', {
					id: 'commentListTable',
					style: 'width: 500px; border: none; border-spacing: 0; overflow: auto;'
				})).append($('<tr/>', {
					style: 'width: 500px; height:20px; border: none; border-bottom: 1px solid lightgray; max-width: 490px;',
					}).append($('<td/>', {	
						style: 'width: 250px; text-align: center; border: none; border-bottom: 1px solid lightgray; max-width: 490px;'
						}).append($('<div/>', {	
							id: "CMMT_WRTER_NM",
							style: "width: 95%; border: none;",
							html: items.CMMT_WRTER_NM
						}))
					).append($('<td/>', {	
						style: 'width: 250px; text-align: center; border: none; border-bottom: 1px solid lightgray; max-width: 490px;'
						}).append($('<div/>', {	
							id: "CMMT_WR_DTTM",
							style: "width: 95%; border: none;",
							html: items.CMMT_WR_DTTM
						}))
					)
				).append($('<tr/>', {
					style: 'width: 500px; height:100px; text-align: center; border: none; border-bottom: 1px solid lightgray; max-width: 490px;'
					}).append($('<td/>', {
						colspan: '2',
						style: 'text-align: center; border: none; border-bottom: 1px solid lightgray;'
						}).append($('<div/>', {	
							id: "summernote",
							name: "CMMT_CNTNS",
							style: "width: 500px; height:100px; border: none; overflow: auto;",
							html: items.CMMT_CNTNS
						}))
					)
				).append($('<div/>', {	
					style: "width: 500px; margin: 10px 0px 10px 390px;"
					}).append($('<input/>', {	
						type: "button",
						id: "toCommentUpdateFormBtn"+items.CMMT_NO,
						value: "수정",
						style: "border: 1px solid lightgray; background-color: whitesmoke; margin-right: 10px"								
					})).append($('<input/>', {	
						type: "button",
						id: "commentDeleteBtn"+items.CMMT_NO,
						value: "삭제",
						style: "border: 1px solid lightgray; background-color: whitesmoke;"								
					}))
				).appendTo($('#commentListForm'));					
				
				/* 수정 버튼 클릭 시 댓글 뷰 페이지 가리고 댓글 수정폼으로 띄우기 */
				$('#toCommentUpdateFormBtn'+items.CMMT_NO).click(function(){
					$.ajax({
						type: 'post',
						url: '/yeonsooproject/comment/getComment',
						data: {'CMMT_NO': items.CMMT_NO},
						success: function(data){
							//alert(JSON.stringify(data));
							var updateForm =
										/* 댓글 수정을 위한 CMMT_NO 숨겨두기 */
										'<input type="hidden" id="CMMT_NO_update" value='+data.CMMT_NO+'>'+
										'<div id="commentUpdateDiv" style="width: 500px; border: 1px solid lightgray; background-color: whitesmoke; margin-bottom: 20px;">'+
											'<form id="commentUpdateForm">'+
												'<table id="commentUpdateTable" style="width: 500px; text-align: center; border: none; overflow: auto;">'+
													'<tr style="height:20px; text-align: center; border: none; max-width: 490px;">'+
														'<td style="border: none; max-width: 250x;"><input type="text" id="CMMT_WRTER_NM_update" style="text-align: center; width: 92%; border: 1px solid lightgray;" value='+data.CMMT_WRTER_NM+'></td>'+																
														'<td style="border: none; max-width: 250px;"><div id="CMMT_WR_DTTM_update" style="text-align: center; width: 95%; border: none;">'+data.CMMT_WR_DTTM+'</td>'+
													'</tr>'+
													'<tr style="height:80px; text-align: center; border: none; max-width: 490px;" id="summernoteTextArea_update">'+
														'<td colspan="2" style="text-align: center; border: none; max-width: 490px;">'+																	
															'<div id="summernote_update" name="CMMT_CNTNS_update" style="text-align: center; border: 1px solid lightgray; width: 97%; height: 100px; overflow: auto; background-color: white; margin-left: 7px;">'+data.CMMT_CNTNS+'</div>'+
														'</td>'+
													'</tr>'+
												'</table>'+
											'</form>'+
											'<div style="width: 500px; margin: 5px 0px 10px 363px;">'+
												'<input type="button" id="commentUpdateBtn" value="댓글 수정" style="border: 1px solid lightgray; background-color: white; margin-right: 10px;">'+
												'<input type="button" id="commentUpdateFormResetBtn" value="취소" style="border: 1px solid lightgray; background-color: white;">'+
											'</div>'+
										'</div>';										
							$('#commentListDiv'+data.CMMT_NO).after($(updateForm));			
							$('#commentListDiv'+items.CMMT_NO).hide();

							
							/* 댓글 수정 div focus 시 summernote 실행 */
							$('#summernote_update').click(function(){
								$('#summernoteTextArea_update').css("backgroundColor", "white")
							    jsonArray = [];
								$('#summernote_update').summernote({
									height: 100,                 //에디터 높이
									minHeight: null,             //최소 높이
									maxHeight: null,             //최대 높이
									focus: true,                 //에디터 로딩후 포커스를 맞출지 여부
									lang: "ko-KR",				 //한글 설정
									placeholder: '댓글을 입력하세요!', //placeholder 설정
									callbacks : { 
												onImageUpload : function(files){
													//파일 업로드(다중업로드를 위해 반복문 사용)
													for(var i = files.length - 1; i >= 0; i--){
														uploadImageBySummernote(files[i]);									
													}
												}
									}		           
								});//summernote 실행
							});//summernote 클릭
							
							/* summernote 이미지 업로드 실행 */
							function uploadImageBySummernote(file){	
								   data = new FormData();
								   data.append("file", file);
								   $.ajax({
								      data : data,
								      type : "POST",
								      url : "/yeonsooproject/comment/uploadImage",
								      contentType : false,
								      enctype : 'multipart/form-data',
								      processData : false,
								      success : function(data) {
								         //alert(data.url);         
								         $('#summernote_update').summernote('insertImage', data.url);
					                     jsonArray.push(data.url);
					                     //alert(jsonArray);
								      }
								   });
								}									
							
							/* 댓글 수정 */
							$('#commentUpdateBtn').click(function(){
								//alert($('#commentUpdateTable').find($('.note-editable')).html());
								if($('#CMMT_WRTER_NM_update').val()==''){
									alert("작성자를 입력하세요!")
								}else if($('#commentUpdateTable').find($('.note-editable')).html()==''){
									alert("내용을 입력하세요!")
								}else{
									var formData = new FormData();					
										for(var i = 0; i<jsonArray.length; i++){
											// str: /yeonsooproject/src/main/webapp/storage/commentImg/temp/172692e1-2c6c-499a-aecd-2b287b623af1_6.png
										    var str = jsonArray[i];					    
										    //alert(str);
										    // '/'이 마지막으로 나타나는 위치 index.
										    var byFileName = str.lastIndexOf("/");
										    // substring(index) index 위치를 포함한 문자열 리턴
										    var result = str.substring(byFileName+1); 
										    //alert(result);
										    
										    formData.append('file[]', result);
										}									
										formData.append('CMMT_NO', $('#CMMT_NO_update').val());
										formData.append('CMMT_WRTER_NM', $('#CMMT_WRTER_NM_update').val());
										formData.append('CMMT_CNTNS', $('#commentUpdateTable').find($('.note-editable')).html());	
									
									$.ajax({
										type: 'post',
										url: '/yeonsooproject/comment/commentUpdate',
										data: formData, 			      
										contentType : false,
									    enctype : 'multipart/form-data',
									    processData : false,
										success: function(data){
											alert("댓글 수정 완료!");
											/* 현재 페이지가  뷰 페이지인지 업데이트 폼인지에 따라 댓글 작성 후 이동 페이지 다르게 설정 */
											if($('#pageSort').val()=="boardView"){
												location.href="/yeonsooproject/board/boardView?BBRD_NO="+$('#BBRD_NO').html();
											}else{
												location.href="/yeonsooproject/board/boardUpdateForm?BBRD_NO="+$('#BBRD_NO').html();
											}
										},
										error: function(err){
											console.log(err);
										}	
									}); //ajax				
								}			
							});
							
							/* 댓글 수정 취소 버튼 클릭 시 */
							$('#commentUpdateFormResetBtn').click(function(){
								if($('#pageSort').val()=="boardView"){
									location.href="/yeonsooproject/board/boardView?BBRD_NO="+$('#BBRD_NO').html();
								}else{
									location.href="/yeonsooproject/board/boardUpdateForm?BBRD_NO="+$('#BBRD_NO').html();
								}
							});							
						},//success					
						error: function(err){
							console.log(err);
						}
					});//ajax						
				});
				
				/* 댓글 삭제 버튼 클릭 시*/
				$('#commentDeleteBtn'+items.CMMT_NO).click(function(){
					if(confirm("정말로 댓글을 삭제하시겠습니까?")){
						$.ajax({
							type: 'post',
							url: '/yeonsooproject/comment/commentDelete',
							data: {'CMMT_NO': items.CMMT_NO},
							success: function(data){
								alert("댓글 삭제 완료!")
								if($('#pageSort').val()=="boardView"){
									location.href="/yeonsooproject/board/boardView?BBRD_NO="+$('#BBRD_NO').html();
								}else{
									location.href="/yeonsooproject/board/boardUpdateForm?BBRD_NO="+$('#BBRD_NO').html();
								}
							},
							error: function(err){
								console.log(err);
							}
						});
					}//if(댓글 삭제)
				});//댓글 삭제 버튼 클릭 이벤트
			});//each
			$('#commentPagingDiv').html(data.commentPaging.pagingHTML);
		}, //success
		error: function(err){
			console.log(err);
		} //error
	});//ajax
});