/* eslint-disable */ 
// import logo from './logo.svg';
import { useState } from 'react';
import './App.css';

function App() {
  
  let post = '응애'
  let [title, setTitle] = useState(['첫 번째', '두 번째', '세 번째'])
  let [like, setLike] = useState([0, 0, 0])

  let [modal, setModal] = useState(false)

  let [modaltitle, setModaltitle] = useState(0)

  return (
    <div className="App">

      <div className="nav-tab">
        <h3>{post}</h3>
      </div>

      {
        title.map(function(a, i) {
          return (
            <div className="list" key={i}>
              
              <h4 onClick={()=>{setModal(!modal), setModaltitle(i)}} style={{display:'inline'}}>{a}</h4>
              <span onClick={() => {
                let copy = [...like]
                copy[i]++
                setLike(copy)}}>👍</span> {like[i]}
              <h1></h1>
              <div>
              <button onClick={() => {
                let copy = [...title]
                copy[i] = "바꿔"
                setTitle(copy)
              }}>제목변경</button>
              </div>
              <p>7월 7일 작성</p>
            </div>
          )
        })
      }

      {
        modal == true ? <Modal title={title} like={like} setTitle={setTitle} modaltitle={modaltitle}></Modal> : null
      }
      
    </div>
  );
}

function Modal(props){
  return (
    <div className="modal">
      <h4>{props.title[props.modaltitle]}</h4>
      <p>{props.like}</p>
      <p>날짜</p>
      <button onClick={()=>{
        let copy = [...props.title]
        copy[0] = "수정함"
        props.setTitle(copy)
      }}>글수정</button>
    </div>
  )
}

export default App;