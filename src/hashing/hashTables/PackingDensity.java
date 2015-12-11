package hashing.hashTables;

public enum PackingDensity {
	
	NINETY_PERCENT("90%", 5591),
	EIGHTY_PERCENT("80%", 6247),
	SEVENTY_PERCENT("70%", 7129),
	SIXTY_PERCENT("60%", 8297),
	FIFTY_PERCENT("50%", 10007),
	FOURTY_PERCENT("40%", 12503),
	THRITY_PERCENT("30%", 16661),
	TWENTY_PERCENT("20%", 25013),
	TEN_PERCENT("10%", 50021);
	
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
