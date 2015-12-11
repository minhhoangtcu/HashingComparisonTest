package hashing.gui;

import hashing.gui.controllers.ChooseFileController;
import hashing.gui.controllers.SearchController;

public class Control {
	
	View view;
	Model model;

	public Control(Model model, View view) {
		this.view = view;
		this.model = model;
		
		view.addBtnChooseFileListener(new ChooseFileController(model, view));
		view.addBtnSearchListener(new SearchController());
	}

}
