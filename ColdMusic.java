/***********************************
Creating a simple tune that will play when it's 
cold.
Play it using an instrument chosen based on the continent
****************************************/ 
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;

public class ColdMusic
{
  // To hold instruments, and song notes
  private String instrument1, instrument2, currentInstrument;
  private String coldSong1;
  private String coldSong2;
  
  /**
    Constructor to initialize instruments and song notes
  */
  
  public ColdMusic()
  {
      instrument1 = "PAN_FLUTE";
      instrument2 = "OBOE";
      coldSong1 = "] F G A Bb C D E F6W";
      coldSong2 = "] F G A Bb C D E F6W";
  }
  
  /**
    Play method picks and instrument based on the continent
    and plays the songs defined in the class using it.
    @param continent Continent 2-letter code
  */
  
  public void play(String continent)
  {
    // Selecting instrument based on continent
    switch (continent)
    {
      // North America  
      case "NA":  currentInstrument = instrument1;
                  break;
      // Africa
      case "AF":  currentInstrument = instrument2;
                  break;
      // If the continent code doesnt match anything
      default:    currentInstrument = instrument1;
    }
      
    // Declare and initialize a player  
    Player player = new Player();
    
    // Store first song with the chosen instrument as a Pattern to play
    Pattern p1 = new Pattern ("V0 I[" + currentInstrument + coldSong1);
    
    // Store second song with the chosen instrument as a Pattern to play
    Pattern p2 = new Pattern ("V1 I[" + currentInstrument + coldSong2);
    
    // Play both patterns
    player.play(p1, p2);
  }
}
