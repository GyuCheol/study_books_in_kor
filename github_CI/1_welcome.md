# github actions이 뭔지?

github actions는 유연한 방법들이다. (S/W 업무 흐름의 모든 형태를 자동으로 하는 방법)  
여기에 github actions을 사용하는 몇 가지 방법을 소개한다.

- Automated testing (CI) - 자동화된 테스트
- Continuous delivery and deployment - 지속적 전달 및 배포
- Responding to workflow tirggers using issues, @ mentions, labels, and more - 업무 흐름의 트리거에 따른 응답  
  (트리거는 이유, 멘션, 레이블 등이 사용됨)
- Triggering code reviews (코드 리뷰 트리거 발생)
- Managing branches
- Triaging issues and pull requests (이슈, 풀 리퀘스트 분류?)

최고의 부분은, 이 업무 흐름들은 코드로서 너의 저장소에 저장된다. (쉽게 공유하고 팀 간에 재사용이 가능)  
자세한 내용은, github actions feature apge 또는 documentation을 참조

## 시작하기 전에

기본적인 github 사용법을 배우고 와야한다.

## 실습

### Actions and Workflows

아래 2개 구성 요소는 Github Actions이 커버하기 위해 사용됨

- the `action` itself
- a `workflow` that uses action(s)

하나의 workflow는 많은 action을 포함할 수 있다.  
각 action은 각각 자신의 목적을 가진다.  
우리는 각 파일의 디렉터리에 연관된 액션 파일을 작성할 것이다.

### Types of Actions (Action 유형들)

Action은 2가지 유형으로 제공된다. (come in)

- docker container actions  
  도커 컨테이너 action은 github actions code와 함께 묶이게 되는 환경이고 오직 linux 환경으로 실행될 수 있다.

- javascript actions  
  자바 스크립트 액션은 github action 코드와 환경으로부터 분리된다. (더 빠른 실행이지만, 의존성 관리에 책임을 동반함)

## 실습1] Dockerfile 만들기

1. 새로운 branch 만들기 (first-action)
2. branch에 새로운 폴더 action-a 만들기
3. action-a 폴더에 Dockerfile 파일 만들기
4. 아래 내용 집어 넣기

```docker
FROM debian:9.5-slim

ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
```

5. commit
6. master에 대해 pull request 때리기

## 실습2] entrypoint script 추가

Dockerfile을 커밋 했다, 이제 dockerfile의 마지막 부분을 보면 아래를 확인할 수 있다.

```docker
ENTRYPOINT ["/entrypoint.sh"]
```

이 파일은 docker에서 실행될 것이고, 그것은 실제로 동작하게 될 액션을 정의한 것이다.

아래와 같은 entrypoint.sh 파일 추가하기 (action-a 폴더)

```shell
#!/bin/sh -l

sh -c "echo Hello world my name is $INPUT_MY_NAME"
```

## 실습3] action metadata 파일 추가하기

우리는 input 매개변수를 사용하여 MY_NAME의 값을 읽을 것이다.

`action.yml` 만들기 (같은 action-a 폴더)

```yml
name: 'Hello Actions'
description: 'Greet someone'
author: 'octocat@github.com'

inputs:
  MY_NAME:
    description: 'Who to greet'
    required: true
    default: 'World'

runs:
  using: 'docker'
  image: 'Dockerfile'

branding:
  icon: 'mic'
  color: 'purple'
```

## 실습4] workflow 파일 실행

workflows는 .github/workflows 폴더에 특별한 파일들도 정의된다. (main.yml)  
workflows는 너가 선택한 이벤트를 기반으로 실행할 수 있다. 이번 실습에선, push event를 이용할 것이다.  
우리는 다음 step의 workflow의 각 줄을 분해할 것이다.

`workflow file` 시작하기  
먼저, 우리는 workflow의 구조를 추가할 것이다.

`.github/workflows/main.yml`을 만들자. (아래 코드 넣기)

```yml
name: A workflow for my Hello World file
on: push
```

## 실습5] workflow 파일로 부터 액션 실행

위 yml 파일의 각 의미를 살펴보자  
`name` : workflow의 이름이다. 이 이름은 어떤 pull request든 나타나게 된다. (또는 Actions tab에서도 보임)  
이 이름을 잘 짓는 것이 좋다. (구분하기 쉽고, 가독성 좋게)
`on` : _push_ 라는 이벤트에서 실행될 수 있도록 식별한다.

### Actions

Workflows는 jobs을 함께 잇고, job는 step을 함께 잇는다. (piece togeter 종합하다?? ?.?)  
우리는 이제 action을 실행하는 job을 만들 것이다.  
Actions는 같은 repository, 다른 공용 repository 또는 배포된 docker 컨테이너 이미지에서 사용될 수 있다.  
우리는 이 저장소에서 우리가 정의한 액션을 사용할 것이다.

아까 작성한 main.yml에 아래 블록을 추가하다.

```yml
jobs:
  build:
    name: Hello world action
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v1
      - uses: ./action-a
        with:
          MY_NAME: 'Mona'
```

##
