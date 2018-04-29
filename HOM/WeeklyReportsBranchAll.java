package HOM;
/**
 * Head Office Management.
 * Weekly Report for all Branches.
 * */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI.ConnectDB;
import GUI.loginPage;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class WeeklyReportsBranchAll {

	private JFrame frame;
	private JTextField branch_txt;
	private JTable table;
	
	private String numOfClients = "";
	private String weekly = "";
	 
	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeeklyReportsBranchAll window = new WeeklyReportsBranchAll();
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
	public WeeklyReportsBranchAll() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 571, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Branch Id:");
		
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

				String sqlReportBranch = "SELECT DAYOFWEEK(`Date`), COUNT(DISTINCT `ClientID`) FROM `Consultation` WHERE `BranchID`='" + branchID+"'";
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
		
		JLabel lblNewLabel = new JLabel("Weekly Reports Per Branch For All Days");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(139, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(110))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(421)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(86)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(23, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addGap(62))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(label))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(button))
						.addComponent(button_1))
					.addComponent(button_2)
					.addGap(49))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Day", "Num of Clients"
			}
		));
		frame.getContentPane().setLayout(groupLayout);
	}
	
}
