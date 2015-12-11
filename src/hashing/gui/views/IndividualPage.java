package hashing.gui.views;

import java.io.IOException;

import hashing.hashTables.PackingDensity;
import hashing.reader.TemplateReader;
import hashing.reader.TemplateReader.Template;

public class IndividualPage {
	
	public static final String PATH = "templates/IndividualAlgorithm.html";

	public static String fillAll(String name, PackingDensity density,
			int collisions, int keys,
			int comparesMissing, int searchsMissing,
			int comparesPresent, int searchsPresent) throws IOException {
		String input = TemplateReader.read(Template.INDIVIDUAL_PAGE);
		input = input.replace("$name", name);
		input = input.replace("$density", density.getName());
		input = input.replace("$totalCollisions", collisions+"");
		input = input.replace("$totalKeys", keys+"");
		input = input.replace("$averageCollisions", (double) collisions/keys +"");
		input = input.replace("$totalComparesMissing", comparesMissing+"");
		input = input.replace("$totalSearchsMissing", searchsMissing+"");
		input = input.replace("$averageComparesMissing", (double) comparesMissing/searchsMissing +"");
		input = input.replace("$totalComparesPresent", comparesPresent+"");
		input = input.replace("$totalSearchsPresent", searchsPresent+"");
		input = input.replace("$averageComparesPresent", (double) comparesPresent/searchsPresent +"");
		return input;
		
	}
	
}
