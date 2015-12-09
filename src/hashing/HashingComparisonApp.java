package hashing;

import hashing.gui.Control;
import hashing.gui.Model;
import hashing.gui.View;

public class HashingComparisonApp {
	
	Control control;
	Model model;
	View view;
	
	public static void main(String[] args) {
		HashingComparisonApp app = new HashingComparisonApp();
		app.view.setVisible(true);
	}
	
	public HashingComparisonApp() {
		model = new Model(control);
		view = new View(control);
		control = new Control(model, view);
	}

}