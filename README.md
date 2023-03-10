# 파일 확장자 차단 과제

## description

- projectName : fileextension
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
	implementation 'com.h2database:h2'
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
