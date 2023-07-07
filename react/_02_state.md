# state
- 변동사항이 많은 데이터는 변수가 아니라 state를 만들어 저장하자
- state는 변동사항이 생기면 해당 state를 사용하는 html을 자동으로 재랜더링 해준다
```js
import { useState } from 'react'

function App() {
  let [title, setTitle] = useState("그냥 제목")
  ...
}
```
- 위 코드에서 title은 state이름, setTitle은 state변경함수의 이름이다
- state명은 useState에 넣어둔 값이 지정된다
- state변경함수의 경우 지정해주지 않아도 지장이 없다
- state는 중괄호를 사용해 호출할 수 있다
```js
import { useState } from 'react'

function App() {
  let [title, setTitle] = useState("그냥 제목")
  return (
    <div className="App">
      <div>{ title }</div>
    </div>
  );
}
```
> state의 값이 여러개일 경우
- 대괄호로 값을 감싸고 인덱싱을 통해 해당 값을 불러온다
```js
import { useState } from 'react'

function App() {
  let [title, setTitle] = useState(["첫 번째", "두 번째", "세 번째"])
  return (
    <div className="App">
      <div>{ title[0] }</div>
      <div>{ title[1] }</div>
      <div>{ title[2] }</div>
    </div>
  );
}
```
## state 수정
- 이벤트가 발생 해 state값이 변경되는 경우
### 이벤트
- 일반적인 자바스크립트는 이벤트가 아래와 같지만
```js
<div onclick="실행할 js 코드"></div>
```
- react의 경우 아래와 같이 실행 된다
```js
<div onClick={실행할 함수}></div>
```
- 이때, 실행되는 코드가 아닌 함수를 중괄호 안에 넣어야 한다
- 따로 만들어 둔 함수명을 불러와도 되지만, 중괄호 안에 함수를 직접 만들어도 가능하다
```js
<div onClick={function () {실행할 코드}}></div>
<div onClick={() => {실행할 코드}}></div>
```
### state 수정
- useState에는 state값 수정에 해당하는 함수가 포함되어 있다
```js
import {useState} from 'react';

function App() {
  let [title, setTitle] = {'제목'}
}
```
- 위 코드의 경우 setTitle이 state값 수정 함수명이 된다
```js
import {useState} from 'react';

function App() {
  let [title, setTitle] = {'제목'}
  return (
    <div className="App">
      <span onClick={() => {setTitle('바뀐 제목')}}>이벤트 실행</span>
    </div>
  );
}
```
- 위 코드 기준 span태그를 클릭하면 setTitle함수가 실행되 title의 값이 setTitle('바뀐 제목')의 괄호 안의 값인 '바뀐 제목'으로 바뀌게 된다
- 이때, 수치를 수정하기 위해 등호를 사용한다면 오류가 나기 때문에 등호는 사용하면 안된다
- 즉, state의 값이 변경 함수 괄호 안의 값으로 완전히 대체된다
```js
import {useState} from 'react';

function App() {
  let [title, setTitle] = {1}
  return (
    <div className="App">

      // 오류 발생
      <span onClick={() => {setTitle(title=title+1)}}>이벤트 실행</span>

      // 등호 사용 없이 식만 사용
      <span onClick={() => {setTitle(title+1)}}>이벤트 실행</span>

    </div>
  );
}
```
## array/object state 수정
- array/object state의 값을 변경하려면 추가적인 과정이 필요하다
```js
import {useState} from 'react';

function App() {
  let [title, setTitle] = useState(['첫 번째', '두 번째', '세 번째'])
  return (
    <div className="App">
      <div>{title[0]}</div>

      <button onClick={() => {
        let copy = [...title]
        copy[0] = '제목 수정'
        setTitle(copy)
      }}></button>

      <div>{title[1]}</div>
      <div>{title[2]}</div>
    </div>
  )
}
```
- 기존 state 복사 -> 값 수정 -> state수정
> 원리
- state변경 함수의 경우 두 값을 비교한 후, 값이 다를 경우 변경한다
- 변수에 값을 지정할 때
  - 값이 ram에 저장된 후
  - 변수명에 해당 값의 주소값이 지정된다
- copy를 하지않고 값을 변경할 경우
  - ram에 저장된 값이 변경된다
  - 하지만, 주소값은 변경되지 않는다
- state변경 함수 실행 시 값을 비교하는게 아니라, 주소값을 비교한다
  - copy를 하지 않을 경우 주소값 자체가 변경되는게 아니기 때문에, 값이 변경되지 않는다
- 따라서, copy를 해 주소값을 새롭게 지정을 해줘야 state변경 함수가 정상적으로 실행된다
```js
let copy = [...title]
```
- 위 코드에서 ... 은 괄호를 제거한다는 의미
- ...을 통해 괄호가 사라졌으므로 array를 유지하기 위해 대괄호를 씌우는 것
- 만약, 데이터가 object라면 대괄호 대신 중괄호를 사용한다
- 이를, deep copy 혹은 shallow copy 라고 한다