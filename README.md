# hashmapbroker


Create a target space in your pivotal account else use default 'development' space.

Steps to deploy the service on cloudfoundry.

1.git clone https://github.com/amit28/hashmapbroker.
2.run command "mvn clean install -DskipTests" in the project root directory.
3.cf push --random-route.
It will fail as rquired db services is not binded yet. Add service "ClearDB MySQL Database" from marketplace in the target space and create service key for it and also bind it with the application. Copy the service key content and update application.properties file under src/main/resources folder with the db credentials.
Now repeat step 2 and 3 again.

Application will be deployed on cloudfoundry and can be seen in the target space of your choice in pivotal.

Using Postman rest client application can be tested.
