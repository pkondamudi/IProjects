import java.awt.TextField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class SudokuMaker {

	private static JFrame sudoku;
	private static BasicPanel pivotBasicPanel;
	private static BasicPanel[][] sudokuPanel = new BasicPanel[GridLayoutBuilder.MAX_ROW][GridLayoutBuilder.MAX_COL];
	private static EventListener eventListener = new EventListener();
	private static boolean emptyValuesExists = false;
	private static boolean invalidValueExists = false;

	static {
		for (int row = 0; row < GridLayoutBuilder.MAX_ROW; row++) {
			for (int col = 0; col < GridLayoutBuilder.MAX_COL; col++) {
				pivotBasicPanel = new BasicPanel();
				sudokuPanel[row][col] = pivotBasicPanel;
			}
		}
	}

	private SudokuMaker() {
	}

	public static JFrame playSudoku() {
		sudoku = new JFrame("Sudoku.");
		sudoku.setSize(400, 400);
		sudoku.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		sudoku.add(GridPanelBuilder.buildSudokuPanel(sudokuPanel));
		sudoku.setVisible(true);
		return sudoku;
	}

	public static void validate() {
		emptyValuesExists = false;
		invalidValueExists = false;
		StringBuilder finalResult = new StringBuilder();
		finalResult.append(validateGridRules(getSudokuPanel()));
		finalResult.append(validateRowRules(getSudokuPanel()));
		finalResult.append(validateColumnRules(getSudokuPanel()));
		if(emptyValuesExists){
			JOptionPane.showMessageDialog(null, "Fields are empty!!! Fill them all...");
		}else if(invalidValueExists){
			JOptionPane.showMessageDialog(null, "All values should be between 1 and 9");
		}
		else if(finalResult.toString().length() == 0){
			JOptionPane.showMessageDialog(null, "You Won!!!");
		}else{
			JOptionPane.showMessageDialog(null, finalResult.toString());
		}
	}

	private static StringBuilder validateColumnRules(BasicPanel[][] sudokuPanel2) {
		// TODO Auto-generated method stub
		BasicPanel pivotBasicPanel;
		StringBuilder result = new StringBuilder();
		ArrayList<String> currentColValues = new ArrayList<String>();
		for (int masterCol = 0; masterCol < (GridLayoutBuilder.MAX_ROW * GridLayoutBuilder.MAX_COL); masterCol++) {
			for (int row = 0; row < GridLayoutBuilder.MAX_ROW; row++) {
				for (int col = 0; col < GridLayoutBuilder.MAX_COL; col++) {
					pivotBasicPanel = sudokuPanel[row][col];
					for (int index = 0; index < GridLayoutBuilder.MAX_ROW; index++) {
						currentColValues.addAll(pivotBasicPanel.getColumnValues(index));
					}
				}
			}
			if(countDuplicates(currentColValues).length() > 0){
				result.append("\nDuplicates at Column:"+masterCol+"-"+countDuplicates(currentColValues));
			}
		}

		return result;
	}

	private static StringBuilder countDuplicates(ArrayList<String> list) {
		StringBuilder result = new StringBuilder();
		Set<String> uniqueSet = new HashSet<String>(list);
		for (String temp : uniqueSet) {
			result.append(temp + ": " + Collections.frequency(list, temp));
		}
		return result;
	}

	private static StringBuilder validateRowRules(BasicPanel[][] sudokuPanel) {
		// TODO Auto-generated method stub

		BasicPanel pivotBasicPanel;
		StringBuilder result = new StringBuilder();
		ArrayList<String> currentRowValues = new ArrayList<String>();
		for (int masterRow = 0; masterRow < (GridLayoutBuilder.MAX_ROW * GridLayoutBuilder.MAX_COL); masterRow++) {
			for (int row = 0; row < GridLayoutBuilder.MAX_ROW; row++) {
				for (int col = 0; col < GridLayoutBuilder.MAX_COL; col++) {
					pivotBasicPanel = sudokuPanel[row][col];
					for (int index = 0; index < GridLayoutBuilder.MAX_ROW; index++) {
						currentRowValues.addAll(pivotBasicPanel.getRowValues(index));
					}
				}
			}
			if(countDuplicates(currentRowValues).length() > 0){
				result.append("\nDuplicates at row:"+masterRow+"-"+countDuplicates(currentRowValues));
			}
		}

		return result;
	}

	private static Object validateGridRules(BasicPanel[][] sudokuPanel) {
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		int currentGrid = 1;
		TextField inputTextField;
		BasicPanel pivotBasicPanel;
		// BasicPanel[][] sudokuPanel = getSudokuPanel();
		TextField[][] sudokuInputsArray;
		ArrayList<String> currentGridValues;
		for (int row = 0; row < GridLayoutBuilder.MAX_ROW; row++) {
			for (int col = 0; col < GridLayoutBuilder.MAX_COL; col++) {
				currentGridValues = new ArrayList<String>();
				pivotBasicPanel = sudokuPanel[row][col];
				System.out.println("@: " + row + ":" + col);
				sudokuInputsArray = pivotBasicPanel.getSudokuInputsArray();
				for (int inputRow = 0; inputRow < GridLayoutBuilder.MAX_ROW; inputRow++) {
					for (int inputCol = 0; inputCol < GridLayoutBuilder.MAX_COL; inputCol++) {
						inputTextField = sudokuInputsArray[inputRow][inputCol];
						System.out.println(inputTextField.getText());
						if(inputTextField.getText().length() == 0){
							emptyValuesExists  = true;
						}
						if(inputTextField.getText().length() > 0 && (Integer.parseInt(inputTextField.getText()) < 1 || Integer.parseInt(inputTextField.getText()) > 9)){
							invalidValueExists = true;
						}
						if (currentGridValues.contains(inputTextField.getText())
								&& inputTextField.getText().length() > 0) {
							result.append("Repeated value in grid:" + currentGrid + " value:" + inputTextField.getText()
									+ "\n");
							System.out.println("Repeated value in grid:" + currentGrid + " value:"
									+ inputTextField.getText() + "\n");
						} else {
							currentGridValues.add(inputTextField.getText());
						}
					}
				}
				currentGrid++;
			}
		}
		return result;
	}

	public static BasicPanel[][] getSudokuPanel() {
		return sudokuPanel;

	}

	public static EventListener getListener() {
		// TODO Auto-generated method stub
		return eventListener;
	}

}
