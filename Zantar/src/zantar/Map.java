package zantar;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

public class Map {
	
	private static Map INSTANCE;
	private int zone = 1;
	BufferedReader reader = null;
	
	private Map() {
		File file = new File("map" + zone + ".txt");
	}
	
	public static Map getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Map();
		return INSTANCE;
	}
	
}