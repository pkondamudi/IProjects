import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BasicPanel {

	private TextField[][] sudokuInputsArray;
	private JPanel basicPanel;
	private String panelName;

	public BasicPanel() {
		sudokuInputsArray = SudokuInputBuilder.perpareInputsAsArray();
		basicPanel = GridPanelBuilder.buildBasicGridPanel(sudokuInputsArray);
	}

	public TextField[][] getSudokuInputsArray() {
		return sudokuInputsArray;
	}

	public void setSudokuInputsArray(TextField[][] sudokuInputsArray) {
		this.sudokuInputsArray = sudokuInputsArray;
	}

	public JPanel getBasicPanel() {
		return basicPanel;
	}

	public void setBasicPanel(JPanel basicPanel) {
		this.basicPanel = basicPanel;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public ArrayList<String> getRowValues(int row) {
		TextField inputTextField;
		ArrayList<String> currentRowValues = new ArrayList<String>();
		for (int inputCol = 0; inputCol < GridLayoutBuilder.MAX_COL; inputCol++) {
			inputTextField = sudokuInputsArray[row][inputCol];
			System.out.println(inputTextField.getText());
			currentRowValues.add(inputTextField.getText());
		}
		return currentRowValues;
	}

	public ArrayList<String> getColumnValues(int col) {
		TextField inputTextField;
		ArrayList<String> currentColumnValues = new ArrayList<String>();
		for (int inputRow = 0; inputRow < GridLayoutBuilder.MAX_ROW; inputRow++) {
			inputTextField = sudokuInputsArray[inputRow][col];
			System.out.println(inputTextField.getText());
			currentColumnValues.add(inputTextField.getText());
		}
		return currentColumnValues;
	}

}
