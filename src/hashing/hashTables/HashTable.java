package hashing.hashTables;

import hashing.resolutionMethods.Hash;
import hashing.resolutionMethods.HashingResolutionMethod;

public abstract class HashTable {
	
	protected int sizeOfTable;
	protected int numberOfPairs;
	protected String[] values;
	protected HashingResolutionMethod method;
	
	public HashTable(int sizeOfTable, HashingResolutionMethod method) {
		this.sizeOfTable = sizeOfTable;
		this.method = method;
		numberOfPairs = 0;
		values = new String[sizeOfTable];
	}

	protected int hash(String key) {
		return Hash.divisionHash(key);
	}
	
	public abstract int put(String key) throws TableFullException;
	public abstract int search(String key);
	public HashingResolutionMethod getMethod() {
		return method;
	}
}
