## Introduction

spring boot template project  

## Start service guide
install java 8 ,gradle 4.0. redis .
then 
$ cd path/just_user
$path/gradle-4.0/bin/gradle bootRun -Dfile.encoding=UTF-8

if no error,then access http://localhost:8080/api/v1/apiDoc  which page show api doc.

## Auth guide
after call http://serverUrl/api/v1/auth/ajaxLogin  ,server return x-auth-token in header , client add x-auth-token in http request header to keep session and auth info.


# Main model
spring boot : security,session,redis, jpa ,session,H2,Mysql,thymeleaf
http://start.spring.io/

springfox-swagger2 (online doc and test api):  https://github.com/springfox/springfox 


## Add groovy
if you like Groovy,then you can add <br>	
compile('org.codehaus.groovy:groovy:2.4.2') <br>
to build.gradle
eclipse groovy plugin address：https://github.com/groovy/groovy-eclipse/wiki 


## Spring loaded
spring loaded http://wiselyman.iteye.com/blog/2146475<br>
-javaagent:/home/asdtiang/workspace-sts-3.6.3.SR1/study/devLib/springloaded-1.2.3.RELEASE.jar -noverify
‪E:\study\devLib\springloaded-1.2.3.RELEASE.jar

## Dev start
Run org.asdtiang.ju.JustUserApplication


## Gen production 
gradle clean war   -Dfile.encoding=UTF-8  
 





 
