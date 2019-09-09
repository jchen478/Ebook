import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;


public class books {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					books window = new books();
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
	public books() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 327);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Select Option", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 145, 418, 81);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(6, 16, 130, 50);
		panel_1.add(btnSearch);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(146, 16, 130, 50);
		panel_1.add(btnInsert);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(284, 16, 130, 50);
		panel_1.add(btnModify);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new modify().setVisible(true);
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search window = new search();
				JFrame searchFrame = window.frame;
				searchFrame.setVisible(true);
			}
		});
		
		JLabel lblBooksDatabase = new JLabel("Books Database");
		lblBooksDatabase.setFont(new Font("Garamond", Font.PLAIN, 20));
		lblBooksDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooksDatabase.setBounds(10, 11, 408, 28);
		panel.add(lblBooksDatabase);
	}
}
