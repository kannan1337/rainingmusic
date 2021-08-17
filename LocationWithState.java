/**
   This class inherits Location class, adding a field for
   state code. This is required for cities with same names
   in different states in the USA. The string for weather
   query is also changed to have the state code included. 
*/

public class LocationWithState extends Location
{
   // Declare field for state code which was not part of Location class
   private String stateCode;
   
   /**
      Constructor method for city with state code
      @param pLabel Label describing the location address
      @param pCity City
      @param pStateCode 2 letter state code (works for USA)
      @param pCountryCode3Letter 3 letter country code from HERE API
   */
   
   public LocationWithState(String pLabel, String pCity, String pStateCode, String pCountryCode3Letter)
   {
      // Call super class constructor which does not handle state codes
      super(pLabel, pCity, pCountryCode3Letter);
      
      stateCode = pStateCode;
   }
   
   /**
      Getter method for 2 Letter state code
      @return 2-letter state code
   */
   public String getStateCode()
   {
      return stateCode;
   }
   
   
   /**
      Getter method for location string to query with OpenWeatherMap.
      Overrides superclass method to add state code as part of string.
      @return City, 2-letter state code, 2-letter country code
   */
   @Override
   public String getWeatherQueryLocation()
   {
      // City, state code, country code
      return getCity() + "," + stateCode + "," + getCountryCode();
   }
}  