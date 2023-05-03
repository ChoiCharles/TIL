# Form
> 개요
- 현재 Django 서버는 들어오는 요청을 모두 수용하고 있는데, 이러한 요청 중에는 비정상적인 혹은 악의적인 요청이 있다는 것을 생각해야 함
- 이처럼 사용자가 입력한 데이터가 우리가 원하는 데이터 형식이 맞는지에 대한 유효성 검증이 반드시 필요
    - 이런 유효성 검증은 많은 부가적인 것들을 고려해서 구현해야 하는데, 이는 개발 생산성을 늦출뿐더러 쉽지 않은 작업임
- Django Form은 이 과정에서 과중한 작업과 반복 코드를 줄여줌으로써 훨씬 쉽게 유효성 검증을 진행할 수 있도록 만들어 줌
### Form에 대한 Django의 역할
---
- Form은 Django의 유효성 검사 도구 중 하나로 외부의 악의적 공격 및 데이터 손상에 대한 중요한 방어 수단
- Django는 Form과 관련한 유효성 검사를 단순화하고 자동화 할 수 있는 기능을 제공하여, 개발자가 직접 작성하는 코드보다 더 안전하고 빠르게 수행 하는 코드를 작성할 수 있다
### Django는 Form에 관련된 작업의 세 부분을 처리
---
1. 렌더링을 위한 데이터 준비 및 재구성
2. 데이터에 대한 HTML forms 생성
3. 클라이언트로부터 받은 데이터 수신 및 처리
# Form Class
> 개요
- Django form 관리 시스템의 핵심
### 선언
---
- Model Class를 선언하는 것과 비슷해 비슷한 이름의 필드 타입을 많이 가지고 있다
- Model과 마찬가지로 상속을 통해 선언
> forms.py
- 앱 폴더에 forms.py를 생성 후 ArticleForm Class선언
```python
# articles/forms.py
from django import forms

class ArticleForm(forms.Form):
    title = forms.CharField(max_length=10)
    content = forms.CharField()
```
- form에는 model field와 달리 TextField가 없음
> view 업데이트
- 업데이트 전
```python
def new(request):
    return render(request, 'articles/new.html')

def create(request):
    title = request.POST.get('title')
    content = request.POST.get('content')
    article = Article(title=title, content=content)
    article.save()
    return redirect('articles:index')
```
- 업데이트 후
```python
from .forms import ArticleForm

def create(request):
    if request.method == 'POST':
        title = request.POST.get('title')
        content = request.POST.get('content')
        article = Article(title=title, content=content)
        article.save()
        return redirect('articles:detail', article.pk)
    else:
        form = ArticleForm()
        context = {'form':form}
        return render(request, 'articles/create.html', context)
```
> create 템플릿 업데이트
- 업데이트 전 (new.html)
```html
{% extends 'base.html' %}

{% block content %}
  <h1>글작성</h1>
  <hr>

  <form action="{% url 'articles:create' %}" method="POST">
    {% csrf_token %}
    <label for="title">제목 : </label>
    <input type="text" id="title" name="title"><br>
    <label for="content">내용 : </label>
    <input type="text" id="content" name="content"><br>
    <input type="submit">
  </form>
{% endblock content %}
```
- 업데이트 후 (create.html)
```html
{% extends 'base.html' %}

{% block content %}
  <h1>글작성</h1>
  <hr>
  <form action="{% url 'articles:create' %}" method="POST">
    {% csrf_token %}
    {{form.as_p}}
    <input type="submit">
  </form>
{% endblock content %}
```
### From rendering options
---
- \<label> & \<input> 쌍에 대한 3가지 출력 옵션
1. as_p()
    - 각 필드가 단락(\<p>태그)으로 감싸져서 렌더링
2. as_ul()
    - 각 필드가 단락(\<li>태그)으로 감싸져서 렌더링
    - \<ul>태그는 직접 작성해야 한다
3. as_table()
    - 각 필드가 단락(\<tr>태그)으로 감싸져서 렌더링
- 특별한 상황이 아닌한 as_p()를 사용
### Django의 2가지 HTML input 요소 표현
---
1. Form field
    - 입력에 대한 유효성 검사 로직을 처리
    - 템플릿에서 직접 사용됨
```python
forms.CharField()
```
2. Widgets
    - 웹페이지의 HTML input 요소 렌더링을 담당
        - 단순히 input 요소의 보여지는 부분을 변경
    - Widgets은 반드시 form fields에 할당 됨
```python
forms.CharField(widget=forms.Textarea)
```
## Widgets
---
> 개요
- Django의 HTML input element의 표현을 담당
- 단순히 HTML 렌더링을 처리하는 것이며 유효성 검증과 아무런 관계가 없음
    - **웹 페이지에서 input element의 단순 raw한 렌더링만을 처리하는 것일 뿐**
> Textarea 위젯 적용
```python
class ArticleForm(forms.Form):
    title = forms.CharField(max_length=10)
    content = forms.CharField(widget=forms.Textarea)
```
- 다양한 built-in 위젯
    - https://docs.djangoproject.com/ko/3.2/ref/forms/widgets/#built-in-widgets


# ModelForm
> 개요
- Form은 Model이랑 중복되는 부분이 많다
- 이미 Model Class에 필드에 대한 정보를 작성했는데 이를 Form에 맵핑하기 위해 Form Class에 필드를 재정의 해야만 한다
- ModelForm을 사용하면 이러한 Form을 더 쉽게 작성할 수 있음
    - Model을 통해 Form Class를 만들 수 있는 helper class
    - ModelForm은 Form과 똑같은 방식으로 View함수에서 사용
### ModelForm 선언
---
> forms.py
- forms 라이브러리에서 파생된 ModelForm 클래스를 상속받음
- 정의한 ModelForm 클래스 안에 Meta 클래스를 선언
- 어떤 모델을 기반으로 form을 작성할 것인지에 대한 정보를 Meta 클래스에 지정
```python
# articles/forms.py
from django import forms
from .models import Article

class ArticleForm(forms.ModelForm):
    class Meta:
        model = Article
        fields = '__all__'
```
- 기존 ArticleForm은 주석처리
> Meta Class
- ModelForm의 정보를 작성하는 곳
- ModelForm을 사용할 경우 참조 할 모델이 있어야 하는데, Meta class의 model 속성이 이를 구성함
    - 참조하는 모델에 정의된 field정보를 Form에 적용함
- fields속성에 '\_\_all__'을 사용하여 모델의 모든 필드를 포함할 수 있음
- 또는 exclude 속성을 사용하여 모델에서 포함하지 않을 필드를 지정할 수 있음
```python
# articles/forms.py
from django import forms
from .models import Article

class ArticleForm(forms.ModelForm):
    class Meta:
        model = Article
        exclude = ('title')
```
- field와 exclude를 같이 작성해도 되나 권장하지 않음
- 모델 정보를 Meta라는 이름의 내부 클래스로 작성하도록 ModelForm의 설계가 이렇게 되어있을 뿐이니 파이썬의 문법적 개념으로 접근하지 말 것
# ModelForm 구현
> 개요
- ModelForm으로 인한 view함수의 구조 변화
### CREATE
---
- 유효성 검사를 통과하면
    - 데이터 저장
    - 상세 페이지로 리다리렉트
- 통과하지 못하면
    - 작성 페이지로 리다이렉트
> views.py
```python
def create(request):
    if request.method == 'POST':
        form = ArticleForm(request.POST)
        if form.is_valid():
            article = form.save()
            return redirect('articles:detail', article.pk)
        return redirect('articles:create')
    else:
        form = ArticleForm()
        context = {'form':form}
        return render(request, 'articles/create.html', context)
```
> "is_valid()" method
- 유효성 검사를 실행하고, 데이터가 유효한지 여부를 boolean으로 반환
- 데이터 유효성 검사를 보장하기 위한 많은 테스트에 대해 Django는 is_valid()를 제공하여 개발자의 편의를 도움
> "save()" method
- form 인스턴스에 바인딩 된 데이터를 통해 데이터베이스 객체를 만들고 저장
- ModelForm의 하위 클래스는 키워드 인자 **instance** 여부를 통해 생성할 지, 수정할 지를 결정함
    - 제공되지 않은 경우 save()는 지저된 모델의 새 인스턴스를 만듦(CREATE)
    - 제공되면 save()는 해당 인스턴스를 수정(UPDATE)
```python
# CREATE
form = ArticleForm(request.POST)
form.save()

# UPDATE
form = ArticleForm(request.POST, instance=article)
form.save()
```

> create 함수 정리
- 정리 전
```python
def create(request):
    if request.method == 'POST':
        form = ArticleForm(request.POST)
        if form.is_valid():
            article = form.save()
            return redirect('articles:detail', article.pk)
        return redirect('articles:create')
    else:
        form = ArticleForm()
        context = {'form':form}
        return render(request, 'articles/create.html', context)
```
- 정리 후
```python
def create(request):
    if request.method == 'POST':
        form = ArticleForm(request.POST)
        if form.is_valid():
            article = form.save()
            return redirect('articles:detail', article.pk)
    else:
        form = ArticleForm()
    context = {'form':form}
    return render(request, 'articles/create.html', context)
```
### UPDATE
---
- ModelForm의 인자 instance는 수정 대상이 되는 객체(기존 객체)를 지정
1. request.POST
    - 사용자가 form을 통해 전송한 데이터(새로운 데이터)
2. instance
    - 수정이 되는 대상
> views.py 수정
- 수정 전
```python
def edit(request, pk):
    article = Article.objects.get(pk=pk)
    context = {'article': article}
    return render(request, 'articles/edit.html', context)

def update(request, pk):
    article = Article.objects.get(pk=pk)
    article.title = request.POST.get('title')
    article.content = request.POST.get('content')
    article.save()
    return redirect('articles:detail', pk=article.pk)
```
- 수정 후
```python
def update(request, pk):
    article = Article.objects.get(pk=pk)
    if request.method == 'POST':
        form = ArticleForm(request.POST, instance=article)
        if form.is_valid():
            form.save()
            return redirect('articles:detail', article.pk)
    else:
        form = ArticleForm(instance=article)
    context = {'form':form, 'article':article}
    return render(request, 'articles/update.html', context)
```
> template 수정
- 수정 전
```html
{% extends 'base.html' %}

{% block content %}
  <h1>글수정</h1>
  <hr>

  <form action="{% url 'articles:update' article.pk %}" method="POST">
    {% csrf_token %}
    <label for="title">제목 : </label>
    <input type="text" id="title" name="title" value="{{article.title}}"><br>
    <label for="content">내용 : </label>
    <input type="text" id="content" name="content"value="{{article.content}}"><br>
    <input type="submit">
  </form>

  <hr>
  <a href="{% url 'articles:detail' article.pk %}">돌아가기</a>
{% endblock content %}
```
- 수정 후
```html
{% extends 'base.html' %}

{% block content %}
  <h1>글수정</h1>
  <hr>

  <form action="{% url 'articles:update' article.pk %}" method="POST">
    {% csrf_token %}
    {{ form.as_p }}
    <input type="submit">
  </form>
  <hr>
  <a href="{% url 'articles:detail' article.pk %}">돌아가기</a>
{% endblock content %}
```
## Form과 ModelForm
---
- ModelForm이 Form보다 더 좋은 것이 아닌 각자 역할이 다른 것이다
- **Form**
    - 사용자의 입력을 필요로 하며 직접 입력 데이터가 DB 저장에 사용되지 않거나 일부 데이터만 사용될 떼
    - 예) 로그인
- **ModelForm**
    - 사용자의 입력을 필요로 하며 입력을 받은 것을 그대로 DB 필드에 맞춰 저장할 때
    - 데이터의 유효성 검사가 끝나면 데이터를 각각 어떤 레코드에 맵핑해야 할지 이미 알고 있기 때문에 곧바로 save() 호출이 가능

# Static files
### Static File
---
- 응답할 때 별도의 처리 없이 파일 내용을 그대로 보여주면 되는 파일
    - 사용자의 요청에 따라 내용이 바뀌는 것이 아니라 요청한 것을 그대로 보여주는 파일
- **파일 자체가 고정**되어 있고, 서비스 중에도 추가되거나 **변경되지 않고 고정** 되어있음
    - 예를 들어, 웹 사이트는 일반적으로 이미지, 자바 스크립트 또는 CSS와 같은 미리 준비된 추가 파일(움직이지 않는)을 제공해야 함
- Django에서는 이러한 파일들을 "static file"이라 함
    - Django는 **staticfiles**앱을 통해 정적 파일과 관련된 기능을 제공
### Media File
---
- 미디어 파일
- 사용자가 웹에서 업로드하는 정적 파일 (user_uploaded)
- 유저가 업로드 한 모든 정적 파일
### 웹 서버와 정적 파일
---
- 웹 서버의 기본동작은
    - 특정 위치(URL)에 있는 자원을 요청(HTTP request)받아서
    - 응답(HTTP response)을 처리하고 제공(serving)하는 것
- 이는 "자원과 자원에 접근 가능한 주소가 있다" 라는 의미
    - 예를 들어, 사진 파일은 자원이고 해당 사진 파일을 얻기 위한 경로인 웹 주소(URL)가 존재함
- 즉, 웹 서버는 요청 받은 URL로 서버에 존재하는 정적 자원(static resource)을 제공함
# Static files 구성
### Django에서 정적파일을 구성하고 사용하기 위한 몇가지 단계
---
1. INSTALLED_APPS에 django.contrib.staticfiles가 포함되어 있는지 확인
2. settings.py에서 **STATIC_URL**을 정의
3. 앱의 static폴더에 정적 파일을 위치
    - 예시) my_app/static/sample_img.jpg
4. 템플릿에서 static템플릿 태그를 사용하여 지정된 경로에 있는 정적 파일의 URL만들기
```html
{% load static %}
<img src="{% static 'sample_img.jpg' %}" alt="sample image">
```
### Django template tag
---
```html
{% load %}
```
- load tag
- 특정 라이브러리, 패키지에 등록된 모든 템플릿 태그와 필터를 로드
```html
{% static '' %}
```
- static tag
- STATIC_ROOT에 저장된 정적 파일에 연결
### Static files 관련 Settings
---
1. STATIC_ROOT
    - Default: None
    - Django 프로젝트에ㅓ 사용하는 모든 정적 파일을 한곳에 모아 넣는 경로
    - collectstatic이 배포를 위해 정적 파일을 수집하는 디렉토리의 절대 경로
    - 개발 과정에서 settings.py의 DEBUG값이 True로 설정되어 있으면 해당 값은 작용되지 않음
    - 실 서비스 환경(배포 환경)에서 Django의 모든 정적 파일을 다른 웹 서버가 직접 제공하기 위해 사용
    - 배포 환경에서는 Django를 직접 실행하는 것이 아니라, 다른 서버에 의해 실행되기 때문에 실행하는 다른 서버는 Django에 내장되어 있는 정적파일들을 인식하지 못함(내장되어 있는 정적 파일들을 밖으로 꺼내는 이유)
2. STATICFILES_DIRS
    - Default:[] (Empth list)
    - app/static/디렉토리 경로를 사용하는 것(기본 경로)외에 추가적인 정적 파일 경로 목록을 정의하는 리스트
    - 추가 파일 디렉토리에 대한 전체 경로를 포함하는 문자열 목록으로 작성되어야 함
    > settings.py
    ```python
    STATICFILES_DIR = [
        BASE_DIR / 'static',
    ]
    ```
3. STATIC_URL
    - Default: None
    - STATIC_ROOT에 있는 정적 파일을 참조 할 때 사용할 URL
    - 개발 단계에서는 실제 정적 파일들이 저장되어 있는 app/static/ 경로(기본경로) 및 STATICFILES_DIRS에 정의된 추가 경로들을 탐색
    - **실제 파일이나 디렉토리가 아니며, URL로만 존재**
    - 비어있지 않은 값으로 설정 한다면 반드시 slash(/)로 끝나야 함
    > settings.py
    ```python
    STATIC_URL = '/static/'
    ```
# Static files 사용하기
- Static file을 가져오는 2가지 방법
    1. 기본 경로에 있는 static file가져오기
    2. 추가 경로에 있는 static file가져오기
### 기본경로에 있는 static file가져오기
---
1. articles/static/articles 경로에 이미지 파일 배치
2. static tag를 사용해 이미지 파일 출력
> articles/index.html
```html
{% extends 'base.html' %}
{% load static %}

{% block content %}
    <img src="{% static 'articles/sample_img_1.png' %}" alt="sample-img-1">
    <h1>Articles</h1>
    ...
```
3. 이미지 출력 확인
### 추가 경로에 있는 static file 가져오기
---
1. 추가 경로 작성
> settings.py
```python
STATICFILES_DIRS = [
    BASE_DIR / 'static',
]
```
2. static/ 경로에 이미지 파일 배치하기
3. static tag를 사용해 이미지 파일 출력하기
```html
{% extends 'base.html' %}
{% load static %}

{% block content %}
    <img src="{% static 'sample_img_1.png' %}" alt="sample-img-1">
    <h1>Articles</h1>
    ...
```
4. 이미지 출력 확인