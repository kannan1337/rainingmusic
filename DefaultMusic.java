/***********************************
Creating a simple tune that will play as 
the defalut /repeating tune
****************************************/ 
import org.jfugue.player.Player; // importing the player method
import org.jfugue.pattern.Pattern; // Imporintg the pattern method

public class DefaultMusic
{
public static void main(String[] args)
 {
  Player player = new Player(); // Creating a new player
  Pattern p1 = new Pattern ("C D E F G A B C6W");
  player.play(p1); // Playing the pattern
 }
}
