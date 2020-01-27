# Project Title

Alan Rodrigeuz Simple Java Test project for Clip.

## Getting Started

These project was build it with Spring Boot and MongoDB. You can compile/run this project with a local instance of MongoDB (the project has a docker-compose file to run mongoDB without complications) or you can compile/run the project only with an internet connection, beacuse the project has a cluster MongoDB configured running on the cloud. See the instructions below to run/compile one or another configuration.

### Prerequisites

These prerequisites are mandatory, regardless of which configuration of the project will be executed.

```
Internet connection
```
To download the project.
```
git version control
```
The port 8081 needs to open on the server that the project will be deployed. To see if this port is open, you can execute this command on yor terminal. If yo have any problems, you can take a look [to this](https://www.2daygeek.com/how-to-check-whether-a-port-is-open-on-the-remote-linux-system-server/)
```
nc -zvw3 <Server IP> 8081
```
Also you need the JDK 8 running on the server. To see if you have JDK 8 installed, run the follow command.
```
java -version
```

#### Prerequisites

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

