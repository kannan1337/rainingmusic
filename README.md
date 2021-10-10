# rainingmusic

Place the following files inside /lib/

1. Google gson jar file: https://maven-badges.herokuapp.com/maven-central/com.google.code.gson/gson
2. json-simple jar file: https://code.google.com/archive/p/json-simple/downloads
3. jfuge jar file: http://www.jfugue.org/download.html

The in your IDE, edit CLASSPATH, add these three jar files there. In Eclipse IDE if it still says a class file is unrecognized, follow the following alternate method only for that missing package.

Alternative method:
===================

Download Gson files from 
https://github.com/google/gson

Click Code>Download ZIP.
Then copy "com" folder from this path within the zip
/gson-master/gson/src/main/java/
and paste in same folder as your code.
Then copy "com" folder from 
/gson-master/gson/src/main/java-templates/
as well and paste in same folder as code. It should basically add just one more file to it.

Then download Json-simple from
https://github.com/fangyidong/json-simple
Click Code>Download ZIP
Then copy "org" folder from this path within the zip:
/json-simple-master/src/main/java/
adnd paster in the same folder as your code.

Also download jfuge and copy "org" folder into the same folder as the code.
Also download CSV file and place in same folder as the code.

API References:
===================

1. Open Weather Map API: https://openweathermap.org/current
2. HERE Geocoding & Search API: https://developer.here.com/documentation/geocoding-search-api/api-reference-swagger.html 
