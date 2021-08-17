/**
   Top level Music class that provides method to choose
   instrument and play intro music with tempo based on weather.
*/ 

// Required for jfuge player
import org.jfugue.player.Player;

// Required for patterns to be played in jfuge player
import org.jfugue.pattern.Pattern;

public class Music
{
   // Instrument to play intro music
   private String introInstrument;
   
   // Set of notes that are played as intro music
   private String introTune;
   
   // Pattern that hold the music to play as intro
   protected Pattern introPattern;
   
   // Holds tempo of the music
   private int tempo;
   
   /**
      Constructor to set default intrument, music notes, & tempo.
   */
   
   public Music()
   {
      // Set intrument used to play intro music
      introInstrument = "Piano";
      
      // Set the notes to be played as intro
      introTune = "C D E F G A B C6W";
      
      // Set default tempo
      tempo = 100;
      
      // Initialize the intro pattern
      introPattern = new Pattern (introTune);
   }
   
   /**
      Method to choose the instrument based on continent.
      chooseInstrument method uses method overloading.
      It may either be passed a String object or Weather object.
      This instance defines when a String object is passed.
      @param continent Continent code (2 letters).
      @return String holding the name of the instrument chosen.
   */
   
   public String chooseInstrument(String continent)
   {
      String chosenInstrument;
      
      switch (continent)
      {
         // Africa
         case "AF":  chosenInstrument = "Marimba";
                     break;
                     
         // Antartica
         case "AN":  chosenInstrument = "Crystal";
                     break;
         
         // Asia
         case "AS":  chosenInstrument = "Shamisen";
                     break;
         
         // Europe
         case "EU":  chosenInstrument = "Steel_Drums";
                     break;
         
         // North America
         case "NA":  chosenInstrument = "Acoustic_Bass";
                     break;
         
         // Oceania
         case "OC":  chosenInstrument = "Rain";
                     break;
                     
         // South America
         case "SA":  chosenInstrument = "Pan_Flute";
                     break;
                     
         // If continent not found
         default:    chosenInstrument = introInstrument;
      }
      
      // Return the intrument chosen as a string
      return chosenInstrument;
   }
   
   /**
      Method to choose the instrument based on continent.
      chooseInstrument method uses method overloading.
      It may either be passed a String object or Weather object.
      This instance defines when a Weather object is passed.
      The continent code of the continent in which the location
      used in the Weather object passed, is used to choose an
      instrument.
      @param weather Weather object containing the weather report.
      @return String holding the name of the instrument chosen.
   */
   
   public String chooseInstrument(Weather weather)
   {
      // To hold the chosen instrument as a string
      String chosenInstrument;
      
      // Store the country code from weather object
      String countryCode = weather.getCountryCode();
      
      // Store the continent code corresponding to the country
      // This uses the LocationHelp class to pull this information
      String continentcode = LocationHelp.getContinentCode(countryCode);
      
      /*
         Using method overloading call the version of the chooseInstrument()
         method that takes a string for continent code as its parameter
         and save its return as chosenInstrument
      */
      chosenInstrument = chooseInstrument(continentcode);
      
      // Return the instrument chosen as a string
      return chosenInstrument;
   }
   
   /**
      Plays the intro music with the default instrument for intro music
      and with tempo chosen based on temperature in the Weather object
      passed in as parameter.
      This uses method overloading.
      It can either be passed a Weather object or be called with no parameters.
      @param weather Weather object of the location for which weather was pulled.
   */
   
   public void play(Weather weather)
   {
      // Set tempo based on the temperature from Weather object.
      tempo = weather.getTemp();
      
      // Display what this is playing and how
      System.out.println("\nPlaying (super class).. \n Tune: Music (Intro) \n"
         + " Instrument: " + introInstrument + " (default for intro music) \n"
         + " Tempo: " + getTempo() + " (set from temperature: "
         + weather.getTemp() + " deg F)");
      
      // Create a new player
      Player player = new Player();
      
      // Set the tempo of the pattern to tempo
      introPattern.setTempo(tempo);
      
      // Set the intrument of the pattern to the default instrument
      introPattern.setInstrument(introInstrument);
      
      // Play the pattern
      player.play(introPattern);
   }
   
   /**
      Plays the introPattern with current settings when no parameter is passed.
      This uses method overloading.
      It can either be passed a Weather object or be called with no parameters.
   */
   
   public void play()
   {
      // Display what this is playing.
      System.out.println("\nPlaying intro tune with current settings..");
      
      // Create a new player
      Player player = new Player();
      
      // Play the pattern with current settings.
      player.play(introPattern);      
   }
   
   /**
      Getter method that returns the tempo
      @return Current tempo of the tune
   */
   
   public int getTempo()
   {
      // Return the current tempo of the tune
      return tempo;
   }
   
   /**
      To clear and set new tune in a pattern
      @param pPattern Pattern to be changed
      @param pTune Tune to be set for the pattern
   */
   
   protected void setPattern(Pattern pPattern, String pTune)
   {
      // Clear the existing pattern
      pPattern.clear();
      
      // Set the pattern to the tune passed in
      pPattern.add(pTune);
   }
   
   /**
      Returns relevant fields in the object.
      @return String with relevant fields and their values
   */
   public String toString()
   {
      return "Intro Tune: " + introTune
         + "\nIntro Instrument: " + introInstrument
         + "\nTempo: " + tempo;
   }

}