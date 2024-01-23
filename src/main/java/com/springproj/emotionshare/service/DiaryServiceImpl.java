package com.springproj.emotionshare.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproj.emotionshare.domain.DiaryVO;
import com.springproj.emotionshare.domain.Diary_DiaryShareVO;
import com.springproj.emotionshare.repository.DiaryDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	private DiaryDAO diaryDAO;
	
	@Override
	public int saveDiary(String dTITLE, String uID, String dCONTENT) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dDATE = sdf.format(new java.util.Date());
		int primeDID = diaryDAO.getPrimeDID();
		DiaryVO diary = DiaryVO.builder()
									.dID(primeDID + 1)
									.dTITLE(dTITLE)
									.dFROMID(uID)
									.dCONTENT(dCONTENT)
									.dDATE(dDATE)
									.build();
		log.info("insert Diary(DiaryServiceImpl):" + diary.toString());
		diaryDAO.insert(diary);
		return diary.getDID();
	}

	@Override
	public int updateDiary(DiaryVO diary) {
		diaryDAO.updateDiary(diary);
		return diary.getDID();
	}

	@Override
	public void deleteDiary(int dID) {
		diaryDAO.deleteDiary(dID);
	}

	@Override
	public DiaryVO getDiary(int dID) {
		DiaryVO findDiary = diaryDAO.getDiary(dID); 
		return findDiary;
	}

	@Override
	public List<DiaryVO> getDiaryList(String uID) {
		List<DiaryVO> diaryList = diaryDAO.getDiaryList(uID);
		return diaryList;
	}
	
	
	@Override
	public DiaryVO getTodayDiary(String uID) {
		DiaryVO todayDiary = diaryDAO.getTodayDiary(uID);
		return todayDiary;
	}
	
	
	@Override
	public void updateMyOldDiaryDeleteCheck(int dID) {
		diaryDAO.updateMyOldDiaryDeleteCheck(dID);
	}

	
	///////////////////////////////////
	/// diaryshare
	


	@Override
	public List<Diary_DiaryShareVO> getReceivedDiaryList(String uID) {
		List<Diary_DiaryShareVO> receivedDiaryList = diaryDAO.getReceivedDiaryList(uID);
		return receivedDiaryList;
	}

	@Override
	public void updateReceivedDiaryReadCheck(HashMap<String,String> map) {
		diaryDAO.updateReceivedDiaryReadCheck(map);
	}

	@Override
	public void updateReceivedDiaryDeleteCheck(HashMap<String, String> map) {
		diaryDAO.updateReceivedDiaryDeleteCheck(map);
	}
	
}
