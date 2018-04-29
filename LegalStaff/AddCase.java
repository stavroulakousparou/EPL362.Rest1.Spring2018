package LegalStaff;

/**
 * A legal staff add a new case.
 * 
 * */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUI.ConnectDB;
import GUI.loginPage;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCase {

	private JFrame frame;
	private JTextField details_txt;
	private JTextField client_txt;
	private JTextField lawyer_txt;
	
	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCase window = new AddCase();
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
	public AddCase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblCaseForm = new JLabel("Case Form");
		lblCaseForm.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblStrategy = new JLabel("Strategy");
		
		JComboBox cb_strategy = new JComboBox();
		cb_strategy.setModel(new DefaultComboBoxModel(new String[] {"Strategy1", "Strategy2", "Strategy3", "Strategy4", "Strategy5"}));
		
		JLabel lblDetails = new JLabel("Details");
		
		details_txt = new JTextField();
		details_txt.setColumns(10);
		
		JLabel lblIsMoneyLaudring = new JLabel("Is Money Laundring");
		
		JComboBox cb_ml = new JComboBox();
		cb_ml.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
		
		JLabel lblClient = new JLabel("Client ID");
		
		client_txt = new JTextField();
		client_txt.setColumns(10);
		
		JLabel lblLawyer = new JLabel("Lawyer");
		
		lawyer_txt = new JTextField();
		lawyer_txt.setColumns(10);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		JButton btnGoback = new JButton("Back");
		btnGoback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LegalStaffViewpoint f = new LegalStaffViewpoint();
				f.main(null);
			}
		});
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String strategy = cb_strategy.getSelectedItem().toString();
				String details = details_txt.getText();
				String ml = cb_ml.getSelectedItem().toString();
				String clientID = client_txt.getText();
				String lawyer = lawyer_txt.getText();
				
				
				//check for empty text boxes
				if(strategy.equals("") || details.equals("") || ml.equals("") || clientID.equals("") || lawyer.equals("")){
					JOptionPane.showMessageDialog(frame, "Fill all fields");
					return;
				}

				
				ConnectDB connection = new ConnectDB();
				conn = connection.getDBConnection();

				// If connection Failed
				if (conn == null) {
					System.out.println("Connection Failed");
				}
				
				String sql = "INSERT INTO `Case`(`Strategy`, `Details`, `Flagged_ml`, `ClientID`, `LawyerID`) VALUES ('" + strategy
						+ "', '" + details + "', '" + ml + "', '" + clientID + "', '" + lawyer + "')";
		
				PreparedStatement statement = null;
				
				
				try {
					 statement = conn.prepareStatement(sql);	 
                     statement.executeUpdate();

                     JOptionPane.showMessageDialog(frame, "Saved!");
                     
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStrategy)
						.addComponent(lblIsMoneyLaudring)
						.addComponent(lblDetails)
						.addComponent(lblClient)
						.addComponent(lblLawyer))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_ml, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_strategy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCaseForm)
						.addComponent(details_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(212, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(53, Short.MAX_VALUE)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(btnGoback, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblCaseForm)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStrategy)
						.addComponent(cb_strategy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDetails)
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(details_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblIsMoneyLaudring)
						.addComponent(cb_ml, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClient)
						.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLawyer)
						.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogout)
						.addComponent(btnGoback)
						.addComponent(btnSubmit))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	

}