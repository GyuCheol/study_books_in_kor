# Servlets - Life Cycle

서블릿 라이프 사이클은 그것이 생성과고 파괴될 때까지의 전체 프로세스로 정의될 수 있다.  
서블릿의 의해 아래를 따른다.  
    - 서블릿은 init() 메소드를 호출하는 것으로 초기화 된다.
    - 클라이언트의 요청마다 service() 메소드를 호출한다.
    - 종료될 때 destroy() 메소드를 호출한다.
    - 최종적으로 JVM gc에 위해 수집된다.

조금 더 자세히 알아보자.  

## init 메소드
init 메소드는 단 한번 호출된다.  
그것은 서블릿이 생성 되거나 유저 요청이 한번도 호출되지 않았을 때 단 한번만 호출된다.  
그래서 이것은 1번 초기화를 위해 사용된다.

서블릿은 일반적으로 유저가 처음으로 URL를 호출했을 때 생성된다.  
그러나 역시 서버가 처음 시작될 때 로드되도록 명시할 수도 있다.

init method는 요렇게 정의됨.
```java
public void init() throws ServletException {
    // code...
}
```

## service 메소드

service 메소드는 실제 작업이 수행되는 주 메소드이다.  
서블릿 container (tomcat같은 웹서버)는 유저로 부터 온 요청을 처리하기 위해 service 메소드를 호출한다.  
그리고 유저에게 다시 보내기 위한 포맷된 응답을 작성한다. (html 같은 것들?)

서버가 각 서블릿의 요청을 수신할 때마다, 서버는 새로운 스레드를 생성하고, serivce를 호출한다.  
servie 메소드는 HTTP request 형식(GET, POST, PUT, DELETE 등)을 확인하고 doGet, doPost, doPut, doDelet 등 적절한 메소드를 호출한다.

service 메소드는 요렇게 정의됨.
```java
public void service(ServletRequest req, ServletResponse resp) throws ServeletException, IOException {
    // code...
}
```

service 메소드는 container (tomcat 같은)에 의해 호출되며 service 메소드는 다시, doGet, doPost, doPut, doDelete 등 적절한 메소드를 호출한다.  
그래서 넌 딱히 service 메소드에서 아무것도 할 필욘없다. 다만 doGet, doPost 등 해당하는 메소드를 재정의했다면 말이다.

doGet, doPost는 제일 자주 쓰이는 메소드들이다. 이것에 대해서도 알아보자.

## doGet 메소드
GET 요청은 한 일반적인 URL 요청이나 별도 METHOD 명시가 없는 HTML form에서 오게되며
doGet 메소드가 이것을 처리하게 된다.
```java
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    // codes...
}
```

## doPost 메소드
POST 요청 결과는 POST를 명시한 HTML 형식으로 오게되며,   
doPost 메소드가 이것을 처리하게 된다.
```java
public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
   // Servlet code
}
```

## destroy 메소드
이 메소드는 서블릿의 수명 마지막에 단 한번 호출된다.  
이 메소드는 너의 서블릿에게 DB 연결이나 기타 thread, cookie 목록 등 다양한 자원을 다시 반환할 때 사용하면 된다.  
destroy 메소드가 호출된 후, 서블릿은 이것을 GC에 처리되도록 mark한다.  
이것은 평범하게 생김.
```java
public void destroy() {
    // Finalization code...
}
```

## 설계도
<img src='./img/4_1.jpg' />

- Web Server를 거쳐 HTTP 요청이 온다.
- 서블릿 container는 service 메소드를 호출하기 전에 servlet를 불러온다.
- servlet container는 여러 요청들을 멀티 쓰레드를 만들어서 각 쓰레드에 service 메소드를 호출하여  
각각의 서블릿 인스턴스에 처리하도록 제어한다.
