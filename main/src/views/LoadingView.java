package views;

import javax.swing.JPanel;

import main.Main;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadingView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoadingView() {
		super();
		setLayout(new MigLayout("insets 0", "[grow][][grow]", "[grow][][grow]"));
		
		ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("tenor2.gif"));
		JLabel label = new JLabel(imgIcon);
		add(label, "cell 1 1");
		setBackground(Color.WHITE);
		Main.frame.setTitle("Loading...");
	}
}
