# Servlets - 예제
서블릿은 Servlet 인터페이스를 구현하여 HTTP 요청을  처리는 자바 클래스다.  
웹 app 개발자는 일반적으로 HTTP 요청을 처리하도록 설계된 추상 클래스인 HttpServlet을 상속하여 클래스를 작성한다.

## 샘플 코드
아래는 Hello World를 보여주는 서블릿의 구조 샘플이다.


```java
// HelloWorld.java
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
   private String message;

   public void init() throws ServletException {
      // Do required initialization
      message = "Hello World";
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>" + message + "</h1>");
   }

   public void destroy() {
      // do nothing.
   }
}
```

## 서블릿 컴파일하기
위에 보여진 HelloWorld.java를 만들어보자.  
적당한 위치에 파일을 옮기고, CLASSPATH를 추가해야한다. (서블릿을 import 할수 있도록)  
너의 환경이 적절히 셋팅 되었다고 가정한다.  그러면 아래 명령어를 수행 시, 문제 없이 동작한다.
```
$ javac HelloWorld.java
```

만약 서블릿이 다른 라이브러리를 의존한다면, 너는 그 jar 파일들 역시 CLASSPATH에 적절히 포함시켜야한다.  
여기서는 단지 servlet-api.jar만 포함했다 왜냐면 이 소스 코드는 다른 것을 사용하지 않고 있거든.

이 command line은 내장된 JDK의 javac 컴파일러를 쓰는 것이다.  
이 명령어가 적절히 동작하려면, 너는 java SDK 역시 적절히 PATH 환경 변수에 설정해야 한다.

만약 모든 것이 잘 되었다면, 같은 폴더에 helloWorld.class 파일이 생성된다. 다음 섹션을 제품에 deploy하는 법을 설명할 것이다.

## Servlet 배포
기본적으로, servlet app은 tomcat 경로의 /webapps/ROOT에 위치한다.  
그리고 class 파일은 /webapps/ROOT/WEB-INF/classes에 위치해야 할 것이다.

만약에 너가 com.myorg.MyServlet이란 이름의 class 파일이 있다면, 이것은 WEB-INF/classes/com/myorg/MyServlet.class 위치에 있어야 한다.

지금은, 단지 HelloWorld.class를 webapps/ROOT/WEB-INF/classes에 복사해서 넣고, 아래에 맞게 web.xml 파일을 수정하다. (xml 파일은 WEB-INF 폴더에 있다.)
```xml
<servlet>
   <servlet-name>HelloWorld</servlet-name>
   <servlet-class>HelloWorld</servlet-class>
</servlet>

<servlet-mapping>
   <servlet-name>HelloWorld</servlet-name>
   <url-pattern>/HelloWorld</url-pattern>
</servlet-mapping>
```

위의 xml entry들을 <web-app> 안에 넣다.  
다른 entry도 많을건데 신경 쓸 필욘 없다.

거의 다 끝났다. 이제 tomcat 폴더의 bin 폴더에 있는 startup.bat을 실행하다. (끌땐, shutdown.bat 실행)  
이제 http://localhost:8080/HelloWorld가 정상 동작되는 것을 볼 수 있다!