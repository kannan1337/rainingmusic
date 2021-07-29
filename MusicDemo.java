import java.util.Scanner;
import org.jfugue.player.Player;

// jsdkjas

public class MusicDemo
{
   private static String input;
   private static Weather weather;
   
   public static void main(String[] args)
   {
      Player player = new Player();
      
    
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter city: ");
      input = scanner.nextLine();
      
      weather = new Weather(input);
      
      System.out.println(weather.getCity() + " temperature: " + weather.getTemp());
      
      player.play("T" + String.valueOf(weather.getTemp()) + " C D E F");
      
      System.out.print("\nChange city? (Y/N): ");
      input = scanner.nextLine();
      
      
      while (input.charAt(0) == 'Y' || input.charAt(0) == 'y')
      {
         System.out.print("\nEnter new city: ");
         input = scanner.nextLine();
         weather.updateCity(input);
         System.out.println(weather.getCity() + " temperature: " + weather.getTemp());
         player.play("T" + String.valueOf(weather.getTemp()) + " C D E F");
         System.out.print("\nChange city? (Y/N): ");
         input = scanner.nextLine();
         
      }
      
   }
}
