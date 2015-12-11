package hashing.gui;

import java.io.IOException;

import hashing.reader.AlphanumericReader;

public class Model {
	
	private Control control;
	private String[] searchingKeys;
	private String[] insertingKeys;

	public Model() { }

	public void setControl(Control control) {
		this.control = control;
		try {
			insertingKeys = AlphanumericReader.getKeys(AlphanumericReader.RANDOM);
		} catch (IOException e) {
			System.err.println("MISSING FILES.");
			control.alertMissingFiles();
		}
	}

	public String[] getSearchingKeys() {
		return searchingKeys;
	}

	public void setSearchingKeys(String[] searchingKeys) {
		this.searchingKeys = searchingKeys;
	}

	public String[] getInsertingKeys() {
		return insertingKeys;
	}

	public void setInsertingKeys(String[] insertingKeys) {
		this.insertingKeys = insertingKeys;
	}
}