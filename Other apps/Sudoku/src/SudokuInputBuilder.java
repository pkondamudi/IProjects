import java.awt.TextField;
import java.util.ArrayList;
import java.util.Random;

public class SudokuInputBuilder {

	private static TextField[][] sudokuInputsArray;
	private static Random randomInputGenerator = new Random();

	public static TextField[][] perpareInputsAsArray() {
		String randomInput;
		TextField inputTextField;
		ArrayList<String> validInputs = new ArrayList<String>();
		sudokuInputsArray = new TextField[GridLayoutBuilder.MAX_ROW][GridLayoutBuilder.MAX_COL];
		for (int row = 0; row < GridLayoutBuilder.MAX_ROW; row++) {
			for (int col = 0; col < GridLayoutBuilder.MAX_COL; col++) {
				randomInput = Integer.toString(
						randomInputGenerator.nextInt((GridLayoutBuilder.MAX_ROW * GridLayoutBuilder.MAX_COL) + 1) + 0);
				inputTextField = new TextField();
				if (randomInput.equalsIgnoreCase(Integer.toString(row + col))) {
					if (!validInputs.contains(randomInput) && !randomInput.equalsIgnoreCase("0")) {
						inputTextField.setText(randomInput);
						inputTextField.setEditable(false);
						validInputs.add(randomInput);
					}
				}
				inputTextField.addActionListener(SudokuMaker.getListener());
				sudokuInputsArray[row][col] = inputTextField;
			}
		}
		return sudokuInputsArray;
	}
}
