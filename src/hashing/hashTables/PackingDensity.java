package hashing.hashTables;

public enum PackingDensity {
	
	NINETY_PERCENT("90%"),
	FIFTY_PERCENT("50%");
	
	private String name;
	private PackingDensity() { }
	private PackingDensity(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
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
