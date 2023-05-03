# M:N (User - User)
- User 자기 자신과의 M:N 관계 설정을 통한 팔로우 기능 구현
## Profile 구현

```python
# accounts/urls.py

urlpatterns = [
    ...
    path('profile/<username>/', views.profile, name='profile'),
]

```
```python
# accounts/views.py

def profile(request, username):
    # person = get_user_model.objects.get(username=username)
    User = get_user_model()
    person = User.objects.get(username=username)
    context = {
        'person':person,
    }
    return render(request, 'accounts/profile.html', context)
```
```html
<!-- accounts/profile.html -->

{% extends "base.html" %}

{% block content %}
<h1>{{ person.username }}님의 프로필</h1>
<hr>
<h5>{{ person.username }}님이 작성한 글</h5>
{% for article in person.article_set.all %}
    <div class='card'>
        <div class='card-body'>
            <h5 class='card-title'>{{ article.title }}</h5>
            <p class='card-text'>{{ article.content }}</p>
            <a href="{% url 'articles:detail' article.pk %}" class='btn btn-primary'>자세히 보기</a>
        </div>
    </div>
{% endfor %}
<hr><br><br>
<h5>{{ person.username }}님이 좋아한 게시글</h5>
<hr>
{% for article in person.like_articles.all %}
    <div class='card'>
        <div class='card-body'>
            <h5 class='card-title'>{{ article.title }}</h5>
            <p class='card-text'>{{ article.content }}</p>
            <a href="{% url 'articles:detail' article.pk %}" class='btn btn-primary'>자세히 보기</a>
        </div>
    </div>
{% endfor %}
<hr><br><br>
<a href="{% url 'articles:index' %}" class='btn btn-success'>목록보기</a>
{% endblock content %}
```
- Profile 템플릿으로 이동할 수 있는 하이퍼 링크 작성
```html
<!-- base.html -->
...
안녕하세요, <a href="{% url 'accounts:profile' user.username %}">{{user}}</a> 님 !
...
```
```html
<!-- base.html -->
...
안녕하세요, {{user}} 님 !
<a href="{% url 'accounts:profile' user.username %}">내 프로필</a>
...
```
```html
<!-- articles/index.html -->
...
<p>
    - 작성자: <a href="{% url 'accounts:profile' article.user.username%}">{{article.user}}</a>
</p>
...
```
- Profile 템플릿으로 이동할 수 있는 하이퍼 링크 출력 확인
## Follow
### 모델 관계 설정
---
- ManyToManyField 작성 및 migration 진행
```python
# accounts/models.py

class User(abstractUser):
    followings = models.ManyToManyField('self', symmetrical=False, related_name='followers')
```
### Follow 구현
---
- url 및 view 함수 작성
```python
# accounts/urls.py

urlpatterns = [
    ...,
    path('<int:user_pk>/follow/', views.follow, name='follow'),
]
```
```python
# accounts/views.py

def follow(request, user_pk):
    person = get_user_model().objects.get(pk=user_pk)
    if person != request.user:
        if person.followers.filter(pk=request.user.pk).exists():
        # if request.user in person.followers.all():
            person.followers.remove(request.user)
        else:
            person.followers.add(request.user)
    return redirect('accounts:profile', person.username)
```
- 프로필 유저의 팔로잉, 팔로워 수 & 팔로우, 언팔로우 버튼 작성
```html
<!-- accounts/profile.html -->
<h1>{{ person.username }}님의 프로필</h1>
<div>
    팔로잉 : {{ person.followings.count }} | 팔로워 : {{ person.followers.count }}
</div>
<form action="{% url 'accounts:follow' person.pk %}" method="POST">
    {% csrf_token %}
    {% if request.user in person.followers.all %}
        <button type='submit' class='btn btn-danger'>언팔로우</button>
    {% else %}
        <button type='submit' class='btn btn-success'>팔로우</button>
    {% endif %}
</form>
```
- 데코레이터 및 is_authenticated 추가
```python
# accounts/views.py
from django.views.decorators.http import require_POST

@require_POST
def follow(request, user_pk):
    if request.user.is_authenticated:
        person = get_user_model().objects.get(pk=user_pk)
        if person != request.user:
            if person.followers.filter(pk=request.user.pk).exists():
                person.followers.remove(request.user)
            else:
                person.followers.add(request.user)
        return redirect('accounts:profile', person.username)
    return redirect('accounts:login')
```
## Fixtures
- Fixtures를 사용해 모델에 초기 데이터를 제공하는 방법
> 초기 데이터의 필요성
- 협업하는 A, B 유저가 있다고 생각해보자
    1. A가 먼저 프로젝트를 작업 후 github에 push한다
        - gitignore 설정으로 인해 DB는 업로드하지 않기 때문에 A가 개발하면서 사용한 데이터는 올라가지 않는다
    2. B가 github에서 A가 push한 프로젝트를 pull(혹은 clone)한다
        - 마찬가지로 프로젝트는 받았지만 A가 생성하고 조작한 데이터는 없는 빈 프로젝트를 받게 된다
- 이처럼 Django 프로젝트의 앱을 처음 설정할 때 동일하게 준비 된 데이터로 데이터베이스를 미리 채우는 것이 필요하다
- Django에서는 fixtures를 사용해 앱의 초기 데이터(initial data)를 제공할 수 있다
- 즉, migrations와 fixtures를 사용해 data와 구조를 공유하게 된다
## Providing data with fixtures

### fixtures
---
- Django가 데이터베이스로 가져오는 방법을 알고 있는 데이터 모음
    - Django가 직접 만들기 때문에 데이터베이스 구조에 맞추어 작성 되어있음
> fixture 생성 및 로드
- 생성 (데이터 추출)
    - dumpdata
- 로드 (데이터 입력)
    - loaddata
### dumpdata
---
- 응용 프로그램과 관련된 데이터베이스의 모든 데이터를 표준 출력으로 출력함
- 여러 모델을 하나의 json파일로 만들 수 있음
```git
$ python manage.py dumpdata app_name.ModelName app_name.ModelName ... > {filename}.json
```
- articles app의 article 모델에 대한 data를 json 형식으로 저장
```git
$ python manage.py dumpdata --indent 4 articles.article > articles.json
```
- manage.py와 동일한 위치에 data가 담긴 articles.json 파일이 생성됨
- dumpdata의 출력 결과물은 loaddata의 입력으로 사용됨

### loaddata
---
- fixtures의 내용을 검색하여 데이터베이스로 로드
```git
$ python manage.py loaddata data.json
```
- fixtures 기본 경로
    - app_name/fixtures/
    - Django는 설치된 모든 app의 디렉토리에서 fixtures 폴더 이후의 경로로 fixtures 파일을 찾음
- fixtures의 내용을 검색하여 데이터베이스로 로드
```
# 해당 위치로 fixture 파일 이동

articles/
    fixtures/
        articles.json
        users.json
        comments.json
```
- 기존 db삭제 후 migration진행
- fixtures load 하기
```git
$ python manage.py loaddata articles.json users.json comments.json
```
- load 후 데이터 확인
> loaddata 하는 순서
- loaddata를 한번에 실행하지 않고 하나씩 실행한다면 모델 관계에 따라 순서가 중요할 수 있음
    - comment는 article에 대한 key 및 user에 대한 key가 필요
    - article은 user에 대한 key가 필요
- 즉, 현재 모델 관계에서는 user -> article -> comment 순으로 data를 넣어야 오류가 발생하지 않음
> loaddata 시 encoding codec 관련 에러가 발생하는 경우
- 2가지 방법 중 택 1
1. dumpdata 시 추가 옵션 작성
```git
$ python -Xutf8 manage.py dumpdata ...
```
2. 메모장 활용
    1. 메모장으로 json파일 열기
    2. "다른 이름으로 저장"
    3. 인코딩을 UTF8로 선택 후 저장
# Improve Query
> 사전 준비
- 프로젝트 생성 (query_pjt)
- 앱 생성 (myapp)
- 모델 생성
```python
class PetSitter(models.Model):
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    age = models.IntegerField()

class Pet(models.Model):
    name = models.CharField(max_length=50)
    pet_sitter = models.ForeignKey(PetSitter, on_delete=models.CASCADE)
```
### 모든 Pet의 집사 이름을 출력
```python
def get_pet_data():
    pets = Pet.objects.all()
    for pet in pets:
        print(pet.name, pet.pet_sitter.first_name)
```
```shell
In [1]: from myapp.views import get_pet_data

In [2]: get_pet_data()
~~~~
~~~~
```
> ORM은 몇 번 데이터베이스를 조회할까
```python
from django.db import connection
from django.db import reset_queries

def get_pet_data():
    reset_queries()

    pets = Pet.objects.all()
    for pet in pets:
        print(pet.name, pet.pet_sitter.first_name)

    query_info = connection.queries
    for query in query_info:
        print(query['sql'])
```
```shell
In [1]: from myapp.views import get_pet_data

In [2]: get_pet_data()
~~~~
~~~~
SELECT ... [생략]
SELECT ... [생략]
SELECT ... [생략]
SELECT ... [생략]
```
- pet 테이블에서 다 가져오고 pet의 pet_sitter_id 마다 비교한다
    - N + 1 Problem

## Django ORM
- 장점
    - SQL을 몰라도 딘다
    - SQL을 사용하는 대신 객체 지향적으로 데이터를 조회할 수 있다
    - 재사용, 유지보수가 쉽다
    - DBMS에 대한 의존도가 떨어진다
- 단점
    - 복잡한 SQL문을 그대로 재현하기 어려움
    - 멋모르고 사용하면 이상한 쿼리 나감 (N + 1 Problem)이 대표적
### 특징
- Django ORM은 기본적으로 Lazy Loading 전략을 사용한다
    - == ORM을 작성하면 Database에 Query하는 것이 아니라, 미루고 미루다가 실제로 데이터를 사용할 때 Database에 Query를 날린다
    - == ORM 함수를 호출할 때가 아닌, Queryset이 실제로 평가될 때 DB를 호출한다
    - == Queryset이 실제로 모습을 드러내야 할 때 DB를 부른다 (print, slicing, len, list, iteration, ...)
- 똑같은 데이터를 사용한다면 캐싱을 내부적으로 해둔다
### Lazy Loading (지연 로딩)
- data = Model.objects.all()
    - 이때는 sql로만 가지고 있고 DB에 아무런 콜을 주지 않는다
- print(data), list(data), ...
    - 이때 DB에 콜을 날려서 데이터를 조회한다
- 왜?
    - 객체와 RDB를 연결하는 ORM입장에서는, 객체 코드로 다루는 모든 경우에 호출을 하는것은 매우 비용이 많이 드는 작업이므로, 실제로 해당 데이터가 필요한 시점에 데이터베이스를 호출한다
### Eager Loading (즉시 로딩)
- Lazy Loading : 지금 사용하지 않으면 가져오지 않는다
- Eager Loading : 지금 사용하지 않더라도 가져온다
    - 보통 여러 테이블의 데이터를 한 번에 가져올 때 사용
- Django ORM에서는 select_related(정참조 관계)와 prefetch_relatec(역참조 관계)로 사용
```python
def get_pet_data():
    reset_queries()

    pets = Pet.objects.all().select_related('pet_sitter')
    for pet in pets:
        print(pet.name, pet.pet_sitter.first_name)

    query_info = connection.queries
    for query in query_info:
        print(query['sql'])
```
> selet_related
- 1:1 또는 N:1 참조관계에서 사용
- SQL에서 INNER JOIN절을 활용
    - SQL의 INNER JOIN을 사용하여 참조하는 테이블의 일부를 가져오고, SELECT FROM을 통해 관련된 필드들을 가져옴
> prefetch_related
- M:N 또는 N:1 역참조 관계에서 사용
- SQL이 아닌 Python을 통한 JOIN이 진행됨

### Django ORM - Caching
- 특정 데이터를 불러온 후 재사용할 경우 ORM은 저장해둔 캐싱을 사용한다
- 불러온 데이터에 변화를 일으키는 쿼리가 아니라면 저장해둔 데이터를 사용한다

## Django ORM 특징
- 기본적으로 모든 ORM은 지연로딩이다
    - 실제로 필요할 때 데이터베이스에서 데이터를 가져온다
- ORM을 이용해서 가져온 데이터는 캐싱된다