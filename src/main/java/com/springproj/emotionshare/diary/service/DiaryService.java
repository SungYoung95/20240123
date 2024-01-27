package com.springproj.emotionshare.diary.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springproj.emotionshare.diary.controller.DiaryForm;
import com.springproj.emotionshare.diary.controller.DiaryReplyForm;
import com.springproj.emotionshare.diary.domain.DiaryLikeID;
import com.springproj.emotionshare.diary.domain.DiaryLikeVO;
import com.springproj.emotionshare.diary.domain.DiaryReplyLikeID;
import com.springproj.emotionshare.diary.domain.DiaryReplyLikeVO;
import com.springproj.emotionshare.diary.domain.DiaryReplyVO;
import com.springproj.emotionshare.diary.domain.DiaryShareVO;
import com.springproj.emotionshare.diary.domain.DiaryVO;
import com.springproj.emotionshare.diary.domain.Diary_DiaryShareVO;
import com.springproj.emotionshare.diary.repository.DiaryLikeRepository;
import com.springproj.emotionshare.diary.repository.DiaryReplyLikeRepository;
import com.springproj.emotionshare.diary.repository.DiaryReplyRepository;
import com.springproj.emotionshare.diary.repository.DiaryRepository;
import com.springproj.emotionshare.diary.repository.DiaryShareRepository;
import com.springproj.emotionshare.domain.UserEntity;
import com.springproj.emotionshare.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryService{

	private final DiaryRepository diaryRepository;
	
	private final DiaryShareRepository diaryShareRepository;
	
	private final DiaryReplyRepository diaryReplyRepository;
	
	private final DiaryLikeRepository diaryLikeRepository;
	
	private final DiaryReplyLikeRepository diaryReplyLikeRepository;
	
	private final UserRepository userRepository;
	// 일기 저장(사용)
	public Long saveDiary(String dTITLE, String uID, String dCONTENT, String dEMOTION, String dWEATHER, String dFILENAME) {
		//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String dDATE = sdf.format(new java.util.Date());
		LocalDateTime dDATE = LocalDateTime.now();
		Long primeDID = diaryRepository.getPrimeDID() + 1;
		DiaryVO diary = DiaryVO.builder()
									.dID(primeDID)
									.dTITLE(dTITLE)
									.dFROMID(uID)
									.dCONTENT(dCONTENT)
									.dDATE(dDATE)
									.dEMOTION(dEMOTION)
									.dWEATHER(dWEATHER)
									.dCNT(0L)
									.dLIKE(0L)
									.dSHARECHECK("NO_SHARE")
									.dDELETECHECK("NO_DELETE")
									.dFILENAME(dFILENAME)
									.build();
		log.info("insert Diary(DiaryServiceImpl):" + diary.toString());
		diaryRepository.save(diary);
		return diary.getDID();
	}

	// 일기 수정(사용)
	@Transactional
	public Long updateDiary(DiaryForm diary) {
		DiaryVO saveDiary = getDiary(diary.getDID());
		saveDiary.setDTITLE(diary.getDTITLE());
		saveDiary.setDCONTENT(diary.getDCONTENT());
		saveDiary.setDEMOTION(diary.getDEMOTION());
		saveDiary.setDWEATHER(diary.getDWEATHER());
		if(diary.getDFILENAME() != null)
		saveDiary.setDFILENAME(diary.getDFILENAME());
		diaryRepository.flush();
		
		return diary.getDID();
	}
	
	// 일기 삭제(사용)
	@Transactional
	public void deleteDiary(Long dID) {
		diaryRepository.deleteDiary(dID);
	}
	// 일기 조회(사용)
	public DiaryVO getDiary(Long dID) {
		DiaryVO findDiary = diaryRepository.getDiary(dID);
		return findDiary;
	}
	// 일기 목록 조회(사용)
	public List<DiaryForm> getDiaryList(String uID) {
		List<DiaryVO> diaryList = diaryRepository.getDiaryList(uID);
		List<DiaryForm> diaryFormList = new ArrayList<>();
		for(DiaryVO diary : diaryList) {
			DiaryForm diaryForm = new DiaryForm();
			diaryForm.setDID(diary.getDID());		
			diaryForm.setDTITLE(diary.getDTITLE());		
			diaryForm.setDFROMID(diary.getDFROMID());		
			diaryForm.setDGROUPID(diary.getDGROUPID());		
			diaryForm.setDDATE(diary.getDDATE());		
			diaryForm.setDCONTENT(diary.getDCONTENT());		
			diaryForm.setDEMOTION(diary.getDEMOTION());		
			diaryForm.setDWEATHER(diary.getDWEATHER());		
			diaryForm.setDCNT(diary.getDCNT());		
			diaryForm.setDLIKE(diary.getDLIKE());		
			diaryForm.setDSHARECHECK(diary.getDSHARECHECK());		
			diaryForm.setDDELETECHECK(diary.getDDELETECHECK());	
			
			// 이미지 파일 처리
			String oldDFILENAME = diary.getDFILENAME();
			if(oldDFILENAME != null && oldDFILENAME != "") {
				String newDFILENAME = makeImgFileDownloadURI(oldDFILENAME);
				diaryForm.setDFILENAME(newDFILENAME);	
			}else {
				diaryForm.setDFILENAME(diary.getDFILENAME());
			}
			
			
			diaryFormList.add(diaryForm);
		}
		return diaryFormList;
	}
	
	// 오늘의 일기 조회(사용)
	public DiaryForm getTodayDiary(String uID) {
		DiaryVO todayDiary = diaryRepository.getTodayDiary(uID);
		if(todayDiary == null) return null;
		DiaryForm todayDiaryForm = new DiaryForm();
		todayDiaryForm.setDID(todayDiary.getDID());		
		todayDiaryForm.setDTITLE(todayDiary.getDTITLE());		
		todayDiaryForm.setDFROMID(todayDiary.getDFROMID());		
		todayDiaryForm.setDGROUPID(todayDiary.getDGROUPID());		
		todayDiaryForm.setDDATE(todayDiary.getDDATE());		
		todayDiaryForm.setDCONTENT(todayDiary.getDCONTENT());		
		todayDiaryForm.setDEMOTION(todayDiary.getDEMOTION());		
		todayDiaryForm.setDWEATHER(todayDiary.getDWEATHER());		
		todayDiaryForm.setDCNT(todayDiary.getDCNT());		
		todayDiaryForm.setDLIKE(todayDiary.getDLIKE());		
		todayDiaryForm.setDSHARECHECK(todayDiary.getDSHARECHECK());		
		todayDiaryForm.setDDELETECHECK(todayDiary.getDDELETECHECK());		
		
		// 이미지 파일 처리
		String oldDFILENAME = todayDiary.getDFILENAME();
		if(oldDFILENAME != null && oldDFILENAME != "") {
			String newDFILENAME = makeImgFileDownloadURI(oldDFILENAME);
			todayDiaryForm.setDFILENAME(newDFILENAME);	
		}else {
			todayDiaryForm.setDFILENAME(todayDiary.getDFILENAME());
		}
		return todayDiaryForm;
	}
	
	// 이미지 파일 경로 처리
	public String makeImgFileDownloadURI(String dFILENAME) {
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dFILENAME)
                .toUriString();
		
		return fileDownloadUri;
	}
	
	@Transactional
	public void updateMyOldDiaryDeleteCheck(Long dID) {
		diaryRepository.updateMyOldDiaryDeleteCheck(dID);
	}
	
	//조회수 증가
	@Transactional
	public void updateDiaryCount(Long dID) {
		DiaryVO findDiary = getDiary(dID);
		findDiary.setDCNT(findDiary.getDCNT()+1);
	}
	
	// 일기 좋아요(사용)
	@Transactional
	public void likeDiary(String uID, Long dID) {
		DiaryLikeID likeID = new DiaryLikeID(uID, dID);
		log.info("likeDiary likeID : " + likeID.toString());
		try {
			//이미 좋아요 누름
			diaryLikeRepository.findById(likeID).get();
		}catch(Exception e) {
			//없으면 NoSuchElementException
			DiaryLikeVO diarylike = new DiaryLikeVO();
			diarylike.setId(likeID);
			log.info("likeDiary diarylike : " + diarylike.getId().toString());
			diaryLikeRepository.save(diarylike);
			
			DiaryVO findDiary = getDiary(dID);
			findDiary.setDLIKE(findDiary.getDLIKE() + 1);
		}
	}
	
	// 일기 좋아요 누른 사람 한명(사용)
	public String getLikeMan(Long dID){
		String likeman = null;
		List<String> likePeople = diaryLikeRepository.getLikePeople(dID);
		if(likePeople.size() != 0) likeman = likePeople.get(0);
		
		return likeman;
	}

	
	///////////////////////////////////
	/// diaryshare
	
	// 전체 회원 중 랜덤(사용)
	public List<String> choiceRandomUser(String username){
		UserEntity me = userRepository.findByUsername(username);
		Long myId = me.getId();
		
		List<UserEntity> userList = userRepository.findAll();
		List<String> userNameList = new ArrayList<>();
		int size = userList.size();
		
		long[] arr = new long[3];
		for(int i = 0; i < 3; i++) {
			arr[i] = (int)(Math.random() * size) + 1;
			for(int j = 0; j < i; j++) {
				if(arr[i] == arr[j] || arr[i] == myId) i--;
			}
			if(arr[0] == myId) i--;
		}
		log.info("random numbers : " + Arrays.toString(arr));
		UserEntity user1 = userRepository.findById(arr[0]).get();
		UserEntity user2 = userRepository.findById(arr[1]).get();
		UserEntity user3 = userRepository.findById(arr[2]).get();
		
		userNameList.add(user1.getUsername());
		userNameList.add(user2.getUsername());
		userNameList.add(user3.getUsername());
		
		return userNameList;
	}
	// 오늘의 일기 저장(사용)
	@Transactional
	public void shareTodayDiary(Long dID, String username) {
		List<DiaryShareVO> shareList = new ArrayList<>();
		// 유저는 USER3 USER4 USER5 (랜덤으로 뽑았다고 가정)
		List<String> randomUserList = choiceRandomUser(username);
		
		log.info("randomUserList : " + randomUserList);
		
		Long findDsCODE = diaryShareRepository.getDsCODE();
		Long findDsGROUPID = diaryShareRepository.getDsGROUPID();
		
		//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String date = sdf.format(new java.util.Date());
		LocalDateTime date = LocalDateTime.now();
		
		for(String dsID : randomUserList) {
			findDsCODE++;
			DiaryShareVO diaryShare = DiaryShareVO.builder()
												  .dsCODE(findDsCODE)
												  .dsGROUPID(findDsGROUPID + 1)
												  .dsDID(dID)
												  .dsID(dsID)
												  .dsDATE(date)
												  .dsREADCHECK("NO_READ")
												  .dsDELETECHECK("NO_DELETE")
												  .build();
			shareList.add(diaryShare);
		}
		log.info("shareList : " + shareList);
		
		saveDiaryShare(shareList);
		
		updateDiaryShareCheckANDGroupID(dID, findDsGROUPID+1);
//		diaryRepository.updateDiaryShareCheck(dID);
//		diaryRepository.updateDiaryGroupID(dID,findDsGROUPID);
	}
	// 일기 share(사용)
	public void saveDiaryShare(List<DiaryShareVO> list) {
		for(DiaryShareVO diaryShare : list) {
			diaryShareRepository.save(diaryShare);
		}
	}
	// share할때 share인원 지정 및 그룹 지정(사용)
	public void updateDiaryShareCheckANDGroupID(Long dID,Long dsGROUPID) {
		diaryRepository.updateDiaryShareCheck(dID);
		log.info("dsGROUPID (SHARESHARE) : " + dsGROUPID + ", dID : " + dID);
		diaryRepository.updateDiaryGroupID(dsGROUPID,dID);
	}
	
	// READ 체크(사용)
	@Transactional
	public void sharecheckDiary(DiaryShareVO diaryShare) {
		diaryShare.setDsREADCHECK("READ");
		diaryShareRepository.flush();
	}

	// 공유된 일기 목록 조회(사용)
	public List<Diary_DiaryShareVO> getReceivedDiaryList(String uID) {
		List<DiaryShareVO> diaryShareList = diaryShareRepository.getDiaryShareList(uID);
		log.info("diaryShareList : " + diaryShareList);
		List<Diary_DiaryShareVO> findDDShareList = new ArrayList<>();
		
		for(DiaryShareVO diaryShare : diaryShareList) {
			DiaryVO findDiary = diaryRepository.getDiary(diaryShare.getDsDID());
			log.info("ddshare.did DiaryService : " + findDiary.getDID());
			
			//shared 체크
			sharecheckDiary(diaryShare);
			
			Diary_DiaryShareVO ddshareVO = Diary_DiaryShareVO.builder()
												.dID(findDiary.getDID())
												.dTITLE(findDiary.getDTITLE())
												.dFROMID(findDiary.getDFROMID())
												.dGROUPID(findDiary.getDGROUPID())
												.dDATE(findDiary.getDDATE())
												.dCONTENT(findDiary.getDCONTENT())
												.dCNT(findDiary.getDCNT())
												.dLIKE(findDiary.getDLIKE())
												.dsREADCHECK(diaryShare.getDsREADCHECK())
												.dsDELETECHECK(diaryShare.getDsDELETECHECK())
												.build();
			// 이미지 파일 처리
			String oldDFILENAME = findDiary.getDFILENAME();
			if(oldDFILENAME != null && oldDFILENAME != "") {
				String newDFILENAME = makeImgFileDownloadURI(oldDFILENAME);
				ddshareVO.setDFILENAME(newDFILENAME);	
			}else {
				ddshareVO.setDFILENAME(findDiary.getDFILENAME());
			}
			
			
			findDDShareList.add(ddshareVO);
		}
		
		return findDDShareList;
	}
	// shared 일기 읽음처리 (사용)
	@Transactional
	public void updateReceivedDiaryReadCheck(Long dGROUPID, Long dID, String uID) {
		log.info("dGROUPID : " + dGROUPID + ", dID : " + dID + ", uID : " + uID);
		diaryShareRepository.updateReceivedDiaryReadCheck(dGROUPID, dID, uID);
	}
	// share 일기 삭제처리(사용할 예정..)
	@Transactional
	public void updateReceivedDiaryDeleteCheck(Long dGROUPID, Long dID, String uID) {
		diaryShareRepository.updateReceivedDiaryDeleteCheck(dGROUPID, dID, uID);
	}
	
	public Long getNoReadCheckSharedDiary(String username) {
		return diaryShareRepository.getNoReadCheckSharedDiary(username);
	}
	
	////////////////////////////////
	//// DiaryReply
	// 댓글 생성(사용)
	@Transactional
	public void saveDiaryReply(DiaryReplyForm form) {
		Long id = diaryReplyRepository.getMaxDRID();
		if(id == null) {
			id = 1L;
		}else {
			id++;
		}
		
		Long depth = 0L;
		Long step = 0L;
		form.setDepth(depth);
		form.setStep(step);
		log.info("DiaryReplyForm : " + form.toString());
		
		DiaryReplyVO saveDiaryReply = new DiaryReplyVO();
		saveDiaryReply.setId(id);
		saveDiaryReply.setDid(form.getDid());
		saveDiaryReply.setWriter(form.getWriter());
		saveDiaryReply.setContent(form.getContent());
		saveDiaryReply.setDate(form.getDate());
		saveDiaryReply.setLike(form.getLike());
		saveDiaryReply.setGroupnum(id);
		saveDiaryReply.setDepth(form.getDepth());
		saveDiaryReply.setStep(form.getStep());
		saveDiaryReply.setDeleteCheck(form.getDeleteCheck());
		
		diaryReplyRepository.save(saveDiaryReply);
	}
	
	// 댓글 목록 조회(사용)
	public List<DiaryReplyVO> getDiaryReplyList(Long did){
		return diaryReplyRepository.getDiaryReplyListByDID(did);
	}
	
	// 대댓글 작성
	@Transactional
	public void saveLoopDiaryReply(DiaryReplyForm form, Long rID) {
		Long id = diaryReplyRepository.getMaxDRID();
		if(id == null) {
			id = 1L;
		}else {
			id++;
		}
		DiaryReplyVO findDiaryReply = diaryReplyRepository.getDIDByRID(rID);
		Long step = diaryReplyRepository.getMaxStepReply(findDiaryReply.getGroupnum()) + 1L;
		//log.info("LoopDiaryReply (service) step : " + step);
		
		DiaryReplyVO saveDiaryReply = new DiaryReplyVO();
		saveDiaryReply.setId(id);
		saveDiaryReply.setDid(findDiaryReply.getDid());
		saveDiaryReply.setWriter(form.getWriter());
		saveDiaryReply.setContent(form.getContent());
		saveDiaryReply.setDate(form.getDate());
		saveDiaryReply.setLike(form.getLike());
		saveDiaryReply.setGroupnum(findDiaryReply.getGroupnum());
		saveDiaryReply.setDepth(form.getDepth());
		saveDiaryReply.setStep(step);
		saveDiaryReply.setDeleteCheck(form.getDeleteCheck());
		
		//log.info("saveDiaryReply" + saveDiaryReply.toString());
		diaryReplyRepository.save(saveDiaryReply);
	}
	
	// 댓글 수정(사용)
	@Transactional
	public void updateDiaryReply(String content, Long rID) {
		DiaryReplyVO updateDiaryReply = diaryReplyRepository.findById(rID).get();
		updateDiaryReply.setContent(content);
	}
	
	//댓글 삭제(사용)
	@Transactional
	public void deleteDiaryReply(Long rID) {
		DiaryReplyVO updateDiaryReply = diaryReplyRepository.findById(rID).get();
		updateDiaryReply.setContent("삭제된 댓글입니다.");
		updateDiaryReply.setDeleteCheck("DELETE");
	}
	
	// 댓글 좋아요
	@Transactional
	public void likeDiaryReply(String uID, Long rID) {
		DiaryReplyLikeID likeID = new DiaryReplyLikeID(uID, rID);
		log.info("likeReplyDiary likeID : " + likeID.toString());
		try {
			//이미 좋아요 누름
			diaryReplyLikeRepository.findById(likeID).get();
		}catch(Exception e) {
			//없으면 NoSuchElementException
			DiaryReplyLikeVO diaryReplylike = new DiaryReplyLikeVO();
			diaryReplylike.setId(likeID);
			log.info("likeDiary diarylike : " + diaryReplylike.getId().toString());
			diaryReplyLikeRepository.save(diaryReplylike);
			
			DiaryReplyVO findReply = diaryReplyRepository.findById(rID).get();
			findReply.setLike(findReply.getLike() + 1);
		}
	}
	
	// 댓글 조회(사용)
	public DiaryReplyVO getDiaryReply(Long rID) {
		return diaryReplyRepository.findById(rID).get();
	}
}
