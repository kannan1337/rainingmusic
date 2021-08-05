/***********************************
Creating a simple tune that will play when it's hot
****************************************/ 
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;

public class HotMusic
{
 public static void main(String[] args)
 {
  Player player = new Player();
  Pattern p1 = new Pattern ("V0 I[STEEL_DRUMS] C D E F G A B C6W");
  Pattern p2 = new Pattern ("V1 I[MARIMBA] C D E F G A B C6W"); 
   player.play(p1, p2);
  }
}

