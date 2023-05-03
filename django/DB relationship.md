# A many-to-one relationship
### RDB에서의 관계
---
1. 1:1
    - One-to-one relationships
    - 한 테이블의 레코드 하나가 다른 테이블의 레코드 단 한 개와 관련된 경우
2. N:1
    - Many-to-one relationships
    - 한 테이블의 0개 이상의 레코드가 다른 테이블의 레코드 한 개와 관련된 경우
    - 기준 테이블에 따라 1:N 이라고도 함
    - 예시
        - 여러 개의 주문 입장에서 각각 어떤 주문에 속해 있는지 표현해야 하므로 고객 테이블의 PK를 주문 테이블에 FK로 집어 넣어 관계를 표현
        - 고객(1)은 여러 주문(N)을 진행할 수 있음
3. M:N
    - Many-to-many relationships
    - 한 테이블의 0개 이상의 레코드가 다른 테이블의 0개 이상의 레코드와 관련된 경우
    - 양쪽 모두에서 N:1 관계를 가짐
### Foreign Key
---
> 개념
- 외래 키(외부 키)
- 관계형 데이터베이스에서 다른 테이블의 행을 식별할 수 있는 키
- 참조되는 테이블의 기본 키(Primary Key)를 가리킴
- 참조하는 테이블의 행 1개의 값은, 참조되는 측 테이블의 행 값에 대응됨
    - 이 때문에 참조하는 테이블의 행에는, 참조되는 테이블에 나타나지 않는 값을 포함할 수 없음
- 참조하는 테이블 행 여러 개가, 참조되는 테이블의 동일한 행을 참조할 수 있음
> 특징
- 키를 사용하여 부모 테이블의 유일한 값을 참조(참조 무결성)
- 외래 키의 값이 반드시 부모 테이블의 기본 키일 필요는 없지만 유일한 값이어야 함
> 참조 무결성
- 데이터베이스 관계 모델이서 관련된 2개의 테이블 간의 일관성을 말함
- 외래 키가 선언된 테이블의 외래 키 속성(열)의 값은 그 테이블의 부모가 되는 테이블의 기본 키 값으로 존재해야 함
# N:1 (Comment - Article)
## 모델 관계 설정
---
- 게시판의 게시글과 N:1 관계를 나타낼 수 있는 댓글 구현
- N:1 관계에서 댓글을 담당할 Comment 모델은 N, Article 모델은 1이 될 것
## Django Relationship fields
---
1. OneToOneField()
    - A one-to-one relationship
2. ForeignKey()
    - A many-to-one relationship
3. ManyToManyField()
    - A many-to-many relationship
> ForeignKey(to, on_delete, **options)
- A Many-to-one relationship을 담당하는 Django의 모델 필드 클래스
- Django 모델에서 관계형 데이터베이스의 외래 키 속성을 담당
- 2개의 필수 위치 인자가 필요
    1. 참조하는 model class
    2. on_delete옵션
## Comments Model
---
```python
# articles/models.py

class Comment(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
    content = models.CharField(max_length=200)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    
    def __str__(self):
        return self.content
```
- 외래 키 필드는 ForeignKey클래스를 작성하는 위치와 관계없이 필드의 마지막에 작성됨
- ForeignKey() 클래스의 인스턴스 이름은 참조하는 모델 클래스 이름의 단수형(소문자)으로 작성하는 것을 권장
> on_delete
- 외래 키가 참조하는 객체가 사라졌을 때, 외래 키를 가진 객체를 어떻게 처리할 지를 정의
- 데이터 무결성을 위해서 매우 중요한 설정
- on_delete 옵션 값
    - CASCADE: 부모 객체(참조 된 객체)가 삭제 됐을 때 이를 참조하는 객체도 삭제
    - PROTECT, SET_NULL, SET_DEFAULT ... 등 여러 옵션 값들이 존재

### Shell로 댓글 만들기
---
- shell_plus 실행
```git
$ python manage.p shell_plus
```
- 댓글 생성
```python
# Comment 클래스의 인스턴스 comment 생성
comment = Comment()

# 인스턴스 변수 저장
comment.content = 'first comment'

# DB에 댓글 저장
comment.save()

# 에러
... NOT NULL constraint failed ...
# articles_comment 테이블의 ForeignKeyField, article_id 값이 저장시 누락되었기 때문

# 게시글 생성 및 확인
article = Article.objects.create(title='title', content='content')
article
=> <Article: title>

# 외래 키 데이터 입력
comment.article = article

# DB에 저장
comment.save()
comment
=> <Comment: first comment>
```
- 댓글 속성 값 확인
```python
comment.pk
=> 1

comment.content
=> 'first comment'

# 클래스 변수명인 article로 조회 시 해당 참조하는 게시물 객체를 조회할 수 있음
comment.article
=> <Article: title>

# article_pk는 존재하지 않는 필드이기 때문에 사용 불가
comment.article_id
=> 1
```
- comment 인스턴스를 통한 article 값 접근
```python
# 1번 댓글이 작성된 게시물의 pk조회
comment.article.pk
=> 1

# 1번 댓글이 작성된 게시물의 content조회
comment.article.content
=> 'content'
```
- 두번째 댓글 작성
```python
comment = Comment(content='second comment', article=article)
comment.save()

comment.pk
=> 2

comment
=> <Comment: second comment>

comment.article_id
=> 1
```
- .sqlite에서 작성된 댓글 확인
## 관계 모델 참조
---
> Related manager
- Related manager는 N:1 혹은 M:N 관계에서 사용 가능한 문맥(context)
- Django는 모델 간 N:1 혹은 M:N 관계가 설정되면 역참조할 때 사용할 수 있는 manager를 생성
    - 이전에 모델 생성 시 objects라는 매니저를 통해 queryset api를 사용했던 것처럼 related manager를 통해 queryset api를 사용할 수 있게 됨
### 역참조
---
- 나를 참조하는 테이블(나를 외래 키로 지정한)을 참조하는 것
- 즉, 본인을 외래 키로 참조 중인 다른 테이블에 접근하는 것
- N:1관계에서는 1이 N을 참조하는 상황
    - 외래 키를 가지지 않은 1이 외래 키를 가진 N을 참조
### article.comment_set.method()
---
- Article 모델이 Comment 모델을 참조(역참조)할 때 사용하는 매니저
- article.comment 형식으로는 댓글 객체를 참조할 수 없음
    - 실제로 Article클래스에는 Comment와의 어떠한 관계도 작성되어 있지 않음
- 대신 Django가 역참조 할 수 있는 comment_set manager를 자동으로 생성해 article.comment_set 형태로 댓글 객체를 참조할 수 있음
- 반면 참조 상황(Comment -> Article)에서는 실제 ForeignKey 클래스로 작성한 인스턴스가 Comment클래스의 클래스 변수이기 때문에 comment.article형태로 작성 가능
### Related manager 연습
---
- shell_plus 실행
```git
$ python manage.p shell_plus
```
- 1번 게시글 조회
```python
article = Article.objects.get(pk=1)
```
- dir()함수를 사용해 클래스 객체가 사용할 수 있는 메서드 확인
```python
dir(article)
[
    ...
    'comment_set',
    'content',
    'created_at',
    ...
]
```
- 1번 게시글에 작성된 모든 댓글 조회(역참조)
```python
article.comment_set.all()
=> <QuerySet [<Comment: first comment>, <Comment: second comment>]>
```
- 1번 게시글에 작성된 모든 댓글 출력
```python
comments = article.comment_set.all()
for comment in comments:
    print(comment.content)
```
> ForeignKey arguments - related_name
```python
# articles/models.py

class Comment(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE, related_name='comments')
    ...
```
- ForeignKey 클래스의 선택 옵션
- 역참조 시 사용하는 매니저 이름(model_set manager)을 변경할 수 있음
- 작성 후, migration 과정 필요
- 선택 옵션이지만 상황에 따라 반드시 작성해야 하는 경우가 생기기도 함
- 작성 후 다시 원래 코드로 복구
    - 기존 article.comment_set은 더 이상 사용 불가, article.comments로 대체
### admin site 등록
---
- 새로 작성한 Comment 모델을 admin site에 등록하기
```python
# articles/admin.py
from .models import Article, Comment

admin.site.register(Article)
admin.site.register(Comment)
```
## Comment 구현
### CREATE
---
- 사용자로부터 댓글 데이터를 입력 받기 위한 CommentForm 작성
```python
# articles/forms.py
from .models import Article, Comment

class CommentForm(forms.ModelForm):
    class Meta:
        model = Comment
        fields = '__all__'
```
- detail 페이지에서 CommentForm 출력(view함수)
```python
# articles/views.py
from .forms import ArticleForm, CommentForm

def detail(request, pk):
    article = Article.objects.get(pk=pk)
    comment_form = CommentForm()
    context = {
        'article': article,
        'comment_form': comment_form,
    }
    return render(request, 'articles/detail.html', context)
```
- detail 페이지에서 CommentForm 출력(템플릿)
```html
<!-- articles/detail.html -->
{% extends 'base.html' %}
{% block content %}
    ...
    <form action="#" method="POST">
      {% csrf_token %}
      {{ comment_form}}
      <input type="submit" value="작성">
    </form>
{% endblock content %}
```
- detail페이지를 들어가 보면 댓글 작성시 직접 게시글의 번호를 선택 해야함
- 실제로는 해당 게시글에 댓글을 작성하면 자연스럽게 그 게시글에 댓글이 작성되어야 함
- 이렇게 출력되는 이유는 Comment 클래스의 외래 키 필드 article또한 데이터 입력이 필요하기 때문에 출력 되고 있는 것
- 하지만, 외래 키 필드는 **사용자의 입력으로 받는 것이 아니라 view 함수 내에서 받아 별도로 처리되어 저장**되어야 함
- 외래 키 필드를 출력에서 제외 후 확인
```python
# articles/forms.py
from .models import Article, Comment

class CommentForm(forms.ModelForm):
    class Meta:
        model = Comment
        exclude = ('article,')
```
- 제외된 외래 키 데이터는 url.py를 통해 받을 수 있음
```python
# articles/urls.py
urlpatterns = [
    ...,
    path('<int:pk>/comments/', views.comments_create, name='comments_create'),
]
```
```html
<!-- articles/detail.html -->
<form action="{% url 'articles:comments_create' article.pk %}" method="POST">
    {% csrf_token %}
    {{ comment_form}}
    <input type="submit" value="작성">
</form>
```
```python
# articles/views.py
def comments_create(request, pk):
    article = Article.objects.get(pk=pk)
    comment_form = CommentForm(request.POST)
    if comment_form.is_valid():
        comment.save()
    return redirect('articles:detail', article.pk)
```
- 작성을 마치고 보면 article 객체 저장이 이루어질 타이밍이 보이지 않음
- 그래서 save()메서드는 데이터베이스에 저장하기 전에 객체에 대한 추가적인 작업을 진행할 수 있도록 인스턴스만을 반환해주는 옵션 값을 제공
> The save() method
- save(commit=False)
    - "Create, but don't save the new instance"
    - 아직 데이터베이스에 저장되지 않은 인스턴스를 반환
    - 저장하기 전에 객체에 대한 사용자 지정 처리르 수행할 때 유용하게 사용
- save메서드와 commit옵션을 사용해 DB에 저장되기 전 article객체 저장하기
```python
# articles/views.py

def comments_create(request, pk):
    article = Article.objects.get(pk=pk)
    comment_form = CommentForm(request.POST)
    if comment_form.is_valid():
        comment = comment_form.save(commit=False)
        comment.article = article
        comment.save()
    return redirect('articles:detail', article.pk)
```
- 댓글 작성 후 테이블 확인
### READ
---
- 작성한 댓글 목록 출력
- 특정 article에 있는 모든 댓글을 가져온 후 context에 추가
```python
# articles/views.py
from .models import Article, Comment

def detail(request, pk):
    article = Article.objects.get(pk=pk)
    comment_form = CommentForm()
    comments = article.comment_set.all()
    context = {
        'article': article,
        'comment_form': comment_form,
        'comments':comments,
    }
    return render(request, 'articles/detail.html', context)
```
- detail 템플릿에서 댓글 목록 출력
```html
<!-- articles/detail.html -->
...
<ul>
    {% for comments in comments %}
    <li>
        {{ comments.content }}
    </li>
    {% endfor %}
</ul>
...
```
- detail 템플릿에서 댓글 목록 출력 확인
### DELETE
---
- 댓글 삭제 구현(url, view)
```python
# articles/urls.py

urlpatterns = [
    ...,
    path('<int:article_pk>/comments/<int:comment_pk>/delete/', views.comments_delete, name='comments_delete'),
]
```
```python
# articles/views.py

def comments_delete(request, article_pk, comment_pk):
    comment = Comment.objects.get(pk=comment_pk)
    comment.delete()
    return redirect('articles:detail', article_pk)
```
- 댓글 삭제 버튼을 각 댓글 옆에 출력
```html
<!-- articles/detail.html -->
...
<ul>
    {% for comments in comments %}
    <li>
        {{ comments.content }}
        <form action="{% url 'articles:comments_delete' article.pk comments.pk %}" method="POST">
        {% csrf_token %}
        <input type="submit" value="삭제">
        </form>
    </li>
    {% endfor %}
</ul>
...
```
- 댓글 삭제 버튼 출력 확인 및 삭제 시도
- 댓글 수정은 JavaScript의 영역

## Comment 추가 사항
### 댓글 개수 출력
---
1. DTL filter - **length**사용
```html
{{ comments|length }}
{{ article.comment_set.all|length }}
```
2. Queryset API - **count()**사용
```html
{{ comments.count }}
{{ article.comment_set.count }}
```
- detail 템플릿에 작성
```html
<!-- articles/datail.html -->

<h4>댓글 목록</h4>
{% if comments %}
    <p><b>{{ comments|length }}개의 댓글이 있습니다.</b></p>
{% endif %}
```
- 작성 후 출력 확인
### 댓글이 없는 경우 대체 컨텐츠 출력
---
- DTL for empty 활용
```html
<!-- articles/detail.html -->
...
<ul>
    {% for comments in comments %}
    <li>
        {{ comments.content }}
        <form action="{% url 'articles:comments_delete' article.pk comments.pk %}" method="POST">
        {% csrf_token %}
        <input type="submit" value="삭제">
        </form>
    </li>
    {% empty %}
        <p>댓글이 없습니다.</p>
    {% endfor %}
</ul>
...
```
# N:1 (Article - User)
## Referencing the User model
### Django에서 User모델을 참조하는 방법
---
1. settings.AUTH_USER_MODEL
    - 반환 값: 'accounts.User'(문자열)
    - User 모델에 대한 외래 키 또는 M:N관계를 정의 할 때 사용
    - **models.py의 모델 필드에서 User모델을 참조할 때 사용**
2. get_user_model()
    - 반환 값: User Object(객체)
    - 현재 활성화(active)된 User모델을 반환
    - 커스터마이징한 User모델이 있을 경우는 Custom User모델, 그렇지 않으면 User를 반환
    - **models.py가 아닌 다른 모든 곳에서 유저 모델을 참조할 때 사용**
## 모델 관계 설정
### Article과 User간 모델 관계 설정
---
- Article 모델에 User 모델을 참조하는 외래 키 작성
```python
# articles/models.py
from django.conf import settings

class Article(models.Model):
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
```
### Migration 진행
---
- 기존에 존재하던 테이블에 새로운 컬럼이 추가되어야 하는 상황이기 때문에 migrations파일이 곧바로 만들어지지 않고 일련의 과정이 필요
```git
$ python manage.py makemigrations
```
```git
You are trying to add a non-nullable field 'user' to article without a default; we can't do 
that (the database needs something to populate existing rows).
Please select a fix:
 1) Provide a one-off default now (will be set on all existing rows with a null value for this column)
 2) Quit, and let me add a default in models.py
Select an option:
```
- 첫번째 화면
    - 기본적으로 모든 컬럼은 NOT NULL제약조건이 있기 때문에 데이터가 없이는 새로 추가되는 외래 키 필드 user_id가 생성되지 않음
    - 그래서 기본값을 어떻게 작성할 것인지 선택해야 함
    - 1을 입력하고 Enter진행 (다음 화면에서 직접 기본 값 입력)
```git
Please enter the default value now, as valid Python
The datetime and django.utils.timezone modules are available, so you can do e.g. timezone.now
Type 'exit' to exit this prompt
```
- 두번째 화면
    - article의 user_id에 어떤 데이터를 넣을 것인지 직접 입력해야 함
    - 마찬가지로 1을 입력하고 Enter 진행
    - 그러면 기존에 작성된 게시글이 있다면 모두 1번 회원이 작성한 것으로 처리됨
- migrations 파일 생성 후 migrate 진행
```git
$ python manage.py migrate
```
- article 테이블 스키마 변경 및 확인
### Django에서 User모델을 참조하는 방법 정리
---
- 문자열과 객체를 반환하는 특징과 Django의 내부적인 실행 원리에 관련된 것이므로 이렇게만 외운다
- User모델을 참조할 때
    - models.py에서는 **settings.AUTH_USER_MODEL**
    - 다른 모든 곳에서는 **get_user_model()**

## CRUD
### CREATE
---
- ArticleForm 출력 확인 시 create템플릿에서 불필요한 필드가 출력됨
- 이전에 CommentForm에서 외래 키 필드 article이 출력되는 상황과 동일한 상황
- user필드에 작성해야 하는 user객체는 view함수의 request객체를 활용해야 함
- ArticleForm의 출력 필드 수정
```python
# articles/forms.py
class ArticlesForm(forms.ModelForm):
    class Meta:
        model = Article
        fields = ('title', 'content',)
```
- 수정 확인 후 게시글 작성
- 게시글 작성 시 NOT NULL constraint failed: articles_article.user_id 에러 발생
- 게시글 작성 시 외래 키에 저장되어야 할 작성자 정보가 누락 되었기 때문
- 게시글 작성 시 작성자 정보가 함께 저장될 수 있도록 save의 commit옵션을 활용
```python
# articles/views.py

@login_required
@require_http_methods(['GET', 'POST'])
def create(request):
    if request.mothod == 'POST':
        form = ArticleForm(request.POST)
        if form.is_valid():
            article = form.save(commit=False)
            article.user = request.user
            article.save()
            return redirect('articles:detail', article.pk)
```
- 수정 후 게시글이 잘 작성 되는지 확인
### DELETE
---
- 이제 게시글에는 작성자 정보가 함께 들어있기 때문에 현재 삭제를 요청하려는 사람과 게시글을 작성한 사람을 비교하여 본인의 게시글만 삭제 할 수 있도록 함
```python
# articles/views.py

@require_POST
def delete(request, pk):
    article = Article.objects.get(pk=pk)
    if request.user.is_authenticated:
        if request.user == article.user:
            article.delete()
            return redirect('articles:index')
    return redirect('articles:detail', article.pk)
```
### UPDATE
---
- 수정도 마찬가지로 수정을 요청하려는 사람과 게시글을 작성한 사람을 비교하여 본인의 게시글만 수정할 수 있도록 함
```python
# articles/views.py

@login_required
@require_http_methods(['GET', 'POST'])
def update(request, pk):
    article = Article.objects.get(pk=pk)
    if request.user == article.user:
        if request.method == 'POST':
            form = Article.form(request.POST, instance=article)
            if form.is_valid():
                form.save()
                return redirect('articles:detail', article.pk)
        else:
            form = ArticleForm(instance=article)
    else:
        return redirect('articles:index')
    ...
```
- 추가로 해당 게시글의 작성자가 아니라면, 수정/삭제 버튼을 출력되지 않도록 함
```html
<!-- articles/detail.html -->
...
{% if request.user == article.user %}
<a href="{% url 'articles:update' article.pk %}">수정하기</a>
<form action="{% url 'articles:delete' article.pk %}" id="delete-form">
    {% csrf_token %}
    <input type="submit" value="삭제하기" id="delete-btn" />
</form>
{% endif %}
...
```
- 다른 계정으로 접속하여 detail템플릿에서 다른 회원이 작성한 게시글을 확인
### READ
---
- index템플릿과 detail템플릿에서 각 게시글의 작성자 출력
```html
<!-- articles/index.html -->
{% for article in articles %}
    <p>
      [{{article.id}}] <a href="{% url 'articles:detail' article.pk %}" id="article-title">{{article.title}}</a>
      <p><b>작성자: {{ article.user }}</b></p>
    </p>
    <hr />
{% endfor %} 
```
```html
<!-- articles/detail.html -->
...
    <p><b>작성자: {{ article.user }}</b></p>
    <p>글 제목 : {{article.title}}</p>
    <p>글 내용 : {{article.content}}</p>
    <p>생성시각 : {{article.created_at}}</p>
    <p>수정시각 : {{article.updated_at}}</p>
...
```
- 출력확인

# N:1 (Comment - User)
- Comment 모델과 User 모델 간 관계 설정
- 0개 이상의 댓글은 1개의 회원에 의해 작성 될 수 있음
## 모델 관계 설정
- Comment 모델에 User 모델을 참조하는 외래 키 작성
```python
# articles/models.py

class Comment(models.Model):
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    ...
```
- detail 템플릿에서 각 게시글의 작성자 출력
```html
...
{{comment.user}}
...
```
## 인증된 사용자에 대한 접근 제한
- 인증된 사용자인 경우만 댓글 작성
```python
# articles/views.py

@require_POST
def comments_create(request, pk):
    if request.user.is_authenticated:
        article = Article.objects.get(pk=pk)
        ...
        return redirect('articles:detail', article.pk)
    return redirect('accounts:login')
```
- 인증된 사용자인 경우만 댓글 삭제
```python
# articles/views.py

@require_POST
def comments_delete(request, article_pk, comment_pk):
    if request.user.is_authenticated:
        comment = Comment.objects.get(pk=comment_pk)
        ...
    return redirect('articles:detail', article_pk)
```
- 비인증 사용자는 CommentForm을 볼 수 없도록 하기
```html
<!-- articles/detail.html -->

...
{% if request.user.is_authenticated %}
  <form action = "{% url 'articles:comments_create' article.pk %}" method = "POST">
...
{% else %}
  <a href="{% url 'accounts:login' %}">[댓글을 작성하려면 로그인하세요.]</a>
{% endif %}
...
```