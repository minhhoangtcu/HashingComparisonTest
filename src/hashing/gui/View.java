package hashing.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import hashing.hashTables.PackingDensity;
import hashing.reader.TemplateReader;
import hashing.reader.TemplateReader.Template;
import hashing.resolutionMethods.HashingResolutionMethod;
import net.miginfocom.swing.MigLayout;

public class View extends JFrame {

	private JPanel contentPane;
	private JButton btnChooseFile;
	private JButton btnSearch;
	private JLabel lblFeedBack;
	private JLabel lblFile;
	private JTextPane paneHTMLOutput;
	private JComboBox<String> comboBoxCollisionMethod;
	private JLabel lblPackingDensity;
	private JComboBox<String> comboBoxPackingDensity;
	private Control control;

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Hasing Collisions Algorithms Comparision - Minh Hoang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[30%][20%][50%]", "[90%][10%]"));
		
		/*
		 * Output section
		 */
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0 3 1,grow");
		
		paneHTMLOutput = new JTextPane();
		paneHTMLOutput.setContentType("text/html");
		try {
			paneHTMLOutput.setText(TemplateReader.read(Template.WELCOME_PAGE));
		} catch (IOException e) {
			paneHTMLOutput.setText("file 'WelcomeScreen.html' is missing in the templates folder.\n Please resolve for the program to work normally");
		}
		scrollPane.setViewportView(paneHTMLOutput);
		
		/*
		 * Input section
		 */
		JPanel panelInput = new JPanel();
		contentPane.add(panelInput, "cell 0 1 2 1,grow");
		panelInput.setLayout(new MigLayout("", "[33%][33%,grow][33%]", "[33%][33%][33%]"));
		
		JLabel lblCollisionMethod = new JLabel("Collision Resolution Method:");
		panelInput.add(lblCollisionMethod, "cell 0 0,alignx right");
		
		comboBoxCollisionMethod = new JComboBox<String>(new DefaultComboBoxModel<String>(HashingResolutionMethod.getAllNames()));
		comboBoxCollisionMethod.setEnabled(false);
		panelInput.add(comboBoxCollisionMethod, "cell 1 0,growx");
		
		btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		btnSearch.setBackground(Color.WHITE);
		panelInput.add(btnSearch, "cell 2 0 1 3,growx");
		
		lblPackingDensity = new JLabel("Packing Density:");
		panelInput.add(lblPackingDensity, "cell 0 1,alignx trailing");
		
		comboBoxPackingDensity = new JComboBox<String>(new DefaultComboBoxModel<String>(PackingDensity.getAllNames()));
		comboBoxPackingDensity.setEnabled(false);
		panelInput.add(comboBoxPackingDensity, "cell 1 1,growx");
		
		JLabel lblAnalyzeKey = new JLabel("Analyze from Keys:");
		panelInput.add(lblAnalyzeKey, "cell 0 2,alignx trailing");
		
		btnChooseFile = new JButton("Choose Keys");
		btnChooseFile.setBackground(Color.WHITE);
		panelInput.add(btnChooseFile, "cell 1 2,growx");
		
		/*
		 * Feedback section
		 */
		JPanel panelFeedback = new JPanel();
		contentPane.add(panelFeedback, "cell 2 1,grow");
		panelFeedback.setLayout(new MigLayout("", "[100%]", "[50%][50%]"));
		
		lblFeedBack = new JLabel("Feedback");
		lblFeedBack.setVisible(false);
		lblFeedBack.setForeground(Color.RED);
		lblFeedBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelFeedback.add(lblFeedBack, "cell 0 0,alignx center,growy");
		
		lblFile = new JLabel("please choose a set of keys to search in the hash table");
		lblFile.setVisible(false);
		lblFile.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panelFeedback.add(lblFile, "cell 0 1,alignx right,growy");
	}

	public void setControl(Control control) {
		this.control = control;
	}

	/*
	 * Add Listeners to interactive elements
	 */
	public void addBtnChooseFileListener(ActionListener listener) {
		btnChooseFile.addActionListener(listener);
	}
	public void addBtnSearchListener(ActionListener listener) {
		btnSearch.addActionListener(listener);
	}
	
	/*
	 * Change text of display elements
	 */
	public void setLblFeedBack(String text) {
		lblFeedBack.setText(text);
	}
	public void setLblFile(String text) {
		lblFile.setText(text);
	}
	public void setPaneHTMLOutput(String text) {
		paneHTMLOutput.setText(text);
	}
	
	/*
	 * Turn visibility of elements on or off
	 */
	public void turnOnLblFeedBack() {
		lblFeedBack.setVisible(true);
	}
	public void turnOffLblFeedBack() {
		lblFeedBack.setVisible(false);
	}
	public void turnOnLblFile() {
		lblFile.setVisible(true);
	}
	public void turnOffLblFile() {
		lblFile.setVisible(false);
	}
	public void turnOnHashingResolutionMethodAndSearchButton() {
		comboBoxCollisionMethod.setEnabled(true);
		btnSearch.setEnabled(true);
	}
	
	/*
	 * Get selection from data driven elements
	 */
	public HashingResolutionMethod getChoosenHasingResolutionMethod() {
		return HashingResolutionMethod.values()[comboBoxCollisionMethod.getSelectedIndex()];
	}
	public PackingDensity getChoosenPackingDensity() {
		return PackingDensity.values()[comboBoxPackingDensity.getSelectedIndex()];
	}
		
}
