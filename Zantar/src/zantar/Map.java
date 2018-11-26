package zantar;

import java.io.BufferedReader;
import java.util.Scanner; 
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Map {
	
	private static Map INSTANCE;
	private String zone = "1";
	private String map;
	BufferedReader reader = null;
	Scanner scanner;
	ArrayList<String> locations = new ArrayList<>();
	
	private Map() {
		map = "/Zantar/map1.txt";
		scanner = new Scanner(new FileReader("/Zantar/map1.txt"));
	}
	
	public static Map getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Map();
		return INSTANCE;
	}
	
}