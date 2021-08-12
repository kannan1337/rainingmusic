/**
   This program demos the use of weather class and uses its temperature
   return method to play four chords with that tempo
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
      
      // Print the temperature using the getter method from the weather object
      System.out.println(weather.getCity() + ", " + weather.getCountryCode()
         + " " + CountryContinentMap.getCountry(weather.getCountryCode())
         + ", " + CountryContinentMap.getContinentCode(weather.getCountryCode())
         + " " + CountryContinentMap.getContinent(weather.getCountryCode())
         + ", temperature: " + weather.getTemp()
          + ", type: " + weather.getWeatherType());
      
      // Play cold music depending on continent
      music = new ColdMusic();
      music.play(weather);
            
   }
}
