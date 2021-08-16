/**
   Reads country and continents list from CSV file named
   country-and-continent-codes-list.csv in same folder.
   Provides methods to return continent code, continent name,
   and country name when 2 letter country code is available.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class CountryContinentMap
{      
   // Initialize fields with default values of USA
   private static String continentCode = "NA",
      continent = "North America",
      country = "United States of America";
   
   /**
      Sets continent code  and continent for given country
      @param countryCode 2 letter country code
   */
   private static void getData(String countryCode)
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
            // Checks if first string of line is the country code
            if(scanner.next().equals(countryCode))
            {
               // Copy next column to continent code
               continentCode = scanner.next();
               
               // Copy next column to continent
               continent = scanner.next();
               
               // Skip the three letter country code column
               scanner.next();
               
               // Copy the next column to country
               country = scanner.next();
               
               // Break if country found
               break;
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
      @param countryCode 2 letter country code
      @return 2 letter continent code
   */
   public static String getContinentCode(String countryCode)
   {
      // Call getData method to change fields with values from csv file
      getData(countryCode);
      
      // Return 2 letter continent code
      return continentCode;
   }
   
   /**
      Returns Continent name for given 2 letter country code
      @param countryCode 2 letter country code
      @return Continent name
   */
   public static String getContinent(String countryCode)
   {
      // Call getData method to change fields with values from csv file
      getData(countryCode);
      
      // Return continent name
      return continent;
   }
   
   /**
      Returns Country name for given 2 letter country code
      @param countryCode 2 letter country code
      @return Continent name
   */
   public static String getCountry(String countryCode)
   {
      // Call getData method to change fields with values from csv file
      getData(countryCode);
      
      // Return country name
      return country;
   }
}