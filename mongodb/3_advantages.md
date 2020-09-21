# MongoDB - Advantages

어떤 관계형 DB는 테이블과 관계된 것들을 보여주는 전형적인 schema 설계를 가진다.  
MongdoDB에서는 관계에 관련한 개념이 없다.

## RDB 대비 MongoDB의 장점

- Schema Less  
  MongoDB는 document DB로, 하나의 collection이 각기 다른 document를 보관한다.  
  필드의 수, 콘텐츠, 문서의 크기는 document에 따라 다를 수 있다.
- 단일 개체의 구조가 명확하다.
- 복잡한 join이 없다.
- 깊은 query-ability. MongoDB는 동적 쿼리를 지원한다. (SQL만큼 강력한 질의 언어 기반으로 문서 기반의)
- 튜닝
- 쉬운 확장 - MongoDB는 확장이 쉽다.
- app object에서 db object로의 변환/매핑 과정이 필요 없다. (json)
- working set을 적재하기 위해 내부 메모리를 사용함 (데이터 접근에 빠름)

## 왜 MongoDB를 쓰는가?

- Document Oriented Storage(문서 지향 저장소) - 데이터는 JSON 스타일로 적재된다.
- 어떤 attribute든 index
- Replication(Master/Slave 저장 방식), 고가용성

Master : 수정사항 반영  
Slave : 실제 데이터 복사

- Auto_Sharding
- Rich queries
- Fast in-place updates
- MongoDB 회사의 전문가 지원

## 어디서 MongoDB를 쓰는가?

- Big Data
- Content Management and Delivery
- 모바일, Social Infrastructure
- User Data Management
- Data Hub
