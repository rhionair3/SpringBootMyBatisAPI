# -------------------------------------------------
# SpringBootMyBatisAPI
# ------------------------------------------------
## Spring-Boot Maven MyBatis Mysql RestAPI 
## -----------------------------------------------

>> ## Required
### ----------------------------------------------

1. Maven 3.5 +
2. Mysql
3. Java JDK 8 +
4. SpringBoot 1.0.5 + (Current Use 2.0.5-SNAPSHOT)
5. Editor (Current Use Visual Studio Code)

>> ## How To
### ----------------------------------------------

1. Clone This Repository To Your Local
2. Chnage The Database (MySQL) Connection In File :  src/main/application.properties
   - server.port = FILL YOUR SERVER PORT TO USE IN LOCAL OR SERVER ( Current Use 8090 )
   - spring.datasource.url = jdbc:mysql://DBHOST:DBPORT/DBNAME ( Current jdbc:mysql://localhost:33067/mybatissb ) Make Sure This Apps Have Access Permission
   - spring.datasource.username = YOUR_DBHOST_USRNAME
   - spring.datasource.password = YOUR_DBHOST_PASSWORD (If your DB Host Using Password / Current use none)
3. If all have been set open current directory app in commandline
4. excecute : mvn clean install
5. If no error, excecute : mvn package spring-boot:repackage
6. Step 5 To Rebuild jar for current project with updated changed
7. Run The Project with execute in command line : mvn spring-boot:run
8. Now we can access via browser : http://HOSTNAME:PORT
9. Why Success With Error Path ?
10. This Project Rest API Using Re-path / define path in UserCntroller File
11. To access all CRUD REST API use below link path :
    - List All : http://HOSTNAME:PORT/api/users
    - List As ID : http://HOSTNAME:PORT/api/users/ID
12. Where The Update, Delete, Insert Link / Path ?
    - The Crud Not Using uri segment 
    - The Crud Using HTTPMethod Request ( For Easy To Secure )

## Check This Out

