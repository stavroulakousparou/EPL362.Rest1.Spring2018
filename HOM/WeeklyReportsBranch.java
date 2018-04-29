package HOM;

/**
 *  Head Office Management.
 *  Weekly Report for each Branch.
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

import GUI.ConnectDB;
import GUI.loginPage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class WeeklyReportsBranch {

	private JFrame frame;
	private JTextField branch_txt;
	private JTable table;

	private String numOfClients = "";
	private String weekly = "";
	private String day = "";
	 
	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeeklyReportsBranch window = new WeeklyReportsBranch();
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
	public WeeklyReportsBranch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 569, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblWeeklyReportsPer = new JLabel("Weekly reports Per Branch");
		lblWeeklyReportsPer.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label = new JLabel("Branch ID: ");

		branch_txt = new JTextField();
		branch_txt.setColumns(10);

		JButton button = new JButton("Search");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				String branchID = branch_txt.getText();

				if (branchID.equals("")) {
					JOptionPane.showMessageDialog(frame, "Please enter a branch id! ");
					return;
				}

				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}

				boolean flag = false;

				String sqlReportBranch = "SELECT DAYOFWEEK(`Date`), COUNT(DISTINCT `ClientID`) FROM `Consultation` WHERE `Completed`=1 AND `BranchID`='" + branchID
						+ "'";
				PreparedStatement statem = null;

				try {
					statem = conn.prepareStatement(sqlReportBranch);
					ResultSet res = statem.executeQuery();

					while (res.next()) {
						weekly = res.getString(1);
						numOfClients = res.getString("COUNT(DISTINCT `ClientID`)");
						
						if (branchID.length() != 0 && numOfClients.length() != 0 && weekly != null) {
							flag = true;

							switch (weekly) {
							case "1": model.addRow(new Object[] { "Sunday", numOfClients });
									  break;
							
							case "2": model.addRow(new Object[] { "Monday", numOfClients });
							          break;
							 
							case "3": model.addRow(new Object[] { "Tuesday", numOfClients });
							          break;
							          
							case "4": model.addRow(new Object[] { "Wednesday", numOfClients });
							  break;
					
							case "5": model.addRow(new Object[] { "Tuesday", numOfClients });
					          break;
					 
							case "6": model.addRow(new Object[] { "Friday", numOfClients });
					          break;
					          
							case "7": model.addRow(new Object[] { "Saturday", numOfClients });
					          break;
									  
							}

							
						}
					}

					// when the user enter wrong
					if (!flag) {
						JOptionPane.showMessageDialog(frame, "WRONG branch id! Enter again!");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		JButton button_1 = new JButton("Back");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				HOMViewpoint f = new HOMViewpoint();
				f.main(null);
			}
		});

		JButton button_2 = new JButton("Logout");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblWeeklyReportsPer).addGap(173))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(57)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 93,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 93,
												GroupLayout.PREFERRED_SIZE))
								.addGap(29))))
				.addGroup(groupLayout.createSequentialGroup().addGap(106)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(124, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblWeeklyReportsPer).addGap(36)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(label))
										.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(branch_txt,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addComponent(button)))
						.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_1).addGap(18).addComponent(button_2)))
				.addGap(47)));

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null }, },
				new String[] { "WeekDay", "Num of Clients" }));
		frame.getContentPane().setLayout(groupLayout);
	}

}
