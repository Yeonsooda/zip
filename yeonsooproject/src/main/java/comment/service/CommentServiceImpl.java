package comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comment.bean.CommentDTO;
import comment.bean.CommentPaging;
import comment.dao.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	public CommentDAO commentDAO;
	@Autowired
	public CommentPaging commentPaging;
	
	//댓글 쓰기
	@Override
	public void commentWrite(Map<String, String> map){    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)
		commentDAO.commentWrite(map);
	}
	
	//뎃글 리스트 가져오기&게시글별 총 댓글 수 가져오기
	@Override
	public Map<String, Object> getCommentList(Map<String, Object> map){    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), commentPg(댓글 현재  페이지)
		//System.out.println(map);
		//댓글 현재 페이지
		int commentPg = Integer.parseInt((String) map.get("commentPg"));
		//페이지별 글 끝 번호
		int endNum = commentPg * 3;
		//페이지별 글 시작 번호 - 1
		int startNum = endNum - 3;
		
		map.put("startNum", startNum);
		
		List<CommentDTO> list = commentDAO.getCommentList(map);    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), startNum(페이지별 글 시작 번호 - 1)만 사용
		//System.out.println(list);
		
		//댓글 총 글수
		int commentBoard = commentDAO.getTotalComment(map);    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호)만 사용
		
		//페이징 처리
		commentPaging.setCurrentPage(commentPg);    //댓글 리스트 현재 페이지
		commentPaging.setPageBlock(3);    //한 페이지당 나타낼 페이지 번호 블록 수(3)
		commentPaging.setPageSize(3);    //한 페이지 당 나타낼 글 수(3)
		commentPaging.setTotalComment(commentBoard);    //댓글 총 글 수
		commentPaging.makePagingHTML();    //페이징 처리
				
		Map<String, Object> mapForCommentList = new HashMap<String, Object>();
		mapForCommentList.put("list", list);
		mapForCommentList.put("commentPaging", commentPaging);		
		
		return mapForCommentList;
	}
	
	//댓글 삭제
	@Override
	public void commentDelete(int CMMT_NO){
		commentDAO.commentDelete(CMMT_NO);
	}

	//댓글 정보 가져오기
	@Override
	public CommentDTO getComment(int CMMT_NO){
		return commentDAO.getComment(CMMT_NO);
	}
	
	//댓글 수정
	@Override
	public void commentUpdate(Map<String, String> map){    //map: CMMT_NO(댓글 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)
		commentDAO.commentUpdate(map);
	}

	//BoardController에서 시행 - 글 삭제 시 댓글 삭제
	@Override
	public void commentDeleteByBoard(int BBRD_NO){
		commentDAO.commentDeleteByBoard(BBRD_NO);
	}
}
