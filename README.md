# 파일 확장자 차단 과제

## description

- projectName : fileextension
- address : 3.37.18.8:8080/fileextension
- git branch :

```
master
ㄴ develop
    ㄴfeature/create-entity
    ㄴfeature/block-fileextension
```

- dependency

```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'com.mysql:mysql-connector-j:8.0.31'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'javax.servlet:jstl'
	implementation "org.apache.tomcat.embed:tomcat-embed-jasper"
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

```

- Tech Stack : JAVA 11, SpringBoot 2.7.9, MySQL8, SpringDataJPA, JQuery

---

- ERD
    ![파일확장자erd](https://user-images.githubusercontent.com/80368511/224299465-61a0f99e-b540-43f3-aed6-2a367421b103.PNG)

---

- Architecture : layered architecture

```
ㄴ flow
  ㄴ controller
  ㄴ dto
  ㄴ entity
  ㄴ repository
  ㄴ service
  ㄴ util

```

- consideration : 
   + 고정확장자 체크박스를 클릭할 때 DB에 추가/삭제 되는것이 아니라 기존 데이터의 보이기 컬럼을 update했습니다. 고정확장자는 이미 초기에 모두 존재한다고 가정했습니다.  
   + 커스텀확장자 X버튼을 눌렀을 때 delete하는것이 아니라 보이기 컬럼을 update합니다.  
 그 이유는 지워진 데이터의 기록을 따로 하지 않고 이미 사용자가 다시 입력한 확장자를 다시 사용할 수 있기 때문입니다. 만약 delete를 하고 다시 해당 확장자를 추가하지 않으면
 괜찮지만 다시 사용한다고 하면 비효율적이기 때문입니다. 
   + 예외처리같은 경우 요구조건의 범위(200개 이상 추가 불가)나 다른 문제사항을 생각하여 해결하였습니다. 
   
- 최종 화면
![실행화면](https://user-images.githubusercontent.com/80368511/224419117-5790bd4d-9c87-4417-944c-bdbb5af323ef.PNG)
