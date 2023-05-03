# Vue Data Management
## Data in components
- 정적 웹페이지가 아닌 동적 웹페이지를 만든다
    - 웹페이지에서 다뤄야 할 데이터가 등장
    - User data, 게시글, data 등등
- 한 페이지 내에서 같은 데이터를 공유 해야 함
    - Vue 에서 페이지 들은 component로 구분 되어 있음
- MyComponent에 정의된 data를 MyChild에서 사용하려면
    - 컴포넌트는 부모-자식 관계를 가지고 있으므로, 부모-자식 관계만 데이터를 주고받게 한다
    - 데이터의 흐름을 파악하기 용이하다
    - 유지 보수하기 쉬워진다
### pass props & emit event
- 부모 -> 자식으로의 데이터의 흐름
    - pass props 의 방식
- 자식 -> 부모로의 데이터의 흐름
    - emit event 의 방식
## Pass Props
- 요소의 속성(property)을 사용하여 데이터 전단
- props는 부모(상위) 컴포넌트의 정보를 전달하기 위한 사용자 지정 특성
- 자식(하위) 컴포넌트는 props 옵션을 사용하여 수신하는 props를 명시적으로 선언해야 함
### props in HelloWorld
- 프로젝트 생성 후 만들어지는 기본 템플릿에서 props를 확인할 수 있다
- App.vue의 msg라는 property를 확인 가능
```vue
<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>
```
- HelloWorld.vue에서 msg를 사용한 것을 확인할 수 있다
```vue
<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>
```
- App.vue에서 property로 넘긴 msg가 출력되는 걸 서버 실행 시 확인할 수 있다
- App.vue의 <HelloWorld /> 요소에 msg="~" 라는 property를 설정하고, 하위 컴포넌트인 HelloWorld는 자신에게 부여된 msg property를 template에서 {{ msg }} 의 형태로 사용한 것
- msg property의 value를 바꾸면 화면에 보이는 문장이 달라짐
### Pass Props
- 이런 부모 -> 자식 으로의 data 전달 방식을 pass props 라고 함
- 정적인 데이터를 전달하는 경우 static props 라고 명시하기도 함
- 요소에 속성을 작성하듯이 사용 가능하여, prop-data-name="value"의 형태로 데이터를 전달
    - 이때 속성의 키 값은 kebab-case를 사용
- Prop 명시
- 데이터를 받는 쪽, 즉 하위 컴포넌트에서도 props 에 대해 명시적으로 작성해 주어야 함
- 전달받은 props를 type과 함께 표시
- 컴포넌트를 문서화할 뿐만 아니라, 잘못된 타입을 전달하는 경우 브라우저의 자바스크립트 콘솔에서 사용자에게 경고
```vue
// HelloWorld.vue

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>
```
- 작성 순서
```vue
// MyComponent.vue

<template>
  <div class="border">
    <h1>This is MyComponent</h1>
    <MyChild static-props="component에서 child로" />
  </div>
</template>
```
```vue
// MyChild.vue

<script>
export default {
  name: 'MyChild',
  props: {
    staticProps: String,
  }
}
</script>
```
```vue
// MyChild.vue

<template>
  <div>
    <h3>This is child component</h3>
    <p>{{ staticProps }}</p>
  </div>
</template>
```
### Pass Props convention
- 부모에서 넘겨주는 props
    - kebab-case
    (HTML 속성명은 대소문자를 구분하지 않기 때문)
- 자식에서 받는 props
    - camelCase
- 부모 템플릿(html)에서 kebab-case로 넘긴 변수를 자식 스크립트(vue)에서 자동으로 cameCase로 변환하여 인식함
### Dynamic props
- 변수를 props로 전달할 수 있음
- v-bind directive를 사용해 데이터를 동적으로 바인딩
- 부모 컴포넌트의 데이터가 업데이트 되면 자식 컴포넌트로 전달되는 데이터 또한 업데이트 됨
```vue
// MyComponent.vue

<template>
  <div class="border">
    <h1>This is MyComponent</h1>
    <MyChild static-props="component에서 child로" 
    :dynamic-props="dynamicProps"
    />
  </div>
</template>

<script>
import MyChild from '@/components/MyChild'

export default {
  name: 'MyComponent',
  components: {
    MyChild,
  },
  data: function() {
    return {
      dynamicProps: "It's in data"
    }
  }
}
</script>
```
```vue
// MyChild.vue

<template>
  <div>
    <h3>This is child component</h3>
    <p>{{ staticProps }}</p>
    <p>{{ dynamicProps }}</p>
  </div>
</template>

<script>
export default {
  name: 'MyChild',
  props: {
    staticProps: String,
    dynamicProps: String,
  }
}
</script>
```
### 컴포넌트의 data 함수
- 각 vue 인스턴스는 같은 data 객체를 공유하므로 새로운 data 객체를 반환(return)하여 사용해야 함
```vue
<script>
data: function () {
    return{
        // component's data
    }
}
</script>
```
### Dynamic Props
- :dynamic-props="dynamicProps" 는 앞의 key값(dynamic-props)이란 이름으로 뒤의 " " 안에 오는 데이터(dynamicProps)를 전달하겠다는 뜻
- 즉, :my-props="dynamicProps"로 데이터를 넘긴다면, 자식 컴포넌트에서 myProps로 데이터를 받아야 함
```vue
// MyComponent.vue

<template>
  <div class="border">
    <h1>This is MyComponent</h1>
    <MyChild static-props="component에서 child로" 
    :my-props="dynamicProps"
    />
  </div>
</template>
```
```vue
// MyChild.vue

<template>
  <div>
    <h3>This is child component</h3>
    <p>{{ staticProps }}</p>
    <p>{{ myProps }}</p>
  </div>
</template>

<script>
export default {
  name: 'MyChild',
  props: {
    staticProps: String,
    myProps: String,
  }
}
</script>
```
- v-bind로 묶여있는 " " 안의 구문은 javascript의 구문으로 볼 수 있음
    - 따라서 dynamicProps라고 하는 변수에 대한 data를 전달할 수 있는 것
- 그렇다면, 숫자를 props로 전달하기 위해서 다음 두 방법 중 어떤게 맞을까
```vue
// 1
<SomeComponent num-props="1" />
//2
<SomeComponent :num-props="1" />
```
- 첫 번째 방식은 static props로 string으로서의 "1"을 전달
- 두 번째 방식은 dynamic props로 숫자로서의 1을 전달
### 단방향 데이터 흐름
- 모든 props는 부모에서 자식으로 즉 아래로 단방향 바인딩을 형성
- 부모 속성이 업데이트되면 자식으로 흐르지만 반대 방향은 아님
    - 부모 컴포넌트가 업데이트 될 때마다 자식 컴포넌트의 모든 prop들이 최신 값으로 새로고침 됨
- 목적
    - 하위 컴포넌트가 실수로 상위 컴포넌트 상태를 변경하여 앱의 데이터 흐름을 이해하기 힘들게 만드는 것을 방지
- 하위 컴포넌트에서 prop을 변경하려고 시도해서는 안되며 그렇게 하면 Vue는 콘솔에서 경고를 출력함
## Emit Event
- 부모 컴포넌트에서 자식 컴포넌트로 데이터를 전달할 때는 이벤트를 발생 시킴
- 이벤트를 발생시켜 데이터를 전달하는 방법
    1. 데이터를 이벤트 리스너의 콜백함수의 인자로 전달
    2. 상위 컴포넌트는 해당 이벤트를 통해 데이터를 받음
### $emit
- $emit 메서드를 통해 부모 컴포넌트에 이벤트를 발생
    - **$emit('event-name')** 형식으로 사용하며 부모 컴포넌트에 **event-name**이라는 이벤트가 발생했다는 것을 알림
    - 마치 사용자가 마우스 클릭을 하면 click 이벤트가 발생하는 것처럼 $emit('event-name') 이 실행되면 event-name 이벤트가 발생하는 것
- 작성 순서
1. 자식 컴포넌트에 버튼을 만들고 클릭 이벤트 추가
2. $emit을 통해 부모 컴포넌트에게 child-to-parent 이벤트를 트리거
```vue
// MyChild.vue

<template>
  <div>
    ...
    <button @click="childToParent">클릭</button>
  </div>
</template>

<script>
export default {
  ...
  methods: {
    childToParent: function () {
      this.$emit('child-to-parent', 'child data')
    }
  }
}
</script>
```
3. emit된 이벤트를 상위 컴포넌트에서 청취 후 핸들러 함수 실행
```vue
// MyComponent.vue

<template>
  <div class="border">
    <h1>This is MyComponent</h1>
    <MyChild 
    ...
    @child-to-parent="parentGetEvent"
    />
  </div>
</template>

<script>
import MyChild from '@/components/MyChild'

export default {
  ...
  methods: {
    parentGetEvent(inputData) {
      console.log('자식 컴포넌트에서 발생한 이벤트')
      console.log(`child에서 보낸${inputData}`)
    }
  }
}

</script>
```
### emit Event 흐름 정리
1. 자식 컴포넌트에 있는 버튼 클릭 이벤트를 청취하여 연결된 핸들러 함수(ChildToParent)호출
2. 호출된 함수에서 $emit을 통해 상위 컴포넌트에 이벤트(child-to-parent)발생
3. 상위 컴포넌트는 자식 컴포넌트가 발생시킨 이벤트(child-to-parent)를 청취하여 연결된 핸들러 함수(parentGetEvent)호출
### emit with data