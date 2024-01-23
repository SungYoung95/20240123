package com.springproj.emotionshare.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DiaryVO {
	
	private int dID; 		 // 글아이디, 전화번호(uTel)+sysdate, 테스트는 전화번호만	
	private String dTITLE;   // 글제목 NOT NULL
	private String dFROMID;  // 보내는사람, 사용자아이디(uID), NOT NULL
	private int dGROUPID;    // 받는 그룹 번호, 그룹 번호(dsGROUPID)
	private String dDATE;      // 작성날짜, NOT NULL , java.sql.Date는 시분초 표기안됨.. 그래서 String씀
	private String dCONTENT; // 글내용, NOT NULL
	private int dEMOTION;    // 감정, 0:즐거움 1:슬픔...
	private int dWEATHER;    // 날씨, 0:맑음 1:흐림...
	private int dCNT;        // 조회수, DEFAULT:0
	private int dLIKE;       // 추천수, DEFAULT:0
	private int dDELETECHECK;  // 송신자 삭제 체크, DEFAULT:0 보유 1:삭제
}
