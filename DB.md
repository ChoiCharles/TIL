# DataBase
- 우리는 매순간 데이터를 생성해 내고 있다
- 따라서 무한하게 증가하는 데이터를 '잘' 저장하고 관리하는 기술이 필요하다
> 파일을 이용한 데이터 관리
- 일반적으로 데이터를 파일에 저장한다
- 장점
    - 운영체제에 관계 없이 어디에서나 쉽게 사용가능
    - 이메일이나 메신저를 이용해 간편하게 전송 가능
- 단점
    - 성능과 보안적 측면에서 한계가 명확
    - 대용량 데이터를 다루기에 적합하지 않음
    - 데이터를 구조적으로 정리하기에 어려움
    - 확장이 불가능한 구조
> 표(스프레드 시트)를 이용한 데이터 관리
- 스프레드 시트(엑셀 시트)를 사용
- 스프레드 시트는 컬럼(열)을 통해 데이터의 유형을 지정하고 레코드(행)를 통해 구체적인 데이터 값을 포함
- 무한하게 커질 수 없음(100만 행 정도가 최대)
- 데이터 보안 측면
- 데이터 무결성 측면
## 데이터베이스?
---
- 정보들이 조직화 된 것
> DBMS(Database Management System)?
- 데이터를 조작하는 프로그램
- SQLite, MySQL, ORACLE 등등
### 종류
---
> SQL(관계형 데이터베이스)
- Relational Database
- Relation : 수학 > 집합/논리 > 관계
- 표 형식으로된 데이터베이스
- SQLite, MySQL, ORACLE, PostgreSQL, MariaDB
> NoSQL(비관계형 데이터베이스)
- No 보다는 Not Only
- Document, Graph, Key-Value
- 관계형 데이터베이스의 한계를 극복하기 위해 조금 더 유연한 데이터베이스
- 실제로 많이 쓰이는 데이터베이스로, 서브 데이터베이스로 두고 빠른 처리, 확장이 필요한 기능에서 사용하는 경우가 많음
- 채팅, 소셜 관계, 실시간 사진, 메세지 처리, 실시간 추천 등
- 일반적으로 메인 데이터베이스는 역사와 전통의 관계형 데이터베이스를 사용
- mongoDB, redis, elasticsearch
### 시작하기
---
- 데이터베이스를 조작하는 언어 SQL(Structured Query Language)
- 대부분의 관계형 데이터베이스 시스템에서 사용가능
- 매우 직관적인 구조로 되어있음
## 관계형 데이터베이스
---
- 데이터베이스를 사용한 CRUD의 반복
    - 회원가입
    - 회원탈퇴
    - 프로필 조회
    - 프로필 수정
    - 새로운 피드 작성
    - 좋아요
    - 팔로우
- 데이터를 테이블, 행, 열 등으로 나누어 구조화 하는 방식
- 구조화해서 저장하므로 보다 체계적으로 데이터를 저장하고 관리할 수 있음
- 자료를 여러 테이블로 나눠 관리하고, 테이블간 관계를 설정해 여러 데이터를 조작할 수 있음
- 데이터릐 무결성(정확성, 일관성) 유지에 장점이 있음
- SQL을 사용하여 데이터를 조회하고 조작
### 구조
---
1. 스키마
2. 테이블
    - 필드
    - 레코드
    - 기본 키   
> 스키마
- 테이블의 구조
- 데이터베이스에서 자료의 구조, 표현 방법, 관계 등 전반적인 명세를 기술한 것
> 테이블
- 필드와 레코드를 사용해 조직된 데이터 요소들의 집합
- 관계(Relation)라고도 부름
1. 필드
    - 속성, 컬럼
2. 레코드
    - 튜플, 행
> 필드
- 속성 혹은 컬럼
- 각 필드에는 고유한 데이터 형식(타입)이 지정됨
> 레코드
- 퓨틀 혹은 행
- 테이블의 데이터는 레코드에 저장됨
### PK(Primary Key)
---
- 기본 키
- 각 레코드의 고유한 값
    - 각각의 데이터를 구분할 수 있는 고윳값
- 기술적으로 다른 항목과 절대로 중복될 수 없는 단일 값(unique)
- 데이터베이스 관리 및 테이블 간 관계 설정 시 주요하게 활용

### FK(Foreign Key)
---
- 외래 키
- 한 테이블의 속성 중 다른 테이블의 레코드를 식별할 수 있는 키
- 다른 테이블의 기본 키를 참조
- 참조하는 테이블의 속성 1개의 값은, 참조되는 측 테이블의 레코드 값에 대응 됨
- 각 레코드에서 서로 다른 테이블 간의 관계를 만드는 데 사용할 수 있음
# SQL
---
> Structured Query Language
- 관계형 데이터베이스에서 데이터를 관리하기 위해 사용하는 언어
### Commands
---
- 특성에 따라 다음 세 가지 그룹으로 분류
    1. DDL (Data Definition Language)
        - 관계형 데이터베이스 구조(테이블, 스키마)를 정의(생성, 수정 및 삭제)하기 위한 명령어
        - CREATE, DROP, ALTER
    2. DML (Data Manipulation Language)
        - 데이터를 조작(추가, 조회, 변경, 삭제)하기 위한 명령어
        - INSERT, SELECT, UPDATE, DELETE
    3. DCL (Data Control Language)
        - 데이터의 보안, 수행제어, 사용자 권한 부여 등을 정의 하기 위한 명령어
        - GRANT, REVOKE, COMMIT, ROLLBACK
- SQLite는 파일로 관리되는 DB이기 때문에 SQL을 이용한 접근 제한이 아닌 운영 체제의 파일 접근 권한으로만 제어가능, SQLite에는 권한 설정을 담당하는 GRANT(권한부여)와 REVOKE(권한회수)는 지원하지 않아 DCL부분은 우선 생략
### Syntax
---
```
SELECT column_name FROM table_name;
```
- 모든 SQL문(statement)는 SELECT, INSERT, UPDATE 등과 같은 키워드로 시작하고, 하나의 statement는 세미콜론(;)으로 끝남
    - 세미콜론은 각 SQL문을 구분하는 표준 방법
- SQL 키워드는 대소문자를 구분하지 않음
    - 즉, SELECT와 select는 SQL문에서 동일한 의미
    - 하지만 대문자로 작성하는 것을 권장
- Statement (문)
    - 독립적으로 실행할 수 있는 완전한 코드 조각
    - statement는 clause로 구성됨
- Clause (절)
    - statement의 하위 단위
## DDL
---
> 사전 준비
1. vscode SQLite 확장프로그램 설치
2. 데이터베이스 mydb.sqlite3 파일 생성
3. DDL.sql 파일 생성
4. vscode 실행 후 DDL.sql 화면에서 마우스 우측 버튼 -> Use Database 선택
5. 데이터베이스 목록에서 mydb.sqlite3 선택
### CREATE TABLE
---
- "Create a new table in the database"
- 데이터베이스에 새 테이블을 만듦
> contacts 테이블 생성
```sql
-- DDL.sql

CREATE TABLE contacts (
    name TEXT NOT NULL,
    age INTEGER NOT NULL,
    email TEXT NOT NULL UNIQUE
);
```
- Query 실행
    - 실행하고자 하는 명령문에 커서를 두고 마우스 우측 버튼 -> 'Run Selected Query' 선택
    - 명령문을 모두 선택 할 필요 없으며, 실행하고자 하는 명령문 안에 커서가 올라가 있으면 가능
- 쿼리 실행 후 테이블 및 스키마 확인
### Data Types
---
1. NULL
    - NULL value
    - 정보가 없거나 알 수 없음을 의미
2. INTEGER
    - 정수
    - 크기에 따라 0, 1, 2, 3, 4, 6 또는 8바이트와 같은 가변 크기를 가짐
3. REAL
    - 실수
    - 8바이트 부동 소수점을 사용하는 10진수 값이 있는 실수
4. TEXT
    - 문자 데이터
5. BLOB (Binary Large Object)
    - 입력된 그대로 저장된 데이터 덩어리 (대용 타입 없음)
    - 바이너리 등 멀티미디어 파일
    - 예시
        - 이미지 데이터
- SQLite에는 별도의 Boolean 타입이 없음
    - 대신 정수 0(false)과 1(true)로 저장됨
### Constraints
---
- 제약조건
- 입력하는 자료에 대해 제약을 정함
- 제약에 맞지 않다면 입력이 거부됨
- 사용자가 원하는 조건의 데이터만 유지하기 위한 즉, 데이터의 무결성을 유지하기 위한 보편적인 방법으로 테이블의 특정 컬럼에 설정하는 제약
> 데이터 무결성
- 데이터 베이스 내의 데이터에 대한 정확성, 일관성을 보장하기 위해 데이터 변경 혹은 수정 시 여러 제한을 두어 데이터의 정확성을 보증하는 것
    - 무결성이란 데이터의 정확성, 일관성을 나타냄
- 데이터베이스에 저장된 데이터의 무결성을 보당하고 데이터베이서의 상태를 일관되게 유지하는 것이 목적
> 종류
1. NOT NULL
    - 컬럼이 NULL값을 허용하지 않도록 지정
    - 기본적으로 테이블의 모든 컬럼은 NOT NULL제약조건을 명시적으로 사용하는 경우를 제외하고는 NULL값을 허용함
2. UNIQUE
    - 컬럼의 모든 값이 서로 구별되거나 고유한 값이 되도록 함
3. PRIMARY KEY
    - 테이블에서 행의 고유성을 식별하는 데 사용되는 컬럼
    - 각 테이블에는 하나의 기본 키만 있음
    - 암시적으로 NOT NULL제약조건이 포함되어 있음
```SQL
CREATE TABLE table_name (
    id INTEGER PRIMARY KEY,
    ...
);
```
4. AUTOINCREMENT
    - 사용되지 않은 값이나 이전에 삭제된 행의 값을 재사용하는 것을 방지
    - INTEGER PRIMARY KEY 다음에 작성하면 해당 rowid를 다시 재사용하지 못하도록 함
    - Django에서 테이블 생성 시 id컬럼에 기본적으로 사용하는 제약조건
```SQL
CREATE TABLE table_name (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    ...
);
```
> rowid
- 테이블을 생성할 때 마다 rowid라는 암시적 자동 증가 컬럼이 자동으로 생성됨
- 테이블의 행을 고유하게 식별하는 64비트 부호 있는 정수 값
- 테이블에 새 행을 삽입할 때마다 정수 값을 자동으로 할당
    - 값은 1에서 시작
    - 데이터 삽입 시에 rowid 또는 INTEGER PRIMARY KEY 컬럼에 명시적으로 값이 지정되지 않은 경우, SQLite는 테이블에서 가장 큰 rowid보다 하나 큰 다음 순차 정수를 자동으로 할당(AUTOINCREMENT와 관계 없이)
- 만약 INTEGER PRIMARY KEY 키워드를 가진 컬럼을 직접 만들면 이 컬럼은 rowid 컬럼의 별칭(alias)이 됨
    - 즉, 새 컬럼 이름으로 rowid에 액세스 할 수 있으며 rowid 이름으로도 여전히 액세스가능
- 데이터가 최대 값에 도달하고 새 행을 삽입하려고 하면 SQLite는 사용되지 않는 정수를 찾아 사용
- 만약 SQLite가 사용되지 않은 정수를 찾을 수 없으면 SQLITE_FULL에러가 발생
    - 또한 일부 행을 사겢하고 새 행을 삽입하면 SQLite는 삭제된 행에서 rowid값을 재사용하려고 시도

### ALTER TABLE
---
- "Modify the structure of an existing table"
- 기존 테이블의 구조를 수정(변경)
- SQLite의 ALTER TABLE문을 사용하면 기존 테이블을 다음과 같이 변경할 수 있음
    1. Rename a table
    2. Rename a column
    3. Add a new column to a table
    4. Delete a column

> Rename a table
```sql
ALTER TABLE table_name RENAME TO new_table_name;
```
> Rename a column
```sql
ALTER TABLE table_name RENAME COLUMN column_name TO new_column_name;
```
> Add a new column to a table
```sql
ALTER TABLE table_name ADD COLUMN column_name column_definition;
```
- 테이블에 기존 데이터가 있을 경우 에러 발생
- 새롭게 추가되는 컬럼에는 값이 없기 때문에 NULL로 작성됨
- DAFAULT제약조건을 사용해 오류 해결 가능
```sql
ALTER TABLE new_contacts ADD COLUMN address TEXT NOT NULL DEFAULT 'no address';
```
- 기존의 데이터의 address컬럼의 값은 'no address'로 저장됨
> Delete a column
```sql
ALTER TABLE table_name DROP COLUMN column_name;
```
- 삭제하지 못하는 경우
    - 컬럼이 다른 부분에서 참조되는 경우
        - FOREIGN KEY 제약조건에서 사용되는 경우
    - PRIMARY KEY인 경우
    - UNIQUE 제약조건이 있는 경우

### DROP TABLE
---
- "Remove a table from the database"
- 데이터 베이스에서 테이블을 제거
```sql
DROP TABLE table_name;
```
- 존재하지 않는 테이블을 제거하면 SQLite에서 오류 발생
```
no such table: table_name
```
> 특징
- 한 번에 하나의 테이블만 삭제할 수 있음
- 여러 테이블을 제거하려면 여러 DROP TABLE 문을 실행해야 함
- DROP TABLE 문은 실행 취소하거나 복구할 수 없음
    - 각별히 주의해서 수행해야 함
## DML
---
> 개요
- DML을 통해 데이터를 조작(CRUD)
- INSERT, SELECT, UPDATE, DELETE
> command-line program - "sqlite3"
- SQL 문 및 commands를 사용하여 SQLite 데이터베이스와 상호 작용할 수 있는 간단한 command-line tool
- 실행 화면
```
$ sqlite3
SQLite version 3.41.2 2023-03-22 11:56:21
Enter ".help" for usage hints.
Connected to a transient in-memory database.
Use ".open FILENAME" to reopen on a persistent database.
sqlite>
```
> 사용하기
- 시작하기
```
$ sqlite3
```
- db 파일 열기
```
sqlite> .open mydb.sqlite3
```
```
$ sqlite3 mydb.sqlite3
```
> 종료하기
```
sqlite> .exit
```
### CSV 파일을 SQLite 테이블로 가져오기
---
1. 테이블 생성
```sql
CREATE TABLE users (
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    age INTEGER NOT NULL,
    country TEXT NOT NULL,
    phone TEXT NOT NULL,
    balance INTEGER NOT NULL
);
```
2. DB 파일 열기
```
$ sqlite3 mydb.sqlite3
```
3. 모드를 csv로 설정
```
sqlite> .mode csv
```
4. .import 명령어를 사용하여 csv 데이터를 테이블로 가져오기
```
sqlite> .import users.csv users
```
5. 데이터 확인
## Simple query
---
- SELECT문을 사용하여 간단하게 단일 테이블에서 데이터를 조회
### SELECT statement
---
```sql
SELECT column1, column2 FROM table_name;
```
- 특정 테이블에서 데이터를 조회하기 위해 사용
- 문법 규칙
    1. SELECT절에서 컬럼 또는 쉼표로 구분된 컬럼 목록을 지정
    2. FROM절(clause)에서 데이터를 가져올 테이블을 지정
- SELECT문은 SQLite에서 가장 복잡한 문
- 다양한 절과 함께 사용할 수 있음
    - ORDER BY
    - DISTINCT
    - WHERE
    - LIMIT
    - LIKE
    - GROUP BY
- 전체 데이터 조회하기
```sql
SELECT * FROM users;
```
- 이름과 나이 조회하기
```sql
SELECT first_name, age FROM users;
```
- rowid와 이름 조회하기
```sql
SELECT rowid, first_name FROM users;
```

### Sorting rows
---
- ORDER BY절을 사용해 쿼리의 결과를 정렬
```sql
SELECT select_list FROM table_name ORDER BY column_1 ASC, column_2 DESC;
```
- SELECT문에 추가하여 결과를 정렬
- ORDER BY절은 FROM절 뒤에 위치함
- 하나 이상의 컬럼을 기준으로 결과를 오름차순, 내림차순으로 정렬할 수 있음
- 이를 위해 ORDER BY절 다음에 'ASC' 또는 'DESC' 키워드를 사용
    - ASC : 오름차순 (기본 값)
    - DESC : 내림차순
- 이름과 나이를 나이가 어린 순으로 정렬
```sql
SELECT first_name, age FROM users ORDER BY age ASC;
SELECT first_name, age FROM users ORDER BY age;
```
- 이름과 나이를 나이가 많은 순으로 정렬
```sql
SELECT first_name, age FROM users ORDER BY age DESC;
```
- 이름, 나이, 계좌 잔고를 나이가 어린순으로, 만약 같은 나이라면 계좌 잔고가 많은 순으로 정렬해서 조회
```sql
SELECT first_name, age, balance FROM users ORDER BY age, balance DESC;
```
- ORDER BY절은 하나 이상의 컬럼을 정렬할 경우 첫 번째 열을 사용하여 행을 정렬하고, 그런 다음 두번째 컬럼을 사용하여 정렬 되어있는 행을 정렬하는 방식
- 즉, 먼저 age를 기준으로 먼저 오름차순으로 정렬하고, 이 결과를 balance를 기준으로 내림차순으로 정렬한 것
> Sorting NULLs
- NULL의 정렬 방식
- 정렬과 관련하여 SQLite는 NULL을 다른 값보다 작은 것으로 간주
- 즉, ASC를 사용하는 경우 결과의 시작 부분에 NULL이 표시되고, DESC를 사용하는 경우 결과의 끝에 NULL이 표시됨
### Filtering data
---
- 데이터를 필터링 하여 중복 제거, 조건 설정 등 쿼리를 제어
- Clause
    - SELECT DISTINCT
    - WHERE
    - LIMIT
- Operator
    - LIKE
    - IN
    - BETWEEN
> SELECT DISTINCT clause
```sql
SELECT DISTINCT select_list FROM table_name;
```
- 조회 결과에서 중복된 행을 제거
- DISTINCT 절은 SELECT에서 선택적으로 사용할 수 있는 절
- 문법 규칙
    1. DISTINCT 절은 SELECT 키워드 바로 뒤에 나타나야 함
    2. DISTINCT 키워드 뒤에 컬럼 또는 컬럼 목록을 작성
- 모든 지역 조회
```SQL
SELECT country FROM users;
```
- 중복없이 모든 지역 조회
```sql
SELECT DISTINCT country FROM users;
```
- 중복없이 지역순으로 오름차순 정렬하여 모든 지역 조회
```sql
SELECT DISTINCT country FROM users ORDER BY country;
```
- 이름과 지역이 중보 없이 모든 이름과 지역 조회
    - 각 컬럼의 중복을 따로 계산하는 것이 아닌, 두 컬럼을 하나의 집합으로 보고 중복을 제거
```sql
SELECT DISTINCT first_name, country FROM users;
```
- 이름과 지역 중복 없이 지역 순으로 오름차순 정렬하여 모든 이름과 지역 조회
```sql
SELECT DISTINCT first_name, country FROM users ORDER BY country;
```
> NULL with DISTINCT
- SQLite는 NULL 값을 중복으로 간주
- NULL값이 있는 컬럼에 DISTINCT절을 사용하면 SQLite는 NULL값의 한 행을 유지
> WHERE clause
```sql
SELECT column_list FROM table_name WHERE search_condition;
```
- "Specify the search condition for rows returned by the query"
- 조회 시 특정 검색 조건을 지정
- WHERE절은 SELECT문에서 선택적으로 사용할 수 있는 절
    - SELECT문 외에도 UPDATE 및 DELETE문에서 WHERE절을 사용할 수 있음
- FROM절 뒤에 작성
```SQL
WHERE column_1 = 10
WHERE column_2 LIKE 'Ko%'
WHERE column_3 IN (1,2)
WHERE column_4 BETWEEN 10 AND 20
```
> SQLite comparison operators (비교연산자)
- 두 표현식이 동일한지 테스트
    - =
    - <> or !=
    - <
    - \>
    - <=
    - \>=
> SQLite logical operators (논리연산자)
- 일부 표현식의 truth를 테스트할 수 있음
- 1, 0 또는 NULL 값을 반환
- SQLite는 Boolean 데이터 타임을 제공하지 않으므로 1을 TRUE를 의미하고 0은 FALSE를 의미
- ALL, AND, ANY, BETWEEN, IN, LIKE, NOT, OR 등
- 나이가 30살 이상인 사람들의 이름, 나이, 계좌 잔고 조회
```SQL
SELECT first_name, age, balance FROM users WHERE age >= 30;
```
- 나이가 30살 이상이고 계좌 잔고가 50만원 초과인 사람들의 이름, 나이, 계좌 잔고 조회
```sql
SELECT first_name, age, balance FROM users WHERE age >= 30 AND balance > 500000;
```
> LIKE operator
- "Query data based on pattern matching"
- 패턴 일치를 기반으로 데이터를 조회
- SELECT, DELETE, UPDATE문의 WHERE절에서 사용
- 기본적으로 대소문자를 구분하지 않음
    - 'A' LIKE 'a'는 TRUE
- SQLite는 패턴 구성을 위한 두 개의 와일드카드(wildcards)를 제공
1. %(percent)
    - 0개 이상의 문자가 올 수 있음을 의미
2. _(underscore)
    - 단일(1개)문자가 있음을 의미
> '%'wildcard 예시
- '영%' 패턴은 영으로 시작하는 모든 문자열과 일치(영, 영미, 영미리 등)
- '%도' 패턴은 도로 끝나는 모든 문자열과 일치(도, 수도, 경기도 등)
- '%강원%' 패턴은 강원을 포함하는 모든 문자열과 일치(강원, 강원도, 강원도에 살아요 등)
> '_'wildcard 예시
- '영_'패턴은 영으로 시작하고 총 2자리인 문자열과 일치(영미, 영수, 영호 등)
- '_도'패턴은 도로 끝나고 총 2자리인 문자열과 일치(수도, 과도 등)
> "wildcards" character
- 파일을 지정할 때, 구체적인 이름 대신에 여러 파일을 동시에 지정할 목적으로 사용하는 특수 기호
    - *, ? 등
- 주로 특정한 패턴이 있는 문자열 혹은 파일을 찾거나, 긴 이름을 생략할 때 쓰임
- 텍스트 값에서 알 수 없는 문자를 사용할 수 있는 특수 문자로, 유사하지만 동일한 데이터가 아닌 여러 항목을 찾기에 매우 편리한 문자
- 지정된 패턴 일치를 기반으로 데이터를 수집하는 데도 도움이 될 수 있음
---
- 이름에 '호'가 포함되는 사람들의 이름과 성 조회
```sql
SELECT first_name, last_name FROM users WHERE first_name LIKE '%호%';
```
- 이름이 '준'으로 끝나는 사람들의 이름 조회
```sql
SELECT first_name FROM users WHERE first_name LIKE '%준';
```
- 서울 지역 전화번호를 가진 사람들의 이름과 전화번호 조회
```sql
SELECT first_name, phone FROM users WHERE phone LIKE '02-%';
```
- 나이가 20대인 사람들의 이름과 나이 조회
```SQL
SELECT first_name, age FROM users WHERE age LIKE '2_';
```
- 전화번호 중간 4자리가 51로 시작하는 사람들의 이름과 전화번호 조회
```SQL
SELECT first_name, phone FROM users WHERE phone LIKE '%-51__-%';
```
> IN operator
- "Determine whether a value matches any value in a list of values"
- 값이 값 목록 결과에 있는 값과 일치하는지 확인
- 표현식이 값 목록의 값과 일치하는지 여부에 따라 true 또는 false를 반환
- IN 연산자의 결과를 부정하려면 NOT IN 연산자를 사용
- 경기도 혹은 강원도에 사는 사람들의 이름과 지역 조회
```sql
SELECT first_name, country FROM users WHERE country IN ('경기도', '강원도');
```
```SQL
SELECT first_name, country FROM users WHERE country = '경기도' OR country = '강원도';
```
- 경기도 혹은 강원도에 살지 않는 사람들의 이름과 지역 조회하기
```sql
SELECT first_name, country FROM users WHERE country NOT IN ('경기도', '강원도');
```
> BETWEEN operator
- 값이 값 범위에 있는지 테스트
- 값이 지정된 범위에 있면 true를 반환
- SELECT, DELETE, 및 UPDATE문의 WHERE절에서 사용할 수 잇음
- BETWEEN연산자의 결과를 부정하려면 NOT BETWEEN 연산자를 사용
- 나이가 20살 이상, 30살 이하인 사람들의 이름과 나이 조회
```SQL
SELECT first_name, age FROM users WHERE age BETWEEN 20 AND 30;
```
```SQL
SELECT first_name, age FROM users WHERE age >= 20 AND age <= 30;
```
- 나이가 20살 이상, 30살 이하가 아닌 사람들의 이름과 나이 조회
```sql
SELECT first_name, age FROM users WHERE age NOT BETWEEN 20 and 30;
```
```sql
SELECT first_name, age FROM users WHERE age < 20 OR age > 30;
```
> LIMIT clause
```sql
SELECT column_list FROM table_name LIMIT row_count;
```
- "Constrain the number of rows returned by a query"
- 쿼리에서 반환되는 행 수를 제한
- SELECT 문에서 선택적으로 사용할 수 있는 절
- row_count는 반환되는 행 수를 지정하는 향의 정수를 의미
- 첫 번째 부터 열 번째 데이터까지 rowid와 이름 조회하기
```sql
SELECT rowid, first_name FROM users LIMIT 10;
```
- 계좌 잔고가 가장 많은 10명의 이름과 계좌 잔고 조회하기
```sql
SELECT first_name, balance FROM users ORDER BY balance DESC LIMIT 10;
```
- 나이가 가장 어린 5명의 이름과 나이 조회
```SQL
SELECT first_name, age FROM users ORDER BY age LIMIT 5;
```
> OFFSET keyword
- LIMIT 절을 사용하면 첫 번째 데이터 부터 지정한 수 만큼의 데이터를 받아올 수 있지만, OFFSET과 함께 사용하면 특정 지정된 위치에서부터 데이터를 조회할 수 있음
- 11번째부터 20번째 데이터의 rowid와 이름 조회
```sql
SELECT rowid, first_name FROM users LIMIT 10 OFFSET 10;
```