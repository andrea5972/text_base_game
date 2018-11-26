package zantar;

import java.io.BufferedReader;
import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Map {
	
	private static Map INSTANCE;
	private String zone = "1";
	private String map;
	ArrayList<String> locations = new ArrayList<>();
	
	private Map () throws FileNotFoundException {
		map = "map" + zone + ".txt";
		Scanner scanner = new Scanner(new File(map));
	}
	
	public static Map getInstance() throws FileNotFoundException {
		if (INSTANCE == null)
			INSTANCE = new Map();
		return INSTANCE;
	}
	
}