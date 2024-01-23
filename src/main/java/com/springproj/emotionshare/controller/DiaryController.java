package com.springproj.emotionshare.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springproj.emotionshare.domain.DiaryVO;
import com.springproj.emotionshare.domain.Diary_DiaryShareVO;
import com.springproj.emotionshare.service.DiaryService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DiaryController {
	
	@Autowired
	private DiaryService diaryService;
	
	// 과거 나의 일기 목록
	@GetMapping("/diary/get/{uID}")
	@ResponseBody
	public List<DiaryVO> getDiary(@PathVariable("uID") String uID) {
		log.info("uID(Controller): "+uID);
		List<DiaryVO> findDiarys = diaryService.getDiaryList(uID);
		log.info(findDiarys.toString());
		return findDiarys;
	}
	
	// 과거 나의 일기 상세
	@GetMapping("/diary/into/{dID}")
	@ResponseBody
	public DiaryVO intoDiary(@PathVariable("dID") int dID) {
		log.info("dID(Controller): " + dID);
		DiaryVO findDiary = diaryService.getDiary(dID);
		return findDiary;
	}
	
	// 과거 나의 일기 삭제
	@DeleteMapping("/diary/into/delete")
	public void deleleMyOldDiary(@RequestParam("dID") int dID) {
		log.info("dID(deleteMyOldDiary Controller):" + dID);
		diaryService.updateMyOldDiaryDeleteCheck(dID);
	}
	
	// 오늘 나의 일기 작성
	@PostMapping("/diary/write")
	public void insertDiary(@RequestParam("title") String dTITLE 
							,@RequestParam("content") String dCONTENT
							,HttpServletRequest request) {
		//log.info("title:" + dTITLE + ", content:" + dCONTENT);
		//log.info("오늘의 일기 등록 완료");
		String uID = (String)request.getSession().getAttribute("userID");
		diaryService.saveDiary(dTITLE, uID, dCONTENT);
	}
	
	// 오늘 나의 일기 조회
	@GetMapping("/diary/today")
	public DiaryVO getTodayDiary(HttpServletRequest request) {
		String uID = (String)request.getSession().getAttribute("userID");
		log.info("uID(Controller.todayDiary):" + uID);
		DiaryVO todayDiary = diaryService.getTodayDiary(uID);
		return todayDiary;
	}
	
	// 오늘 나의 일기 수정
	@PutMapping("/diary/today/update")
	public void updateTodayDiary(@RequestParam("dID") int dID,
								@RequestParam("dTITLE") String dTITLE,
								@RequestParam("dCONTENT") String dCONTENT) {
		//log.info("dID(updateTodayDiary : Controller ):" + dID);
		//log.info("dTITLE(updateTodayDiary : Controller ):" + dTITLE);
		//log.info("dCONTENT(updateTodayDiary : Controller ):" + dCONTENT);
		DiaryVO diary = diaryService.getDiary(dID);
		diary.setDTITLE(dTITLE);
		diary.setDCONTENT(dCONTENT);
		//log.info("diary(updateTodayDiary : Controller ):" + diary.toString());
		diaryService.updateDiary(diary);
	}
	
	// 오늘 나의 일기 삭제
	@DeleteMapping("/diary/today/delete")
	public void deleteTodayDiary(@RequestParam("dID") int dID) {
		//log.info("dID(deleteTodayDiary : Controller ) :" + dID);
		diaryService.deleteDiary(dID);
	}
	
	///////////////////////
	//diaryshare
	
	// 수신 받은 읽기 목록 조회
	@GetMapping("/diary/received/{uID}")
	@ResponseBody
	public List<Diary_DiaryShareVO> getReceivedDiarys(@PathVariable("uID") String uID){
		//log.info("uID(Controller):" + uID);
		List<Diary_DiaryShareVO> receivedDiarys = diaryService.getReceivedDiaryList(uID);
		//log.info(receivedDiarys.toString());
		return receivedDiarys;
	}
	
	// 수신 받은 일기 상세
	@GetMapping("/diary/received/into/{dID}")
	@ResponseBody
	public DiaryVO receivedIntoDiary(@PathVariable("dID") int dID,
									HttpServletRequest request) {
		log.info("dID(receivedIntoDiary : Controller): " + dID);
		DiaryVO findDiary = diaryService.getDiary(dID);
		
		// 읽기 체크
		HashMap<String,String> map = new HashMap<>();
		String uID = (String)request.getSession().getAttribute("userID");
		map.put("uID", uID);
		map.put("dGROUPID", String.valueOf(findDiary.getDGROUPID()));
		map.put("dID", String.valueOf(dID));
		//log.info("map:" + map.toString());
		diaryService.updateReceivedDiaryReadCheck(map);
		return findDiary;
	}
	
	// 수신 받은 일기 삭제체크
	@DeleteMapping("/diary/received/into/delete")
	public void deleteReceivedDiary(@RequestParam("dID") int dID,
									HttpServletRequest request) {
		log.info("dID(deleteReceivedDiary : Controller): " + dID);
		DiaryVO findDiary = diaryService.getDiary(dID);
		
		// 삭제 체크
		HashMap<String,String> map = new HashMap<>();
		String uID = (String)request.getSession().getAttribute("userID");
		map.put("uID", uID);
		map.put("dGROUPID", String.valueOf(findDiary.getDGROUPID()));
		map.put("dID", String.valueOf(dID));
		diaryService.updateReceivedDiaryDeleteCheck(map);
	}
}
