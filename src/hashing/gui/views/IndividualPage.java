package hashing.gui.views;

import java.io.IOException;

import hashing.hashTables.PackingDensity;
import hashing.reader.TemplateReader;
import hashing.reader.TemplateReader.Template;
import hashing.resolutionMethods.HashingResolutionMethod;

public class IndividualPage {
	
	public static final String PATH = "templates/IndividualAlgorithm.html";

	public static String fillAll(HashingResolutionMethod method, PackingDensity density,
			int collisions, int keys,
			int compares, int searches) throws IOException {
		String input = TemplateReader.read(Template.INDIVIDUAL_PAGE);
		input = input.replace("$name", method.getName());
		input = input.replace("$density", density.getName());
		input = input.replace("$totalCollisions", collisions+"");
		input = input.replace("$totalKeys", keys+"");
		input = input.replace("$averageCollisions", (double) collisions/keys +"");
		input = input.replace("$totalCompares", compares+"");
		input = input.replace("$totalSearchs", searches+"");
		input = input.replace("$averageCompares", (double) compares/searches +"");
		return input;
		
	}
	
}
