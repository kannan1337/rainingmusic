/**
   This program asks the user to enter a city.
   It then pulls the weather report for it by using Weather class.
   Finds the continent where that city belongs using CountryContinentMap class.
   Initializes and plays music using a music class that could be Cold/Hot/RainMusic
   class depending on the type of weather.
   The music plays at a tempo defined by the temperature,
   using an instrument defined by the continent.
*/

// To get user input
import java.util.Scanner;

public class MusicDemo
{
   /**
      Main method for the demo
   */
   
   public static void main(String[] args)
   {
      // To hold any input from customer
      String input;
      
      // To hold weather report
      Weather weather;
      
      // To hold a Music class or subclass that plays the music
      Music music;
      
      // To hold use input as scanner object
      Scanner scanner = new Scanner(System.in);
      
      // Ask user for city to pull weather for
      System.out.print("Enter city: ");
      
      // City name is stores in input string
      input = scanner.nextLine();
      
      // Create a Weather object using constructor that passes city name into it
      weather = new Weather(input);
      
      // Print the weather using getter methods in weather object
      System.out.println("City: " + weather.getCity() + "\nCountry: "
         + CountryContinentMap.getCountry(weather.getCountryCode()) + " ("
         + weather.getCountryCode() + ") \nContinent: "
         + CountryContinentMap.getContinent(weather.getCountryCode())
         + " (" + CountryContinentMap.getContinentCode(weather.getCountryCode())
         + ") \nWeather type: " + weather.getWeatherType()
         + "\nTemperature: " + weather.getTemp() + " deg F");
      
      // Initialize music object based on weather type
      // POLYMORPHISM
      switch (weather.getWeatherType())
      {
         // Rain Music
         case "Thunderstorm":
         case "Drizzle":
         case "Rain": music = new RainMusic();
            break;
         
         // Hot Music
         case "Clear":
         case "Clouds": music = new HotMusic();
            break;
                     
         // Cold Music
         case "Snow":
         default: music = new ColdMusic();
      }
      
      // Call the play method in the Rain/Hot/ColdMusic class
      music.play(weather);
            
   }
}
