package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Button;
import javax.swing.AbstractListModel;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test extends JFrame {
	private JTextField addBoardTextField;
	private JTextField addNoteTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel parentPanel = new JPanel();
		getContentPane().add(parentPanel);
		parentPanel.setLayout(new MigLayout("", "[250px,leading][grow,trailing]", "[grow,center]"));
		
		JPanel leftPanel = new JPanel();
		parentPanel.add(leftPanel, "cell 0 0,grow");
		leftPanel.setLayout(new MigLayout("insets 0", "[grow]", "[20px,top][grow,bottom]"));
		
		JPanel addBoardPanel = new JPanel();
		leftPanel.add(addBoardPanel, "cell 0 0,growx,aligny center");
		addBoardPanel.setLayout(new MigLayout("insets 0", "[grow,leading][50px,trailing]", "[grow,fill]"));
		
		addBoardTextField = new JTextField();
		addBoardPanel.add(addBoardTextField, "cell 0 0,growx");
		addBoardTextField.setColumns(10);
		
		Button addBoardButton = new Button("+");
		addBoardButton.setMinimumSize(new Dimension(50, 0));
		addBoardPanel.add(addBoardButton, "cell 1 0");
		
		JList list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) ) {
					list.setSelectedIndex(list.locationToIndex(e.getPoint()));
					
					JPopupMenu popupMenu = new JPopupMenu();
					popupMenu.add(new JMenuItem("Click Me!"));
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
	            }
			}
		});
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Test 1", "Test 2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		leftPanel.add(list, "cell 0 1,grow");
		
		JPanel rightPanel = new JPanel();
		parentPanel.add(rightPanel, "cell 1 0,grow");
		rightPanel.setLayout(new MigLayout("insets 0", "[grow]", "[20px,center][grow]"));
		
		JPanel addNotePanel = new JPanel();
		rightPanel.add(addNotePanel, "cell 0 0,grow");
		addNotePanel.setLayout(new MigLayout("insets 0", "[grow][50px]", "[]"));
		
		addNoteTextField = new JTextField();
		addNotePanel.add(addNoteTextField, "cell 0 0,growx");
		addNoteTextField.setColumns(10);
		
		Button addNoteButton = new Button("+");
		addNoteButton.setMinimumSize(new Dimension(50, 0));
		addNoteButton.setEnabled(false);
		addNotePanel.add(addNoteButton, "cell 1 0");
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Note 1", "Note 2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		rightPanel.add(list_1, "cell 0 1,grow");
		setBounds(new Rectangle(0, 0, 1000, 500));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		setVisible(true);
		
	}
}