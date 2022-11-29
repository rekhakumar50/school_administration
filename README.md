# school_administration
1. Instructions to run in local instance
	* Create DB in MySQL: "create database school_administration;"
	* update userName and password of DB in application.properties which is in project resource classpath
	* To Run From IDE:  
      		1. Go to com.example.demo.SchoolAdministrationApplication.class and run as Java Application
	* To start application from Maven: 
     		1. After cloning the repo, go to the project folder 
     		2. run "mvn install" to build the application 
     		3. once build is success, go to the target folder of the project and run the command "java -jar school_administration-0.0.1-SNAPSHOT.jar"
2. Used MySQL as the database
3. Added logs for each trations using Logger
4. Used Exception Handling for Errors
5. Implemented secondary level Cache (EhCache has been used)
6. Covered Junit for Controller, Service and Repository classes
7. Attached postman collections for the API's in E-Mail


## DB Design (Entity Relationship Diagram – ERD):

![image](https://user-images.githubusercontent.com/68632132/204479460-252a578d-2953-4afa-9fe1-124450a1077d.png)

## Class Design:

![image](https://user-images.githubusercontent.com/68632132/204479486-011e177a-534f-4a14-938e-93943bfbcb8e.png)
