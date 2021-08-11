/**
   Uses city as input to create a weather object that stores various
   weather properties of that city.
   Provides methods to cahnge city and retrieve weather properties.
*/

import com.google.gson.*;
import com.google.gson.reflect.*;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Weather
{
   // This is the API key from OpenWeatherMap.org specific to a new user account.
   // The free account holds some limitations.
   private static final String WEATHER_API_KEY = "66942c22b4b7ec481ba04d34e010b30e";
   
   // Holds city, 2 letter country, and the URL that gives the weather report as json string
   private String city, countryCode, weatherUrlString, weatherType,
      report;  // String to hold report
   
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
      update();
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
      
         // Print out the report for testing
         // System.out.println("Weather report is:\n" + report + "\n");
         
         // Convert report string to a Map using google's gson code
         Map<String, Object> reportMap = jsonToMap(report.toString());
         
         // Convert the "weather" field of the reportMap to a new map for weather
         // Gson gson = new Gson();
         // WeatherDescriptiveData[] weatherArray = gson.fromJson(reportMap.get("weather").toString(), WeatherDescriptiveData[].class);
         
         // Retrieve type of weather
         // weatherType = weatherArray[0].getMain();
         
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
                 
      } catch (IOException exception)
      {
         System.out.println(exception.getMessage());
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
      Currently broken
      Getter method for weather type.
      E.g.: rain, clear, snow.
      @return Type of current weather at current city
   */
   /*
   public String getWeatherType()
   {
      return weatherType;
   }
   */
}
