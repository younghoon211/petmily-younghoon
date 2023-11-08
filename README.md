# 🐶 Petmily 🐶
## 🐱 사지 말고, 입양하세요! (Don,t buy, Do Adopt!) 🐱
<br>

# 개발 목적
- Petmily는 유기동물 입양 장려 웹 서비스입니다.
  - 한 해에 발생되는 유기동물은 약 13만마리입니다. 그 중 40%는 사망하고, 16%는 안락사됩니다.
  - 이러한 가슴아픈 일들을 조금이나마 줄여갈 수 있도록, 유기동물 입양 장려 사이트를 개발하게 되었습니다. 🙂
<br>

# 개발 환경

### Language
<img src="https://img.shields.io/badge/Java(JDK 11)-orange?style=for-the-badge&logo=java"/>

### Framework
<img src="https://img.shields.io/badge/SpringBoot 2.7.2-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"> <img width="98" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/03e461e6-990b-4791-abb7-f597fa92210c">

### Front
<img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=Html5&logoColor=white"/> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"/> <img width="65" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/01d3e947-e421-4627-b30d-3f1c8f2e1906">


### DB
<img src="https://img.shields.io/badge/Oracle-red?style=for-the-badge&logo=Oracle&logoColor=white"/>
<br>

# Diagram
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/039602e2-26d4-4119-a7dd-b5965738a901">
<br>
<br>
<br>

# 기능
- [1. 로그인](#-1-로그인-)
- [2. 회원가입](#-2-회원가입-)
- [3. 아이디 찾기](#-3-아이디-찾기-)
- [4. 비밀번호 찾기](#-4-비밀번호-찾기-)
- [5. 유기동물 조회](#-5-유기동물-조회-)
  - [5.1. 후원 신청](#51-후원-신청)
  - [5.2. 입양/임시보호 신청](#52-입양임시보호-신청)
  - [5.3. 봉사 신청](#53-봉사-신청)
- [6. 반려동물 찾아요/유기동물봤어요](#-6-반려동물-찾아요--유기동물-봤어요)
  - [6.1. 반려동물 찾아요](#61-반려동물-찾아요)
  - [6.2. 유기동물 봤어요](#62-유기동물-봤어요)
  - [6.3. 매칭기능 예시](#63-매칭-기능-예시)
- [7. 입양완료 조회](#%EF%B8%8F-7-입양완료-조회-%EF%B8%8F)
- [8. 게시판](#-8-게시판-)
  - [8.1. 자유&문의게시판](#81-자유문의게시판)
    - [8.1.1. 공개/비공개 설정](#811-공개비공개-설정)
    - [8.1.2. 댓글 작성 예시](#812-댓글-작성-예시)
  - [8.2. 입양후기 게시판](#82-입양후기-게시판)
- [9. 마이페이지](#-9-마이페이지-)
  - [9.1. 입양/임보 신청내역](#91-입양임보-신청내역)
  - [9.2. 내가 쓴 게시글](#92-내가-쓴-게시글)
  - [9.3. 찾아요/봤어요 매칭 결과](#93-찾아요봤어요-매칭-결과)
    - [9.3.1. 매칭된 찾아요 게시글](#931-매칭된-찾아요-게시글)
    - [9.3.2. 매칭된 봤어요 게시글](#932-매칭된-봤어요-게시글)
  - [9.4. 회원정보 변경](#94-회원정보-변경)
  - [9.5. 비밀번호 변경](#95-비밀번호-변경)
  - [9.6. 회원 탈퇴](#96-회원-탈퇴)
- [10. 관리자 페이지](#-10-관리자-페이지-)
  - [10.1. 회원정보 관리](#101-회원정보-관리)
  - [10.2. 유기동물 관리](#102-유기동물-관리)
  - [10.3. 게시글 관리](#103-게시글-관리)
  - [10.4. 입양&임시보호 관리](#104-입양임시보호-관리)
  - [10.5. 후원 관리](#105-후원-관리)
  - [10.6. 보호소 관리](#106-보호소-관리)
- [11. 그 외 기능](#-11-그-외-기능-)

<br>
<br>

# 🦊 1. 로그인 🦊
<img width="300" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/37e94cea-ba7d-4ccf-8438-631cc6ed9ac9">☜ `Click here!`


- 잘못된 아이디 또는 비밀번호 입력 시 경고 문구가 표시되고, 아이디 값은 유지된 채 비밀번호 입력창에 포커스됩니다.
- 아이디 미입력 시 경고 문구가 표시되고, 아이디 입력창에 포커스됩니다.
- 비밀번호 미입력 시 경고 문구가 표시되고, 비밀번호 입력창에 포커스됩니다.
- 로그인 후 원래 존재하던 페이지로 이동됩니다.
  - ex) 자유게시판에서 로그인 → 홈 화면이 아닌 자유게시판으로 돌아옴
- 권한이 필요한 요청에 비로그인 사용자가 접근 시도 시 로그인 화면으로 이동됩니다.
  - 로그인이 완료되면 접근하려던 페이지로 정상 이동됩니다.
  - ex) 비로그인 사용자가 글쓰기 시도 시: 로그인 페이지 → 로그인 완료 → 글쓰기 페이지
    
<br>
<br>
<br>

# 🐮 2. 회원가입 🐮
- 사용자의 편의성을 위해 입력과 동시에 값 검증이 이루어지는 ‘input’함수 방식을 채택했습니다.
<br>

<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/f7ac5009-1926-4f52-b4ea-57e123bcc918">☜ `Click here!`

`아이디`
- 3-15자 이내어야 합니다.
- 소문자, 숫자만 입력 가능합니다.
<br>

`비밀번호`
- 8-16자 이내어야 합니다.
- 영문+숫자+특수문자만 입력 가능합니다.
<br>

`비밀번호 확인`
- 8-16자 이내어야 합니다.
- 비밀번호와 값이 일치해야 합니다.
- 비밀번호와 확인이 일치해야만 제출 가능합니다.
<br>

`닉네임`
- 3-15자 이내어야 합니다.
- 한글, 영문, 숫자만 입력 가능합니다.
<br>

<img width="850" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/e0127883-b62e-493e-ad6e-15be725cc577">☜ `Click here!`

`생년월일`
- 1900년-2023년 이내어야 합니다.
- 유효하지 않은 날짜는 입력 불가합니다.(ex: 2월 31일)
<br>

`성별`
- 남자, 여자 옵션 중 선택할 수 있습니다.
<br>

`이메일`
- 5-30자 이내어야 합니다.
- 골뱅이(@)와 점(.)은 필수입니다.
<br>

`이메일 인증`
- 이메일 입력 후 ‘인증하기’버튼 클릭 시 메일이 전송됩니다.
    - 메일이 전송되면 인증코드 입력창이 생성됩니다.
- 올바른 값 입력 시 인증이 완료됩니다.
    - 인증이 완료되면 인증코드 입력창이 수정 불가하도록 변경됩니다.
<br>

`연락처`
- ‘010’으로 시작해야 합니다.
- 입력값은 11자리어야 합니다.

<br>
<br>
<br>

# 🦁 3. 아이디 찾기 🦁
<div>
<img width="250" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/01099a99-e4dc-4290-ad9b-0c92e6ee4ec9" style="width:25%; height: 250px">
<img width="391" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/2c4252fd-fbf9-4f33-b2b8-fc5907c61c61" style="width:40%; height: 250px">
</div>

- 유효한 이메일 입력 시, 해당 이메일로 아이디가 전송됩니다.

<br>
<br>
<br>

# 🐯 4. 비밀번호 찾기 🐯
<div>
<img width="250" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/c8dfc14d-6283-405c-abab-58eb4c36bf35" style="width:25%; height: 250px">
<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/faa935fb-99eb-43e4-98de-f6607f0c573f" style="width:40%; height: 250px">
</div>

- 유효한 아이디와 이메일 입력 시, 해당 이메일로 인증코드가 전송됩니다.

<br>

<img width="250" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/135bb3a2-e495-49a2-b4d4-325473ee83d7">

<br>

- 인증을 마치면, 아래에 새 비밀번호 입력창이 생깁니다.
  - 인증코드 입력창은 수정 불가하도록 변경됩니다.
- 비밀번호, 비밀번호 확인의 입력값 조건은 회원가입 시와 동일합니다.

<br>
<br>
<br>

# 🐨 5. 유기동물 조회 🐨

- 보호소에서 보호중이거나 임시보호중인 동물들을 조회하는 게시판입니다.
- 유기동물의 종, 품종, 나이, 체중, 발견 장소, 보호중인 보호소, 입소 날짜 등의 정보 조회가 가능합니다.
- 상세 페이지에서 해당 동물에게 후원, 입양/임시보호, 봉사가 가능합니다. (회원만 가능)
- 최신순, 오래된순 정렬이 가능합니다.
 
<br>

<div>
<img width="550" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/b8593037-0067-47f6-b6bb-2b4c4fd67ee0" style="width:45%; height: 400px"> 
<img width="550" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/d13361f7-da31-4fb2-9ccc-045aac4c1535" style="width:45%; height: 400px">
</div>
<br>

<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/cf5c97af-e4b7-48d0-b5d1-f0cdb3f00fa4">

- 조건부 검색과 키워드 검색이 가능합니다.
  - 제목, 내용, 품종, 실종 장소 중 하나라도 포함되어 있다면 해당 정보가 검색됩니다.
  
<br>

## 5.1 후원 신청
<img width="700" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/f0ff7ef8-0002-4894-b202-7e7b88bc6fc6" style="height: 500px">
<br>
<img width="200" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/718fed5b-fd63-4b5b-8533-5897730ab9b5">

<br>

- 후원 가능한 최소 금액은 10,000원입니다.
    - 10,000원 미만값 입력시 경고 문구와 함께 제출이 거절됩니다.
- 후원 금액 직접 입력 칸에 값 입력 시 체크가 해제되고, 반대로 체크할 시에는 입력값이 초기화됩니다.

<br>

## 5.2 입양/임시보호 신청

- 임시보호 신청 처리중이거나 임시보호중인 동물은 입양 신청만 가능합니다.
- 이미 입양 신청 처리중인 동물은 임시보호/입양 신청 둘 다 불가합니다.
- 입양/임시보호 진행 현황은 [마이페이지](#91-입양임보-신청내역)에서 확인 가능합니다.

<br>

<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/3cac848e-9a5d-4b9e-9403-f30794c0db53">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/2ac6c845-905c-45af-b1b1-6e8f6e218d38">

- 보호중인 동물은 임시보호 또는 입양 선택 후 신청 가능합니다.
  - 선택 창에서 임시보호 체크 시 시작 날짜, 기간 입력창이 생성됩니다.
  - 임시보호는 최소 1개월 이상만 가능합니다.

<br>

## 5.3 봉사 신청

- 보호소에 보호중인 동물에게만 봉사 가능합니다.
  - 임시보호중인 동물은 불가합니다.
- 신청서 작성 절차는 따로 없으며, 해당 보호소에 연락해 일정 조율 후 방문하시면 됩니다.

<img width="600" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/ba0ab47d-34ee-489b-81b2-d2bd0d892d93">

<br>
<br>
<br>
<br>

# 🐼 6. 반려동물 찾아요 / 유기동물 봤어요

- 찾아요, 봤어요 게시글 작성 시 종, 품종, 실종 장소가 일치하면 매칭됩니다.
  - 글 작성자가 서로 다를 경우에만 매칭됩니다.
  - 매칭된 게시글은 [마이페이지](#93-찾아요봤어요-매칭-결과)에서 확인 가능합니다.
- 최신순, 오래된순, 조회순 정렬이 가능합니다.
- 글 작성 시 이미지파일 업로드가 가능합니다.
  - 이미지파일을 업로드하지 않을 시 기본 이미지가 적용됩니다.
  - 이미지파일 삭제 시 서버의 데이터에서도 삭제됩니다.

<br>

## 6.1 반려동물 찾아요

- 글 작성 시 봤어요 게시판의 종, 품종, 실종 장소와 일치하는 글이 있으면 ‘상태: 매칭됨’으로,  없으면 ‘상태: 실종’으로 작성됩니다.
  - 매칭된 글을 수정할 때 종, 품종, 실종 장소 중 하나라도 변경할 시, ‘실종’ 상태로 되돌아갑니다.

<br>

<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/1012b5c7-40ee-4860-b5bc-1d7e9238fff1">

- 조건부 검색과 키워드 검색이 가능합니다.
  - 제목, 내용, 품종, 실종 장소 중 하나라도 포함되어 있다면 해당 정보가 검색됩니다.
  
<br>

## 6.2 유기동물 봤어요

- 글 작성 시 찾아요 게시판의 종, 품종, 실종 장소와 일치하는 글이 있으면 ‘상태: 매칭됨’으로,  없으면 ‘상태: 보호’로 작성됩니다.
  - 매칭된 글을 수정할 때 종, 품종, 실종 장소 중 하나라도 변경할 시, ‘보호’ 상태로 되돌아갑니다.

<br>

<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/d34c5f23-df81-4a45-8679-8d71cbf9a636">

- 조건부 검색과 키워드 검색이 가능합니다.
  - 제목, 내용, 품종, 발견 장소 중 하나라도 포함되어 있다면 해당 정보가 검색됩니다.

<br>

## 6.3 매칭 기능 예시
#### 아래의 예시는 찾아요가 봤어요에 매칭되는 예시입니다.(반대도 가능)

<br>

### (1) 봤어요 게시판에 비숑을 보호중인 글을 올림
<div>
<img width="600" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/0d71a82b-de1a-49ba-8040-c955e41a8eee" style="width:60%; height: 400px">
</div>

<br>

### (2) 찾아요 게시판에 봤어요 게시판의 종, 품종, 발견 장소와 일치하는 조건의 글을 올림 (올리는 즉시 매칭됨)
<div>
<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/b7cd6d2d-9d71-4973-bbf6-f85cbed62e59" style="width:35%; height: 380px">
<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/ea32a0a5-b8c9-4070-8c11-905602b310e7" style="width:55%; height: 380px">
</div>

<br>

### (3) 봤어요 게시판의 게시글도 매칭 상태로 바뀜
<div>
<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/bc1cc3f2-4a84-45c9-b1f0-bc6a28540086" style="width:35%; height: 350px">
<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/4e199454-1a70-46f0-a3d1-256b77595ea6" style="width:55%; height: 350px">
</div>

<br>
<br>
<br>

# 🐻‍❄️ 7. 입양완료 조회 🐻‍❄️
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/a6b60b48-65ca-4458-9551-2ddf38381eb0" style="width:60%; height: 500px">
<br>

- 최신순, 오래된순, 조회순 정렬이 가능합니다.
<br>

<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/a5b9c725-034d-45a5-b716-dfb340efdb9d">

- 조건부 검색과 키워드 검색이 가능합니다.
  - 이름, 품종, 나이, 실종됐던 장소 중 하나라도 포함되어 있다면 해당 정보가 검색됩니다.

<br>
<br>
<br>

# 🐻 8. 게시판 🐻
- 로그인 한 회원만 글 작성이 가능합니다.
- 최신순, 오래된순, 조회순 정렬이 가능합니다.
- 조건부 키워드 검색이 가능합니다.
  - 제목, 내용, 제목+내용, 작성자로 가능합니다.
- 자유/문의게시판에서는 댓글 작성이 가능합니다.
  - 로그인 한 회원만 댓글 작성이 가능합니다.
  - 본인이 작성한 댓글만 수정/삭제 가능합니다.
  - 비동기 방식으로, 페이지의 로딩 없이 작성/수정/삭제가 가능합니다.

<br>

## 8.1 자유&문의게시판
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/3853981e-5d70-414e-849b-0f923ec4bba3">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/b335f3a5-78d5-48de-97ac-8bcc7e32f3ad">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/18c67cd1-4908-4879-b1d0-9040a78958d1">

<br>
<br>

### 8.1.1 공개/비공개🔒 설정
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/b39d08b8-6758-4807-8f46-1510d02665e4">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/b0d4f398-f0a0-4fed-a6c6-324d063d6450">
<br>
<img width="450" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/362d43ff-89d8-4f42-a205-78f81b3ea4e2" style="height: 80px">

- 문의게시판은 글 작성 시 공개/비공개 선택이 가능합니다.
    - 비공개 선택 시, 작성자와 관리자만 게시글 열람이 가능합니다.
 
<br>
<br>

## 8.1.2 댓글 작성 예시
### 비로그인 시 (댓글 작성 창이 보이지 않음)
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/36fa7944-227d-45fa-a9cd-335ade00ab8c">

### 로그인 시
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/67a01430-3880-42b2-96ca-6ad72306a120">
<br>

### 댓글 수정창
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/39c2fccb-b117-4fce-ac4e-7c0de258c47a">

<br>
<br>

## 8.2 입양후기 게시판
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/c642146d-d5a0-4796-8e2c-6c302692b8a0">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/a613a962-a4ad-4dab-9382-50e2af41e1f9">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/7edc41a9-4666-4dd1-8d4a-4044143f902e">

- 글 작성 시 이미지파일 업로드가 가능합니다.
    - 이미지파일을 업로드하지 않을 시 default image가 적용됩니다.
    - 업로드파일 삭제 시 서버의 데이터에서도 삭제됩니다.
- 입양 기록이 없는 회원은 입양후기글 작성이 불가합니다.

<br>
<br>
<br>
<br>

# 🐰 9. 마이페이지 🐰
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/1a8ca467-466c-4678-ae2d-e6a895b784c1">

<br>
<br>

## 9.1 입양/임보 신청내역
<img width="700" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/7e951cdb-3bc0-47f5-89f7-f54176e3c406">
<br>
<img width="700" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/adbabebe-479d-406a-ac5b-5f089d23afbe">

<br>
<br>

## 9.2 내가 쓴 게시글
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/c14c23f4-1a0a-4c95-92e4-353dd0d69306">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/557bdc3a-3c8e-48fa-93e8-e2a5b4435226">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/2663080a-e2da-445f-93ff-9dbd9d60d1c8" style="height: 275px">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/0724b13c-96a8-4c39-b7c5-09e301b4e421">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/d7f2a5e7-d90e-4540-96d4-b9c193c7b8c7">

<br>
<br>

## 9.3 찾아요/봤어요 매칭 결과
### 9.3.1 매칭된 찾아요 게시글
<div>
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/a073cd1a-3f82-4a88-8fde-6d36ba62856d" style="width:45%; height: 400px">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/7c1abdf6-f019-4c32-80e9-3540449b1aad" style="width:45%; height: 400px">
</div>

- 매칭된 찾아요 게시글 클릭 시, 해당 글에 매칭된 봤어요 게시글 리스트로 이동됩니다.

<br>

### 9.3.2 매칭된 봤어요 게시글
<div>
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/c1b5316b-81b9-473b-860f-87b0a892377a" style="width:45%; height: 400px">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/768a9085-af6a-4bb8-babe-6690cb46510e" style="width:45%; height: 400px">
</div>

- 매칭된 봤어요 게시글 클릭 시, 해당 글에 매칭된 찾아요 게시글 리스트로 이동됩니다.

<br>
<br>

## 9.4 회원정보 변경
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/3d8b8835-df3a-476e-98e8-184cc69fba0e">
<br>

- 닉네임, 이메일, 연락처만 변경 가능합니다.
- 입력값 조건, 검증 절차는 회원가입 시와 동일합니다.

<br>
<br>

## 9.5 비밀번호 변경
<img width="500" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/35b5c0e5-1999-4285-8502-f5aa7f7f408e">
<br>

- 기존 비밀번호 검증 후 변경이 가능합니다.
- 새 비밀번호 입력값의 조건, 검증 절차는 회원가입 시와 동일합니다.

<br>
<br>

## 9.6 회원 탈퇴
<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/3684bcf1-eed5-463a-8613-cdb5337eaae8">
<br>

- 비밀번호 검증 후 탈퇴가 가능합니다.
<br>
<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/6f815246-8a3d-46c9-9a59-a2c8c7133611">
<br>

- alert창의 확인을 누르면 탈퇴가 완료됩니다.

<br>
<br>
<br>
<br>

# 🐹 10. 관리자 페이지 🐹
- 관리자는 모든 게시글의 조회/추가/수정/삭제가 가능합니다.
- 관리자는 모든 댓글의 수정,삭제가 가능합니다.
- 일반 등급 회원이 관리자 페이지 진입 시도 시 에러페이지로 이동됩니다.
- 메인페이지에서 관리자 계정으로 로그인 시 관리자 페이지로 이동됩니다.

<br>

## 10.1 회원정보 관리
- 회원 등급을 제외한 모든 정보의 수정이 가능합니다.
<img width="800" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/1a8ce6e3-a1dd-464c-a529-04405aa87948" style="width:65%; height: 400px">
<img width="350" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/c8c85c68-8a62-4a6f-8665-5697e7c96b8a" style="width:20%; height: 400px">

<br>
<br>

## 10.2 유기동물 관리
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/e8c61ad2-fef6-434e-90c4-6b3b6508fd7f" style="width:45%; height: 400px">
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/6a262461-7306-48fb-a111-126474ee9a9a" style="width:45%; height: 400px">

<br>
<br>

## 10.3 게시글 관리
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/f394dce3-3332-4e61-b23d-bf504984c400" style="width:45%; height: 400px">
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/db3fdfeb-0ce3-4b7f-93a2-06c4a8eddd25" style="width:40%; height: 400px">

<br>
<br>

## 10.4 입양&임시보호 관리
- 입양, 임시보호는 형식 동일 (아래 예시는 입양)
<div>
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/5eb0d073-ddf7-49d9-90f9-e66c52396a6a" style="width:45%; height: 300px">
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/ad0aec8a-e869-452c-938b-c874b94ebc31" style="width:45%; height: 300px">
</div>

<br>
<br>

## 10.5 후원 관리
<div>
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/e6a9dd12-5de0-450c-b162-6c2848cc4115" style="width:45%; height: 200px">
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/b302095b-be8a-473b-b577-98595051ab8f" style="width:40%; height: 200px">
</div>

<br>
<br>

## 10.6 보호소 관리
<div>
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/6141f6a8-2dfe-48c6-9c93-0dee295ec1f9" style="width:45%; height: 200px">
<img src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/d1b27bd7-a352-4381-852f-af1fec32e486" style="width:45%; height: 200px">
</div>

<br>
<br>
<br>

# 🐷 11. 그 외 기능 🐷
- 일반 등급 회원이 비정상적인 요청으로 타 회원의 글을 수정/삭제 불가하도록 구현했습니다.
- 400, 404, 500, 그 외 default 에러 페이지를 구현했습니다.

<img width="450" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/aace7c86-5711-4ae0-a11c-13e3a4660680" style="width:45%; height: 110px">
<img width="450" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/591e6f6f-8b24-4db4-9bb5-f832c7bd6003" style="width:45%; height: 110px">
<img width="450" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/264bee67-3a43-49b6-992a-26c5f3aa17f1" style="width:45%; height: 110px">
<img width="450" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/b902fe1a-bb43-4b33-a0e1-ccd14b6140c3" style="width:45%; height: 110px">





