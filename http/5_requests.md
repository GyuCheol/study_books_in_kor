# HTTP - Requests  
HTTP 클라이언트는 HTTP 요청을 서버에 보낸다.  
(아래의 형식을 포함한 한 요청 메시지)  
- Request-line (가장 첫줄)
- 0개 이상의 헤더 (CRLF로 분할된)
- 헤더와 body를 구분할 empty line
- 메시지 바디 (있거나 없거나)

다음 sections은 이 요청 메시지에 대해 설명한다.

## Request-Line  
Request-Line은 메소드 토큰과 함께 시작된다.  
이것은 Reuqest-URI과 프로토콜 버전, 그리고 CRLF로 끝맺음이 된다.  
모든 토큰은 space (그냥 공백)으로 분할되어 있다.

```
Request-Line = Method SP Request-URI SP HTTP-Version CRLF
```

이제 각 부분에 대해 이야기해보자

## Request Method  
요청 메소드는 주어진 Request_URI의 식별된 자원을 어떻게 수행되기 위한 것인지 방법을 표시한다.  
이 메소드는 대소문자를 하므로, 대문자(uppercase)로 언급해야 한다.  
아래 리스트에 맞게 HTTP1.1은 이런 메소드를 지원한다.  

1. GET  
주어진 URI를 사용하여 서버에서 정보를 가져오기 위해 사용된다.  
이 요청은 보통 데이터만 가져오지, 다른 영향은 없어야 한다.

2. HEAD  
GET과 흡사하지만, status line과 header만 전송한다.

3. POST  
데이터를 서버에 보내기 위해 사용된다.  
예를 들어, 파일 업로드 정보나 form 등.

4. PUT  
타겟 자원의 정보를 대체하기 위해 사용한다. (UPDATE?)

5. DELETE  
타겟 자원의 정보를 지우기 위새 사용한다.

6. CONNECT  
주어진 URI에 식별된 서버로 터널을 만들기 위해 사용된다. ?.?

7. OPTIONS  
대상 리소스를 위한 communication 옵션들을 설명한다.

8. TRACE  
타겟 리소스까지의 경로를 따라 메시지 loop back을 수행한다.

주로 쓰는 Method가 GET, POST, PUT, DELETE라 나머진 잘 이해가 안됨.

## Request-URI  
Request_URI은 한 Uniform Resource Identifier이고, 요청한 자원을 식별한다.  
아래와 같이 일반적으로 사용된다.
```
Request-URI = "*" | absoluteURI | abs_path | authority
```

1. "*"  
`별표(asterisk)`은 특정 자원이 아닌 서버 자체에 적용될 때 사용된다.  
요청한 method가 자원이 필요하지 않을 때만 작용한다.  
`example`  
OPTIONS * HTTP/1.1

2. absoluteURI  
`absoluteURI`은 HTTP 요청이 proxy로 만들어질 때 사용된다.  
프록시는 유효한 cache 요청이나 서비스를 보내고, 응답을 반환하는데 요청된다.  
`example`  
GET http://www.w3.org/pub/WWW/TheProject.html HTTP/1.1

3. abs_path  
가장 일반적인 형식의 Request-URI은 한 서버나 gateway에서 자원을 식별하기 위해 사용된다.  
예를 들어, 서버에서 부터 직접 자원을 받기 위한 클라이언트는 80번 포트를 사용하는 TCP 연결을 만들 것이다. 아래를 참고하자  
`example`  
GET /pub/WWW/TheProject.html HTTP/1.1  
Host: www.w3.org

이러한 abs_path는 empty가 될 수 없다.  
적어도 "/"는 포함하여야 한다. (server root를 의미한다)

## Request Header Fields  
우리는 여러 챕터를 통해 일반적인 header와 entity-header를 배우게 될 것이다.  
지금은, Request header를 확인해보자.

request-header 필드는 클라이언트가 요청에 대한 추가 정보를 보내도록 허용한다.  
그러한 필드들은 요청 수정자로 동작한다.  
여기 약간의 중요한 Request-header 필드들을 나열한다. (겁나 많다.)  
- Accept-Charset
- Accept-Encoding
- Accept-Language
- Authorization
- Expect
- From
- Host
- If-Match
- If-Modified-Since
- If-None_Match
- If-Range
- If-Unmodified-Since
- Max-Forwards
- Proxy-Authorization
- Range
- Referer
- TE
- User-Agent

또한, 너는 너의 custom 필드를 포함시킬 수도 있다.  
물론, Client, Web Server가 둘다 이해하도록 코딩되어야 하겠지만. 

## Request 메시지 예제  
자, 이제 함께 HTTP 요청 형식을 입력해보자.  
이것은 tutorialspoint.com에서 동작하는 웹서버의 hello.htm 페이지를 fetch하는 요청이다.

```
GET /hello.htm HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
Host: www.tutorialspoint.com
Accept-Language: en-us
Accept-Encoing: gzip, deflate
Connection: Keep_Alive
```

여기에 우리는 어떤 요청 데이터도 보내지 않고 있다.  
왜냐하면 우리는 일반 HTML 페이지를 teching하고 있기 때문이다.  
연결은 일반적인 헤더이고, 나머지 헤더는 요청 헤더이다.(?.?)  
아래 예제는 어떻게 폼 데이터를 보내는지 보여주는 header와 body이다.  
```
POSET /cgi-bin/process.cgi HTTP1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
Host: www.tutorialspoint.com
Content-Type: application/x-www-form-urlencoded
Content-Length: length
Accept-Language: en-us
Accept-Encoing: gzip, deflate
Connection: Keep_Alive

licenseID=string&content=string&/paramsXML=string
```

이것은 주어진 `/cgi-bin/process.cgi`가 전달된 데이터를 처리하기 위해 사용될 것을 의미한다.  
그리고 그에맞춰 응답은 반환될 것이다.  
여기 `content-type`은 client가 보내는 데이터를 의미하고, `length`는 데이터 body의 길이를 의미한다.  
아래는 XML 데이터를 서버에 보내는 예제이다.  
Content-Type이 변경됨과 body 내용이 그에맞게 보여주고 있다.

```
POST /cgi-bin/process.cgi HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
Host: www.tutorialspoint.com
Content-Type: text/xml; charset=utf-8
Content-Length: length
Accept-Language: en-us
Accept-Encoding: gzip, deflate
Connection: Keep-Alive

<?xml version="1.0" encoding="utf-8"?>
<string xmlns="http://clearforest.com/">string</string>
```
