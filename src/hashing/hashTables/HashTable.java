package hashing.hashTables;

import hashing.resolutionMethods.Hash;

public abstract class HashTable {
	
	protected int sizeOfTable;
	protected int numberOfPairs;
	protected String[] values;
	
	public HashTable(int sizeOfTable) {
		this.sizeOfTable = sizeOfTable;
		numberOfPairs = 0;
		values = new String[sizeOfTable];
	}

	protected int hash(String key) {
		return Hash.divisionHash(key);
	}
	
	public abstract int put(String key) throws TableFullException;
	public abstract int search(String key);
	
}
