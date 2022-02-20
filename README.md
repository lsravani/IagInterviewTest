# Getting Started
The application exposes following endpoint (http://localhost:8080/person/{name}) ex: http://localhost:8080/person/michael
 to provide age , gender and nationality of given name.
 
  ####Building and Deploying the application
 The project uses Maven as a build tool. It already contains .mvn wrapper script, so there's no need to install maven.
 

 
 ####Running the application                       
 Create the image of the application by executing the following command:
 
 ```bash
  mvn spring-boot:run
 ```
 ####Unit TestCase Execution             
  Execute the below maven command to run the testcases inside the project
  
   ```bash
    mvn test
   ```

   You should get a response similar to this:
  ```bash
   {
   "age": "70",
   "gender": "male",
   "nationality": "US"
   }
 ```


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)

