package inf101.v18.sem2;

import java.util.Arrays;
import java.util.List;

public class AppInfo {
	/**
	 * Your application name.
	 */
	public static final String APP_NAME = "Connect4";
	/**
	 * The main class, for starting the application
	 */
	public static final Class<?> APP_MAIN_CLASS = main.Main.class; // e.g., inf101.v18.sem2.Main.class
	/**
	 * Your name.
	 */
	public static final String APP_DEVELOPER = "Jakob Kallestad";
	/**
	 * A short description.
	 */
	public static final String APP_DESCRIPTION = "Semesteroppgave2, its a replicate of the game Connect4";
	/**
	 * List of extra credits (e.g. for media sources)
	 */
	public static final List<String> APP_EXTRA_CREDITS = Arrays.asList("Thanks to Gard Askeland for providing some of the music for the game.");
	
	
	/**
	 * Help text. Could be used for an in-game help page, perhaps.
	 * <p>
	 * Use <code>\n</code> for new lines, <code>\f<code> between pages (if
	 * multi-page).
	 */
	public static final String APP_HELP = "No help required :)";
}
