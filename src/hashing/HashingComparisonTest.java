package hashing;

import java.io.IOException;
import hashing.gui.Control;
import hashing.gui.Model;
import hashing.gui.View;
import hashing.gui.views.WelcomePage;
import hashing.hashTables.LinearProbingHashTable;
import hashing.hashTables.PackingDensity;
import hashing.hashTables.TableFullException;
import hashing.reader.AlphanumericReader;
import hashing.resolutionMethods.HashingResolutionMethod;

public class HashingComparisonTest {

	Control control;
	Model model;
	View view;

	public static void main(String[] args) throws IOException, TableFullException {
		HashingComparisonTest app = new HashingComparisonTest();
		app.view.setVisible(true);
	}

	public HashingComparisonTest() throws IOException, TableFullException {
		model = new Model(control);
		view = new View(control);
		control = new Control(model, view);

		// System.out.println("Linear Probing - for 50% packing density or 1/2
		// load factor");
		// String[] randomKeys =
		// AlphanumericReader.getKeys(AlphanumericReader.RANDOM);
		// LinearProbingHashTable linearTable = new
		// LinearProbingHashTable(PackingDensity.FIFTY_PERCENT.getSize());
		// int totalCollisions = 0;
		// int counter = 0;
		// for (String key : randomKeys) {
		// totalCollisions += linearTable.put(key);
		// }
		// System.out.println("Average number of collisions: " +
		// (double)totalCollisions/PackingDensity.FIFTY_PERCENT.getSize());
		// String[] presentKeys =
		// AlphanumericReader.getKeys(AlphanumericReader.PRESENT_KEYS);
		// int totalCompares = 0;
		// for (String key: presentKeys) {
		// totalCompares += linearTable.search(key);
		// }
		// System.out.println("Average number of compares for present keys: " +
		// (double)totalCompares/presentKeys.length);
		// String[] missingKeys =
		// AlphanumericReader.getKeys(AlphanumericReader.MISSING_KEYS);
		// totalCompares = 0;
		// for (String key: missingKeys) {
		// totalCompares += linearTable.search(key);
		// }
		// System.out.println("Average number of compares for missing keys: " +
		// (double)totalCompares/missingKeys.length);
		//
		// System.out.println("\nLinear Probing - for 90% packing density or .9
		// load factor");
		// linearTable = new
		// LinearProbingHashTable(PackingDensity.NINETY_PERCENT.getSize());
		// totalCollisions = 0;
		// counter = 0;
		// for (String key : randomKeys) {
		// totalCollisions += linearTable.put(key);
		// }
		// System.out.println("Average number of collisions: " +
		// (double)totalCollisions/PackingDensity.NINETY_PERCENT.getSize());
		// totalCompares = 0;
		// for (String key: presentKeys) {
		// totalCompares += linearTable.search(key);
		// }
		// System.out.println("Average number of compares for present keys: " +
		// (double)totalCompares/presentKeys.length);
		// totalCompares = 0;
		// for (String key: missingKeys) {
		// totalCompares += linearTable.search(key);
		// }
		// System.out.println("Average number of compares for missing keys: " +
		// (double)totalCompares/missingKeys.length);

		LinearProbingHashTable linearTable = new LinearProbingHashTable(PackingDensity.FIFTY_PERCENT.getSize());
		String[] randomKeys = AlphanumericReader.getKeys(AlphanumericReader.RANDOM);
		int totalCollisions = 0;
		for (String key : randomKeys) {
			totalCollisions += linearTable.put(key);
		}

		String[] presentKeys = AlphanumericReader.getKeys(AlphanumericReader.PRESENT_KEYS);
		int totalComparesPresent = 0;
		for (String key : presentKeys) {
			totalComparesPresent += linearTable.search(key);
		}

		String[] missingKeys = AlphanumericReader.getKeys(AlphanumericReader.MISSING_KEYS);
		int totalComparesMissing = 0;
		for (String key : missingKeys) {
			totalComparesMissing += linearTable.search(key);
		}

		view.setPaneHTMLOutput(WelcomePage.generateContent(HashingResolutionMethod.LINEAR_PROBING,
				PackingDensity.FIFTY_PERCENT, (double) totalCollisions / randomKeys.length,
				(double) totalComparesMissing / missingKeys.length,
				(double) totalComparesPresent / presentKeys.length));
	}

}
