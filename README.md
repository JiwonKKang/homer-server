## Homer

MLB 야구 데이터를 이용한 야구 시뮬레이션 게임입니다.<br>
자신만의 야구팀을 꾸려 다른 유저의 스쿼드와 겨뤄볼수있습니다.

## Stack
Front : Veiw.js  
Server: Spring Boot, Java, JDBC Template, MySQL  
AWS : AWS RDS  
Tools : Azure DevOps  


## Project Introduction

> ### Main
![image](https://github.com/JiwonKKang/homer-server/assets/128073698/73511e4d-f3f7-4de8-8e42-91eae56f68a2)

> ### Create Squad
![image](https://github.com/JiwonKKang/homer-server/assets/128073698/a748d1af-3db0-4243-8713-7f6e4cf5f2d3)

> ### Search Player
![image](https://github.com/JiwonKKang/homer-server/assets/128073698/bd1eac2d-2326-4295-b4f2-8dd1c83c62be)

> ### Update Squad
![image](https://github.com/JiwonKKang/homer-server/assets/128073698/4fb1d705-b116-437d-b9ca-f7933aab5a60)

> ### Match Result
![image](https://github.com/JiwonKKang/homer-server/assets/128073698/c249ff78-3546-4042-b52a-046ebe065964)

## 느낀점

- JDBC Template에서는 쿼리마다 일일이 결과에 매핑되는 RowMapper를 만들어주어야했다. 때문에 너무 마구잡이로 쿼리결과에 매핑되는 클래스를 만들다보니 재사용성도 없는 클래스가 너무 많아졌다.
- JPA떄는 Bulk 연산이 어려웠지만(saveAll을 통한 batch insert와는 다른거같음) JDBC Template을 통해 쿼리를 짜서 손쉽게 Bulk 연산을 할수있어 성능상에서 이점을 가져갔다.
  
## 개선 사항

- 야구 선수 정보는 자주 변하지않고 자주 조회하는 데이터 -> Redis 캐싱을 통해 검색 성능 향상 기대
