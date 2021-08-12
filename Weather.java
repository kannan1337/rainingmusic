/**
   Uses city as input to create a weather object that stores various
   weather properties of that city.
   Provides methods to cahnge city and retrieve weather properties.
*/

// Google Gson is used to conver json string to Map
import com.google.gson.*;
import com.google.gson.reflect.*;

// Json.Simple is used to extract an array from json string
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

// To read the resulting weather report file/string from visiting URL
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;

// Used to retrieve json string weather report by connecting to a URL
import java.net.URL;
import java.net.URLConnection;

// For the Map that the Json string weather report is converted to
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Weather
{
   // This is the API key from OpenWeatherMap.org specific to a new user account.
   // The free account holds some limitations.
   private static final String WEATHER_API_KEY = "66942c22b4b7ec481ba04d34e010b30e";
   
   // Holds city, 2 letter country, URL that gives the weather report,
   // the type of weather (clear/rain/snow), the retrieved weather report json string.
   private String city, countryCode, weatherUrlString, weatherType, report;
   
   // Hold temperature value as an int, timezone
   private int temperature, timeZone;
   
   /**
      Converts json string to a map.
      This piece of code was adapted from the internet.
      Source will be added in next update.
   */
   public static Map<String, Object> jsonToMap(String str)
   {
      Map<String, Object> map = new Gson().fromJson(
         str, new TypeToken<HashMap<String, Object>>()
         {}.getType()
         );
         return map;
   }
   
   /*
      Constructor that calls updateCity method with the param
      and then updates the weather using update method.
      @param pCity Name of the city
   */
   public Weather(String pCity)
   {
      updateCity(pCity);
   }
   
   /**
      Updates the City
      @param pCity Name of the city
   */
   public void updateCity(String pCity)
   {
      // Update the city
      city = pCity;
      
      // Create new URL to query the weather with updated city
      weatherUrlString = "http://api.openweathermap.org/data/2.5/weather?q="
         + city + "&appid=" + WEATHER_API_KEY + "&units=imperial";
      System.out.println("Weather query URL: " + weatherUrlString + "\n");
      
      // Call update method to get new weather report
      update();
   }
   
   public void update()
   {
      try
      {
         // URL object created from the URL string
         URL weatherUrl = new URL(weatherUrlString);
      
         // A connection is created using the URL
         URLConnection urlConn = weatherUrl.openConnection();
      
         // The text received from URL is stored
         InputStreamReader inputStreamReader = new InputStreamReader(urlConn.getInputStream());
      
         // Create scanner object to hold the received text
         Scanner scanner = new Scanner(inputStreamReader);
      
         // Save the text to report string
         report = scanner.nextLine();
                 
      } catch (IOException exception)
      {
         System.out.println(exception.getMessage());
      }
      
      // Print out the report for testing
      System.out.println("Weather report is:\n" + report + "\n");
      
      // Convert report string to a Map using google's gson code
      Map<String, Object> reportMap = jsonToMap(report.toString());
      
      // Retrieve timezone and save as from reportmap, to float, to int
      timeZone = (int) Float.parseFloat(reportMap.get("timezone").toString());
      
      // Convert the "main" field of the reportMap to a new map for main
      Map<String, Object> mainMap = jsonToMap(reportMap.get("main").toString());
      
      // Retrieve the temperature from mainmap, convert to float, then to int
      // and save in temperature field
      temperature = (int) Float.parseFloat(mainMap.get("temp").toString());
      
      // Convert the "sys" field of the reportMap to a new map for main
      Map<String, Object> sysMap = jsonToMap(reportMap.get("sys").toString());
      
      // Retrieve country code from sys map
      countryCode = sysMap.get("country").toString();
      
      try
      {
         // Create Parser for JSON text
         JSONParser parser = new JSONParser();
         
         // Stores parsed JSON text as JSONObject
         JSONObject weatherObj = (JSONObject)parser.parse(report);
         
         // Stores the info from weather section of this json string as an array
         // This is because weather section contains an array with 1 element
         JSONArray weatherObjArray = (JSONArray)weatherObj.get("weather");
         
         // Store the 1st element of this array as a JSONObject
         weatherObj = (JSONObject)weatherObjArray.get(0);
         
         /*
            Get info from the "main" key of this string, store in weatherType
            Possible weather types are:
            ID 2xx: "Thunderstorm"
            ID 3xx: "Drizzle"
            ID 5xx: "Rain"
            ID 6xx: "Snow"
            ID 7xx: "Atmosphere"
            ID 800: "Clear"
            ID 80x: "Clouds"
         */
         weatherType = weatherObj.get("main").toString();
         
      } catch(ParseException pe)
      {
         // Print exception from trying to parse json string
         System.out.println("position: " + pe.getPosition());
         System.out.println(pe);
      }
   }
   
   /**
      Getter method for temperature
      @return Current temperature for the city
   */
   public int getTemp()
   {
      return temperature;
   }
   
   /**
      Getter method for city
      @return Current city
   */
   public String getCity()
   {
      return city;
   }
   
   /**
      Getter method for timezone
      @return Time zone for the current city
   */
   public int getTimeZone()
   {
      return timeZone;
   }
   
   /**
      Getter method for 2 letter country code
      @return 2 letter country code of current city
   */
   public String getCountryCode()
   {
      return countryCode;
   }
   
   /*
      Getter method for weather type.
      Possible weather types are:
      ID 2xx: "Thunderstorm"
      ID 3xx: "Drizzle"
      ID 5xx: "Rain"
      ID 6xx: "Snow"
      ID 7xx: "Atmosphere"
      ID 800: "Clear"
      ID 80x: "Clouds"
      @return Type of current weather type at current city
   */
   public String getWeatherType()
   {
      return weatherType;
   }
}
