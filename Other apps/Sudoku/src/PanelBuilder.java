import java.awt.LayoutManager;

import javax.swing.JPanel;

public class PanelBuilder {
	
//	private static final int MAX_WIDTH = 100;
//	private static final int MAX_HEIGHT = 100;

	private PanelBuilder() {
	}

	public static JPanel buildPanel(LayoutManager layout) {

		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(layout);
		return basicPanel;
	}
}
