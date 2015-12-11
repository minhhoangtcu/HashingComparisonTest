package hashing.gui;

public class Model {
	
	private Control control;
	private String[] searchingKeys;
	private String[] insertingKeys;

	public Model(Control control) {
		this.control = control;
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