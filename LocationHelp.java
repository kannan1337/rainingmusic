/**
   Reads country and continents list from CSV file named
   country-and-continent-codes-list.csv in same folder.
   Provides methods to return continent code, continent name,
   and country name when 2 letter country code is available.
*/

// To read file contents
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// To read the resulting weather report file/string from visiting URL
import java.io.InputStreamReader;
import java.io.IOException;

// Used to retrieve json string weather report by connecting to a URL
import java.net.URL;
import java.net.URLConnection;

// Json.Simple is used to extract an array from json string
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

// To hold array list of Locations
import java.util.ArrayList;

public final class LocationHelp
{
   /*
      This is the API key for HERE Location Services REST APIs specific to the
      programmer's user account. It expires every few days or so and requires
      renewal.
   */
   private static final String LOCATION_API_KEY = "hTJHRgc45thH3h69WBNC5Y7FRRTfEWSDBt65x9bCegE";
   
   // To hold location suggestions json string retrieved from web service
   private static String report;
   
   // Initialize fields with default values of USA
   private static String continentCode = "NA",
      continent = "North America",
      countryCode = "US",
      country = "United States of America";
   
   /**
      Sets continent code  and continent for given country
      @param countryCode2Letter Country code (2 or 3 letters)
   */
   private static void getData(String pCountryCode)
   {
      try
      {
         // Get scanner instance
         Scanner scanner = new Scanner(new File("country-and-continent-codes-list.csv"));
         
         // Set the delimiter used in file
         scanner.useDelimiter(";");
         
         // Skip headings
         scanner.nextLine();
         
         // Continues loop till end of file
         while (scanner.hasNext()) 
         {
            // Check first two colums before skipping to next line
            for (byte i = 0; i < 2; i++)
            {
               // Checks if first string of line is the country code
               if(scanner.next().equals(pCountryCode))
               {
                  // If 3 letter country code is passed
                  if(pCountryCode.length() == 3)
                  {
                     // Copy next column to country code to change to 2 letter code
                     countryCode = scanner.next();
                  }
                  // If two letter country code was passed
                  else
                  {
                     // Copy passed in argument to country code
                     countryCode = pCountryCode;
                  }
                  
                  // Copy next column to continent code
                  continentCode = scanner.next();
                  
                  // Copy next column to continent
                  continent = scanner.next();
                  
                  // Copy the next column to country
                  country = scanner.next();
                  
                  // Break if country found
                  break;
               }
            }
            
            // Skip to next line
            scanner.nextLine();
         }
         
         // Close the scanner  
         scanner.close();
      }
      catch (FileNotFoundException exception)
      {
         // Print error message if file not found
         System.out.println(exception.getMessage());
      }
   }
   
   /**
      Returns 2 letter continent code for given 2 letter country code
      @param pCountryCode 2 letter country code
      @return 2 letter continent code
   */
   public static String getContinentCode(String pCountryCode)
   {
      // Call getData method to change fields with values from csv file
      getData(pCountryCode);
      
      // Return 2 letter continent code
      return continentCode;
   }
   
   /**
      Returns Continent name for given 2 letter country code
      @param pCountryCode 2 letter country code
      @return Continent name
   */
   public static String getContinent(String pCountryCode)
   {
      // Call getData method to change fields with values from csv file
      getData(pCountryCode);
      
      // Return continent name
      return continent;
   }
   
   /**
      Returns Country name for given 2 letter country code
      @param pCountryCode 2 letter country code
      @return Continent name
   */
   public static String getCountry(String pCountryCode)
   {
      // Call getData method to change fields with values from csv file
      getData(pCountryCode);
      
      // Return 2 letter country code
      return countryCode;
   }
   
   /**
      Returns 2 letter Country Code for given 3 letter country code
      @param pCountryCode 3 letter country code
      @return Continent name
   */
   public static String convert3to2LetterCountryCode(String pCountryCode)
   {
      // Call getData method to change fields with values from csv file
      getData(pCountryCode);
      
      // Return country name
      return countryCode;
   }
   
   /**
      Returns list of location suggestions based on
      the first few letters of the city passed into
      this method.
      @param input First fetters of city entered by user
      @return Array of 5 top matches of cities
   */
   
   public static ArrayList<Location> autocomplete(String input)
   {
      // Create new URL string to query matched locations with user input
      String locationUrlString = "https://autocomplete.search.hereapi.com/v1/autocomplete?types=city&limit=10&apiKey="
         + LOCATION_API_KEY + "&q=" + input;
            
      try
      {
         
         // URL object created from the URL string
         URL locationUrl = new URL(locationUrlString);
         
         // A connection is created using the URL
         URLConnection urlConn = locationUrl.openConnection();
         
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
      
      // Declare ArrayList to store location suggestions of type Location object
      ArrayList<Location> locationSuggestions = new ArrayList<Location>();
      
      try
      {
         // Create Parser for JSON text
         JSONParser parser = new JSONParser();
         
         // Stores parsed JSON text as JSONObject
         JSONObject locationObj = (JSONObject)parser.parse(report);
         
         // Stores the info from weather section of this json string as an array
         // This is because weather section contains an array with 1 element
         JSONArray locationObjArray = (JSONArray)locationObj.get("items");
         
         // Determine size of the JsonArray
         int numOfSuggestions = locationObjArray.size();
         
         for(int i = 0; i < numOfSuggestions; i++)
         {
            // Store the first element of locationObjArray as Json object
            locationObj = (JSONObject)locationObjArray.get(i);
            
            // Store the address field in the above as a json object
            locationObj = (JSONObject)locationObj.get("address");
            
            // Store relevant fields from the location retrieved
            String label = locationObj.get("label").toString();
            String countryCode = locationObj.get("countryCode").toString();
            String city = locationObj.get("city").toString();
                        
            /*
               Store USA's state code if country is USA
               Then call the relevant constructor based on country USA or not
               to store the i-th location suggestion in to the array of Location objects.
            */
            
            if(countryCode.equals("USA"))
            {
               // Store state code field from the location retrieved
               String usaStateCode = locationObj.get("stateCode").toString();
               
               // Call the constructor specific to USA location
               // Constructor overloading
               locationSuggestions.add(new Location(label, city, usaStateCode, countryCode));
            }
            
            // Call constructor for countries outside USA
            // Constructor overloading
            else
            {
               locationSuggestions.add(new Location(label, city, countryCode));
            }
            
         }         
      } catch(ParseException pe)
      {
         // Print exception from trying to parse json string
         System.out.println("position: " + pe.getPosition());
         System.out.println(pe);
      }
      
      // Return the location suggestions String array
      return locationSuggestions;
   }
}
