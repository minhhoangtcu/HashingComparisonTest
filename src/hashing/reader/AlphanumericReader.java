package hashing.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class AlphanumericReader {
	
	public static final String PRESENT_KEYS = "data/PresentKeys.dat";
	public static final String MISSING_KEYS = "data/MissingKeys.dat";
	public static final String RANDOM = "data/Random.dat";

	/**
	 * <p> Return an array containing all the keys (lines).</p>
	 * @param size number of keys/lines the method is going to read from the file
	 * @param path path to the file
	 * @return an array containing all the keys 
	 * @throws IOException error indicating that the method cannot find the file with the provided path
	 */
	public static String[] getKeys(int size, String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		String[] output = new String[size];
		int i = 0;
		while (((line = reader.readLine()) != null) && (i < size)) {
			output[i] = line.trim();
			i++;
		}
		reader.close();
		return output;
	}
	
	/**
	 * <p> Return an array containing all the keys (lines).</p> 
	 * @param path path to the file
	 * @return an array containing all the keys  
	 * @throws IOException error indicating that the method cannot find the file with the provided path
	 */
	public static String[] getKeys(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		LinkedList<String> list = new LinkedList<>();
		while ((line = reader.readLine()) != null)
			list.add(line.trim());
		reader.close();
		return list.toArray(new String[list.size()]);
	}
	
}
