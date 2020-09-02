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

- no-store : 캐시는 저장되지 않아야 함.

- max-age = seconds : 지정된 시간(초)보다 크지 않은 응답을 수락할 것을 의미함.

- max-stale [= seconds] : 그것의 명시한 시간보다 초과된 응답을 허용할 것을 의미함. 만약 시간 값이 주어진다면, 그것은 그 시간 보다 더 오래 만료되서는 안됨.

- min-fresh = seconds : 명시한 시간(초) 보다 작은 시간을 가진 응답을 허용할 것을 의미.

- no-transform : body를 변환할 수 없음.

- only-if-cached : 새 데이터를 검색하지 않음. 캐시된 경우에만 문서를 보낼 수 있고, 이것을 위해 서버에 contact하면 안됨.

아래는 response에 대한 cache 헤더이다.

- public : 응답은 아무 캐시에게서 캐시된 것을 의미.

- private : 
