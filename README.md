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
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/f609e995-7933-4d47-a1c5-645189e44655">
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
<img width="300" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/6c4e3f92-f75b-4386-8379-1b58adecc8a1">☜ `Click here!`

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

<img width="400" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/e7afbbf0-469a-4716-bd2e-45fb333879b7">☜ `Click here!`

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

<img width="890" src="https://github.com/younghoon211/petmily-younghoon/assets/82747286/f491b6db-fd05-49c1-ad89-1b0c59f8bd6e">☜ `Click here!`

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
<img width="250" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/c5a76412-d9dc-4179-aee9-9da7c48b1480" style="width:25%; height: 250px">
<img width="391" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/be7bee94-e8b6-47c3-a692-f84d44a9d7c7" style="width:40%; height: 250px">
</div>

- 유효한 이메일 입력 시, 해당 이메일로 아이디가 전송됩니다.

<br>
<br>
<br>

# 🐯 4. 비밀번호 찾기 🐯
<div>
<img width="250" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/99ad2964-9b8d-43da-b1fb-f8e149453a91" style="width:25%; height: 250px">
<img width="400" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/ea957016-2f55-4d07-b0f9-0c25673642f4" style="width:40%; height: 250px">
</div>

- 유효한 아이디와 이메일 입력 시, 해당 이메일로 인증코드가 전송됩니다.

<br>

<img width="250" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/37df7371-04cb-408e-a06d-d1d8e44339f8">

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
<img width="550" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/fda27492-a425-482c-a58c-862473abaa50" style="width:45%; height: 400px"> 
<img width="550" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/c864c3da-99bc-42fe-b820-07251f2f72cc" style="width:45%; height: 400px">
</div>
<br>

<img width="400" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/b695edcd-33c6-49c7-a584-4d318c17580d">

- 조건부 검색과 키워드 검색이 가능합니다.
  - 제목, 내용, 품종, 실종 장소 중 하나라도 포함되어 있다면 해당 정보가 검색됩니다.
  
<br>

## 5.1 후원 신청
<img width="700" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/757e844f-0cd3-4b6f-bd7d-873c75822b0d" style="height: 500px">
<br>
<img width="200" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/af6972da-fc90-42eb-bc60-fd951968f50b">

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
  
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/d797e145-6b59-47f6-a483-382a425ca894">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/865894a0-3d53-414a-8e69-a11a8cfe2747">

- 보호중인 동물은 임시보호 또는 입양 선택 후 신청 가능합니다.
  - 선택 창에서 임시보호 체크 시 시작 날짜, 기간 입력창이 생성됩니다.
  - 임시보호는 최소 1개월 이상만 가능합니다.

<br>

## 5.3 봉사 신청

- 보호소에 보호중인 동물에게만 봉사 가능합니다.
  - 임시보호중인 동물은 불가합니다.
- 신청서 작성 절차는 따로 없으며, 해당 보호소에 연락해 일정 조율 후 방문하시면 됩니다.

<img width="600" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/c46371e8-e849-4a78-82d5-0d2101b912fa">

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

<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/42eeacc0-1cd5-48c2-9eab-7e448a8e4480">

- 조건부 검색과 키워드 검색이 가능합니다.
  - 제목, 내용, 품종, 실종 장소 중 하나라도 포함되어 있다면 해당 정보가 검색됩니다.
  
<br>

## 6.2 유기동물 봤어요

- 글 작성 시 찾아요 게시판의 종, 품종, 실종 장소와 일치하는 글이 있으면 ‘상태: 매칭됨’으로,  없으면 ‘상태: 보호’로 작성됩니다.
  - 매칭된 글을 수정할 때 종, 품종, 실종 장소 중 하나라도 변경할 시, ‘보호’ 상태로 되돌아갑니다.

<br>

<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/c944b54f-3624-4d44-b774-6f585445de0c">

- 조건부 검색과 키워드 검색이 가능합니다.
  - 제목, 내용, 품종, 발견 장소 중 하나라도 포함되어 있다면 해당 정보가 검색됩니다.

<br>

## 6.3 매칭 기능 예시
#### 아래의 예시는 찾아요가 봤어요에 매칭되는 예시입니다.(반대도 가능)

<br>

### (1) 봤어요 게시판에 비숑을 보호중인 글을 올림
<div>
<img width="600" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/20299adf-23b4-45e1-aee1-6ff9314142f3" style="width:60%; height: 400px">
</div>

<br>

### (2) 찾아요 게시판에 봤어요 게시판의 종, 품종, 발견 장소와 일치하는 조건의 글을 올림 (올리는 즉시 매칭됨)
<div>
<img width="400" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/3ddf8c00-15b1-494a-a515-2ed6fb2a6929" style="width:35%; height: 380px">
<img width="400" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/946818b7-a21b-4b58-9d03-7f9951b5c1f8" style="width:55%; height: 380px">
</div>

<br>

### (3) 봤어요 게시판의 게시글도 매칭 상태로 바뀜
<div>
<img width="400" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/a15e5dec-84cf-4289-a544-8fbc8c060c22" style="width:35%; height: 350px">
<img width="400" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/c45d0138-a605-41bf-808a-ac67a0fc7cce" style="width:55%; height: 350px">
</div>

<br>
<br>
<br>

# 🐻‍❄️ 7. 입양완료 조회 🐻‍❄️

<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/c8671b1c-a8fe-4d30-af4f-7b663453d77f" style="width:60%; height: 500px">
<br>

- 최신순, 오래된순, 조회순 정렬이 가능합니다.
<br>

<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/dc337c27-6b41-47b8-8e8b-bcbd300b02e9">

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
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/5404de00-e004-403d-ae15-8c5a3b9e6fb6">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/1ead860a-e3d9-4c9c-8185-b0cacc3a4c27">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/116efcc6-4768-4d6a-bae8-59e3fa7668f9">

<br>
<br>

### 8.1.1 공개/비공개🔒 설정
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/b6dd9a5f-d9f0-47ef-9e01-130c897bf20b">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/94dcc9e2-d5a1-48c7-9d2c-2b6d58d19be3">
<br>
<img width="450" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/55c627c6-4fe2-46df-885b-975c3b3251d6" style="height: 80px">

- 문의게시판은 글 작성 시 공개/비공개 선택이 가능합니다.
    - 비공개 선택 시, 작성자와 관리자만 게시글 열람이 가능합니다.
 
<br>
<br>

## 8.1.2 댓글 작성 예시
### 비로그인 시 (댓글 작성 창이 보이지 않음)
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/95f973fc-2df1-46bd-8247-21450e2d45bc">

### 로그인 시
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/54633251-cd87-43e3-9544-2c62b7441a99">
<br>

### 댓글 수정창
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/d8cb61d3-6eec-4c02-aac9-11bba3e1105e">

<br>
<br>

## 8.2 입양후기 게시판
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/b66e91ac-75d8-4cd1-b8e1-f0fbdbad781a">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/ae6b2f2b-e3b9-441f-970d-35f8effd070b">
<br>
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/10f9c025-1597-4aca-a005-2872fe7e00be">

- 글 작성 시 이미지파일 업로드가 가능합니다.
    - 이미지파일을 업로드하지 않을 시 default image가 적용됩니다.
    - 업로드파일 삭제 시 서버의 데이터에서도 삭제됩니다.
- 입양 기록이 없는 회원은 입양후기글 작성이 불가합니다.

<br>
<br>
<br>
<br>

# 🐰 9. 마이페이지 🐰
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/5cd69a61-0caa-4cb7-ad67-8eacc958a819">

<br>
<br>

## 9.1 입양/임보 신청내역
<img width="700" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/f7a71cde-7064-4167-87ff-d70785f76a91">
<br>
<img width="700" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/d618de6b-2ba3-4eca-bc1d-0f1c59b26062">

<br>
<br>

## 9.2 내가 쓴 게시글
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/c5a2fa40-28ec-4ebe-98b8-ca8b181b16f6">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/e625def7-ec10-42b1-a49c-f37dc025e31f">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/7fefd53e-328b-40ab-a897-c0e90365544a" style="height: 275px">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/c9d52bad-f6a4-4ddc-899d-e0efbdff1ad3">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/cf44fb59-b0a1-4e7c-bc7c-08a95ba07f39">

<br>
<br>

## 9.3 찾아요/봤어요 매칭 결과
### 9.3.1 매칭된 찾아요 게시글
<div>
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/996fa55b-e3f4-4323-87d8-96d72543fa6d" style="width:45%; height: 400px">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/3276c995-912a-4bda-98d3-53f29732432d" style="width:45%; height: 400px">
</div>

- 매칭된 찾아요 게시글 클릭 시, 해당 글에 매칭된 봤어요 게시글 리스트로 이동됩니다.

<br>

### 9.3.2 매칭된 봤어요 게시글
<div>
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/2e4ab029-ebbb-42f8-ba37-dfa78b0384ba" style="width:45%; height: 400px">
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/20b92359-b83f-4ae7-b302-4fb175acc696" style="width:45%; height: 400px">
</div>

- 매칭된 봤어요 게시글 클릭 시, 해당 글에 매칭된 찾아요 게시글 리스트로 이동됩니다.

<br>
<br>

## 9.4 회원정보 변경
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/18ece750-3637-4386-94e8-b2d8fd6364de">
<br>

- 닉네임, 이메일, 연락처만 변경 가능합니다.
- 입력값 조건, 검증 절차는 회원가입 시와 동일합니다.

<br>
<br>

## 9.5 비밀번호 변경
<img width="500" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/1f03f9cb-1d32-4b59-b94e-2272e67ecf46">
<br>

- 기존 비밀번호 검증 후 변경이 가능합니다.
- 새 비밀번호 입력값의 조건, 검증 절차는 회원가입 시와 동일합니다.

<br>
<br>

## 9.6 회원 탈퇴
<img width="400" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/0397e61e-b18e-4b15-a843-4c40e6cf03e2">
<br>

- 비밀번호 검증 후 탈퇴가 가능합니다.
<br>
<img width="400" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/487328cf-99a5-4896-897b-009bbfcd2c64">
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
<img width="800" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/b4be7a5d-3987-4064-9ee4-5600b17c3638" style="width:65%; height: 400px">
<img width="350" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/9f09792f-05ff-45d9-9297-b97917858238" style="width:20%; height: 400px">

<br>
<br>

## 10.2 유기동물 관리
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/e96cea89-5705-43fa-bc43-9c84ca97a5c7" style="width:45%; height: 400px">
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/21443a34-1292-41a5-af63-0faabdea922b" style="width:45%; height: 400px">

<br>
<br>

## 10.3 게시글 관리
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/0fb4e6d2-fb9f-416a-a7b5-92082f45ac74" style="width:45%; height: 400px">
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/bb575c54-9855-45b0-a1f6-fc655b7163c5" style="width:40%; height: 400px">

<br>
<br>

## 10.4 입양&임시보호 관리
- 입양, 임시보호는 형식 동일 (아래 예시는 입양)
<div>
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/30d98c3f-15fd-49f5-88c9-f871b110496e" style="width:45%; height: 300px">
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/2856f40a-df15-4877-a2d3-efeec595d7b3" style="width:45%; height: 300px">
</div>

<br>
<br>

## 10.5 후원 관리
<div>
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/8cc005a2-4186-44fa-ae0a-6661c081ee8e" style="width:45%; height: 200px">
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/7e478f23-cab6-4e41-9afd-443e7ea0018b" style="width:40%; height: 200px">
</div>

<br>
<br>

## 10.6 보호소 관리
<div>
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/baf43914-4eea-4512-bfb6-d52f33d14c91" style="width:45%; height: 200px">
<img src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/edc6822f-46bd-4c77-86c4-d0b00081f76f" style="width:45%; height: 200px">
</div>

<br>
<br>
<br>

# 🐷 11. 그 외 기능 🐷
- 일반 등급 회원이 비정상적인 요청으로 타 회원의 글을 수정/삭제 불가하도록 구현했습니다.
- 400, 404, 500, 그 외 default 에러 페이지를 구현했습니다.

<img width="450" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/12972bd6-4fcf-4efd-85c2-211cf71a1e92" style="width:45%; height: 110px">
<img width="450" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/f5455d21-c462-41c7-abff-17e539c6ae02" style="width:45%; height: 110px">
<img width="450" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/0074b440-343a-455b-9513-3019f805d835" style="width:45%; height: 110px">
<img width="450" src="https://github.com/younghoon211/petmily-younghoon-/assets/82747286/8a5279c9-568a-42f8-820f-4861d451f50d" style="width:45%; height: 110px">





