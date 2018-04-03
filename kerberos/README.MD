### Kerberos
it shows methods to connect to DB with kerberos
#### connect to impala with java
system parameter :
-Djavax.security.auth.useSubjectCredsOnly=false 
-Djava.security.auth.login.config="C:\Program Files\workenvironment\jdk1.7.0_51_64bit\jre\lib\security\jaas.conf" 
-Djava.security.krb5.conf="C:\Program Files\workenvironment\jdk1.7.0_51_64bit\jre\lib\security\krb5.conf"
