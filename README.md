# 게시판 서비스 v1.0
### 개요
- 해당 서비스는 Spring boot, BootStrap, JS, ThymeLeaf 를 사용한 웹 애플리케이션 입니다. 
- 해당 애플리케이션은 게시판 서비스로서 이용자가 자신의 게시글을 작성하거나 관리할 수 있습니다.
### 기능
- 회원가입 기능, 유저정보 변경기능
- 게시판에 대한 기본 CRUD 제공
- BootStrap, JS 를 구현된 몇몇의 반응형 웹 디자인
- 데이터베이스와 통합한다면 지속적인 저장 가능 (Default 는 Memory 에서 이용됨)
## 시작하기
### 필수조건
- JDK 21
### 설치방법
1. github 저장소를 클론 후 디렉토리를 이동합니다.
``` 
git clone https://github.com/esparant/article-service.git
cd article-service
```
2. 프로젝트를 빌드합니다.
``` 
./gradlew build
```
3. 애플리케이션을 실행합니다.
``` 
./gradlew bootRun
```
4. http://localhost.8080 에서 애플리케이션에 접근합니다.
### Spring 라이브러리
- spring-boot-starter-data-jpa: JPA 와 관련된 기능을 제공
- spring-boot-starter-thymeleaf: Thymeleaf 템플릿 엔진을 사용
- spring-boot-starter-validation: 입력값 검증 기능을 제공
- spring-boot-starter-web: 웹 애플리케이션 개발에 필요한 기본 기능 제공
- Lombok: 코드 축약을 위한 어노테이션을 제공
- H2 Database: 인메모리 데이터베이스
- spring-boot-starter-test: 테스트 관련 기능 제공
- junit-platform-launcher: JUnit 플랫폼 런처
- P6spy: SQL 로그를 확인하기 위한 라이브러리
- QueryDSL: 타입 안전한 쿼리 생성
