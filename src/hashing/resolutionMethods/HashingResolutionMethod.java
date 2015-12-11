package hashing.resolutionMethods;

public enum HashingResolutionMethod {
	LINEAR_PROBING("Linear Probing"),
	QUADRATIC_PROBING("Quadratic Probing"),
	LINEAR_QUOTIENT("Linear Quotient");
	
	private String name;
	
	private HashingResolutionMethod() { }
	
	private HashingResolutionMethod(String name) {
		this.name = name; 
	}
	
	public static String[] getAllNames() {
		String[] output = new String[HashingResolutionMethod.values().length];
		int i = 0;
		for (HashingResolutionMethod method: HashingResolutionMethod.values()) {
			output[i++] = method.getName();
		}
		return output;
	}

	public String getName() {
		return name;
	}
}