package board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	public BoardDAO boardDAO;
	@Autowired
	public BoardPaging boardPaging;

	//글 쓰기(insert)
	@Override
	public void boardWrite(Map<String, String> map){    //map: BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)
		boardDAO.boardWrite(map);		
	}

	//글 리스트 가져오기&총 게시글 수 가져오기 
	@Override
	public Map<String, Object> getBoardList(int pg){
		//페이지별 글번호(1페이지 5개글 기준) => mariadb는 쿼리 상에서 rownum 컷할 수 있음
		//페이지별 게시글 끝번호
		int endNum = pg * 5;
		//페이지별  게시글 시작번호 - 1
		int startNum = endNum - 5;
		
		//글 리스트 가져오기
		List<BoardDTO> list = boardDAO.getBoardList(startNum);
		
		//총 게시글 수 가져오기 
		int totalBoard = boardDAO.getTotalBoard();    //총글수
		
		//페이징 처리
		boardPaging.setCurrentPage(pg);    //게시판 리스트 현재 페이지
		boardPaging.setPageBlock(3);    //한 페이지당 나타낼 페이지 번호 블록 수(3)
		boardPaging.setPageSize(5);    //한 페이지 당 나타낼 글 수
		boardPaging.setTotalBoard(totalBoard);    //게시판 총 글 수
		boardPaging.makePagingHTML();    //페이징 처리
		
		Map<String, Object> mapForBoardList = new HashMap<String, Object>();
		
		mapForBoardList.put("list", list);
		mapForBoardList.put("boardPaging", boardPaging);
		
		return mapForBoardList;
	}

	//글 정보 가져오기
	@Override
	public BoardDTO getBoard(int BBRD_NO){		
		return boardDAO.getBoard(BBRD_NO);
	}

	//글 수정(update)
	@Override
	public void boardUpdate(Map<String, Object> map){    //map: BBRD_NO(글번호), BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)
		boardDAO.boardUpdate(map);
	}

	//글 삭제
	@Override
	public void boardDelete(int BBRD_NO){
		boardDAO.boardDelete(BBRD_NO);
	}

	//글 검색 리스트 가져오기&검색 조건에 맟는 총 게시글 수 가져오기
	@Override
	public Map<String, Object> boardSearch(Map<String, Object> map){    //map: searchOption(검색 옵션), searchKeyword(검색 키워드), searchBoardPg(검색 페이지 번호)
		//검색 후 현재 페이지
		int searchBoardPg = Integer.parseInt((String) map.get("searchBoardPg"));
		//페이지별 게시글글 끝 번호
		int endNum = searchBoardPg * 5;
		//페이지별  게시글 시작번호 - 1
		int startNum = endNum - 5;
		
		map.put("startNum", startNum);
		
		//글 검색 리스트 가져오기
		List<BoardDTO> list = boardDAO.getBoardSearchList(map);    //map: searchOption(검색 옵션), searchKeyword(검색 키워드), startNum(검색 페이지 당 글 시작 번호 - 1)만 사용
		//System.out.println(list);
		
		//검색 조건에 맟는 총 게시글 수 가져오기
		int searchBoard = boardDAO.getSearchBoard(map);    //map: searchOption(검색 옵션), searchKeyword(검색 키워드)만 사용
		
		//페이징 처리
		boardPaging.setCurrentPage(searchBoardPg); //현재 페이지
		boardPaging.setPageBlock(3); //페이지 번호 블록 수
		boardPaging.setPageSize(5); //한 페이지당 글 수
		boardPaging.setTotalBoard(searchBoard); //검색 총글수
		boardPaging.makePagingHTML(); //페이징 처리
				
		Map<String, Object> mapForSearchBoardList = new HashMap<String, Object>();
		mapForSearchBoardList.put("list", list);
		mapForSearchBoardList.put("boardPaging", boardPaging);		
		
		return mapForSearchBoardList;
	}
}
