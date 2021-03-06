package GUI;

import javax.swing.JOptionPane;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import LegalStaff.*;
import HOM.HOMViewpoint;
import LRS.LRSViewpoint;
import Receptionist.ReceptionistViewpoint;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginPage {

	private JFrame frame;
	private JTextField userName_txt;
	private JPasswordField password_txt;
	public String userNameU, typeEmp;

	//private String typeEmployee = "";

	private static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage window = new loginPage();
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
	public loginPage() {
		initialize();
	}

	public void checkLogIn(String username, String password, String type) {
		String message = "";

		// Establishing connection
		ConnectDB connection = new ConnectDB();
		conn = connection.getDBConnection();

		// If connection Failed
		if (conn == null) {
			System.out.println("Connection Failed");
		}

		if (type.compareTo("Legal Staff") == 0) {
			
			String sqlQuery = "SELECT * FROM LegalSt WHERE Username='" + username + "' AND Password = '" + password
					+ "' AND Type = 'LegalStaff'";
			PreparedStatement statement = null;

			try {
				// create and execute statement
				statement = conn.prepareStatement(sqlQuery);
				ResultSet result = statement.executeQuery();

				// Get the strings from the database
				if (result.next()) {

					userNameU = result.getString("Username");
				
					message = "welcome";
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid Crendentials");
					userName_txt.setText("");
					password_txt.setText("");
					message = "Invalid Crendentials";

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (type.compareTo("Receptionist") == 0) {
			
			String sqlQuery = "SELECT * FROM Receptionist WHERE Username='" + username + "' AND Password = '" + password
					+ "'";
			PreparedStatement statement = null;

			try {
				// create and execute statement
				statement = conn.prepareStatement(sqlQuery);
				ResultSet result = statement.executeQuery();

				// Get the strings from the database
				if (result.next()) {

					userNameU = result.getString("Username");
				
					message = "welcome";
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid Crendentials");
					userName_txt.setText("");
					password_txt.setText("");
					message = "Invalid Crendentials";

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (type.compareTo("Legal Records Staff") == 0) {
		
			String sqlQuery = "SELECT * FROM LegalSt WHERE Username='" + username + "' AND Password = '" + password
					+ "' AND Type = 'LegalRecordStaff'";
			PreparedStatement statement = null;

			try {
				// create and execute statement
				statement = conn.prepareStatement(sqlQuery);
				ResultSet result = statement.executeQuery();

				// Get the strings from the database
				if (result.next()) {

					userNameU = result.getString("Username");
				
					message = "welcome";
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid Crendentials");
					userName_txt.setText("");
					password_txt.setText("");
					message = "Invalid Crendentials";

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (type.equals("Head Office Management")) {
			String sqlQuery = "SELECT * FROM Lawyer WHERE Username='" + username + "' AND Password = '" + password
					+ "'";
			PreparedStatement statement = null;

			try {
				// create and execute statement
				statement = conn.prepareStatement(sqlQuery);
				ResultSet result = statement.executeQuery();

				// Get the strings from the database
				if (result.next()) {

					userNameU = result.getString("Username");
				
					message = "welcome";
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid Crendentials");
					userName_txt.setText("");
					password_txt.setText("");
					message = "Invalid Crendentials";

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
				
		if (message.compareTo("welcome") == 0) {

			if (type.equals("Legal Staff")) {
				frame.setVisible(false);
				LegalStaffViewpoint legalStaffView = new LegalStaffViewpoint();
				legalStaffView.main(null);

			} else if (type.equals("Receptionist")) {
				frame.setVisible(false);
				ReceptionistViewpoint recView = new ReceptionistViewpoint();
				recView.main(null);

			} else if (type.equals("Legal Records Staff")) {
				frame.setVisible(false);
				LRSViewpoint lrsView = new LRSViewpoint();
				lrsView.main(null);

			} else if (type.equals("Head Office Management")) {

				frame.setVisible(false);
				HOMViewpoint homView = new HOMViewpoint();
				homView.main(null);

			}

		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblUsername = new JLabel("Username:");
		JComboBox cb_type = new JComboBox();
		userName_txt = new JTextField();
		userName_txt.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");

		JButton btnLogin = new JButton("LogIn");

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// initialize variables
				String username = userName_txt.getText();
				String password = password_txt.getText();
				String type = cb_type.getSelectedItem().toString();

				// check for empty fields
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(frame, "Fill the fields");
					return;
				}

				checkLogIn(username, password, type);

			}
		});

		JLabel lblWelcomeToLawos = new JLabel("Welcome To Law Company");
		lblWelcomeToLawos.setFont(new Font("Arial", Font.BOLD, 18));

		password_txt = new JPasswordField();

		cb_type.setModel(new DefaultComboBoxModel(
				new String[] { "Legal Staff", "Receptionist", "Legal Records Staff", "Head Office Management" }));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(42)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(password_txt, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
								.addComponent(userName_txt, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
								.addComponent(cb_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(151))
				.addGroup(groupLayout.createSequentialGroup().addGap(92).addComponent(lblWelcomeToLawos)
						.addContainerGap(166, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(24).addComponent(lblWelcomeToLawos).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(userName_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(password_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(cb_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(50).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnLogin))
						.addContainerGap(30, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}

}
