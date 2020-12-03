package views;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.List;
import main.Main;
import models.BoardModel;
import models.NoteModel;
import models.api.BoardAPI;
import models.api.NoteAPI;
import models.library.SwingLibrary;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField addBoardTextField;
	private JTextField addNoteTextField;
	
	private List<BoardModel> boards = new ArrayList<BoardModel>();
	DefaultListModel<BoardModel> boardListModel;
	DefaultListModel<NoteModel> noteListModel;
	JList<BoardModel> boardList;
	JList<NoteModel> noteList;
	
	private BoardModel cboard;
	
	@SuppressWarnings("unchecked")
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
		
		boardList = new JList<BoardModel>();
		boardListModel = new DefaultListModel<BoardModel>();
		boardList.setModel(boardListModel);
					
		noteList = new JList<NoteModel>();
		noteListModel = new DefaultListModel<NoteModel>();
		noteList.setModel(noteListModel);
		
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
					JPopupMenu popupMenu = new BoardContextMenu(boards, temp_getBoardIndex, boardListModel);
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
	            }
				// Set hanh dong khi click chuot trai
				if (SwingUtilities.isLeftMouseButton(e) ) {
					// Lay ra index cua board hien tai
					int temp_getBoardIndex = boardList.locationToIndex(e.getPoint());
					boardList.setSelectedIndex(temp_getBoardIndex);
					// Them toan bo note cua board vao note list model
					noteListModel.clear();
					noteListModel.addAll(boards.get(temp_getBoardIndex).notes);
					// Set board hien tai
					cboard = boards.get(temp_getBoardIndex);
	            }
			}
		});
		
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
		Main.frame.setTitle("Bảng");

		// Lấy dữ liệu boards và notes
		BoardAPI.read().thenAccept(res -> {
			// Lay du lieu thanh cong thi them vao boards va cap nhat jlist
			if(res.status) {
				boards = (List<BoardModel>)res.data.get("boards");
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
				// Lay gia tri bang hien tai
				BoardModel progBoard = cboard;
				NoteModel model = new NoteModel(addNoteTextField.getText());
				// Kiểm tra trống
				if(model.name.length() == 0) return;
				// Reset text trong o nhap
				addNoteTextField.setText(null);
				// Cap nhat len server
				if(cboard != null) {
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
			}
		});
	}
}
