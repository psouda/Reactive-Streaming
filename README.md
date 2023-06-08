# Data Harvester

It is a program that harvests real-time usage information about the users of a video streaming events' server. It consumes streaming endpoints exposed by the video streaming events' server and collects the data from all three streams for a specific duration or until the third occurrence of a user with first name Sample1 on either of streams, whichever comes first.

## Description

The application is developed with Java and Spring Boot, utilizing the WebFlux framework for reactive programming. It uses WebClient to connect to and consume the event streams. The application aggregates the events and exposes an API endpoint to fetch the aggregated data.

The application also features a custom stop condition mechanism which will stop the streaming once the desired condition is met.

## Technologies Used
- Java 17
- Spring Boot
- Spring WebFlux
- Docker
- Maven

## How to Run
1. Make sure you have Docker, Java 17 and Maven installed in your system.

2. Clone the repository and navigate into the project directory.

3. build the project:
```bash
./mvn clean install
```
4. From the data-harvester module folder run: 

```bash
docker-compose up --build --force-recreate
```


## API Usage
The application exposes the following endpoint:

- `GET /user-events` - Fetches the aggregated view of the data collected. The data includes user id, user's name and surname, user's age, all the events that the user has executed, platform where each event has occurred, the show titles, the first person in the cast for each show (if present), the show ids, and event time in Amsterdam (CET) timezone and dd-MM-yyyy HH:mm:ss.SSS format.

## Notes
The application will start harvesting data as soon as it is launched. The process will stop after 20 seconds or once the third user with first name "Sample1" is encountered, whichever comes first. If you wish to modify this behavior, you can adjust the settings in the application.yml file.