# HTTP - Messages  
HTTP는 CS 구조 모델과, TCP/IP 연결을 가로질러 메시지 교환을 동작하는 stateless 요청/응답 프로토콜을 기반으로 한다.

HTTP 클라이언트는 웹 브라우저나 별도 다른 프로그램일 수 있다.  
그것은 서버에게 1개 이상의 HTTP 요청 메시지를 보낼 목적으로 만들어진 프로그램 일 것이다.  
HTTP 서버는 주로 Apache거나 IIS 등의 프로그램이다.  
그것은 HTTP 요청에 따라 HTTP 응답을 전송하기 위해 연결을 받아들인다.

HTTP는 URI를 사용하여 주어진 자원을 식별하고 연결을 만든다.  
한번 연결이 설정되는 것은, HTTP 메시지를 전달하는 것과 유사하다.  
이 메시지들은 클라이언트로 부터 서버까지 요청과 서버로부터 클라이언트까지 응답을 포함한다. (아래와 같이)  
```
HTTP-message = <Request> | <Response> ; HTTP/1.1 messages
```

HTTP 요청과 HTTP 응답은 RFC822 형식으로 사용된다.  
이 일반적인 메시지 형식은 아래 4가지 아이템으로 이루어져 있다.  
1. 시작 줄
2. CRLF로 나누어진 0개 이상의 헤더
3. 빈 줄 (그냥 CRLF), 헤더 필드의 마지막을 보여주는..
4. 선택적으로, 메시지 body

다음 섹견들은, 이 4가지 아이템에 대해 설명한다.

## Start-Line  
시작하는 줄은 아래 문법을 따른 것을 가지게 된다.
```
start-line = Request-Line | Status-Line
```
Request-Line과 Status-Line에 대해 이야기할 것이다.  
이것은 HTTP 요청과 HTTP 응답 메시지 제각기 다르게 보여지게 될 것이다.  
아래 예시를 참고.
```
GET /hello.htm HTTP/1.1     클라이언트로 부터 보내진 Request-Line
HTTP/1.1 200 OK             서버로부터 보내진 Status-Line
```

## Header Fields  
HTTP header 필드는 요청이나 응답에 대해 필요한 정보를 제공한다.  
추가로 메시지 body에 대한 정보도 포함하기도 한다.  
이것은 총 4가지 유형으로 헤더를 볼 수 있다.

- General-header : 요청, 응답 모두 사용 가능한 헤더들
- Request-header : 요청에서만 사용 가능한 헤더들
- Response-header : 응답에서만 사용 가능한 헤더들
- Entity-header : body에 대한 메타 정보를 정의한 헤더들

이 모든 헤더들은 아래 형식을 따른다.  
```
message-header = field-name ":" [ field-value ]
```

헤더 필드의 예시
```
User-Agent: curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3
Host: www.example.com
Accept-Language: en, mi
Date: mon, 27 Jul 2009 12:28:53 GMT
Server: Apache
Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
ETag: "34aa387-d-1568eb00"
Accept-Ranges: bytes
Content-Length: 51
Vary: Accept-Encoding
Content-Type: text/plain
```

## Body  
Body는 선택적인 HTTP 메시지 부분이다.  
만약 이것이 있다면, 보통 Content-Type과 Content-Length 헤더를 명시하는 것이 자연스럽다.

body는 실제 HTTP 요청 데이터(form data, 업로드한 파일 등)을 서버에 전달하기 위해 쓰거나, 서버로부터 응답한 내용을 명시하기 위해 쓴다.  
아래는 body에 대한 간단한 예.
```html
<html>
  <body>
    <h1>Hello, World!</h1>
  </body>
</html>
```

