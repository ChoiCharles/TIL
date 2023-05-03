# Admin site
- Django의 가장 강력한 기능 중 하나인 automatic admin interface
- 관리자 페이지
    - 사용자가 아닌 서버의 관리자가 활용하기 위한 페이지
    - 모델 class를 admin.py에 등록하고 관리
    - 레코드 생성 여부 확인에 매우 유용하며 직접 레코드를 삽입할 수도 있음
> admin 계정 생성
```git
$ python manage.py createsuperuser
```
- username과 password를 입력해 관리자 계정을 생성
    - email은 선택사항이기 때문에 입력하지 않고 넘어가도 됨
    - 비밀번호 생성시 보안상 터미널에 입력되지 않음
- localhost:8000/admin/으로 접속 후 로그인
- 로그인 후 관리자 페이지에서 데이터를 손쉽게 넣을 수 있음

# READ(index page)
```python
# articles/views.py
from .models import Article

def index(request):
    articles = Article.objects.all()
    context = {
        'articles' : articles,
    }
    return render(request, 'articles/index.html', context)
```
```html
<!--templates/articles/index.html-->
{% extends 'base.html' %}
{% block content %}
    <h1>Articles</h1>
    <hr>
    {% for article in articles %}
        <p>글 번호: {{ article.pk }}</p>
        <p>글 제목: {{ article.title }}</p>
        <p>글 내용: {{ article.content }}</p>
        <hr>
    {% endfor %}
{% endblock content %}
```
- html과 views.py를 연결해서 데이터를 웹에 연결할 수 있다
# READ(detail page)
- 개별 게시글 상세 페이지 제작
- 모든 게시글 마다 뷰 함수와 템플릿 파일을 만들 수는 없음
    - 글의 번호(pk)를 활용해서 하나의 뷰 함수와 템플릿 파일로 대응
- Variable Routing 활용
> urls
```python
# articles/urls.py
urlpatterns = [
    ...
    path('<int:pk>/', views.detail, name='detail')
]
```
- url로 특정 게시글을 조회할 수 있는 번호를 받음
> views
```python
# articles/views.py
def detail(request, pk):
    article = Article.objects.get(id=pk)
    context = {
        'article' : article,
    }
    return render(request, 'articles/detail.html', context)
```
- Article.objects.get(id=pk)에서 pk는 variable routing을 통해 받은 pk, id는 DB에 저장된 레코드의 id컬럼(id를 pk로 쓸 수 있음)
> templates
```html
<!-- templates/articles/detail.html -->
{% extends 'base.html' %}
{% block content %}
    <h2>DETAIL</h2>
    <h3>{{ article.pk }} 번째 글</h3>
    <hr>
    <p>제목: {{ article.title }}</p>
    <p>내용: {{ article.content }}</p>
    <p>작성 시간: {{ article.created_at }}</p>
    <p>수정 시간: {{ article.updated_at }}</p>
    <hr>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```
```html
<!--templates/articles/index.html-->
{% extends 'base.html' %}
{% block content %}
    <h1>Articles</h1>
    <hr>
    {% for article in articles %}
        <p>글 번호: {{ article.pk }}</p>
        <a hrdf="{% url 'articles:detail' article.pk %}">
            <p>글 제목: {{ article.title }}</p>
        </a>
        <p>글 내용: {{ article.content }}</p>
        <hr>
    {% endfor %}
{% endblock content %}
```
- 기존 index.html에서 제목을 클릭하면 detail.html로 이동할 수 있도록 수정
# CREATE
> 개요
- 사용자의 입력을 받을 페이지를 렌더링 하는 함수 1개
    - "new" view function
- 사용자가 입력한 데이터를 전송 받아 DB에 저장하는 함수 1개
    - "create" view function
- 2개의 새로운 view함수가 필요
### NEW
---
```python
# articles/urls.py
urlpatterns = [
    ...
    path('new/', views.new, name='new')
]

# articles/views.py
def new(request):
    return render(request, 'articles/new.html')
```
```html
<!--templates/articles/new.html-->
{% extends 'base.html' %}
{% block content %}
    <h1>NEW</h1>
    <form action='#' method='GET'>
        <label for="title">Title: </label>
        <input type="text" name="title"><br>
        <label for="content">Content: </label>
        <textarea name="content"></textarea><br>
        <input type="submit">
    </form>
    <hr>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```
### CREATE
---
```python
# articles/urls.py
urlpatterns = [
    ...
    path('create/', views.create, name='create')
]

# articles/views.py
def create(request):
    title = request.GET.get('title')
    content = request.GET.get('content')

    article = Article(title=title, content=content)
    article.save()

    return redirect('articles:detail', article.pk)
```
- create메서드를 이용하면 save까지 한번에 진행되지만, 추후에 유효성검사를 수행할 수 없으므로 create메서드는 잘 사용하지 않는다
```html
<!--templates/articles/new.html-->
{% extends 'base.html' %}
{% block content %}
    <h1>NEW</h1>
    <form action="{% url 'articles:detail' %}" method='GET'>
        <label for="title">Title: </label>
        <input type="text" name="title"><br>
        <label for="content">Content: </label>
        <textarea name="content"></textarea><br>
        <input type="submit">
    </form>
    <hr>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```
- new.html의 form작성 마무리

# HTTP Method
- HTTP : 네트워크 상에서 데이터를 주고 받기위한 약속
- HTTP Method : 데이터(리소스)에 어떤 요청(행동)을 원하는지 나타낸 것
    - GET
        - 특정 리소스를 가져오도록 요청할 때 사용
        - 반드시 데이터를 가져올 때만 사용해야 함
        - DB에 변화를 주지 않음
        - CRUD에서 R을 담당
    - POST
        - 서버로 데이터를 전송할 때 사용
        - 서버에 변경사항을 만듦
        - 리소스를 생성/변경하기 위해 데이터를 HTTP body에 담아 전송
        - GET의 쿼리 스트링 파라미터와 다르게 URL로 데이터를 보내지 않음
        - CRUD에서 C/U/D를 담당
### POST method 적용
---
```html
<!--templates/articles/new.html-->
{% extends 'base.html' %}
{% block content %}
    <h1>NEW</h1>
    <form action="{% url 'articles:detail' %}" method='POST'>
        ...
    </form>
    <hr>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```
- form의 method를 GET에서 POST로 변경
```python
# articles/views.py
def create(request):
    title = request.POST.get('title')
    content = request.POST.get('content')
    ...
```
- view함수 역시 POST로 수정
- **GET에서 POST로 바꾸기만 하면 Forbidden오류 발생**
> 403 Forbidden
- 서버에 요청이 전달되었지만, 권한 때문에 거절되었다는 의미
- 서버에 요청은 도달했으나 서버가 접근을 거부할 때 반환
- 게시글을 작성할 권한이 없다. 즉, 작성자가 누군지 모르니 함부로 작성할 수 없다는 의미
- DB를 조작하는 것은 단순 조회와 달리 최소한의 신원 확인이 필요하기 때문
> CSRF
- Cross-Site-Request-Forgery
- 사이트 간 요청 위조
- 사용자가 자신의 의지와 무관하게 공격자가 의도한 행동을 하여 특정 웹 페이지를 보안에 취약하게 하거나 수정, 삭제 등의 작업을 하게 만드는 공격 방법
> CSRF 공격 방어
- "Security Token 사용 방식 (CSRF Token)"
    - 사용자의 데이터에 임의의 난수 값(Token)을 부여해 매 요청마다 해당 난수 값을 포함시켜 전송 시키도록 함
    - 이후 서버에서 요청을 받을 때마다 전달된 token값이 유효한지 검증
    - 일반적으로 데이터 변경이 가능한 POST, PATCH, DELETE Method 등에 적용
    - Django는 DTL에서 csrf_token 템플릿 태그를 제공
> csrf_token 템플릿 태그
```html
{% csrf_token %}
```
- 해당 태그가 없다면 Django서버는 요청에 대해 403 forbidden으로 응답
- 탬플릿에서 내부 URL로 향하는 Post form을 사용하는 경우에 사용
    - 외부 URL로 향하는 POST form에 대해서는 CSRF 토큰이 유출되어 취약성을 유발할 수 있기 때문에 사용해서는 안됨
```html
<!--templates/articles/new.html-->
{% extends 'base.html' %}
{% block content %}
    <h1>NEW</h1>
    <form action="{% url 'articles:detail' %}" method='POST'>
        {% csrf_token %}
        ...
    </form>
    <hr>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```
# DELETE
> urls
- 삭제하고자 하는 특정 글을 조회 후 삭제해야 함
```python
urlpatterns = [
    path('<int:pk>/delete/', views.delete, name='delete)
]
```
> views
```python
# articles/views.py
def delete(request, pk):
    article = Article.objects.get(pk=pk)
    article.delete()
    return redirect('articles:index')
```
> templates
- Detail페이지에 작성하며 DB에 영향을 미치기 때문에 POST method를 사용
```html
<!-- articles/detail.html -->
{% extents 'base.html' %}
{% block content %}
    ...
    <form action="{% url 'articles:delete' article.pk %}" method="POST">
        {% csrf_token %}
        <input type="submit" value="DELETE">
    </form>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```
# UPDATE
> 개요
- 사용자의 입력을 받을 페이지를 덴더링 하는 함수 1개
    - "edit" view function
- 사용자가 입력한 데이터를 전송 받아 DB에 저장하는 함수 1개
    - "update" view function
- create와 마찬가지로 2개의 view함수가 필요
### Edit
---
> urls

```python
urlpatterns = [
    path('<int:pk>/edit/', views.edit, name='edit')
]
```
> views
```python
def edit(request, pk):
    article = Article.objects.get(pk=pk)
    context = {
        'article':article,
    }
    return render(request, 'articles/edit.html', context)
```
> templates
```html
<!--templates/articles/edit.html-->
{% extends 'base.html' %}
{% block content %}
    <h1>NEW</h1>
    <form action="#" method='POST'>
        {% csrt_token %}
        <label for="title">Title: </label>
        <input type="text" name="title" value="{{ article.title }}"><br>
        <label for="content">Content: </label>
        <textarea name="content">{{ article.content }}</textarea><br>
        <input type="submit">
    </form>
    <hr>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```
```html
<!-- templates/articles/detail.html -->
{% extends 'base.html' %}
{% block content %}
    <h2>DETAIL</h2>
    <h3>{{ article.pk }} 번째 글</h3>
    <hr>
    <p>제목: {{ article.title }}</p>
    <p>내용: {{ article.content }}</p>
    <p>작성 시간: {{ article.created_at }}</p>
    <p>수정 시간: {{ article.updated_at }}</p>
    <hr>
    <a href="{% url 'articles:edit' article.pk%}">EDIT</a><br>
    <form action="{% url 'articles:delete' article.pk %}" method="POST">
        {% csrf_token %}
        <input type="submit" value="DELETE">
    </form>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```
### Update
---
> urls
```python
urlpatterns = [
    path('<int:pk>/update/', views.update, name='update'),
]
```
> views
```python
def update(request, pk):
    article = Article.objects.get(pk=pk)
    article.title = request.POST.get('title')
    article.content = request.POST.get('content')
    article.save()
    return redirect('articles:detail', article.pk)
```
> templates
```html
<!--templates/articles/edit.html-->
{% extends 'base.html' %}
{% block content %}
    <h1>NEW</h1>
    <form action="{% url 'articles:update' article.pk%}" method='POST'>
        {% csrt_token %}
        ...
    </form>
    <hr>
    <a href="{% url 'articles:index' %}">[back]</a>
{% endblock content %}
```