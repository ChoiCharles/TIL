# Build RESTful API
## POST
- 게시글 데이터 생성하기
- 요청에 대한 데이터 생성이 성공했을 경우는 201 Created 상태 코드를 응답하고 실패 했을 경우는 400 Bad request를 응답
```python
# articles/views.py

from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from .models import Article
from .serializers import ArticleSerializer

@api_view(['GET', 'POST'])
def article_list(request):
    if request.method == 'GET':
        articles = Article.objects.all()
        serializer = ArticleSerializer(articles, many=True)
        return Response(serializer.data)
    elif request.method == 'POST':
        serializer = ArticleSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
```
- POST http://127.0.0.1:8000/api/v1/articles/ 응답 확인
- 새로 생성된 데이터 확인
## Raising an exception on invalid data
- "유효하지 않은 데이터에 대해 예외 발생시키기"
- is_valid()는 유효성 검사 오류가 있는 경우 ValidationError 예외를 발생시키는 선택적 raise_exception 인자를 사용할 수 있음
- DRF에서 제공하는 기본 예외 처리기에 의해 자동으로 처리되며 기본적으로 HTTP 400 응답을 반환
- view 함수 코드 변경
```python
# articles/views.py

@api_view(['GET', 'POST'])
def article_list(request):
    if request.method == 'GET':
        articles = Article.objects.all()
        serializer = ArticleListSerializer(articles, many=True)
        return Response(serializer.data)
    elif request.method == 'POST':
        serializer = ArticleListSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        # return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
```
## DELETE
- 게시글 데이터 삭제하기
- 요청에 대한 데이터 삭제가 성공했을 경우는 204 No Content 상태 코드 응답 (명령을 수행했고 더 이상 제공할 정보가 없는 경우)
```python
# articles/views.py

@api_view(['GET', 'DELETE'])
def article_detail(request, article_pk):
    article = Article.objects.get(pk=article_pk)
    ...
    elif request.method == 'DELETE':
        article.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
```
- DELETE http://127.0.0.1:8000/api/v1/articles/21/ 응답 확인

## PUT
- 게시글 데이터 수정하기
- 요청에 대한 데이터 수정이 성공했을 경우는 200 OK 상태 코드 응답
```python
# articles/views.py

@api_view(['GET', 'DELETE', 'PUT'])
def article_detail(request, article_pk):
    article = Article.objects.get(pk=article_pk)
    ...
    elif request.method == 'PUT':
        serializer = ArticleListSerializer(articles, data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data)
```
- 수정된 데이터 확인
# Django REST framework - N:1 Relation
- N:1 관계에서의 모델 data를 Serialization하여 JSON으로 변환하는 방법
> 사전 준비
- Comment 모델 작성 및 데이터베이스 초기화
```python
# articles/models.py

class Comment(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
```
- Migration 진행
- 준비된 fixtures 데이터 load
```
$ python manage.py loaddata articles.json comments.json
```
## GET - List
- 댓글 데이터 목록 조회
- Article List와 비교하며 작성
```python
# articles/serializers.py

from .models import Article, Comment

class CommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        fields = '__all__'
```
```python
# articles/urls.py

urlpatterns = [
    ...,
    path('comments/', views.comment_list),
]
```
```python
# articles/views.py

from .models import Article, Comment
from .serializers import ArticleListSerializer, CommentSerializer

@api_view(['GET'])
def comment_list(request):
    comments = Comment.objects.all()
    serializer = CommentSerializer(comments, many=True)
    return Response(serializer.data)
```
- GET http://127.0.0.1:8000/api/v1/comments/ 응답 확인
## GET - Detail
- 단일 댓글 데이터 조회
- Article과 달리 같은 serializer 사용
```python
# articles/urls.py

urlpatterns = [
    ...,
    path('comments/<int:comment_pk>/', views.comment_detail),
]
```
```python
# articles/views.py

@api_view(['GET'])
def comment_detail(request):
    comments = Comment.objects.get(pk=comment_pk)
    serializer = CommentSerializer(comments)
    return Response(serializer.data)
```
- GET http://127.0.0.1:8000/api/v1/comments/1/ 응답 확인

## POST
- 단일 댓글 데이터 생성
```python
# articles/urls.py

urlpatterns = [
    ...,
    path('articles/<int:article_pk>/comments/', views.comment_create),
]
```
```python
# articles/views.py

@api_view(['POST'])
def comment_create(request, article_pk):
    article = Article.objects.get(pk=article_pk)
    serializer = CommentSerializer(data=request.data)
    if serializer.is_valid(raise_exception=True):
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)
```
### Passing Additional attributes to .save()
- **save()** 메서드는 특정 Serializer 인스턴스를 저장하는 과정에서 추가적인 데이터를 받을 수 있음
- **CommentSerializer**를 통해 Serialize되는 과정에서 Parameter로 넘어온 **article_pk**에 해당하는 article객체를 추가적인 데이터를 넘겨 저장
```python
# articles/views.py

@api_view(['POST'])
def comment_create(request, article_pk):
    article = Article.objects.get(pk=article_pk)
    serializer = CommentSerializer(data=request.data)
    if serializer.is_valid(raise_exception=True):
        serializer.save(article=article)
        return Response(serializer.data, status=status.HTTP_201_CREATED)
```
- POST http://127.0.0.1:8000/api/v1/articles/1/comments/ 응답 확인
- 에러
    - CommentsSerializer에서 article field 데이터 또한 사용자로부터 입력 받도록 설정되어 있기 때문
### 읽기 전용 필드 설정
- **read_only_field**를 사용해 외래 키 필드를 **'읽기 전용 필드'**로 설정
- 읽기 전용 필드는 데이터를 전송하는 시점에 '해당 필드를 유효성 검사에서 제외시키고 데이터 조회 시에는 출력'하도록 함
```python
# articles/serializer.py

class CommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        fields = '__all__'
        read_only_fields = ('article',)
```
- POST http://127.0.0.1:8000/api/v1/articles/1/comments/ 응답 재확인
## DELETE & PUT
- 댓글 데이터 삭제 및 수정 구현
```python
# articles/views.py

@api_view(['GET', 'DELETE', 'PUT'])
def comment_detail(request, comment_pk):
    comments = Comment.objects.get(pk=comment_pk)
    if request.method == 'GET':
        serializer = CommentSerializer(comments)
        return Response(serializer.data)
    elif request.method == 'DELETE':
        comments.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    elif request.method == 'PUT':
        serializer = CommentSerializer(comments, data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data)
```
- DELETE http://127.0.0.1:8000/api/v1/comments/21/ 응답 확인
- PUT http://127.0.0.1:8000/api/v1/comments/1/ 응답 확인

# N:1 - 역참조 데이터 조회
## 특정 게시글에 작성된 댓글 목록 출력하기
- 기존 필드 override - Article Detail
    - "게시글 조회 시 해당 게시글의 댓글 목록까지 함께 출력"
    - Serializer는 기존 필드를 override하거나 추가적인 필드를 구성할 수 있음
1. **PrimaryKeyRelatedField()
```python
# articles/serializers.py

class ArticleSerializer(serializers.ModelSerializer):
    comment_set = serializers.PrimaryKeyRelatedField(many=True, read_only=True)
    class Meta:
        model = Article
        # fields = ('id', 'title', 'content')
        fields = '__all__'
```
- models.py에서 **related_name**을 통해 이름 변경 가능
- 역참조 시 생성되는 **comment_set**을 override 할 수 있음
2. Nested relationships
```python
# articles/serializers.py

class ArticleSerializer(serializers.ModelSerializer):
    # comment_set = serializers.PrimaryKeyRelatedField(many=True, read_only=True)
    comment_set = CommentSerializer(many=True, read_only=True)
    class Meta:
        model = Article
        # fields = ('id', 'title', 'content')
        fields = '__all__'
```
- 모델 관계 상으로 참조 된 대상은 참조하는 대상의 표현에 포함되거나 중첩(nested)될 수 있음
- 이러한 중첩된 관계는 serializers를 필드로 사용하여 표현 할 수 있음
- 두 클래스의 상/하 위치를 변경해야 함

## 특정 게시글에 작성된 댓글의 개수 출력
- 새로운 필드 추가 - Article Detail
    - "게시글 조회 시 해당 게시글의 댓글 개수까지 함께 출력"
```python
# articles/serializers.py

class ArticleListSerializer(serializers.ModelSerializer):
    comment_set = CommentSerializer(many=True, read_only=True)
    comment_count = serializers.IntegerField(source='comment_set.count', read_only=True)
    class Meta:
        model = Article
        fields = '__all__'
```
- **source**
    - serializers field's argument
    - 필드를 채우는 데 사용할 속성의 이름
    - 점 표기법(dotted notation)을 사용하여 속성을 탐색 할 수 있음

> [주의] 읽기 전용 필드 지정 이슈
- 특정 필드를 override 혹은 추가한 경우 read_only_fields가 동작하지 않으니 주의
```python
# 사용 불가능

class ArticleListSerializer(serializers.ModelSerializer):
    comment_set = CommentSerializer(many=True)
    comment_count = serializers.IntegerField(source='comment_set.count')
    class Meta:
        model = Article
        fields = '__all__'
        read_only_fields = ('comment_set', 'comment_count')
```
# Django shortcuts functions
- django.shortcuts 패키지는 개발에 도움 될 수 있는 여러 함수와 클래스를 제공
- 제공되는 shortcuts 목록
    - render(), redirect(), **get_object_or_404()**, **get_list_or_404()**
## get_object_or_404()
- 모델 manager objects에서 get()을 호출하지만, 해당 객체가 없을 땐 기존 DoesNotExist 예외 대신 Http404를 raise 함
```python
# articles/views.py

from django.shortcuts import get_object_or_404

article = Article.objects.get(pk=article_pk)
comment = Comment.objects.get(pk=comment_pk)

# 위 코드를 다음과 같이 변경
article = get_object_or_404(Article, pk=article_pk)
comment = get_object_or_404(Comment, pk=comment_pk)
```
## get_list_or_404()
- 모델 manager objects에서 filter()의 결과를 반환하고 해당 객체 목록이 없을 땐 Http404를 raise 함
```python
# articles/views.py

from django.shortcuts import get_object_or_404, get_list_or_404

articles = Article.objects.all()
comments = Comment.objects.all()

# 위코드를 다음과 같이 변경
articles = get_list_or_404(Article)
comments = get_list_or_404(Comment)
```
- 존재하지 않는 게시글 조회 시 이전에는 500 상태코드를 응답했지만 현재는 404 상태코드를 응답
> 왜 사용할까?
- 클라이언트 입장에서 "서버에 오류가 발생하여 요청을 수행할 수 없다(500)"라는 원인이 정확하지 않은 에러를 마주하기 보다는, 서버가 적절한 예외 처리를 하고 클라이언트에게 올바른 에러를 전달하는 것 또한 중요한 요소

# Serializer 활용
## 특정 게시물의 모든 댓글 조회
```python
# urls.py

urlpatterns = [
    path('articles/', views.article_list),
    path('articles/<int:article_pk>/', views.article_detail),
    # path('comments/', views.comment_list),
    # path('articles/<int:article_pk>/comments/', views.comment_create),
    path('articles/<int:article_pk>/comments/', views.comment_list),
    path('comments/<int:comment_pk>/', views.comment_detail),
]
```
- views.py의 comment_list와 comment_create 합성
```python
# articles/views.py

@api_view(['GET', 'POST'])
def comment_list(request, article_pk):
    article = get_object_or_404(Article, pk=article_pk)
    
    if request.method == 'GET':
        comments = article.comment_set.all()
        serializer = CommentSerializer(comments, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = CommentSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save(article=article)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
```
## to_representation()
- 모델 수정 없이 기존의 필드명을 다른 필드명으로 사용 하거나, 특정 필드를 출력되지 않게 할 수 있음
    - comment_set -> comments
- 필드명 바꿔서 출력
```python
# articles/serializers.py

class ArticleListSerializer(serializers.ModelSerializer):
    comment_set = CommentSerializer(many=True, read_only=True)
    comment_count = serializers.IntegerField(source='comment_set.count', read_only=True)
    class Meta:
        model = Article
        fields = (
            'id',
            'title',
            'content',
            'comment_set',
            'comment_count',
        )
    
    def to_representation(self, instance):
        rep = super().to_representation(instance)
        rep['comments'] = rep.pop('comment_set', [])
        return rep
```
- 특정 필드 출력 x
```python
# articles/serializer.py

class CommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        fields = '__all__'
        read_only_fields = ('article',)
        
    def to_representation(self, instance):
        rep = super().to_representation(instance)
        rep.pop('article', None)
        return rep
```
## 상속 이용하기
- 목록 조회시 변화는 없지만 게시글 상세 조회시 작성자 정보도 같이 출력
```python
# articles/serializers.py

class ArticleListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Article
        fields = (
            'id',
            'title',
            'content',
        )
    
class ArticleDetailSerializer(ArticleListSerializer):
    comment_set = CommentSerializer(many=True, read_only=True)
    comment_count = serializers.IntegerField(source='comment_set.count', read_only=True)
    
    class Meta(ArticleListSerializer.Meta):
        fields = ArticleListSerializer.Meta.fields + (
            'comment_set',
            'comment_count',
        )
    
    def to_representation(self, instance):
        rep = super().to_representation(instance)
        rep['comments'] = rep.pop('comment_set', [])
        return rep
```
```python
# articles/views.py

from .serializers import ArticleListSerializer, CommentSerializer, ArticleDetailSerializer

@api_view(['GET', 'DELETE', 'PUT'])
def article_detail(request, article_pk):
    articles = Article.objects.get(pk=article_pk)
    if request.method == 'GET':
        serializer = ArticleDetailSerializer(articles)
        return Response(serializer.data)
    elif request.method == 'DELETE':
        articles.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    elif request.method == 'PUT':
        serializer = ArticleDetailSerializer(articles, data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data)
```

# 문서화
## 스웨거(Swagger)
- 스웨거(Swagger)는 개발자가 REST 웹 서비스를 설계, 빌드, 문서화 등을 도와주는 오픈 소스 소프트웨어 프레임워크 
- API를 설계하고 문서화 하는데 도움을 주는 라이브러리
## DRF-YASG
- 스웨거를 기반으로 DRF API에 대한 문서를 쉽게 작성할 수 있도록 도와주는 도구 
- DRF-YASG는 스웨거를 구현한 것 중 하나, 스키마를 자동으로 파악해 인터렉티브한 문서를 생성해줌

```
pip install drf-yasg
```
```python
# settings.py
INSTALLED_APPS = [
    ...,
    'dfr_yasg',
    ...,
]
```
```python
# urls.py
# drf_yasg 공식문서 quick start 참고

from django.contrib import admin
from django.urls import path, include, re_path
from rest_framework import permissions
from drf_yasg.views import get_schema_view
from drf_yasg import openapi

schema_view = get_schema_view(
   openapi.Info(
      title="MY DRF API",
      default_version='v1',
      description="Swagger를 이용한 DRF API 문서",
      terms_of_service="https://www.google.com/policies/terms/",
      contact=openapi.Contact(email="contact@snippets.local"),
      license=openapi.License(name="BSD License"),
   ),
   public=True,
   permission_classes=[permissions.AllowAny],
)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/v1/', include('articles.urls')),
]

urlpatterns += [
   re_path(r'^swagger(?P<format>\.json|\.yaml)$', schema_view.without_ui(cache_timeout=0), name='schema-json'),
   re_path(r'^swagger/$', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
   re_path(r'^redoc/$', schema_view.with_ui('redoc', cache_timeout=0), name='schema-redoc'),
]
```
- http://127.0.0.1:8000/swagger/ 접속