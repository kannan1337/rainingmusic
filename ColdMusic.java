/***********************************
Creating a simple tune that will play when it's 
cold
****************************************/ 
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;

public class ColdMusic
{
 public static void main(String[] args)
 {
  Player player = new Player();
  Pattern p1 = new Pattern ("V0 I[PAN_FLUTE] F G A Bb C D E F6W");
  Pattern p2 = new Pattern ("V1 I[OBOE] F G A Bb C D E F6W"); 
   player.play(p1, p2);
  }
}
