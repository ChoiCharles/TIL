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

# CSS 상속
- CSS는 상속을 통해 부모 요소의 속성을 자식에게 상속한다
    - 속성(Property)중에는 상속이 되는 것과 되지 않는 것들이 있다