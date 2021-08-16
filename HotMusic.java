/**
   HotMusic class that inherits the Music class.
   It holds the tune to be played when the weather is Clear
   or Clouds, method to play the intro music from its super class,
   and play its own 2nd line of music with tempo based on weather.
*/ 

// Required for jfuge player
import org.jfugue.player.Player;

// Required for patterns to be played in jfuge player
import org.jfugue.pattern.Pattern;

public class HotMusic extends Music
{
   // Set of notes that are exclusive to this child class
   // to be played when weather is clear/clouds
   private String tune;
   
   // Pattern that stores the notes and related settings for this child class.
   private Pattern pattern;
  
   // Instrument to play this custom tune
   private String instrument;
   
   /**
      Constructor to call super class constructor and
      set the music notes specific to the hot class.
   */
   public HotMusic()
   {
      // Call super class constructor to set intro music fields
      super();
      
      // Set the notes to be played secondly
      tune = "E F# G# A B C# D# EW";
      
      // Set the pattern to the tune defined in this child class
      pattern = new Pattern (tune);
   }
  
   /**
      Plays the second line of music defined in this child class
      with instrument chosen based on the continent of the location
      for which the weather report was pulled, and with
      tempo chosen based on temperature.
      This uses method overloading.
      It can either be passed a Weather object or be called with no parameters.
      @param weather Weather object of the location for which weather was pulled.
   */
   
   public void play(Weather weather)
   {
      // Play intro music by calling the play method in the super class
      super.play(weather);
      
      // Choose intrument to play the 2nd line of music in this child class
      instrument = chooseInstrument(weather);
      
      // Display what this is playing and how
      System.out.println("\nPlaying (child class).. \n Tune: Hot Music "
         + "(chosen from Weather: Clear/Clouds) \n Instrument: "
         + instrument + " (chosen from continent: "
         + LocationHelp.getContinent(weather.getCountryCode())
         + ")\n Tempo: " + getTempo() + " (set from temperature: "
         + weather.getTemp() + " deg F)");
      
      // Create a new player
      Player player = new Player();
      
      // Set the tempo of the pattern to tempo used in intro music (from temperature)
      pattern.setTempo(getTempo());
      
      // Set the intrument of the pattern to the instrument chosen earlier
      pattern.setInstrument(instrument);
      
      // Play the pattern
      player.play(pattern);
   }
   
   /**
      Plays the pattern with current settings when no parameter is passed.
      This uses method overloading.
      It can either be passed a Weather object or be called with no parameters.
   */
   
   public void play()
   {
      // Display what this is playing.
      System.out.println("\nPlaying Hot Music tune with current settings..");
      
      // Create a new player
      Player player = new Player();
      
      // Play the pattern with current settings.
      player.play(pattern);      
   }
}