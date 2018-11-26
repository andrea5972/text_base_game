package zantar;

import java.io.BufferedReader;
import java.util.Scanner; 
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Map {
	
	private static Map INSTANCE;
	private String zone = "1";
	private String map;
	BufferedReader reader;
	ArrayList<String> locations = new ArrayList<>();
	
	private Map() {
		map = "/Zantar/map1.txt";
		//System.out.println(map);
		//Scanner scanner = new Scanner(new FileReader(map));
		//reader = new BufferedReader(new FileReader(map));
	}
	
	public static Map getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Map();
		return INSTANCE;
	}
	
}