## Toy Project (회원 기능 담당 RESTful API 서버)


## URL
#### 1. http://localhost:8080/
- index.html을 불러옵니다.
- index.html에서 1. 회원 가입 ("/join"), 2. 회원 목록 조회 및 수정 ("/member/search")으로 접속할 수 있습니다.
#### 2. http://localhost:8080/join
- join.html을 불러옵니다.
- join.html에서 form 데이터를 "/api/user/join"에 post 방식으로 회원 가입 데이터를 전달합니다.
#### 3. http://localhost:8080/member/search
- search.html을 불러옵니다.
- "/api/user/list?page=${page}&pageSize=${pageSize}"으로 데이터를 불러와 페이지별로 회원 데이터를 나타냅니다.
- "수정" 버튼을 클릭 시 "/member/edit?id=${memberId}"으로 이동합니다.
#### 4. http://localhost:8080/member/edit?id=example
- form 데이터를 "/api/user?id=example"에 post 방식으로 회원 정보를 전달해 수정합니다.

## Description & Exception
1. 회원가입
- 아이디 또는 이메일이 존재한다면 사용할 수 없는 로직을 구현했습니다.
2. 회원 목록 조회
- 회원 등록순, 이름순으로 한 페이지당 20개 데이터씩 보여집니다.
3. 회원 수정
- 비밀번호가 일치해야 회원 정보가 수정되는 로직을 구현했습니다.
  
## Test
- JUnit, Mockito를 활용해 테스트 코드를 작성했습니다. ("src/test")

## Log
- logback을 활용해 로그를 파악하였으며, 로그 정보는 logs 폴더 안에 저장됩니다.

## GitHub
- 개인 Branch를 생성해 Pull request로 main Branch에 merge 했습니다.

## Open API
- Swagger를 적용시켜 "http://localhost:8080/swagger-ui/index.html"에 접속 시, API 명세를 확인할 수 있습니다.

## Tech Stack
- Framework: Spring Boot (2.6.2), jdk 1.8 (Java 8)
- Database: AWS RDS (MySQL)
- ORM: Spring Data JPA
- Tool: postman, Intellij
- Log: logback
- API: RESTful API, Swagger
- VCS: GitHub (Pull requests)
- Test: JUnit, Mockito

#### 회원 정보 수정시 비밀번호 입력을 위해 암호화해 저장해두지 않았습니다.
