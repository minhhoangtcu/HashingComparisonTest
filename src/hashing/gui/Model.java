package hashing.gui;

import java.io.IOException;

import hashing.hashTables.HashTable;
import hashing.hashTables.PackingDensity;
import hashing.hashTables.TableFullException;
import hashing.reader.AlphanumericReader;
import hashing.resolutionMethods.HashingResolutionMethod;

public class Model {
	
	private Control control;
	private String[] searchingKeys;
	private String[] insertingKeys;
	private String[] searchingMissingKeys;
	private String[] searchingPresentKeys;

	public Model() { }

	public void setControl(Control control) {
		this.control = control;
		try {
			insertingKeys = AlphanumericReader.getKeys(AlphanumericReader.RANDOM);
			searchingMissingKeys = AlphanumericReader.getKeys(AlphanumericReader.MISSING_KEYS);
			searchingPresentKeys = AlphanumericReader.getKeys(AlphanumericReader.PRESENT_KEYS);
			generateStats();
		} catch (IOException e) {
			System.err.println("MISSING FILES.");
			control.alertMissingFiles();
		} catch (TableFullException e) {
			System.err.println("TABLE FULL.");
		}
	}
	
	public void generateStats() throws TableFullException {
		int populate = 200;
		
		for (HashingResolutionMethod method: HashingResolutionMethod.values()) {
			System.out.println(method.getName() + " | Inserting");
			for (PackingDensity density: PackingDensity.values()) {
				System.out.println(getInsertingTime(populate, HashTable.getHashTable(method, density.getSize()), density));
			}
		}
		
		String[] randomKeys = getInsertingKeys();
		
		for (HashingResolutionMethod method: HashingResolutionMethod.values()) {
			System.out.println(method.getName() + " | Searching Miss");
			for (PackingDensity density: PackingDensity.values()) {
				HashTable table = HashTable.getHashTable(method, density.getSize());
				for (String key : randomKeys)
					table.put(key);
				System.out.println(getSearchingMissingTime(populate, table, density));
			}
		}
		for (HashingResolutionMethod method: HashingResolutionMethod.values()) {
			System.out.println(method.getName() + " | Searching Present");
			for (PackingDensity density: PackingDensity.values()) {
				HashTable table = HashTable.getHashTable(method, density.getSize());
				for (String key : randomKeys)
					table.put(key);
				System.out.println(getSearchingPresentTime(populate, table, density));
			}
		}
	}
	
	public long getInsertingTime(int populate, HashTable table, PackingDensity density) throws TableFullException {
		String[] randomKeys = getInsertingKeys();
		long tStart = System.currentTimeMillis();
		for (int i = 0; i < populate; i++) {
			for (String key : randomKeys) {
				table.put(key);
			}
		}
		return System.currentTimeMillis() - tStart;

	}
	
	public long getSearchingMissingTime(int populate, HashTable table, PackingDensity density) {
		String[] missingKeys = getSearchingMissingKeys();
		long tStart = System.currentTimeMillis();
		for (int i = 0; i < populate; i++) {
			for (String key : missingKeys) {
				table.search(key);
			}
		}
		return System.currentTimeMillis() - tStart;

	}
	
	public long getSearchingPresentTime(int populate, HashTable table, PackingDensity density) {
		String[] presentKeys = getSearchingPresentKeys();
		long tStart = System.currentTimeMillis();
		for (int i = 0; i < populate; i++) {
			for (String key : presentKeys) {
				table.search(key);
			}
		}
		return System.currentTimeMillis() - tStart;
	}

	public String[] getSearchingKeys() {
		return searchingKeys;
	}

	public void setSearchingKeys(String[] searchingKeys) {
		this.searchingKeys = searchingKeys;
	}

	public String[] getInsertingKeys() {
		return insertingKeys;
	}

	public void setInsertingKeys(String[] insertingKeys) {
		this.insertingKeys = insertingKeys;
	}

	public String[] getSearchingMissingKeys() {
		return searchingMissingKeys;
	}

	public String[] getSearchingPresentKeys() {
		return searchingPresentKeys;
	}

}