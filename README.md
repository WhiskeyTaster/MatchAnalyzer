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
![IMG_01](https://imgupload.pl/images/2023/01/24/matchanalyzer_01.png)

First, you have to load data. To do that, simply click on the "Load data" button. \
After that, data will be loaded from data.json file and data from file will be printed:
![IMG_02](https://imgupload.pl/images/2023/01/24/load_data.png)

You can load your own data (if file exists at src/main/resources/data/ folder) simply by use
```
localhost:8080/event/load_{fileName}
```
for instance 
```
localhost:8080/event/load_shortdata
```

If file not exists or data was not loaded properly, then message will be displayed:\
![IMG_03](https://imgupload.pl/images/2023/01/24/matchanalyzer_06.png)

Remember: if you previously loaded data successfully and then you have tried to load data from not existing file (or corrupted), then you will keep data from last succeeded attempt. 

If no data has been loaded successfully, then you will see short message:\
![IMG_04](https://imgupload.pl/images/2023/01/24/not_loaded.png)

You can display loaded data by command
```
localhost:8080/event/show
```

If we want to see ordered (by probability) results, simply type
```
localhost:8080/event/show{number}
```
for instance
```
localhost:8080/event/show10
```
will result with\
![IMG_05](https://imgupload.pl/images/2023/01/20/matchanalyzer_04.png)

At the end, we can also want to see all team names ordered alphabetically. To get them, just type
```
localhost:8080/event/teams
```
at yout browser bar. 

![IMG_06](https://imgupload.pl/images/2023/01/20/matchanalyzer_03.png)
