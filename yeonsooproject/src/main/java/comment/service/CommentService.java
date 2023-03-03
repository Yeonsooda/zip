package comment.service;

import java.util.Map;

import comment.bean.CommentDTO;

public interface CommentService{
	//댓글 쓰기
	public void commentWrite(Map<String, String> map);    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)
	
	//뎃글 리스트 가져오기&게시글별 총 댓글 수 가져오기
	public Map<String, Object> getCommentList(Map<String, Object> map);    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), commentPg(댓글 현재  페이지)

	//댓글 삭제
	public void commentDelete(int CMMT_NO);

	//댓글 정보 가져오기
	public CommentDTO getComment(int CMMT_NO);

	//댓글 수정
	public void commentUpdate(Map<String, String> map);    //map: CMMT_NO(댓글 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)

	//BoardController에서 시행 - 글 삭제 시 댓글 삭제
	public void commentDeleteByBoard(int BBRD_NO);
}
