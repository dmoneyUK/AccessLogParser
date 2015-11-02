AccessLogParser
======

A Java application which can read a web access log file from given path and display the request information in the standard output.

Run Application
--------
As a maven project, you can download the source code and execute "mvn install" to generate the jar file. 
If you don't have maven on you environment, you can use the "LogAnalyzer-1.0.0-jar-with-dependencies.jar" file in the root path.

####Option 1:
Run the application with a file path as the parameter, go to command line prompt and execute:
```
    java -jar LogAnalyzer-1.0.0-jar-with-dependencies.jar access.log
```

####Option 2:
Run the application without a file name and then input the file path in the prompt by following the guide.
```
	java -jar LogAnalyzer-1.0.0-jar-with-dependencies.jar
	Please give a correct path the log file to be analyze:
	access.log
```

Note: Please modify the command above to point to the correct path of the "access.log" in you environment.

Expected Result
--------
The application will read the log file and display the requests information in time order. The requests timestamp are 
converted to minute level and in UTC +0100 time zone to keep consistent. 

####Example Snippet:
    30/Mar/2015:10:11 +0100
    Number of successful request per minute: 1489
    Number of error request per minute: 840
    Mean response time per minute: 410190486
    MBs sent per minute: 12.786433219909668

Logs
========
This application uses slf4j and logback. By default, the log is in INFO level. You can also change the log level by define your own
logback.xml file.
If you want to use your own logback.xml file, use the command below:
```
    java -Dlogback.configurationFile=./logback.xml LogAnalyzer-1.0.0-jar-with-dependencies.jar access.log
    or
    java -Dlogback.configurationFile=./logback.xml LogAnalyzer-1.0.0-jar-with-dependencies.jar
````


