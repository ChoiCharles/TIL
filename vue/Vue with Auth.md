# DRF Auth System
## Authentication & Authorization
### Authentication - 인증, 입증
- 자신이라고 주장하는 사용자가 누구인지 확인하는 행위
- 모든 보안 프로세스의 첫 번째 단계 (가장 기본 요소)
- 즉, 내가 누구인지를 확인하는 과정
- 401 Unauthorized
  - HTTP 표준에서는 "미승인(unauthorized)"을 명확히 하고 있지만, 의미상 이 응답은 "비인증(unauthenticated)"을 의미
### Authorization - 권한 부여, 허가
- 사용자에게 특정 리소스 또는 기능에 대한 액세스 권한을 부여하는 과정(절차)
- 보안 환경에서 권한 부여는 항상 인증이 먼저 필요함
  - 사용자는 조직에 대한 액세스 권한을 부여 받기 전에 먼저 자신의 ID가 진짜인지 먼저 확인해야 함
- 서류의 등급, 웹 페이지에서 글을 조회 & 삭제 & 수정 할 수 있는 방법, 제한 구역
  - 인증이 되었어도 모든 권한을 부여 받는 것은 아님
- 403 Forbidden
  - 401과 다른 점은 서버는 클라이언트가 누구인지 알고 있음
- Authentication and authorization work together
- 회원가입 후, 로그인 시 서비스를 이용할 수 있는 권한 생성
  - 인증 이후에 권한이 따라오는 경우가 많음
- 단, 모든 인증을 거쳐도 권한이 동일하게 부여되는 것은 아님
  - Django에서 로그인을 했더라도 다른사람의 글까지 수정 / 삭제가 가능하진 않음
- 세션, 토큰, 제 3자를 활용하는 등의 다양한 인증 방식이 존재
## authentication determined
### 인증 여부 확인 방법
- DRF 공식문서에서 제안하는 인증 절차 방법
  - https://www.django-rest-framework.org/api-guide/authentication/

![](2023-05-15-10-17-56.png)

- BasicAuthentication, SessionAuthentication?
  - settings.py에 작성해야 할 설정
    - "기본적인 인증 절차를 어떠한 방식으로 둘 것이냐"를 설정하는 것
    - 예시의 2가지 방법 외에도 각 framework마다 다양한 인증 방식이 있음
- 뒤에서 사용할 방법은 DRF가 기본으로 제공해주는 인증 방식 중 하나인 **TokenAuthentication**
- 모든 상황에 대한 인증 방식을 정의 하는 것이므로, 각 요청에 따라 다른 인증 방식을 거치고자 한다면 다른 방식이 필요
- view 함수마다 (각 요청마다) 다른 인증 방식을 설정하고자 한다면 decorator 활용

![](2023-05-15-10-21-29.png)

- [참고] permisson_classes
  - 권한 관련 설정
  - 권한 역시 특정 view 함수마다 다른 접근 권한을 요구 할 수 있음
### 다양한 인증 방식
- BasicAuthentication
  - 가장 기본적인 수준의 인증 방식
  - 테스트에 적합
- SessionAuthentication
  - Django에서 사용했던 session기반의 인증 시스템
  - DRF와 Django의 session인증 방식은 보안적 측면을 구헝하는 방법에 차이가 있음
- RemoteUserAuthentication
  - Django의 Remote user방식을 사용할 때 활용하는 인증 방식
- TokenAuthentication
  - 매우 간단하게 구현 할 수 있음
  - 기본적인 보안 기능 제공
  - 다양한 외부 패키지가 있음
- [중요] settings.py에서 **DEFAULT_AUTHENTICATION_CLASSES**를 정의
  - TokenAuthentication 인증 방식을 사용할 것임을 명시

### TokenAuthentication 사용 방법
- INSTALLED_APPS에 **rest_ramework.authtoken** 등록
```python
INSTALLED_APPS = [
    ...,
    'rest_framework.authtoken',
]
```
- 각 User 마다 고유 Token 생성
```python
from rest_framework.authtoken.models import Token

token = Token.objects.create(user=...)
print(token.key)
```
- 생성한 Token을 각 User에게 발급
  - User는 발급 받은 Token을 요청과 함께 전송
  - Token을 통해 User 인증 및 권한 확인
- Token 발급 방법
```python
def some_view_func(request):
    token = Token.objects.create(user=...)
    return Response({'token':token.key})
```
- User는 발급 받은 Token을 headers에 답아 요청과 함께 전송
  - 단, 반드시 Token 문자열 함께 삽입
    - 삽입해야 할 문자열은 각 인증 방식 마다 다름 (ex. Bearer, Auth, JWT 등)
  - 주의) Token 문자열과 발급받은 실제 token사이를 ' '(공백)으로 구분
- Authorization HTTP headers 작성 방법
```
Authorization: Token 9944b09199c62bcf9418ad846dd0e4bbdfc6ee4b
```
### 토큰 생성 및 관리 문제점
- 기본 제공 방식에서 고려하여야 할 사항들
  1. Token 생성 시점
  2. 생성한 Token 관리 방법
  3. User와 관련된 각종 기능 관리 방법
    - 회원가입
    - 로그인
    - 회원 정보 수정
    - 비밀 번호 변경 등 ...
## Dj-rest-auth
- 회원가입, 인증(소셜미디어 인증 포함), 비밀번호 재설정, 사용자 세부 정보 검색, 회원 정보 수정 등을 위한 REST API end point 제공
- 주의) Django-rest-auth는 더 이상 업데이트를 지원하지 않음 Dj-rest-auth사용
- https://github.com/iMerica/dj-rest-auth
### Dj-rest-auth 사용방법
1. 패키지 설치
```
$ pip install dj-rest-auth
```
2. App 등록
```python
INSTALLED_APPS = (
  ...,
  'rest_framework',
  'rest_framework.authtoken',
  ...,
  'dj_rest_auth',
)
```
3. url 등록
```python
urlpatterns = [
  path('dj-rest-auth/', include('dj_rest_auth.urls')),
]
```
- 시작전에 auth.User를 accounts.User로 변경 필요
- auth.User로 설정된 DB제거
- settings.py에 "AUTH_USER_MODEL = 'accounts.User'" 추가
```python
# settings.py

AUTH_USER_MODEL = 'accounts.User'
```
### dj-rest-auth 사용하기
- dj-rest-auth 설치
```
$ pip install dj-rest-auth
```
- settings.py에 추가
```python

INSTALLED_APPS = [
  ...,
  'rest_framework.authtoken',
  'dj_rest_auth',
]
```
- makemigrations & migrate
- my_api/urls.py 경로 추가
```python
urlpatterns = [
  ...,
  path('accounts/', include('dj_rest_auth.urls'))
]
```
- /accounts/ 로 이동해 확인
  - 회원가입 기능 없음
- 공식문서 상에서 회원가입은 optional로 따로 설치해야함
### Registration
- Registration기능을 사용하기 위해 추가 기능 등록 및 설치 필요
  - dj-rest-auth는 소셜 회원가입을 지원한다
  - dj-rest-auth의 회원가입 기능을 사용하기 위해서는 django-allauth필요
- django-allauth설치
```
# 반드시 '' 도 함께 입력
$ pip install 'dj-rest-auth[with_social]'
```
- settings.py에 내용 추가
```python
INSTALLED_APPS = [
  ...,
  'django.contrib.sites',
  'allauth',
  'allauth.account',
  'allauth.socialaccount',
  'dj_rest_auth.registration'
]

REST_AUTH = {
  'SESSION_LOGIN':False
}

SITE_ID = 1
```
- [참고] SITE_ID ?
  - Django는 하나의 컨텐츠를 기반으로 여러 도메인에 컨텐츠를 게시 가능하도록 설계됨
  - 다수의 도메인이 하나의 데이터베이스에 등록
  - 현재 프로젝트가 첫번째 사이트임을 나타냄
- urls.py 경로 추가
```python
urlpatterns = [
  path('accounts/signup/', include('dj_rest_auth.registration.urls'))
]
```
- migrate
- /accounts/signup/ 페이지 확인
- Get method는 접근 불가
- POST 요청 양식 제공
### Sign up & Login
- 회원가입 요청 후 결과 확인
  - 요청에 대한 응답으로 Token 발급
- 로그인 시에도 동일한 토큰 발급
  - 정상적인 로그인 가능
- 발급 받은 토큰은 테스트를 위해 기록
### Password change
- /accounts/password/change/ 기능 확인
  - 로그인 되어 있거나, 인증이 필요한 기능
  - DRF 자체 제공 HTML form 에서는 토큰을 입력할 수 있는 공간이 없음
  - Postman에서 진행
- settings.py에 내용 추가
```python
REST_FRAMEWORK = {
  'DEFAULT_AUTHENTICATION_CLASSES': [
    'rest_framework.authentication.TokenAuthentication',
  ],
}
```

- Postman으로 양식에 맞춰 POST 요청
  - body/form-data에 값 입력

![](2023-05-15-13-53-20.png)

- headers에 Token 입력
  - Authorization: Token { your token } 형식에 맞춰 입력

![](2023-05-15-13-54-10.png)

- 결과 확인

## Permission setting
- DRF 공식문서 -> API Guide -> Permissions 확인
- 권한 세부 설정
  1. 모든 요청에 대해 인증을 요구 하는 설정
  2. 모든 요청에 대해 인증이 없어도 혀용하는 설정
- 설정 위치 == 인증 방법을 설정한 곳과 동일
  - 우선 모든 요청에 대해 허용 설정
```python
REST_FRAMEWORK = {
    'DEFAULT_PERMISSION_CLASSES': [
        'rest_framework.permissions.AllowAny',
    ],
}
```
### Article List Read
- articles/views.py 작성
- 게시글 조회 및 생성 요청 시 인증된 경우만 허용하도록 권한 부여
  - decorator를 할용
```python
from rest_framework.decorators import permission_classes
from rest_framework.permissions import IsAuthenticated

@api_view(['GET', 'POST'])
@permission_classes([IsAuthenticated])
def article_list(request):
    if request.method == 'GET':
        # articles = Article.objects.all()
        articles = get_list_or_404(Article)
        serializer = ArticleListSerializer(articles, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = ArticleSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            # serializer.save(user=request.user)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
```
- /articles/ 조회 요청 확인
- 게시글 조회 시 로그인 필요
### Article Create
- /articles/ 생성 요청 확인
  - Postman으로 진행

![](2023-05-15-14-07-25.png)

![](2023-05-15-14-07-39.png)

- 결과 확인

![](2023-05-15-14-08-10.png)

### Article Detail Read
- /articles/1/ 상세 조회 요청 확인
  - headers에 token을 답지 않아도 조회 가능
  - 인증 필요 권한 설정을 따로 하지 않았기 때문

![](2023-05-15-14-10-18.png)

### 정리
1. 인증 방법 설정
  - DEFAULT_AUTHENTICATION_CLASSES
2. 권한 설정하기
  - DEFAULT_PERMISSION_CLASSES
3. 인증 방법, 권한 세부 설정도 가능
  - @authentication_classes
  - @permission_classes
4. 인증 방법은 다양한 방법이 있으므로 내 서비스에 적합한 방식을 선택

## DRF Auth with Vue
### Vue Server 요청 정상 작동 여부 확인
- 정상 작동하던 게시글 전체 조회 요청이 작동하지 않음
  - 401 status code
  - 인증되지 않은 사용자이므로 조회 요청이 불가능해진 것
### SignUp Page
- views/SignUpView.vue 코드 확인
  - Server에서 정의한 field명 확인
    1. username
    2. password1
    3. password2
```vue
// views/SignUpView.vue

<template>
  <div>
    <h1>Sign Up Page</h1>
    <form>
      <label for="username">username : </label>
      <input type="text" id="username"><br>

      <label for="password1"> password : </label>
      <input type="password" id="password1"><br>

      <label for="password2"> password confirmation : </label>
      <input type="password" id="password2">
      
      <input type="submit" value="SignUp">
    </form>
  </div>
</template>
```
- router/index.js 설정
```js
// router/index.js

...
import SignUpView from '@/views/SignUpView'
...

Vue.use(VueRouter)

const routes = [
  ...
  {
    path: '/signup',
    name: 'SignUpView',
    component: SignUpView
  },
]
```
- src/App.vue 작성
  - 파이프 라인 등을 활용하여 링크간 공간 확보
```vue
// src/App.vue

<template>
  <div id="app">
    <nav>
      <router-link :to="{ name: 'ArticleView' }">Articles</router-link> | 
      <router-link :to="{ name: 'SignUpView' }">SignUpPage</router-link> | 
    </nav>
    <router-view/>
  </div>
</template>
```
- views/SignUpView.vue 결과 확인
### SignUp Request
- 회원가입을 완료 시 응답 받을 정보 Token을 store에서 관리할 수 있도록 actions를 활용하여 요청 후, state에 저장할 로직 작성
  - 회원가입이나 로그인 후 얻을 수 있는 Token은 server를 구성 방식에 따라 매 요청마다 요구 할 수 있으므로, 다양한 컴포넌트에서 쉽게 접근 할 수 있도록 중앙 상태 저장소인 vuex에서 관리
- views/SignUpView.vue 작성
  - 사용자 입력 값을 하나의 객체 payload에 담아 전달
```vue
// views/SignUpView.vue

<template>
  <div>
    <h1>Sign Up Page</h1>
    <form @submit.prevent="signUp">
      <label for="username">username : </label>
      <input type="text" id="username" v-model="username"><br>

      <label for="password1"> password : </label>
      <input type="password" id="password1" v-model="password1"><br>

      <label for="password2"> password confirmation : </label>
      <input type="password" id="password2" v-model="password2">
      
      <input type="submit" value="SignUp">
    </form>
  </div>
</template>

<script>
export default {
  name: 'SignUpView',
  data() {
    return {
      username: null,
      password1: null,
      password2: null,
    }
  },
  methods: {
    signUp() {
      const username = this.username
      const password1 = this.password1
      const password2 = this.password2
      console.log('asdf')
      const payload = {
        username, password1, password2
      }
      this.$store.dispatch('signUp', payload)
    }
  }
}
</script>

```
- store/index.js actions 작성
- payload가 가진 값을 각각 할당
- AJAX 요청으로 응답 받은 데이터는 다수의 컴포넌트에서 사용해야 함
- state에 저장 할 것
```js
// store/index.js

actions: {
  signUp(context, payload) {
    const username = payload.username
    const password1 = payload.password1
    const password2 = payload.password2
    axios({
      method: 'post',
      url: `${API_URL}/accounts/signup/`,
      data: {
        username, password1, password2
      }
    })
    .then(res => {
      context.commit('SIGN_UP', res.data.key)
    })
    .catch(err => console.log(err))
  }
},
```
- store/index.js mutations 작성
- token을 저장할 위치 확인
- mutations를 통해 state 변화
```js
// store/index.js

export default new Vuex.Store({
  state: {
    token: null
  },
  mutations: {
    SIGN_UP(state, token){
      state.token = token
    }
  },
})
```
- 요청 결과 확인
### 토큰 관리
- 게시물 전체 조회와 달리, 인증 요청의 응답으로 받은 Token은 매번 요청하기 힘듦
  - 비밀번호를 항상 보관하고 있을 수는 없음
  - localStorage에 token 저장을 위해 vuex-persistedstate 활용
- 설치
```
$ npm install vuex-persistedstate
```
- store/index.js 내용 추가
```js
// store/index.js

import createPersistedState from 'vuex-persistedstate'

export default new Vuex.Store({
  plugins: [
    createPersistedState(),
  ],
})
```
- localStorage 확인
### [참고] User 인증 정보를 localStorage에 저장해도 될까?
- 안전한 방법은 아니다
- 따라서, vuex-persistedstate는 아래 2가지 방법을 제공
  1. 쿠키를 사용하여 관리
  2. 로컬 저장소를 난독화 하여 관리

## Login Request
### Login Page
- views/LogInView.vue 작성
- 회원가입 로직과 동일
- Server에서 정의한 field명 확인
  1. username
  2. password
```vue
// views/LogInView.vue

<template>
  <div>
    <h1>LogIn Page</h1>
    <form @submit.prevent="login">
      <label for="username">username : </label>
      <input type="text" id="username" v-model="username"><br>

      <label for="password"> password : </label>
      <input type="password" id="password" v-model="password"><br>

      <input type="submit" value="logIn">
    </form>
  </div>
</template>

<script>
export default {
  name: 'LogInView',
  data() {
    return {
      username: null,
      password: null,
    }
  },
  methods: {
    login() {
      const username = this.username
      const password = this.password
      const payload = {
        username, password
      }
      this.$store.dispatch('login', payload)
    }
  }
}
</script>
```
- router/index.js 내용 추가
```js
...
import LogInView from '@/views/LogInView'

...

const routes = [
  ...
  {
    path: '/login',
    name: 'LogInView',
    component: LogInView
  },
  ...
]
```
- src/App.vue 내용 추가
```vue
// src/App.vue

<template>
  <div id="app">
    <nav>
      ...
      <router-link :to="{ name: 'LogInView' }">LogInPage</router-link>
    </nav>
    <router-view/>
  </div>
</template>
```
- 로그인 화면 확인
### Login Request
- signUp과 다른 점은 password1, password2 가 password로 바뀐것 뿐
- 요청을 보내고 응답을 받은 Token을 state에 저장하는 것 까지도 동일
  - mutations가 처리 해야 하는 업무가 동일
  - SIGN_UP mutations를 SAVE_TOKEN mutations로 통합해 signup과 login모두 접근가능
- views/LogInView.vue 내용 추가
  - 사용자 입력 값을 하나의 객체 payload에 담아 전달
```vue
// views/LogInView.vue

<script>
export default {
  name: 'LogInView',
  data() {
    return {
      username: null,
      password: null,
    }
  },
  methods: {
    login() {
      const username = this.username
      const password = this.password
      const payload = {
        username, password
      }
      this.$store.dispatch('login', payload)
    }
  }
}
</script>
```
- store/index.js 추가
- payload가 가진 값을 각각 할당
- AJAX 요청으로 응답 받은 데이터는 다수의 컴포넌트에서 사용해야 함
- state에 저장 할 것
  - 이 때, mutations는 SAVE_TOKEN 호출 확인
```js
// store/index.js
mutations: {
  ...
  SAVE_TOKEN(state, token){
    state.token = token
  }
},

actions: {
  ...
  signUp(context, payload) {
    const username = payload.username
    const password1 = payload.password1
    const password2 = payload.password2
    axios({
      method: 'post',
      url: `${API_URL}/accounts/signup/`,
      data: {
        username, password1, password2
      }
    })
    .then(res => {
      console.log(res)
      context.commit('SAVE_TOKEN', res.data.key)
    })
    .catch(err => console.log(err))
  },
  login(context, payload) {
    const username = payload.username
    const password = payload.password
    axios({
      method: 'post',
      url: `${API_URL}/accounts/login/`,
      data: {
        username, password
      }
    })
    .then((res) => {
      context.commit('SAVE_TOKEN', res.data.key)
    })
    .catch((err) => {
      console.log(err)
    })
  }
},
```
- 최종 결과 확인
  - 정확한 결과 확인을 위해 기존 토큰 삭제 추천

### IsAuthenticated in Vue
- 회원가입, 로그인 요청에 대한 처리 후 state에 저장도니 Token을 직접 확인하기 전까지 인증 여부 확인 불가
- 인증 되지 않았을 시 게시글 정보를 확인할 수 없으나 이유를 알 수 없음
  - 로그인 여부를 확인 할 수 있는 수단이 없음
- store/index.js 추가
- 로그인 여부 판별 코드
  - Token이 있으면 true 없으면 false 반환
```js
// store/index.js

export default new Vuex.Store({
  ...
  getters: {
    isLogin(state) {
      return state.token ? true : false
    }
  },
})
```
- views/ArticleView.vue 추가
  - isLogin 정보를 토대로 게시글 정보를 요청할 것인지, LogInView로 이동시킬 것인지 결정
```vue
// views/ArticeView.vue

<script>
export default {
  ...
  computed:{
    isLogin() {
      return this.$store.getters.isLogin
    }
  },
  methods: {
    getArticles() {
      if (this.isLogin) {
        this.$store.dispatch('getArticles')
      } else {
        alert('로그인이 필요한 페이지 입니다')
        this.$router.push({name: 'LogInView'})
      }

    }
  }
}
</script>
```
- store/index.js 내용 추가
- store/index.js에서는 $router에 접근 할 수 없음
  - router를 import 해야 함
```js
// store/index.js

import router from '@/router'
...
export default new Vuex.Store({
  ...
  mutations: {
    ...
    SAVE_TOKEN(state, token){
      state.token = token
      router.push({name:'ArticleView'})
    }
  },
})
```
- localStorage에서 token 삭제 후, 새로고침
- articles 링크 클릭 시 LoginPage로 이동
> 로그인 후 articles에선
- 인증은 받았지만 게시글 조회 시 인증 정보를 담아 보내고 있지 않음
- 원인
  - 로그인은 해쓰나 Django에서는 로그인 한 것으로 인식하지 못함
  - 발급 받은 token을 요청으로 보내지 않았기 때문
## Request with Token
- Token이 준비되었으니 headers HTTP에 Token을 담아 요청을 보내면 된다
### Article List Read with Token
- store/index.js 추가
  - headers에 Authorizations와 token추가
```js
// store/index.js

actions: {
  getArticles(context) {
    axios({
      method: 'get',
      url: `${API_URL}/api/v1/articles/`,
      headers: {
        Authorization: `Token ${context.state.token}`
      }
    })
    .then((res) => {
    // console.log(res, context)
      context.commit('GET_ARTICLES', res.data)
    })
    .catch((err) => {
      console.log(err)
    })
  },
}
```
- 결과 확인
  - 404오류는 view함수가 그렇게 처리하도록 했기 때문
  - 게시글 생성 기능 완성 후, 다시 결과 확인
### Article Create with Token
- views/CreateView.vue 추가
  - headers에 Authorization와 token 추가
```vue
// views/CreateView.vue

<script>
import axios from 'axios'
const API_URL = 'http://127.0.0.1:8000'

export default {
  name: 'CreateView',
  data() {
    return {
      title: null,
      content: null,
    }
  },
  methods: {
    createArticle() {
      const title = this.title
      const content = this.content

      if (!title) {
        alert('제목 입력해주세요')
        return
      } else if (!content){
        alert('내용 입력해주세요')
        return
      }
      axios({
        method: 'post',
        url: `${API_URL}/api/v1/articles/`,
        data: { title, content},
        headers: {
          Authorization: `Token ${this.$store.state.token}`
        }
      })
      .then(() => {
        // console.log(res)
        this.$router.push({name: 'ArticleView'})
      })
      .catch((err) => {
        console.log(err)
      })
    }
  }
}
</script>
```
- 정상 작동 확인

### Create Article with User
- articles/models.py 추가
- 게시글 작성 시 User 정보를 포함하여 작성하도록 수정
  - User 정보를 Vue에서도 확인 가능하도록 정보 제공
```python
# articles/models.py

from django.db import models
from django.conf import settings

# Create your models here.
class Article(models.Model):
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    title = models.CharField(max_length=100)
    content = models.TextField()
    ...
```
- makemigrations & migrate
  - 기존 게시글에 대한 User 정보 default값 설정
- articles/serializers.py 추가
  - ArticleListSerializer에서 user는 사용자가 작성 하지 않음 -> fields에 추가
  - ArticlesSerializer에서 user는 읽기 전용으로 제공
  - username을 확인 할 수 있도록 username field 정의 필요
```python
# articles/serializers.py

class ArticleListSerializer(serializers.ModelSerializer):
    username = serializers.CharField(source='user.username', read_only=True)

    class Meta:
        model = Article
        # fields = ('id', 'title', 'content')
        fields = ('id', 'title', 'content', 'user', 'username')

class ArticleSerializer(serializers.ModelSerializer):
    comment_set = CommentSerializer(many=True, read_only=True)
    comment_count = serializers.IntegerField(source='comment_set.count', read_only=True)
    username = serializers.CharField(source='user.username', read_only=True)

    class Meta:
        model = Article
        fields = '__all__'
        read_only_fields = ('user', )
```
- articles/views.py 수정
  - 게시글 생성 시 user 정보 저장
```python
# articles/views.py

@api_view(['GET', 'POST'])
@permission_classes([IsAuthenticated])
def article_list(request):
    ...
    elif request.method == 'POST':
        serializer = ArticleSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            # serializer.save()
            serializer.save(user=request.user)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
```
- components/ArticleListItem.vue 추가
  - article이 가지고 있을 user 정보 출력
```vue
// components/ArticleListItem.vue

<template>
  <div>
    <h5>{{ article.id }}</h5>
    <p>작성자 : {{ article.username }}</p>
    <p>{{ article.title }}</p>
    <router-link :to="{
      name: 'DetailView',
      params: {id: article.id }}">
      [DETAIL]
    </router-link>
    <hr>
  </div>
</template>
```
- 작성자 정보 확인