# HTTP Header Fields  
HTTP 헤더 필드는 요청이나 응답 또는 body에 대한 필요한 정보를 제공한다.  
헤더는 4가지 타입으로 구분이 된다.

- General-header : 요청, 응답 모두에 쓰일 수 있는 헤더

- Client Request-header : 요청에만 쓰이는 헤더

- Server Response-header : 응답에만 쓰이는 헤더

- Entity-header : body에 대한 메타 정보를 정의한다. 만약 바디가 없다면, 식별된 자원에 대한 메타 정보가 된다.

## General-header (일반적인 헤더)

`Cache-Control`  
caching 시스템이 준수해야 하는 지시사항을 지정하는 데 사용된다.  
문법은 아래를 따른다.  

```
Cache-Control : cache-request-directive|cache-response-directive
```

HTTP 클라이언트나 서버는 Cache-control 헤더를 사용할 수 있다.  
사용하는 경우는, 캐시에 대한 매개변수를 지정하거나 문서를 요청할 때 사용함.  
캐싱하도록 명령하는 것은 ','로 분할된 목록으로 명시되어진다.  

```
Cache-control: no-cache
```

아래 목록과 같이 중요 HTTP 요청에서 캐시 요청 지시를 할 수 있음.

- no-cache : 캐시는 서버와 함께 성공적인 갱신 없이 다음 요청을 만족하는 응답으로 사용하지 않아야 한다.  
(다른 자료를 찾아보니, 캐시를 하지만, 재 검증을 위해 서버 요청을 보낸다고 함..)

- no-store : 캐시는 저장되지 않아야 함.

- max-age = seconds : 지정된 시간(초)보다 크지 않은 응답을 수락할 것을 의미함.

- max-stale [= seconds] : 그것의 명시한 시간보다 초과된 응답을 허용할 것을 의미함. 만약 시간 값이 주어진다면, 그것은 그 시간 보다 더 오래 만료되서는 안됨.

- min-fresh = seconds : 명시한 시간(초) 보다 작은 시간을 가진 응답을 허용할 것을 의미.

- no-transform : body를 변환할 수 없음.

- only-if-cached : 새 데이터를 검색하지 않음. 캐시된 경우에만 문서를 보낼 수 있고, 이것을 위해 서버에 contact하면 안됨.

아래는 response에 대한 cache 헤더이다.

- public : 아무 캐시에게서 캐시되었을 것을 의미.(?)  
(어떤 캐시에 의해서든 캐시된다는 것)

- private : 전체 또는 부분 응답 메시지가 단일 사용자를 겨냥 했거나 공유 캐시에 의해 캐시 되지 않아야 함을 의미 (?)

- no-cache : 캐시는 하지만, 재검증을 위해 서버에 요청을 보냄.

- no-store : 캐시로 저장하지 않아야함 (캐시X)

- no-transform : body가 변환되지 않음.

- must-revalidate

- proxy-revalidate

- max-age

- s-maxage

캐시는 내공 부족으로 여기까지만..  
조금 더 공부한 이후에 이해가 될 듯.

`Connection`  
연결 필드는 연결 상태가 어떻게 되는지 명시하는 옵션이다.  
아래와 같이 헤더를 지정하면, 응답 이후에 연결은 닫히게 된다.
```
Connection: close
```

기본적으로, HTTP1.1은 집요한 연결을 사용한다.  
즉, 동작 이후 자동으로 닫히지 않는다.  
HTTP1.0에서는 기본적으로 자동으로 닫히게 설정되어있었다.  
만약 1.0 client에게 좀 더 긴 연결을 원한다면, keep-alive 매개변수를 따라야한다.
```
Connection: keep-alive
```

`Date`  
모든 HTTP 날짜 stamp는 예외 없이 GMT 형식으로 표현되어야 한다.  
HTTP app은 아래와 같은 3가지 형식을 따른다.  
```
Sun, 06 Nov 1994 08:49:37 GMT  ; RFC 822, updated by RFC 1123
Sunday, 06-Nov-94 08:49:37 GMT ; RFC 850, obsoleted by RFC 1036
Sun Nov  6 08:49:37 1994       ; ANSI C's asctime() format
```

1번째 줄의 형식이 일반적으로 따르게 되는 것이다.

`Pragma`  
이 필드는 특별한 지시를 명세를 구현한 것을 포함하기 위해 쓰여졌다.

이외 필드들 (딱히 더 확인하기 힘든 필드들...)  
- Trailer, Transfer-Encoding, Upgrade, Via, Warning

## Client Request Header  
`Accept`  
응답에 받아들여지는 분명한 media type을 명세하기 위해 사용된다.

```
Accept: text/plain; q=0.5, text/html, text/x-dvi; q=0.8, text/x-c
```

