# API

- 두 소프트웨어가 서로 통신할 수 있도록 연결 시켜주는 인터페이스

- API요청시 json이라는 포멧으로 받을 수 있음

- requests 라이브러리를 이용해 원격으로 API를 호출할수 있음

```python
response = requests.get('url')
response = requests.get('url').json()
```

- .json()으로 정리된 형태로 API를 볼수 있음

### REST API

**Representational State Transfer(REST)**

- API 작동 방식에 대한 조건을 부과하는 소프트웨어 아키텍처

- 균일한 인터페이스를 가졌다



# CLI

**Command Line Interface**

- 가상 터미널 혹은 터미널에서 컴퓨터와 상호작용하는 방식

- GUI(Graphic User Interface)에 비해 메모리 소모가 적음

## 명령어

---

```git
ls
ls -l
ls -a
```

- 현재 디렉토리 폴더 내부 확인

- ls -l로 파일 상세정보 확인가능

- ls -a로 숨겨진 파일 확인가능

```git
cd dirName
cd ~
cd ..
```

- 현재 directory에 존재하는 directory로 이동하거나, cd .. 으로 상위 directory로 이동가능

- cd ~로 root directory로 이동가능

```git
mkdir dirName
```

- 현재 directory에서 새로운 directory를 생성함

```git
touch fileName
```

- 현재 directory에서 새로운 파일 생성

```git
mv file(dir)Name dirName
```

- 파일(directory)을 현재 directory내에 존재하는 directory로 옮김

```git
start fileName
```

- 해당파일을 실행시킴

```git
rm fileName
rm -r dirName
```

- directory내 파일 혹은 directory를 삭제함
