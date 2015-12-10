package hashing.hashTables;

public class KeyComparator {
	
	private int numberOfCompares;
	
	public KeyComparator() {
		numberOfCompares = 0;
	}
	
	public boolean isEquals(String a, String b) {
		numberOfCompares++;
		return a.equals(b);
	}

	public int getNumberOfCompares() {
		return numberOfCompares;
	}

}
