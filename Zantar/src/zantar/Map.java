/**
 * Map class
 * Creates direction for the game
 */

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
	private String mapFile;

	public final int NORTH_BOUNDARY;
	public final int SOUTH_BOUNDARY;
	public final int EAST_BOUNDARY;
	public final int WEST_BOUNDARY;

	private final int x_norm;
	private final int y_norm;

	private String[][] map = new String[11][11];

	private Map() {
		mapFile = "map" + zone + ".txt";
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(mapFile));
			// First Line
			String line = scanner.nextLine();

			String[] boundaries = line.split(",");
			x_norm = Integer.parseInt(boundaries[0]);
			y_norm = Integer.parseInt(boundaries[1]);
			NORTH_BOUNDARY = y_norm;
			SOUTH_BOUNDARY = -1 * y_norm;
			EAST_BOUNDARY = x_norm;
			WEST_BOUNDARY = -1 * x_norm;

			for(int i = 0; i < 11; i++) {
				for(int j = 0; j < 11; j++) {
					map[i][j] = null;
				}
			}

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();

				String[] loc = line.split("\\+");

				String[] xy = loc[0].split(",");
				map[Integer.parseInt(xy[0]) + 5][Integer.parseInt(xy[1]) + 5] = loc[1];
			}

			scanner.close();
		} catch (FileNotFoundException ex) {
			throw new RuntimeException("Map file not found!");
		}
	}

	public static Map getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Map();
		return INSTANCE;
	}

	public String getLocationData(int x, int y) {
		if (Math.abs(x) > x_norm || Math.abs(y) > y_norm) {
			return null;
		}
		return map[x + x_norm][y + y_norm];
	}

	public void removeLocationData(int x, int y) {
		map[x + x_norm][y + y_norm] = null;
	}
}
