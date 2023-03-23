# Authentication with User
> 개요
- 회원 가입, 회원 탈퇴, 회원정보 수정, 비밀번호 변경
## 회원 가입
---
- 회원가입은 User를 Create하는 것이며 UserCreationForm built-in form을 사용
### UserCreationForm
---
- 주어진 username과 password로 권한이 없는 새 user를 생성하는 ModelForm
- 3개의 필드를 가짐
    1. username (from the user model)
    2. password1
    3. password2
### 회원 가입 페이지 작성
---
> urls.py
```python
app_name = 'accounts'
urlpatterns = [
    ...,
    path('signup/', views.signup, name='signup'),
]
```
> views.py
```python
from django.contrib.auth.forms import AuthenticationForm, UserCreationForm

def signup(request):
    if request.method == "POST":
        pass
    else:
        form = CustomUserCreationForm()
    context = {
        'form':form
    }
    return render(request, 'accounts/signup.html', context)
```
> signup.html
```html
{% extends 'base.html' %}

{% block content %}
  <h1>회원가입</h1>
  <form action="{% url 'accounts:signup' %}" method="POST">
    {% csrf_token %}
    {{form.as_p}}
    <input type="submit" value="회원가입">
  </form>
  <a href="{% url 'articles:index' %}">목록보기</a>
{% endblock content %}
```
### 회원가입 로직 작성
---
> views.py
```python
def signup(request):
    if request.method == "POST":
        form = UserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            return redirect('articles:index')
    else:
        form = UserCreationForm()
    context = {
        'form':form
    }
    return render(request, 'accounts/signup.html', context)
```
- 여기까지만 하면 회원가입 진행 시 오류 발생
    - 회원가입에 사용하는 UserCreationForm이 이전에 대체한 커스텀 유저 모델이 아닌 기존 유저 모델로 인해 작성된 클래스이기 때문에
### 커스텀 유저 모델을 사용하기위해 다시 작성하거나 확장해야 하는 forms
---
1. UserCreationForm
2. UserChangeForm
- 두 form 모두 class Meta: model = User 가 등록된 form이기 때문에 반드시 커스텀(확장)해야 함
> forms.py
```python
from django.contrib.auth import get_user_model
from django.contrib.auth.forms import UserCreationForm, UserChangeForm

class CustomUserCreationForm(UserCreationForm):
    class Meta(UserCreationForm.Meta):
        model = get_user_model()

class CustomUserChangeForm(UserChangeForm):
    class Meta(UserChangeForm.Meta):
        model = get_user_model()
```
> get_user_model()
- **현재 프로젝트에서 활성화된 사용자 모델(active user model)**을 반환
- 직접 참조하지 않는 이유
    - 기존 User 모델이 아닌 User 모델을 커스텀 한 상황에서는 커스텀 User 모델을 자동으로 반환해주기 때문
- Django는 User클래스를 직접 참조하는 대신 get_user_model()을 사용해 참조해야 한다고 강조하고 있음
### CustomUserCreationForm() 으로 대체
---
> views.py
```python
from .forms import CustomUserChangeForm, CustomUserCreationForm

def signup(request):
    if request.method == "POST":
        form = CustomUserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            auth_login(request, user) # 회원가입 후 바로 로그인
            return redirect('articles:index')
    else:
        form = CustomUserCreationForm()
    context = {
        'form':form
    }
    return render(request, 'accounts/signup.html', context)
```
### AbstractBaseUser의 모든 subclass와 호환되는 forms
---
1. AuthenticationForm
2. SetPasswordForm
3. PasswordChangeForm
4. AdminPasswordChangeForm
- 기존 User 모델을 참조하는 Form이 아니기 때문

## 회원 탈퇴
---
- DB에서 유저를 Delete하는 것과 같음
### 회원 탈퇴 로직 작성
---
> urls.py
```python
app_name = 'accounts'
urlpatterns = [
    ...,
    path('delete/', views.delete, name='delete'),
]
```
> views.py
```python
def delete(request):
    user = request.user
    user.delete()
    auth_logout(request) # 탈퇴 후 자동 로그아웃
    return redirect('articles:index')
```
> base.html
```html
...
<form action="{% url 'accounts:delete' %}" method="POST">
    {% csrf_token %}
    <input type="submit" value="회원탈퇴">
</form>
...
```

## 회원정보 수정
---
- **UserChangeForm** built-in form을 사용해 User를 Update하는 것
### UserChangeForm
---
- 사용자의 정보 및 권한을 변경하기 위해 admin 인터페이스에서 사용되는 ModelForm
- UserChangeForm 또한 ModelForm이기 때문에 instance 인자로 기존 user 데이터 정보를 받는 구조 또한 동일함
- 이미 이전에 CustomUserChangeForm으로 확장했기 때문에 CustomUserChangeForm을 사용
### 회원정보 수정 페이지 작성
---
> urls.py
```python
app_name = 'accounts'
urlpatterns = [
    ...,
    path('update/', views.update, name='update'),
]
```
> views.py
```python
def update(request):
    if request.method == "POST":
        pass
    else:
        form = CustomUserChangeForm(instance=request.user)
    context = {
        'form':form
    }
    return render(request, 'accounts/update.html', context)
```
> update.html
```html
{% extends 'base.html' %}

{% block content %}
  <h1>회원정보수정</h1>
  <form action="{% url 'accounts:update' %}" method="POST">
    {% csrf_token %}
    {{form.as_p}}
    <input type="submit" value="수정하기">
  </form>
  <a href="{% url 'articles:index' %}">목록보기</a>
{% endblock content %}
```
- 회원정보 수정 페이지 링크 작성
> base.html
```html
  <body>
    <div id="nav">
      <a href="{% url 'accounts:signup' %}">회원가입</a>
      <a href="{% url 'accounts:login' %}">로그인</a>
      <a href="{% url 'accounts:logout' %}">로그아웃</a>
      <a href="{% url 'accounts:update' %}">정보수정</a>
    ...
```
### UserChangeForm 사용 시 문제점
---
- 일반 사용자가 접근해서는 안 될 정보들(fields)까지 모두 수정이 가능해짐
    - admin 인터페이스에서 사용되는 ModelForm이기 때문
- 따라서 UserChangeForm을 상속받아 작성해 두었던 서브 클래스 CustomUserChangeForm에서 접근 가능한 필드를 조정해야 함
### CustomUserChangeForm fields 재정의
---
> forms.py
```python
class CustomUserChangeForm(UserChangeForm):
    class Meta(UserChangeForm.Meta):
        model = get_user_model()
        fields = ('username', 'email', 'first_name', 'last_name',)
```
### 회원정보 수정 로직 작성
---
> views.py
```python
def update(request):
    if request.method == "POST":
        form = CustomUserChangeForm(request.POST, instance=request.user)
        if form.is_valid():
            form.save()
            return redirect('articles:index')
    else:
        form = CustomUserChangeForm(instance=request.user)
    context = {
        'form':form
    }
    return render(request, 'accounts/update.html', context)
```
## 비밀번호 변경
---
### PasswordChangeForm
---
- 사용자가 비밀번호를 변경할 수 있도록 하는 Form
- 이전 비밀번호를 입력하여 비밀번호를 변경할 수 있도록 함
- 이전 비밀번호를 입력하지 않고 비밀번호를 설정할 수 있는 SetPasswordForm을 상속받는 서브 클래스
### 비밀번호 변경 페이지 작성
---
> urls.py
```python
# 비밀번호 페이지의 기본 경로가 password/
app_name = 'accounts'
urlpatterns = [
    ...,
    path('password/', views.change_password, name='change_password'),
]
```
> views.py
```python
from django.contrib.auth.forms import AuthenticationForm, PasswordChangeForm

def change_password(request):
    if request.method == 'POST':
        pass
    else:
        form = PasswordChangeForm(request.user)
    context = {'form':form}
    return render(request, 'accounts/change_password.html', context)
```
> change_password.html
```html
{% extends 'base.html' %}

{% block content %}
  <h1>비밀번호변경</h1>
  <form action="{% url 'accounts:change_password' %}" method="POST">
    {% csrf_token %}
    {{form.as_p}}
    <input type="submit" value="변경하기">
  </form>
  <a href="{% url 'articles:index' %}">목록보기</a>
{% endblock content %}
```
### 비밀번호 변경 로직 작성
---
> views.py
```python
def change_password(request):
    if request.method == 'POST':
        form = PasswordChangeForm(request.user, request.POST)
        if form.is_valid():
            form.save()
            return redirect('articles:index')
    else:
        form = PasswordChangeForm(request.user)
    context = {'form':form}
    return render(request, 'accounts/change_password.html', context)
```
- 단, 비밀번호 변경 후 로그인 상태가 지속되지 않음
    - 비밀번호가 변경되면 기존 세션과의 회원 인증 정보가 일치하지 않게 되어 로그인 상태가 유지되지 못함
    - 비밀번호는 변경되었으나 비밀번호가 변경 되면서 기존 세션과의 회원 인증 정보가 일치하지 않기 때문
### update_session_auth_hash()
---
- update_session_auth_hash(request, user)
- 현재 요청(current request)과 새 session data가 파생 될 업데이트 된 사용자 객체를 가져오고, session data를 적절하게 업데이트 해줌
- 암호가 변경되어도 로그아웃 되지 않도록 새로운 password의 session data로 session을 업데이트
- 작성
> views.py
```python
from django.contrib.auth import update_session_auth_hash

def change_password(request):
    if request.method == 'POST':
        form = PasswordChangeForm(request.user, request.POST)
        if form.is_valid():
            form.save()
            update_session_auth_hash(request, form.user)
            return redirect('articles:index')
    else:
        form = PasswordChangeForm(request.user)
    context = {'form':form}
    return render(request, 'accounts/change_password.html', context)
```

# View decorators
- 기존에 작성된 함수에 기능을 추가하고 싶을 때, 해당 함수를 수정하지 않고 기능을 추가해주는 함수
- Django는 다양한 HTTP 기능을 지원하기 위해 view 함수에 적용할 수 있는 여러 데코레이터를 제공
- 데코레이터 예시
```python
def hello(func):
    def wrapper():
        print('HIHI')
        func()
        print('HIHI')
    return wrapper

@hello
def bye():
    print('byebye')

bye()

###
HIHI
byebye
HIHI
```
## Allowed HTTP methods
---
> 개요
- django.views.decorators.http의 데코레이터를 사용하여 요청 메서드를 기반으로 저븐을 제한할 수 있음
- 일치하지 않는 메서드 요청이라면 405 Method Not Allowed를 반환
- 메서드 목록
    1. require_http_methods()
    2. require_POST()
    3. require_safe()
> 405 Method Not Allowed?
- 요청 방법이 서버에게 전달 되었으나 사용 불가능한 상태
### require_http_methods()
---
- View 함수가 특정한 요청 method만 허용하도록 하는 데코레이터
> views.py
```python
from django.views.decorators.http import require_http_methods

@require_http_methods(['GET', 'POST'])
def create(request):
    ...

@require_http_methods(['GET', 'POST'])
def update(request, pk):
    ...
```
### require_POST()
---
- view 함수가 POST 요청 method만 허용하도록 하는 데코레이터
> views.py
```python
from django.views.decorators.http import require_http_methods, require_POST

@require_POST
def delete(request, pk):
    ...
    return redirect('articles:index')
```

### require_safe()
---
- require_GET이 있지만 Django에서는 require_safe사용을 권장
> views.py
```python
from django.views.decorators.http import require_http_methods, require_POST, require_safe

@require_safe
def index(request):
    ...

@require_safe
def detail(request, pk):
    ...
```

# Limiting access to loged-in users
> 개요
- 로그인 사용자에 대한 접근 제한하기
## is_authenticated
---
- User model의 속성 중 하나
- 사용자가 인증 되었는지 여부를 알 수 있는 방법
- 모든 User 인스턴스에 대해 항상 True인 읽기 전용 속성
    - AnonymousUser에 대해서는 항상 False
- 일반적으로 request.user에서 이 속성을 사용 (request.user.is_authenticated)
- 권한과는 관련이 없으며, 사용자가 활성화 상태이거나 유효한 세션을 가지고 있는지도 확인하지 않음
### is_authenticated 적용
---
- 로그인과 비로그인 상태에서 출력되는 링크를 다르게 설정하기
> base.html
```html
{% if request.user.is_authenticated %}
  <h3>Hello, {{user}}</h3>
  <form action="{% url 'accounts:logout' %}" method="POST">
    {% csrf_token %}
    <input type="submit" value="Logout">
  </form>
  <a href="{% url 'accounts:update' %}">회원정보수정</a>
  <form action="{% url 'accounts:delete' %}" method="POST">
    {% csrf_token %}
    <input type="submit" value="회원탈퇴">
  </form>
{% else %}
  <a href="{% url 'accounts:login' %}">Login</a>
  <a href="{% url 'accounts:signup' %}">Signup</a>
{% endif %}
```
- 인증된 사용자만 게시글 작성 링크를 볼 수 있도록 처리하기
- 하지만 비 로그인 상태로도 URL을 직접 입력하면 게시글 작성 페이지로 갈 수 있음
> index.html
```html
{% extends 'base.html' %}

{% block content %}
  <h1>Articles</h1>
  {% if request.user.is_autenticated %}
    <a href="{% url 'articles:create' %}">CREATE</a>
  {% else %}
    <a href="{% url 'accounts:login' %}">새 글을 작성하려면 로그인하세요</a>
  {% endif %}
  ...
{% endblock content %}
```
- 인증된 사용자라면 로그인 로직을 수행할 수 없도록 처리
> views.py
```python
def login(request):
    if request.user.is_authenticated:
        return redirect('articles:index')
    ...
```