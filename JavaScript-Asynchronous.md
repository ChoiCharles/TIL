# 동기와 비동기
## 동기 (Synchronous)
- 모든 일을 순서대로 하나씩 처리하는 것
- 이전 작업이 끝나야 다음 작업을 시작한다
### 웹에서의 동기
```javascript
<body>
  <button>버튼</button>
  <script>
    const btn = document.querySelector('button')
    btn.addEventListener('click', () => {
      alert('you clicked me!')
      const pElem = document.createElement('p')
      pElem.innerText = 'p Element'
      document.body.appendChild(pElem)
    })
  </script>
</body>
```
- 버튼을 누르기 전까지 p태그는 보이지 않음
## 비동기 (Asynchronous)
- 작업을 시작한 후 결과를 기다리지 않고 다음 작업을 처리하는 것 (병렬적 수행)
- 시간이 필요한 작업들은 요청을 보낸 뒤 응답이 빨리 오는 작업부터 처리
- 예시
    - Gmail에서 메일 전송을 누르면 목록 화면으로 전환되지만 실제로 메일을 보내는 작업은 병렬적으로 뒤에서 처리됨
```javascript
function slowRequest(callBack) {
    console.log('1. 오래걸리는거')
    setTimeout(function () {
    callBack()
    }, 3000)
}

function myCallBack() {
    console.log('2. 콜백함수실행')
}

slowRequest(myCallBack)
console.log('3. 다른작업실행')
// 결과
// 1. 오래걸리는거
// 3. 다른작업실행
// 2. 콜백함수실행
```
### 비동기를 사용하는 이유
- 사용자 경험
    - 아주 큰 데이터를 불러온 뒤 실행되는 앱의 경우 동기로 처리한다면 데이터를 모두 불러온 뒤에야 앱의 실행 로직이 수행되므로 사용자는 마치 앱이 멈춘것과 같은 경험을 겪게 됨
    - 동기식 처리는 트정 로직이 실행되는 동안 다른 로직 실행을 차단하기 때문에 마치 프로그램이 응답하지 않는 듯한 사용자 경험을 만들게 됨
    - 비동기로 처리한다면 먼저 처리되는 부분부터 보여줄 수 있으므로 사용자 경험에 긍정적인 효과를 볼 수 있음 <br>이와 같은 이유로 많은 웹 기능은 비동기 로직을 사용해서 구현되어 있음
# JavaScript의 비동기 처리
- JavaScript는 한 번에 하나의 일만 수행할 수 있는 Single Thread 언어로 동시에 여러 작업을 처리할 수 없음
- 즉, JavaScript는 하나의 작업을 요청한 순서대로 처리할 수 밖에 없다
> Thread <br>작업을 처리할 때 실제로 작업을 수행하는 주체로, multi-thread라면 업무를 수행할 수 있는 주체가 여러 개라는 의미
## JavaScript Runtime
- JavaScript 자체는 Single Thread이므로 비동기 처리를 할 수 있도록 도와주는 환경이 필요함
- 특정 언어가 동작할 수 있는 환경을 "런타임(Runtime)"이라 함
- JavaScript에서 비동기와 관련한 작업은 브라우저 또는 Node 환경에서 처리
- 이중 브라우저 환경에서의 비동기 동작은 크게 아래의 요소들로 구성됨
    1. JavaScript Engine의 Call Stack
    2. Web API
    3. Task Queue
    4. Event Loop
## 비동기 처리 동작 방식
- 브라우저 환경에서의 JavaScript의 비동기는 아래와 같이 처리된다
1. 모든 작업은 Call Stack(LIFO)으로 들어간 후 처리된다
2. 오래 걸리는 작업이 Call Stack으로 들어오면 Web API로 보내 별도로 처리하도록 한다
3. Web API에서 처리가 끝난 작업들은 곧바로 Call Stack으로 들어가지 못하고 Task Queue(FIFO)에 순서대로 들어간다
4. Event Loop가 Call Stack이 비어 있는 것을 계속 체크하고 Call Stack이 빈다면 Task Queue에서 가장 오래된 (가장 앞에 있는) 작업을 Call Stack으로 보낸다
## 비동기 처리 동작 요소
1. Call Stack
    - 요청이 들어올 때 마다 순차적으로 처리하는 Stack(LIFO)
    - 기본적인 JavaScript의 Single Thread 작업 처리
2. Web API
    - JavaScript 엔진이 아닌 브라우저에서 제공하는 runtime 환경
    - 시간이 소요되는 작업을 처리 (setTimeout, DOM Event, AJAX 요청 등)
3. Task Queue
    - 비동기 처리된 Callback 함수가 대기하는 Queue(FIFO)
4. Event Loop
    - Call Stack과 Task Queue를 지속적으로 모니터링
    - Call Stack이 비어 있는지 확인 후 비어 있다면 Task Queue에서 대기 중인 오래된 작업을 Call Stack으로 Push
## 정리
- JavaScript는 한 번에 작업을 수행하는 Single Thread 언어로 동기적 처리를 하지만, 브라우저 환경에서는 Web API에서 처리된 작업이 지속적으로 Task Queue를 거쳐 Event Loop에 의해 Call Stack에 들어와 순차적으로 실행됨으로써 비동기 작업이 가능한 환경이 됨
# Axios
- JavaScript의 HTTP 웹 통신을 위한 라이브러리
- 확장 가능한 인터페이스와 쉽게 사용할 수 있는 비동기 통신 기능을 제공
- node 환경은 npm을 이용해서 설치 후 사용할 수 있고, brower 환경은 CDN을 이용해서 사용할 수 있음
## 기본구조
```javascript
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
  axios.get('요청할 URL')
    .then(성공하면 수행할 콜백함수)
    .catch(실해하면 수행할 콜백함수)
</script>
```
- get, post 등 여러 method 사용가능
- then을 이용해서 성공하면 수행할 로직을 작성
- catch를 이용해서 실패하면 수행할 로직을 작성
## 고양이 사진 가져오기
- The Cat API (https://api.thecatapi.com/v1/images/search)
    - 이미지를 요청해서 가져오는 작업을 비동기로 처리

### Python으로 요청 (동기)
```python
import requests 

print('고양이는 야옹')

cat_image_search_url = 'https://api.thecatapi.com/v1/images/search'
response = requests.get(cat_image_search_url)

if response.status_code == 200:
    print(response.json())
else: 
    print('실패했다옹')
    
print('야옹야옹')

# 출력결과
# 고양이는 야옹
# [...]
# 야옹야옹
```
### Axios로 요청 (비동기)
```javascript
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
  console.log('고양이는 야옹')
  const catImageSearchURL = 'https://api.thecatapi.com/v1/images/search'
  const btn = document.querySelector('button')

  btn.addEventListener('click', function () {
    axios.get(catImageSearchURL)
      .then((response) => {
        const imgElem = document.createElement('img')
        imgElem.setAttribute('src', response.data[0].url)
        document.body.appendChild(imgElem)
      })
      .catch((error) => {
        console.log('실패했다옹')
      })

    console.log('야옹야옹')
  })
</script>
// 출력결과
// 고양이는 야옹
// 야옹야옹
// [...]
```
### 결과 비교
- 동기식 코드(python)는 위에서부터 순서대로 처리가 되기 때문에 첫번째 print가 출력되고 이미지를 가져오는 처리를 기다렸다가 다음 print가 출력된다
- 비동기식 코드(JavaScript)는 바로 처리가 가능한 작업(console.log)은 바로 처리하고, 오래 걸리는 작업인 이미지를 요청하고 가져오는 일은 요청을 보내 놓고 기다리지 않고 다음 코드로 진행 후 완료가 된 시점에 결과 출력이 진행됨
### 정리
- axios는 비동기로 데이터 통신을 가능하게 하는 라이브러리
- 같은 방식으로 Django REST API로 요청을 보내서 데이터를 받아온 후 처리할 수 있음
# Callback과 Promise
### 비동기 처리의 단점
- 비동기 처리의 핵심은 Web API로 들어오는 순서가 아니라 작업이 완료되는 순서에 따라 처리한다는 것
- 그런데 이는 개발자 입장에서 코드의 실행 순서가 불명확하다는 단점이 있음
- 이런 단점으로 인해 실행결과를 예상하면서 코드를 작성할 수 없게 함
- 이 부분은 콜백 함수를 사용해 해소할 수 있다
## 콜백 함수 (Callback Function)
### 콜백 지옥 (Callback Hell)
- 콜백 함수는 연쇄적으로 발생하는 비동기 작업을 순차적으로 동작할 수 있게 함
- 보통 어떤 기능의 실행 결과를 받아서 다른 기능을 수행하기 위해 많이 사용하는데, 이 과정을 작성하다 보면 비슷한 패턴이 계속 발생하게 됨
- 비동기 처리를 위한 콜백을 작성할 때 마주하는 문제를 Callback Hell(콜백 지옥)이라 하며, 그때의 코드 작성 형태가 마치 피라미드와 같다고 해서 파멸의 피라미드 라고도 부름
### 정리
- 콜백 함수는 비동기 작업을 순차적으로 실행할 수 있게 하는 반드시 필요한 로직
- 비동기 코드를 작성하나 보면 콜백 함수로 인한 콜백 지옥은 반드시 나타나는 문제
    - 코드의 가독성이 떨어짐
    - 유지보수가 어려워짐
## 프로미스 (Promise)
- Callback Hell 문제를 해결하기 위해 등장한 비동기 처리를 위한 객체
- '작업이 끝나면 실행 시켜줄게' 라는 약속
- 비동기 작업의 완료 또는 실패를 나타내는 객체
- Promise 기반의 클라이언트가 바로 Axios 라이브러리
    - 성공에 대한 약속 then()
    - 실패에 대한 약속 catch()
### then & catch
- then(callback)
    - 요청한 작업이 성공하면 callback 실행
    - callback은 이전 작업의 성공 결과를 인자로 전달 받음
- catch(callback)
    - then()이 하나라도 실패하면 callback 실행
    - callback은 이전 작업의 실패 객체를 인자로 전달 받음
- then과 catch 모두 항상 promise 객체를 반환 하므로 chaining을 할 수 있음
- axios로 처리한 비동기 로직이 항상 promise 객체를 반환 하므로 then을 계속 이어나가면서 작성할 수 있다
```javascript
axios.get('url')
    .then(callback1)
    .then(callback2)
    .then(callback3)
    ...
    .catch(callback)
```
### 비동기 콜백과 Promise
- 기존 콜백 함수 방식
```javascript
work(function(){
    work2(result1, function(result2){
        work3(result2, function(result3){
            console.log(result3)
        })
    })
})
```
- promise 방식
```javascript
work1()
  .then((result1) => {
    return result2
  })
  .then((result2) => {
    return result3
  })
  .catch((error) => {

  })
```
- promise방식은 비동기 처리를 위에서 아래로 적는 일반적인 코드 작성처럼 할 수 있음
### Promise가 보장하는 것
1. callback 함수는 JavaScript의 Event Loop가 현재 실행 중인 Call Stack을 완료하기 이전에는 절대 호출되지 않음
    - Promise callback 함수는 Event Queue에 배치되는 엄격한 순서로 호출됨
2. 비동기 작업이 성공하거나 실패한 뒤에 .then() 메서드를 이용하여 추가한 경우에도 1번과 똑같이 동작
3. .then()을 여러번 사용하여 여러 개의 callback 함수를 추가할 수 있음(Chaining)
    - 각각의 callback은 주어진 순서대로 하나하나 실행하게 됨
    - Chaining은 Promise의 가장 뛰어난 장점