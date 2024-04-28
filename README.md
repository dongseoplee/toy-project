## Toy Project (회원 기능 담당 RESTful API 서버)


## URL
#### 1. http://localhost:8080/
- index.html을 불러옵니다.
- index.html에서 1. 회원 가입 ("/join"), 2. 회원 목록 조회 및 수정 ("/member/search")으로 접속할 수 있습니다.
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/7b464113-68f9-4a7b-8566-42a3e88a091b" width = 600 height = 300/>

#### 2. http://localhost:8080/join
- join.html을 불러옵니다.
- join.html에서 form 데이터를 "/api/user/join"에 post 방식으로 회원 가입 데이터를 전달합니다.
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/c5bdb754-1b73-4d02-8eee-0c0e572ff4ff" width = 600 height = 300/>

#### 3. http://localhost:8080/member/search
- search.html을 불러옵니다.
- "/api/user/list?page=${page}&pageSize=${pageSize}"으로 데이터를 불러와 페이지별로 회원 데이터를 나타냅니다.
- "수정" 버튼을 클릭 시 "/member/edit?id=${memberId}"으로 이동합니다.
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/703db9a1-1883-4028-98c6-f72722e3e99c" width = 600 height = 300/>

#### 4. http://localhost:8080/member/edit?id=example
- form 데이터를 "/api/user?id=example"에 post 방식으로 회원 정보를 전달해 수정합니다.
- postman 사용시 put 방식으로도 회원 정보 수정이 가능합니다.
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/f0999141-a126-453d-b28b-1d3e628beb9e" width = 600 height = 300/>

## Description & Exception
1. 회원가입
- 아이디 또는 이메일이 이미 존재한다면 중복으로 사용할 수 없는 로직을 구현했습니다.
2. 회원 목록 조회
- 회원 등록순, 이름순으로 한 페이지당 20개 데이터씩 보여집니다.
3. 회원 수정
- 비밀번호가 일치해야 회원 정보가 수정되는 로직을 구현했습니다.

## Open API
- Swagger를 적용시켜 "http://localhost:8080/swagger-ui/index.html"에 접속 시, API 명세를 확인할 수 있습니다.

#### Postman
- 1-1. 회원가입 성공 (201)
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/c4315ba8-f9a6-4a59-a9ef-1f45bad5a38c" width = 600 height = 450/>

- 1-2. 회원가입 실패 (409)
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/c930525f-8050-40fa-b036-411d83b663ee" width = 600 height = 450/>

- 2. 회원 목록 조회 (페이지네이션)
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/d3d2db5b-7eee-4a0e-b5a5-0507faabc061" width = 600 height = 450/>

- 3-1. 회원 수정 (POST)
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/51294b38-72dd-474f-bd2d-8ceb71a2a158" width = 600 height = 450/>

- 3-2. 회원 수정 (PUT)
<img src="https://github.com/dongseoplee/toy-project/assets/76763417/65aa8027-bd04-4520-9a7d-d4037f09a83c" width = 600 height = 450/>

## How To Start
1. `git clone https://github.com/dongseoplee/toy-project.git`
2. build.gradle 파일을 IntelliJ로 실행합니다.
3. jdk 1.8 (Java8)로 설정한뒤 ToyApplication.java를 실행시킵니다.
4. http://localhost:8080 주소로 접속합니다.

## Test
- JUnit, Mockito를 활용해 테스트 코드를 작성했습니다. ("src/test")

## Log
- logback을 활용해 로그를 파악하였으며, 로그 정보는 logs 폴더 안에 저장됩니다.

## GitHub
- 개인 Branch를 생성해 Pull request로 main Branch에 merge 했습니다.

## Tech Stack
- Framework: Spring Boot (2.6.2), jdk 1.8 (Java 8)
- Database: AWS RDS (MySQL)
- ORM: Spring Data JPA
- Tool: Postman, IntelliJ
- Log: logback
- API: RESTful API, Swagger
- VCS: GitHub (Pull requests)
- Test: JUnit, Mockito

#### 회원 정보 수정시 비밀번호 입력을 위해 암호화해 저장해두지 않았습니다.
