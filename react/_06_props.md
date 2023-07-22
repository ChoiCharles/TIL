# props
- 부모 컴포넌트의 state값을 자식 컴포넌트에서 사용하고 싶을 때 사용
- 부모에서 자식으로 props라는 이름(일반적으로 사용되는)의 객체로 보내줄 수 있다
```js
// App.js
import {useState} from 'react';

function App() {
  let [title, setTitle] = useState([1, 2, 3])

  return (
    <div>
      <Modal></Modal>
    </div>
  )
}

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
- 위와 같은 형태의 코드에서 부모인 App컴포넌트의 title을 자식인 Modal컴포넌트에서 사용하고 싶을 때 사용한다
## 사용법
```js
// App.js
import {useState} from 'react';

function App() {
  let [title, setTitle] = useState([1, 2, 3])

  return (
    <div>
      <Modal title={title}></Modal>
    </div>
  )
}

function Modal(props){
  return (
    <div className="modal">
      <h4>{props.title}</h4>
      <p>날짜</p>
      <p>상세내용</p>
    </div>
  )
}
```
- App에서 Modal컴포넌트를 불러올 때 사용한 \<Modal\>에 속성값을 추가함으로서 Modal에 state를 보내줄 수 있다
- 위 코드 기준 title={title} 에서 우측항인 중괄호로 감싸진 title의 경우 자식컴포넌트로 보낼 state 값이다
- 좌측항인 title은 props로 전송될 객체의 이름이 된다
  - 만약 \<Modal a={title}\>\</Modal\>의 형태로 작성한다면, 자식 컴포넌트에선 props.a 로 state값을 불러온다
- 자식 컴포넌트인 Modal의 속성값인 props는 자신이 원하는 이름으로 지을 수 있으며, 일반적으로 props라 짓는다
- props는 객체형태로 전달되며, 여러 state를 전송 할 수 있다
```js
// App.js
import {useState} from 'react';

function App() {
  let [title, setTitle] = useState([1, 2, 3])
  const content = "으악"

  return (
    <div>
      <Modal title={title} content={content} color="red"></Modal>
    </div>
  )
}

function Modal(props){
  return (
    <div className="modal" style={{background:props.color}}>
      <h4>{props.title}</h4>
      <p>날짜</p>
      <p>{props.content}</p>
    </div>
  )
}
```
- 위 코드와 같이 부모 컴포넌트에서 자식 컴포넌트에 여러 속성을 입력하면 그 값이 props에 객체 형태로 저장되 전송된다
- 위 코드의 content처럼 단순 변수부터, color와 같이 별도의 정의 없이 단순하게 지정해도 전송이 된다
- 또한, 함수 역시 전송이 되며 이를 이용해서 부모 컴포넌트의 state값을 수정할 수 있다
```js
import {useState} from 'react';

function App(){
  let [title, setTitle] = useState([1, 2, 3])
  let [content, setContent] = useState(["첫 번째", "두 번째", "세 번째"])
  let [modal, setModal] = useState(false)
  let [modaltitle, setModaltitle] = useState(0)

  return (
    <div className="App">
      {
        title.map(function(contenttitle, i){
          return (
            <div className="content" key={i}>
              <h3 onClick={()=>{setModal(!modal), setModaltitle(i)}}>{contenttitle}</h3>
              <div>{content[i]}</div>
            </div>
          )
        }
        )
      }
      {
        modal == true ? <Modal title={title} modaltitle={modaltitle} setTitle={setTitle}/> : null
      }
    </div>
  )
}

function Modal(props){
  return (
    <div>
      <div>{props.title[props.modaltitle]}</div>
      <button onClick={()=>{
        let copy=[...props.title]
        copy[props.modaltitle]="수정함"
        props.setTitle(copy)
        }}>제목 수정</button>
    </div>
  )
}
```
- 위 코드는 글 제목을 클릭하면 해당 글 제목에 해당하는 모달창이 노출된다
  - 모달창이 노출된 상태에서 제목 수정에 해당하는 버튼을 클릭하면 props로 전송된 함수가 실행되 부모컴포넌트의 state값이 변경된다
- 다만, state가 자식 컴포넌트에서만 유효하다면 굳이 부모 컴포넌트에 작성할 필요가 없지만,
자식에서 부모로는 보낼 수 없기 때문에 state의 위치를 잘 선정해야 한다