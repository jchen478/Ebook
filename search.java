

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import javax.swing.JFrame;

public class search {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search window = new search();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public search() {
		initialize();
	}
	private JComboBox combobox1;
	private JPanel boxPanel;
	private String selectStr; 
	private String fromStr;
	private String whereStr; 
	private String groupbyStr;
	private String orderbyStr;
	private JTextField searchStringText1;
	private JLabel lblInput;
	private JComboBox comboboxInput2;
	private JTextField searchStringText2;
	private JScrollPane scrollPane_2;
	private JTable table = new JTable();
	private JTable table2 = new JTable();
	private JTextField textFieldName;


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/**
		 * Objects
		 */
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(25, 22, 754, 534);
		frame.getContentPane().add(tabbedPane);

		boxPanel = new JPanel();
		tabbedPane.addTab("Book Search", null, boxPanel, null);
		boxPanel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 173, 683, 274);
		boxPanel.add(scrollPane_1);

		JButton btnUpdateResultsBoxSearch = new JButton("Update Results!");
		btnUpdateResultsBoxSearch.setBounds(519, 44, 137, 29);
		boxPanel.add(btnUpdateResultsBoxSearch);

		JLabel lblSearchString = new JLabel("Search Criteria 1");
		lblSearchString.setBounds(77, 49, 131, 16);
		boxPanel.add(lblSearchString);

		searchStringText1 = new JTextField();
		searchStringText1.setBounds(377, 44, 130, 26);
		boxPanel.add(searchStringText1);
		searchStringText1.setColumns(10);

		JComboBox comboboxInput1 = new JComboBox();
		comboboxInput1.setModel(new DefaultComboBoxModel(new String[] {"", "Author", "Book Title", "Publisher", "Seller"}));
		comboboxInput1.setBounds(200, 45, 160, 27);
		boxPanel.add(comboboxInput1);

		lblInput = new JLabel("Search Criteria 2");
		lblInput.setBounds(77, 81, 131, 16);
		boxPanel.add(lblInput);

		comboboxInput2 = new JComboBox();
		comboboxInput2.setModel(new DefaultComboBoxModel(new String[] {"", "Author", "Book Title", "Publisher", "Seller"}));
		comboboxInput2.setBounds(200, 77, 160, 27);
		boxPanel.add(comboboxInput2);

		searchStringText2 = new JTextField();
		searchStringText2.setBounds(377, 76, 130, 26);
		boxPanel.add(searchStringText2);
		searchStringText2.setColumns(10);

		JButton btnSeeBookDetails = new JButton("See Book Details");
		btnSeeBookDetails.setBounds(555, 453, 153, 29);
		boxPanel.add(btnSeeBookDetails);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(572, 132, 136, 29);
		boxPanel.add(scrollPane_2);

		JLabel lblPleaseSelectAt = new JLabel("Please select at least one search criteria and type in matching patterns. ");
		lblPleaseSelectAt.setBounds(25, 17, 683, 16);
		boxPanel.add(lblPleaseSelectAt);

		JLabel lblNumberOfRecords = new JLabel("Number of Records");
		lblNumberOfRecords.setBounds(577, 112, 131, 16);
		boxPanel.add(lblNumberOfRecords);

		JLabel lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setBounds(25, 155, 110, 16);
		boxPanel.add(lblSearchResults);

		JPanel statPanel = new JPanel();
		tabbedPane.addTab("Statistics", null, statPanel, null);
		statPanel.setLayout(null);

		JLabel lblStatisticsOf = new JLabel("Statistics of ");
		lblStatisticsOf.setBounds(19, 18, 88, 16);
		statPanel.add(lblStatisticsOf);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Author", "Publisher", "Seller"}));
		comboBox.setBounds(105, 14, 115, 27);
		statPanel.add(comboBox);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(231, 13, 117, 29);
		statPanel.add(btnUpdate);

		JScrollPane associatePane = new JScrollPane();
		associatePane.setBounds(254, 83, 457, 392);
		statPanel.add(associatePane);

		JLabel lblAssociatedBooks = new JLabel("Associated Books For ");
		lblAssociatedBooks.setBounds(267, 56, 139, 16);
		statPanel.add(lblAssociatedBooks);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Author", "Publisher", "Seller"}));
		comboBox_1.setBounds(408, 52, 104, 27);
		statPanel.add(comboBox_1);

		JLabel lblWithId = new JLabel("with name");
		lblWithId.setBounds(524, 56, 73, 16);
		statPanel.add(lblWithId);

		textFieldName = new JTextField();
		textFieldName.setBounds(596, 53, 115, 26);
		statPanel.add(textFieldName);
		textFieldName.setColumns(10);

		JScrollPane countPane = new JScrollPane();
		countPane.setBounds(16, 83, 205, 392);
		statPanel.add(countPane);

		JLabel lblResults = new JLabel("Results");
		lblResults.setBounds(18, 58, 61, 16);
		statPanel.add(lblResults);

		/**
		 * Methods
		 */

		// box search
		btnUpdateResultsBoxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dbConnect demoObj = new dbConnect();
				demoObj.Connection();

				// blank strings
				selectStr = ""; 
				fromStr = "";
				whereStr = "";


				String sqlQuery = ""; 
				ResultSet rs;
				selectStr = "select distinct book.bid as bookId, book.title as BookTitle ";

				if (comboboxInput1.getSelectedItem().equals("Book Title")) {

					whereStr += " where book.title LIKE " + "'%" + searchStringText1.getText() + "%'"; 
					fromStr += " from book " ;
					sqlQuery = selectStr + fromStr + whereStr + " order by book.bid";

				}

				// other entities -> book
				if (comboboxInput1.getSelectedItem().equals("Seller")) {
					selectStr += ", seller.sname as Seller"; 
					fromStr += " from sold,  ";  
					whereStr += " where sold.bid = book.bid "; 
					whereStr += " and sold.sid = seller.sid "; 
					whereStr += " AND seller.sname LIKE " + "'%" + searchStringText1.getText() + "%'"; 
				}
				else if (comboboxInput1.getSelectedItem().equals("Publisher")) {
					selectStr += ", publisher.pname as Publisher"; 
					fromStr += " from publish,  ";  
					whereStr += " where publish.bid = book.bid "; 
					whereStr += " and publish.pid = publisher.pid "; 
					whereStr += " AND publisher.pname LIKE " + "'%" + searchStringText1.getText() + "%'"; 
				}
				else if (comboboxInput1.getSelectedItem().equals("Author")) {
					selectStr += ", author.aname as Author"; 
					fromStr += " from writebook,  ";  
					whereStr += " where writebook.bid = book.bid "; 
					whereStr += " and writebook.aid = author.aid "; 
					whereStr += " and author.aname LIKE " + "'%" + searchStringText1.getText() + "%'"; 
				}		

				if (! comboboxInput1.getSelectedItem().equals("Book Title")) {
					fromStr += "book" + ", " + comboboxInput1.getSelectedItem(); 
					sqlQuery = selectStr + fromStr + whereStr + " order by book.bid";

				}

				if (!comboboxInput1.getSelectedItem().equals("")) {
					if (comboboxInput2.getSelectedItem().equals("")) {
						System.out.println(sqlQuery);
						rs = demoObj.simpleQuery(sqlQuery);		
						table.setModel(DbUtils.resultSetToTableModel(rs));
						scrollPane_1.setViewportView(table);
						String sqlQuery2 = "select count(1) as Results from (" + sqlQuery + ") as n";
						rs = demoObj.simpleQuery(sqlQuery2);
						table2.setModel(DbUtils.resultSetToTableModel(rs));
						table2.setTableHeader(null);
						scrollPane_2.setViewportView(table2);

					}
					else {

						selectStr = "select distinct n.bookId, n.bookTitle "; 
						fromStr = "from (" + sqlQuery + ") as n ";		

						if (comboboxInput2.getSelectedItem().equals("Author")) {
							fromStr += ",  writebook,  ";  
							whereStr = " where writebook.bid = n.bookId "; 
							whereStr += " and writebook.aid = author.aid "; 
							whereStr += " and author.aname LIKE " + "'%" + searchStringText2.getText() + "%'"; 
							fromStr += comboboxInput2.getSelectedItem(); 
						}

						if (comboboxInput2.getSelectedItem().equals("Publisher")) {
							fromStr += ",  publish,  ";  
							whereStr = " where publish.bid = n.bookId "; 
							whereStr += " and publish.pid = publisher.pid "; 
							whereStr += " AND publisher.pname LIKE " + "'%" + searchStringText2.getText() + "%'"; 
							fromStr += comboboxInput2.getSelectedItem(); 
						}


						if (comboboxInput2.getSelectedItem().equals("Seller")) {
							fromStr += ",  sold,  ";  
							whereStr = " where sold.bid = n.bookId "; 
							whereStr += " and sold.sid = seller.sid "; 
							whereStr += " AND seller.sname LIKE " + "'%" + searchStringText2.getText() + "%'"; 
							fromStr += comboboxInput2.getSelectedItem(); 
						}


						if (comboboxInput2.getSelectedItem().equals("Book Title")) {

							whereStr = " where n.bookTitle LIKE " + "'%" + searchStringText2.getText() + "%'"; 
						}

						sqlQuery = selectStr + fromStr + whereStr + " order by n.bookId";
						System.out.println(sqlQuery);
						rs = demoObj.simpleQuery(sqlQuery);		
						table.setModel(DbUtils.resultSetToTableModel(rs));
						scrollPane_1.setViewportView(table);
						String sqlQuery2 = "select count(1) as Results from (" + sqlQuery + ") as n";
						rs = demoObj.simpleQuery(sqlQuery2);
						table2.setModel(DbUtils.resultSetToTableModel(rs));
						table2.setTableHeader(null);
						scrollPane_2.setViewportView(table2);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please search using at least input 1 first.");
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dbConnect demoObj = new dbConnect();
				demoObj.Connection();

				// blank strings
				selectStr = " select " + comboBox.getSelectedItem();
				fromStr = " from book, " + comboBox.getSelectedItem();
				whereStr = "";
				groupbyStr = " group by " + comboBox.getSelectedItem();
				orderbyStr = " order by count(" + comboBox.getSelectedItem();

				String sqlQuery = ""; 
				ResultSet rs;

				if (comboBox.getSelectedItem().equals("Author")) {
					selectStr += ".aname as Author, count(author.aname) ";
					fromStr += ", writebook "; 
					whereStr += " where writebook.bid = book.bid and author.aid = writebook.aid"; 
					groupbyStr += ".aname"; 
					orderbyStr += ".aname) desc";
				}
				else if (comboBox.getSelectedItem().equals("Publisher")) {
					selectStr += ".pname as Publisher, count(publisher.pname) ";
					fromStr += ", publish "; 
					whereStr += " where publish.bid = book.bid and publisher.pid = publish.pid"; 
					groupbyStr += ".pname"; 
					orderbyStr += ".pname) desc";
				}
				else if (comboBox.getSelectedItem().equals("Seller")) {
					selectStr += ".sname as Seller, count(seller.sname) ";
					fromStr += ", sold "; 
					whereStr += " where sold.bid = book.bid and seller.sid = sold.sid"; 
					groupbyStr += ".sname"; 
					orderbyStr += ".sname) desc";
				}

				if (!comboBox.getSelectedItem().equals("")) {
					sqlQuery = selectStr + fromStr + whereStr + groupbyStr + orderbyStr;
					System.out.println(sqlQuery);
					rs = demoObj.simpleQuery(sqlQuery);		
					table.setModel(DbUtils.resultSetToTableModel(rs));
					countPane.setViewportView(table);
				}

				if (!comboBox_1.getSelectedItem().equals("")) {
					
					// blank strings
					selectStr = "";
					fromStr = "";
					whereStr = "";

					sqlQuery = ""; 
					String name = textFieldName.getText();
					if (name.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter a name.");
					}
					else {
						selectStr = "select b.title from " + comboBox_1.getSelectedItem() 
									+ ", book b ";
						if (comboBox_1.getSelectedItem().equals("Author")) {
							fromStr += ", writebook ";
							whereStr = " where " + comboBox_1.getSelectedItem() + ".aname LIKE '%" + name + "%'";
							whereStr += " and b.bid = writebook.bid and author.aid = writebook.aid";
						}
						else if (comboBox_1.getSelectedItem().equals("Publisher")) {
							fromStr += ", publish ";
							whereStr = " where " + comboBox_1.getSelectedItem() + ".pname LIKE '%" + name + "%'";
							whereStr += " and b.bid = publish.bid and publisher.pid = publish.pid";
						}
						else if (comboBox_1.getSelectedItem().equals("Seller")) {
							fromStr += ", sold ";
							whereStr = " where " + comboBox_1.getSelectedItem() + ".sname LIKE '%" + name + "%'";
							whereStr += " and b.bid = sold.bid and sold.sid = seller.sid";
						}
						sqlQuery = selectStr + fromStr + whereStr;
						System.out.println(sqlQuery);
						rs = demoObj.simpleQuery(sqlQuery);		
						table2.setModel(DbUtils.resultSetToTableModel(rs));
						associatePane.setViewportView(table2);
					}
				}
			}
		});

		// look up book details
		btnSeeBookDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new bookDetails().setVisible(true);;
			}
		});

	}
}
