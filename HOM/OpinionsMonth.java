package HOM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI.ConnectDB;
import GUI.loginPage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JScrollPane;

/**
 * Head Office Management.
 * Summary of legal opinions given each month.
 * 
 * */
public class OpinionsMonth {

	private JFrame frame;
	private JTextField branch_txt;
	private JTable table;
	private String opinion = "";
	private static Connection conn = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpinionsMonth window = new OpinionsMonth();
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
	public OpinionsMonth() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame, and show up the values for the 
	 * branch that the user chosen.
	 * 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 589, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Branch id: ");

		branch_txt = new JTextField();
		branch_txt.setColumns(10);

		JButton button = new JButton("Refresh");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				String branchID = branch_txt.getText();
				
				if(branchID.equals("")){
					JOptionPane.showMessageDialog(frame, "Please enter a branch id!");
					return;
				}

				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connectio Failes");
				}
				
				boolean flag = false;

				String sqlOp = "SELECT * FROM Consultation WHERE BranchID='" + branchID + "'";
				PreparedStatement state = null;

				try {
					state = conn.prepareStatement(sqlOp);
					ResultSet res = state.executeQuery();

					// Get the strings from the database
					while (res.next()) {
						opinion = res.getString("LegalOpinion");

						if (opinion.length() == 0) {
							flag = true;
							JOptionPane.showMessageDialog(frame, "No results!");
						} else if (opinion.length() != 0){
							flag = true;
							model.addRow(new Object[] { opinion });
						}
					}

					// when the user enter wrong 
					if(!flag) {
						JOptionPane.showMessageDialog(frame, "WRONG branch id! Enter again!");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		JButton goBack = new JButton("GoBack");
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				HOMViewpoint f = new HOMViewpoint();
				f.main(null);
			}
		});

		JButton logOutBut = new JButton("LogOut");
		logOutBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Legal Opinions Per Month");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(logOutBut, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(goBack, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(56))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(176)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addContainerGap(200, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addComponent(goBack)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)
								.addComponent(button)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(logOutBut)))
					.addGap(47))
		);
		
				table = new JTable();
				scrollPane.setViewportView(table);
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Legal Opinions"
					}
				));
		frame.getContentPane().setLayout(groupLayout);
	}
	

}
