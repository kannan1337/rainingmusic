/***********************************
Creating a simple tune that will play when it's 
cold
****************************************/ 
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;

public class ColdMusic
{
  private String instrument1, instrument2, currentInstrument;
  private String coldSong1;
  private String coldSong2;
  
  public ColdMusic()
  {
      instrument1 = "PAN_FLUTE";
      instrument2 = "OBOE";
      coldSong1 = "] C D E F G A B C6W";
      coldSong2 = "] C D E F G A B C6W";
  }
  
  public void play(String continent)
  {
      switch (continent)
      {
      case "NA":  currentInstrument = instrument1;
                  break;
      case "AF":  currentInstrument = instrument2;
                  break;
      default:    currentInstrument = instrument1;
      }
      
      Player player = new Player();
      Pattern p1 = new Pattern ("V0 I[" + currentInstrument + coldSong1);
      Pattern p2 = new Pattern ("V1 I[" + currentInstrument + coldSong2); 
      player.play(p1, p2);
  }
}
