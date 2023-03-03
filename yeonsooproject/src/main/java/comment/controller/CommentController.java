package comment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import comment.bean.CommentDTO;
import comment.service.CommentService;

@Controller
@RequestMapping(value="/comment")
public class CommentController{
	protected static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	protected static final Logger filelogger = LoggerFactory.getLogger("fileLogger");
	
	public static void main(String[] args){
		logger.debug("CommentController");
		filelogger.debug("fileLogger logger print");		
	}
	
	@Autowired
	private CommentService commentService;

	//댓글 이미지 업로드 (by.summernote 에디터)
	@RequestMapping(value="/uploadImage", produces="application/json; charset=utf8")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile upload, HttpServletRequest req){
		JsonObject jsonObject = new JsonObject();        
		   	         
		//프로젝트 실제 경로 가져오기
		String contextRoot = new HttpServletRequestWrapper(req).getRealPath("/");    //contextroot 경로를 확인한 후 servers 폴더 > server.xml > docBase에  경로 수정
		//업로드 폴더 경로(프로젝트 내부경로 + 업로드할 폴더 경로 지정): 댓글 저장 전까지는 임시 폴더(temp)에만 저장
		String uploadPath = contextRoot + "WEB-INF\\storage\\commentImg\\temp\\"; 
		
		//System.out.println("$$$$$$$" + contextRoot);
		
		//오리지널 파일명
		String fileName = upload.getOriginalFilename();
		//System.out.println("$$$$$$$" + fileName);
		
		////랜덤 문자 생성(고유키 생성)하여 파일명에 추가
		String savedFileName = UUID.randomUUID() + "_" + fileName;
		//System.out.println("$$$$$$$" + savedFileName);	         
		
		//파일 생성(파일 경로+파일명)
		File targetFile = new File(uploadPath + savedFileName);   
		
		try{
			//파일 데이터를 읽어서
			InputStream fileStream = upload.getInputStream();
			//읽은 데이터를 생성된 파일(targetFile)에 저장	
			FileUtils.copyInputStreamToFile(fileStream, targetFile);         
			jsonObject.addProperty("url", "/yeonsooproject/src/main/webapp/storage/commentImg/temp/" + savedFileName); 
			jsonObject.addProperty("responseCode", "success");
		         
		}catch(IOException e){
			//에러 발생 시 생성된 파일 삭제
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		String result = jsonObject.toString();
		//System.out.println("$$$$$$$" + result);
		
		return result;
		}	
	
	//댓글 쓰기
	@PostMapping(value="/commentWrite")
	@ResponseBody
	public void commentWrite(@RequestParam Map<String, String> map, @RequestParam(value="file[]", required = false) List<String> summernoteFile, HttpServletRequest req) throws Exception{
		//map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)
		
		//작성된 댓글 내용 중 이미지 파일이 존재할 때만 실행
		if(summernoteFile!=null){
			//댓글 저장 시 임시 폴더에 저장된 파일을 최종 폴더(copy)에 복사
			summernoteFileCopy(summernoteFile, req);
		
			//System.out.println(map);
			
			//임시 폴더 경로(temp)를 최종 폴더 경로(copy)로 변경해줌
			String finalFilePath = map.get("CMMT_CNTNS").replaceAll("/commentImg/temp/", "/commentImg/copy/");
			//System.out.println(finalFilePath);
			
			//변경된 정보를 map에 실어서
			map.put("CMMT_CNTNS", finalFilePath);
		}
		//댓글 작성
		commentService.commentWrite(map);		
	}
	
	//댓글 쓰기 실행 시 이미지 파일이 있을 경우, 임시 폴더에 저장된 파일을 최종 폴더에 복사
	public Map<String, Object> summernoteFileCopy(@RequestParam(value = "file[]") List<String> fileList, HttpServletRequest req) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		
		for(int i = 0; i < fileList.size(); i++){
			//프로젝트 실제 경로 가져오기
			String contextRoot = new HttpServletRequestWrapper(req).getRealPath("/");

			//임시 폴더에 저장된 원본 파일 경로
			String oriFilePath = contextRoot + "WEB-INF\\storage\\commentImg\\temp\\" + fileList.get(i);
			logger.debug("oriFilePath: {}", oriFilePath);

			//복사될 파일 경로를 지정하여 파일 생성
			File copyFilePath = new File(contextRoot + "WEB-INF\\storage\\commentImg\\copy\\" + fileList.get(i));
			logger.debug("copyFilePath: {}", copyFilePath);

			try{
				//임시 폴더에 저장된 원본 파일의 경로를 읽어서
				FileInputStream fis = new FileInputStream(oriFilePath);
				//최종 폴더에 파일 생성
				FileOutputStream fos = new FileOutputStream(copyFilePath);    

				int data = 0;
				
				//더이상 읽을 데이터가 없을때까지 데이터를 읽어서
				while((data = fis.read()) != -1){
					//파일을 생성
					fos.write(data);
				}
				fis.close();
				fos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		result.put("SUCCESS", true);
		logger.debug("result: {}", result);
		return result;
	}
	
	//뎃글 리스트 가져오기&게시글별 총 댓글 수 가져오기
	@PostMapping(value="/getCommentList")
	@ResponseBody
	public Map<String, Object> getCommentList(@RequestParam Map<String, Object> map){    //map: CMMT_BBRD_NO(댓글이 작성된 게시글의 번호), commentPg(댓글 현재  페이지)
		return commentService.getCommentList(map);		
	}
	
	//댓글 삭제
	@PostMapping(value="/commentDelete")
	@ResponseBody
	public void commentDelete(@RequestParam int CMMT_NO){
		//System.out.println(CMMT_NO);
		commentService.commentDelete(CMMT_NO);
	}
	
	//댓글 정보 가져오기
	@PostMapping(value="/getComment")
	@ResponseBody
	public CommentDTO getComment(@RequestParam int CMMT_NO){
		//System.out.println(CMMT_NO);
		return commentService.getComment(CMMT_NO);
	}
	
	//댓글 수정
	@PostMapping(value="/commentUpdate")
	@ResponseBody
	public void commentUpdate(@RequestParam Map<String, String> map, @RequestParam(value="file[]", required = false) List<String> summernoteFile, HttpServletRequest req) throws Exception{
		//map: CMMT_NO(댓글 번호), CMMT_WRTER_NM(댓글 작성자 이름), CMMT_CNTNS(댓글 내용)
		
		//System.out.println(map);
		logger.debug("@@@@@@@commentUpdatemap!"+map);
		
		//작성된 댓글 내용 중 이미지 파일이 존재할 때만 실행
		if(summernoteFile!=null){
			//댓글 저장 시 임시 폴더에 저장된 파일을 최종 폴더(copy)에 복사
			summernoteFileCopy(summernoteFile, req);
		
			//System.out.println(map);
			
			//임시 폴더 경로(temp)를 최종 폴더 경로(copy)로 변경해줌
			String finalFilePath = map.get("CMMT_CNTNS").replaceAll("/commentImg/temp/", "/commentImg/copy/");
			//System.out.println(finalFilePath);
			//변경된 정보를 map에 실어서
			map.put("CMMT_CNTNS", finalFilePath);
		}
		//댓글 수정
		commentService.commentUpdate(map);		
	}	
}		
