package Rules;

public class Instructions {

	public static String gameRule1 = "RELOCATE";
	public static String gameRule2 = "AND";
	public static String gameRule3 = "PREPARE";
	
	public static String[] restRule = new String[3];
	
	public static String[] getRuleArray() {
		restRule[0] = gameRule1;
		restRule[1] = gameRule2;
		restRule[2] = gameRule3;
		return restRule;
	}
	
}
