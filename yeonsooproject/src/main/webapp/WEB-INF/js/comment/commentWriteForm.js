/* 댓글 textarea focus 시 summernote 실행 */
$('#summernote').focus(function(){
	$('#summernoteTextArea').css("backgroundColor", "white")
    jsonArray = [];
	$('#summernote').summernote({
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
	});
});

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
	         $('#summernote').summernote('insertImage', data.url);
             jsonArray.push(data.url);
             //alert(jsonArray);
	      }
	   });
	}


$('#commentWriteBtn').click(function(){
	if($('#CMMT_WRTER_NM').val()==''){
		alert("작성자를 입력하세요!")
	}else if($('.note-editable').html()==''){
		alert("내용을 입력하세요!")
	}else{
		var formData = new FormData();					
			for(var i = 0; i<jsonArray.length; i++){
				//str: /yeonsooproject/src/main/webapp/storage/commentImg/temp/172692e1-2c6c-499a-aecd-2b287b623af1_6.png
			    var str = jsonArray[i];					    
			    //alert(str);
			    //'/'이 마지막으로 나타나는 위치 index.
			    var byFileName = str.lastIndexOf("/");
			    //substring(index) index 위치를 포함한 문자열 리턴
			    var result = str.substring(byFileName+1); 
			    //alert(result);
			    
			    formData.append('file[]', result);
			}
			//alert($('#BBRD_NO').html());
			formData.append('CMMT_BBRD_NO', $('#BBRD_NO').html());
			formData.append('CMMT_WRTER_NM', $('#CMMT_WRTER_NM').val());
			formData.append('CMMT_CNTNS', $('.note-editable').html());	
		$.ajax({
			type: 'post',
			url: '/yeonsooproject/comment/commentWrite',
			data: formData, 			      
			contentType : false,
		    enctype : 'multipart/form-data',
		    processData : false,
			success: function(data){
				alert("댓글 작성 완료!");
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
		})				
	}			
});

/* 댓글 쓰기 취소 버튼 클릭 시 */
$('#commentResetBtn').click(function(){
	if($('#pageSort').val()=="boardView"){
		location.href="/yeonsooproject/board/boardView?BBRD_NO="+$('#BBRD_NO').html();
	}else{
		location.href="/yeonsooproject/board/boardUpdateForm?BBRD_NO="+$('#BBRD_NO').html();
	}
});		