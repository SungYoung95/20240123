package com.springproj.emotionshare.service;

import java.util.HashMap;
import java.util.List;

import com.springproj.emotionshare.domain.DiaryVO;
import com.springproj.emotionshare.domain.Diary_DiaryShareVO;

public interface DiaryService {
	
	public int saveDiary(String dTITLE, String uID, String dCONTENT);
	public int updateDiary(DiaryVO diary);
	public void deleteDiary(int dID);
	public DiaryVO getDiary(int dID);
	public List<DiaryVO> getDiaryList(String uID);
	public DiaryVO getTodayDiary(String uID);
	
	public void updateMyOldDiaryDeleteCheck(int dID);
	///////
	//diaryshare
	
	public List<Diary_DiaryShareVO> getReceivedDiaryList(String uID);
	
	public void updateReceivedDiaryReadCheck(HashMap<String,String> map);
	
	public void updateReceivedDiaryDeleteCheck(HashMap<String,String> map);
}
