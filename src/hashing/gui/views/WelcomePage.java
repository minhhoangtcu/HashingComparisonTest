package hashing.gui.views;

import java.io.IOException;

import hashing.gui.Model;
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
	public static final String[] LINEAR_PROBE_50_IDS = {"$lp50Collisions", "$lp50ComparisonsMissing", "$lp50ComparisonsPresent", "$lp50TimeInsert", "$lp50TimeSearchMissing", "$lp50TimeSearchPresent"};
	public static final String[] LINEAR_PROBE_90_IDS = {"$lp90Collisions", "$lp90ComparisonsMissing", "$lp90ComparisonsPresent", "$lp90TimeInsert", "$lp90TimeSearchMissing", "$lp90TimeSearchPresent"};
	public static final String[] QUADRATIC_PROBE_50_IDS = {"$qp50Collisions", "$qp50ComparisonsMissing", "$qp50ComparisonsPresent", "$qp50TimeInsert", "$qp50TimeSearchMissing", "$qp50TimeSearchPresent"};
	public static final String[] QUADRATIC_PROBE_90_IDS = {"$qp90Collisions", "$qp90ComparisonsMissing", "$qp90ComparisonsPresent", "$qp90TimeInsert", "$qp90TimeSearchMissing", "$qp90TimeSearchPresent"};
	public static final String[] LINEAR_QUOTIENT_50_IDS = {"$lq50Collisions", "$lq50ComparisonsMissing", "$lq50ComparisonsPresent", "$lq50TimeInsert", "$lq50TimeSearchMissing", "$lq50TimeSearchPresent"};
	public static final String[] LINEAR_QUOTIENT_90_IDS = {"$lq90Collisions", "$lq90ComparisonsMissing", "$lq90ComparisonsPresent", "$lq90TimeInsert", "$lq90TimeSearchMissing", "$lq90TimeSearchPresent"};
	
	public static String fillAll(Model model) throws IOException, TableFullException {
		String input = TemplateReader.read(Template.WELCOME_PAGE);
		for (PackingDensity density: PackingDensity.values()) {
			LinearProbingHashTable linearTable = new LinearProbingHashTable(density.getSize());
			QuadraticProbingHashTable quadTable = new QuadraticProbingHashTable(density.getSize());
			LinearQuotientHashTable quotTable = new LinearQuotientHashTable(density.getSize());
			input = fillUpOneMethod(model, input, linearTable, density);
			input = fillUpOneMethod(model, input, quadTable, density);
			input = fillUpOneMethod(model, input, quotTable, density);
		}
		return input;
	}
	
	public static String fillUpOneMethod(Model model, String input, HashTable table, PackingDensity density) throws IOException, TableFullException {
		int POPULATE = 200;
		
		String[] randomKeys = populateArray(model.getInsertingKeys(), POPULATE);
		int totalCollisions = 0;
		long tStart = System.currentTimeMillis();
		for (String key : randomKeys) {
			totalCollisions += table.put(key);
		}
		long insertingTime = (long) System.currentTimeMillis() - tStart;

		
		String[] presentKeys = populateArray(AlphanumericReader.getKeys(AlphanumericReader.PRESENT_KEYS), POPULATE);
		tStart = System.currentTimeMillis();
		int totalComparesPresent = 0;
		for (String key : presentKeys) {
			totalComparesPresent += table.search(key);
		}
		long searchingMissTime = (long) System.currentTimeMillis() - tStart;

		String[] missingKeys = populateArray(AlphanumericReader.getKeys(AlphanumericReader.MISSING_KEYS), POPULATE);
		tStart = System.currentTimeMillis();
		int totalComparesMissing = 0;
		for (String key : missingKeys) {
			totalComparesMissing += table.search(key);
		}
		long searchingPresentTime = (long) System.currentTimeMillis() - tStart;

		return WelcomePage.fillUpOneRow(input, table.getMethod(), density,
				(double) totalCollisions / randomKeys.length,
				(double) totalComparesMissing / missingKeys.length,
				(double) totalComparesPresent / presentKeys.length,
				insertingTime, searchingMissTime, searchingPresentTime);
	}
	
	public static String fillUpOneRow(String input,
			HashingResolutionMethod method, PackingDensity density,
			double collisions, double comparesMissing, double comparesPresent,
			long insertingTime, long searchingMissTime, long searchingPresentTime) throws IOException {
		String[] ids = new String[6];
		
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
		input = input.replace(ids[3], insertingTime+"");
		input = input.replace(ids[4], searchingMissTime+"");
		input = input.replace(ids[5], searchingPresentTime+"");
		return input;
	}
	
	/**
	 * Expand the array with the number of times provided. This method will copy elements from old array to make a new array with a lot more elements.
	 * @param times
	 * @return a new and bigger array
	 */
	public static String[] populateArray(String[] old, int times) {
		String[] newArray = new String[old.length*times];
		int oldIndex = 0;
		for (int newIndex = 0; newIndex < newArray.length; newIndex++) {
			newArray[newIndex] = old[oldIndex];
			oldIndex++;
			if (oldIndex == old.length)
				oldIndex = 0;
		}
		return newArray;
	}
	
}
