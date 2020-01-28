# Simple Java Test project for Clip

Alan Rodriguez Simple Java Test project for Clip.

## Getting Started

These project was build it with Spring Boot and MongoDB. You can compile/run this project with a local instance of MongoDB (the project has a docker-compose file to run mongoDB without complications) or you can compile/run the project only with an internet connection, beacuse the project has a cluster MongoDB configured running on the cloud. See the instructions below to run/compile one or another configuration.

### Prerequisites

These prerequisites are mandatory, regardless of which configuration of the project will be executed.

- Internet connection
- git version control software

The port 8081 needs to open on the server that the project will be deployed. To see if this port is open, you can run this command in your terminal. If you have any problems, you can take a look [to this](https://www.2daygeek.com/how-to-check-whether-a-port-is-open-on-the-remote-linux-system-server/)
```
nc -zvw3 <Server IP> 8081
```
Also you need the JDK 8 running on the server. To see if you have JDK 8 installed, run the following command
```
java -version
```
You will see something like this:
![Java Version](https://4.bp.blogspot.com/-NEe5SaGoB0Y/WN_XWW_HxQI/AAAAAAAABVM/2l6qyAFkQrgeBPh90fXvHXF_rixAeMtCgCLcB/s1600/Screenshot%2Bfrom%2B2017-04-01%2B10-36-53.png)

If you don't have JDK 8 installed, you can download from [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
#### Prerequisites for local build and execuion

If you want to build the project and run it on local instance with mongoDB, you will need these requisites:

-maven 3.5.0: [Here you can download the binaries](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

-docker 1.13.0+ [Here you can found the instructions](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

-docker-compose 1.25 (Compose file format 3.2) [Here you can found the instructions and the repository](https://github.com/docker/compose/releases)

### Downloading the repository

Download the project fom github repository: 

```
git clone  https://github.com/bravocec/alan-rodriguez-sample-java-test.git
```

And go to the project folder.

```
cd alan-rodriguez-sample-java-test/
```

### Compiling and running [Local environment]

Once you have download the project, go to the project folder and run the following command

```
mvn clean package -Plocal
```

The project will start  to compile and run the unit test. Once the project has finished building, you need to start the docker container for the MongoDB before you run the project

So then, go to the folder "docker-files"

```
cd docker-files/
```

And then run the following command

```
docker-compose up --build -d
```

Once the MongoDB docker container has finished deploying, you finllay will be able to run the application. Run the following command to start the application in a backend process

```
cd ..
nohup java -Xms256m -Xmx256m -jar ClipDemoProject/target/ClipDemoProject-1.0-SNAPSHOT &
```

### Compiling and running [MongoDB Cloud Cluster]

If you no have docker on the server/machine where you are building and runing the ClipDemoProject,  you can also compile a version that will use a MongoDB Cluster that is runing on the Cloud

Execute the following command to build the cloud version of the project

```
mvn clean package -Pcloud
```

And then execute the folliwng command to start the application

```
nohup java -Xms256m -Xmx256m -jar ClipDemoProject/target/ClipDemoProject-1.0-SNAPSHOT.jar &
```

### Only run the binaries.

If you only want to run the pre-compiled binaries, run the following commands

#### Run local version.

Note: To run this binary,  make sure the MongoDB docker container is running.

Run the following command

```
nohup java -Xms256m -Xmx256m -jar binaries/ClipDemoProject-1.0-SNAPSHOT-Local.jar &
```

#### Run cloud version.

Run the following command

```
nohup java -Xms256m -Xmx256m -jar binaries/ClipDemoProject-1.0-SNAPSHOT-Cloud.jar &
```

## Test the application

The project has enable Swagger, so you can test the application only with your browser,  or if you want, you can also test the application with software like [Postman](https://www.getpostman.com/downloads/) or [SoapUI](https://www.soapui.org/)

### Swagger UI

Open your browser and enter de following URL

```
http://<IP Server>:8081/clip-demo-project/swagger-ui.html
```

You will see something like this.

![Swagger](https://github.com/bravocec/alan-rodriguez-sample-java-test/blob/master/images/Swagger.png)

Then click on the "transaction-operations-controller"

![TransctionOperations](https://github.com/bravocec/alan-rodriguez-sample-java-test/blob/master/images/TransactionOperations.jpg)

Click on any transaction operation and test it

![TransactionList](https://github.com/bravocec/alan-rodriguez-sample-java-test/blob/master/images/listTransaction.jpg)

### Test the application with other software

If you will use Postman or SoapUI, here are the contracts.

- /transactions/add [POST]

```
{
  "amount": <double>,
  "date": "string",
  "description": "string",
  "user_id": <int>
}
```

- /transactions/list [GET]

```
?user_id=<int>
```

- /transactions/randomTransaction [GET]

```
no params
```

- /transactions/report [GET]

```
?user_id=<int>
```

- /transactions/show [GET]

```
?user_id=<int>&transaction_id=<int>
```

- /transactions/sum [POST]

```
?user_id=<int>
```