/**
 * The events you may encounter
 * On your way to save the world
 */

package zantar;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Event {

	public static final Random random = new Random();

	// A HashMap of possible events	
    private static final List<String> events = Arrays.asList(
			"bridge", "building", "river", "bog", "meadow", "house", "stump");
	private final String event;
	
	public Event() {
		int x = random.nextInt(100);
		int r;
		if (x > 94) {
			r = 6;
		} else {
			r = random.nextInt(6);
		}
		event = events.get(r);
	}
	
	public boolean occurs() {
		//String event = event.substring(0, 1).toUpperCase() + event.substring(1);
		//int n = random.nextInt(1);
		int n = 0; // Hard set to no due to time constraints
		if (event == "stump") {
			//return true;
			return false;
		} else if (n == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public String name() {
		return Character.toUpperCase(event.charAt(0)) + event.substring(1, event.length());
	}
	
	public String type() {
		int n = random.nextInt(10);
		if (event == "stump") {
			return "stump";
		} else if (n > 7) {
			return "bad";
		} else {
			return "good";
		}
	}
	
	public String action(String type) {
		int amnt = random.nextInt(10) + 1;
		int n = random.nextInt(1);
		if (n == 1) {
			return("gold," + Integer.toString(amnt));
		}
		else {
			return("health," + Integer.toString(amnt));
		}
	}
}
