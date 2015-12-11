package hashing.hashTables;

public enum PackingDensity {
	
	NINETY_PERCENT("90%", 5591),
	FIFTY_PERCENT("50%", 10007);
	
	private String name;
	private int size;
	private PackingDensity() { }
	private PackingDensity(String name, int size) {
		this.name = name;
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public int getSize() {
		return size;
	}
	public static String[] getAllNames() {
		String[] output = new String[PackingDensity.values().length];
		int i = 0;
		for (PackingDensity method: PackingDensity.values()) {
			output[i++] = method.getName();
		}
		return output;
	}
}
