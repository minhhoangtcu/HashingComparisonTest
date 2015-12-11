package hashing.gui.controllers;

import hashing.gui.Model;
import hashing.gui.View;

public class Controller {
	
	protected Model model;
	protected View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public Controller(View view) {
		this.view = view;
	}

}
