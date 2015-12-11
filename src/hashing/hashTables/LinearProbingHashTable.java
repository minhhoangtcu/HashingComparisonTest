package hashing.hashTables;

import hashing.resolutionMethods.HashingResolutionMethod;

/**
 * <p>
 * This hash table use linear probing algorithm as a collision resolution
 * method. Due to the purpose of the program, which is to test the performance
 * of collision handling algorithms, keys are not necessary. The size of the
 * table is necessary because it is going to be fixed.
 * </p>
 * 
 * @author minhhoang
 */
public class LinearProbingHashTable extends HashTable {

	public LinearProbingHashTable(int sizeOfTable) {
		super(sizeOfTable, HashingResolutionMethod.LINEAR_PROBING);
	}

	/**
	 * Linear Probe Collision Handling inserting key algorithm according to Dr.
	 * Comer hand out with some modification.
	 * 
	 * @param key
	 * @return number of collisions when putting this key inside the table
	 * @throws TableFullException
	 *             when the table is full
	 */
	public int put(String key) throws TableFullException {
		int index = hash(key) % sizeOfTable;
		int pointer = index;
		int collisions = 0;
		while (values[pointer] != null && !values[pointer].equals(key)) {
			pointer = (pointer + 1) % sizeOfTable;
			if (pointer == index)
				throw new TableFullException();
			collisions++;
		}
//		collisions++; // enable this means show the number of probes it took to insert
		values[pointer] = key;
		numberOfPairs++;
		return collisions;
	}

	/**
	 * Linear Probe searching key method.
	 * 
	 * @param key
	 * @return number of compares when searching this key inside the table
	 */
	public int search(String key) {
		int index = hash(key) % sizeOfTable;
		int pointer = index;
		KeyComparator tempComparator = new KeyComparator();
		while (values[pointer] != null && !tempComparator.isEquals(values[pointer], key)) {
			pointer = (pointer + 1) % sizeOfTable;
			if (pointer == index) { // search failed
				return tempComparator.getNumberOfCompares();
			}
		}
		return tempComparator.getNumberOfCompares();
	}

}
