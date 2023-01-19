# MatchAnalyzer
## How to build and run?
To run application you can run Maven through command-line:
```
mvn spring-boot:run
```
or Maven wrapper (in command-line too):
```
mvnw spring-boot:run
```
However, you can also build and run application through you IDE. 

## Managing application
After running application you have to open your browser and type:
```
localhost:8080
```

After that, you should see page similiar to below:\
![IMG_01](https://imgupload.pl/images/2023/01/20/matchanalyzer_01.png)

From here we can go see all results, that are not ordered:\
![IMG_02](https://imgupload.pl/images/2023/01/20/matchanalyzer_02.png)

We can see that we can type at the browser
```
localhost:8080/event/showAll
```
to get all results.\
If we want to see ordered (by probability) results, simply type
```
localhost:8080/event/show{number}
```
for instance (picture below):
```
localhost:8080/event/show10
```
![IMG_03](https://imgupload.pl/images/2023/01/20/matchanalyzer_04.png)

At the end, we can also want to see all team names ordered alphabetically. To get them, simply type
```
localhost:8080/event/teams
```
at yout browser bar. 

![IMG_04](https://imgupload.pl/images/2023/01/20/matchanalyzer_03.png)
