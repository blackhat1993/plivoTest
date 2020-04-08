# Software Development Engineer in Test Api Assignment

This file contains the detailed steps to execute the Test Cases.

This is a Maven project.

  1. Programming language used: Java
  2. Api Automation Framework: RestAssured
  3. Unit Testing Framework: TestNg


The following dependencies are added in pom.xml file

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>ApiAutomationFramework</groupId>
  <artifactId>ApiAutomationFramework</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <dependencies>
  
  	<!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
	<dependency>
    	<groupId>io.rest-assured</groupId>
    	<artifactId>rest-assured</artifactId>
    	<version>3.3.0</version>
	</dependency>
  	
  	<!-- https://mvnrepository.com/artifact/org.testng/testng -->
	<dependency>
    	<groupId>org.testng</groupId>
    	<artifactId>testng</artifactId>
    	<version>6.14.3</version>
	</dependency>
	
		<!-- https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple -->
	<dependency>
    	<groupId>com.googlecode.json-simple</groupId>
    	<artifactId>json-simple</artifactId>
    	<version>1.1.1</version>
	</dependency>
		
  </dependencies>
</project>


File config.properties contains the data to be used by the test cases.


  1. baseUri = https://slack.com
  2. token = xoxp-1041863667649-1041863668625-1045047477287-797c0599919d0cacaa499c2a89fdc92f
  3. channel.name = channel15
  4. channel.name.new = channel16


Enum Class Uri.java contains all the Uris

      CHANNEL_CREATE ("/api/channels.create"),
	  CHANNEL_JOIN ("/api/channels.join"),
	  CHANNEL_RENAME ("/api/channels.rename"),
	  CHANNEL_ARCHIVE ("/api/channels.archive"),
	  CHANNEL_LIST ("/api/channels.list"),
	  CHANNEL_INFO ("/api/channels.info");
    

Test Classes:

1. BaseTest.java:
    
      1. To read the data from the properties file
      2. To validate whether auth token is provided or not
      
2. PlivoTest.java:
    
      1. It extends BaseTest.java
      2. Test case to Create a new Channel
      3. Test case to Join the newly created Channel
      4. Test case to Rename the Channel
      5. Test case to List all Channels and Validate if the Channel name has changed successfully
      6. Test case to Archive the Channel
      7. Test case to Validate if the Channel is archived successfully
      
      

To test the full scenario provide the data in config.properties file and run PlivoTest.java class as TestNg Test.

