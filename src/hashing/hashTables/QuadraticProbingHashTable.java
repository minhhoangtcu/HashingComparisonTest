package hashing.hashTables;

public class QuadraticProbingHashTable extends HashTable {

	public QuadraticProbingHashTable(int sizeOfTable) {
		super(sizeOfTable);
	}

	/**
	 * Quadratic Probe Collision Handling inserting key algorithm according to
	 * Dr. Comer hand out with some modification
	 * 
	 * @param key
	 * @return number of collisions when putting this key inside the table
	 * @throws TableFullException
	 *             when the table is full
	 */
	public int put(String key) throws TableFullException {
		int index = hash(key) % sizeOfTable;
		int pointer = index;
		int i = 0;
		int collisions = 0;
		while (values[pointer] != null && !values[pointer].equals(key)) {
			i++;
			if (i > (sizeOfTable - 1) / 2)
				throw new TableFullException();
			pointer = (index + i ^ 2) % sizeOfTable;
			collisions++;
		}
		values[pointer] = key;
		numberOfPairs++;
		return collisions;
	}

	/**
	 * Quadratic Probe searching key method
	 * 
	 * @param key
	 * @return number of compares when searching this key inside the table
	 */
	public int search(String key) {
		int index = hash(key) % sizeOfTable;
		int pointer = index;
		int i = 0;
		KeyComparator tempComparator = new KeyComparator();
		while (values[pointer] != null && !tempComparator.isEquals(values[pointer], key)) {
			i++;
			if (i > (sizeOfTable - 1) / 2) //search failed
				return tempComparator.getNumberOfCompares();
			pointer = (index + i ^ 2) % sizeOfTable;
		}
		return tempComparator.getNumberOfCompares();
	}

}
