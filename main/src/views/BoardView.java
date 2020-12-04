package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Main;
import models.BoardModel;
import models.NoteModel;
import models.ObjectModel;
import models.api.BoardAPI;
import models.api.NoteAPI;
import models.library.SwingLibrary;
import net.miginfocom.swing.MigLayout;
import views.context.menu.BoardContextMenu;
import views.context.menu.NoteContextMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.text.SimpleDateFormat;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class BoardView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField addBoardTextField;
	private JTextField addNoteTextField;
	
	private List<BoardModel> boards = new ArrayList<BoardModel>();
	private DefaultListModel<BoardModel> boardListModel;
	private DefaultListModel<NoteModel> noteListModel;
	private JList<BoardModel> boardList;
	private JList<NoteModel> noteList;
	
	private BoardModel cboard;
	
	@SuppressWarnings("serial")
	private Map<String, Color> colorMap = new HashMap<String, Color>() {{
		put("red", new Color(243, 114, 120));
		put("orange", new Color(255, 201, 14));
		put("yellow", new Color(255, 242, 0));
		put("green", new Color(100, 225, 137));
		put("blue", new Color(62, 197, 255));
	}};
	
	@SuppressWarnings({ "serial" })
	private Map<String, Integer> colorMapPriority = new HashMap<String, Integer>() {{
		put("red", 1);
		put("orange", 2);
		put("yellow", 3);
		put("green", 4);
		put("blue", 5);
	}};
	
	private int sortColorPriority(String l, String r) {
		// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
    	if(colorMapPriority.containsKey(l) && colorMapPriority.containsKey(r)) {
    		if(colorMapPriority.get(l) < colorMapPriority.get(r)) return -1;
    		else if(colorMapPriority.get(l) > colorMapPriority.get(r)) return 1;
    		else return 0;
    	}
    	else if (colorMapPriority.containsKey(l)) return -1;
    	else if (colorMapPriority.containsKey(l)) return 1;
    	else return 0;
	}
	
	private int sortDate(String l, String r) {
		l = l.substring(0, 10) + l.substring(11, 19);
		r = r.substring(0, 10) + r.substring(11, 19);
		SimpleDateFormat dateFormMat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		Date ld, rd;
		// Convert string to date
		try {
		    ld = dateFormMat.parse(l);
		    rd = dateFormMat.parse(r);
		} catch (Exception e) {
		    e.printStackTrace();
		    return 0;
		}
		// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		if(ld.getTime() < rd.getTime()) return -1;
		else if(ld.getTime() > rd.getTime()) return 1;
		else return 0;
	}
	
	@SuppressWarnings({ "unchecked", "serial" })
	public BoardView() {
		super();
		
		setLayout(new MigLayout("insets 5", "[250px][grow]", "[20px][grow][20px]"));
		
		JPanel addBoardPanel = new JPanel();
		add(addBoardPanel, "cell 0 0,grow");
		addBoardPanel.setLayout(new MigLayout("inset 0", "[grow][50px]", "[]"));
		
		addBoardTextField = new JTextField();
		addBoardPanel.add(addBoardTextField, "cell 0 0,growx");
		addBoardTextField.setColumns(10);
		
		JButton addBoardButton = new JButton("+");
		
		addBoardButton.setMinimumSize(new Dimension(50, 0));
		addBoardPanel.add(addBoardButton, "cell 1 0");
		
		JPanel addNotePanel = new JPanel();
		add(addNotePanel, "cell 1 0,grow");
		addNotePanel.setLayout(new MigLayout("insets 0", "[grow][50px]", "[grow]"));
		
		addNoteTextField = new JTextField();
		addNotePanel.add(addNoteTextField, "cell 0 0,growx");
		addNoteTextField.setColumns(10);
		
		JButton addNoteButton = new JButton("+");
		addNoteButton.setMinimumSize(new Dimension(50, 0));
		addNotePanel.add(addNoteButton, "cell 1 0");
		
		boardList = new JList<BoardModel>();
		boardListModel = new DefaultListModel<BoardModel>();
		boardList.setModel(boardListModel);
					
		noteList = new JList<NoteModel>();
		noteListModel = new DefaultListModel<NoteModel>();
		noteList.setModel(noteListModel);
		
		noteList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Set hanh dong khi chuot phai vao board jlist
				if (SwingUtilities.isRightMouseButton(e) ) {
					// Lay ra index cua note hien tai
					int temp_getNoteIndex = noteList.locationToIndex(e.getPoint());
					noteList.requestFocus();
					noteList.setSelectedIndex(temp_getNoteIndex);
					// Hien popup
					JPopupMenu popupMenu = new NoteContextMenu(cboard.notes, temp_getNoteIndex, noteListModel);
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
	            }
			}
		});
		
		JScrollPane boardScrollPane = new JScrollPane(boardList);
		add(boardScrollPane, "cell 0 1,grow");
		
		JScrollPane noteScrollPane = new JScrollPane(noteList);
		
		add(noteScrollPane, "cell 1 1,grow");
		
		JCheckBox sortBoardByColor = new JCheckBox("Sắp xếp theo màu", null, Main.enableSortBoardByColor);
		add(sortBoardByColor, "flowx,cell 0 2");
		
		JCheckBox sortNoteByColor = new JCheckBox("Sắp xếp theo màu", null, Main.enableSortNoteByColor);
		add(sortNoteByColor, "cell 1 2");
		
		Main.frame.setTitle("Bảng");

		// Sort bảng theo màu sắc khi check vào box
		sortBoardByColor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// Nếu box đã được check thì sắp theo color
				if(e.getStateChange() == ItemEvent.SELECTED) {
					boards.sort(new Comparator<BoardModel>() {
					    @Override
					    public int compare(BoardModel lhs, BoardModel rhs) {
					        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
					    	return sortColorPriority(lhs.color, rhs.color);
					    }
					});
				}
				// Nếu box chưa check thì sắp theo ngày
				else {
					boards.sort(new Comparator<BoardModel>() {
					    @Override
					    public int compare(BoardModel lhs, BoardModel rhs) {
					        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
					    	return sortDate(lhs.date_created, rhs.date_created);
					    }
					});
				}
				boardListModel.clear();
				boardListModel.addAll(boards);
				// Update setting
				Main.enableSortBoardByColor = sortBoardByColor.isSelected();
			}
		});
		
		// Set màu cho board
		boardList.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if(colorMap.containsKey(boards.get(index).color)) {
					c.setBackground(colorMap.get(boards.get(index).color));
					c.setForeground(Color.DARK_GRAY);
				}
				if(cellHasFocus) {
					// Set chữ đậm
					Map<TextAttribute, Object> attributes = new HashMap<>();
					attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
					c.setFont(Font.getFont(attributes));
					
					// Set board hien tai
					cboard = boards.get(index);
					
					// Nếu nút sort by color được chọn thì sort theo màu
					if(sortNoteByColor.isSelected()) sortByColor(cboard.notes);
					// Nếu không thì sort theo ngày tạo
					else sortByDate(cboard.notes);
					// Them toan bo note cua board vao note list model
					noteListModel.clear();
					noteListModel.addAll(cboard.notes);
				}
				return c;
			}
		});
				
		// Set màu cho note
		noteList.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if(colorMap.containsKey(cboard.notes.get(index).color)) {
					c.setBackground(colorMap.get(cboard.notes.get(index).color));
					c.setForeground(Color.DARK_GRAY);
				}
				if(cellHasFocus) {
					// Set chữ đậm
					Map<TextAttribute, Object> attributes = new HashMap<>();
					attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
					c.setFont(Font.getFont(attributes));
				}
				return c;
			}
		});
				
		// Sort note theo màu sắc khi check vào box
		sortNoteByColor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// Nếu không có bảng nào được chọn thì không làm gì
				if(cboard == null) return;
				// Nếu box đã được check thì sắp theo color
				if(e.getStateChange() == ItemEvent.SELECTED) {
					cboard.notes.sort(new Comparator<NoteModel>() {
					    @Override
					    public int compare(NoteModel lhs, NoteModel rhs) {
					        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
					    	return sortColorPriority(lhs.color, rhs.color);
					    }
					});
				}
				// Nếu box chưa check thì sắp theo ngày
				else {
					cboard.notes.sort(new Comparator<NoteModel>() {
					    @Override
					    public int compare(NoteModel lhs, NoteModel rhs) {
					        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
					    	return sortDate(lhs.date_created, rhs.date_created);
					    }
					});
				}
				noteListModel.clear();
				noteListModel.addAll(cboard.notes);
				// Update setting
				Main.enableSortNoteByColor = sortNoteByColor.isSelected();
			}
		});

		// Lấy dữ liệu boards và notes
		BoardAPI.read().thenAccept(res -> {
			// Lay du lieu thanh cong thi them vao boards va cap nhat jlist
			if(res.status) {
				boards = (List<BoardModel>)res.data.get("boards");
				// Nếu nút sort by color được chọn thì sort theo màu
				if(Main.enableSortBoardByColor) sortByColor(boards);
				// Nếu không thì sort theo ngày tạo
				else sortByDate(boards);
				boardListModel.addAll(boards);
				for(BoardModel i: boards) {
					NoteAPI.read(i.id).thenAccept(ress -> {
						// Neu thanh cong thi them toan bo note vao board
						if(ress.status) {
							i.notes.addAll((List<NoteModel>)ress.data.get("notes"));
							// Kiem tra neu board hien tai dang bat thi cap nhat lai danh sach note
							if(cboard == i) {
								noteListModel.clear();
								noteListModel.addAll(i.notes);
							}
						}
						else {
							System.out.println(ress.message);
						}
					});
				}
			}
			// Neu xay ra loi thi hien popup
			else {
				SwingLibrary.alert(res.message);
			}
		});
		
		// Chèn hành động thêm board cho nút
		addBoardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardModel board = new BoardModel();
				board.name = addBoardTextField.getText();
				
				// Kiểm tra trống
				if(board.name.length() == 0) return;
				
				// Xoa thanh nhap
				addBoardTextField.setText(null);
				
				// Tạo board
				BoardAPI.create(board).thenAccept(res -> {
					// Nếu không có lỗi thì thôi
					if(res.status) {
						// Them board vao board list va cap nhat jlist
						boards.add(board);
						boardListModel.clear();
						boardListModel.addAll(boards);
						try {
							// Them thong tin duoc tao tren server vao board
							board.id = String.valueOf(res.data.get("id"));
							board.date_created = String.valueOf(res.data.get("date_created"));
							board.user_id = String.valueOf(res.data.get("user_id"));
						}
						catch(Exception e2) {
							System.out.println(e2);
						}
						
					}
					// Nếu có lỗi hiện popup
					else {
						SwingLibrary.alert(res.message);
					}
				});
			}
		});
		
		// Chèn hành động thêm note cho nút
		addNoteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Kiem tra xem da chon board chua
				if(cboard == null) return;
				// Lay gia tri bang hien tai
				BoardModel progBoard = cboard;
				NoteModel model = new NoteModel(addNoteTextField.getText());
				// Kiểm tra trống
				if(model.name.length() == 0) return;
				// Reset text trong o nhap
				addNoteTextField.setText(null);
				// Cap nhat len server
				NoteAPI.create(model, cboard.id).thenAccept(res -> {
					// Neu co loi thi hien popup loi
					if(!res.status) {
						SwingLibrary.alert(res.message);
					}
					// Nếu không có lỗi thì thôi
					else {
						// Them thong tin duoc tao tren server vao board
						model.id = String.valueOf(res.data.get("id"));
						model.date_created = String.valueOf(res.data.get("date_created"));
						model.board_id = String.valueOf(res.data.get("board_id"));
						// Them note moi vao board
						progBoard.notes.add(model);
						// Kiem tra neu bang hien tai dang bat thi cap nhat lai list note
						if(progBoard == cboard) {
							noteListModel.clear();
							noteListModel.addAll(cboard.notes);
						}
					}
				});
			}
		});
		
		boardList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Set hanh dong khi chuot phai vao board jlist
				if (SwingUtilities.isRightMouseButton(e) ) {
					// Lay ra index cua board hien tai
					int temp_getBoardIndex = boardList.locationToIndex(e.getPoint());
					boardList.requestFocus();
					boardList.setSelectedIndex(temp_getBoardIndex);
					// Hien popup
					JPopupMenu popupMenu = new BoardContextMenu(boards, temp_getBoardIndex, cboard, noteListModel, boardListModel);
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
	            }
			}
		});
	}
	
	private <T> void sortByColor(List<T> list) {
		list.sort(new Comparator<T>() {
		    @Override
		    public int compare(T lhs, T rhs) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		    	return sortColorPriority(((ObjectModel)lhs).color, ((ObjectModel)rhs).color);
		    }
		});
	}
	
	private <T> void sortByDate(List<T> list) {
		list.sort(new Comparator<T>() {
		    @Override
		    public int compare(T lhs, T rhs) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		    	return sortDate(((ObjectModel)lhs).date_created, ((ObjectModel)rhs).date_created);
		    }
		});
	}
}
