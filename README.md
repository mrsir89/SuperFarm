# THIS REPOSITORY IS FOR ❗️BACKEND❗️ONLY


❗️SuperFarmApplication.java 파일 맨 위에 해당 프로젝트 버전과 날짜 명시


❗️Git Clone 부터 merge 까지

git remote를 내 로컬에 복사하기(프로젝트 다운)
 $ git clone https://github.com/mrsir89/SuperFarm

'SuperFarm' 프로젝트가 복사됨, 해당 프로젝트로 이동하기
 $ cd SuperFarm 

내가 push(remote에 upload)할 branch로 이동하기
 $ git checkout 브랜치명


- 내 로컬(내 컴퓨터 드라이브)과 remote의 내 branch가 동기화 된다.
- 여기서 프로젝트 오픈하여 작업한다.
- 변경사항이 생긴다.


변경된 프로젝트를 내 branch에 upload 하기
 $ git branch                  (현재 branch 어디인지 확인!!)
 $ git status                  (상태확인, 무엇이 바뀌었는지 빨간색으로 처리됨)
 $ git add 변경된파일이름          (변경사항이 초록색으로 바뀜(staging 영역으로) -A는 전부 다)
 $ git commit -m "description" (commit message 작성, 여기서부터 merge 가능)
 $ git push                    (remote 저장소에 upload 됨. Test가능하고 기능구현 완전한 버전만 신중히 push)  
