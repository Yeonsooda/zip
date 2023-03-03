package comment.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
public class CommentDTO{
	@JsonProperty("CMMT_NO")
	private int CMMT_NO;    //댓글 번호
	
	@JsonProperty("CMMT_WRTER_NM")
	private String CMMT_WRTER_NM;    //댓글 작성자 이름
	
	@JsonProperty("CMMT_BBRD_NO")
	private int CMMT_BBRD_NO;    //댓글이 달린 게시글의 번호
	
	@JsonProperty("CMMT_CNTNS")
	private String CMMT_CNTNS;    //댓글 내용
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	@JsonProperty("CMMT_WR_DTTM")
	private Date CMMT_WR_DTTM;    //댓글 작성일(댓글 수정 시 수정일시로 update 처리됨)
}
