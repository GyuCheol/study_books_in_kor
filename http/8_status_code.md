# HTTP - Status Code  
Status Code는 서버 응답의 구성 요소이다.  
3자리 숫자로 이루어져 있고, 1번째 숫자는 상태 코드의 분류를 정의하고,  
뒤의 2자리 숫자는 특정한 규칙은 없다.

첫 숫자에 따라 아래의 5가지 의미를 의미한다.
1. 1xx : Informational  
요청이 수신되었고, 진행 중이라는 의미.

2. 2xx : Success  
요청이 성공적으로 수신 되었고, 이해되었고, 승인 되었음 의미.

3. 3xx : Redirection  
요청이 완료됨에 따라 추가 행동을 취해야 함을 의미

4. 4xx : Client Error  
요청에 잘못된 문법이나 채우지 못한 것이 포함됨을 의미.

5. 5xx : Server Error  
서버에서 유효한 요청을 처리하는 것을 실패 했음을 의미

HTTP 상태 코드는 확장 가능하고, HTTP App은 모든 Status Code 의미를 이해할 필요가 없다.

## 1xx Information

- `100 Continue`  
요청의 일부만 서버에 의해 수신되었지만, 반려되지 않은 한,  
클라이언트는 요청을 계속 해야함을 의미한다.

- `101 Switching Protocols`  
서버가 프로토콜을 변환한다.

## 2xx Successful

- `200 OK`  
요청이 잘 처리됨

- `201 Created`  
요청이 완료 되었고, 새로운 자원이 생성되었음

- `202 Accepted`  
요청 처리가 수락 되었지만, 절차는 완료되지 않음.

- `203 Non-authoriative Information`  
entitiy 헤더의 정보가 원본 서버가 아닌 다른 third-party에 의한 것.

- `204 No Content`  
상태 코드와 헤더가 응답에서 주어졌지만, body가 없음.

- `205 Reset Content`  
브라우저는 추가적인 입력을 위해 현재 트랜잭션에 사용된 형식을 clear해야함

- `206 Partial Content`  
서버는 요청된 사이즈의 부분의 데이터만 반환함. 요청에서 명시한 Range 헤더의 부분만 응답에서 사용되었다는 의미.  
서버는 Content-Range 헤더에 range를 반드시 명시해야한다.

## 3xx Redirection  

- `300 Multiple Choices`  
하나의 링크 목록. 유저는 여기서 선택하고 해당 위치로 이동할 수 있다.  
최대 5개 주소

- `301 Moved Permanently`  
요청된 페이지는 새로운 url로 이동되었다.

- `302 Found`  
요청된 페이지는 임시로 새 url로 이동 되었다.

- `303 See Other`  
요청된 페이지가 다른 url에서 발견될 수 있다.

- `304 Not Modified`  
이것은 특절 날짜 이래로 URL이 수정되지 않았음을 나타내는 `If-Modified-Since` 또는 `If-None-Match` 헤더를 위한 하나의 응답 코드이다.

- `305 Use Proxy`  
요청된 URL은 반드시 언급된 Location header proxy를 통해 접근되어야 함

- `306 Unused`  
이 코드는 더 이상 사용되지는 않는다. 하지만 예약된 코드임..

- `307 Temporary Redirect`  
요청된 페이지는 일시적으로 새 url로 이동 되었다. (302와 동일?)

## 4xx Client Error  

- `400 Bad Request`  
서버는 이 요청을 이해하지 못했음.

- `401 Unauthorized`  
이 페이지는 유저 이름, 패스워드가 필요하다.

- `402 Payment Required`  
아직 이 코드를 사용할 수 없음.

- `403 Forbidden`  
현재 요청에 대한 접근 금지

- `404 Not Found`  
서버에서 해당 페이지를 찾을 수 없음.

- `405 Method Not Allowed`  
요청에서 명시된 method가 허용되지 않음

- `406 Not Acceptable`  
서버는 오직 클라이언트에 의해 수락 되지 않은 응답만을 생성할 수 있음. 

- `407 Proxy Authentication Required`  
이 요청을 전달하기 전에, proxy 서버로 자격을 증명해야 한다.

- `408 Request Timeout`  
이 요청은 서버가 기다리기 위한 준비된 것 보다 더 기다렸다.

- `409 Conflict`  
요청은 충돌 때문에 완료 될 수 없었음.

- `410 Gone`  
요청된 페이지가 더이상 유효하지 않음.

- `411 Length Required`  
Content-Length가 정의되지 않았음, 서버는 그것 없이 요청을 허용하지 않음.

- `412 Precondition Failed`  
서버에 의해 요청에 의해 주어진 선행 조건이 거짓으로 평가됨.

- `413 Request Entity Too Large`  
body가 너무 커서 서버에서 요청 거부함.

- `414 Request-url Too Long`  
url이 너무 길어서 서버에서 거부함.  
보통, post 요청을 get으로 요청할 때 일어난다.

- `415 Unsupported Media Type`  
서버에서 지원하지 않은 mediatype은 거부함.

- `416 Requested Range Not Satisfiable`  
요청된 byte range는 경계를 벗어나서 사용할 수 없음.

- `417 Expectation Failed`  
서버에 의해 처리 실패가 예상됨. (header 등을 읽고서 판단)

## 5xx Server Error

- `500 Internal Server Error`  
요청이 완료되지 않았음. 서버가 예상치 못한 상태를 만남.

- `501 Not Implemented`  
서버는 요청된 기능을 지원하지 않음.

- `502 Bad Gateway`  
서버는 유효하지 않은 응답을 upstream server로 부터 수신 받음.  
(Apache나 NginX 등으로 부터?)

- `503 Service Unavailable`
서버는 일시적으로 다운됨.

- `504 Gateway Timeout`  
gateway가 시간 초과됨

- `505 HTTP version Not Supported`  
서버는 요청된 http protocol 버전을 지원하지 않음.
