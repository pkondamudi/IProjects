import java.awt.TextField;

import javax.swing.JPanel;

public class GridPanelBuilder {

	private GridPanelBuilder() {
	}

	public static JPanel buildBasicGridPanel(TextField[][] sudokuInputs) {
		JPanel basicPanel = PanelBuilder.buildPanel(GridLayoutBuilder.getBasicGrid());
		for (int row = 0; row < GridLayoutBuilder.MAX_ROW; row++) {
			for (int col = 0; col < GridLayoutBuilder.MAX_COL; col++) {
				basicPanel.add(sudokuInputs[row][col]);
			}
		}
		return basicPanel;
	}

	public static JPanel buildSudokuPanel(BasicPanel[][] sudokuPanel) {
		BasicPanel pivotBasicPanel;
		JPanel basicPanel = PanelBuilder.buildPanel(GridLayoutBuilder.getBasicSudokuGrid());
		for (int row = 0; row < GridLayoutBuilder.MAX_ROW; row++) {
			for (int col = 0; col < GridLayoutBuilder.MAX_COL; col++) {
			pivotBasicPanel = sudokuPanel[row][col];
			basicPanel.add(pivotBasicPanel.getBasicPanel());
			}
		}
		return basicPanel;
	}
}
