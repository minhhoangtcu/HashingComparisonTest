package hashing.gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import hashing.gui.Model;
import hashing.gui.View;
import hashing.reader.AlphanumericReader;

public class ChooseFileController extends Controller implements ActionListener {
	
	public ChooseFileController(Model model, View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Choose a file with keys to search in the hash tables.");
		
		int returnVal = fc.showOpenDialog(view);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			setKeysOnModel(path);
			view.setLblFile(selectedFile.getName());
			view.turnOnHashingResolutionMethodAndSearchButton();
		}
	}
	
	private void setKeysOnModel(String path) {
		String[] output;
		try {
			output = AlphanumericReader.getKeys(path);
			model.setSearchingKeys(output);
		} catch (IOException e) {
			view.setLblFeedBack("File not found!");
		}
	}
}
