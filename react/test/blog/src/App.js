/* eslint-disable */ 
// import logo from './logo.svg';
import { useState } from 'react';
import './App.css';

function App() {
  
  let post = '응애'
  let [title, setTitle] = useState(['첫 번째', '두 번째', '세 번째'])
  let [like, setLike] = useState(0)

  return (
    <div className="App">
      <div className="nav-tab">
        <h3>{post}</h3>
      </div>
      <div className="list">
        <h4>{title[0]} <span onClick={() => {setLike(like+1)}}>👍</span> {like} </h4>
        <p>7월 7일 작성</p>
      </div>
      <div className="list">
        <h4>{title[1]}</h4>
        <button onClick={() => {
          let copy = [...title]
          copy[1] = "제목 수정"
          setTitle(copy)
        }}>제목 변경</button>
        <p>7월 7일 작성</p>
      </div>
      <div className="list">
        <h4>{title[2]}</h4>
        <p>7월 7일 작성</p>
      </div>
    </div>
  );
}

export default App;
