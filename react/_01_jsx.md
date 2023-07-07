# JSX
- react는 기존의 웹을 구성하는 html 대신 JSX를 사용한다
```js
// App.js

import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      // 샘플코드에서 div태그 하나만 남겨두고 지운 상태
    </div>
  );
}

export default App;
```
## 태그 class 지정
- html처럼 태그에 class를 지정할 수 있는데 JSX는 일종의 자바스크립트라서 class는 예약어로 지정되어 있다
- 따라서, 어떤 태그에 class를 지정하고 싶다면 className으로 지정할 수 있다
```js
// App.js

import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <div className="nav-tag">
      </div>
    </div>
  );
}

export default App;
```
## 변수 출력
- function과 return사이에 변수 정의 가능
```js
// App.js

import logo from './logo.svg';
import './App.css';

function App() {
  let name = "kim";
  const age = 20;
  return (
    <div className="App">
      <div className="nav-tag">
        <p>{ name }</p>
        <p>{ age }</p>
      </div>
    </div>
  );
}

export default App;
```
- 정의한 변수값을 출력하고 싶다면 중괄호를 이용해 출력한다
    - { 변수명 }
- 이 작업을 **데이터 바인딩**이라 하며, href, id, className, src 등 여러 태그들의 속성에도 적용할 수 있다
```js
// App.js

import logo from './logo.svg';
import './App.css';

function App() {
  const navName = "nav-tag";
  return (
    <div className="App">
      <div className={navName}>
      </div>
    </div>
  );
}

export default App;
```
## style 속성 추가
```js
<div className="App">
```
- style은 위 코드와는 달리 데이터바인딩을 하듯 중괄호로 감싸야 한다
- 다만, 중괄호 안에 객체의 형태로 style을 삽입해야 하기에 중괄호가 총 2번 사용된다
```js
<div style={{color:'red', fontSize='12px'}}>글자 스타일 적용</div>
```
- 하지만 속성명 사이에 대쉬(-)기호를 사용하면 빼기로 인식을 하기때문에 위 코드처럼 대쉬기호를 사용하지 않고 대쉬기호 다음 글자를 대문자로 작성해야 한다(CamelCase)