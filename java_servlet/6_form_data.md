# Servlets - Form Data  
웹 브라우저에서 웹 서버 그리고 너의 backend 프로그램까지의 정보들이 전송되는 과정을 넌 반드시 이해된 상태여야 한다.  
브라우저는 웹서버로 정보를 통과하기 위한 2개의 메소드들을 사용한다. (GET, POST method)

## GET Method  
GET 메소드는 encode되어 첨부된 유저 정보를 요청한 페이지에 전송한다.  
그 페이지와 encode된 정보는 ?로 분리되어 있다.  
아래와 같다.
    http://www.test.com/hello?key1=value1&key2=value2

GET 메소드는 브라우저의 url 입력창에 보여지는 하나의 긴 문자열이며 웹서버에 전달되는 기본 메소드이다.  
민감한 정보나 패스워드와 같은 것을 절대로 GET 메소드를 이용하지마라.  
GET 메소드는 단지 1024 문자만 이용될 수 있다.

이 정보는 QUERY_STRING이라는 헤더를 사용하며 전달 되었고, QUERY_STRING 환경 변수를 통해 접근될 수 있고, 서블릿은 이러한 요청을 doGet 메소드에서 제어할 수 있다.

## POST Method  
백엔드 프로그램에 전달하는 일반적으로 더 믿을만한 메소드는 POST 메소드다.  
이것은 GET 메소드와 같은 방식으로 정보들을 묶지만, URL에 긴 메시지를 포함하지는 않는다.

이 메시지는 너가 처리하기 위해 분석하고 사용될 수 있도록 기본 입력 형식으로 백엔드 프로그램에 온다.

서블릿은 이런 요청을 doPost 메소드를 통해 제어한다.

## 서블릿에서 Form Data 읽기  
서블릿은 method 상황에 따라 자동으로 form 데이터를 분석을 제어할 수 있다.
- getParameter() - request 개체에서 호출하여 form 매개변수를 얻을 수 있다.
- getParameterValues() - 매개변수에서 보여주는 1개 이상의 값들을 보여준다.
- getParameterNames() - 요청에 사용된 매개변수 이름 목록을 받는다.

## Get 메소드 예제

```java
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class HelloForm extends HttpServlet {
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "Using GET Method to Read Form Data";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
         
      out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + title + "</h1>\n" +
               "<ul>\n" +
                  "  <li><b>First Name</b>: "
                  + request.getParameter("first_name") + "\n" +
                  "  <li><b>Last Name</b>: "
                  + request.getParameter("last_name") + "\n" +
               "</ul>\n" +
            "</body>" +
         "</html>"
      );
   }
}
```

위 예제는 HelloForm을 클래스를 정의하여 Get Method에  
html 문서를 보이도록 한 소스코드이다.  
url에서 들어온 매개변수를 읽어, 페이지에 표시할 수 있도록  
request의 getParameter를 이용하는 모습을 볼 수 있다.

이후, web.xml에 아래 서블릿 정보를 추가하면 실행해볼 수 있다.

```xml
<servlet>
   <servlet-name>HelloForm</servlet-name>
   <servlet-class>HelloForm</servlet-class>
</servlet>

<servlet-mapping>
   <servlet-name>HelloForm</servlet-name>
   <url-pattern>/HelloForm</url-pattern>
</servlet-mapping>
```

## Form을 이용한 Get Method 예제

```html
<html>
   <body>
      <form action = "HelloForm" method = "GET">
         First Name: <input type = "text" name = "first_name">
         <br />
         Last Name: <input type = "text" name = "last_name" />
         <input type = "submit" value = "Submit" />
      </form>
   </body>
</html>
```

위는 HTML Form과 submit 버튼을 사용하는 간단한 예제이다.  
이것으로 입력 값을 다뤄볼 것이다.

form 태그의 action은 이 폼을 제출할 path url을 뜻 한다.  
즉, /HelloForm에 전달될 것임.  

## Form을 이용한 POST Method 예제  
위의 html에서 button으로 제출하게 되면, Post method로 servlet에 오게 된다.

