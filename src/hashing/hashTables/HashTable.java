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

	public static HashTable getHashTable(HashingResolutionMethod method, int sizeOfTable) {
		switch (method) {
		case LINEAR_PROBING:
			return new LinearProbingHashTable(sizeOfTable);
		case QUADRATIC_PROBING:
			return new QuadraticProbingHashTable(sizeOfTable);
		case LINEAR_QUOTIENT:
			return new LinearQuotientHashTable(sizeOfTable);
		default:
			return null;
		}
	}
}
