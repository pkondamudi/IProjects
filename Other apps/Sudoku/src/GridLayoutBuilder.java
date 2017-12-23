import java.awt.GridLayout;
import java.awt.LayoutManager;

public class GridLayoutBuilder {
	

	public static final int MAX_ROW = 3;
	public static final int MAX_COL = 3;
	
	public static LayoutManager getBasicGrid() {
		// TODO Auto-generated method stub
		GridLayout panelGrid = new GridLayout(MAX_ROW, MAX_COL);
		
		return panelGrid;
	}
	public static LayoutManager getBasicSudokuGrid() {
		// TODO Auto-generated method stub
		GridLayout panelGrid = new GridLayout(MAX_ROW, MAX_COL);
		panelGrid.setHgap(5);
		panelGrid.setVgap(5);
		return panelGrid;
	}

}
