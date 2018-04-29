package HOM;

/**
 * Head Office Management.
 * Summary of the times that clients have had to wait for appointments.
 * 
 * */

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import GUI.ConnectDB;
import GUI.loginPage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AppTimeWait {

	private JFrame frame;
	private JTextField client_txt;
	private JTable table;
	
	private String appID;
	private String appDate;
	private String appTime;
	private String appLegalStID;
	private String appCaseID;
	private String appBranchID;
	private String appReco;
	private String appLegalOpinion;
	
	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppTimeWait window = new AppTimeWait();
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
	public AppTimeWait() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblClientTimesWaited = new JLabel("Completed Consultations");
		lblClientTimesWaited.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblEnterClientId = new JLabel("Client Id:");

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
					JOptionPane.showMessageDialog(frame, "lease enter a client id!");
					return;
				}

				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connectio Failes");
				}
				
				boolean flag = false;

				String sqlAppTimeWait = "SELECT * FROM Consultation WHERE ClientID ='" + clientID
						+ "' AND Completed = 1 ";
				PreparedStatement state = null;
				
				try {
					state = conn.prepareStatement(sqlAppTimeWait);
					ResultSet res = state.executeQuery();
					
					while (res.next()) {
						
						appID = res.getString("ConsultationId");
						appDate = res.getString("Date");
						appTime = res.getString("Time");
						appLegalStID = res.getString("LegalStaffID");
						appCaseID = res.getString("CaseID");
						appBranchID = res.getString("BranchID");
						appReco = res.getString("Recommendation");
						appLegalOpinion = res.getString("LegalOpinion");
						
						if (clientID.length() == 0) {
							flag = true;
							JOptionPane.showMessageDialog(frame, "No results!");
						} else if (clientID.length() != 0){
							flag = true;
							model.addRow(new Object[] { appID, appDate, appTime, clientID, appLegalStID, appCaseID, appBranchID, appReco, appLegalOpinion});
						}
					}
					
					// when the user enter wrong
					if(!flag) {
						JOptionPane.showMessageDialog(frame, "No results");
					}
				}catch(SQLException e1) {
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
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(467).addComponent(btnBack,
										GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblEnterClientId, GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, 86,
												GroupLayout.PREFERRED_SIZE)
										.addGap(57)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 86,
												GroupLayout.PREFERRED_SIZE)
										.addGap(132).addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 93,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup().addGap(207).addComponent(lblClientTimesWaited))
						.addGroup(groupLayout.createSequentialGroup().addGap(22).addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap().addComponent(lblClientTimesWaited).addGap(28)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE).addComponent(btnBack)
						.addGap(1)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(lblEnterClientId))
								.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(client_txt,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(button)
								.addGroup(groupLayout.createSequentialGroup().addGap(17).addComponent(btnLogout)))
						.addGap(48)));

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null }, },
						new String[] { "Appointment ID", "Date", "Time", "Client ID", "Legal Staff ID", "Case ID",
								"Branch ID", "Recommendation", "Legal Opinion" }));
		frame.getContentPane().setLayout(groupLayout);
	}

}
