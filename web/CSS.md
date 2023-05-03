# CSS (Cascading Style Sheets)
- 스타일을 지정하기 위한 언어
- 선택한 후, 스타일을 지정
> CSS구문
```css
h1{
    color:blue;
    font-size:15px;
}
```
- h1 : 선택자(Selector)
- color:blue; : 선언(Declaration)
- font-size : 속성(Property)
- 15px; : 값(value)
### CSS
- CSS구문은 선택자를 통해 스타일을 지정할 HTML요소를 선택
- 중괄호 안에서는 속성과 값, 하나의 쌍으로 이루어진 선언을 진행
- 각 쌍은 선택한 요소의 속성, 속성에 부여할 값을 의미
    - 속성(Property):어떤 스타일 기능을 변경할지 결정
    - 값(Value):어떻게 스타일 기능을 변경할지 결정
> 정의 방법
- 인라인(inline)
- 내부 참조(embedding)
- 외부 참조(link file) - 분리된 CSS파일
### 인라인
```html
<h1 style="color: blue; font-size: 100px;">Hello</h1>
```
- 해당 태그에 직접 style속성을 활용
### 내부 참조
```html
<head>
    <style>
        h1{
            color:blue;
            font-size:15px;
        }
    </style>
</head>
```
- head태그 내에 style을 지정
### 외부 참조
```html
<head>
    <title>mySite</title>
    <link rel="stylesheet" href="mystyle.css">
</head>
```
```css
h1{
    color:blue;
    font-size:15px;
}
```
- 외부 CSS파일을 head내 link를 통해 불러오기

# CSS Selectors
> 선택자(Selectors) 유형
- 기본 선택자
    - 전체 선택자(*), 요소(tag)선택자
    - 클래스(class) 선택자, 아이디(id) 선택자, 속성(attr) 선택자
- 결합자(Combinators)
    - 자손 결합자, 자식 결합자

```html
<style>
    /* 전체 선택자 */
    * {
        color: red;
    }

    /* 요소 선택자 */
    h2 {
        color: orange;
    }

    /* 클래스 선택자 */
    .green {
        color: green;
    }

    /* id 선택자 */
    #purple {
        color: purple;
    }

    /* 자식 선택자 */
    .box > p {
        font-size: 30px;
    }

    /* 자손 선택자 */
    .box p {
        color: blue;
    }
</style>
```
### 선택자 정리
- 요소 선택자
    - HTML 태그를 직접 선택
- 클래스(class)선택자
    - 마침표(.)문자로 시작하며, 해당 클래스가 적용된 항목을 선택
- 아이디(id)선택자
    - #문자로 시작하며, 해당 아이디가 적용된 항목을 선택
    - 일반적으로 하나의 문서에 1번만 사용. 여러번 사용해도 동작하지만, 단일 id를 사용하는 것을 권장
### 적용 우선순위
1. 중요도(Importance) - 사용시 주의
    - !important
2. 우선 순위(Specificity)
    - 인라인 > id > class, 속성 > 요소

### CSS 상속
- CSS는 상속을 통해 부모 요소의 속성을 자식에게 상속한다
    - 속성(Property)중에는 상속이 되는 것과 되지 않는 것들이 있다


## Box model
---
> 모든 요소는 박스모델이고, 위에서부터 아래로, 왼쪽에서 오른쪽으로 쌓인다
- 하나의 박스는 네 영역으로 이루어짐
    - content : 글이나 이미지 등 요소의 실제 내용
    - padding : 테두리 안쪽의 내부 여백 요소에 적용된 배경색, 이미지는 padding까지 적용
    - border : 테두리 영역
    - margin : 테두리 바깥의 외부 여백 배경색을 지정할 수 없다
> margin
```css
.margin{
    margin-top: 10px;
    margin-right: 20px;
    margin-bottom: 30px;
    margin-left: 40px;
}
# 전체 적용
.margin-1{
    margin: 10px;
}
# 상하 적용, 좌우 적용
.margin-2{
    margin: 10px 20px;
}
# 상 적용, 좌우 적용, 하 적용
.margin-3{
    margin: 10px 20px 30px;
}
# 시계방향 적용
.margin-4{
    margin: 10px 20px 30px 40px;
}
```
> padding
```css
.margin-padding{
    margin: 10px;
    padding: 30px;
}
```
> border
```css
.border{
    border-width: 2px;
    border-style: dashed;
    border-color: black;
}
.border{
    border: 2px dashed black;
}
```

### box-sizing
---
- 기본적으로 모든 요소의 box-sizing은 content-box
    - Padding을 제외한 순수 cotents 영역만을 box로 지정
- 다만, 우리가 일반적으로 영역을 볼 때는 border까지의 너비를 100px로 보는 것을 원함
    - 그 경우 box-sizing을 border-box로 설정

# 개발자 도구
### 크롬 개발자 도구
---
- 웹 브라우저 크롬에서 제공하는 개발과 관련된 다양한 기능을 제공
- 주요 기능
    - Elements - DOM 탐색 및 CSS 확인 및 변경
        - Styles - 요소에 적용된 CSS확인
        - Computed - 스타일이 계산된 최종 결과
        - Event Listeners - 해당 요소에 적용된 이벤트(JS)
    - Sources, Network, Performance, Application, Security, Audits 등

# CSS 디스플레이
> display에 따라 크기와 배치가 달라진다
- display: block
    - 줄 바꿈이 일어나는 요소(다른 elem를 밀어낸다)
    - 화면 크기 전체의 가로 폭을 차지한다
    - 블록 레벨 요소 안에 인라인 레벨 요소가 들어갈 수 있음
    - div / ul, ol, li / p / hr / form 등
- display: inline
    - 줄 바꿈이 일어나지 않는 행의 일부 요소
    - content를 마크업 하고 있는 만큼만 가로 폭을 차지한다
    - width, height, margin-top, margin-bottom을 지정할 수 없다
    - 상하 여백은 line-height로 지정한다
    - span / a / img / input, label / b,em, i, strong
- display: inline-block
    - block과 inline 레벨 요소의 특징을 모두 가짐
    - inline처럼 한 줄에 표시 가능하고, block처럼 width, height, margin 속성을 모두 지정할 수 있음
- display: none
    - 해당 요소를 화면에 표시하지 않고, 공간조차 부여되지 않음
    - 이와 비슷한 visibility: hidden은 해당 요소가 공간은 차지하나 화면에 표시만 하지 않는다


# CSS Position
- 문서 상에서 요소의 위치를 지정
    - static : 모든 태그이 기본 값 (기준 위치)
        - 일반적인 요소의 배치 순서에 따름
        - 부모 요소 내에서 배치될 때는 부모 요소의 위치를 기준으로 배치됨
    - 아래는 좌표 Property(top, bottom, left, right)를 사용하여 이동 가능
        - relative : 상대 위치
             - 자기 자신의 static위치를 기준으로 이동 (normal flow 유지)
             - 레이아웃에서 요소가 차지하는 공간은 static일 때와 같음 (normal position 대비 offset)
        - absolute : 절대 위치
            - 요소를 일반적인 문서 흐름에서 제거 후 레이아웃에 공간을 차지하지 않음 (normal flow에서 벗어남)
            - static이 아닌 가장 가까이 있는 부모/조상 요소를 기준으로 이동 (없는 경우 body)
        - fixed : 고정 위치
            - 요소를 일반적인 문서 흐름에서 제거 후 레이아웃에 공간을 차지하지 않음 (normal flow에서 벗어남)
            - 부모 요소와 관계없이 viewport를 기준으로 이동
                - 스크롤 시에도 항상 같은 곳에 위치함
        - sticky : 스크롤에 따라 static -> fixed로 변경
            - 속성을 적용한 박스는 평소에 문서 안에서 static상태와 같이 일반적인 흐름에 따르지만, 스크롤 위치가 임계점에 이르면 fixed와 같이 박스를 화면에 고정할 수 있는 속성