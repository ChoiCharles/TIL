# Django
> 서비스 개발에 필요한 기능들을 미리 구현해서 모아놓은 프레임워크(Framework)
- Python으로 작성된 프레임워크
    - Python이라는 언어의 강력함과 거대한 커뮤니티
- 수많은 여러 유용한 기능들
- 검증된 웹 프레임워크
    - 화해, Toss, 두나무, 당근 마켓, 요기요 등
    - 유명한 많은 서비스들이 사용한다 == 안정적으로 서비스를 할 수 있다는 검증

## 클라이언트와 서버
---
- 오늘날 우리가 사용하는 대부분의 웹 서비스는 클라이언트-서버 구조를 기반으로 동작
- 클라이언트와 서버 역기 하나의 컴퓨터
- 클라이언트
    - 웹 사용자의 인터넷에 연결된 장치(wi-fi에 연결된 컴퓨터 또는 모바일)
    - Chrome 또는 Firefox와 같은 웹 브라우저
    - 서비스를 요청하는 주체
- 서버
    - 웹 페이지, 사이트 또는 앱을 저장하는 컴퓨터
    - 클라이언트가 웹 페이지에 접근하려고 할 때 서버에서 클라이언트 컴퓨터로 웹 페이지 데이터를 응다해 아용자의 웹 브라우저에 표시됨
    - 요청에 대해 서비스를 응답하는 주체
- 상호작용 예시(google접속)
    1. 인터넷에 연결된 전세계 언딘가에 있는 구글 컴퓨터에게 Google홈페이지.html 파일을 달라고 요청하는 것
    2. 구글 컴퓨터는 울리의 요청을 한고 Google 홈페이지.html 파일을 인터넷을 통해서 우리 컴퓨터에게 응답
    3. 전달받은 Google 홈페이지.html 파일을 웹 브라우저가 우리가 볼 수 있도록 해석
- 자원을 요청하는 쪽을 클라이언트, 자원을 제고하는 쪽이 서버
> 정리
- 우리가 사용하는 웹은 클라이언트-서버 구조로 이루어져 있음
- Django는 서버를 구현하는 웹 프레임워크

## 무작정 따라하기
---
```git
$ pip install django==3.2.18
$ django-admin startproject firstpjt(프로젝트 이름)
$ python manage.py runserver
```
- 이후 터미널에'Starting development server at [http링크]'라고 뜨면 링크 복사 후 브라우저에서 접속
```git
$ python -m venv venv
$ source venv/Scrpts/activate     # 가상환경 활성화
$ deactivate                      # 가상환경 비활성화
$ pip freeze > requirements.txt   # 가상환경 패키지 목록 저장
$ pip instll -r requirements.txt  # 파일로부터 패키지 설치
```
## 프로젝트와 앱
---
### 프로젝트 구조
---
- \_\_init\_\_.py
    - Python에게 이 디렉토리를 하나의 Python 패키지로 다루도록 지시
    - 별도로 추가 코드를 작성하지 않음
- asgi.py
    - Asynchronous Server Gateway Interface
    - Django 애플리케이션이 비동기식 웹 서버와 연결 및 소통하는 것을 도움
    - 추후 배포 시에 사용하며 지금은 수정하지 않음
- settings.py
    - Django 프로젝트 설정을 관리
- urls.py
    - 사이트의 url과 적절한 views의 연결을 지정
- wsgi.py
    - Web Server Gateway Interface
    - Django 애플리케이션이 웹서버와 연결 및 소통하는 것을 도움
    - 추후 배포 시에 사용하며 지금은 수정하지 않음
- manage.py
    - Django 프로젝트와 다양한 방법으로 상호작용 하는 커맨드라인 유틸리티
```git
$ python manage.py <command> [options]
```
### Django Application
---
- 애플리케이션(앱) 생성
```git
$ python manage.py startapp 앱이름
```
- 앱이름은 '복수형'으로 작성하는 것을 권장
- 앱(App) == 하나의 큰 기능 단위
    - 정해진 규칙은 없으며 개발자가 판단해서 앱 생성, 여러 개의 앱이 아닌 단일 앱으로 개발해도 괜찮음
### 애플리케이션 구조
---
- admin.py
    - 관리자용 페이지를 설정 하는 곳
- apps.py
    - 앱의 정보가 작성된 곳
    - 별도로 추가 코드를 작성하지 않음
- models.py (중요)
    - 애플리케이션에서 사용하는 Model을 정의하는 곳
    - MTV 패턴의 M에 해당
- tests.py
    - 프로젝트의 테스트 코드를 작성하는 곳
- views.py (중요)
    - view 함수들이 정의 되는 곳
    - MTV 패턴의 V에 해당
### 애플리케이션 등록 (매우 중요)
---
- 앱을 사용하기 위해서는 반드시 INSTALLED_APPS 리스트에 반드시 추가해야 함
```python
# settings.py
INSTALLED_APPS = [
    'articles',  # 새로만든 앱의 이름
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]
```
### 프로젝트와 앱
---
- Project
    - 'collection of apps'
    - 프로젝트는 앱의 집합
    - 프로젝트에는 여러 앱이 포함될 수 있음
    - 앱은 여러 프로젝트에 있을 수 있음
- Application
    - 앱은 실제 요청을 처리하고 페이지를 보여주는 등의 역할을 담당
    - 앱은 하나의 역할 및 기능 단위로 작성하는 것을 권장

## 요청과 응답
---
```python
# urls.py
from django.contrib import admin
from django.urls import path
from articles import views  # 추가

urlpatterns = [
    path('admin/', admin.site.urls),
    path('articles/', views.index), # 추가
]
```
```python
# views.py
# 기본형태
from django.shortcuts import render

# Create your views here.
def index(request):
    pass

-------------------------------------------------------------

from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def index(request):
    return HttpResponse("<h1>hi</h1>")
```

# Django's Design Pattern
> Django의 디자인 패턴은 MTV패턴으로 MVC패턴을 기반으로 변형된 패턴이다
### MVC 소프트웨어 디자인 패턴
---
- MVC : Model - View - Controller
    - 데이터 및 논리 제어를 구현하는데 널리 사용되는 스프트웨어 디자인 패턴
- 하나의 큰 프로그램을 세가지 역할로 구분한 개발 방법론
1. Model : 데이터와 관련된 로직을 관리
2. View : 레이아웃과 화면을 처리
3. Comtroller : 명령을 model과 view부분으로 연결
> MVC패턴의 목적
- 관심사 분리
- 더 나은 업무의 분리와 향상된 관리를 제공
- 각 부분을 독립적으로 개발할 수 있어, 하나를 수정하고 싶을 때 모두 건들지 않아도 됨
    - 개발 효율성 및 유지보수가 쉬워짐
    - 다수의 멤버로 개발하기 용이
### MTV 디자인 패턴
---
- Model
    - MVC 패턴에서 Model의 역할에 해당
    - 데이터와 관련된 로직을 관리
    - 응용프로그램의 데이터 구조를 정의하고 데이터베이스의 기록을 관리
- Template
    - 레이아웃과 화면을 처리
    - 화면상의 사용자 인터페이스 구조와 레이아웃을 정의
    - MVC 패턴에서 View의 역할에 해당
- View
    - Model & Template과 관련한 로직을 처리해서 응답을 변환
    - 클라이언트의 요청에 대해 처리를 분기하는 역할
    - 동작 예
        - 데이터가 필요하다면 model에 접근해서 데이터를 가져오고 가져온 데이터를 template로 보내 화면을 구성하고 구성된 화면을 응답으로 만들어 클라이언트에게 반환
    - MVC 패턴에서 Controller의 역할에 해당
> 정리
- Django는 MTV 디자인 패턴을 가지고 있음
    - Model : 데이터 관련
    - Template : 화면 관련
    - View : Model & Template 중간 처리 및 응답 반환
# Django Template
- 데이터 표현을 제어하는 도구이자 표현에 관련된 로직
- Django Template을 이용한 HTML 정적 부분과 동적 컨텐츠 삽입
- Template System의 기본 목표를 숙지
- Django Template System
    - 데이터 표현을 제어하는 도구이자 표현에 관련된 로직을 담당

### Django Template Language (DTL)
---
- Django template에서 사용하는 built-in template system
- 조건, 반복, 변수 치환, 필터 등의 기능을 제공
    - Python처럼 일부 프로그래밍 구조(if, for 등)를 사용할 수 있지만 이것은 Python 코드로 실행되는 것이 아님
    - Django 템플릿 시스템은 단순히 Python이 HTML에 포함 된 것이 아니니 주의
- 프로그래밍적 로직이 아니라 프레젠테이션을 표현하기 위한 것임을 명심
### DTL Syntax
---
1. Variable
2. Filters
3. Tags
4. Comments
### Variable
---
```html
{{ variable }}
```
- 변수명은 영어, 숫자와 밑줄(_)의 조합으로 구성될 수 있으나 밑줄로는 시작 할 수 없음
    - 공백이나 구두점 문자 또한 사용할 수 없음
- dot(.)를 사용하여 변수 속성에 접근할 수 있음
- render()의 세번째 인자로 {'key':value}와 같이 딕셔너리 형태로 넘겨주며, 여기서 정의한 key에 해당하는 문자열이 template에서 사용 가능한 변수명이 됨
### Filters
---
```html
{{{ variable|filter}}}
```
- 표시할 변수를 수정할 때 사용
- 예시
    - name변수를 모두 소문자로 출력, age변수에 2를 더함
    ```html
    {{ name|lower }}
    {{ age|add:2}}
    ```
- 60개의 built-in template filters를 제공
- chained가 가능하며 일부 필터는 인자를 받기도 함
```html
{{ name|truncatewords:30 }}
```
### Tags
---
```html
{% tag %}
```
- 출력 텍스트를 만들거나, 반복 또는 논리를 수행하여 제어 흐름을 만드는 등 변수보다 복잡한 일들을 수행
- 일부 태그는 시작과 종료 태그가 필요
```html
{% if %}{% endif %}
```
- 약 24개의 built-in template tags를 제공
### Comments
---
```html
{# #}
```
- Django template에서 라인의 주석을 표현하기 위해 사용
- 아래처럼 유효하지 않은 템플릿 코드가 포함될 수 있음
```html
{# {% if %} text {% endif %} #}
```
- 한 줄 주석에만 사용할 수 있음 (줄바꿈 허용x)
- 여러줄 주석은 {% comment %}와 {% endcomment %} 사이에 입력
```html
{% comment %}
    여러 줄
    주석
{% endcomment %}
```

# Template inheritance
### 템플릿 상속
---
- 템플릿 상속은 기본적으로 코드의 재사용성에 초점을 맞춤
- 템플릿 상속을 사용하면 사이트의 모든 공통 요소를 포함하고, 하위 템플릿이 재정의(override) 할 수 있는 블록을 정의하는 기본 'skeleton'템플릿을 만들 수 있음

```html
{% extends '' %}
```
- 자식(하위)템플릿이 부모 템플릿을 확장한다는 것을 알림
- **반드시 템플릿 최상단에 작성 되어야 함 (즉, 2개 이상 사용할 수 없음)**

```html
{% block content %}{% endblock content %}
```
- 하위 템플릿에서 재지정(overridden)할 수 있는 블록을 정의
- 즉, 하위 템플릿이 채울 수 있는 공간
- 가독성을 높이기 위해 선택적으로 endblock 태그에 이름을 지정할 수 있음
```html
{% block content %}
{% endblock content %}

{% block content %}
{% endblock %}
```

# Variable routing
- URL주소를 변수로 사용하는 것을 의미
- URL의 일부를 변수로 지정하여 view함수의 인자로 넘길 숭 있음
- 즉, 변수 값에 따라 하나의 path()에 여러 페이지를 연결 시킬 수 있음
### Variable routing 작성
---
- 변수는 '<>'에 정의하며 view함수의 인자로 할당됨
- 기본 타입은 string이며 5가지 타입으로 명시할 수 있음
1. str
    - '/'를 제외하고 비어 있지 않은 모든 문자열
    - 작성하지 않을 경우 기본 값
2. int
    - 0 또는 양의 정수와 매치
3. slug
4. uuid
5. path
```python
urlpatterns = [
    path('hello/<str:name>/', views.hello)
]
```
### View 함수 작성
- variable routing으로 할당된 변수를 인자로 받고 템플릿 변수로 사용할 수 있음
```python
def hello(request, name):
    context = {
        'name' : name,
    }
    return render(request, 'hello.html', context)
```
```html
{% extends 'base.html' %}
{% block content %}
<h1>hi {{ name }}!</h1>
{% endblock %}
```
# App URL mapping
> 서비스를 개발하다 보면 url과 view는 많아지게 되고 이걸 모두 프로젝트의 urls.py에서 관리하면 가독성기 떨어지고 유지보수에 좋지 않다
- 하나의 여러 앱이 존재한다면, 각각의 앱 안에 urls.py를 만들고 프로젝트 urls.py에서 각 앱의 urls.py파일로 URL매핑을 위탁할 수 있음
- **각각의 app폴더 안에 urls.py를 작성**
```
mypjt
    articles
        __pyache__
        migrations
        __init__.py
        admin.py
        apps.py
        models.py
        tests.py
        **urls.py**
        views.py
    mypjt
        __pycache__
        __init__.py
        asgi.py
        settings.py
        urls.py
        wsgi.py
    pages
        __pyache__
        migrations
        __init__.py
        admin.py
        apps.py
        models.py
        tests.py
        **urls.py**
        views.py
```
```python
# mypjt urls.py
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('articles/', include('articles.urls')),
    path('pages/', include('pages.urls'))
]
```
```python
# articles urls.py
from django.urls import path
from . import views

urlpatterns = [
    path('index/', views.index)
]
```