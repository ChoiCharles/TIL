# JavaScript 시작하기
## JavaScript란?
- JavaScript는 클라이언트 측 웹(브라우저)에서 실행
- JavaScript는 쉡게 배울 수 있고 강력한 스크립트 언어
- 웹페이지가 이벤트 발생 시 어떻게 작동하는지 디자인/프로그래밍 웹페이지 동작을 제어하는 데 널리 사용
> Web 기술의 기반이 되는 언어
- HTML 문서의 콘텐츠를 동적으로 변경할 수 있는 언어
- Web이라는 공간에서 채팅, 게임 등 다양한 동작을 할 수 있게 된 기반
- 실제 JavaScript는 언어의 확장성 만큼 큰 인기를 얻고 있는 언어
## JavaScript Engine
- JavaScript Engine은 자바스크립트 코드를 실행하는 프로그램 또는 인터프리터로 여러 목적으로 자바스크립트 엔진을 사용하지만, 대체적으로 웹 브라우저에서 사용
### 웹 브라우저의 역할
- URL을 통해 Web을 탐색함
- HTML/CSS/JavaScript를 이해한 뒤 해석해서 사용자에게 하나의 화면으로 보여줌
- 웹 서비스 이용 시 클라이언트의 역할을 함
- 즉, 웹 페이지 코드를 이해하고 보여주는 역할을 하는 것이 바로 웹 브라우저
### JavaScript Engine
- HTML/CSS/JavaScript를 이해한뒤 해석
    - JavaScript를 해석하는 것이 JavaScript Engine의 역할
- 각 브라우저마다 자체 JavaScript Engine을 개발, 사용하고 있음
    - V8 - Chrome
    - Chakra - Microsoft Edge
    - JSC(JavaScript Core)
    - SpiderMonkey - FireFox
- 대체적으로 웹 브라우저에서 사용
- 웹 브라우저 외에는 어떻게 할용할까
    - Node.js
        - Node.js는 V8 엔진을 사용하여 서버 측에서 자바스크립트 코드를 실행 가능하며 브라우저 조작 이외의 역할도 수행
## JavaScript 실행 환경 구성
### Web Browser로 실행
- HTML 파일에 직접 JavaScript 코드를 작성 후 웹 브라우저로 파일 열기
```html
<!DOCTYPE html>
<html lang="en">
<head>
  ...
</head>
<body>

  <script>
    console.log('hello')
  </script>
</body>
</html>
```
- Chrome의 개발자 도구- Console 탭에서 결과 확인 가능
- .js 확장자를 가진 파일에 JavaScript를 작성하고, 해당 파일을 HTML에 포함 가능
```
// hello.js

console.log('hello')
```
```html
<!-- hello.html -->

<!DOCTYPE html>
<html lang="en">
<head>
  ...
</head>
<body>
</body>
<script type="text/javescript" src="hello.js"></script>
</html>
```
- 웹 브라우저의 console에서 바로 JavaScript를 입력해도 된다
- 특별하게 웹 브라우저에서 바로 실행할 수 있는 JavaScript문법들을 Vanilla JavaScript라고 부름
### 정리
- 웹 브라우저는 JavaScript를 해석하는 엔진을 가지고 있음
- 특히, Chrome의 V8의 경우 JavaScript를 번역하는 속도가 매우 빠름
- 현재의 JavaScript는 시장에 자리잡은 언어이며, 개발에서 큰 축을 담당하는 언어
## JavaScript를 시작하기 전에
### EcmaScript
- EcmaScript란, Ecma International(전자 정보 통신 시스템 표준화 기구)이 ECMA-262 규격에 따라 정의하고 있는 표준화된 스크립트 프로그래밍 언어를 뜻함
- 즉, JavaScript를 표준화하기 위해 만들어짐
### EcmaScript가 정의하는 것
- JavaScript의 기본적인 문법, 데이터 타입, 객체 모델, 함수, 연산자 등을 저의
### 주석
- 한 줄 주석(//)과 여러줄(/**/)주석
### 들여쓰기와 코드 블럭
- python은 4칸 들여쓰기를 사용하지만, JavaScript는 2칸 들여쓰기를 사용
- 블럭(block)은 if, for, 함수에서 중괄호{}내부를 말함
    - python은 들여쓰기를 이용해서 코드 블럭을 구분
    - JavaScript는 중괄호 {} 를 사용해 코드 블럭을 구분
```js
if (isClean) {
    console.log('clean!')
}
```
### 세미콜론
- JavaScript는 세미콜론을 선택적으로 사용 가능
- 세미콜론이 없으면 ASI에 의해 자동으로 세미콜론이 삽입됨
    - ASI (Automatic Semicolon Insertion, 자동 세미콜론 삽입 규칙)
- 세미콜론 사용 여부에 대해서는 연전히 논란이 많은 주제
# JavaScript 기본문법
## 변수와 식별자
### 식별자 정의와 특징
- 식별자(identifier)는 변수를 구분할 수 있는 변수명을 말함
- 식별자는 반드시 문자, 달러($) 또는 밑줄(_)로 시작
- 대소문자를 구분하며, 클래스명 외에는 모두 소문자로 시작
- 예약어 사용 불가능
    - for, if function 등
- 카멜 케이스 (camelCase)
    - 변수, 객체, 함수에 사용
```js
// 변수
let dog
let variableName

// 객체
const userInfo = {name:'Tom', age:20}

// 함수
function add() {}
function getName() {}
```
- 파스칼 케이스(PascalCase)
    - 클래스, 생성자에 사용
```js
// 클래스
class User {
    constructor(options) {
        this.name = options.name
    }
}

// 생성자 함수
function User(options) {
    this.name = options.name
}
```
- 대문자 스네이크 케이스(SNAKE_CASE)
    - 상수(constants)에 사용
    - 상수 : 개발자의 의도와 상관없이 변경될 가능성이 없는 값을 의미
```js
// 값이 바뀌지 않을 상수
const API_KEY = 'my-key'
const PI = Math.PI

// 재할당이 일어나지 않는 변수
const NUMBERS = [1, 2, 3]
```
### 변수 선언 키워드
- python과 다르게 JavaScript는 변수를 선언하는 키워드가 정해져 있음
    1. let
        - 블록 스코프 지역 변수를 선언 (추가로 동시에 값을 초기화)
        - 재할당 가능 & 재선언 불가능
    ```js
    let number = 10 // 선언 및 초기값 할당
    number = 20     // 재할당
    let number = 10 // 재선언 불가능
    ```
    2. const
        - 블록 스코프 읽기 전용 상수를 선언 (추가로 동시에 값을 초기화)
        - 재할당 불가능 & 재선언 불가능
        - 선언 시 반드시 초기값을 설정 해야 하며, 이후 값 변경이 불가능
    ```js
    const number = 10   // 선언 및 초기값 할당
    number = 20         // 재할당 불가능
    const number = 10   // 재선언 불가능
    ```
    3. var
        - 변수를 선언 (추가로 동시에 값을 초기화)
        - 재할당 & 재선언 가능
        - ES6 이전에 변수를 선언할 때 사용되던 키워드
        - "호이스팅"되는 특성으로 인해 예기치 못한 문제 발생 가능
            - 따라서 ES6 이후부터는 var 대신 const와 let을 사용하는 것을 권장
        - 함수 스코프(function scope)를 가짐
> [참고] 선언, 할당, 초기화
- 선언 (Declaration)
    - 변수를 생성하는 행위 또는 시점
- 할당 (Assignment)
    - 선언된 변수에 값을 저장하는 행위 또는 시점
- 초기화 (Initialization)
    - 선언된 변수에 처음으로 값을 저장하는 행위 또는 시점
```js
let x = 1
if (x === 1) {
    let x = 2
    console.log(x)  //2
}

console.log(x)      //1
```
```js
let foo             // 선언
console.log(foo)    // undefined

foo = ll            // 할당
console.log(foo)    // ll

let bar = 0         // 선언 + 할당
console.log(bar)    // 0
```
### 블록 스코프 (block scope)
- if, for, 함수 등의 중괄호 {} 내부를 가리킴
- 블록 스코프를 가지는 변수는 블록 바깥에서 접근 불가능
### 함수 스코프 (function scope)
- 함수의 중괄호 내부를 가리킴
- 함수 스코프를 가지는 변수는 함수 바깥에서 접근 불가능
```js
function foo() {
    var x = 5
    console.log(x)  // 5
}

// ReferenceError: x is not defined
console.log(x)
```
### 호이스팅 (hoisting)
- 변수를 선언 이전에 참조할 수 있는 현상
- var로 선언된 변수는 선언 이전에 참조할 수 있으며, 이러한 현상을 호이스팅이라 함
- 변수 선언 이전의 위치에서 접근 시 undefined를 반환
```js
console.log(name)   // undefined => 선언 이전에 참조

var name = '홍길동' // 선언

// 위 코드를 암묵적으로 아래와 같이 이해함
var name // undefined로 초기화
console.log(name)

var name = '홍길동'
```
- 즉, JavaScript에서 변수들은 실제 실행시에 코드의 최상단으로 끌어 올려지게 되며(hoisted) 이러한 이유 때문에 var로 선언된 변수는 선언 시에 undefined로 값이 초기화되는 과정이 동시에 일어남
- 반면 let, const는 호이스팅이 일어나면 에러를 발생시킴
- 변수를 선언하기 전에 접근이 가능한 것은 코드의 논리적인 흐름을 깨뜨리는 행위이며 이러한 것을 방지하기 위해 let, const가 추가되었음
    - 즉, var는 사용하지 않아야 하는 키워드
- 다만, 아직까지도 많은 기존의 JavaScript 코드는 ES6 이전의 문법으로 작성되어 있으므로 호이스팅에 대한 이해가 필요
### 변수 선언 키워드 정리
|키워드|재선언|재할당|스코프|비고|
|-----|-----|-----|-----|-----|
|let|X|O|블록 스코프|ES6부터 도입|
|const|X|X|블록 스코프|ES6부터 도입|
|var|O|O|함수 스코프|사용 X|
- 어디에 변수를 쓰고 상수를 쓸지 결정하는 것은 프로그래머의 몫
- Airbnb 스타일 가이드에서는 기본적으로 const 사용을 권장
    - 재할당해야 하는 경우만 let
## 데이터 타입
- JavaScript의 모든 값은 특정한 데이터 타입을 가짐
- 크게 원시 타입(Primitive type)과 참조 타입(Reference type)으로 분류됨
### 원시 타입(Primitive type)
1. Number - 정수 또는 실수형 숫자를 표현하는 자료형
    ```js
    const a=13
    const b=-5
    const c=3.14
    const d=2.998e8
    const e=Infinity
    const f=-Infinity
    const g=NaN
    ```
    - NaN을 반환하는 경우
        1. 숫자로서 읽을 수 없음 (parselnt("어쩌구"), Number(undefined))
        2. 결과가 허수인 수학 계산식 (Math.sqrt(-1))
        3. 피연산자가 NaN (7 ** NaN)
        4. 정의할 수 없는 계산식 (0 * Infinity)
        5. 문자열을 포함하면서 덧셈이 아닌 계산식 ("가" / 3)
2. String - 문자열을 표현하는 자료형
    - 작은 따옴표 또는 큰 따옴표 모두 가능
    ```js
    const sentence1 = 'hello'
    const sentence2 = "hello"

    console.log(sentence1)
    console.log(sentence2)
    ```
    - 곱셈, 나눗셈, 뺄셈은 안되지만 덧셈을 통해 문자열끼리 붙일 수 있음
    ```js
    const firstName = 'Tony'
    const lastName = 'Stark'
    const fullName = firstName + lastName

    console.log(fullName)
    ```
    - 따옴표를 사용하면 선언시 줄 바꿈 불가능
    - 대신 escape sequence를 사용할 수 있기 때문에 \n를 사용
    ```js
    // Bad
    const word = 'hello
    world'//Uncaught SyntaxError: Invalid or unexpected token

    // Good
    const word1 = 'hello \nworld'
    console.log(word1)
    ```
    - Template Literal(1왼쪽의 점)을 사용하면 줄 바꿈이 가능, 문자열 사이에 변수도 삽입 가능
    ```js
    const word2=`hello
    world`
    console.log(word2)

    const age=0
    const message=`홍길동은 ${age}세입니다.`
    consolo.log(message)
    ```
3. null - 값이 없음을 나타냄
    ```js
    let lastName=Null
    console.log(lastName) //null
    ```
4. undefined - 값이 할당되지 않은 변수를 나타냄
    ```js
    let firstName
    console.log(firstName) // undefined
    ```
5. Boolean - 참과 거짓을 표현하는 자료형
    - 조건문 또는 반복문에서 유용하게 사용
        조건문 또는 반복문에서 boolean이 아닌 데이터 타입은 자동 형변환 규칙에 따라 true 또는 false로 변환됨
6. Symbol - 유일한 값을 표현하는 자료형, ES6에서 추가

### Template literals
- 내자왼 표현식을 허용하는 문자열 작성 방식
- ES6+ 부터 지원
- Backtick(``)을 이용하며, 여러 줄에 걸쳐 문자열을 정의할 수도 있고 JavaScript의 변수를 문자열 안에 바로 연결할 수 있는 이점이 생김
- 표현식을 넣을 수 있는데 이는 $와 중괄호(${expression})로 표기
### null과 undefined
- null과 undefined의 가장 대표적인 차이점은 typeof 연산자를 통해 타입을 확인 했을 때 나타남
```js
typeof null         // object
typeof undefined    // undefined
```
- null이 원시 타입임에도 불구하고 object로 출력되는 이유는 JavaScript 설계 당시의 버그를 지금까지 해결하지 못한 것
- 쉽게 해결 할 수 없는 이유는 이미 null 타입에 의존성을 띄고 있는 많은 프로그램들이 망가질 수 있기 때문 (하위 호환 유지)
### 참조 타입 (Reference type)
1. Object - 이름과 값을 가진 속성(property)들의 집합으로 이루어진 자료구조
2. Array - 여러 개의 값을 순서대로 저장하는 자료구조
3. function - function 키워드를 통해 생성하며, 호출 시 실행 될 코드를 정의
### 객체 (Object)
- 객체는 속성(property)의 집합이며, 중괄호 내부에 key와 value의 쌍으로 표현
- key
    - 문자열 타입만 가능
    - key 이름에 띄어쓰기 등의 구분자가 있으면 따옴표로 묶어서 표현
- value
    - 모든 타입(함수 포함) 가능
- 객체 요소 접근
    - 점(.) 또는 대괄호([])로 가능
    - key 이름에 띄어쓰기 같은 구분자가 있으면 대괄호 접근만 가능
```js
const me= {
    name:'jack',
    phoneNumver:'01012345678',
    'samsung products':{
        buds:'Galaxy Buds Pro',
        galaxy:'Galaxy S99',
    },
}

console.log(me.name)
console.log(me['name'])
console.log(me['samsung products'])
console.log(me.samsung products) // 불가능
console.log(me['samsung products'].buds)
```
### 배열 (Array)
- 키와 속성들을 담고 있는 참조 타입의 객체
- 순서를 보장하는 특징이 있음
- 주로 대괄호([])를 이용하여 생성하고, 0을 포함한 양의 정수 인덱스로 특정 값에 접근 가능
- 배열의 길이는 array.length형태로 접근 가능
```js
const numbers=[1, 2, 3, 4, 5]
console.log(numbers[0])     // 1
console.log(numbers[-1])    // undefined
console.log(numbers.length) // 5
console.log(numbers[numbers.length-1]) // 5
```
### 함수 (Function)
- 참조 타입 중 하나로써 function 타입에 속함
- JavaScript에서 함수를 정의하는 방법은 주로 2가지로 구분됨
    - 함수 선언식 (function declaration)
        - 일반적인 프로그래밍 언어의 함수 정의 방식
        ```js
        function 함수명(매개변수) {

        }

        function add(num1,num2) {
            return num1+num2
        }
        add(2,7)
        ```
    - 함수 표현식 (function expression)
        - 표현식 내에서 함수를 정의하는 방식
        - 함수 표현식은 함수의 이름을 생략한 익명 함수로 정의 가능
        ```js
        변수키워드 함수명 = function (매개변수) {

        }

        const sub = function (num1, num2) {
            return num1-num2
        }
        sub(7,2)
        ```
        - 표현식에서 함수 이름을 명시하는 것도 가능
        - 다만 이 경우 함수 이름은 호출에 사용되지 못하고 디버깅 용도로 사용됨
        ```js
        const mySub = function namedSub(num1, num2) {
            return num1-num2
        }
        mySub(1, 2) // -1
        namedSub(1m 2)  // ReferenceError: namedSub is not defined
        ```
### ToBoolean Conversions (자동 형변환)
|데이터 타입|False|True|
|----|----|----|
|undefined|항상 False|X|
|null|항상 False|X|
|Number|0, -0, NaN|나머지|
|String|빈 문자열|나머지|
|Object|X|항상 True|
## 연산자
### 할당 연산자
- 오른쪽에 있는 피연산자의 평가 결과를 왼쪽 피연산자에 할당하는 연산자
- 다양한 연산에 대한 단축 연산자 지원
- Increment 및 Decrement 연산자
    - Increment(++): 피연산자의 값을 1 증가시키는 연산자
    - Decrement(--): 피연산자의 값을 1 감소시키는 연산자
    - += 또는 -= 와 같이 더 분명한 표현으로 적을 것을 권장
```js
let c=0
c += 10
console.log(c) // c + 10
c -= 3
console.log(c) // c - 3
c *= 10
console.log(c) // c * 10
c ++
console.log(c) // c + 1
c --
console.log(c) // c - 1
```
### 비교연산자
- 피연산자들(숫자, 문자, Boolean 등)을 비교하고 결과값을 boolean으로 반환하는 연산자
- 문자열은 유니코드 값을 사용하며 표준 사전 순서를 기반으로 비교
    - 알파벳끼리 비교할 경우
        - 알파벳 순서상 후순위가 더 크다
        - 소문자가 대문자보다 더 크다
```js
3 > 2 // true
3 < 2 // false
'A'<'B' // true
'Z'<'a' // true
'가'<'나' // true
```
### 동등 연산자 (==)
- 두 연산자가 같은 값으로 평가되는지 비교 후 boolean값을 반환
- 비교할 때 암묵적 타입 변환을 통해 타입을 일치시킨 후 같은 값인지 비교
- 두 피연산자가 모두 객체일 경우 메모리의 같은 객체를 바라보는지 판별
- 예상치 못한 결과가 발생할 수 있으므로 특별한 경우를 제외하고 사용하지 않음
```js
const a=1
const b='1'
console.log(a==b)    // true
console.log(a==true) // true
```
### 일치 연산자(===)
- 두 피연산자의 값과 타입이 모두 같은 경우 true를 반환
- 같은 객체를 가리키거나, 같은 타입이면서 같은 값인지를 비교
- 엄격한 비교가 이뤄지며 암묵적 타입 변환이 발생하지 않음
    - 엄격한 비교- 두 비교 대상의 타입과 값 모두 같은지 비교하는 방식
```js
const a=1
const b='1'
console.log(a===b)    // false
console.log(a===Number(b)) // true
```
### 논리 연산자
- 세가지 논리 연산자로 구성
    - && : and
    - || : or
    - ! : not
- 단축 평가 지원
    - ex) false && true => false
    - ex) true || false => true

### 삼항 연산자 (Ternary Operator)
- 3개의 피연산자를 사용하여 조건에 따라 값을 반환하는 연산자
- 가장 앞의 조건식이 참이면 :(콜론) 앞의 값이 반환되며, 그 반대일 경우 : 뒤의 값이 반환된는 연산자
- 삼항 연산자의 결과 값이기 때문에 변수에 할당 가능
```js
true ? 1:2 // 1
false ? 1:2 // 2

const result=Math.PI>4 ? 'Yep':'Nope'
console.log(result) // Nope
```
### 스프레드 연산자 (Spread Operator)
- 배열이나 객체를 전개하여 각 요소를 개별적인 값으로 분리하는 연산자
- 주로 함수 호출 시 매개변수로 배열이나 객체를 전달할 때 사용
- 얕은 복사를 위해서도 활용 가능
```js
const numbers=[1,2,3]
const otherNumbers=[...numbers,4,5] // [1,2,3,4,5]
const copyNumbers=[...numbers] // [1,2,3]
const obj={a:1, b:2}
const otherObj={c:3, ...obj} // {a:1, b:2, c:3}
const copyObj={...obj} // {a:1, b:2}
```
## 조건문
- 조건 표현식의 결과값을 boolean 타입으로 변환 후 참/거짓을 판단
### if statement
- if, else if, else
    - 조건은 소괄호(condition) 안에 작성
    - 실행할 코드는 중괄호 {} 안에 작성
    - 블록 스코프 생성
```js
const name='manager'
if (name==='admin') {
    console.log('관리자님 환영합니다.')
}
else if (name==='manager') {
    console.log('매니저님 환영합니다.')
}
else {
    console.log(`${name}님 환영합니다.`)
}
```
## 반복문
### while
- 조건문이 참이기만 하면 문장을 계속해서 수행
```js
while (조건문) {

}

let i=0
while (i<6) {
    console.log(i)
    i+=1
}
```
### for
- 특정한 조건이 거짓으로 판별될 때까지 반복
```js
for([초기문];[조건문];[증감문]) {

}

for (let i=0;i<6;i++) {
    console.log(i)
}
```
- 동작 예시
    1. 반복문 진입 및 초기문(let i=0)을 통해 변수 선언
    2. 조건문 평가 후 코드 블럭 실행
    3. 코드 블럭 실행 이후 i값 증가
### for...in
- 객체(object)의 속성을 순회할 때 사용
- 배열도 순회 가능하지만 인덱스 순으로 순회한다는 보장이 없으므로 권장하지 않음
```js
for (variable in object) {
    statements
}

const fruits={a:'apple', b:'banana'}
for (const key in fruits) {
    console.log(key)    // a, b
    console.log(fruits[key])    // apple, banana
}
```
### for...of
- 반복 가능한 객체를 순회할 때 사용
- 반복 가능한(iterable) 객체의 종류: Array, Set, String 등
```js
for (variable of object) {
    statement
}

const numbers=[0, 1, 2, 3]
for (const number of numbers) {
    console.log(number) //0, 1, 2, 3
}
```

### for...in과 for...of 차이
- for...in은 "속성 이름"을 통해 반복, 객체 순회 적합
- for...of는 "속성 값"을 통해 반복, Iterable 순회 적합
```js
const arr=[3, 5, 7]

for (const i in arr) {
    console.log(i) // 0 1 2
}

for (const i of arr) {
    console.log(i) // 3 5 7
}
```
|키워드|종류|연관 키워드|스코프
|----|----|----|----|
|if|조건문|-|블록스코프|
|while|반복문|break, continue|블록스코프|
|for|반복문|break, continue|블록스코프|
|for...in|반복문|객체 순회|블록스코프|
|for...of|반복문|Iterable 순회|블록스코프|

# 함수
- 참조 타입 중 하나로써 function 타입에 속함
- JavaScript에서 함수를 정의하는 방법은 주로 2가지로 구분됨
    - 함수 선언식 (function declaration)
    - 함수 표현식 (function expression)
## 함수의 정의
### 함수 선언식 (Function declaration)
- 일반적인 프로그래밍 언어의 함수 정의 방식
```js
function 함수명() {

}

function add(num1, num2) {
    return num1+num2
}
add(2, 7)
```
### 함수 표현식 (Function expresstion)
- 표현식 내에서 함수를 정의하는 방식
- 함수 표현식은 함수의 이름을 생략한 익명 함수로 정의 가능
```js
변수키워드 함수명=function() {

}

const sub=function(num1, num2) {
    return num-num2
}
sum(7, 2)
```
- 표현식에서 함수 이름을 명시하는 것도 가능
- 다만 이 경우 함수 이름은 호출에 사용되지 못하고 디버깅 용도로 사용됨
```js
const mySub = function namedSub(num1, num2) {
    return num1-num2
}
mySub(1, 2) // -1
namedSub(1m 2)  // ReferenceError: namedSub is not defined
```
### 기본 인자 (Default arguments)
- 인자 작성 시 '=' 문자 뒤 기본 인자 선언 가능
```js
const greeting = funtion (name = 'Anonymous') {
    return `Hi ${name}`
}
greeting()
```
### 매개변수와 인자의 개수 불일치 허용
- 매개변수보다 인자의 개수가 많을 경우
```js
const noArgs = function() {
    return 0
}
noArgs(1, 2, 3) // 0

const twoArgs = function(arg1, arg2) {
    return [arg1, arg2]
}
twoArgs(1, 2, 3) // [1, 2]
```
- 매개변수보다 인자의 개수가 적을 경우
```js
const threeArgs = function(arg1, arg2, arg3) {
    return [arg1, arg2, arg3]
}
threeArgs()     // [undefined, undefined, undefined]
threeArgs(1)    // [1, undefined, undefined]
threeArgs(2, 3) // [2, 3, undefined]
```
### Spread syntax (...)
- 전개 구문
- 전개 구문을 사용하면 배열이나 문자열과 같이 반복 가능한 객체를 배열의 경우는 요소, 함수의 경우는 인자로 확장할 수 있음
    1. 배열과의 사용 (배열 복사)
    ```js
    let parts = ['어깨', '무릎']
    let lyrics = ['머리', ...parts, '발']
    console.log(lyrics) // ['머리', '어깨', '무릎', '발']
    ```
    2. 함수와의 사용 (Rest parameters)
        - 정해지지 않은 수의 매개변수를 배열로 받을 수 있음
    ```js
    function func(a, b, ...theArgs) {

    }

    const restOpr = function(arg1, arg2, ...restArgs) {
        return [arg1, arg2, restArgs]
    }
    restArgs(1, 2, 3, 4, 5) // [1, 2, [3, 4, 5]]
    restArgs(1, 2)          // [1, 2, []]
    ```
## 선언식과 표현식
### 함수의 타입
- 선언식 함수와 표현식 함수 모두 타입은 function으로 동일
```js
// 함수 표현식
const sum = function(args) {}

// 함수 선언식
function sub(args) {}

console.log(typeof sum) // function
console.log(typeof sub) // function
```
### 호이스팅 - 선언식
- 함수 선언식으로 정의한 함수는 var로 정의한 변수처럼 호이스팅이 발생
- 즉, 함수 호출 이후에 선언해도 동작함
```js
sum(2, 7)   // 9
function sum (num1, num2) {
    return num1+num2
}
```
### 호이스팅 - 표현식
- 반면 함수 표현식으로 선언한 함수는 함수 정의 전에 호출 시 에러 발생
- 함수 표현식으로 정의된 함수는 변수로 평가되어 변수의 scope규칙을 따름
```js
sub(7, 2)   // Uncaught ReferenceError: ...
const sub = function(num1, num2) {
    return num1-num2
}
```
## Arrow Function
### 화살표 함수 (Arrow Function)
- 함수를 비교적 간결하게 정의할 수 있는 문법
- function 키워드와 중괄호를 이용한 구문을 짧게 사용하기 위해 탄생
    1. function 키워드 생략 가능
    2. 함수의 매개변수가 하나 뿐이라면 매개변수의 '()' 생략 가능
    3. 함수의 내용이 한 줄이라면 '{}' 와 'return' 도 생략 가능
- 화살표 함수는 항상 익명 함수
    - ===함수표현식에서만 사용가능
```js
const arrow1 = function (name) {
    return `hello ${name}`
}

// 1. function 키워드 삭제
const arrow2 = (name) => {
    return `hello ${name}`
}

// 2. 인자가 1개일 경우에만 () 생략 가능
const arrow3 = name => {
    return `hello ${name}`
}

// 3. 함수 바디가 return을 포함한 표현식 1개일 경우에 {} & return 삭제 가능
const arrow4 = name => `hello ${name}`
```
### 화살표 함수 (Arrow Function) 응용
```js
// 1. 인자가 없다면? () or _ 로 표시 가능
let noArgs = () => 'No args'
noArgs = _ => 'No args'

// 2-1. object를 return 한다면
let returnObject = () => { return { key: 'value'} } // return 을 명시적으로 적어준다

// 2-2. return을 적지 않으려면 괄호를 붙여야 함
returnObject = () => ({ key: 'value' })
```
## this
- 어떠한 object를 가리키는 키워드
    - java에서의 this와 python에서의 self는 인스턴스 자기자신을 가리킴
- JavaScript의 함수는 호출될 때 this를 암묵적으로 전달 받음
- JavaScript에서의 this는 일반적인 프로그래밍 언어에서의 this와 조금 다르게 동작
- JavaScript는 해당 함수 호출 방식에 따라 this에 바인딩 되는 객체가 달라짐
- 즉, 함수를 선언할 때 this에 객체가 결정되는 것이 아니고, 함수를 호출할 때 함수가 어떻게 호출 되었는지에 따라 동적으로 결정됨
### this INDEX
1. 전역 문맥에서의 this
2. 함수 문맥에서의 this
    - 단순 호출
    - Method (객체의 메서드로서)
    - Nested
### 전역 문맥에서의 this
- 브라우저의 전역 객체인 window를 가리킴
    - 전역객체는 모든 객체의 유일한 최상위 객체를 의미
```js
console.log(this) // window
```
### 함수 문맥에서의 this
- 함수의 this 키워드는 다른 언어와 조금 다르게 동작
    - this의 값은 함수를 호출한 방법에 의해 결정됨
    - 함수 내부에서 this의 값은 함수를 호출한 방법에 의해 좌우됨
1. 단순 호출
    - 전역 객체를 가리킴
    - 브라우저에서 전역은 window를 의미함
    ```js
    const myFunc = function () {
        console.log(this)
    }
    //브라우저
    myFunc() // window
    ```
2. Method (Function in Object, 객체의 메서드로서)
    - 메서드로 선언하고 호출한다면, 객체의 메서드이므로 해당 객체가 바인딩
    ```js
    const myObj = {
        data: 1,
        myFunc() {
            console.log(this)   // myObj
            console.log(this.data)  // 1
        }
    }
    myObj.myFunc()  // myObj
    ```
3. Nested (Function 키워드)
    - forEach의 콜배 함수에서의 this가 메서드의 객체를 가리키지 못하고 전역 객체 window를 가리킴
    - 단순 호출 방식으로 사용되었기 때문
    - 이를 해결하기 위해 등장한 함수 표현식이 바로 "화살표 함수"
    ```js
    const myObj = {
        numbers: [1],
        myFunc() {
            console.log(this)   // myObj
            this.numbers.forEach(function (num)
            {
                console.log(num)    // 1
                console.log(this)   // window
            })
        }
    }
    myObj.myFunc()
    ```
3. Nested (화살표 함수)
    - 이전의 일반 function 키워드와 달리 메서드의 객체를 잘 가리킴
    - 화살표 함수에서 this는 자신을 감싼 정적 범위
    - 자동으로 한 단계 상위의 scope의 context를 바인딩
    ```js
    const myObj = {
        numbers: [1],
        myFunc() {
            console.log(this)   // myObj
            this.numbers.forEach((num) => {
                console.log(num)    // 1
                console.log(this)   // myObj
            })
        }
    }
    myObj.myFunc()
    ```
### 화살표 함수
- 화살표 함수는 호출의 위치와 상관없이 상위 스코프를 가리킴 (Lexical scope this)
- Lexical scope
    - 함수를 어디서 호출하는지가 아니라 어디에 선언하였는지에 따라 결정
    - Static scope 라고도 하며 대부분의 프로그래밍 언어에서 따르는 방식
- 따라서 함수 내의 함수 상황에서 화살표 함수를 쓰는 것을 권장

### Lexical scope
- 함수를 어디서 호출하는지가 아니라 **어디에 선언**하였는지에 따라 결정
```js
let x=1 // global

function first() {
    let x=10
    second()
}
function second() {
    console.log(x)
}
first() // 1
second() // 1
```
### this 정리
- 이렇게 this가 런타임에 결정되면 장점도 있고 단점도 있음
- 함수(메서드)를 하나만 만들어 여러 객체에서 재사용할 수 있다는 것은 장점이지만, 이런 유연함이 실수로 이어질 수 있다는 것은 단점
- JavaScript가 this를 다루는 방식이 좋은지, 나쁜지는 우리가 판단할 문제가 아님!
- 중요한 것은 개발자는 this의 동작 방식을 충분히 이해하고 장점을 취하면서 실수를 피하는 데만 집중하면 됨

# Array와 Object
- JavaScript의 데이터 타입 중 참조 타입에 해당 하는 타입은 Array와 Object이며, 객체라고도 말함
- 객체는 속성들의 모음(collection)
    - 속성?
        - ex) 컵 - 색: 빨간색, 길이: 15cm, 소재: 플라스틱
# 배열 (Array)
- 키와 속성들을 담고 있는 참조 타입의 객체
- 순서를 보장하는 특징이 있음
- 주로 대괄호([])를 이용하여 생성하고, 0을 포함한 양의 정수 인덱스로 특정 값에 접근 가능
- 배열의 길이는 array.length 형태로 접근 가능
```js
const numbers=[1, 2, 3, 4, 5]
console.log(numbers[0])     // 1
console.log(numbers[-1])    // undefined
console.log(numbers.length) // 5

console.log(numbers[numbers.length-1])  //5
console.log(numbers[numbers.length-2])  //4
console.log(numbers[numbers.length-3])  //3
console.log(numbers[numbers.length-4])  //2
console.log(numbers[numbers.length-5])  //1
```
## 배열 메서드 기초
|메서드|설명|비고|
|---|---|---|
|reverse|원본 배열의 요소들의 순서를 반대로 정렬| |
|push & pop|배열의 가장 뒤에 요소를 추가 또는 제거| |
|unshift & shift|배열의 가장 앞에 요소를 추가 또는 제거| |
|includes|배열에 특정 값이 존재하는지 판별 후 참/거짓 반환| |
|indexOf|배열에 특정 값이 존재하는지 판별 후 인덱스 반환|요소가 없을 경우 -1 반환|

- array.reverse()
    - 원본 배열 요소들의 순서를 반대로 정렬
    ```js
    const numbers=[1, 2, 3, 4, 5]
    numbers.reverse()
    console.log(numbers) // [5, 4, 3, 2, 1]
    ```
- array.push()
    - 배열의 가장 뒤에 요소 추가
- array.pop()
    - 배열의 마지막 요소 제거
    ```js
    const numbers=[1, 2, 3, 4, 5]
    
    numbers.push(100)
    console.log(numbers)        // [1, 2, 3, 4, 5, 100]

    console.log(numbers.pop())  // 100
    console.log(numbers)        // [1, 2, 3, 4, 5]
- array.includes(value)
    - 배열에 특정 값(value)이 존재하는지 판별 후 true 또는 false 반환
    ```js
    const numbers=[1, 2, 3, 4, 5]

    console.log(numbers.includes(1))    // true
    console.log(numbers.includes(100))  // false
    ```
- array.indexOf(value)
    - 배열에 특정 값이 존재하는지 확인 후 가장 첫 번째로 찾은 요소의 인덱스 반환
    - 만약 해당 값이 없을 경우 -1 반환
    ```js
    const numbers=[1, 2, 3, 4, 5]
    
    console.log(numbers.indexOf(3))     // 2
    console.log(numbers.indexOf(100))   // -1
    ```
## 배열 메서드 심화
### Array Helper Methods
- 배열을 순회하며 특정 로직을 수행하는 메서드
- 메서드 호출 시 인자로 callback 함수를 받는 것이 특징
|메서드|설명|비고|
|---|---|---|
|forEach|배열의 각 요소에 대해 콜백 함수를 한 번씩 실행|반환 값 없음|
|map|콜백 함수의 반환 값을 요소로 하는 새로운 배열 반환| |
|filter|콜백 함수의 반환 값이 참인 요소들만 모아서 새로운 배열을 반환| |
|reduce|콜백 함수의 반환 값들을 하나의 값(acc)에 누적 후 반환| |
|find|콜백 함수의 반환 값이 참이면 해당 요소를 반환| |
|some|배열의 요소 중 하나라도 판별 함수를 통과하면 참을 반환| |
|every|배열의 모든 요소가 판별 함수를 통과하면 참을 반환| |
### 콜백 함수
- 다른 함수의 인자로 전달되는 함수
```js
const numbers=[1, 2, 3]
numbers.forEach(function(num) {
    console.log(num**2)
})
// 1
// 4
// 9

const callBackFunction = function(num) {
    console.log(num**2)
}

const numbers=[1, 2, 3]
numbers.forEach(callBackFunction)
// 1
// 4
// 9
```
- python의 map에 square함수를 인자로 넘겨 numbers 배열의 각 요소를 square 함수의 인자로 사용하였음
```python
numbers=[1, 2, 3]
def square(num):

squared_numbers = list(map(square, numbers))
print(squared_numbers)  # [1, 4, 9]
```
- forEach 메서드에 callBackFunc 함수를 인자로 넘겨 numbers 배열의 각 요소를 callBackFunc 함수의 인자로 사용하였음
```js
const callBackFunc = function(num) {
    console.log(num**2)
}
// 1
// 4
// 9
const numbers = [1, 2, 3]
numbers.forEach(callBackFunc)
```
### Array Helper Methods - forEach
```js
array.forEach(funtion (element, index, array) {

})
```
- array.forEach(callback(element[, index[, array]]))
- 인자로 주어지는 함수(콜백 함수)를 배열의 각 요소에 대해 한 번씩 실행
    - 콜백 함수는 3가지 매개변수로 구넝
        1. element: 배열의 요소
        2. index: 배열요소의 인덱스
        3. array: 배열 자체
- 반환 값(return) 없음
```js
const colors = ['red', 'blue', 'green']

printFunc = function (color) {
    console.log(color)
}
colors.forEach(printFunc)
// red
// blue
// green

// 함수 정의를 인자로 넣기
colors.forEach(function (color) {
    console.log(color)
})

// 화살표 함수 적용
colors.forEach((color) => {
    return console.log(color)
})
```
### Array Helper Methods - map
```js
array.map(function (element, index, array) {

})
```
- array.map(callback(element[, index[, array]]))
- 배열의 각 요소에 대해 콜백 함수를 한 번씩 실행
- 콜백 함수의 반환 값을 요소로 하는 새로운 배열 반환
- 기존 배열 전체를 다른 형태로 바꿀 때 유용
    - forEach + return 이라고 생각
```js
const numbers = [1, 2, 3]

// 함수 정의(표현식)
const doubleFunc = function (number) {
    return number * 2
}

// 함수를 다른 함수의 인자로 넣기(콜백 함수)
const doubleNumbers = numbers.map(doubleFunc)
console.log(doubleNumbers)  // [2, 4, 6]

// 함수 정의를 인자로 넣기
const doubleNumbers = numbers.map(function (number) {
    return number * 2
})
console.log(doubleNumbers) // [2, 4, 6]

// 화살표 함수 적용
const doubleNumbers = numbers.mpa((number) => {
    return number * 2
})
console.log(doubleNumbers) // [2, 4, 6]
```

### Array Helper Methods - filter
```js
array.filter(function (element, index, array) {

})
```
- array.filter(callback(element[, index[, array]]))
- 배열의 각 요소에 대해 콜백 함수를 한 번씩 실행
- 콜백 함수의 반환 값이 true인 요소들만 모아서 새로운 배열 반환
- 기존 배열의 요소들을 필터링할 때 유용
```js
const products = [
    {name: 'cucumber', type: 'vegetable'},
    {name: 'banana', type: 'fruit'},
    {name: 'carrot', type: 'vegetable'},
    {name: 'apple', type: 'fruit'},
]

// 함수 정의
const fruitFilter = function (product) {
    return product.type === 'fruit'
}
// 콜백함수
cons fruits = products.filter(fruitFilter)
console.log(fruits)
// [{name: 'banana', type: 'fruit'}, {name: 'apple', type: 'fruit'}]

// 함수 정의를 인자로 넣기
const fruits = product.filter(function (product) {
    return product.type === 'fruit'
})
console.log(fruits)

// 화살표 함수 적용
const fruits = products.filter((product) => {
    return product.type === 'fruit'
})
console.log(fruits)
```

### Array Helper Methods - reduce
```js
array.reduce(function (acc, element, index, array) {

}, initialValue)
```
- array.reduce(callback(acc, element, [index[, array]])[, initialValue])
- 인자로 주어지는 함수(콜백 함수)를 배열의 각 요소에 대해 한 번씩 실행해서, 하나의 결과 값을 반환
- 즉, 배열을 하나의 값으로 계산하는 동작이 필요할 때 사용(총합, 평균 등)
- map, filter 등 여러 배열 메서드 동작을 대부분 대체할 수 있음
- reduce 메서드의 주요 매개변수
    - acc
        - 이전 callback 함수의 반환 값이 누적되는 변수
    - initialValue (optional)
        - 최초 callback 함수 호출 시 acc에 할당되는 값, default 값은 배열의 첫 번째 값
- reduce의 첫번째 매개변수인 콜백 함수의 첫번째 매개변수(acc)는 누적된 값(전 단계 까지의 결과)
- reduce의 두번째 매개변수인 initialValue는 누적될 값의 초기값, 지정하지 않을 시 첫번째 요소의 값이 됨
    - 빈 배열의 경우 initialValue를 제공하지 않으면 에러 발생
```js
// 콜백 함수 정의
const testx = [90, 90, 80, 77]

// 총합
const sum = tests.reduce(function (total, x) {
    return total + x
}, 0)
// 0 생략 가능, 결과: 337

// 화살표 함수
const sum = tests.reduce((total, x) => total + x, 0)
console.log(sum) // 337

// 평균
const sum = tests.reduce((total, x) => total + x, 0) / tests.length
console.log(sum) // 84.25
```

### Array Helper Methods - find
```js
array.find(function (element, index, array) {

})
```
- array.find(callback(element[, index[, array]]))
- 배열의 각 요소에 대해 콜백 함수를 한 번씩 실행
- 콜백 함수의 반환 값이 true면, 조건을 만족하는 첫번째 요소를 반환
- 찾는 값이 배열에 없으면 undefined반환
```js
const avengers = [
    {name:'Tony Stark', age:45},
    {name:'Steve Rogers', age:32},
    {name:'Thor', age:0},
]
const avenger = avengers.find(function (avenger) {
    return avenger.name === 'Tony Stark'
})
console.log(avenger) // {name:'Tony Stark', age:45}

// 화살표 함수 적용
const avenger = avengers.find((avenger) => {
    return avenger.name ==='Tony Stark'
})
console.log(avenger) // {name:'Tony Stark', age:45}
```

### Array Helper Methods - some
```js
array.some(function (element, index, array) {

})
```
- array.some(callback(element[, index[, array]]))
- 배열의 요소 중 하나라도 주어진 판별 함수를 통과하면 true반환
- 모든 요소가 통과하지 못하면 거짓 반환
- 빈 배열은 항상 false 반환
```js
const arr=[1,2,3,4,5]
const result=arr.some((ele) => {
    return ele%2===0
})
// true
```
### Array Helper Methods - every
```js
array.every(function (element, index, array) {

})
```
- array.every(callback(element[, index[, array]]))
- 배열의 모든 요소가 주어진 판별 함수를 통과하면 true반환
- 하나의 요소라도 통과하지 못하면 false반환
- 빈 배열은 항상 true반환
```js
const arr=[1,2,3,4,5]
const result=arr.every((ele) => {
    return ele%2===0
})
// false
```
### 배열 순회 비교
```js
const chars=['A', 'B', 'C', 'D']

// for loop
for (let idx=0; idx<chars.length; idx++) {
    console.log(idx, chars[idx])
}

// for ... of
for (const char of chars) {
    console.log(char)
}

// forEach
chars.forEach((char, idx) => {
    console.log(idx, char)
})

chars.forEach(char => {
    console.log(char)
})
```
|방식|특징|
|---|---|
|for loop|- 모든 브라우저 환경에서 지원 <br>- 인데스를 활용하여 배열의 요소에 접근 <br>- break,continue 사용 가능|
|for ... of|- 일부 오래된 브라우저 환경에서 지원 X <br>- 인덱스 없이 배열의 요소에 바로 접근 가능 <br>- break, continue 사용 가능|
|forEach|- 대부분의 브라우저 환경에서 지원 <br>- break, coutinue 사용 불가능 <br>- Airbnb Style Guide에서 권장하는 방식|

# 객체 (Object)
- 객체는 속성(property)의 집합이며, 중괄호 내부에 key와 value의 쌍으로 표현
- key
    - 문자열 타입만 가능
    - key 이름에 띄어쓰기 등의 구분자가 있으면 따옴표로 묶어서 표현
- value
    - 모든 타입(함수포함) 가능
- 객체 요소 접근
    - 점(.) 또는 대괄호([])로 가능
    - key 이름에 띄어쓰기 같은 구분자가 있으면 대괄호 접근만 가능
- 객체는 속성으로 함수를 정의할 수 있음 (메서드)
```js
const person = {
    name:'Viktor',
    age:30,
    greeting: function() {
        console.log(`Hello, my name in ${this.name}.`)
    }
};
person.greeting()
```
### 생성자 함수
- JS에서 객체를 하나 생성할때
    - 하나의 객체를 선언하여 생성
    ```js
    const member = {
        name:'kim',
        age:22,
        sId:2022311491,
    }
    ```
- 동일한 형태의 객체를 여러개 만들고 싶다면
    - 생성자 함수이 new 연산자를 사용
    ```js
    function Member(name, age, sId) {
        this.name=name
        this.age=age
        this.sId=sId
    }
    const member3=new Member('issac', 21, 2022654321)
    ```
- 함수의 이름은 반드시 대문자로 시작
- 생성자 함수를 사용할 때는 반드시 new연산자 사용
## 객체 관련 문법
- ES6에 새로 도입된 문법들로 객체 생성 및 조작에 유용하게 사용 가능
    1. 속성명 축약
    2. 메서드명 축약
    3. 계산된 속성명 사용
    4. 구조 분해 할당
    5. 객체 전개 구문(Spread Operator)
### 속성명 축약
- 객체를 정의할 때 key와 할당하는 변수의 이름이 같으면 예시와 같이 축약 가능
```js
var books=['Learning JavaScript', 'Learning Python']
var magazines=['Vogue', 'Science']

//ES5
var bookShop={
    books: books,
    magazines: magazines,
}
console.log(bookShop)
/*
{
    books: ['Learning JavaScript', 'Learning Python']
    magazines: ['Vogue', 'Science']
}
*/
```
```js
const books=['Learning JavaScript', 'Learning Python']
const magazines=['Vogue', 'Science']

//ES6+
const bookShop={
    books,
    magazines,
}
console.log(bookShop)
/*
{
    books: ['Learning JavaScript', 'Learning Python']
    magazines: ['Vogue', 'Science']
}
*/
```
### 메서드명 축약
- 메서드 선언 시 function 키워드 생략 가능
```js
// ES5
var obj={
    greeting: function() {
        console.log('hi')
    }
}
obj.greeting()
```
```js
// ES6+
const obj={
    greeting() {
        console.log('hi')
    }
}
obj.greeting()
```
### 계산된 속성
- 객체를 정의할 때 key의 이름을 표현식을 이용하여 동적으로 생성 가능
```js
const key='country'
const value=['한국', '미국', '일본', '중국']
const myObj={
    [key]:value,
}
console.log(myObj) // { country: ['한국', '미국', '일본', '중국'] }
console.log(myObj.country) // ['한국', '미국', '일본', '중국']
```

### 구조 분해 할당
- 배열 또는 객체를 분해하여 속성을 변수에 쉽게 할당할 수 있는 문법
```js
//ES5
const userInformation = {
    name: 'kim',
    userId: 'KIM',
    email: 'kim1234@gmail.com'
}

const name=userInformation.name
const userId=userInformation.userId
const email=userInformation.email
```
```js
//ES6+
const userInformation = {
    name: 'kim',
    userId: 'KIM',
    email: 'kim1234@gmail.com'
}

const {name}=userInformation
const {userId, email} = userInformation
```
### Spread syntax (...)
- 배열과 마찬가지로 전개구문을 사용해 객체 내부에서 객체 전개 가능
- 얕은 복사에 활용 가능
```js
const obj={b:2, c:3, d:4}
const newObj={a:1, ...obj, e:5}
console.log(newObj) // {a:1, b:2, c:3, d:4, e:5}
```
### JSON
- JavaScript Object Notation
- Key-Value 형태로 이루어진 자료 표기법
- JavaScript의 Object와 유사한 구조를 가지고 있지만 Object는 그 자체로 타입이고, JSON은 형식이 있는 문자열
- 즉, JSON을 Object로 사용하기 위해서는 변환 작업이 필요
```js
const jsObject = {
    coffee: 'Americano',
    iceCream: 'Cookie and cream',
}

// Object -> JSON
const objToJson = Json.stringify(jsObject)
console.log(objToJson)  // {"coffee":"Americano","iceCream":"Cookie and cream"}
console.log(typeop objToJson)   // string

// JSON -> Object
const jsonToObj = JSON.parse(objToJson)
console.log(jsonToObj)  // {coffee: 'Americano', iceCream: 'Cookie and cream'}
console.log(typeof jsonToObj)   // object
```
### 배열은 객체다
- 배열은 키와 속성들을 담고 있는 참조 타입의 객체
- 배열은 인덱스를 키로 가지며 length 프로퍼티를 갖는 특수한 객체