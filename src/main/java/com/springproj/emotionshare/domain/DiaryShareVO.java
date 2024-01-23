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
public class DiaryShareVO {
	private int    dsCODE; 		  // 그냥 기본키, 등록될때마다 1씩 증가
	private int    dsGROUPID; 	  // 수신자들 그룹번호
	private int    dsDID;		  // 일기 아이디(dID)
	private String dsID;		  // 사용자 아이디(uID)
	private String dsDATE;		  // 등록 시간
	private int    dsREADCHECK;   // 수신자 읽음체크, DEFAULT:0 안읽음
	private int    dsDELETECHECK; // 수신자 삭제체크, DEFAULT:0 보유 1:삭제
}
