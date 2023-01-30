'''
filter
리스트나 튜플과 같은 순회 가능한 데이터구조 값들을 함수에 적용하는데
적용후 값중 True만 반환 !!! filter 라는 객체로 반환
'''
num = [1, 2, 3, 4, 5]
def get_even(t):
    return True if t%2==0 else False
result = filter(get_even, num)
print(list(result))

'''
lambda
익명함수 함수를 간략하게 적기 위해서 사용
'''
result = lambda a, b:a + b
print(result(2, 3))

lst1 = [1, 2, 3, 4, 5]
lst2 = [6, 7, 8, 9, 10]
result = lambda x, y:x + y
a = list(map(result, lst1, lst2))
print(a)

lst = list(range(100))
even = lambda x:x%2==0
lst2 = list(filter(even, lst))
print(lst2)

'''
데이터에 일괄적용 = map
데잉터에 일괄적용하는데 True값만 따로 저장하겠다 = filter
lambda 익명함수 (사용자 함수를 직접 적지 않고 간단하게 함수 사용학 싶을때 사용)
'''

'''
recursion 재귀함수
함수가 자기자신을 계속 호출하는 함수
'''
# 1 2 3 4 5 6 7 8 9 10 10 9 8 7 6 5 4 3 2 1
def abc(level):
    if level == 11:
        return
    print(level, end = ' ')
    abc(level + 1)
    print(level, end = ' ')

abc(1)



'''
문자열
리스트 튜플 딕셔너리 셋 관련 메소드
copy (깊은복사 얕은복사)
'''
# 특정 문자가 있는지 확인
# 없으면 -1 반환

st = 'apple, banana, mango'
index = st.find('a')
print(index)
index = st.find('ap')
print(index)
index = st.find('a', 1)

# 없으면 오류남
alpha = st.index('p')
print(alpha)

# 대소문자 확인
print(st.isupper())
print(st.islower())
print(st.isalpha())

# 문자열의 갯수
print(st.count('a'))

# 합치기
st = ['a', 'p', 'p', 'l', 'e']
str2 = ''.join(st)
print(str2)

# 리스트안에 문자를 합치는데 사이사이에 , 를 넣어라
str2 = ','.join(st)
print(str2)

st = ['apple', 'banana', 'mango']
str = ','.join(st)
print(str)

# 모두 대문자로 바꾸기
# 모두 소문자로 바꾸기
st = 'apple, banana, mango'
str2 = st.upper()
print(str2)
str2 = st.lower()
print(str2)

# 공백지우기
st = '  apple'
str2 = st.lstrip()  # 오른쪽은 rstrip, 양쪽은 strip
print(str2)

# 교체 replace
st = 'apple'
str2 = st.replace('ap', 'mp')
print(str2)

# 리스트 값 추가
st = ['apple', 'banana', 'mango']
st.append('orange')
st.insert(1, 'orange')  # 리스트 값을 중간 또는 맨앞에 추가할 때 사용
print(st)


st = [1, 2, 3]
str2 = [4, 5]
st.extend(str2)
print(st)
st += str2  # extend랑 같음

# 리스트 값 지우기
st = [1, 2, 3]
st.pop()
print(st)

st = [1, 2, 3, 4, 1, 2, 3, 4]
st.remove(4)
print(st)

st = [1, 2, 3, 4, 1, 2, 3, 4]
del st[3:]
print(st)

st = [1, 2, 3, 4, 1, 2, 3, 4]
st.reverse()
print(st)
print(st[::-1])

# sort
a1 = [6, 3, 9]
print(a1)
a1.sort()
print(a1)
a1.sort(reverse = True)
print(a1)

a1 = [6, 3, 9]
ret = sorted(a1)    # 원본데이터 유지
print(a1, ret)
ret = sorted(a1, reverse = True)
print(a1, ret)

# lambda를 이용한 sort
lst = list(range(10))
print(lst)
ret = sorted(lst, key = lambda x:x) # ret = sorted(lst)
ret = sorted(lst, key = lambda x:-x) # ret = sorted(lst, reverse = True)

lst = [(3, 'banana'), (2, 'apple'), (1, 'carrot')]
ret = sorted(lst, key = lambda x:x[1])
print(ret)

# 딕셔너리
st = {'kevin':1, 'john':2, 'bob':3}

# 딕셔너리에 key, value 추가하기
st['kate'] = 'hi'
st['kevin'] = 11
print(st)
del st['kate']
print(st)

lst = st.keys()
print(list(lst))

lst = st.values()
print(list(lst))

lst = st.items()
print(list(lst))

# 딕셔너리 값 출력하기
st = {'kevin':1, 'john':2, 'bob':[1, 2, 3]}
print(st.get('kevin'))
print(st.get('kkkkkk', '값없음'))
print(st.get('kevin', '값없음'))

# 딕셔너리 값 정렬하기
st = {'kevin':27, 'john':22, 'bob':35}
ret = dict(sorted(st.items(), key = lambda x:x[1]))
print(ret)

'''
문자열
리스트 튜플 딕셔너리 셋 관련 메소드
copy (깊은복사 얕은복사)
'''
# lst = [1, 2, 3]
# lst2 = lst
# lst[0] = 100
# print(*lst2)

lst = [1, 2, 3]
lst2 = lst.copy()   # 얕은복사
#lst2 = lst[:]
lst[0] = 100
print(lst2)

lst = [[1, 2], [3, 4]]
lst2 = lst[:]   # copy()
lst[0][0] = 100
print(lst2[0][0])
'''
얕은복사는 2차원배열부턴 개입이 안됨
'''

# 깊은복사
import copy
lst = [[1, 2], [3, 4]]
lst2 = copy.deepcopy(lst)
lst[0][0] = 100
print(lst[0][0])

# 주소값
a = 5
b = 5
print(id(a), id(b))
lst = [1, 2, 3]
lst2 = lst
print(id(lst), id(lst2))

lst = [1, 2, 3]
lst2 = lst[:]
print(id(lst), id(lst2))

lst = [[1, 2], [3, 4]]
lst2 = lst[:]
print(id(lst), id(lst2))
print(id(lst[0]), id(lst2[0]))

import copy
lst = [[1, 2], [3, 4]]
lst2 = copy.deepcopy(lst)
print(id(lst[0]), id(lst2[0]))