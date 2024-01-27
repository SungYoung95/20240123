# emotion-share-diary

# version eleven 옮긴 파일 리스트

emotionshare.diary.* 패키지들

static에 있는 업로드 폴더 및 파일

view diary폴더 내 jsp파일들

pom.xml 설정 dependency 1개 업로드 추가

application.properties 설정 멀티파트 부분

maincontroller 세션 설정 /main 부분

index 페이지 링크 수정 왼쪽 서브메뉴 글씨 수정

########### 사진이 안떠요!! 파일 업로드가 안됨!!!
application.properties 멀티파트 경로 설정 2개 해야함

예시)
C:/workspaces/git/20240123/src/main/resources/static/upload

/workspaces/git/20240123/src/main/resources/static/upload

// 긴급수정필요 내용
// 일기 작성이 안됨!!!!!

// 수정

DiaryService 55줄 내용

Long primeDID = diaryRepository.getPrimeDID() + 1;

-->
 
Long primeDID = 0L;
if(diaryRepository.getPrimeDID() == null) { primeDID = 0L;}
else {primeDID = diaryRepository.getPrimeDID() + 1;}

// 일기 공유가 안됨!!!!!

DiaryService 258줄 내용

Long findDsCODE = diaryShareRepository.getDsCODE();
if(findDsCODE == null) findDsCODE = 0L;
Long findDsGROUPID = diaryShareRepository.getDsGROUPID();
if(findDsGROUPID == null) findDsGROUPID = 0L;

// 공유되는 번호 로직 수정

DiaryService 221줄 내용

choiceRandomUser메서드 아래처럼 변경

// 전체 회원 중 랜덤(사용) 
	public List<String> choiceRandomUser(String username){
		UserEntity me = userRepository.findByUsername(username);
		Long myId = me.getId();
		
		List<UserEntity> userList = userRepository.findAll();
		List<String> userNameList = new ArrayList<>();
		int size = userList.size();
		
		int myIndex = 0;
		for(UserEntity user : userList) {
			if(user.getId() == myId) {break;}
				myIndex++;
		}
		
		long[] arr = new long[3];
		for(int i = 0; i < 3; i++) {
			arr[i] = (int)(Math.random() * size);
			for(int j = 0; j < i; j++) {
				if(arr[i] == arr[j] || arr[i] == myIndex) i--;
			}
			if(arr[0] == myId) i--;
		}
		log.info("random numbers : " + Arrays.toString(arr));
		UserEntity user1 = userList.get((int)arr[0]);
		UserEntity user2 = userList.get((int)arr[1]);
		UserEntity user3 = userList.get((int)arr[2]);
		
		userNameList.add(user1.getUsername());
		userNameList.add(user2.getUsername());
		userNameList.add(user3.getUsername());
		
		return userNameList;
	}
