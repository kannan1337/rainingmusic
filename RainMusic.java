/***********************************
Creating a simple tune that will play when it's 
Raining. This has been updated to use the C
major scale
****************************************/ 
import org.jfugue.player.Player; // importing the player method
import org.jfugue.pattern.Pattern;// Imporintg the pattern method


public class RainMusic
{
 public static void main(String[] args)
 {
  Player player = new Player();// Creating a new player

  Pattern p1 = new Pattern ("V0 I[RAIN] C D E F G A B C6W");
  Pattern p2 = new Pattern ("V1 I[CRYSTAL] C D E F G A B C6W");
   player.play(p1, p2); // play the patern
  }
}
