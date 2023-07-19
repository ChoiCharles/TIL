# component

- 컴포넌트는 복잡한 html코드를 한 단어로 치환할 수 있다
- 함수를 만들어 불러오는 것과 같다


## 컴포넌트 제작
- 컴포넌트로 변환할 모달창을 제작
```js
// App.js

<div className="modal">
  <h4>제목</h4>
  <p>날짜</p>
  <p>상세내용</p>
</div>
```
```css
/* App.css */

.modal {
  margin-top: 20px;
  padding: 20px;
  background: #eee;
  text-align: left;
}
```

- 이렇게 만들어진 코드를 아래와 같이 변환할 수 있다
```js
// App.js

function Modal(){
  return (
    <div className="modal">
      <h4>제목</h4>
      <p>날짜</p>
      <p>상세내용</p>
    </div>
  )
}
```
- 이렇게 만들어진 함수(function)를 component라 부르며, 아래와 같은 방법으로 사용할 수 있다
```js
// App.js

function App () {
  return (
    <div>
      <Modal></Modal>
      // or
      <Modal/>
    </div>
  )
}
```
- 컴포넌트 이름의 첫 글자는 대문자로 작성한다
- 반드시 하나의 div 태그 안에 작성한다
  - 만약 의미없는 div 태그를 사용하기 싫다면 fragments 문법을 사용해 div를 생략한 <></> 빈 태그를 사용할 수 있다
- 반드시 하나의 div 태그 안에 작서해야 하므로 2개의 div 태그를 사용하고 싶다면, 2개의 div 태그를 div 태그로 감싸줘야 한다
- App 또한 하나의 컴포넌트다

> arrow function을 사용해 작성해도 된다
```js
let Modal = () => {
  return (<div></div>)
}

const Modal = () => {
  return (<div></div>)
}
```

## 컴포넌트를 만드는 상황
- 기본적으로 함수를 사용하는 상황과 동일하다
- 반복해서 사용하는 html 코드들
- 내용이 자주 변경될 것 같은 html의 부분
- 다른 페이지를 만들고 싶을 때
- 한 페이지를 다른 팀원과 협업해 작성할 때

## 단점
- html코드를 깔끔하게 작성하기 위해 컴포넌트를 계속해서 만든다면 이후 관리가 어려워진다
- state를 쓰기위한 과정이 복잡해진다
  - props 문법을 사용해 가능하지만 복잡해지는건 사실이다