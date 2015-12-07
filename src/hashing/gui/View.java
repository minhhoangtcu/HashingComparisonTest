package hashing.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSize;
	private JButton btnChooseFile;
	private JButton btnSort;
	private JComboBox<String> list;
	private JLabel lblFeedBack;
	private JLabel lblFile;
	private JTextPane paneHTMLOutput;
	private Control control;

	/**
	 * Create the frame.
	 */
	public View(Control control) {
		this.control = control;
		
		setTitle("Hasing Collisions Algorithms Comparision - Minh Hoang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[30%][20%][50%]", "[90%][10%]"));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0 3 1,grow");
		
		paneHTMLOutput = new JTextPane();
		paneHTMLOutput.setContentType("text/html");
		scrollPane.setViewportView(paneHTMLOutput);
		
		JPanel panelInput = new JPanel();
		contentPane.add(panelInput, "cell 0 1 2 1,grow");
		panelInput.setLayout(new MigLayout("", "[33%][33%,grow][33%]", "[50%][50%,grow]"));
		
		JLabel lblSortingSize = new JLabel("Collision Handling Method");
		panelInput.add(lblSortingSize, "cell 0 0,alignx trailing");
		
		textFieldSize = new JTextField();
		textFieldSize.setEditable(false);
		panelInput.add(textFieldSize, "cell 1 0,growx");
		textFieldSize.setColumns(10);
		
		btnSort = new JButton("Sort");
		btnSort.setEnabled(false);
		btnSort.setBackground(Color.WHITE);
		panelInput.add(btnSort, "cell 2 0,grow");
		
		JLabel lblSortingAlgorithm = new JLabel("Sorting Algorithm");
		panelInput.add(lblSortingAlgorithm, "cell 0 1,alignx trailing");
		
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>();
		list = new JComboBox<String>(comboModel);
		list.setEnabled(false);
		panelInput.add(list, "cell 1 1,grow");
		
		btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBackground(Color.WHITE);
		panelInput.add(btnChooseFile, "cell 2 1,growx");
		
		JPanel panelFeedback = new JPanel();
		contentPane.add(panelFeedback, "cell 2 1,grow");
		panelFeedback.setLayout(new MigLayout("", "[100%]", "[50%][50%]"));
		
		lblFeedBack = new JLabel("Feedback");
		lblFeedBack.setVisible(false);
		lblFeedBack.setForeground(Color.RED);
		lblFeedBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelFeedback.add(lblFeedBack, "cell 0 0,alignx center,growy");
		
		lblFile = new JLabel("please choose a file");
		lblFile.setVisible(false);
		lblFile.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panelFeedback.add(lblFile, "cell 0 1,alignx right,growy");
	}

	public JButton getBtnChooseFile() {
		return btnChooseFile;
	}
	public JButton getBtnSort() {
		return btnSort;
	}
	public void enableInputs() {
		textFieldSize.setEditable(true);
		list.setEnabled(true);
		btnSort.setEnabled(true);
	}
	public int getTextFieldSize() throws NumberFormatException {
		return Integer.parseInt(textFieldSize.getText());
	}
	public boolean isSizeValid() {
		String text = textFieldSize.getText();
		if (text.isEmpty()) {
			return false;
		} else {
			try {
				Integer.parseInt(text);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
	public int getSelectedSortingMethodIndex() {
		return list.getSelectedIndex();
	}
	public void setLblFeedBack(String text) {
		lblFeedBack.setVisible(true);
		lblFeedBack.setText(text);
	}
	
	public void setLblFile(String text) {
		lblFile.setVisible(true);
		lblFile.setText(text);;
	}
	public void setPaneHTMLOutput(String text) {
		paneHTMLOutput.setText(text);
	}
}
