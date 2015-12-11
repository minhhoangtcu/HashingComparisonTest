package hashing.hashTables;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableTest {

	@Test
	public void linearProbingTest1() throws TableFullException {
		LinearProbingHashTable table = new LinearProbingHashTable(5);

		// Insert new keys
		int collisions = table.put("0");
		assertEquals(0, collisions);
		collisions = table.put("1");
		assertEquals(0, collisions);
		collisions = table.put("2");
		assertEquals(0, collisions);
		collisions = table.put("3");
		assertEquals(0, collisions);
		collisions = table.put("4");
		assertEquals(0, collisions);

		// Search for keys
		int compares = table.search("0");
		assertEquals(1, compares);
		compares = table.search("1");
		assertEquals(1, compares);
		compares = table.search("2");
		assertEquals(1, compares);
		compares = table.search("3");
		assertEquals(1, compares);
		compares = table.search("4");
		assertEquals(1, compares);
		compares = table.search("5");
		assertEquals(5, compares);
	}

	@Test
	public void linearProbingTest2() throws TableFullException {
		LinearProbingHashTable table = new LinearProbingHashTable(5);

		// Insert new keys
		int collisions = table.put("0");
		assertEquals(0, collisions);
		collisions = table.put("1");
		assertEquals(0, collisions);
		collisions = table.put("2");
		assertEquals(0, collisions);
		collisions = table.put("4");
		assertEquals(0, collisions);

		// Search for keys
		int compares = table.search("3"); // 3 hash to 1. Empty at 1 -> compare
											// should be 0
		assertEquals(0, compares);
		compares = table.search("7"); // 7 hash to 0. Slot at 0, linear probe to
										// 1. But empty -> compare should be 1
		assertEquals(1, compares);
		compares = table.search("@"); // @ hash to 4. Slot at 4, linear probe to
										// 0, 1. But empty -> compare should be
										// 2
		assertEquals(2, compares);
		compares = table.search("9"); // 9 hash to 2. Slot at 2, linear probe to
										// 3, 4, 0. But empty -> compare should
										// be 3
		assertEquals(4, compares);
	}

	@Test
	public void quadraticProbingTest1() throws TableFullException {
		LinearProbingHashTable table = new LinearProbingHashTable(5);

		// Insert new keys
		int collisions = table.put("0");
		assertEquals(0, collisions);
		collisions = table.put("1");
		assertEquals(0, collisions);
		collisions = table.put("2");
		assertEquals(0, collisions);
		collisions = table.put("3");
		assertEquals(0, collisions);
		collisions = table.put("4");
		assertEquals(0, collisions);

		// Search for keys
		int compares = table.search("0");
		assertEquals(1, compares);
		compares = table.search("1");
		assertEquals(1, compares);
		compares = table.search("2");
		assertEquals(1, compares);
		compares = table.search("3");
		assertEquals(1, compares);
		compares = table.search("4");
		assertEquals(1, compares);
		compares = table.search("5");
		assertEquals(5, compares);

	}

	public void quadraticProbingTest2() throws TableFullException {
		LinearProbingHashTable table = new LinearProbingHashTable(5);

		// Insert new keys
		int collisions = table.put("0");
		assertEquals(0, collisions);
		collisions = table.put("1");
		assertEquals(0, collisions);
		collisions = table.put("2");
		assertEquals(0, collisions);
		collisions = table.put("4");
		assertEquals(0, collisions);

		// Search for keys
		int compares = table.search("3"); // 3 hash to 1. Empty at 1 -> compare
											// should be 0
		assertEquals(0, compares);
		compares = table.search("7"); // 7 hash to 0. Slot at 0, quad probe to
										// 1. But empty -> compare should be 1
		assertEquals(1, compares);
		compares = table.search("@"); // @ hash to 4. Check 4, because not empty
										// and not equals to @ -> continue with
										// +1 compare (i = 1)
										// hash to 0 because (4 + 1) mod 5 = 0
										// for i=1. because not empty and not
										// equals to @ -> continue with +1
										// compare (i = 2)
										// hash to 3 because (4 + 4) mod 5 = 3
										// for i=2. because not empty and not
										// equals to @ -> continue with +1
										// compare but i = 3, so stop
										// total: 3 compares
		assertEquals(3, compares);
		compares = table.search("9"); // 9 hash to 2. Check 2, because not empty
										// and not equals to 9 -> continue with
										// +1 compare (i=1)
										// hash to 3 because (2 + 1) mod 5 = 3
										// for i=1. because not empty and not
										// equals to 9 -> continue with +1
										// compare (i=2)
										// hash to because (2 + 4) mod 5 = 1.
										// But empty, so stop
										// total: 2 compares
		assertEquals(2, compares);
	}
	
	@Test
	public void quoutientProbingTest1() throws TableFullException {
		QuotientProbingHashTable table = new QuotientProbingHashTable(5);

		// Insert new keys
		int collisions = table.put("0");
		assertEquals(0, collisions);
		collisions = table.put("1");
		assertEquals(0, collisions);
		collisions = table.put("2");
		assertEquals(0, collisions);
		collisions = table.put("3");
		assertEquals(0, collisions);
		collisions = table.put("4");
		assertEquals(0, collisions);

		// Search for keys
		int compares = table.search("0");
		assertEquals(1, compares);
		compares = table.search("1");
		assertEquals(1, compares);
		compares = table.search("2");
		assertEquals(1, compares);
		compares = table.search("3");
		assertEquals(1, compares);
		compares = table.search("4");
		assertEquals(1, compares);
		compares = table.search("5");
		assertEquals(4, compares);

	}
	
	@Test
	public void quoutientProbingTest2() throws TableFullException {
		QuotientProbingHashTable table = new QuotientProbingHashTable(5);

		// Insert new keys
		int collisions = table.put("0");
		assertEquals(0, collisions);
		collisions = table.put("1");
		assertEquals(0, collisions);
		collisions = table.put("2");
		assertEquals(0, collisions);
		collisions = table.put("4");
		assertEquals(0, collisions);

		// Search for keys
		int compares = table.search("7"); // 7 hash to 0.
		assertEquals(1, compares);
		compares = table.search("@"); // @ hash to 4.
		assertEquals(1, compares);
		compares = table.search("9"); // 9 hash to 2.
		assertEquals(1, compares);
	}

}
