# Todo 패키지 구조

## 개요
Todo 패키지는 할 일 관리 기능을 구현한 패키지입니다. MVC 패턴을 따르며 다음과 같은 하위 패키지들로 구성되어 있습니다.

### 패키지 구조 
todo/
├── controller/ # Todo 관련 요청을 처리하는 컨트롤러
├── service/ # 비즈니스 로직을 처리하는 서비스
├── repository/ # 데이터 접근을 담당하는 레포지토리
├── domain/ # Todo 관련 엔티티 클래스
└── dto/ # 데이터 전송 객체

## 주요 컴포넌트

### Controller
- `TodoController`: Todo CRUD 작업을 위한 REST API 엔드포인트 제공
  - GET `/api/todos`: 모든 할 일 목록 조회
  - POST `/api/todos`: 새로운 할 일 생성
  - PUT `/api/todos/{id}`: 특정 할 일 수정
  - DELETE `/api/todos/{id}`: 특정 할 일 삭제

### Service
- `TodoService`: Todo 관련 비즈니스 로직 처리
  - 할 일 생성, 수정, 삭제, 조회 기능 구현
  - 사용자 권한 확인 및 유효성 검사

### Repository
- `TodoRepository`: JPA를 사용한 데이터베이스 조작
  - Todo 엔티티의 CRUD 연산 처리

### Domain
- `Todo`: 할 일 정보를 담는 엔티티 클래스
  ```java
  public class Todo {
      private Long id;
      private String title;
      private String content;
      private boolean completed;
      private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      private User user;
  }
  ```

### DTO
- `TodoRequestDto`: 클라이언트로부터 받는 요청 데이터
- `TodoResponseDto`: 클라이언트에게 전송하는 응답 데이터

## 주요 기능
1. 할 일 생성
2. 할 일 조회 (단일/전체)
3. 할 일 수정
4. 할 일 삭제
5. 할 일 완료 상태 토글
6. 사용자별 할 일 관리

## 의존성
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok
- H2 Database (개발용)
- MySQL (운영용) 