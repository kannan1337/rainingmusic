/**
   This class holds details about a location
   as pulled from Here Geocoding API and then
   converted to format accepted by OpenWeatherMap.org API
*/

public class Location
{
   // Declare fields that describe a location
   private String label, city, countryCode2Letter, countryCode3Letter;
   
   /**
      Constructor method for city without state code (used for outside USA)
      @param pLabel Label describing the location address
      @param pCity City
      @param pStateCode 2 letter state code (works for USA)
      @param pCountryCode3Letter 3 letter country code from HERE API
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
      Getter method for city
      @return City name
   */
   public String getCity()
   {
      return city;
   }
   
   /**
      Getter method for 2 Letter country code
      @return 2-letter country code
   */
   public String getCountryCode()
   {
      return countryCode2Letter;
   }
   
   /**
      Getter method for location string to query with OpenWeatherMap
      @return City, 2-letter country code
   */
   public String getWeatherQueryLocation()
   {
      // City, country 2 letter code
      return city + "," + countryCode2Letter;
   }
   
   /**
      toString Method returns the label
      @return Label describing location address
   */
   public String toString()
   {
      return label;
   }
}  