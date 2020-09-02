# HTTP - Responses  
요청된 메시지를 받고 해석이 끝난 후, 서버는 HTTP 응답 메시지와 함께 응답한다. 응답 메시지는 아래를 포함한다.  
- A Status-line
- 0개 이상의 헤더 (CRLF로 구분됨)
- empty line (헤더 끝맺음을 알려줌)
- message-body

기본적으로 request 형태와 동일하다.  
하지만, 내부에 들어가는 세부적인 내용은 차이가 있고, 아래 sections에서 설명함.

## Message Status-Line  
Status-Line은 프로포콜 버전과, 숫자로 나타낸 status code, 그 코드와 연관된 텍스트가 있다. 모두 공백으로 구분되어짐.
```
Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
```

## HTTP Version  
HTTP 1.1을 지원하는 서버는 아래와 같은 버전을 반환할 것이다.
```
HTTP-Version = HTTP/1.1
```

## Status Code  
Status-Code 구성은 3개의 숫자로 표현이 되어있다.  
1번째 숫자는 응답에 대한 분류를 정의하고, 나머지 2숫자는 어떤 역할은 없지만 구분을 나타낸다.  
1번째 숫자는 아래와 같이 5가지의 분류가 있다.  
1. Informational  
요청이 서버에 수신되었고, 진행 중임을 의미한다.

2. Success  
성공적으로 전달되었다. (서버에서 무사히 처리됨)

3. Redirection  
요청이 완료 됨에 따라 추가적인 액션을 취해야한다는 뜻이다.  
페이지가 다른 곳으로 이동 되거나 등.

4. Client Error  
응답에 잘못된 문법이나, 제대로 채우지 않은 내용을 포함하고 있다는 뜻.

5. Server Error  
서버에서 요청을 적절히 처리하는데에 실패했다는 뜻.

HTTP status code는 확장성이 있고 HTTP app은 등록된 모든 status code를 이해할 필요가 없다.  
추가로 이것에 대해서는 다른 챕터에서 설명하게 될 것임.

## Response Header Fields  
다른 챕터에서 더 배우겠지만, 지금은 어떤 header이 있는지 살펴보자.

응답 헤더 필드는 응답에 대한 서버에서 추가적인 정보를 전달하기 위해 쓰인다.  
이 헤더들은 서버와 요청 URI에 식별된 자원에 따른 접근 정보를 준다.
- Accept-Ragnes
- Age
- ETag
- Location
- Proxy-Authenticate
- Retry-After
- Server
- Vary
- WWW-Authenticate

이외에도 적절한 custom 필드를 정의하여 사용해도 되긴함.

## 응답 메시지에 대한 예제들

```
HTTP/1.1 200 OK
Date : Mon, 27, Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (wWin32)
Last-modified: Wed, 22 Jul 2009 19:15:56 GMT
Content-Length: 88
Content-Type: text/html
Connection: Closed

<html>
<body>
<h1>Hello, World!</h1>
</body>
</html>
```

아래는 error 상태를 보여주는 응답 메시지이다.  
```
HTTP/1.1 404 Not Found
Date: Sun, 18 Oct 2012 10:36:20 GMT
Server: Apache/2.2.14 (Win32)
Content-Length: 230
Connection: Closed
Content-Type: text/html; charset=iso-8859-1

<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
<html>
<head>
   <title>404 Not Found</title>
</head>
<body>
   <h1>Not Found</h1>
   <p>The requested URL /t.html was not found on this server.</p>
</body>
</html>
```

