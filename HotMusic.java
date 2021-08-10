/***********************************
Creating a simple tune that will play when it's hot,
This has been updated to have the c major scale.
****************************************/ 
import org.jfugue.player.Player;// importing the player method
import org.jfugue.pattern.Pattern;// importing the pattern method


public class HotMusic
{
 public static void main(String[] args)
 {
  Player player = new Player();// Creating a new player
  Pattern p1 = new Pattern ("V0 I[STEEL_DRUMS] C D E F G A B C6W");
  Pattern p2 = new Pattern ("V1 I[MARIMBA] C D E F G A B C6W"); 
   player.play(p1, p2); // playing the pattern
  }
}

