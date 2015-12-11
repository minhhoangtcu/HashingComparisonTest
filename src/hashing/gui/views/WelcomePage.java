package hashing.gui.views;

import java.io.IOException;

import hashing.hashTables.PackingDensity;
import hashing.reader.TemplateReader;
import hashing.reader.TemplateReader.Template;
import hashing.resolutionMethods.HashingResolutionMethod;

public class WelcomePage {
	
	public static final String PATH = "templates/WelcomeScreen.html";
	public static final String[] LINEAR_PROBE_50_IDS = {"$lp50Collisions", "$lp50ComparisonsMissing", "$lp50ComparisonsPresent"};
	public static final String[] LINEAR_PROBE_90_IDS = {"$lp90Collisions", "$lp90ComparisonsMissing", "$lp90ComparisonsPresent"};
	public static final String[] QUADRATIC_PROBE_50_IDS = {"$qp50Collisions", "$qp50ComparisonsMissing", "$qp50ComparisonsPresent"};
	public static final String[] QUADRATIC_PROBE_90_IDS = {"$qp90Collisions", "$qp90ComparisonsMissing", "$qp90ComparisonsPresent"};
	public static final String[] LINEAR_QUOTIENT_50_IDS = {"$lq50Collisions", "$lq50ComparisonsMissing", "$lq50ComparisonsPresent"};
	public static final String[] LINEAR_QUOTIENT_90_IDS = {"$lq90Collisions", "$lq90ComparisonsMissing", "$lq90ComparisonsPresent"};
	
	public static String generateContent(HashingResolutionMethod method, PackingDensity density, double collisions, double comparesMissing, double comparesPresent) throws IOException {
		String input = TemplateReader.read(Template.WELCOME_PAGE);
		String[] ids = new String[3];
		
		switch (method) {
		case LINEAR_PROBING:
			if (density == PackingDensity.FIFTY_PERCENT)
				ids = LINEAR_PROBE_50_IDS;
			else
				ids = LINEAR_PROBE_90_IDS;
			break;
		case LINEAR_QUOTIENT:
			if (density == PackingDensity.FIFTY_PERCENT)
				ids = QUADRATIC_PROBE_50_IDS;
			else
				ids = QUADRATIC_PROBE_90_IDS;
			break;
		case QUADRATIC_PROBING:
			if (density == PackingDensity.FIFTY_PERCENT)
				ids = LINEAR_QUOTIENT_50_IDS;
			else
				ids = LINEAR_QUOTIENT_90_IDS;
			break;
		default:
			break;
		}
		
		input = input.replace(ids[0], collisions+"");
		input = input.replace(ids[1], comparesMissing+"");
		input = input.replace(ids[2], comparesPresent+"");
		return input;
	}
	
}
