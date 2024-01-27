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

// 긴급수정 내용 
>> 일기 작성이 안됨!!!!!

// 수정

DiaryService 55줄 내용

Long primeDID = diaryRepository.getPrimeDID() + 1;

-->
 
Long primeDID = 0L;
if(diaryRepository.getPrimeDID() == null) { primeDID = 0L;}
else {primeDID = diaryRepository.getPrimeDID() + 1;}
