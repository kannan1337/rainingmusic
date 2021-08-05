/***********************************
Creating a simple tune that will play when it's 
Raining
****************************************/ 
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;

public class RainMusic
{
 public static void main(String[] args)
 {
  Player player = new Player();
  Pattern p1 = new Pattern ("V0 I[RAIN] Bb C D Eb F G A Bb6W");
  Pattern p2 = new Pattern ("V1 I[CRYSTAL] Bb C D Eb F G A Bb6W");
   player.play(p1, p2);
  }
}
