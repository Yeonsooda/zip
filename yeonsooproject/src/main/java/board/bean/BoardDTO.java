package board.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
public class BoardDTO {
	@JsonProperty("BBRD_NO")
	private int BBRD_NO;    //게시글 번호
	
	@JsonProperty("BBRD_WRTER_NM")
	private String BBRD_WRTER_NM;    //게시글 작성자 이름
	
	@JsonProperty("BBRD_TTL")
	private String BBRD_TTL;    //게시글 제목
	
	@JsonProperty("BBRD_CNTNS")
	private String BBRD_CNTNS;    //게시글 내용
	
	@JsonProperty("BBRD_WR_DTTM")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date BBRD_WR_DTTM;    //게시글 작성일(게시글 수정 시 수정일시로 update 처리됨)
}
