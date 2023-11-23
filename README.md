# -TEMP-

로그인 화면

1. 프로젝트 설계서

# EnjoyTrip 프로젝트 설계서

![logo](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/811e64bb-3ca5-4f87-a7a8-3804a530dfe8)

## Team

| 조장 | 조원 |
| --- | --- |
| 이우경 | 복영석 |

****

### **국내 관광지 정보와 여행 계획 및 여행지 정보를 공유하는 서비스입니다.**

## 프로젝트 개요

📌 Front-End : Vue.js / VueBootstrap

📌 Back-End : Spring Boot / REST API

📌 DB : MySQL / My Batis

📌 LIBRARY : Vue3..

📌 API : 한국관광공사 API / Kakao 지도 /  

## 1. 요구사항 정의서 (Usecase Diagram)
![usecase1](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/f7f5881c-3435-46d7-93c6-a4922a12738a)

### 주요 기능 - 여행계획 관련

- 여행 계획 등록
    - 조회한 관광지를 활용하여 여행 계획을 설정할 수 있습니다.
    - 작성한 여행계획은 ‘나의여행계획’ 네비게이션 바를 클릭하여 확인할 수 있습니다.
    - 여행계획을 ‘공유게시판’을 통해 자동으로 공유할 수 있습니다.
- 기능 2: (여기에 설명)

![usecase2](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/4b1aad60-2bd7-4939-9a89-5176023a6ad2)
-f86cd526d4b1)

## 2. 테이블 구조도 (ERD)

![ERD_Diagram](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/5448a006-d152-4dbf-bb35-5aaa34bec0b5)

**주요 엔티티 및 관계**

- attraction: sido와 gugun을 참고하여 attraction목록을 참조 [content_id] detail을description참조
- board:  comments (내용) , file_info(파일정보) , memo(덧글), members(글쓴이)를 활용

## 3. 클래스 다이어그램

![class-diagram](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/530f2e6a-db1d-441a-9f73-2036dfac9b33)


## 4. 화면 설계서

### [메인페이지]

![screencapture-localhost-5173-2023-11-23-15_04_20](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/3d45d1b4-fd51-46f9-91b6-f0af599de235)

- 깔끔한 UI와 간단한 애니메이션으로 사용자 경험 향상시켰습니다
- 메인 페이지에는 사용자가 최대한 서비스를 많이 이용하도록 여러 여행지를 추천해줍니다
- 다양한 사용자의 여행 후기를 미리 참고 할 수 있습니다.

### [로그인화면]                                                    [회원가입]

![1](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/a446baf9-9c4c-49f8-802d-8a4649de2f5c)

- 아이디저장과 로그인 관련된입니다
- 특정 아이디로 접속시 관리자역활을 수행합니다.

### [마이페이지]

![3](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/11b000ec-2d2d-4253-b3fe-902f415eb625)

- 회원가입 페이지입니다.
- 아이디는 중복이 불가능하여 중복을 확인합니다
- 비밀번호는 실수를 방지하기 위하여 재확인 합니다.

![2](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/d68d3448-2025-4362-b463
![class-diagram](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/17ef0dbc-7747-485c-98dd-554d944d8a54)



- 깔끔한 UI와 간단한 프로필로 사용자의 정보를 출력합니다
- 닉네임,이메일 ,비밀번호등 정보를 조건에 한정해서 변경가능이 합니다.

  

### [관광지 상세보기]

![7](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/b7434303-fdc4-4f20-81b6-5107249d4970)

- 관광지 정보와 상세 설명, 그리고 지도에서의 위치를 확인 가능합니다****
- 그 동안 검색해왔던 관광지들의 상대적거리를 지도에서 뿌려줍니다.

### [경로 공유하기]

![8](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/44837b5c-51fc-4f1f-8fc2-647d12247a2c)

![9](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/c9d24cca-531b-4406-aecd-2df1b836ef24)


- 내가 찜햇던 관광지를 다른사람들이 볼 수 있게 게시글에 바로 등록이 가능합니다
- 내가 찜햇던 목록들을 일괄삭제 및 확인 할 수 있습니다.

### [여행 공유 게시판 ]

![10](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/e2858f8d-8c0e-45f9-8ed5-72406a6927a4)

- 다양한 사람들이 게시글을 작성 할 수 있습니다.
- 게시글에는 나의 의견을 어필이 가능한 댓글달기가 존재합니다.

### [관광지 검색]


![11](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/c60f5c3b-c8b5-4153-be3f-b8bc963d1681)

- 카카오맵 지도가 포함하여 어디에 있는지 측정이 가능합니다
- 다양한 시도/군 검색을 통하여 특정 관광지에 대한 정보를 필터링 할 수 있습니다.
- 여행지 담기를 이용하여 맘에드는 관광지를 나의 여행 계획에 포함 할 수 있습니다.
