# HTTP - Parameters  
중요한 HTTP 프로토콜 매개변수(Parameter)에 대해 다룸.  
기것들의 문법, 통신에 사용된 방법 등을 배운다.  
예를들어, 날짜 형식, URL 형식 등등..  
이것은 HTTP 클라이언트나 서버가 요청과 응답 메시지를 동안 만드는 것을 도울 것임.  
차후 배우게될 메시지 구조에 대한 매개변수들의 완벽한 사용법을 보게될 것이다.  

## HTTP Version  
HTTP는 <major>.<minor> 라는 구조로 번호를 부여하여 버전을 명시한다.  
HTTP 메시지의 버전은 HTTP-Version 필드 가장 첫 줄에 표기 되어있다.  
HTTP 버전을 명세하는 전체적인 문법이다.

```
HTTP-Version = "HTTP" "/" 1*DIGIT "." 1*DIGIT  
HTTP/1.0  
or  
HTTP/1.1
```

## Uniform Resource Identifiers(URI)  
URI는 간단히 형식화 되었고, 대소문자를 구분하지 않고, 이름, 위치 등 문자열을 포함한다.  
이것은 자원을 식별하기 위해서 사용하는데  
예를 들면 웹사이트나 웹서비스 등을 식별한다고 생각하면 된다.  
URI의 일반적인 문법은 아래를 따른다.  

```
URI = "http:" "//" host [ ":" port ] [ abs_path [ "?" query ]]
```

port가 만약 비었다면 80을 기본으로 가정하여 사용한다.  
abs_path가 비었다면 그냥 /을 쓰는 것과 똑같다.  
예약어와 안전하지 않은 집합은 '%'로 HEX HEX encoding쓰면 된다.  

`Example`  
아래 목록은 모두 똑같은 URI를 나타내는 예시들이다.  
%7은 ~을 나타낸다.
```
http://abc.com:80/~smith/home.html
http://ABC.com/%7Esmith/home.html
http://ABC.com:/%7esmith/home.html
```

## Date/Time Formats  
모든 HTTP date/time stamp는 예외 없이 GMT(Greenwich Mean Time)로 나타내야 한다.  
HTTP 앱은 아래와 같은 3가지 형식을 따른다.  
```
Sun, 06 Nov 1994 08:49:37 GMT   ; RFC 822, updated by RFC 1123
Sunday, 06-Nov-94 08:49:37 GMT  ; RGC 850, obsoleted by RFC 1036
Sun Nov  6 08:49:37 1994        ; ANSI C's asctime() format
```

## Character Sets  
문자열 셋은 기본적으로 ASCII를 사용한다.  
예를 들어 아래와 같음.  
```
US-ASCII
or
ISO-8859-1
or
ISO-8859-7
```

## Content Encodings  
콘텐트 인코딩 값은 콘텐트가 네트워크를 통과하기 전에 사용된 인코딩 알고리즘을 보여준다.  
콘텐트 코딩은 주로 문서가 압축되거나 유용하게 변형된 형태를 사용할 수 있게 사용되었기 때문.  

모든 content-condig 값들은 대소문자를 구분하지 않는다. (case-insensitive)  
HTTP/1.1은 Accept-Encoding과 Content-Encoding 헤더 필드 둘다 콘텐츠 코딩 값으로 사용한다.  

`Example`
```
Accept-encoding: gzip
or
Accept-encoding: compress
or
Accept-encoding: deflate
```

## Media Types  
HTTP는 `Content-Type`과 `Accept` 헤더 필드로 인터넷 미디어 타입을 사용한다.  
이것은 확장적인 데이터 타이핑과 type negotiation을 제공하기 위해서 사용함.  
모든 미디어 타입 값들은 IANA(Integer Assigned Number Authority)에 등록되어 있다.  

media type은 아래의 예를 따른다.  
```
media-type = type "/" subtype *(";" parameter)
```

`Example`
```
Accept: image/gif
```

## Language Tags  
HTTP는 `Accept-Language`와 `Content-Languag` 필드를 언어 태그로 사용한다.  
언어 태그는 1개 이상으로 지정될 수 있고,  
가능하면 주 언어를 먼저 태그하고 그 다음에는 다른 언어를 태그한다.  
```
language-tag = primary-tag *("-" subtag)
```

`Example`
```
en, en-US, en-cockney, i-cherokee, x-pig-latin
```

en-US  
여기서 앞의 en은 ISO-639 언어 약어이고,  
뒤의 US는 ISO-3166 국가 코드이다.
