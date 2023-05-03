# DataBase
## Grouping data
---
### Aggregate function (집계함수)
- 값 집합의 최대값, 최소값, 평균, 합계 및 갯수를 계산
- 값 집합에 대한 계산을 수행하고 단일 값을 반환
    - 여러 행으로부터 하나의 결과 값을 반환하는 함수
- SELECT문의 GROUP BY절과 함께 종종 사용됨
- 제공되는 함수
    - AVG(), COUNT(), MAX(), MIN(), SUM()
- AVG(), COUNT(), MAX(), MIN(), SUM()는 숫자를 기준으로 계산이 되어져야 하기 때문에 반드시 컬럼의 데이터 타입이 숫자(INTEGER)일 때만 사용 가능

- 전체 행 수 조회
```sql
SELECT COUNT(*) FROM users;
```

- 전체 유저의 평균 balance
```sql
SELECT AVG(balance) FROM users;
```

- 현재 모든 유저의 지역을 조회
```sql
SELECT DISTINCT country FROM users;
```

- 각 지역별 평균 중 특정 값 조회
```sql
SELECT country, avg(balance) FROM users WHERE country="전라북도";
```

### GROUP BY
---
```sql
SELECT column_1, aggregate_function(column_2) FROM table_name GROUP BY column_1, column_2;
```
- "Make a set of summary rows from a set of rows"
- 특정 그룹으로 묶인 결과를 생성
- 선택된 컬럼 값을 기준으로 데이터(행)들의 공통 값을 묶어서 결과로 나타냄
- SELECT문에서 선택적으로 사용가능한 절
- SELECT문의 FROM절 뒤에 작성
    - WHERE절이 포함된 경우 WHERE절 뒤에 작성해야 함
- 각 그룹에 대해 MIN, MAX, SUM, COUNT 또는 AVG와 같은 집계 함수(aggregate function)를 적용하여 각 그룹에 대한 추가적인 정보 제공가능

- 지역별 평균 balance
```sql
SELECT country, avg(balance) FROM users GROUP BY country;
```

- 지역별 평균 balance + 정렬
```sql
SELECT country, avg(balance) FROM users GROUP BY country ORDER BY avg(balance) DESC;
```

- 30살 이상인 사람들의 평균 나이
```SQL
SELECT AVG(age) FROM users WHERE age>=30;
```

- 그룹별로 포함되는 데이터의 수를 조회
```sql
SELECT country, COUNT(*) FROM users GROUP BY country;
```
> COUNT 참고사항
- 이전 쿼리에서 COUNT(), COUNT(age), COUNT(last_name)등 어떤 컬럼을 넣어도 결과는 같음
- 현재 쿼리에서는 그룹화된 country를 기준으로 카운트 하는 것이기 때문에 어떤 컬럼을 카운트해도 전체 개수는 동일하기 때문

- 각 성씨가 몇 명씩 있는지 조회
```sql
SELECT last_name, COUNT(*) AS number_of_name FROM users GROUP BY last_name;
```


## Changing data
---
- INSERT
- UPDATE
- DELETE

### INSERT
---
```SQL
INSERT INTO table_name (column1, column2, ...) VALUES (value1, value2, ...);
```
- 새 행을 테이블에 삽입
- 문법 규칙
    1. 먼저 INSERT INTO 키워드 뒤에 데이터를 삽입할 테이블의 이름을 지정
    2. 테이블 이름 뒤에 쉼표로 구분된 컬럼 목록을 추가
        - 컬럼 목록은 선택사항이지만 컬럼 목록을 포함되는 것이 권장됨
    3. VALUES 키워드 뒤에 쉼표로 구분된 값 목록을 추가
        - 만약 컬럼 목록을 생략하는 경우 값 목록의 모든 컬럼에 대한 값을 지정해야 함
        - 값 목록의 값 개수는 컬럼 목록의 컬럼 개수와 같아야 함

- 단일 행 삽입
```SQL
INSERT INTO classmates (name, age, address) VALUES ('홍길동', 23, '서울');
```
```SQL
INSERT INTO classmates VALUES ('홍길동', 23, '서울');
```

- 여러 행 삽입
```SQL
INSERT INTO classmates
VALUES
('김철수', 30, '경기'),
('이영미', 31, '강원'),
('박진성', 26, '전라'),
('최지수', 12, '충청'),
('정요한', 28, '경상');
```

### UPDATE
---
```SQL
UPDATE table_name
SET column_1 = new_value_1,
    column_2 = new_value_2
WHERE
    serch_condition;
```
1. UPDATE절 이후에 업데이트 할 테이블을 지정
2. SET절에서 테이블의 각 컬럼에 대해 새 값을 설정
3. WHERE절의 조건을 사용하여 업데이트할 행을 지정
    - WHERE절은 선택 사항. 생략하면 UPDATE문은 테이블의 모든 행에 있는 데이터를 업데이트 함
4. 선택적으로 ORDER BY 및 LIMIT절을 사용하여 업데이트 할 행 수를 지정 할 수도 있음

- 2번 데이터의 이름을 '김철수한무두루미', 주소를 '제주도'로 수정
```SQL
UPDATE classmates
SET name='김철수한무두루미',
    address='제주도'
WHERE rowid=2;
```

### DELETE
---
```SQL
DELETE FROM table_name WHERE search_condition;
```
- 테이블에서 행을 제거
- 테이블의 한 행, 여러 행 및 모든 행을 삭제할 수 있음
- 문법 규칙
    1. DELETE FROM 키워드 뒤에 행을 제거하려는 테이블의 이름을 지정
    2. WHERE절에 검색 조건을 추가하여 제거할 행을 식벌
        - WHERE절은 선택 사항이며, 생략하면 DELETE문은 테이블의 모든 행을 삭제
    3. 선택적으로 ORDER BY 및 LIMIT 절을 사용하여 삭제할 행 수를 지정 할 수도 있음

- 5번 데이터 삭제
```sql
DELETE FROM classmates WHERE rowid=5;
```

- 삭제 확인
```sql
SELECT rowid, * FROM classmates;
```

- 이름에 '영'이 포함되는 데이터 삭제
```sql
DELETE FROM classmates WHERE name LIKE '%영%';
```

- 테이블의 모든 데이터 삭제
```SQL
DELETE FROM classmates;
```

## 정규형
---
- 데이터베이스를 구조화 하는 방법론
- 데이터의 중복을 최소화하고 일관성과 무결성을 보장하기 위함
- 데이터의 구조를 더 좋은 구조로 바꾸는 것을 정규화라고 함
- 관계형 데이터베이스의 경우 6개의 정규형이 있음

> 제 1정규형
- 하나의 속성에는 값이 하나만 들어가야함

> 제 2정규형
- 테이블의 기본키에 종속되지 않는 컬럼은 테이블이 분리 되어야함
- 복합키(composite key)
    - 테이블에서 행을 유일하게 구분하기 위해 두 개 이상의 속성을 조합하여 사용하는 기본키

> 제 3정규형
- 다른 속성에 의존(종속)하는 속성은 따로 분리

## JOIN
---
- 테이블을 나누면 관리는 편해지지만 조회하기 위해서는 테이블을 하나로 만드는게 편하다
> CROSS JOIN
```sql
SELECT * FROM articles, users WHERE articles.userId=users.rowId;
SELECT * FROM articles, users WHERE userId=usert.rowId;
```
- 데이터가 많아지면 잘 쓰지 않는다
### INNER JOIN
---
```sql
SELECT * FROM articles INNER JOIN users ON userId=users.rowId;
```
- CROSS JOIN보다 명확
- 단, 특정 값끼리 매칭이 되야지만 한다
    - 매칭되지 않는 값들은 데이터에 누락된다
### LEFT(OUTER) JOIN
---
```SQL
SELECT * FROM articles LEFT JOIN users ON userId=users.rowId;
```
- JOIN을 기준으로 왼쪽 테이블을 기준으로 오른쪽 테이블결합
- 데이터가 없다면 NULL 반환
### RIGHT(OUTER) JOIN
---
```SQL
SELECT * FROM articles RIGHT JOIN users on userId=users.rowId;
```
- LEFT JOIN과 반대로 오른쪽 테이블을 기준으로 반환한다
- SQLite3에서는 지원 x