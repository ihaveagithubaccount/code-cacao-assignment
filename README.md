# code-cacao-assignment

After you've imported this project from git, configure it in into Maven project.
I've attached DB dump for this project which you can use. The tables can also be automatically created if we add spring.jpa.hibernate.ddl-auto = create in the properties file but this way we already have some data to work with and to to the test. (the root and password in the config file should be changed accordingly to your local settings )I recommend starting the app and then running the tests because some of them are dependent on data from the DB. I run the tests with mvn clean install, and for building the app without tests just go mvn install -DskipTests. In order for some of the tests to work we need the application to be running.

I created my own API, based on the mock api you provided http://private-f0e1c9-fleetchapi.apiary-mock.com/. (I have also tried using the mock api and i send you also the requests i tried with postman)

I have implemented the following requests:
http://localhost:8080/user
http://localhost:8080/user/id
http://localhost:8080/registerUser
http://localhost:8080/updateUser/id
http://localhost:8080/deleteUser/id

All these requests depend on the token they need to have in the header, we get this token on requesting http://localhost:8080/login, post method where we provide {"username":"admin","password":"password"} in the body. The result is a token which we use in the header for any of the request i've written above.

