package hashing;

import java.io.IOException;

import hashing.gui.Control;
import hashing.gui.Model;
import hashing.gui.View;
import hashing.gui.views.WelcomePage;
import hashing.hashTables.TableFullException;

public class HashingComparisonApp {
	
	Control control;
	Model model;
	View view;
	
	public static void main(String[] args) throws IOException, TableFullException {
		HashingComparisonApp app = new HashingComparisonApp();
		app.view.setVisible(true);
	}
	
	public HashingComparisonApp() throws IOException, TableFullException {
		model = new Model(control);
		view = new View(control);
		control = new Control(model, view);
		view.setPaneHTMLOutput(WelcomePage.fillAll());
	}
}