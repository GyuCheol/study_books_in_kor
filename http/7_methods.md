# HTTP - Methods  
HTTP/1.1의 일반적인 methods 세트는 아래 정리 되어 있다.  
이 세트는 요구사항에 맞게 확장 될 수 있다.  
이것들은 대소문자 구분되므로, 대문자로 써야한다.

# GET Method  
GET 메소드는 매개변수를 URL에 명시함으로 서버로부터 데이터를 받는 요청이다.  
이것은 문서를 받기 위한 주 메소드로 사용된다.  
아래 헤더를 보자.
```
GET /hello.htm HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
Host: www.tutorialspoint.com
Accept-Language: en-us
Accept-Encoding: gzip, deflate
Connection: Keep-Alive
```

서버는 아래와 같은 메시지를 반환한다.
```
HTTP/1.1 200 OK
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
ETag: "34aa387-d-1568eb00"
Vary: Authorization,Accept
Accept-Ranges: bytes
Content-Length: 88
Content-Type: text/html
Connection: Closed

<html>
<body>
<h1>Hello, World!</h1>
</body>
</html>
```

# HEAD Method  
HEAD 메소드는 기능적으로는 GET과 비슷하지만, 서버는 헤더만 반환하지 body는 반환하지 않는다.

아래는 request의 예시.
```
HEAD /hello.htm HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
Host: www.tutorialspoint.com
Accept-Language: en-us
Accept-Encoding: gzip, deflate
Connection: Keep-Alive
```

이 메시지를 받고 서버는 이렇게 응답할 것이다. (body가 없음)  
메소드 단어 그대로 header만 받았다.
```
HTTP/1.1 200 OK
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
ETag: "34aa387-d-1568eb00"
Vary: Authorization,Accept
Accept-Ranges: bytes
Content-Length: 88
Content-Type: text/html
Connection: Closed
```

# POST method  

POST method는 데이터를 서버에 보내고 싶을 때 사용한다. 파일 데이터나, 폼 데이터나 기타 등등.  
아래 예시처럼, POST 메소드가 서버에 body를 보내는 것을 볼 수 있다.

```
POST /cgi-bin/process.cgi HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
Host: www.tutorialspoint.com
Content-Type: text/xml; charset=utf-8
Content-Length: 88
Accept-Language: en-us
Accept-Encoding: gzip, deflate
Connection: Keep-Alive

<?xml version="1.0" encoding="utf-8"?>
<string xmlns="http://clearforest.com/">string</string>
```

이 요청을 받은 서버의 응답 예다.
```
HTTP/1.1 200 OK
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
ETag: "34aa387-d-1568eb00"
Vary: Authorization,Accept
Accept-Ranges: bytes
Content-Length: 88
Content-Type: text/html
Connection: Closed

<html>
<body>
<h1>Request Processed Successfully</h1>
</body>
</html>
```

# PUT Method  
PUT메소드는 서버에 body에 포함된 내용을 저장하는 것을 요청하기 위해 사용된다.  
생성 또는 업데이트에 이용되는 느낌임.

요청의 예시
```
PUT /hello.htm HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
Host: www.tutorialspoint.com
Accept-Language: en-us
Connection: Keep-Alive
Content-type: text/html
Content-Length: 182

<html>
<body>
<h1>Hello, World!</h1>
</body>
</html>
```

서버는 body를 저장할 것이고, 아래를 응답할 것이다.
```
HTTP/1.1 201 Created
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
Content-type: text/html
Content-length: 30
Connection: Closed

<html>
<body>
<h1>The file was created.</h1>
</body>
</html>
```

## DELETE Method  
DLETE 메소드는 서버에 주어진 URL에 명시된 위치의 파일을 삭제하는 것을 요청하기 위해 사용된다.  
아래는 요청 예시
```
DELETE /hello.htm HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
Host: www.tutorialspoint.com
Accept-Language: en-us
Connection: Keep-Alive
```

아래는 응답 예시
```
HTTP/1.1 200 OK
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
Content-type: text/html
Content-length: 30
Connection: Closed

<html>
<body>
<h1>URL deleted.</h1>
</body>
</html>
```

## CONNECT Method  
이 메소드는 HTTP 넘어 웹서버와 클라이언트가 네트워크 연결을 수립하기 위해 사용된다.  

아래와 같이 요청된다.
```
CONNECT www.tutorialspoint.com HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
```

연결이 수립되었고, 서버는 아래와 같이 응답한다.
```
HTTP/1.1 200 Connection established
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
```

## OPTIONS Method  
이 메소드는 웹서버가 어떤 HTTP 메소드들을 지원하는지 알기 위해서 사용한다.  
요청은 아래와 같이 될 수 있다.  
만약 클라이언트가 URL을 *로 보낸다면 서버 전체에 대한 참조를 하게 된다.  
다른 특정 URI가 있다면 해당 URI에 대한 메소드만 볼 것이다.
```
OPTIONS * HTTP/1.1
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
```

서버는 이렇게 응답한다. (Allow 헤더를 통해 확인)
```
HTTP/1.1 200 OK
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
Allow: GET,HEAD,POST,OPTIONS,TRACE
Content-Type: httpd/unix-directory
```

## TRACE Method  
이 메소드는 개발할 때 디버깅 목적으로 사용된다.  

```
TRACE / HTTP/1.1
Host: www.tutorialspoint.com
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
```

위와 같은 요청에 따라 아래와 같이 서버는 응답할 것이다.

```
HTTP/1.1 200 OK
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
Connection: close
Content-Type: message/http
Content-Length: 39

TRACE / HTTP/1.1
Host: www.tutorialspoint.com
User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
```
