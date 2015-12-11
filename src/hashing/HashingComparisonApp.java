/**
 * Author: Minh Hoang
 * Project Name: Hashing Collision Handling Comparison
 * Features:
 * 	Select a file containing a lot of keys to test inserting into the predefined table.
 * 	Display the data nicely using html. Consumer can change the template of what to display with the html files.
 * 	Analyze algorithms based on time and packing density. To enable this, please remove the comment in class Model.
 */
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
		model = new Model();
		view = new View();
		control = new Control(model, view);
		model.setControl(control);
		view.setControl(control);
		view.setPaneHTMLOutput(WelcomePage.fillAll(model));
	}
}