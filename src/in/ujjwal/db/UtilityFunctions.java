package in.ujjwal.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class UtilityFunctions {

	public static double getRandomIntegerBetweenRange(double min, double max){
	    double x = (int)(Math.random()*((max-min)+1))+min;
	    return x;
	}
	
	public void callmp3(String path) throws FileNotFoundException, JavaLayerException
	{
		 FileInputStream fis = new FileInputStream(path);
	     Player playMP3 = new Player(fis);

	     playMP3.play();
	}
}
