/***********************************
Creating a simple tune that will play when it's 
cold. This has been update to play the c major scale
****************************************/ 
import org.jfugue.player.Player; // importing the player method
import org.jfugue.pattern.Pattern; // importing the pattern method

public class ColdMusic
{
 public static void main(String[] args)
 {
  Player player = new Player(); // creating a new player
  Pattern p1 = new Pattern ("V0 I[PAN_FLUTE] C D E F G A B C6W");
  Pattern p2 = new Pattern ("V1 I[OBOE] C D E F G A B C6W"); 
   player.play(p1, p2); // playing the pattern
  }
}
