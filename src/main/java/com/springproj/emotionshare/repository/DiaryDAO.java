package com.springproj.emotionshare.repository;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproj.emotionshare.domain.DiaryVO;
import com.springproj.emotionshare.domain.Diary_DiaryShareVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DiaryDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 일기 등록
	public void insert(DiaryVO diary) {
		mybatis.insert("insertDiary", diary);
	}
	
	// 해당 유저가 작성한 모든 일기 조회
	public List<DiaryVO> getDiaryList(String uID){
		return mybatis.selectList("getDiaryList",uID);
	}
	
	// 해당 일기 조회
	public DiaryVO getDiary(int dID) {
		log.info("dID(DAO):" + dID);
		return mybatis.selectOne("getDiary",dID);
	}
	
	// 일기 수정
	public void updateDiary(DiaryVO diary) {
		mybatis.update("updateDiary",diary);
	}
	
	// 일기 삭제
	public void deleteDiary(int dID) {
		mybatis.delete("deleteDiary",dID);
	}
	
	// 일기 최신 번호 조회
	public int getPrimeDID() {
		return mybatis.selectOne("getPrimeDID");
	}
	
	// 오늘의 일기 조회
	public DiaryVO getTodayDiary(String uID) {
		return mybatis.selectOne("getTodayDiary", uID);
	}
	
	// 과거 나의 일기 삭제체크
	public void updateMyOldDiaryDeleteCheck(int dID) {
		mybatis.update("updateMyOldDiaryDeleteCheck", dID);
	}
	
	////////////////////////
	//diaryshare
	
	// 수신된 일기 목록 조회
	public List<Diary_DiaryShareVO> getReceivedDiaryList(String uID){
		return mybatis.selectList("getReceivedDiaryList",uID);
	}
	
	// 수신된 일기 읽기체크
	public void updateReceivedDiaryReadCheck(HashMap<String,String> map) {
		mybatis.update("updateReceivedDiaryReadCheck", map);
	}
	
	// 수신된 일기 삭제체크
	public void updateReceivedDiaryDeleteCheck(HashMap<String,String> map) {
		mybatis.update("updateReceivedDiaryDeleteCheck",map);
	}
}
