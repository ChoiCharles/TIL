# 인증과 권한
> 개요
- Django authentication system(인증 시스템)은 **인증(Authentication)**과 **권한(Authorization)**부여를 함께 제공(처리)하며, 이러한 기능을 일반적으로 인증 시스템이라고 함
- 필수 구성은 settings.py에 이미 포함되어 있으며 INSTALLED_APPS에서 확인 가능
    - django.contrib.auth
- Authentication(인증)
    - 신원 확인
    - 사용자가 자신이 누구인지 확인하는 것
- Authorization(권한, 허가)
    - 권한 부여
    - 인증된 사용자가 수행할 수 있는 작업을 결정
- 사전 설정
    - 두 번째 app accounts 생성 및 등록
# Custom User Model
> 개요
- "Custom User Model로 **대체하기**"
- Django는 기본적인 인증 시스템과 여러가지 필드가 포함된 User Model을 제공, 대부분은 개발 환경에서 기본 User Model을 Custom User Model로 대체함
- 개발자들이 작성하는 일부 프로젝트에서는 django에서 제공하는 built-in User model의 기본 인증 요구사항이 적절하지 않을 수 있음
- Django는 현재 프로젝트에서 사용할 User Model을 결정하는 **AUTH_USER_MODEL**설정 값으로 Default User Model을 재정의(override)할 수 있도록 함
### AUTH_USER_MODEL
---
- 프로젝트에서 User를 나타낼 때 사용하는 모델
- 프로젝트가 진행되는 동안 (모델을 만들고 마이그레이션 한 후) 변경할 수 없음
- 프로젝트 시작 시 설정하기 위한 것이며, 참조하는 모델은 첫 번째 마이그레이션에서 사용할 수 있어야 함
    - 즉, 첫번째 마이그레이션 전에 확정 지어야 하는 값
- 다음과 같은 기본 값을 가지고 있음
```python
# setting.py
AUTH_USER_MODEL = 'auth.User'
```
## substituting a custom User model
---
- AbstractUser를 상속받는 커스텀 User 클래스 작성
- 기존 User 클래슫 AbstractUser로 상속받기 때문에 커스텀 User 클래스도 완전히 같은 모습을 가지게 됨
```python
# accounts/models/py
from django.contrib.auth.models import AbstractUser

class User(AbstractUser):
    pass
```
- Django 프로젝트에서 User를 나타내는데 사용하는 모델을 방금 생성한 커스텀 User 모델로 지정 (settings.py 아무데나 적어도 상관 없음)
```python
# settings.py
AUTH_USER_MODEL = 'accounts.User'
```
- admin.py에 커스텀 User 모델을 등록
    - 기본 User모델이 아니기 때문에 등록하지 않으면 admin site에 출력되지 않음
```python
# accounts/admin.py
from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from .models import User

admin.site.register(User, UserAdmin)
```
> [주의] 프로젝트 중간에 AUTH_USER_MODEL변경하기
- 모델 관계에 영향을 미치기 때문에 훨씬 더 어려운 작업이 필요
- 프로젝트 처음에 위 과정을 확실하게 진행하기

> 반드시 User 모델을 대체해야 하나?
- Django는 새 프로젝트를 시작하는 경우 기본 User모델이 충분 하더라도 커스텀 User 모델을 설정하는 것을 **강력하게 권장**
- 커스텀 User 모델은 **기본 User 모델과 동일하게 작동 하면서도 필요한 경우 나중에 맞춤 설정할 수 있기 때문**
    - 단, User 모델 대체 작업은 프로젝트의 모든 migrations 혹은 첫 migrate를 실행하기 전에 이 작업을 마쳐야 함

### 데이터베이스 초기화
---
1. migrations 파일 삭제
    - migrations 폴더 및 __init__.py는 삭제하지 않음
    - 번호가 붙은 파일만 삭제
2. db.sqlite3 삭제
3. migrations 진행
    - makemigrations
    - migrate
# 쿠키
> 개요
- HTTP 쿠키는 상태가 있는 세션을 만들도록 해줌
### 개념
---
- 서버가 사용자의 웹 브라우저에 전송하는 작은 데이터 조각이다
- 사용자가 웹사이트를 방문할 경우 해당 웹사이트의 서버를 통해 사용자의 컴퓨터에 설치되는 작은 기록 정보 파일
    1. 브라우저(클라이언트)는 쿠키를 로컬에 KEY-VALUE의 데이터 형식으로 저장
    2. 이렇게 쿠키를 저장해 놓았다가, **동일한 서버에 재요청 시 저장된 쿠키를 함께 전송**
- 쿠키는 두 요청이 동일한 브라우저에서 들어왔는지 아닌지를 판단할 때 주로 사용됨
    - 이를 이용해 사용자의 로그인 상태를 유지할 수 있음
    - 상태가 없는(stateless) HTTP 프로토콜에서 상태 정보를 기억 시켜 주기 때문
- 즉, 웹 페이지에 접속하면 웹 페이지를 응답한 서버로부터 쿠키를 받아 브라우저에 저장하고, 클라이언트가 같은 서버에 재요청 시마다 요청과 함께 저장해 두었던 쿠키도 함께 전송

### 사용 목적
---
1. 세션 관리
    - 로그인, 아이디 자동완선, 공지 하루 안 보기, 팝업 체크, 장바구니 등의 정보 관리
2. 개인화
    - 사용자 선호, 테마 등의 설정
3. 트래킹
    - 사용자 행동을 기록 및 분석

### 세션
---
- 사이트와 특정 브라우저 사이의 "state(상태)"를 유지시키는 것
- 클라이언트가 서버에 접속하면 서버가 특정 session id를 발급하고, 클라리언트는 session id를 쿠키에 저장
    - 클라이언트가 다시 동일한 서버에 접속하면 요청과 함께 쿠키(session id가 저장된)를 서버에 전달
    - 쿠키는 요청 때마다 서버에 함께 전송 되므로 서버에서 session id를 확인해 알맞은 로직을 처리
- session id는 세션을 구별하기 위해 필요하며, 쿠키에는 session id만 저장

### 쿠키 수명
---
1. Session cookie
    - 현재 세션(current session)이 종료되면 삭제됨
    - 브라우저 종료와 함께 세션이 삭제됨
2. Persistent cookies
    - Expires 속성에 지정된 날짜 혹은 Max-Age속성에 지정된 기간이 지나면 삭제됨

### Session in Django
---
- Django는 **database-backed sessions 저장방식**을 기본 값으로 사용
    - session 정보는 Django DB의 **django_session 테이블**에 저장
- Django는 특정 session id를 포함하는 쿠키를 사용해서 각각의 브라우저와 사이트가 연결된 session을 알아냄
- Django는 우리가 session 메커니즘(복잡한 동작원리)에 대부분을 생각하지 않게끔 많은 도움을 줌

# Login
> 개요
- 로그인은 **Session을 Create**하는 과정

### AuthenticationForm
---
- 로그인을 위한 built-in form
    - 로그인 하고자 하는 사용자 정보를 입력 받음
    - 기본적으로 username과 password를 받아 데이터가 유효한지 검증
- request를 첫번째 인자로 취함

### 로그인 페이지 작성
> urls.py
```python
from django.urls import path
from . import views

app_name = 'accounts'
urlpatterns = [
    path('login/', views.login, name='login'),
]
```
> login.html
```html
{% extends 'base.html' %}
{% block content %}
    <h1>Login</h1>
    <form action="{% url 'accounts:login' %}" method="POST">
        {% csrf_token %}
        {{form.as_p}}
        <input type="submit" value="로그인">
    </form>
{% endblock content %}
```
> views.py
```python
from django.contrib.auth.forms import AuthenticationForm

def login(request):
    if request.method == 'POST':
        pass
    else:
        form = AuthenticationForm()
    context = {
        'form':form
    }
    return render(request, 'accounts/login.html', context)
```
### login()
---
- login(request, user, backend=None)
- 인증된 사용자를 로그인 시키는 로직으로 view함수에서 사용됨
- 현재 세션에 연결하려는 인증 된 사용자가 있는 경우 사용
- HttpRequest객체과 User객체가 필요
### 로그인 로직 작성
---
- 로그인 페이지 작성
- view함수와 login과의 충돌을 방지하기 위해 import한 login함수 이름을 auth_login으로 변경해서 사용
```python
from django.shortcuts import render, redirect
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth import login as auth_login

def login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)
        if form.is_valid():
            auth_login(request, form.get_user())
            return redirect('articles:index')
    else:
        form = AuthenticationForm()
    context = {
        'form':form
    }
    return render(request, 'accounts/login.html', context)
```
> get_user()
- AuthenticationForm의 인스턴스 메서드
- 유효성 검사를 통과했을 경우 로그인 한 사용자 객체를 반환
### 세션 데이터 확인
---
- 로그인 후 개발자 도구와 DB에서 django로부터 발급받은 세션 확인
(로그인은 관리자 계정을 만든 후 진행)
1. django_session테이블에서 확인
2. 브라우저에서 확인
    - 개발자 도구 - Application - Cookies
### 로그인 링크 작성
---
```html
<body>
  <a href="{% url 'accounts:login' %}">로그인</a>
  <hr>
  {% block content %}
  {% endblock content %}
</body>
```
# Authentication with User
> 개요
- 템플릿에서 인증 관련 데이터를 출력하는 방법
### 현재 로그인 한 유저 정보 출력
---
```html
<body>
  <h3>Hello, {{user}}</h3>
  <a href="{% url 'accounts:login' %}">로그인</a>
  <hr>
  {% block content %}
  {% endblock content %}
</body>
```
- settings.py의 context processors 설정 값으로 인해 context데이터 없이 user변수 사용 가능
### context processors
---
- 템플릿이 렌더링 될 때 호출 가능한 context 데이터 목록
- 작성된 context 데이터는 기본적으로 템플릿에서 사용 가능한 변수로 포함됨
- 즉, django에서 자주 사용하는 데이터 목록을 미리 템플릿에 로드해 둔 것
- user 변수를 담당하는 프로세서는 django.contrib.auth.context_processors.auth
- 이외에 더 많은 Built_in template context processors들은 공식문서를 참고
> django.contrib.auth.context_processors.auth
- 현재 로그인한 사용자를 나타내는 User 클래스의 인스턴스가 템플릿 변수 {{ user }}에 저장됨
    - user.email 등의 형태로 사용가능
# Logout
> 개요
- 로그아웃은 **Session을 Delete**하는 과정
### logout()
---
- logout(request)
- HttpRequest 객체를 인자로 받고 반환값이 없음
- 사용자가 로그인하지 않은 경우 오류를 발생시키지 않음
- 다음 2가지 일을 처리한다
    1. 현재 요청에 대한 session data를 DB에서 삭제
    2. 클라이언트의 쿠키에서도 sessionid를 삭제
    - 이는 다른 사람이 동일한 웹 브라우저를 사용하여 로그인하고, 이전 사용자의 세션 데이터에 액세스하는 것을 방지하기 위함
### 로그아웃 로직 작성
> urls.py
```python
from django.urls import path
from . import views

app_name = 'accounts'
urlpatterns = [
    path('login/', views.login, name='login'),
    path('logout/', views.logout, name='logout'),
]
```
> views.py
```python
from django.contrib.auth import logout as auth_logout

def logout(request):
    auth_logout(request)
    return redirect('articles:index')
```
> base.html
```html
{% comment %} 
<a href="{% url 'accounts:logout' %}">로그아웃</a> 
{% endcomment %}
  <form action="{% url 'accounts:logout' %}" method="POST">
    {% csrf_token %}
    <input type="submit" value="로그아웃">
  </form>
```