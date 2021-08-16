/**
   This class holds details about a location
   as pulled from Here Geocoding API and then
   converted to format accepted by OpenWeatherMap.org API
*/

public class Location
{
   // Declare fields that describe a location
   private String label, city, countryCode2Letter, countryCode3Letter, usaStateCode;
   
   /**
      Constructor method for city outside USA
   */
   
   public Location(String pLabel, String pCity, String pCountryCode3Letter)
   {
      // Set label i.e. City, State, Country
      label = pLabel;
      
      // Set other location fields
      city = pCity;
      countryCode3Letter = pCountryCode3Letter;
      
      // FInd and store 2 letter country code from 3 letter country code
      countryCode2Letter = LocationHelp.convert3to2LetterCountryCode(countryCode3Letter);
   }
   
   /**
      Constructor method for city in USA
   */
   
   public Location(String pLabel, String pCity, String pUsaStateCode, String pCountryCode3Letter)
   {
      // Call generic (non-USA-specific) constructor
      this(pLabel, pCity, pCountryCode3Letter);
      
      usaStateCode = pUsaStateCode;
   }
   
   
   /**
      Getter method for location string to query with OpenWeatherMap
   */
   public String getWeatherQueryLocation()
   {
      // For USA add state code to the query location string
      if(countryCode2Letter.equals("US"))
      {
         // City, USA state code, US
         return city + "," + usaStateCode + "," + countryCode2Letter;
      }
      
      // For cities outside USA
      else
      {
         // City, country 2 letter code
         return city + "," + countryCode2Letter;
      }
   }
   
   /**
      toString Method returns the label
   */
   public String toString()
   {
      return label;
   }
}  