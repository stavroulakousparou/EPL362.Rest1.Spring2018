package HOM;

/**
 *  Head Office Management.
 *  Weekly Report for each Client.
 *  
 * */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import GUI.*;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;

public class WeeklyReportsClient {

	private JFrame frame;
	private JTextField client_txt;
	private JTable table;
	
	private String weekly = "";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeeklyReportsClient window = new WeeklyReportsClient();
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
	public WeeklyReportsClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 618, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblWeeklyReportsPer = new JLabel("Weekly Reports Per Client");
		lblWeeklyReportsPer.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblEnterClientId = new JLabel("Enter Client Id:");

		client_txt = new JTextField();
		client_txt.setColumns(10);

		JButton button = new JButton("Search");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				String clientID = client_txt.getText();
				if (clientID.equals("")) {
					JOptionPane.showMessageDialog(frame, "Enter a client id");
					return;
				}

				// Establishing connection
				ConnectDB connection = new ConnectDB();
				Connection conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				Statement stmt;

				try {
					stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(
							"SELECT DAYOFWEEK(`Date`), COUNT(DISTINCT `Recommendation`), COUNT(DISTINCT `LegalOpinion`) FROM `Consultation` WHERE (`ClientID`='"
									+ clientID + "' AND `Date` <= CURDATE()) GROUP BY DAYOFWEEK(`Date`)");

					if (rs.next()) {
						do {
							weekly = rs.getString(1);

							switch (weekly) {
							case "1": model.addRow(new Object[] { "Sunday", rs.getString(2),rs.getString(3) });
									  break;
							
							case "2": model.addRow(new Object[] { "Monday", rs.getString(2),rs.getString(3) });
							          break;
							 
							case "3": model.addRow(new Object[] { "Tuesday", rs.getString(2),rs.getString(3) });
							          break;
							          
							case "4": model.addRow(new Object[] { "Wednesday", rs.getString(2),rs.getString(3) });
							  break;
					
							case "5": model.addRow(new Object[] { "Tuesday", rs.getString(2),rs.getString(3) });
					          break;
					 
							case "6": model.addRow(new Object[] { "Friday", rs.getString(2),rs.getString(3) });
					          break;
					          
							case "7": model.addRow(new Object[] { "Saturday", rs.getString(2),rs.getString(3) });
					          break;
									  
							}
							
						} while (rs.next());
					} else
						JOptionPane.showMessageDialog(frame, "No results.");

				} catch (SQLException e1) {
					System.err.println("[!]Problem with requested statement");
					e1.printStackTrace();
				}
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				HOMViewpoint f = new HOMViewpoint();
				f.main(null);
			}
		});

		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(201)
							.addComponent(lblWeeklyReportsPer))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterClientId, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(33)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
									.addGap(53)
									.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWeeklyReportsPer)
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addComponent(btnLogout)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnterClientId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(btnBack))
					.addGap(94))
		);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Day of Week", "Num Of Recommendations", "Num of Legal Opinions" }));
		frame.getContentPane().setLayout(groupLayout);
	}

}
