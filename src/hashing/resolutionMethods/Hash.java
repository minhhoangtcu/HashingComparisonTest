package hashing.resolutionMethods;

public class Hash {

	/**
	 * <p>
	 * Use Horner's Rule to Hash an alphanumeric string with 4 chars. And, each
	 * char will take 8 bits
	 * </p>
	 * 
	 * @param key
	 *            an alphanumeric key with 4 chars
	 * @return an integer value represent the key
	 * @throws IllegalArgumentException
	 *             there are more or less than 4 chars
	 */
	public static int divisionHash(String key) throws IllegalArgumentException {
		if (key.trim().length() != key.length())
			throw new IllegalArgumentException("Invalid key. Space before/after key.");
		else {
			int output = 0;
			for (int i = 0; i < key.length(); i++) {
				int shift = (key.length() - 1 - i) * 8;
				output += (int) key.charAt(0) << shift;
			}
			return output;
		}
	}

}
