package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import board.bean.BoardDTO;

@Repository
@Transactional
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	//글 쓰기(insert)
	@Override
	public void boardWrite(Map<String, String> map){    //map: BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)    
		sqlSession.insert("boardSQL.boardWrite", map);		
	}
	
	//글 리스트 가져오기
	@Override
	public List<BoardDTO> getBoardList(int startNum){
		return sqlSession.selectList("boardSQL.getBoardList", startNum);
	}
	
	//총 게시글 수 가져오기
	@Override
	public int getTotalBoard(){
		return sqlSession.selectOne("boardSQL.getTotalBoard");
	}
	
	//글 정보 가져오기
	@Override
	public BoardDTO getBoard(int BBRD_NO){
		return sqlSession.selectOne("boardSQL.getBoard", BBRD_NO);
	}
	
	//글 수정(update)
	@Override
	public void boardUpdate(Map<String, Object> map){    //map: BBRD_NO(글번호), BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)
		sqlSession.update("boardSQL.boardUpdate", map);
	}
	
	//글 삭제
	@Override
	public void boardDelete(int BBRD_NO){
		sqlSession.delete("boardSQL.boardDelete", BBRD_NO);	
	}
	
	//글 검색 리스트 가져오기
	@Override
	public List<BoardDTO> getBoardSearchList(Map<String, Object> map){    //map: searchOption(검색 옵션), searchKeyword(검색 키워드)만 사용
		return sqlSession.selectList("boardSQL.getBoardSearchList", map);
	}
	
	//검색 조건에 맟는 총 게시글 수 가져오기
	@Override
	public int getSearchBoard(Map<String, Object> map){
		return sqlSession.selectOne("boardSQL.getSearchBoard", map);    //map: searchOption(검색 옵션), searchKeyword(검색 키워드)만 사용
	}
}
