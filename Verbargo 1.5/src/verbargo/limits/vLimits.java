package verbargo.limits;

public interface vLimits {
	
	public static final int MAX_SCORE = 50;
	
	public static final int MIN_SCORE = -50;
	
	public static final int MIN_TEAM_PLAYERS = 2;
	
	public static final int MAX_TEAM_PLAYERS = 10;
	
	public static final int MAX_TEAMS = 4;
	
	public static final int MAX_RV_WORDS = 6;
	
	public static final int INITIAL_DELAY = 1000;
	
	public static final int MAX_TURN_TIME = 60000;
	
	public static final int SWITCH_SCREEN_TIME = 5;
		
	public static final int DEFAULT_DIFFICULTY = 1;
	
	public static final boolean DEFAULT_TIME_OPTION = false;
	
	public static final int DEFAULT_SECONDS = 60;
	
	public static enum DIFFICULTY_TRANS {EASY,MEDIUM,HARD};
}
