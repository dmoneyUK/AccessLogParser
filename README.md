AccessLogParser
======

An Java application which can read a log file from given path and display request information in the standard output.

Run Application
--------
As a maven project, you can download the source code and execute "mvn install" to generate the jar file. 
If you don't have maven on you environment, you can use the jar file in the root folder ("/AccessLogParser/").

To run the application, go to command line prompt and execute:
java -jar LogAnalyzer-1.0.0.jar access.log

Note: Please modify the command above to point to the correct path of the "access.log" in you environment.

Expected Result
--------
The application will read the log file and display the requests information in time order. The displayed requests are converted 
to UTC +0100 time zone to keep consistent. 

Example:
30/Mar/2015:10:11 +0100
Number of successful request per minute: 1489
Number of error request per minute: 840
Mean response time per minute: 410190486
MBs sent per minute: 12.786433219909668


