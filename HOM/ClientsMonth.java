package HOM;

/**
 * Head Office Management.
 * The numbers of clients who attended each branch each month.
 * 
 * */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import GUI.ConnectDB;
import GUI.loginPage;

import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;

public class ClientsMonth {

	private JFrame frame;
	private JTextField branch_txt;
	private JTable table;
	
	private String month = "";
	private String client = "";
	private String dateSt = "";
	
	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientsMonth window = new ClientsMonth();
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
	public ClientsMonth() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblClientsPerBranch = new JLabel("Clients Per Branch Each Month");
		lblClientsPerBranch.setFont(new Font("Arial", Font.BOLD, 16));

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				String branchID = branch_txt.getText();
				
				if (branchID.equals("")) {
					JOptionPane.showMessageDialog(frame, "Please enter a branch id!");
					return;
				}

				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}
				
				boolean flag = false;
				
				String aqlClMo = "SELECT Date, COUNT(DISTINCT `ClientID`) FROM `Consultation` WHERE `BranchID`= '"
				+ branchID + "' GROUP BY MONTH(`Date`)";
				PreparedStatement state = null;
				
				try {
					state = conn.prepareStatement(aqlClMo);
					ResultSet res = state.executeQuery();

					// Get the strings from the database
					while (res.next()) {
						dateSt = res.getString("Date");
						System.out.println(dateSt);

						client = res.getString("COUNT(DISTINCT `ClientID`)");

						if (client.length() == 0 || dateSt.length() == 0) {
							flag = true;
							JOptionPane.showMessageDialog(frame, "No results!");
						} else if (client.length() != 0 || dateSt.length() != 0){
							flag = true;
							
							 String[] DateClient = dateSt.split("-");
					         month = DateClient[1];
							 
							model.addRow(new Object[] { month, client });
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

		JButton btnGoback = new JButton("GoBack");
		btnGoback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				HOMViewpoint f = new HOMViewpoint();
				f.main(null);
			}
		});

		JButton btnLogout = new JButton("LogOut");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});

		branch_txt = new JTextField();
		branch_txt.setColumns(10);

		JLabel lblEnterBranchId = new JLabel("Branch Id: ");

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(199).addComponent(lblClientsPerBranch)
						.addContainerGap(199, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(19)
						.addComponent(lblEnterBranchId, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE).addGap(18)
						.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(57).addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addGap(132)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnGoback, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addGap(57))
				.addGroup(groupLayout.createSequentialGroup().addGap(71)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(103, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblClientsPerBranch)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnGoback).addGap(18)
								.addComponent(btnLogout).addGap(43))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEnterBranchId).addComponent(btnRefresh))
								.addGap(60)))));

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Month", "Clients" }));
		frame.getContentPane().setLayout(groupLayout);
	}

	}
