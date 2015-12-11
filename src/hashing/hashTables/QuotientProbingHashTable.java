package hashing.hashTables;

public class QuotientProbingHashTable extends HashTable {

	public QuotientProbingHashTable(int sizeOfTable) {
		super(sizeOfTable);
	}

	/**
	 * Quotient Probe Collision Handling inserting key algorithm according to
	 * Dr. Comer hand out with some modification
	 * 
	 * @param key
	 * @return number of collisions when putting this key inside the table
	 * @throws TableFullException
	 *             when the table is full
	 */
	public int put(String key) throws TableFullException {
		int hashed = hash(key);
		int index = hashed % sizeOfTable;
		int quotient = hashed / sizeOfTable;
		int pointer = index;
		int i = 0;
		int collisions = 0;

		if (quotient == 0) // this happens when hash(key) < tableSize
			quotient = 1;
		while (values[pointer] != null && !values[pointer].equals(key)) {
			i++;
			if (i == sizeOfTable - 1)
				throw new TableFullException();
			pointer = (index + i * quotient) % sizeOfTable;
			collisions++;
		}
		values[pointer] = key;
		numberOfPairs++;
		return collisions;
	}

	/**
	 * Quotient Probe searching key method
	 * 
	 * @param key
	 * @return number of compares when searching this key inside the table
	 */
	public int search(String key) {
		int hashed = hash(key);
		int index = hashed % sizeOfTable;
		int quotient = hashed / sizeOfTable;
		int pointer = index;
		int i = 0;
		KeyComparator tempComparator = new KeyComparator();

		if (quotient == 0) // this happens when hash(key) < tableSize
			quotient = 1;
		while (values[pointer] != null && !tempComparator.isEquals(values[pointer], key)) {
			i++;
			if (i == sizeOfTable - 1) // search failed
				return tempComparator.getNumberOfCompares();
			pointer = (index + i * quotient) % sizeOfTable;
		}
		return tempComparator.getNumberOfCompares();
	}

}
