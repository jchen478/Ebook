import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;


@SuppressWarnings("serial")
public class modify extends JFrame {
	
	dbConnect connect = new dbConnect();
	private JPanel contentPane;	
	private JComboBox comboBoxTitle;
	private JComboBox comboBoxPub;
	private JComboBox comboBoxSeller;
	private JComboBox comboBoxAuthor;
	private JTable publisherTable;
	private JTable sellerTable;
	private JTable authorTable;
	private JTable bookTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modify frame = new modify();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Builds and updates book.title combobox when called
	@SuppressWarnings("unchecked")
	public void fillComboBook() {
		try {
			comboBoxTitle.removeAllItems();
			connect.Connection();
			String sqlQuery ="Select title from book Order By title;";
			ResultSet rs = connect.simpleQuery(sqlQuery);
			while( rs.next()) {
				comboBoxTitle.addItem(rs.getObject("title"));				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//Builds and updates publisher.title combobox when called
	@SuppressWarnings("unchecked")
	public void fillComboPub() {
		try {
			comboBoxPub.removeAllItems();
			connect.Connection();
			String sqlQuery ="Select pname From publisher Order By pname;";
			ResultSet rs = connect.simpleQuery(sqlQuery);
			while( rs.next()) {
				comboBoxPub.addItem(rs.getObject("pname"));				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//Builds and updates seller.sname combobox when called
	@SuppressWarnings("unchecked")
	public void fillComboSeller() {
		try {
			connect.Connection();
			String sqlQuery ="Select sname From seller Order By sname;";
			ResultSet rs = connect.simpleQuery(sqlQuery);
			while( rs.next()) {
				comboBoxSeller.addItem(rs.getObject("sname"));				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//Builds and updates author.aname combobox when called
	@SuppressWarnings("unchecked")
	public void fillComboAuthor() {
		try {
			connect.Connection();
			String sqlQuery ="Select aname From author Order By aname;";
			ResultSet rs = connect.simpleQuery(sqlQuery);
			while( rs.next()) {
				comboBoxAuthor.addItem(rs.getObject("aname"));				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
		
	public modify() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 799, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.setBounds(5, 5, 771, 488);
		contentPane.add(tabbedPane);

		
////////////////////////////////////////Books////////////////////////////////////////
		
		JPanel book = new JPanel();
		tabbedPane.addTab("Book", null, book, null);
		book.setLayout(null);
		
		//Book combobox
		comboBoxTitle = new JComboBox();
		comboBoxTitle.setBounds(171, 14, 425, 25);
		book.add(comboBoxTitle);
		AutoCompleteDecorator.decorate(comboBoxTitle);
		fillComboBook();

		//Loads book from combobox into table
		JButton btnLoadBooks = new JButton("Load Books");
		btnLoadBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sTitle = (String) comboBoxTitle.getModel().getSelectedItem();
				System.out.println(sTitle);
				connect.Connection();
				String sqlQuery = "SELECT book.bid, book.title, book.length, book.description "
						       + "FROM book "
						       + " WHERE book.title LIKE " + "'%" + sTitle + "%'";
				System.out.println(sqlQuery);
				ResultSet bs = connect.simpleQuery(sqlQuery);
				bookTable.setModel(DbUtils.resultSetToTableModel(bs));	
			}
		});
		btnLoadBooks.setBounds(619, 13, 135, 25);
		book.add(btnLoadBooks);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 101, 742, 106);
		book.add(scrollPane);
		
		//Book table
		bookTable = new JTable();
		scrollPane.setViewportView(bookTable);
		bookTable.setRowHeight(50);
		bookTable.setFont(new Font("", 0, 14));

		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"bid", "title", "length", "description"
			}
		) {
			
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		//Updates database with changes made in table
		JButton btnTable = new JButton("Update Table");
		btnTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object tablebID =  bookTable.getValueAt(0, 0);
				String tableTitle = (String) bookTable.getValueAt(0, 1);
				Object tableLength =  bookTable.getValueAt(0, 2);
				String tableDesc = (String) bookTable.getValueAt(0, 3);
				connect.Connection();
				String sqlQuery = "UPDATE book SET title = '" + tableTitle + "', length = '" + tableLength + "', description = '" + tableDesc
								 + "' WHERE book.bid = " + tablebID;
				connect.exQuery(sqlQuery);
				comboBoxTitle.removeAllItems();
				fillComboBook();
				
			}
		});
		btnTable.setBounds(12, 220, 127, 25);
		book.add(btnTable);
		
		JLabel lblSelectBookTo = new JLabel("Select Book to Update");
		lblSelectBookTo.setBounds(12, 18, 147, 21);
		book.add(lblSelectBookTo);
		
////////////////////////////////////////Publisher////////////////////////////////////////
				
		JPanel publisher = new JPanel();
		tabbedPane.addTab("Publisher", null, publisher, null);
		publisher.setLayout(null);
		
		comboBoxPub = new JComboBox();
		comboBoxPub.setBounds(171, 14, 425, 25);
		publisher.add(comboBoxPub);
		AutoCompleteDecorator.decorate(comboBoxPub);
		fillComboPub();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 101, 742, 106);
		publisher.add(scrollPane_1);
		
		publisherTable = new JTable();
		scrollPane_1.setViewportView(publisherTable);
		publisherTable.setRowHeight(50);
		publisherTable.setFont(new Font("", 0, 14));

		publisherTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"pid", "pname"
			}
		) {
			
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnLoadPublisher = new JButton("Load Publisher");
		btnLoadPublisher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sPname = (String) comboBoxPub.getModel().getSelectedItem();
				System.out.println(sPname);
				connect.Connection();
				String sqlQueryPub ="SELECT publisher.pid, publisher.pname"
							      + " FROM publisher"
							      + " WHERE publisher.pname LIKE " + "'%" + sPname + "%'";
				System.out.println(sqlQueryPub);
				ResultSet ps = connect.simpleQuery(sqlQueryPub);
				publisherTable.setModel(DbUtils.resultSetToTableModel(ps));
			}
		});
				
		btnLoadPublisher.setBounds(619, 13, 135, 25);
		publisher.add(btnLoadPublisher);

		//Updates database with changes made in table
		JButton btnNewButton = new JButton("Update Publisher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object tablepID =  publisherTable.getValueAt(0, 0);
				String tablePname = (String) publisherTable.getValueAt(0, 1);
				connect.Connection();
				String sqlQuery = "UPDATE publisher SET pname = '" + tablePname 
								 + "' WHERE publisher.pid = " + tablepID;
				connect.exQuery(sqlQuery);
				comboBoxPub.removeAllItems();
				fillComboPub();
			}
		});
		btnNewButton.setBounds(12, 220, 135, 25);
		publisher.add(btnNewButton);
		
		JLabel lblSelectPublisherTo = new JLabel("Select Publisher to Update");
		lblSelectPublisherTo.setBounds(12, 18, 174, 21);
		publisher.add(lblSelectPublisherTo);
		
////////////////////////////////////////Seller////////////////////////////////////////
		
		JPanel seller = new JPanel();
		tabbedPane.addTab("Seller", null, seller, null);
		seller.setLayout(null);
		
		comboBoxSeller = new JComboBox();
		comboBoxSeller.setBounds(171, 14, 425, 25);
		seller.add(comboBoxSeller);
		AutoCompleteDecorator.decorate(comboBoxSeller);
		fillComboSeller();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 101, 742, 106);
		seller.add(scrollPane_2);
		
		sellerTable = new JTable();
		scrollPane_2.setViewportView(sellerTable);
		sellerTable.setRowHeight(50);
		sellerTable.setFont(new Font("", 0, 14));

		sellerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sid", "sname"
			}
		) {
			
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnLoadSeller = new JButton("Load Seller");
		btnLoadSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sSname = (String) comboBoxSeller.getModel().getSelectedItem();
				System.out.println(sSname);
				connect.Connection();
				String sqlQuerySeller = " SELECT seller.sid, seller.sname"
						              + " FROM seller"
						              + " WHERE seller.sname LIKE " + "'%" + sSname + "%'";
				System.out.println(sqlQuerySeller);
				ResultSet ss = connect.simpleQuery(sqlQuerySeller);
				sellerTable.setModel(DbUtils.resultSetToTableModel(ss));
			}
		});

		btnLoadSeller.setBounds(619, 13, 135, 25);
		seller.add(btnLoadSeller);
		
		//Updates database with changes made in table
		JButton btnNewButton_1 = new JButton("Update Seller");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object tablesID =  sellerTable.getValueAt(0, 0);
				String tableSname = (String) sellerTable.getValueAt(0, 1);
				connect.Connection();
				String sqlQuery = "UPDATE seller SET sname = '" + tableSname 
						 + "' WHERE seller.sid = " + tablesID;
				connect.exQuery(sqlQuery);
				comboBoxSeller.removeAllItems();
				fillComboSeller();
			}
		});
				
		btnNewButton_1.setBounds(12, 220, 127, 25);
		seller.add(btnNewButton_1);
		
		JLabel lblSelectSellerTo = new JLabel("Select Seller to Update");
		lblSelectSellerTo.setBounds(12, 18, 147, 21);
		seller.add(lblSelectSellerTo);
		fillComboSeller();
		

////////////////////////////////////////Author////////////////////////////////////////	
		
		JPanel author = new JPanel();
		tabbedPane.addTab("Author", null, author, null);
		author.setLayout(null);
		
		comboBoxAuthor = new JComboBox();
		comboBoxAuthor.setBounds(171, 14, 425, 25);
		author.add(comboBoxAuthor);
		AutoCompleteDecorator.decorate(comboBoxAuthor);
		fillComboAuthor();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 101, 742, 106);
		author.add(scrollPane_3);
		
		authorTable = new JTable();
		scrollPane_3.setViewportView(authorTable);
		authorTable.setRowHeight(50);
		authorTable.setFont(new Font("", 0, 14));

		authorTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"aid", "aname"
			}
		) {
			
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	
		JButton btnLoadAuthor = new JButton("Load Books");
		btnLoadAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sAname = (String) comboBoxAuthor.getModel().getSelectedItem();
				System.out.println(sAname);
				connect.Connection();
				String sqlQueryAuthor = " SELECT author.aid, author.aname"
						              + " FROM author"
						              + " WHERE author.aname LIKE " + "'%" + sAname + "%'";
				System.out.println(sqlQueryAuthor);
				ResultSet as = connect.simpleQuery(sqlQueryAuthor);
				authorTable.setModel(DbUtils.resultSetToTableModel(as));
			}
		});
		btnLoadAuthor.setBounds(619, 13, 135, 25);
		author.add(btnLoadAuthor);
		
		//Updates database with changes made in table
		JButton btnUpdateBooks = new JButton("Update Author");
		btnUpdateBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object tableaID =  authorTable.getValueAt(0, 0);
				String tableAname = (String) authorTable.getValueAt(0, 1);
				connect.Connection();
				String sqlQuery = "UPDATE author SET aname = '" + tableAname 
						 + "' WHERE author.aid = " + tableaID;
				connect.exQuery(sqlQuery);
				comboBoxAuthor.removeAllItems();
				fillComboAuthor();
			}
		});
		btnUpdateBooks.setBounds(12, 220, 127, 25);
		author.add(btnUpdateBooks);
		
		JLabel lblSelectAuthorTo = new JLabel("Select Author to Update");
		lblSelectAuthorTo.setBounds(12, 18, 147, 21);
		author.add(lblSelectAuthorTo);
	}
}

