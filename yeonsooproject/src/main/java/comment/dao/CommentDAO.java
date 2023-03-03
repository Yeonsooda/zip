package comment.dao;

import java.util.List;
import java.util.Map;

import comment.bean.CommentDTO;

public interface CommentDAO{
	//댓글 쓰기
	public void commentWrite(Map<String, String> map);    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)
	
	//뎃글 리스트 가져오기
	public List<CommentDTO> getCommentList(Map<String, Object> map);    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), startNum(페이지별 글 시작 번호 - 1)만 사용
	
	//게시글별 총 댓글 수 가져오기
	public int getTotalComment(Map<String, Object> map);    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호)만 사용
	
	//댓글 삭제
	public void commentDelete(int CMMT_NO);
	
	//댓글 정보 가져오기
	public CommentDTO getComment(int CMMT_NO);
	
	//댓글 수정
	public void commentUpdate(Map<String, String> map);    //map: CMMT_NO(댓글 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)

	//BoardController에서 시행 - 글 삭제 시 댓글 삭제
	public void commentDeleteByBoard(int BBRD_NO);
}
