# Git

## Git?

- (분산)버전 관리 프로그램
- 최종파일과 변경사항들만을 저장
- 백업, 복구, 협업이 가능

### 분산?

- 서버와 당사자들 각각 저장하기 때문에 분산 버전 관리 프로그램
- 화제등과 같은 사고에서 안전하다

## 구성

---

### **로컬영역(내컴퓨터)**

#### working area

- 지정된 폴더(바탕화면에 만든 폴더같은)
- git add(명령어)로 staging area로 보낼수 있음
- 한번이라도 add를 한 파일은 git이 관리함(tracked file)
- 새로 생성한 파일을 untracked file이라고 부름
- 수정여부에 따라 modified file, unmodified file로 분류

#### staging area

- git commit(명령어)로 git directory로 보낼수 있음

#### git directory

- git push(명령어)로 remote repo로 보낼수 있음

## 명령어

---

기본적인 명령어는 일반적인 CLI와 같음
  
  ```git
  $ git init
  ```
 - 현재 디렉토리를 Git으로 관리한다는 초기화 명령어
  
  ```git
  $ git status
  ```
 - 말그대로 현재 상태를 볼수 있음, 어떤파일이 올라가 있는지 tracked file인지 등
  
  ```git
  $ git add (file)
  ```
 - working area의 파일을 staging area로 올림
  
  ```git
  $ git rm --cached (file)
  ```
 - staging area의 파일을 working area로 완전히 이동시킴(untracked file로 변환)
  
  ```git
  $ .gitignore
  ```
 - 원하는 파일을 예외처리해 add나 commit에서 제외할 수 있음
  
  ```git
  $ git commit -m "commit log message"
  ```
 - git directory로 이동시킴 
  
  ```git
  $ git log
  ```
 - commit한 기록을 볼수 있음
 ```git
 $ git push origin master
 $ git push -u origin master
 ```
 - 원격저장소로 push
 - origin은 저장소 이름, master는 브렌치 이름
 - -u를 붙여서 push했을 때 다음 push부턴 저장소 이름과 브렌치 이름을 생략가능
 ```git
 $ git remote add origin (url)
 ```
 - origin이라는 이름으로 url의 원격저장소를 추가
 ```git
 $ git remote -v
 ```
- 원격저장소 조회
```git
$ git remote rm origin
```
- origin이라는 이름의 원격저장소 삭제

# Github (remote repo, 원격저장소)

- 외부 저장소
- GitLab, GitBucket도 존재

```git
$ git clone (url)
```
- 복사해서 가져옴
- init 및 add가 이미 되어있음
- 처음 한번만 하면됨
```git
$ git pull origin master
```
- origin이라는 원격저장소에서 master라는 브렌치를 가져온다