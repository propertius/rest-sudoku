SUDOKU
=======
Simple REST Web Service implemented with Java and Spring that creates random Sudoku boards, validates successive moves and recognizes a finished Game.
### Life cycle Management
Dependency management and task execution can be performed both with Gradle or Maven (version 3+). The following steps are executed with Gradle.

To run the application
```shell
$ ./gradlew bootRun
```
In order to build a single executable JAR
```shell
$ ./gradlew build
```
Execute the jar
```shell
$ java -jar build/libs/sudoku-0.1.0.jar
```
Run tests
```shell
$ ./gradlew test
```
## API Design
#### Get random board
In order to obtain a random Sudoku board ready to be played, one should issue the following `GET` request
```shell
GET /sudoku
```
the response will be a JSON object like the following:
```shell
[
    [0, 0, 0, 8, 0, 0, 0, 0, 3],
    [0, 2, 4, 1, 3, 7, 5, 0, 0],
    [0, 9, 8, 5, 6, 0, 0, 0, 7],
    [0, 0, 0, 0, 7, 6, 8, 0, 0],
    [0, 4, 0, 0, 5, 0, 0, 0, 6],
    [0, 0, 0, 0, 1, 0, 4, 2, 5],
    [0, 7, 3, 6, 0, 0, 0, 0, 0],
    [1, 0, 9, 0, 0, 0, 0, 0, 0],
    [0, 6, 0, 0, 9, 0, 0, 0, 8]
]
```
#### Validate Move
The next `POST` request will validate a move. The body of the request should contain a JSON object, with the same format as the one returned from the `GET` request above.
```shell
POST /validate
```
The possible outcomes are either `true` or `false`.
#### Validate Game End
Finally, the API offers a way to find out whether a Game is finished or not. Just like the request above that validates a move, it takes a JSON object as the body of the request, and returns a `boolean` with the response.
```shell
POST /finished
```