package com.springproj.emotionshare.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.springproj.emotionshare.domain.DiaryVO;
import com.springproj.emotionshare.domain.Diary_DiaryShareVO;

@Mapper
public interface DiaryMapper {
	
	// 오늘 나의 일기 작성
	@Insert("INSERT INTO DIARY(dID,dTITLE,dFROMID,dDATE,dCONTENT) VALUES(#{dID},#{dTITLE},#{dFROMID},TO_DATE(#{dDATE},'YYYY.MM.DD HH24:MI:SS'),#{dCONTENT})")
	public void insertDiary(DiaryVO diary);
	
	// 오늘 나의 일기 수정(제목, 내용)
	@Update("UPDATE DIARY SET dTITLE= #{dTITLE},dCONTENT = #{dCONTENT} WHERE dID = #{dID}")
	public void updateDiary(DiaryVO diary);
	
	// 오늘 나의 일기 삭제
	@Delete("DELETE FROM DIARY WHERE dID = #{dID}")
	public void deleteDiary(int dID);
	
	// 과거 나의 일기 삭제(하는척)
	@Update("UPDATE DIARY SET dDELETECHECK = 1 where dID = #{dID}")
	public void updateMyOldDiaryDeleteCheck(int dID);
	
	// 과거 나의 일기 목록 조회
	@Select("SELECT * FROM DIARY WHERE dFROMID = #{dFROMID} AND dDELETECHECK <> 1 ORDER BY dID DESC")
	public List<DiaryVO> getDiaryList(String uID);
	
	// 일기 상세 조회
	@Select("SELECT * FROM DIARY WHERE dID = #{dID}")
	public DiaryVO getDiary(int dID);
	
	// 일기 최신번호 조회(새로운 일기 등록시 최신번호 입력을 위한)
	@Select("SELECT Max(dID) FROM DIARY")
	public int getPrimeDID();
	
	// 오늘 작성한 일기 조회
	@Select("SELECT * FROM DIARY WHERE TO_CHAR(dDATE,'YYYYMMDDHH24MISS') > TO_CHAR(TRUNC(sysdate),'YYYYMMDDHH24MISS') AND dFROMID = #{uID}")
	public DiaryVO getTodayDiary(String uID);
	
	/////////////////////////
	// DIARY_SHARE
	
	// 글번호(dID) 해당하는 글을 수신받은 유저들 아이디 목록 조회
	@Select("SELECT DIARY_SHARE.dsID FROM DIARY, DIARY_SHARE WHERE DIARY.dID = DIARY_SHARE.dsDID AND dID = #{dID}")
	public List<String> getGroupUserIDs(int dID);
	
	// 수신 받은 일기 목록 조회
	@Select("SELECT * FROM DIARY, DIARY_SHARE WHERE DIARY.dGROUPID = DIARY_SHARE.dsGROUPID AND DIARY_SHARE.dsID = #{uID} AND DIARY_SHARE.dsDELETECHECK <> 1 ORDER BY DIARY.dID DESC")
	public List<Diary_DiaryShareVO> getReceivedDiaryList(String uID);
	
	// 수신 받은 일기 읽기체크 수행
	@Update("UPDATE DIARY_SHARE SET dsREADCHECK = 1 WHERE dsGROUPID = #{dGROUPID} AND dsDID  = #{dID} AND dsID = #{uID}")
	public void updateReceivedDiaryReadCheck(HashMap<String,String> map);
	
	// 수신 받은 일기 삭제체크 수행
	@Update("UPDATE DIARY_SHARE SET dsDELETECHECK = 1 WHERE dsGROUPID = #{dGROUPID} AND dsDID  = #{dID} AND dsID = #{uID}")
	public void updateReceivedDiaryDeleteCheck(HashMap<String,String> map);
}
