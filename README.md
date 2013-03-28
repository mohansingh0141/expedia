Author : Mohan Singh
=======

Expedia Challenge

-----------------------
Steps to Compile,Test and Deploy this Spring MVC Application (Tested on Tomcat7 / ubuntu ):

(After Deployment Application URL will be : "http://localhost:8080/springcode/home" )

1. Clone This Repo onto your system.
2. Go to the Directory where pom.xml for this project is located (expedia/springcode/pom.xml).
3. Run "mvn clean" command.
4. Run "mvn test" command to run junit test cases.
5. Run "mvn package" command to build the war file.
6. Run "mvn tomcat:deploy" to deploy the war in your servlet controller. (Assuming Server Already Running )
   Please Note that This application expects Tomcat as the server.
   This application was tested with following entries in "tomcat-users.xml" (your_tomcat_home/conf/tomcat-users.xml)
   =>

	<tomcat-users>
		<role rolename="manager" />
		<role rolename="manager-gui" />
		<role rolename="manager-script" />
		<role rolename="admin" />
		<user username="admin" password="password" roles="admin,manager,manager-gui,manager-script" />
	</tomcat-users>
	
   => Application Assumes that Tomcat7 server is running on "localhost" at port "8080". If you need to change the entires please
   update pom.xml entry for deployment.

   => Please Make sure you have respective server entry in your Maven settings.xml file. This application was tested
      Using following entries repectively
	--> In pom.xml

		<plugin>
			<groupId>org.codehaus.mojo</groupId>
			<artifactId>tomcat-maven-plugin>
			<configuration>
				<url>http://127.0.0.1:8080/manager/text</url>
				<server>Tomcat7</server>
			</configuration>
		</plugin>
	--> Respective Entry in Maven Settings file (settings.xml)
		Add follwoing line in "servers" section of the file if not already specified: 

			<server>
				<id>Tomcat7</id>
				<username>admin</username>
				<password>password</password>
			</server>

7. Other way of deploment :
	=> Take the war generated in step 5 from "springcode/target" directory and deploy directly into tomcat webapps
	   directory and restart tomcat.

8. Access The Application At following URL :

	http://localhost:8080/springcode/home
		