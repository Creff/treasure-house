### Kerberos
it shows methods to connect to DB with kerberos
#### connect to impala with java
system parameter :
-Djavax.security.auth.useSubjectCredsOnly=false 
-Djava.security.auth.login.config="C:\Program Files\workenvironment\jdk1.7.0_51_64bit\jre\lib\security\jaas.conf" 
-Djava.security.krb5.conf="C:\Program Files\workenvironment\jdk1.7.0_51_64bit\jre\lib\security\krb5.conf"
#### notice
where build java code in eclipse, please use jdk or jre which include jaas.conf and krb5.conf. 
##### for example:
 jaas.conf, krb5.conf and other configuration about kerberos are saved in xxxx/xxxx/xxx/jre
 loacaiton of jre system library must be setted to jre location above in eclipse
