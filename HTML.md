# 웹 사이트
웹 사이트란 웹 브라우저를 통해서 접속하는 웹 페이지들의 모음

즉, 링크를 통해 여러 웹 페이지를 연결한 것이 웹 사이트

### 구성요소
 - HTML : 구조(레이아웃)
 - HTML + CSS : 표현(스타일링)
 - HTML + CSS + JS : 동작(인터렉션)

### VS Code 확장 프로그램
- Auto Rename Tag
- Highlight Matching Tag
- Auto Close Tag

# HTML(Hyper Text Markup Language)
> 웹페이지를 구조화하기 위한 언어

MDN, W3Schoools 참고
### Hyper Text
- 참조(하이퍼링크)를 통해 사용자가 한 문서에서 다른 무서로 즉시 접근할 수 있는 텍스트
### Markup Language
- 태그 등을 이용해서 문서나 데이터의 구조를 명시하는 언어

```html
<!DOCTYPE html>                 # html : 문서의 최상위(root)요소
<html lang="ko">
<head>                          # head : 문서 메타에디터 요소
    <meta charset="UTF-8">      # 문서 제목, 인코딩, 스타일, 외부 파일 로딩 등
    <title>Document</title>     # 일반적으로 브라우저에 나타나지 않는 내용
</head>
<body>                          # body : 문서 본문 요소
                                # 실제 화면 구성과 관련된 내용
</body>
</html>
```
### head 예시
```
- <title> : 브라우저 상단 타이틀
- <link> : 외부 리소스 연결 요소(CSS파일 등)
- <style> : CSS 직접 작성
```

## 요소(element)
---
```
- <h1> : 여는(시작)태그
- </h1> : 닫는(종료)태그
```
- HTML요소는 시작 태그와 종료 태그 그리고 태그 사이에 위치한 내용으로 구성
    - 태그(Element, 요소)는 내용을 감싸는 것으로 그 정보의 성격과 의미를 정의
- 내용이 없는 태그
    - br, hr, img, input, link, meta
- 요소는 중첩(nested)될 수 있음
    - 요소의 중첩을 통해 하나의 문서를 구조화
    - 여는 태그와 닫는 태그의 쌍을 잘 확인해야함
        - 오류는 반환하는 것이 아닌 그냥 레이아웃이 깨진 상태로 출력되기 때문에, 디버깅이 힘들어 질 수 있음
## 속성(attribute)
---
```
<a href="https://google.com"></a>
   속성명       속성값
```
- 각 태그별로 사용할 수 있는 속성이 다르다
- 속성은 속성명과 속성값으로 이루어져 있다
> 속성 작성방식 통일
```
<a href="https://google.com"></a>
      공백X             쌍따옴표 사용
```

- 속성을 통해 태그의 부가적인 정보를 설정할 수 있음
- 요소는 속성을 가질 수 있으며, 경로나 크기와 같은 추가적인 정보를 제공
- 요소의 시작 태그에 작성하며 보통 이름과 값이 하나의 쌍으로 존재
- 태그와 상관없이 사용 가능한 속성(HTML Global Attribute)들도 있음


> HTML Global Attribute
- 모든 HTML요소가 공통으로 사용할 수 있는 대표적인 속성(몇몇 요소 제외)
    - id : 문서 전체에서 유일한 고유 식별자 지정
    - class : 공백으로 구분된 해당 요소의 클래스의 목록(CSS, JS에서 요소를 선택하거나 접근)
    - style : inline 스타일
> HTML 코드 예시
```html
<!DOCTYPE html>
<html lang="en>
<head>
    <meta charset="UTF-8>
    <title>Hello, HTML</title>
</head>
<body>
    <!-- 주석 -->
    <h1>나의 첫번째 HTML</h1>
    <p>본분.</p>
    <span>인라인 요소</span>
    <a href="https://www.nave.com">네이버로 이동</a>
</body>
</html>
```

## 문서 구조화
---
```
<p></p> : 하나의 문단
<hr> : 주제를 분리하기 위한 수평선
<div></div> : 의미없는 블록 레벨 컨테이너
```
```
<form>
```
- form은 사용자의 정보(데이터)를 제출하기 위한 영역
```
<input>
```
- 다양한 타입을 가지는 입력 데이터 유형과 위젯이 제공됨
> input label
- label을 클릭하여 input 자체의 초점을 맞추거나 활성화 시킬 수 있음
    - 사용자는 선택할 수 있는 영역이 늘어나 웹/모바일(터치) 환경에서 편하게 사용할 수 있음
    - label과 input입력의 관계가 시각적 뿐만 아니라 화면리더기에서도 label을 읽어 쉽게 내용을 확인 할 수 있도록 함
- input에 id속성을, label에는 for속성을 활용하여 상호 연관을 시킴
```html
<label for="agreement">개인정보 수집에 동의합니다.</label>
<input type="checkbox" name="agreement" id="agreement">
```
> input 유형 - 일반
- 일반적으로 입력을 받기 위하여 제공되며 type으로 HTML기본 검증 혹은 추가 속성을 활용할 수 있음
    - text:일반 텍스트 입력
    - password:입력 시 값이 보이지 않고 문자를 특수기호(*)로 표현
    - email:이메일 형식이 아닌 경우 form 제출 불가
    - number:min, max, step속성을 활용하여 숫자 범위 설정 가능
    - file:accept 속성을 활용하여 파일 타입 지정 가능
> input 유형 - 항목 중 선택
- label로 선택에 대한 내용을 작성하고, 항목으로 선택할 수 있는 input을 제공
- 동일한 범주에 속하는 항목을은 name을 통일하고, 선택된 항목의 값은 value로 지정함
    - checkbox : 다중 선택
    - radio : 단일 선택
> input 유형 - 종합
- input요소의 동작은 type에 따라 달라지므로, MDN에서 각각의 내용을 숙지할 것