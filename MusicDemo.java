/**
   This program demos the use of weather class and uses its temperature
   return method to play four chords with that tempo
   Now also plays ColdMusic based on continent of location chosen.
*/

import java.util.Scanner;
import org.jfugue.player.Player;

public class MusicDemo
{
   // To hold any input from customer
   private static String input;
   
   // To hold weather report
   private static Weather weather;
   
   // To hold object that contains countries-continents list
   //private static CountryContinentMap countries;
   
   /**
      Main method for the demo
   */
   public static void main(String[] args)
   {
      // Create a new jfuge music player
      Player player = new Player();
      
      // To hold use input as scanner object
      Scanner scanner = new Scanner(System.in);
      
      // Call constructor to create map with countries & continents
      //countries = new CountryContinentMap();
      
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
         + " temperature: " + weather.getTemp());

      // Play 4 chords using the temperature as the tempo
      player.play("T" + String.valueOf(weather.getTemp()) + " C D E F");
      
      // Play cold music depending on continent
      ColdMusic coldMusic = new ColdMusic();
      coldMusic.play(CountryContinentMap.getContinentCode(weather.getCountryCode()));
      
      // Ask user whether to change the city
      System.out.print("\nChange city? (Y/N): ");
      
      // Store user input
      input = scanner.nextLine();
      
      // Proceed with loop only if user said they want to update the city
      while (input.charAt(0) == 'Y' || input.charAt(0) == 'y')
      {
         // Ask user for new city and store input in string
         System.out.print("\nEnter new city: ");
         input = scanner.nextLine();
         
         // Call the weather object's updateCity method to change the city
         weather.updateCity(input);
         
         // Print temperature
         System.out.println(weather.getCity() + " temperature: " + weather.getTemp());
         
         // Play 4 chords using the temperature as the tempo
         player.play("T" + String.valueOf(weather.getTemp()) + " C D E F");
         
         // Ask user whether to change the city and store input
         System.out.print("\nChange city? (Y/N): ");
         input = scanner.nextLine();  
      }      
   }
}
