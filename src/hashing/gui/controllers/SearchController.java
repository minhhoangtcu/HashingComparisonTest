package hashing.gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import hashing.gui.Model;
import hashing.gui.View;
import hashing.gui.views.IndividualPage;
import hashing.hashTables.HashTable;
import hashing.hashTables.PackingDensity;
import hashing.hashTables.TableFullException;
import hashing.resolutionMethods.HashingResolutionMethod;

public class SearchController extends Controller implements ActionListener {

	public SearchController(Model model, View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PackingDensity density = view.getChoosenPackingDensity();
		HashingResolutionMethod method = view.getChoosenHasingResolutionMethod();
		HashTable table = HashTable.getHashTable(method, density.getSize());
		
		try {
			int totalCollisions = 0;
			for (String key: model.getInsertingKeys()) {
				totalCollisions += table.put(key);
			}
			int totalCompares = 0;
			for (String key : model.getSearchingKeys()) {
				totalCompares += table.search(key);
			}
			
			view.setPaneHTMLOutput(IndividualPage.fillAll(method, density, totalCollisions, model.getInsertingKeys().length, totalCompares, model.getSearchingKeys().length));
			view.setLblFeedBack("Successfully analyzied the keys");
			view.turnOnLblFeedBack();
		} catch (TableFullException e1) {
			view.turnOnLblFeedBack();
			view.setLblFeedBack("Table size is too small!");
		} catch (IOException e1) {
			view.turnOnLblFeedBack();
			view.setLblFeedBack("Templete file is missing! Please reinstall!");
		}
		
		
	}

}
