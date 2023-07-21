# map 함수
- map 함수를 컴포넌트를 사용하지 않고 반복해서 등장하는 html 태그들을 축약할 수 있다
```js
const array = [1, 2, 3]
array.map(function(){
  console.log(1)
})
```
- 위 코드를 실행 시 1 이 array의 길이인 3만큼 3번 출력된다
- array의 값을 출력하고 싶다면 아래와 같다
```js
const array = [1, 2, 3]
array.map(function(a){
  console.log(a)
})
```
- map 함수를 사용할 때 괄호안의 function은 콜백함수라 한다

## jsx에 적용
- jsx는 if문과 같이 for문을 사용할 수 없다(map함수를 사용하는 이유)
- if문과 같이 중괄호 안에서 map함수를 사용하면 된다
```js
function App(){
  const array = [1, 2, 3]
  return(
    ...
    {
      array.map(function(){
        return(<div>hi</div>)
      })
    }
    ...
  )
}
```
- 위 코드가 실행되면 hi 가 3번 출력 된다
- array의 값이 3개로 총 3번 반복되는 것
- 아래와 같이 사용한다면 'hi' 대신 array의 값을 출력할 수 있다
```js
function App(){
  const array = [1, 2, 3]
  return (
    ...
    {
      array.map(function(a){
        return (<div>a</div>)
      })
    }
    ...
  )
}
```
- map함수에 따라 a 에 1, 2, 3 이 순서대로 입력되기에 그런것
- array값이 아닌 인덱스 로도 접근하고 싶다면 아래와 같다
```js
function App(){
  const array = [1, 2, 3]
  return (
    ...
    {
      array.map(function(a, i){
        return (
          <div>i</div>
        )
      })
    }
    ...
  )
}
```
- map의 콜백함수에 속성값을 하나 더 부여하면 array의 인덱스 번호로 할당된다