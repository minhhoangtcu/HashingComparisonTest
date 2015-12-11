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

		int populate = 200;

		PackingDensity density = PackingDensity.FIFTY_PERCENT;
		LinearProbingHashTable linearTable = new LinearProbingHashTable(density.getSize());
		QuadraticProbingHashTable quadTable = new QuadraticProbingHashTable(density.getSize());
		LinearQuotientHashTable quotTable = new LinearQuotientHashTable(density.getSize());
		input = fillUpOneMethod(model, input, linearTable, density);
		input = fillUpOneMethodTime(populate, model, input, linearTable, density);
		input = fillUpOneMethod(model, input, quadTable, density);
		input = fillUpOneMethodTime(populate, model, input, quadTable, density);
		input = fillUpOneMethod(model, input, quotTable, density);
		input = fillUpOneMethodTime(populate, model, input, quotTable, density);

		density = PackingDensity.NINETY_PERCENT;
		linearTable = new LinearProbingHashTable(density.getSize());
		quadTable = new QuadraticProbingHashTable(density.getSize());
		quotTable = new LinearQuotientHashTable(density.getSize());
		input = fillUpOneMethod(model, input, linearTable, density);
		input = fillUpOneMethodTime(populate, model, input, linearTable, density);
		input = fillUpOneMethod(model, input, quadTable, density);
		input = fillUpOneMethodTime(populate, model, input, quadTable, density);
		input = fillUpOneMethod(model, input, quotTable, density);
		input = fillUpOneMethodTime(populate, model, input, quotTable, density);
		return input;
	}
	
	
	public static String fillUpOneMethod(Model model, String input, HashTable table, PackingDensity density)
			throws IOException, TableFullException {
		String[] randomKeys = model.getInsertingKeys();
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

		return WelcomePage.fillUpOneRow(input, table.getMethod(), density, (double) totalCollisions / randomKeys.length,
				(double) totalComparesMissing / missingKeys.length, (double) totalComparesPresent / presentKeys.length);
	}
	
	public static String fillUpOneMethodTime(int populate, Model model, String input, HashTable table,
			PackingDensity density) throws IOException, TableFullException {
		String[] randomKeys = model.getInsertingKeys();
		long tStart = System.currentTimeMillis();
		for (int i = 0; i < populate; i++) {
			for (String key : randomKeys) {
				table.put(key);
			}
		}
		long insertingTime = System.currentTimeMillis() - tStart;

		String[] missingKeys = model.getSearchingMissingKeys();
		tStart = System.currentTimeMillis();
		for (int i = 0; i < populate; i++) {
			for (String key : missingKeys) {
				table.search(key);
			}
		}
		long searchingMissTime = System.currentTimeMillis() - tStart;

		String[] presentKeys = model.getSearchingPresentKeys();
		tStart = System.currentTimeMillis();
		for (int i = 0; i < populate; i++) {
			for (String key : presentKeys) {
				table.search(key);
			}
		}
		long searchingPresentTime = System.currentTimeMillis() - tStart;

		return WelcomePage.fillUpOneRowTime(input, table.getMethod(), density, insertingTime, searchingMissTime,
				searchingPresentTime);
	}
	
	public static String fillUpOneRow(String input, HashingResolutionMethod method, PackingDensity density,
			double collisions, double comparesMissing, double comparesPresent) throws IOException {
		String[] ids = getIDs(method, density);

		input = input.replace(ids[0], collisions + "");
		input = input.replace(ids[1], comparesMissing + "");
		input = input.replace(ids[2], comparesPresent + "");

		return input;
	}
	
	public static String fillUpOneRowTime(String input, HashingResolutionMethod method, PackingDensity density,
			long insertingTime, long searchingMissTime, long searchingPresentTime) {
		String[] ids = getIDs(method, density);
		input = input.replace(ids[3], insertingTime + "");
		input = input.replace(ids[4], searchingMissTime + "");
		input = input.replace(ids[5], searchingPresentTime + "");
		return input;
	}
	
	private static String[] getIDs(HashingResolutionMethod method, PackingDensity density) {
		switch (method) {
		case LINEAR_PROBING:
			if (density == PackingDensity.FIFTY_PERCENT)
				return LINEAR_PROBE_50_IDS;
			else
				return LINEAR_PROBE_90_IDS;
		case LINEAR_QUOTIENT:
			if (density == PackingDensity.FIFTY_PERCENT)
				return QUADRATIC_PROBE_50_IDS;
			else
				return QUADRATIC_PROBE_90_IDS;
		case QUADRATIC_PROBING:
			if (density == PackingDensity.FIFTY_PERCENT)
				return LINEAR_QUOTIENT_50_IDS;
			else
				return LINEAR_QUOTIENT_90_IDS;
		default:
			return null;
		}
	}
}
