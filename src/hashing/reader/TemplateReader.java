package hashing.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TemplateReader {
	
	public enum Template {
		WELCOME_PAGE,
	}
	
	public static String read(Template type) throws IOException {
		FileReader htmlTemplateFile;
		switch (type) {
		case WELCOME_PAGE:
			htmlTemplateFile = new FileReader("templates/WelcomeScreen.html");
			break;
		default:
			htmlTemplateFile = null;
		}
		return getContent(htmlTemplateFile);
	}
	
	private static String getContent(FileReader file) throws IOException {
		BufferedReader reader = new BufferedReader(file);
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line + "\n");
		}
		return builder.toString();
	}

}
