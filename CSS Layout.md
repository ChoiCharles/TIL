# CSS Layout
## Float
---
- 박스를 왼쪽 혹은 오른쪽으로 이동시켜 텍스트를 포함 인라인요소들이 주변을 wrapping 하도록 함
- 요소가 Normal flow를 벗어나도록 함
### Float 속성
- none : 기본값
- left : 요소를 왼쪽으로 띄움
- right : 요소를 오른쪽으로 띄움
### 정리
- Float은 레이아웃을 구성하기 위해 필수적으로 사용 되었으나, Flexbox, Grid의 등장과 함께 사용도가 낮아짐
- Float 활용 전략 - Normal Flow에서 벗어난 레이아웃 구성
    - 원하는 요소들을 Float으로 지정하며 배치

## Flexbox
---
> Layout을 위해 만들어짐
- 행과 열 형태로 아이템들을 배치하는 1차원 레이아웃 모델
- 축
    - main axis (메인 축)
    - cross axis (교차 축)
- 구성 요소
    - Flex Container (부모 요소)
        - flexbox 레이아웃을 형성하는 가장 기본적인 모델
        - Flex Item들이 놓여있는 영역
        - display 속성을 flex 혹은 inline-flex로 지정
    - Flex Item (자식 요소)
        - 컨테이너에 속해 있는 컨텐츠(박스)
### Flex 속성
- 배치 설정
    - flex-direction
        - Main axis 기준 방향 설정
        - 역방향의 경우 HTML 태그 선언 순서와 시각적으로 다르니 유의
    - flex-wrap
        - 아이템이 컨테이너를 벗어나는 경우 해당 영역 내에 배치되도록 설정
    - flex-flow
        - flex-direction과 flex-wrap의 shorthand
        - flex-direction과 flex-wrap에 대한 설정 값을 차례로 작성
- 공간 나누기
    - justify-content (main axis)
        - Main axis를 기준으로 공간 배분
    - align-content (cross axis)
        - Cross axis를 기준으로 공간 배분
- 정렬
    - align-items
        - 모든 아이템을 cross axis 기준으로 정렬
    - align-self
        - 개별 아이템을 cross axis 기준으로 정렬
- 기타속성
    - flex-grow : 남은 영역을 아이템에 분배
    - order : 배치 순서