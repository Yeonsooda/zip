package board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import board.bean.BoardDTO;
import board.service.BoardService;
import comment.service.CommentService;

@Controller
@RequestMapping(value="/board")
public class BoardController{
	
	protected static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	protected static final Logger filelogger = LoggerFactory.getLogger("fileLogger");
	
	public static void main(String[] args){
		logger.debug("BoardController");
		filelogger.debug("fileLogger logger print");
	}
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;
	
	//글 쓰기창 띄우기
	@GetMapping(value="/boardWriteForm")
	public String boardWriteForm(){
		return "/board/boardWriteForm";
	}
		
	//글쓰기
	@PostMapping(value="/boardWrite")
	@ResponseBody
	public void boardWrite(@RequestParam Map<String, String> map){    //map: BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)
		boardService.boardWrite(map);
	}
	
	//CKEditor 이미지 업로드
	@PostMapping(value="/uploadImage")
	public void uploadImage(HttpServletRequest req, HttpServletResponse res, @RequestParam MultipartFile upload) throws Exception{    //CKEditor 상에서 file이 전송		
		//업로드 폴더 경로
		String uploadPath = req.getSession().getServletContext().getRealPath("/").concat("WEB-INF\\storage\\boardImg\\");
		//System.out.println("uploadPath: " + uploadPath);
		
		//랜덤 문자 생성(고유키 생성)
		UUID uid = UUID.randomUUID();

		OutputStream out = null;
		PrintWriter printWriter = null;

		//인코딩
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json");

		try{
			//오리지널 파일명
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();

			//최종 저장된 이미지 업로드 실제 경로
			String ckUploadPath = uploadPath + File.separator + uid + "_" + fileName;

			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush();    //out에 저장된 데이터를 전송하고 초기화

			printWriter = res.getWriter();
			
			//글 작성 화면에서 노출될 최종 저장된 이미지 업로드 경로
			String fileUrl = "/yeonsooproject/src/main/webapp/storage/boardImg/" + uid + "_" + fileName;
			
			//업로드시 메시지 출력
			JsonObject json = new JsonObject();
			json.addProperty("uploaded", 1);
			json.addProperty("fileName", fileName);
			json.addProperty("url", fileUrl);
			printWriter.println(json);
			//System.out.println("내가바로콜백" + json);

			printWriter.flush();
			//System.out.println("test url: " + req.getSession().getServletContext().getRealPath("/"));
			//System.out.println("url: " + fileUrl);
			//System.out.println("ckUploadPath: " + ckUploadPath);
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(out != null){
					out.close();
				}
				if(printWriter != null){
					printWriter.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return;
	}
	
	//글 리스트 가져오기&총 게시글 수 가져오기 => boardList폼은 메인페이지에 include '/' 호출 시 띄워짐
	@PostMapping(value="/getBoardList")
	@ResponseBody
	public Map<String, Object> getBoardList(@RequestParam(required=false, defaultValue="1") int pg){
		return boardService.getBoardList(pg);		
	}	
	
	//글 보기 창 띄우기 => 댓글 구역 commentDisplay.jsp(댓글 쓰기, 댓글 리스트) include(20221020)
	@GetMapping(value="/boardView")
	public ModelAndView boardView(@RequestParam int BBRD_NO){
		ModelAndView mav = new ModelAndView();
		mav.addObject("BBRD_NO", BBRD_NO);
		mav.setViewName("/board/boardView");
		
		return mav;
	}
	
	//글 정보 가져오기
	@PostMapping(value="/getBoard")
	@ResponseBody
	public BoardDTO getBoard(@RequestParam int BBRD_NO){
		return boardService.getBoard(BBRD_NO);
	}
	
	//글 수정 폼 띄우기 => 댓글 구역 commentDisplay.jsp(댓글 쓰기, 댓글 리스트) include(20221020)
	@GetMapping(value="/boardUpdateForm")
	public ModelAndView boardUpdateForm(@RequestParam int BBRD_NO){
		ModelAndView mav = new ModelAndView();
		mav.addObject("BBRD_NO", BBRD_NO);
		mav.setViewName("/board/boardUpdateForm");
		
		return mav;
	}
	
	//글 수정
	@PostMapping(value="/boardUpdate")
	@ResponseBody
	public void boardUpdate(@RequestParam Map<String, Object> map){    //map: BBRD_NO(글번호), BBRD_TTL(글제목), BBRD_WRTER_NM(글 작성자), BBRD_CNTNS(글 내용)
		//System.out.println(map);
		boardService.boardUpdate(map);		
	}
	
	//글 삭제
	@PostMapping(value="/boardDelete")
	@ResponseBody
	public void boardDelete(@RequestParam int BBRD_NO) {
		boardService.boardDelete(BBRD_NO);
		//글 삭제 시 댓글도 삭제되도록 함
		commentService.commentDeleteByBoard(BBRD_NO);
	}	
	
	//글 검색 리스트 가져오기&검색 조건에 맟는 총 게시글 수 가져오기
	@PostMapping(value="/boardSearch")
	@ResponseBody
	public Map<String, Object> boardSearch(@RequestParam Map<String, Object> map){    //map: searchOption(검색 옵션), searchKeyword(검색 키워드), searchBoardPg(검색 페이지 번호)	
		//System.out.println("####################"+map);		
		return boardService.boardSearch(map);
	}	
}
