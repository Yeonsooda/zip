package board.service;

import java.util.Map;

import board.bean.BoardDTO;

public interface BoardService {
	//글 쓰기(insert)
	public void boardWrite(Map<String, String> map);    //map: BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)
	
	//글 리스트 가져오기&총 게시글 수 가져오기
	public Map<String, Object> getBoardList(int pg);
	
	//글 정보 가져오기
	public BoardDTO getBoard(int BBRD_NO);
	
	//글 수정(update)
	public void boardUpdate(Map<String, Object> map);    //map: BBRD_NO(글번호), BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)

	//글 삭제
	public void boardDelete(int BBRD_NO);

	//글 검색 리스트 가져오기&검색 조건에 맟는 총 게시글 수 가져오기
	public Map<String, Object> boardSearch(Map<String, Object> map);    //map: searchOption(검색 옵션), searchKeyword(검색 키워드), searchBoardPg(검색 페이지 번호)
}
