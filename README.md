# PWPRestAPI
Programmable web, University Of Oulu Course Project
Description:
This readme file contains the description of the technologies used in development the database api and also describe the steps to deploy the project. This document also contains the references from where concepts are taken for the development. 

Key Technologies:

1. Java EE
2. Hibernate 4.2.0.Final
3. Spring MVC 3.2.0.RELEASE
4. MySQL 5.6.17
5. Maven
6. TestNG 6.8.7
7. Selenium 2.39.0
8. Tomcat v7.0

Project Settings:
Note: The project has been developed using Eclipse IDE so the settings are related to the IDE.
1. Project Deploement:
	1. This is a maven project so in order to run the project it only needs to be imported using "Existing Maven Projects".
	2. Tomcat server needs to be configured with the IDE to run the appplication.
2. Database Settings:
	This Database connectivity with MySQL is ensured using Mysql Connector 5.1.21. The database settings could be found under the project in servlet-config.xml file.
3. Database API Testing:
	3.1 TestNG plugin needs to be installed in the IDE (In my case eclipse under Eclipse marketplace) in order to run the tests.
	3.2 The sample data that actually is used to compare the result is according to the database records(.sql file) that is attached with the confluence. If the data is changed than the sample data to compare also need to be changed in the test files. The files are marked with version the latest file is marked with the latest version.
	3.3 In order to run the tests after installed the TestNG plugin right click on the individual tests that resides under src/test/java/com/comag10/crowdflower. So, Right Click on the test -> Run as ->TestNG Test.
4. Database Population:
	In order to populate the database Install a MySQL client e.g., HeidiSQL and import the database .sql file.
	
Note:
1. I have used Maven in this project so all the dependencies are in the pom.xml file along with the version specified per dependency.
2. There is my thesis project template that resides under github (link has been mentioned in references), I have reused this template to create the project instead of spending time in to creating the template for the project.
3. This project contains a folder named database in which psoas.sql file resides through which the database and its data can be imported.
4. The project's base url is http://{host}:port/PWPRestAPI/api/v1/ and then it continues with the resources which are as follows:

	4.1 /announcements (Mehtod = GET)
	4.2 /usersettings/user/{id} (Method = GET)
	4.3 /category/{catId}/contest/{id} (Method = GET)
	4.4 /contest/{id}/votes/ (Method = GET)
	4.5 /category/{id}/contests/ (Method = GET)
	4.6 /announcement/ (Method = POST)
	4.7 /contest/ (Method = POST)
	4.8 /vote/ (Method = POST)
	4.9 /updatesetting/user/{userId}/setting/{settingId}/status/{status}/ (Method = PUT)
	
	