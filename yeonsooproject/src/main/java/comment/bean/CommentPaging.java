package comment.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class CommentPaging{
	private int currentPage;    //댓글 리스트 현재 페이지
	private int pageBlock;    //한 페이지당 나타낼 페이지 번호 블록 수(3)
	private int pageSize;    //한 페이지 당 나타낼 글 수(3)
	private int totalComment;    //댓글 총 글 수
	private StringBuffer pagingHTML;    //페이징 처리(commentPaigingDiv에 넣어줄 데이터)
	
	public void makePagingHTML(){
		pagingHTML = new StringBuffer();
		
		//총 페이지 수
		int totalPage = (totalComment + pageSize - 1) / pageSize;
		
		//시작 페이지 번호
		int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;
		
		//끝 페이지 번호
		int endPage = startPage + pageBlock - 1;
		
		if(endPage > totalPage) endPage = totalPage;		
		
		if(startPage != 1){
			pagingHTML.append("[<span id='pagingNo' onClick='commentPaging(" + (startPage - 1) + ")'>prev</span>]");
		}
		
		for(int i = startPage; i <= endPage; i++){
			if(i == currentPage) {
				pagingHTML.append("[<span id='currentPagingNo' onClick='commentPaging("+i+")'>"+i+"</span>]");
			}else{
				pagingHTML.append("[<span id='pagingNo' onClick='commentPaging("+i+")'>"+i+"</span>]");
			}
		}
		
		if(endPage < totalPage){
			pagingHTML.append("[<span id='pagingNo' onClick='commentPaging(" + (endPage + 1) + ")'>next</span>]");
		}		
	}
}
