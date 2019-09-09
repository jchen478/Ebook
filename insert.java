import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class insert {

	 public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insert window = new insert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
    private JButton btnNewButton;
    private JTable table;
    private JTextField bid;
    private JTextField pid;
    private JTextField pdate;
    private JTextField inputAll;
    private JButton btnNewButton_1;
    private JTextField title;
    private JTextField length;
    private JTextField description;
    private JButton btnNewButton_2;
    private JTextField wbid;
    private JTextField waid;
    private JButton btnNewButton_3;
    private JTextField sbid;
    private JTextField ssid;
    private JTextField sprice;
    
    
    /**
	 * Create the application.
	 */
    
	public insert() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
    
	private void initialize() {
		//whole frame of the application
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(130, 74, -114, 140);
		frame.getContentPane().add(tabbedPane);
		
		//Publish relation button
		//This will parse bid, pid, pdate in order.
		btnNewButton = new JButton("Create publish relation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbConnect demoObj = new dbConnect();
				demoObj.Connection();
				String sqlQuery5 = "select create_publish (" + bid.getText() + "," + pid.getText() + "," + "'" + pdate.getText() + "'" + ");";
				demoObj.simpleQuery(sqlQuery5);
			}
		});
		btnNewButton.setBounds(5, 163, 197, 27);
		frame.getContentPane().add(btnNewButton);
		
		bid = new JTextField();
		bid.setToolTipText("bid");
		bid.setBounds(246, 163, 116, 24);
		frame.getContentPane().add(bid);
		bid.setColumns(10);
		
		pid = new JTextField();
		pid.setToolTipText("pid");
		pid.setBounds(376, 163, 116, 24);
		frame.getContentPane().add(pid);
		pid.setColumns(10);
		
		pdate = new JTextField();
		pdate.setToolTipText("date");
		pdate.setBounds(508, 163, 116, 24);
		frame.getContentPane().add(pdate);
		pdate.setColumns(10);

		//Making a box for insert choice and getting input through JTextbox
		//and parse those to database query
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel (new String [] {"", "Author", "Publisher", "Seller"}));
		comboBox.setBounds(5, 29, 197, 36);
		frame.getContentPane().add(comboBox);
		
		inputAll = new JTextField();
		inputAll.setToolTipText("NAME");
		inputAll.setBounds(246, 29, 179, 36);
		frame.getContentPane().add(inputAll);
		inputAll.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbConnect demoObj = new dbConnect();
				demoObj.Connection();
				if(comboBox.getSelectedItem().equals("Author")) {
					String sqlQuery = "select insert_author(" + "'" + inputAll.getText() + "'" + ");" ;
					demoObj.simpleQuery(sqlQuery);
				}
				if(comboBox.getSelectedItem().equals("Publisher")) {
					String sqlQuery = "select insert_publisher(" + "'" + inputAll.getText() + "'" + ");" ;
					demoObj.simpleQuery(sqlQuery);
				}
				if(comboBox.getSelectedItem().equals("Seller")) {
					String sqlQuery = "select insert_seller(" + "'" + inputAll.getText() + "'" + ");" ;
					demoObj.simpleQuery(sqlQuery);
				}
			}
		});
		btnInsert.setBounds(456, 29, 138, 27);
		frame.getContentPane().add(btnInsert);
		
		//Insert button by using stored procedure
		btnNewButton_1 = new JButton("Insert Book");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbConnect demoObj = new dbConnect();
				demoObj.Connection();
				String Query = "select insert_book(" + "'" + title.getText() + "'" + "," + length.getText() + "," + "'" + description.getText() + "'" + ");";
				demoObj.simpleQuery(Query);
			}
		});
		btnNewButton_1.setBounds(5, 113, 197, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		title = new JTextField();
		title.setToolTipText("Title");
		title.setBounds(246, 113, 116, 24);
		frame.getContentPane().add(title);
		title.setColumns(10);
		
		length = new JTextField();
		length.setToolTipText("Length");
		length.setBounds(376, 113, 116, 24);
		frame.getContentPane().add(length);
		length.setColumns(10);
		
		description = new JTextField();
		description.setToolTipText("Description");
		description.setBounds(508, 113, 116, 24);
		frame.getContentPane().add(description);
		description.setColumns(10);
		
		//creating writebook relation through input and parsing input to database
		btnNewButton_2 = new JButton("Create writebook relation");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbConnect demoObj = new dbConnect();
				demoObj.Connection();
				String sqlQuery = "select create_writebook (" + wbid.getText() + "," + waid.getText() + ");";
				demoObj.simpleQuery(sqlQuery);
			}
		});
		btnNewButton_2.setBounds(5, 213, 197, 27);
		frame.getContentPane().add(btnNewButton_2);
		
		wbid = new JTextField();
		wbid.setToolTipText("bid");
		wbid.setBounds(246, 213, 116, 24);
		frame.getContentPane().add(wbid);
		wbid.setColumns(10);
		
		waid = new JTextField();
		waid.setToolTipText("aid");
		waid.setBounds(376, 213, 116, 24);
		frame.getContentPane().add(waid);
		waid.setColumns(10);
		
		//Creating sold relationship
		btnNewButton_3 = new JButton("Create sold relation");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbConnect demoObj = new dbConnect();
				demoObj.Connection();
				String sqlQuery = "select create_sold (" + sbid.getText() + "," + ssid.getText() +"," + sprice.getText() + ");";
				demoObj.simpleQuery(sqlQuery);
			}
		});
		btnNewButton_3.setBounds(5, 276, 197, 27);
		frame.getContentPane().add(btnNewButton_3);
		
		sbid = new JTextField();
		sbid.setToolTipText("bid");
		sbid.setBounds(246, 276, 116, 24);
		frame.getContentPane().add(sbid);
		sbid.setColumns(10);
		
		ssid = new JTextField();
		ssid.setToolTipText("sid");
		ssid.setBounds(376, 276, 116, 24);
		frame.getContentPane().add(ssid);
		ssid.setColumns(10);
		
		sprice = new JTextField();
		sprice.setToolTipText("Price");
		sprice.setBounds(508, 276, 116, 24);
		frame.getContentPane().add(sprice);
		sprice.setColumns(10);
		
		table = new JTable();

	}
}
