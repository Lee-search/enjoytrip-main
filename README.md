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
![usecase1](https://github.com/Lee-search/enjoytrip-main/assets/95065616/dd95c539-61fc-4230-98aa-43565abfe94f)

### 주요 기능 - 여행계획 관련

- 여행 계획 등록
    - 조회한 관광지를 활용하여 여행 계획을 설정할 수 있습니다.
    - 작성한 여행계획은 ‘나의여행계획’ 네비게이션 바를 클릭하여 확인할 수 있습니다.
    - 여행계획을 ‘공유게시판’을 통해 자동으로 공유할 수 있습니다.

![usecase2](https://github.com/Lee-search/enjoytrip-main/assets/95065616/692328b6-6f6a-4479-8d5d-37f4f9e48d31)

## 2. 테이블 구조도 (ERD)

![ERD_Diagram](https://github.com/Lee-search/enjoytrip-main/assets/95065616/20a19e1d-06ba-41de-95a1-a429ee78384a)

**주요 엔티티 및 관계**

- sido와 gugun을 참고하여 attraction 출력
- 공공API의 불안정을 고려해 attraction 관련 DB 추가 저장
- board (게시글 정보), comments (게시글 댓글) , file_info(파일정보), members(맴버 정보)를 활용

## 3. 클래스 다이어그램

![class-diagram](https://github.com/Lee-search/enjoytrip-main/assets/95065616/bc582a87-2596-4866-86db-ca0f25a229af)


## 4. 화면 설계서

### [메인페이지]

![screencapture-localhost-5173-2023-11-23-15_04_20](https://github.com/Lee-search/enjoytrip-vue/assets/95065616/3d45d1b4-fd51-46f9-91b6-f0af599de235)

- 깔끔한 UI와 간단한 애니메이션으로 사용자 경험 향상을 고려했습니다.
- 메인 페이지에는 사용자가 최대한 서비스를 많이 이용하도록 여러 여행지를 추천합니다.
- 다양한 사용자의 여행 후기를 미리 참고할 수 있습니다.

### [로그인화면]                                                    [회원가입]

![1](https://github.com/Lee-search/enjoytrip-main/assets/95065616/7464ae8b-7960-4bf4-b15f-0fb5260f9dd8)
- 아이디저장과 로그인 관련된입니다
- 특정 아이디로 접속시 관리자역활을 수행합니다.

### [마이페이지]

![3](https://github.com/Lee-search/enjoytrip-main/assets/95065616/69ce714e-0e83-431e-bff2-dd538cf3f6dc)

- 회원가입 페이지입니다.
- 아이디는 중복이 불가능하여 중복을 확인합니다
- 비밀번호는 실수를 방지하기 위하여 재확인 합니다.


![2](https://github.com/Lee-search/enjoytrip-main/assets/95065616/dad530c8-e1e7-4e5a-88e4-2d3407e370fd)




- 깔끔한 UI와 간단한 프로필로 사용자의 정보를 출력합니다
- 닉네임,이메일 ,비밀번호등 정보를 조건에 한정해서 변경가능이 합니다.

  

### [관광지 상세보기]

![7](https://github.com/Lee-search/enjoytrip-main/assets/95065616/a02d8064-1096-4e0a-b9f0-9a4eca9574e2)
- 관광지 정보와 상세 설명, 그리고 지도에서의 위치를 확인 가능합니다****
- 그 동안 검색해왔던 관광지들의 상대적거리를 지도에서 뿌려줍니다.

### [경로 공유하기]

![8](https://github.com/Lee-search/enjoytrip-main/assets/95065616/74dc7f18-285b-46b3-9036-2e7282cfbb7b)

![9](https://github.com/Lee-search/enjoytrip-main/assets/95065616/0eb397ae-d800-4782-b6df-6c58849c913c)


- 내가 찜햇던 관광지를 다른사람들이 볼 수 있게 게시글에 바로 등록이 가능합니다
- 내가 찜햇던 목록들을 일괄삭제 및 확인 할 수 있습니다.

### [여행 공유 게시판 ]

![10](https://github.com/Lee-search/enjoytrip-main/assets/95065616/e946c763-c899-4028-b8b0-2567b12821f2)

- 다양한 사람들이 게시글을 작성 할 수 있습니다.
- 게시글에는 나의 의견을 어필이 가능한 댓글달기가 존재합니다.

### [관광지 검색]


![11](https://github.com/Lee-search/enjoytrip-main/assets/95065616/187aefce-4476-4de7-b01c-648e71630583)


- 카카오맵 지도가 포함하여 어디에 있는지 측정이 가능합니다
- 다양한 시도/군 검색을 통하여 특정 관광지에 대한 정보를 필터링 할 수 있습니다.
- 여행지 담기를 이용하여 맘에드는 관광지를 나의 여행 계획에 포함 할 수 있습니다.
