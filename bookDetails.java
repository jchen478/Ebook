import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class bookDetails extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookDetails frame = new bookDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ResultSet rs;
	private JTable titleTable = new JTable();
	private JTable authorTable = new JTable();
	private JTable descriptTable = new JTable();
	private JTable priceTable = new JTable();
	private JTable dateTable = new JTable();
	private JTable sellerTable = new JTable();
	private JTable publishTable = new JTable();
	String shortDescript = "";
	/**
	 * Create the frame.
	 */
	public bookDetails() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane titlePane = new JScrollPane();
		titlePane.setBounds(18, 59, 403, 41);
		contentPane.add(titlePane);

		JLabel lblBid = new JLabel("bid");
		lblBid.setBounds(18, 20, 30, 16);
		contentPane.add(lblBid);

		textField = new JTextField();
		textField.setBounds(48, 15, 87, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JScrollPane authorPane = new JScrollPane();
		authorPane.setBounds(18, 112, 117, 67);
		contentPane.add(authorPane);

		JScrollPane datePane = new JScrollPane();
		datePane.setBounds(145, 154, 117, 41);
		contentPane.add(datePane);

		JScrollPane pricePane = new JScrollPane();
		pricePane.setBounds(145, 207, 117, 50);
		contentPane.add(pricePane);

		JScrollPane sellerPane = new JScrollPane();
		sellerPane.setBounds(18, 207, 117, 50);
		contentPane.add(sellerPane);


		JScrollPane publishPane = new JScrollPane();
		publishPane.setBounds(147, 111, 115, 34);
		contentPane.add(publishPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(274, 112, 147, 145);
		contentPane.add(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		JButton btnUpdate = new JButton("Refresh!");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dbConnect demoObj = new dbConnect();
				demoObj.Connection();

				String bid, sqlQuery;

				bid = textField.getText(); 
				sqlQuery = "select b.title as Title from book b "
						+ " where b.bid = " + bid;
				rs = demoObj.simpleQuery(sqlQuery);		
				titleTable.setModel(DbUtils.resultSetToTableModel(rs));
				titlePane.setViewportView(titleTable);

				sqlQuery = "select a.aname as Author from book b, writebook w, author a "
						+ " where b.bid = " + bid + " and w.bid = b.bid and a.aid = w.aid";
				rs = demoObj.simpleQuery(sqlQuery);		
				authorTable.setModel(DbUtils.resultSetToTableModel(rs));
				authorPane.setViewportView(authorTable);

				sqlQuery = "select sr.sname as Seller from book b, seller sr, sold s "
						+ " where b.bid = " + bid + " and s.bid = b.bid and sr.sid = s.sid";
				rs = demoObj.simpleQuery(sqlQuery);		
				sellerTable.setModel(DbUtils.resultSetToTableModel(rs));
				sellerPane.setViewportView(sellerTable);

				sqlQuery = "select s.price as Price from book b, sold s "
						+ " where b.bid = " + bid + " and s.bid = b.bid ";
				rs = demoObj.simpleQuery(sqlQuery);		
				priceTable.setModel(DbUtils.resultSetToTableModel(rs));
				pricePane.setViewportView(priceTable);

				sqlQuery = "select p.pdate as PublishDate from book b, publish p "
						+ " where b.bid = " + bid + " and p.bid = b.bid ";
				rs = demoObj.simpleQuery(sqlQuery);		
				dateTable.setModel(DbUtils.resultSetToTableModel(rs));
				datePane.setViewportView(dateTable);

				sqlQuery = "select pr.pname as Publisher from book b, publish p, publisher pr "
						+ " where b.bid = " + bid + " and p.bid = b.bid and pr.pid = p.pid";
				rs = demoObj.simpleQuery(sqlQuery);		
				publishTable.setModel(DbUtils.resultSetToTableModel(rs));
				publishPane.setViewportView(publishTable);

				sqlQuery = "select b.description as Description from book b "
						+ " where b.bid = " + bid;
				rs = demoObj.simpleQuery(sqlQuery);		

				try {
					rs.next();
					shortDescript = rs.getString(1);
					textArea.setText(shortDescript);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(145, 15, 117, 29);
		contentPane.add(btnUpdate);
	

	}
}
