import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;

public class TestMusic

{
	public static void main(String[] args)
 
	{
  
		Player player = new Player();
		Pattern p1 = new Pattern ("I[piano] Gbw Dw Aq Ew RW Gbw Dw Aw Ew RW Gbw Dw Ai Ew RW Gbw Dw Aw Db6 E Gb6 E+Ab"); 
		player.play(p1);
  
 	}

}