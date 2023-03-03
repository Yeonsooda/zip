package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;

public interface BoardDAO{
	//글 쓰기(insert)
	public void boardWrite(Map<String, String> map);    //map: BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)
	
	//글 리스트 가져오기
	public List<BoardDTO> getBoardList(int startNum);
	
	//총 게시글 수 가져오기
	public int getTotalBoard();
	
	//글 정보 가져오기
	public BoardDTO getBoard(int BBRD_NO);
	
	//글 수정(update)
	public void boardUpdate(Map<String, Object> map);    //map: BBRD_NO(글번호), BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)
	
	//글 삭제
	public void boardDelete(int BBRD_NO);
	
	//글 검색 리스트 가져오기
	public List<BoardDTO> getBoardSearchList(Map<String, Object> map);    //map: searchOption(검색 옵션), searchKeyword(검색 키워드), startNum(검색 페이지 당 글 시작 번호)만 사용
	
	//검색 조건에 맟는 총 게시글 수 가져오기
	public int getSearchBoard(Map<String, Object> map);    //map: searchOption(검색 옵션), searchKeyword(검색 키워드)만 사용
}
