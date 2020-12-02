package views;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import main.Main;
import models.BoardModel;
import models.api.BoardAPI;
import models.library.NoteLibrary;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;


public class BoardView extends JPanel {
	private JTextField addBoardTextField;
	private JTextField addNoteTextField;
	
	private List<BoardModel> boards = new ArrayList<BoardModel>();
	private List<String> boardsName = new ArrayList<String>();
	
	public BoardView() {
		super();
		
		setLayout(new MigLayout("insets 5", "[250px][grow]", "[20px][grow]"));
		
		JPanel addBoardPanel = new JPanel();
		add(addBoardPanel, "cell 0 0,grow");
		addBoardPanel.setLayout(new MigLayout("inset 0", "[grow][50px]", "[]"));
		
		addBoardTextField = new JTextField();
		addBoardPanel.add(addBoardTextField, "cell 0 0,growx");
		addBoardTextField.setColumns(10);
		
		Button addBoardButton = new Button("+");
		addBoardButton.setMinimumSize(new Dimension(50, 0));
		addBoardPanel.add(addBoardButton, "cell 1 0");
		
		JPanel addNotePanel = new JPanel();
		add(addNotePanel, "cell 1 0,grow");
		addNotePanel.setLayout(new MigLayout("insets 0", "[grow][50px]", "[grow]"));
		
		addNoteTextField = new JTextField();
		addNotePanel.add(addNoteTextField, "cell 0 0,growx");
		addNoteTextField.setColumns(10);
		
		Button addNoteButton = new Button("+");
		addNoteButton.setMinimumSize(new Dimension(50, 0));
		addNotePanel.add(addNoteButton, "cell 1 0");
		
		JList boardList = new JList();
		boardList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) ) {
					boardList.setSelectedIndex(boardList.locationToIndex(e.getPoint()));
					
					JPopupMenu popupMenu = new JPopupMenu();
					popupMenu.add(new JMenuItem("Click Me!"));
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
	            }
			}
		});
		add(boardList, "cell 0 1,grow");
		
		JList noteList = new JList();
		noteList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Note 1", "Note 2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		add(noteList, "cell 1 1,grow");
		Main.frame.setTitle("Bảng");

		// Lấy dữ liệu boards
		BoardAPI.read().thenAccept(res -> {
			JSONObject resJSON = (JSONObject) res.data.get("boards");
			Iterator<String> resJSONkeys = resJSON.keys();
			try {
				while(resJSONkeys.hasNext()) {
				    String key = resJSONkeys.next();
			    	JSONObject ijsonObject = (JSONObject) resJSON.get(key);
			    	BoardModel iboard = new BoardModel();
			    	iboard.id = String.valueOf(ijsonObject.get("ID"));
			    	iboard.name = String.valueOf(ijsonObject.get("NAME"));
			    	iboard.date_created = String.valueOf(ijsonObject.get("created_at"));
			    	iboard.user_id = String.valueOf(ijsonObject.get("USER_ID"));
			    	boards.add(iboard);
			    	boardsName.add(iboard.name);
				}
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			boardList.setModel(new AbstractListModel() {
				String[] values;
				
				public int getSize() {
					values = new String[boardsName.size()];
					boardsName.toArray(values);
					return boards.size();
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			
			revalidate();
			repaint();
		});
	}
}
