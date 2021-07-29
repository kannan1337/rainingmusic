import com.google.gson.*;
import com.google.gson.reflect.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Weather
{
   private static final String WEATHER_API_KEY = "66942c22b4b7ec481ba04d34e010b30e";
   private String city, weatherUrlString;
   private int temperature;
   
   public static Map<String, Object> jsonToMap(String str)
   {
      Map<String, Object> map = new Gson().fromJson(
         str, new TypeToken<HashMap<String, Object>>()
         {}.getType()
         );
         return map;
   }

   public Weather(String pCity)
   {
      updateCity(pCity);
      update();
   }
   
   public void updateCity(String pCity)
   {
      city = pCity;
      
      weatherUrlString = "http://api.openweathermap.org/data/2.5/weather?q="
         + city + "&appid=" + WEATHER_API_KEY + "&units=imperial";
      System.out.println("Weather query URL: " + weatherUrlString + "\n");
      
      update();
   }
   
   public void update()
   {
      try
      {
         StringBuilder report = new StringBuilder();
         URL weatherUrl = new URL(weatherUrlString);
         URLConnection urlConn = weatherUrl.openConnection();
         InputStreamReader inputStreamReader = new InputStreamReader(urlConn.getInputStream());
         BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
         String inputLine;
         while ((inputLine = bufferedReader.readLine()) != null)
         {
            // System.out.println(inputLine);
            report.append(inputLine);
         }
         bufferedReader.close();
         System.out.println("Weather report is:\n" + report + "\n");
         
         Map<String, Object> reportMap = jsonToMap(report.toString());
         Map<String, Object> mainMap = jsonToMap(reportMap.get("main").toString());
         
         temperature = (int) Float.parseFloat(mainMap.get("temp").toString());         
      } catch (IOException exception)
      {
         System.out.println(exception.getMessage());
      }
   }
   
   public int getTemp()
   {
      return temperature;
   }
   
   public String getCity()
   {
      return city;
   }
}
