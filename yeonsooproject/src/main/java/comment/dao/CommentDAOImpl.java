package comment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import comment.bean.CommentDTO;

@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO{	
	@Autowired
	private SqlSession sqlSession;
	
	//댓글 쓰기
	@Override
	public void commentWrite(Map<String, String> map){    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)
		sqlSession.insert("commentSQL.commentWrite", map);	
	}

	//뎃글 리스트 가져오기
	@Override
	public List<CommentDTO> getCommentList(Map<String, Object> map){    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), startNum(페이지별 글 시작 번호 - 1)만 사용
		return sqlSession.selectList("commentSQL.getCommentList", map);
	}
	
	//게시글별 총 댓글 수 가져오기
	@Override
	public int getTotalComment(Map<String, Object> map){    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호)만 사용
		return sqlSession.selectOne("commentSQL.getTotalComment", map);
	}
	
	//댓글 삭제
	@Override
	public void commentDelete(int CMMT_NO){
		sqlSession.delete("commentSQL.commentDelete", CMMT_NO);
	}
	
	//댓글 정보 가져오기
	@Override
	public CommentDTO getComment(int CMMT_NO){
		return sqlSession.selectOne("commentSQL.getComment", CMMT_NO);
	}
	
	//댓글 수정
	@Override
	public void commentUpdate(Map<String, String> map){    //map: CMMT_NO(댓글 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)
		sqlSession.update("commentSQL.commentUpdate", map);
	}

	//BoardController에서 시행 - 글 삭제 시 댓글 삭제
	@Override
	public void commentDeleteByBoard(int BBRD_NO){
		sqlSession.delete("commentSQL.commentDeleteByBoard", BBRD_NO);
	}
}