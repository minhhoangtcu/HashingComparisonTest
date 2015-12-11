package hashing.gui.views;

import java.io.IOException;

import hashing.hashTables.HashTable;
import hashing.hashTables.LinearProbingHashTable;
import hashing.hashTables.LinearQuotientHashTable;
import hashing.hashTables.PackingDensity;
import hashing.hashTables.QuadraticProbingHashTable;
import hashing.hashTables.TableFullException;
import hashing.reader.AlphanumericReader;
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
	
	public static String fillAll() throws IOException, TableFullException {
		String input = TemplateReader.read(Template.WELCOME_PAGE);
		for (PackingDensity density: PackingDensity.values()) {
			LinearProbingHashTable linearTable = new LinearProbingHashTable(density.getSize());
			QuadraticProbingHashTable quadTable = new QuadraticProbingHashTable(density.getSize());
			LinearQuotientHashTable quotTable = new LinearQuotientHashTable(density.getSize());
			input = fillUpOneMethod(input, linearTable, density);
			input = fillUpOneMethod(input, quadTable, density);
			input = fillUpOneMethod(input, quotTable, density);
		}
		return input;
	}
	
	public static String fillUpOneMethod(String input, HashTable table, PackingDensity density) throws IOException, TableFullException {
		String[] randomKeys = AlphanumericReader.getKeys(AlphanumericReader.RANDOM);
		int totalCollisions = 0;
		for (String key : randomKeys) {
			totalCollisions += table.put(key);
		}

		String[] presentKeys = AlphanumericReader.getKeys(AlphanumericReader.PRESENT_KEYS);
		int totalComparesPresent = 0;
		for (String key : presentKeys) {
			totalComparesPresent += table.search(key);
		}

		String[] missingKeys = AlphanumericReader.getKeys(AlphanumericReader.MISSING_KEYS);
		int totalComparesMissing = 0;
		for (String key : missingKeys) {
			totalComparesMissing += table.search(key);
		}

		return WelcomePage.fillUpOneRow(input, table.getMethod(), density,
				(double) totalCollisions / randomKeys.length,
				(double) totalComparesMissing / missingKeys.length,
				(double) totalComparesPresent / presentKeys.length);
	}
	
	public static String fillUpOneRow(String input, HashingResolutionMethod method, PackingDensity density, double collisions, double comparesMissing, double comparesPresent) throws IOException {
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
